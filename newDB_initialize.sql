SET foreign_key_checks = 0;
-- 忽略外键并清空数据库
DROP TABLE
    IF EXISTS `admins`,
    `users`,
    `folders`,
    `files`,
    `comments`,
    `blobs`;
    
CREATE TABLE
    admins (
        id INT PRIMARY KEY AUTO_INCREMENT,
        username VARCHAR(50) NOT NULL UNIQUE,
        password VARCHAR(50) NOT NULL,
        permission ENUM('Read', 'Write', 'Delete') NOT NULL
    );

CREATE TABLE
    users (
        id INT PRIMARY KEY AUTO_INCREMENT,
        username VARCHAR(50) NOT NULL UNIQUE,
        password VARCHAR(50) NOT NULL,
        registration_date DATETIME,
        user_directory_id INT,
        favorites_folder_id INT,
        quota_limit INT,
        FOREIGN KEY (user_directory_id) REFERENCES folders(id),
        FOREIGN KEY (favorites_folder_id) REFERENCES folders(id)
    );

CREATE TABLE folders (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  parent_folder_id INT,
  folder_type ENUM('Regular', 'Shortcut', 'UserDirectory', 'Favorites') NOT NULL,
  shortcut_destination INT,
  quota_used INT DEFAULT 0,
  permission ENUM('Readable','Writable','Private','Inherited'),
  inherited_from_folder_id INT,
  creator_id INT,
  FOREIGN KEY (parent_folder_id) REFERENCES folders(id),
  FOREIGN KEY (shortcut_destination) REFERENCES folders(id),
  FOREIGN KEY (inherited_from_folder_id) REFERENCES folders(id),
  FOREIGN KEY (creator_id) REFERENCES users(id)
);

CREATE TABLE files (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  folder_id INT,
  file_type ENUM('Regular', 'Shortcut') NOT NULL,
  permission ENUM('Readable', 'Writable', 'Private', 'Inherited'),
  shortcut_destination INT,
 inherited_from_folder_id INT,
  creator_id INT,
  blob_id INT,
  FOREIGN KEY (folder_id) REFERENCES folders(id),
  FOREIGN KEY (shortcut_destination) REFERENCES files(id),
  FOREIGN KEY (inherited_from_folder_id) REFERENCES folders(id),
  FOREIGN KEY (creator_id) REFERENCES users(id),
  FOREIGN KEY (blob_id) REFERENCES blobs(id)
);

CREATE TABLE comments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    file_id INT,
    folder_id INT,
    parent_comment_id INT,
    user_id INT,
    message TEXT NOT NULL,
    posted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_reply_at DATETIME,
    FOREIGN KEY (file_id) REFERENCES files(id) ON DELETE CASCADE,
    FOREIGN KEY (folder_id) REFERENCES folders(id) ON DELETE CASCADE,
    FOREIGN KEY (parent_comment_id) REFERENCES comments(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE blobs (
        id INT PRIMARY KEY AUTO_INCREMENT,
        content BLOB NOT NULL,
        usage_count INT DEFAULT 0
    );
-- 建表语句结束
    
SET foreign_key_checks = 1;

-- 创建触发器
DELIMITER //

DROP TRIGGER IF EXISTS `AfterInsertOnUsers` //
CREATE TRIGGER AfterInsertOnUsers AFTER INSERT ON users FOR EACH ROW 
BEGIN 
	DECLARE userDirId INT;
	DECLARE favoritesId INT;
	-- 创建用户目录
	INSERT INTO
	    folders (
	        name,
	        parent_folder_id,
	        folder_type
	    )
	VALUES (
	        'User Directory',
	        NULL,
	        'UserDirectory'
	    );
	SET userDirId = LAST_INSERT_ID();
	-- 创建用户收藏夹
	INSERT INTO
	    folders (
	        name,
	        parent_folder_id,
	        folder_type
	    )
	VALUES ('Favorites', NULL, 'Favorites');
	SET favoritesId = LAST_INSERT_ID();
	-- 更新用户记录的目录和收藏夹ID
	UPDATE users
	SET
	    user_directory_id = userDirId,
	    favorites_folder_id = favoritesId
	WHERE id = NEW.id;
END // 

DROP TRIGGER IF EXISTS `AfterInsertOnFiles` //
CREATE TRIGGER AfterInsertOnFiles AFTER INSERT ON files FOR EACH ROW 
BEGIN 
	CALL CalculateQuotaUsage(NEW.folder_id);
END // 

DROP TRIGGER IF EXISTS `AfterUpdateOnFolders` //
CREATE TRIGGER AfterUpdateOnFolders AFTER UPDATE ON folders FOR EACH ROW 
BEGIN 
	-- inherit到非inherit的修改,子文件夹和子文件应该继承当前文件夹权限
	IF OLD.permission = 'Inherit'
	AND NEW.permission != 'Inherit' THEN -- 设置子文件夹的权限为继承当前
	UPDATE folders
	SET
	    permission = NEW.permission,
	    inherited_from_folder_id = NEW.id
	WHERE
	    parent_folder_id = OLD.id;
	-- 设置子文件的权限为继承当前
	UPDATE files
	SET
	    permission = NEW.permission,
	    inherited_from_folder_id = NEW.id
	WHERE folder_id = OLD.id;
	END IF;
	-- 当子文件夹的继承被修改(当前文件由非继承变为继承或继承的目标发生改变)
	IF OLD.permission != 'Inherit'
	AND (
	    NEW.permission = 'Inherit'
	    OR OLD.inherited_from_folder_id != NEW.inherited_from_folder_id
	) THEN -- 设置子文件夹的继承目标为此文件夹的继承目标
	UPDATE folders
	SET
	    inherited_from_folder_id = NEW.inherited_from_folder_id
	WHERE
	    parent_folder_id = OLD.id
	    AND permission = 'Inherit';
	-- 设置子文件的继承目标为此文件夹的继承目标
	UPDATE files
	SET
	    inherited_from_folder_id = NEW.inherited_from_folder_id
	WHERE
	    folder_id = OLD.id
	    AND permission = 'Inherit';
	END IF;
END // 


DROP TRIGGER IF EXISTS `AfterInsertOnComments` //
CREATE TRIGGER AfterInsertOnComments AFTER INSERT ON comments FOR EACH ROW 
BEGIN 
    DECLARE parent_id INT;
    DECLARE earliest_reply DATETIME;
    
    IF NEW.file_id IS NOT NULL THEN
        -- Update last_reply_at for the file
        SELECT MIN(posted_at) INTO earliest_reply
        FROM comments
        WHERE file_id = NEW.file_id;
        
        UPDATE files
        SET last_reply_at = earliest_reply
        WHERE id = NEW.file_id;
    ELSE
        -- Update last_reply_at for the folder
        SELECT MIN(posted_at) INTO earliest_reply
        FROM comments
        WHERE folder_id = NEW.folder_id;
        
        UPDATE folders
        SET last_reply_at = earliest_reply
        WHERE id = NEW.folder_id;
    END IF;
    
    SET parent_id = NEW.parent_comment_id;
    
    -- Find the earliest reply time for the parent comment
    WHILE parent_id IS NOT NULL DO
        SELECT MIN(posted_at) INTO earliest_reply
        FROM comments
        WHERE parent_comment_id = parent_id;
        
        IF NEW.file_id IS NOT NULL THEN
            -- Update last_reply_at for the file
            UPDATE files
            SET last_reply_at = earliest_reply
            WHERE id = NEW.file_id;
        ELSE
            -- Update last_reply_at for the folder
            UPDATE folders
            SET last_reply_at = earliest_reply
            WHERE id = NEW.folder_id;
        END IF;
        
        SET parent_id = (SELECT parent_comment_id FROM comments WHERE id = parent_id);
    END WHILE;
END //

-- 以下是存储过程

DROP PROCEDURE IF EXISTS `RegisterAdmin` //
CREATE PROCEDURE RegisterAdmin(
    IN adminName VARCHAR(50),
    IN adminPassword VARCHAR(50),
    OUT adminId INT
)
BEGIN
    INSERT INTO admins (username, password, permission)
    VALUES (adminName, adminPassword, 'Read');
    
    SET adminId = LAST_INSERT_ID();
END //

DROP PROCEDURE IF EXISTS `DeleteAdmin` //
CREATE PROCEDURE DeleteAdmin(
    IN adminId INT
)
BEGIN
    DELETE FROM admins WHERE id = adminId;
END //

DROP PROCEDURE IF EXISTS `RegisterUser` //
CREATE PROCEDURE RegisterUser(
    IN userName VARCHAR(50),
    IN userPassword VARCHAR(50),
    OUT userId INT
)
BEGIN
    INSERT INTO users (username, password, registration_date)
    VALUES (userName, userPassword, NOW());
    
    SET userId = LAST_INSERT_ID();
END //

DROP PROCEDURE IF EXISTS `DeleteUser` //
CREATE PROCEDURE DeleteUser(
    IN userId INT
)
BEGIN
    DELETE FROM users WHERE id = userId;
    -- 通过触发器删除用户目录和收藏夹
END //

DROP PROCEDURE IF EXISTS `RenameUser` //
CREATE PROCEDURE RenameUser(
    IN userId INT,
    IN newUserName VARCHAR(50),
    OUT success BOOLEAN
)
BEGIN
    DECLARE existingCount INT;
    
    SELECT COUNT(*) INTO existingCount
    FROM users
    WHERE username = newUserName;
    
    IF existingCount > 0 THEN
        SET success = FALSE;
    ELSE
        UPDATE users
        SET username = newUserName
        WHERE id = userId;
        
        SET success = TRUE;
    END IF;
END //

DROP PROCEDURE IF EXISTS `ChangeUserPassword` //
CREATE PROCEDURE ChangeUserPassword(
    IN userId INT,
    IN newUserPassword VARCHAR(50),
    OUT success BOOLEAN
)
BEGIN
    DECLARE currentPassword VARCHAR(50);
    
    SELECT password INTO currentPassword
    FROM users
    WHERE id = userId;
    
    IF newUserPassword = currentPassword OR LENGTH(newUserPassword) < 6 THEN
        SET success = FALSE;
    ELSE
        UPDATE users
        SET password = newUserPassword
        WHERE id = userId;
        
        SET success = TRUE;
    END IF;
END //

DROP PROCEDURE IF EXISTS `CreateFolder` //
CREATE PROCEDURE CreateFolder(
    IN parentFolderId INT,
    IN creatorId INT,
    OUT newFolderId INT
)
BEGIN
    DECLARE existingCount INT;
    
    SELECT COUNT(*) INTO existingCount
    FROM folders
    WHERE parent_folder_id = parentFolderId AND name = 'New Folder';
    
    IF existingCount > 0 THEN
        SET newFolderId = 0;
    ELSE
        INSERT INTO folders (name, parent_folder_id, folder_type, creator_id, permission)
        VALUES ('New Folder', parentFolderId, 'Regular', creatorId, 'Inherited');
        
        SET newFolderId = LAST_INSERT_ID();
    END IF;
END //

DROP PROCEDURE IF EXISTS `DownloadFile` //
CREATE PROCEDURE DownloadFile(
    IN fileId INT,
    OUT fileBlob BLOB,
    OUT errorMessage VARCHAR(100)
)
BEGIN
    -- Check if the file exists
    SELECT content INTO fileBlob
    FROM blobs
    WHERE id = (SELECT blob_id FROM files WHERE id = fileId);
    
    IF fileBlob IS NOT NULL THEN
        SET errorMessage = '';
    ELSE
        SET errorMessage = 'The file does not exist.';
    END IF;
END //

DROP PROCEDURE IF EXISTS `CreateFile` //
CREATE PROCEDURE CreateFile(
    IN parentFolderId INT,
    IN creatorId INT,
    IN fileName VARCHAR(100),
    IN fileContent BLOB,
    OUT newFileId INT,
    OUT errorMessage VARCHAR(100)
)
BEGIN
    DECLARE existingCount INT;
    
    -- Check if the file with the same name already exists in the parent folder
    SELECT COUNT(*) INTO existingCount
    FROM files
    WHERE folder_id = parentFolderId AND name = fileName;
    
    IF existingCount > 0 THEN
        SET newFileId = 0;
        SET errorMessage = 'A file with the same name already exists in the folder.';
    ELSE
        -- Insert the file record into the files table
        INSERT INTO files (name, folder_id, file_type, creator_id, blob_id, permission)
        VALUES (fileName, parentFolderId, 'Regular', creatorId, 0, 'Inherited');
        
        SET newFileId = LAST_INSERT_ID();
        
        -- Insert the file content into the blobs table
        INSERT INTO blobs (content) VALUES (fileContent);
        
        -- Update the blob_id of the file with the newly created blob's ID
        UPDATE files
        SET blob_id = LAST_INSERT_ID()
        WHERE id = newFileId;
        
        SET errorMessage = '';
    END IF;
END //


DROP PROCEDURE IF EXISTS `CreateFolderShortcut` //
CREATE PROCEDURE CreateFolderShortcut(
    IN parentFolderId INT,
    IN shortcutName VARCHAR(100),
    IN creatorId INT,
    IN targetFolderId INT,
    OUT newShortcutId INT
)
BEGIN
DECLARE existingCount INT;

SELECT COUNT(*) INTO existingCount
FROM folders
WHERE parent_folder_id = parentFolderId AND name = shortcutName;

IF existingCount > 0 THEN
    SET newShortcutId = 0;
ELSE
    INSERT INTO folders (name, parent_folder_id, folder_type, creator_id, shortcut_target_id, permission)
    VALUES (shortcutName, parentFolderId, 'Shortcut', creatorId, targetFolderId, 'Inherited');

    SET newShortcutId = LAST_INSERT_ID();
END IF;
END //

DROP PROCEDURE IF EXISTS `CreateFileShortcut` //
CREATE PROCEDURE CreateFileShortcut(
    IN parentFolderId INT,
    IN creatorId INT,
    IN fileName VARCHAR(100),
    IN targetFileId INT,
    OUT newShortcutId INT,
    OUT errorMessage VARCHAR(100)
)
BEGIN
    DECLARE existingCount INT;
    
    -- Check if the shortcut with the same name already exists in the parent folder
    SELECT COUNT(*) INTO existingCount
    FROM files
    WHERE folder_id = parentFolderId AND name = fileName;
    
    IF existingCount > 0 THEN
        SET newShortcutId = 0;
        SET errorMessage = 'A file or shortcut with the same name already exists in the folder.';
    ELSE
        -- Insert the shortcut record into the files table
        INSERT INTO files (name, folder_id, file_type, creator_id, shortcut_destination, permission)
        VALUES (fileName, parentFolderId, 'Shortcut', creatorId, targetFileId, 'Inherited');
        
        SET newShortcutId = LAST_INSERT_ID();
        SET errorMessage = '';
    END IF;
END //

DROP PROCEDURE IF EXISTS `RenameFolder` //
CREATE PROCEDURE RenameFolder(
    IN folderId INT,
    IN newFolderName VARCHAR(100),
    OUT success BOOLEAN
)
BEGIN
    DECLARE existingCount INT;

    SELECT COUNT(*) INTO existingCount
    FROM folders
    WHERE parent_folder_id = (SELECT parent_folder_id FROM folders WHERE id = folderId) AND name = newFolderName;

    IF existingCount > 0 THEN
        SET success = FALSE;
    ELSE
        UPDATE folders
        SET name = newFolderName
        WHERE id = folderId;

        SET success = TRUE;
    END IF;
END //

DROP PROCEDURE IF EXISTS `DeleteFolder` //
CREATE PROCEDURE DeleteFolder(
    IN folderId INT
)
BEGIN
    DELETE FROM folders WHERE id = folderId;
    -- 通过触发器级联删除所有子文件夹和文件
END //

DROP PROCEDURE IF EXISTS `CalculateQuotaUsage` //
CREATE PROCEDURE CalculateQuotaUsage(IN FOLDERID INT) BEGIN 
	DECLARE totalSize INT DEFAULT 0;
	SELECT
	    SUM(f.size) INTO totalSize
	FROM files f
	    JOIN folders fo ON fo.id = f.folder_id
	WHERE
	    f.folder_id = folderId
	    OR fo.parent_folder_id = folderId;
	UPDATE folders
	SET quota_used = totalSize
	WHERE id = folderId;
END // 

DROP PROCEDURE IF EXISTS `DeleteFile` //
CREATE PROCEDURE DeleteFile(
    IN fileId INT
)
BEGIN
    DELETE FROM files WHERE id = fileId;
END //

DROP PROCEDURE IF EXISTS `AddCommentToFile` //
CREATE PROCEDURE AddCommentToFile(
    IN fileId INT,
    IN parentCommentId INT,
    IN userId INT,
    IN commentMessage TEXT,
    OUT commentId INT,
    OUT errorMessage VARCHAR(100)
)
BEGIN
    DECLARE folderId INT;
    
    -- Check if the file exists
    SELECT folder_id INTO folderId
    FROM files
    WHERE id = fileId;
    
    IF folderId IS NULL THEN
        SET errorMessage = 'The file does not exist.';
    ELSE
        -- Insert the comment
        INSERT INTO comments (file_id, folder_id, parent_comment_id, user_id, message)
        VALUES (fileId, folderId, parentCommentId, userId, commentMessage);
        
        SET commentId = LAST_INSERT_ID();
        SET errorMessage = '';
    END IF;
END//

DROP PROCEDURE IF EXISTS `AddCommentToFolder` //
CREATE PROCEDURE AddCommentToFolder(
    IN folderId INT,
    IN parentCommentId INT,
    IN userId INT,
    IN commentMessage TEXT,
    OUT commentId INT,
    OUT errorMessage VARCHAR(100)
)
BEGIN
    -- Check if the folder exists
    IF NOT EXISTS (
        SELECT 1
        FROM folders
        WHERE id = folderId
    ) THEN
        SET errorMessage = 'The folder does not exist.';
    ELSE
        -- Insert the comment
        INSERT INTO comments (folder_id, parent_comment_id, user_id, message)
        VALUES (folderId, parentCommentId, userId, commentMessage);
        
        SET commentId = LAST_INSERT_ID();
        SET errorMessage = '';
    END IF;
END//

DROP PROCEDURE IF EXISTS `ReplyToComment` //
CREATE PROCEDURE ReplyToComment(
    IN parentCommentId INT,
    IN userId INT,
    IN commentMessage TEXT,
    OUT commentId INT,
    OUT errorMessage VARCHAR(100)
)
BEGIN
    -- Check if the parent comment exists
    IF NOT EXISTS (
        SELECT 1
        FROM comments
        WHERE id = parentCommentId
    ) THEN
        SET errorMessage = 'The parent comment does not exist.';
    ELSE
        -- Insert the reply
        INSERT INTO comments (parent_comment_id, user_id, message)
        VALUES (parentCommentId, userId, commentMessage);
        
        SET commentId = LAST_INSERT_ID();
        SET errorMessage = '';
    END IF;
END//

DROP PROCEDURE IF EXISTS `UpdateComment` //
CREATE PROCEDURE UpdateComment(
    IN commentId INT,
    IN commentMessage TEXT,
    OUT errorMessage VARCHAR(100)
)
BEGIN
    -- Check if the comment exists
    IF NOT EXISTS (
        SELECT 1
        FROM comments
        WHERE id = commentId
    ) THEN
        SET errorMessage = 'The comment does not exist.';
    ELSE
        -- Update the comment
        UPDATE comments
        SET message = commentMessage
        WHERE id = commentId;
        
        SET errorMessage = '';
    END IF;
END//

DROP PROCEDURE IF EXISTS `DeleteComment` //
CREATE PROCEDURE DeleteComment(
    IN commentId INT,
    OUT errorMessage VARCHAR(100)
)
BEGIN
    -- Delete the comment and its replies
    DELETE FROM comments
    WHERE id = commentId OR parent_comment_id = commentId;
    
    SET errorMessage = '';
END//

DELIMITER ;
