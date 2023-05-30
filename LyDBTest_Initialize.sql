/*Active: 1681389348096@@8.130.77.8@3306@LyDBTest*/

/*重置服务器的数据库*/

USE LyDBTest;
SET foreign_key_checks = 0;

DROP TABLE
    IF EXISTS `Administrator`,
    `Blob_Usage`,
    `Blobs`,
    `Comments`,
    `Files`,
    `Files_Files`,
    `Files_Tags`,
    `Tag_Types`,
    `Tags`,
    `Users`;

SET foreign_key_checks = 1;
/*生成各个表*/

/*管理员表*/

CREATE TABLE
    `Administrator`(
        `Adm_Id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        `Adm_Name` VARCHAR(32) NOT NULL,
        `Adm_Password` VARCHAR(32) NOT NULL,
        `Adm_Permission` INT(11) NOT NULL DEFAULT 0
    );

/*用户表*/

CREATE TABLE
    `Users`(
        `User_Id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        `User_Name` VARCHAR(32) NOT NULL,
        `User_Password` VARCHAR(32) NOT NULL,
        `User_FileList` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Files`(`File_Id`)*/
        `User_Favorites` INT(11) UNSIGNED NULL DEFAULT NULL/*<-`Files`(`File_Id`)*/
        /*
        INDEX `User_FileList_Index` (`User_FileList` ASC),
        INDEX `User_Favorites_Index` (`User_Favorites` ASC)
        */
    );

/*文件与文件夹表*/

CREATE TABLE
    `Files`(
        `File_Id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        `File_Type` INT NOT NULL,
        `File_Name` VARCHAR(64) NOT NULL,
        `File_Creator` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Users`(`User_Id`)*/
        `File_Update` TIMESTAMP NOT NULL,
        `File_Download_Count` INT(11) NULL DEFAULT NULL,
        `File_Citation_Count` INT(11) NULL DEFAULT NULL,
        `File_Size` INT(11) NULL DEFAULT NULL,
        `File_Level` INT(11) NULL DEFAULT NULL,
        `File_Icon` INT(11) NOT NULL DEFAULT 0,
        `File_Icon_Custom` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Blobs`(`Blob_Id`)*/
        `File_Comments` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Comments`(`Comment_Id`)*/
        `File_Data` INT(11) UNSIGNED NULL DEFAULT NULL/*<-`Blobs`(`Blob_Id`)*/
        /*
        INDEX `File_Creator_Index` (`File_Creator` ASC),
        INDEX `File_Icon_Custom_Index` (`File_Icon_Custom` ASC),
        INDEX `File_Comments_Index` (`File_Comments` ASC),
        INDEX `File_Data_Index` (`File_Data` ASC)
        */
    );

/*父文件-子文件关联表*/

CREATE TABLE
    `Files_Files`(
        `Parent` INT(11) UNSIGNED NOT NULL,/*<-`Files`(`File_Id`)*/
        `SubFile` INT(11) UNSIGNED NOT NULL,/*<-`Files`(`File_Id`)*/
        PRIMARY KEY(`Parent`, `SubFile`),
        /*索引:按父文件的id建立索引*/
        INDEX `Parent_Index` (`Parent` ASC)
    );

/*留言表*/

CREATE TABLE
    `Comments`(
        `Comment_Id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        `Comment_Type` INT(11) NOT NULL DEFAULT 0,
        `Comment_Creator` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Users`(`User_Id`)*/
        `Comment_Upd` DATETIME NOT NULL,
        /*更新时间,包括收到回复和回复收到回复,以及自身的修改*/
        `Comment_Mod` DATETIME NOT NULL,
        /*修改时间,仅包含自身的修改时间*/
        `Comment_Msg` TEXT NULL DEFAULT NULL,
        `Comment_Parent` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Comments`(`Comment_Id`)*/
        INDEX `Comment_Creator_Index` (`Comment_Creator` ASC),
        INDEX `Comment_Parent_Index` (`Comment_Parent` ASC)
    );

/*文件实例表*/

CREATE TABLE
    `Blobs`(
        `Blob_Id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        `Blob_Length` INT(11) UNSIGNED NULL,
        `Blob_Next` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Blobs`(`Blob_Id`)*/
        `Blob_Content` BLOB NOT NULL
    );

/*标签类型表*/

CREATE TABLE
    `Tag_Types`(
        `Tag_Type_Id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        `Tag_Type_Name` VARCHAR(32) NOT NULL UNIQUE,
        /*按名字建立索引*/
        INDEX `Tag_Type_Index` (`Tag_Type_Name` ASC)
    );

/*标签表*/

CREATE TABLE
    `Tags`(
        `Tag_Id` INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        `Tag_Name` VARCHAR(32) NOT NULL UNIQUE,
        `Tag_Type` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Tag_Types`(`Tag_Type_Id`)*/
        /*按名字建立索引*/
        INDEX `Tag_Name_Index` (`Tag_Name` ASC),
        /*按分类建立索引*/
        INDEX `Tag_Type_Index` (`Tag_Type` ASC)
    );

/*文件-标签关联表*/

CREATE TABLE
    `Files_Tags`(
        `File` INT(11) UNSIGNED NOT NULL,/*<-`Files`(`File_Id`)*/
        `Tag` INT(11) UNSIGNED NOT NULL,/*<-`Tags`(`Tag_Id`)*/
        PRIMARY KEY(`File`, `Tag`),
        /*按文件id建立索引*/
        INDEX `File_Index` (`File` ASC)
    );

/*Blob实体使用表*/

CREATE TABLE
    `Blob_Usage`(
        `Blob` INT(11) UNSIGNED NOT NULL,/*<-`Blobs`(`Blob_Id`)*/
        `Usage_As_FileContent` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Files`(`File_Id`)*/
        `Usage_As_FileIcon` INT(11) UNSIGNED NULL DEFAULT NULL,/*<-`Files`(`File_Id`)*/
        PRIMARY KEY(`Blob`,`Usage_As_FileContent`,`Usage_As_FileIcon`)
    );

/*添加外键约束的属性*/
    /*ALTER TABLE <数据表名> ADD CONSTRAINT <外键名> FOREIGN KEY(<列名>) REFERENCES <主表名> (<列名>);*/
    
    ALTER TABLE `Users` ADD CONSTRAINT `U_F_File_List` FOREIGN KEY(`User_FileList`) REFERENCES `Files`(`File_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT ;
    ALTER TABLE `Users` ADD CONSTRAINT `U_F_Favorites` FOREIGN KEY(`User_Favorites`) REFERENCES `Files`(`File_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT ;
    
    ALTER TABLE `Files` ADD CONSTRAINT `F_U_File_Creator` FOREIGN KEY(`File_Creator`) REFERENCES `Users`(`User_Id`) ON DELETE SET NULL ON UPDATE RESTRICT ;
    ALTER TABLE `Files` ADD CONSTRAINT `F_B_File_Icon_Custom` FOREIGN KEY(`File_Icon_Custom`) REFERENCES `Blobs`(`Blob_Id`) ON DELETE RESTRICT ON UPDATE CASCADE ;
    ALTER TABLE `Files` ADD CONSTRAINT `F_C_File_Comments` FOREIGN KEY(`File_Comments`) REFERENCES `Comments`(`Comment_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT ;
    ALTER TABLE `Files` ADD CONSTRAINT `F_B_File_Data` FOREIGN KEY(`File_Data`) REFERENCES `Blobs`(`Blob_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT ;
    
    ALTER TABLE `Files_Files` ADD CONSTRAINT `F_F_Parent_Id` FOREIGN KEY(`Parent`) REFERENCES `Files`(`File_Id`) ON DELETE CASCADE ON UPDATE RESTRICT ;
    ALTER TABLE `Files_Files` ADD CONSTRAINT `F_F_SubFile_Id` FOREIGN KEY(`SubFile`) REFERENCES `Files`(`File_Id`) ON DELETE CASCADE ON UPDATE RESTRICT ;
    
    ALTER TABLE `Comments` ADD CONSTRAINT `C_U_Creator` FOREIGN KEY(`Comment_Creator`) REFERENCES `Users`(`User_Id`) ON DELETE SET NULL ON UPDATE CASCADE ;
    ALTER TABLE `Comments` ADD CONSTRAINT `C_C_Parent` FOREIGN KEY(`Comment_Parent`) REFERENCES `Comments`(`Comment_Id`) ON DELETE CASCADE ON UPDATE RESTRICT ;
    
    ALTER TABLE `Blobs` ADD CONSTRAINT `B_B_Blob_Next` FOREIGN KEY(`Blob_Next`) REFERENCES `Blobs`(`Blob_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT ;
    
    ALTER TABLE `Tags` ADD CONSTRAINT `T_TT_Tag_Type` FOREIGN KEY(`Tag_Type`) REFERENCES `Tag_Types`(`Tag_Type_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT ;
    
    ALTER TABLE `Files_Tags` ADD CONSTRAINT `FT_F_File` FOREIGN KEY(`File`) REFERENCES `Files`(`File_Id`) ON DELETE CASCADE ON UPDATE CASCADE ;
    ALTER TABLE `Files_Tags` ADD CONSTRAINT `FT_T_Tag` FOREIGN KEY(`Tag`) REFERENCES `Tags`(`Tag_Id`) ON DELETE CASCADE ON UPDATE CASCADE ;
    
    ALTER TABLE `Blob_Usage` ADD CONSTRAINT `B_Id` FOREIGN KEY(`Blob`) REFERENCES `Blobs`(`Blob_Id`) ON DELETE RESTRICT ON UPDATE RESTRICT ;
    ALTER TABLE `Blob_Usage` ADD CONSTRAINT `F_B_Content` FOREIGN KEY(`Usage_As_FileContent`) REFERENCES `Blobs`(`Blob_Id`) ON DELETE CASCADE ON UPDATE CASCADE ;
    ALTER TABLE `Blob_Usage` ADD CONSTRAINT `F_B_Icon` FOREIGN KEY(`Usage_As_FileIcon`) REFERENCES `Blobs`(`Blob_Id`) ON DELETE CASCADE ON UPDATE CASCADE ;

/*生成视图*/
DROP VIEW IF EXISTS `Files_Files_SubFileName`;
CREATE VIEW `Files_Files_SubFileName` AS 
SELECT  `Files_Files`.`Parent`,`Files_Files`.`SubFile`,`Files`.`File_Name`
FROM  `Files_Files`
LEFT OUTER JOIN  `Files`
ON  `Files_Files`.`SubFile` = `Files`.`File_Id` AND `Files`.`File_Type` = '3';
/*生成存储过程和函数*/
DELIMITER $$

DROP PROCEDURE IF EXISTS `User_Reg`$$
CREATE PROCEDURE `User_Reg`(IN `U_Name` VARCHAR(32),IN `U_PASSWORD` VARCHAR(32),OUT `NEW_UID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
	INSERT INTO `Users`(`User_Name`, `User_Password`) VALUES (`U_Name`, `U_Password`);
	SELECT last_insert_id() INTO `NEW_UID`;
END$$

DROP PROCEDURE IF EXISTS `User_DeReg`$$
CREATE PROCEDURE `User_DeReg`(IN `U_Id` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    DELETE FROM `Users` WHERE `Users`.`User_Id` = `U_Id`;
END$$

DROP PROCEDURE IF EXISTS `User_Rename`$$
CREATE PROCEDURE `User_Rename`(IN `U_Id` INT(11) UNSIGNED,IN `U_NewName` VARCHAR(32)) DETERMINISTIC
BEGIN
    UPDATE `Users` SET `Users`.`User_Name` = `U_NewName` WHERE `Users`.`User_Id` = `U_Id`;
END$$

DROP PROCEDURE IF EXISTS `User_SetPassword`$$
CREATE PROCEDURE `User_SetPassword`(IN `U_Id` INT(11) UNSIGNED,IN `U_NewPassword` VARCHAR(32)) DETERMINISTIC
BEGIN
    UPDATE `Users` SET `Users`.`User_Password` = `U_NewPassword` WHERE `Users`.`User_Id` = `U_Id`;
END$$

DROP PROCEDURE IF EXISTS `File_CreateFolder`$$
CREATE PROCEDURE `File_CreateFolder`(IN `F_NAME` VARCHAR(64),IN `F_CREATOR` INT(11) UNSIGNED,IN `F_Path` INT(11) UNSIGNED,OUT `NEW_ID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    CALL Private_File_CreateFile('4',`F_NAME`,`F_CREATOR`,`NEW_ID`);
    CALL Private_File_Binding(`F_Path`,`NEW_ID`);
END$$

DROP PROCEDURE IF EXISTS `File_Overwrite`$$
CREATE PROCEDURE `File_Overwrite`(IN `F_Id` INT(11) UNSIGNED,IN `File_Data` BLOB) DETERMINISTIC
BEGIN
    DECLARE `NEW_BLOB` INT(11) UNSIGNED;
    CALL `Private_Blob_Create`(`File_Data`,`NEW_BLOB`);
    UPDATE `Files` SET `Files`.`File_Data` = `NEW_BLOB` WHERE ( `Files`.`File_Id` = `F_Id` );
END$$

DROP PROCEDURE IF EXISTS `File_Delete`$$
CREATE PROCEDURE `File_Delete`(IN `F_Id` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    IF( NOT (EXISTS (SELECT * FROM `Users` WHERE (`Users`.`User_FileList` = `F_Id`) OR (`Users`.`User_FileList` = `F_Id`))) )THEN
        CALL Private_File_Delete_Cascade(`F_Id`);
    END IF;
END$$

DROP PROCEDURE IF EXISTS `File_Rename`$$
CREATE PROCEDURE `File_Rename`(IN `F_Id` INT(11) UNSIGNED,IN `F_NewName` VARCHAR(64)) DETERMINISTIC
BEGIN
    UPDATE `Files` SET `Files`.`File_Name` = `F_NewName` WHERE ( `Files`.`File_Id` = `F_Id` );
END$$

DROP PROCEDURE IF EXISTS `File_AddTag`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `File_DelTag`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `File_SetIcon`$$
CREATE PROCEDURE `File_SetIcon`(IN `F_Id` INT(11) UNSIGNED,IN `F_Icon` INT(11) UNSIGNED,IN `F_Icon_Custom` BLOB) DETERMINISTIC
BEGIN
    DECLARE `NEW_Blob` INT(11) UNSIGNED DEFAULT NULL;
    IF(`F_Icon_Custom` IS NULL)THEN
        BEGIN
        UPDATE `Files` SET `Files`.`File_Icon` = `F_Icon`, `Files`.`File_Icon_Custom` = NULL
        WHERE ( `Files`.`File_Id` = `F_Id` );
        END;
    ELSE
        BEGIN
        CALL Private_Blob_Create(`F_Icon_Custom`,`NEW_Blob`);
        UPDATE `Files` SET `Files`.`File_Icon` = '0', `Files`.`File_Icon_Custom` = `NEW_Blob`
        WHERE ( `Files`.`File_Id` = `F_Id` );
        END;
    END IF;
END$$

DROP PROCEDURE IF EXISTS `File_Copy`$$
CREATE PROCEDURE `File_Copy`(IN `F_Path` INT(11) UNSIGNED,IN `F_Source` INT(11) UNSIGNED,IN `F_CREATOR` INT(11) UNSIGNED,OUT `NEW_ID` INT(11) UNSIGNED) DETERMINISTIC 
BEGIN
    DECLARE `NEW_Comment` INT(11) UNSIGNED;
    DECLARE `SourceFile_Name` VARCHAR(64);
    DECLARE `NEW_Comment_Name` VARCHAR(64);
    INSERT INTO `Files`(
        `File_Type`,
        `File_Name`,
        `File_Creator`,
        `File_Size`,
        `File_Level`,
        `File_Icon`,
        `File_Icon_Custom`,
        `File_Comments`,
        `File_Data`)
    SELECT 
        `Files`.`File_Type`,
        `Files`.`File_Name`,
        `F_CREATOR`,
        `Files`.`File_Size`,
        `Files`.`File_Level`,
        `Files`.`File_Icon`,
        `Files`.`File_Icon_Custom`,
        NULL,
        `Files`.`File_Data`
    FROM `Files` WHERE `Files`.`File_Id` = `F_Source`;
    SELECT `File_Name` INTO `SourceFile_Name` FROM `Files` WHERE `Files`.`File_Id` = `F_Source`;
    SELECT CONCAT(`SourceFile_Name`,"_Comments") INTO `NEW_Comment_Name`;
    SELECT last_insert_id() INTO `NEW_ID`;
    CALL Private_Comment_Create_Area('0',`F_CREATOR`,`NEW_Comment_Name`,NULL,`NEW_Comment`);
    UPDATE `Files` SET `File_Comments` = `NEW_Comment` WHERE `Files`.`File_Id` = `NEW_Comment`;
    CALL Private_File_Binding(`F_Path`,`NEW_ID`);
END$$

DROP PROCEDURE IF EXISTS `File_Citation`$$
CREATE PROCEDURE `File_Citation`(IN `F_Path` INT(11) UNSIGNED,IN `F_Source` INT(11) UNSIGNED) DETERMINISTIC 
BEGIN
    CALL Private_File_Binding(`F_Path`,`F_Source`);
END$$

DROP PROCEDURE IF EXISTS `File_Move`$$
CREATE PROCEDURE `File_Move`(IN `F_MoveFrom` INT(11) UNSIGNED,IN `F_MoveTo` INT(11) UNSIGNED,IN `F_Source` INT(11) UNSIGNED) DETERMINISTIC 
BEGIN
    CALL Private_File_Binding(`F_MoveTo`,`F_Source`);
    CALL Private_File_Unbind(`F_MoveFrom`,`F_Source`);
END$$

DROP PROCEDURE IF EXISTS `File_Upload`$$
DROP PROCEDURE IF EXISTS `File_Download`$$

DROP PROCEDURE IF EXISTS `Private_File_CreateFile`$$
CREATE PROCEDURE `Private_File_CreateFile`(IN `F_TYPE` INT(11) UNSIGNED,IN `F_NAME` VARCHAR(64),IN `F_CREATOR` INT(11) UNSIGNED,OUT `NEW_ID` INT(11) UNSIGNED) DETERMINISTIC 
BEGIN
    /*
        创建文件存储过程,输出新建文件的ID,
        可用的FILE_TYPE有4个:1用户根目录,2用户收藏夹,3常规文件,4文件夹
    */ 
    IF (`F_CREATOR` IS NULL) THEN
        INSERT INTO `Files` (`File_Type`, `File_Name`, `File_Creator`) VALUES (`F_TYPE`, `F_NAME`, NULL);
    ELSE
        INSERT INTO `Files` (`File_Type`, `File_Name`, `File_Creator`) VALUES (`F_TYPE`, `F_NAME`, `F_CREATOR`);
    END IF;
    SELECT last_insert_id() INTO `NEW_ID`;
END$$ 

DROP PROCEDURE IF EXISTS `Private_File_Binding`$$
CREATE PROCEDURE `Private_File_Binding`(IN `F_PATH` INT(11) UNSIGNED,IN `F_Id` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    INSERT INTO `Files_Files`(`Parent`,`SubFile`) VALUES (`F_PATH`, `F_Id`);
END$$

DROP PROCEDURE IF EXISTS `Private_File_Unbind`$$
CREATE PROCEDURE `Private_File_Unbind`(IN `F_PATH` INT(11) UNSIGNED,IN `F_Id` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    DELETE FROM `Files_Files` WHERE `Files_Files`.`Parent`=`F_PATH` AND `Files_Files`.`SubFile`=`F_Id`;
END$$

DROP PROCEDURE IF EXISTS `Private_File_Deletion_Management`$$
CREATE PROCEDURE `Private_File_Deletion_Management`(IN `F_Id` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    CALL Private_File_Delete_Restrict(`F_Id`);
END$$

DROP PROCEDURE IF EXISTS `Private_File_Delete_Restrict`$$
CREATE PROCEDURE `Private_File_Delete_Restrict`(IN `F_Id` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    IF( NOT ((EXISTS (SELECT * FROM `Files_Files` WHERE `Files_Files`.`SubFile` = `F_Id`)) OR (EXISTS (SELECT * FROM `Users` WHERE (`Users`.`User_FileList` = `F_Id`) OR (`Users`.`User_FileList` = `F_Id`)))) )THEN
        DELETE FROM `Files_Files` WHERE `Files_Files`.`Parent`=`F_Id`;
        DELETE FROM `Files` WHERE `Files`.`File_Id` = `F_Id`;
    END IF;
END$$

DROP PROCEDURE IF EXISTS `Private_File_Delete_Cascade`$$
CREATE PROCEDURE `Private_File_Delete_Cascade`(IN `F_Id` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    IF( NOT (EXISTS (SELECT * FROM `Users` WHERE (`Users`.`User_FileList` = `F_Id`) OR (`Users`.`User_FileList` = `F_Id`))) )THEN
        DELETE FROM `Files_Files` WHERE `Files_Files`.`SubFile`=`F_Id`;
        CALL Private_File_Delete_Restrict(`F_Id`);
    END IF;
END$$

DROP PROCEDURE IF EXISTS `Comment_Publish`$$
CREATE PROCEDURE `Comment_Publish`(IN `C_Creator` INT(11) UNSIGNED,IN `C_Msg` TEXT,IN `C_Parent` INT(11) UNSIGNED,OUT `NEW_ID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    CALL Private_Comment_Create_Area('1',`C_Creator`,`C_Msg`,`C_Parent`,`NEW_ID`);
END$$

DROP PROCEDURE IF EXISTS `Comment_Modify`$$
CREATE PROCEDURE `Comment_Modify`(IN `C_Id` INT(11) UNSIGNED,IN `C_Msg` TEXT) DETERMINISTIC
BEGIN
    UPDATE `Comments` SET `Comments`.`Comment_Msg` = `C_Msg` WHERE `Comments`.`Comment_Id` = `C_Id`;
    CALL Private_Comment_Upd_Cascade(`C_Id`);
END$$

DROP PROCEDURE IF EXISTS `Comment_Delete`$$
CREATE PROCEDURE `Comment_Delete`(IN `C_ID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    CALL Private_Comment_Delete_Area(`C_ID`);
END$$

DROP PROCEDURE IF EXISTS `Private_Comment_Create_Area`$$
CREATE PROCEDURE `Private_Comment_Create_Area`(IN `C_Type` INT(11),IN `C_Creator` INT(11) UNSIGNED,IN `C_Msg` TEXT,IN `C_Parent` INT(11) UNSIGNED,OUT `NEW_ID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    IF (`C_Type` IS NULL) THEN
        SET `C_Type` = '0';
    END IF;
    INSERT INTO `Comments` (`Comment_Type`,`Comment_Creator`,`Comment_Msg`,`Comment_Parent`) VALUES (`C_Type`, `C_Creator`, `C_Msg`, `C_Parent`);
    SELECT last_insert_id() INTO `NEW_ID`;
    CALL Private_Comment_Upd_Cascade(`NEW_ID`);
END$$ 

DROP PROCEDURE IF EXISTS `Private_Comment_Delete_Area`$$
CREATE PROCEDURE `Private_Comment_Delete_Area`(IN `C_ID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    DELETE FROM `Comments` WHERE `Comments`.`Comment_Id` = `C_ID`;
END$$

DROP PROCEDURE IF EXISTS `Private_Comment_Upd_Cascade`$$
CREATE PROCEDURE `Private_Comment_Upd_Cascade`(IN `CURRENT_ID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    WHILE (`CURRENT_ID` IS NOT NULL) DO
        UPDATE `Comments` SET `Comment_Upd` = sysdate() WHERE `Comments`.`Comment_Id` = `CURRENT_ID`;
        SELECT `Comment_Parent` INTO `CURRENT_ID` FROM `Comments` WHERE `Comments`.`Comment_Id` = `CURRENT_ID`;
    END WHILE;
END$$

DROP PROCEDURE IF EXISTS `Private_Blob_Create`$$
CREATE PROCEDURE `Private_Blob_Create`(IN `B_CONTENT` BLOB,OUT `NEW_ID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    INSERT INTO `Blobs` (`Blob_Content`) VALUES (`B_CONTENT`);
    SELECT last_insert_id() INTO `NEW_ID`;
END$$

DROP PROCEDURE IF EXISTS `Private_Blob_Binding`$$
CREATE PROCEDURE `Private_Blob_Binding`(IN `B_Id` INT(11) UNSIGNED,IN `F_Id_UseAsContent` INT(11) UNSIGNED,IN `F_Id_UseAsIcon` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    IF(`B_Id` IS NOT NULL) THEN
        BEGIN
        IF (`F_Id_UseAsContent` IS NOT NULL) THEN
            INSERT INTO `Blob_Usage` (`Blob`, `Usage_As_FileContent`, `F_Id_UseAsIcon`) VALUES (`B_Id`, `F_Id_UseAsContent`, NULL);
        END IF;
        IF (`F_Id_UseAsIcon` IS NOT NULL) THEN
            INSERT INTO `Blob_Usage` (`Blob`, `Usage_As_FileContent`, `F_Id_UseAsIcon`) VALUES (`B_Id`, NULL, `F_Id_UseAsIcon`);
        END IF;
        END;
    END IF;
END$$

DROP PROCEDURE IF EXISTS `Private_Blob_Unbind`$$
CREATE PROCEDURE `Private_Blob_Unbind`(IN `B_Id` INT(11) UNSIGNED,IN `F_Id_UseAsContent` INT(11) UNSIGNED,IN `F_Id_UseAsIcon` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    IF(`B_Id` IS NOT NULL) THEN
        BEGIN
        IF (`F_Id_UseAsContent` IS NOT NULL) THEN
            DELETE FROM `Blob_Usage` WHERE (`Blob_Usage`.`Blob` = `B_Id` AND `Blob_Usage`.`Usage_As_FileContent` = `F_Id_UseAsContent`);
        END IF;
        IF (`F_Id_UseAsIcon` IS NOT NULL) THEN
            DELETE FROM `Blob_Usage` WHERE (`Blob_Usage`.`Blob` = `B_Id` AND `Blob_Usage`.`Usage_As_FileIcon` = `F_Id_UseAsIcon`);
        END IF;
        END;
    END IF;
END$$

DROP PROCEDURE IF EXISTS `Private_Blob_Clear`$$
CREATE PROCEDURE `Private_Blob_Clear`(IN `B_ID` INT(11) UNSIGNED) DETERMINISTIC
BEGIN
    DELETE FROM `Blobs` WHERE `Blobs`.`Blob_Id` = `B_ID`;
END$$

DROP PROCEDURE IF EXISTS `Private_Tag_Create`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `Private_Tag_Binding`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `Private_Tag_Unbind`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `Private_Tag_AutoClear`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `Private_Tag_SetType`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `Private_TagType_Create`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `Private_TagType_Bingding`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `Private_TagType_Unbind`$$
/*Tag相关部分暂时不做*/
DROP PROCEDURE IF EXISTS `Private_TagType_AutoClear`$$
/*Tag相关部分暂时不做*/
/*以下是修改前的函数*/
/*
DROP FUNCTION IF EXISTS `FILE_DUPLICATE_NAME`$$
CREATE FUNCTION `FILE_DUPLICATE_NAME`(`F_PATH` INT(11) UNSIGNED, `F_NAME` VARCHAR(32)) RETURNS INT(11) UNSIGNED DETERMINISTIC 
    文件重名检测函数,返回重名文件id
BEGIN 
    DECLARE `DUPLICATE_NAME` INT(11) UNSIGNED DEFAULT 0;
    IF (EXISTS (SELECT `SubFile` FROM `Files_Files_SubFileName` WHERE `Parent` = `F_Path` AND `File_Name` = `F_NAME`;))THEN
        BEGIN
        SELECT `SubFile` FROM `Files_Files_SubFileName` WHERE `Parent` = `F_Path` AND `File_Name` = `F_NAME` INTO `DUPLICATE_NAME`;
        RETURN `DUPLICATE_NAME`;
        END;
    ELSE
        RETURN 0;
    END IF;
END;
$$ */

DELIMITER ;
/*生成触发器*/
/*CREATE trigger trigger_name BEFORE|AFTER trigger_EVENT ON TABLE_NAME FOR EACH ROW trigger_STMT */
/*
参数trigger_name表示要创建的触发器名；
参数BEFORE和AFTER指定了触发器执行的时间，前者在触发器事件之前执行触发器语句，后者在触发器事件之后执行触发器语句；
参数trigger_EVENT表示触发事件，即触发器执行条件（触发事件），包含DELETE、INSERT和UPDATE语句；
参数TABLE_NAME表示触发事件的操作表名；
参数FOR EACH ROW表示任何一条记录上的操作满足触发事件都会触发该触发器；
参数trigger_STMT（sql语句）表示激活触发器后被执行的语句。执行语句中如果要引用更新记录中的字段，对于INSERT语句，只有NEW是合法的，表示当前已插入的记录；
对于DELETE语句，只有OLD才合法，表示当前删除的记录；
而UPDATE语句可以和NEW（更新后）以及OLD（更新前）同时使用。
*/
DELIMITER $$
DROP TRIGGER IF EXISTS `Before_Insert_On_Users` $$
CREATE TRIGGER `Before_Insert_On_Users` BEFORE INSERT ON `Users` FOR EACH ROW
BEGIN
    /*自动为用户创建用户目录和收藏夹*/
	DECLARE `NEW_UFavName` VARCHAR(64) DEFAULT "";
	DECLARE `NEW_UFileName` VARCHAR(64) DEFAULT "";
	DECLARE `NEW_UFavID` INT(11) DEFAULT 0;
	DECLARE `NEW_UFileID` INT(11) DEFAULT 0;
	SELECT CONCAT(`NEW`.`User_Name`,"_Files") INTO `NEW_UFileName`;
	SELECT CONCAT(`NEW`.`User_Name`,"_Favorites") INTO `NEW_UFavName`;
	CALL `Private_File_CreateFile`('1',`NEW_UFileName`,NULL,`NEW_UFileID`);
	CALL `Private_File_CreateFile`('2',`NEW_UFavName`,NULL,`NEW_UFavID`);
	SET `NEW`.`User_FileList` = `NEW_UFileID`, `NEW`.`User_Favorites` = `NEW_UFavID`;
END$$

DROP TRIGGER IF EXISTS `After_Insert_On_Users` $$
CREATE TRIGGER `After_Insert_On_Users` AFTER INSERT ON `Users` FOR EACH ROW
BEGIN
    /*补充Before触发器,把新生成的id作为文件的创建者*/
    UPDATE `Files` SET `Files`.`File_Creator` = `NEW`.`User_Id` WHERE `Files`.`File_Id` = `NEW`.`User_FileList` OR `Files`.`File_Id` = `NEW`.`User_Favorites`;
END$$

DROP TRIGGER IF EXISTS `After_Delete_On_Users` $$
CREATE TRIGGER `After_Delete_On_Users` AFTER DELETE ON `Users` FOR EACH ROW
BEGIN
    /*自动强制删除用户目录和收藏夹*/
    IF (`OLD`.`User_FileList` IS NOT NULL) THEN
    CALL Private_File_Delete_Cascade(`OLD`.`User_FileList`);
    END IF;
    IF (`OLD`.`User_Favorites` IS NOT NULL) THEN
    CALL Private_File_Delete_Cascade(`OLD`.`User_Favorites`);
    END IF;
END$$

DROP TRIGGER IF EXISTS `Before_Insert_On_Files` $$
CREATE TRIGGER `Before_Insert_On_Files` BEFORE INSERT ON `Files` FOR EACH ROW
BEGIN
    /*依据将创建的文件类型自动创建留言区,或不做任何操作*/
    DECLARE `NEW_Comment_Id` INT(11) UNSIGNED;
    IF(`NEW`.`File_Type` = '3'OR`NEW`.`File_Type` = '4')THEN
        BEGIN
            /*对于常规文件自动创建留言区,用户目录和用户收藏夹没有自动创建的留言区*/
            CALL Private_Comment_Create_Area('1',NULL,CONCAT(`NEW`.`File_Name`,"_Comments"),NULL,`NEW_Comment_Id`);
            SET `NEW`.`File_Comments` = `NEW_Comment_Id`;
            UPDATE `Comments` SET `Comment_Creator` = `NEW`.`File_Creator` WHERE `Comments`.`Comment_Id` = `NEW`.`File_Comments`;
        END;
    END IF;
    SET `NEW`.`File_Download_Count` = '0';
    SET `NEW`.`File_Citation_Count` = '0';
END$$

DROP TRIGGER IF EXISTS `After_Insert_On_Files` $$
CREATE TRIGGER `After_Insert_On_Files` AFTER INSERT ON `Files` FOR EACH ROW
BEGIN
    /*自动创建绑定*/
    CALL Private_Blob_Binding(`NEW`.`File_Data`,`NEW`.`File_Id`,NULL);
    CALL Private_Blob_Binding(`NEW`.`File_Icon_Custom`,NULL,`NEW`.`File_Id`);
END$$

DROP TRIGGER IF EXISTS `After_Update_On_Files` $$
CREATE TRIGGER `After_Update_On_Files` AFTER UPDATE ON `Files` FOR EACH ROW
BEGIN
    /*数据有变化,触发绑定与解绑*/
    IF (NOT (`NEW`.`File_Data` <=> `OLD`.`File_Data`))THEN
        BEGIN
            CALL Private_Blob_Binding(`NEW`.`File_Data`,`NEW`.`File_Id`,NULL);
            CALL Private_Blob_Unbind(`OLD`.`File_Data`,`OLD`.`File_Id`,NULL);
        END;
    END IF;
    IF (NOT (`NEW`.`File_Icon_Custom` <=> `OLD`.`File_Icon_Custom`))THEN
        BEGIN
            CALL Private_Blob_Binding(`NEW`.`File_Icon_Custom`,NULL,`NEW`.`File_Id`);
            CALL Private_Blob_Unbind(`OLD`.`File_Icon_Custom`,NULL,`OLD`.`File_Id`);
        END;
    END IF;
END$$

DROP TRIGGER IF EXISTS `Before_Delete_On_Files` $$
CREATE TRIGGER `Before_Delete_On_Files` BEFORE DELETE ON `Files` FOR EACH ROW
BEGIN
    /*删除所创建的留言区*/
    DELETE FROM `Comments` WHERE `Comments`.`Comment_Id` = `OLD`.`File_Comments`;
    /*自动触发解绑*/
    CALL Private_Blob_Unbind(`OLD`.`File_Data`,`OLD`.`File_Id`,NULL);
    CALL Private_Blob_Unbind(`OLD`.`File_Icon_Custom`,NULL,`OLD`.`File_Id`);
END$$

DROP TRIGGER IF EXISTS `After_Delete_On_Files_Files` $$
CREATE TRIGGER `After_Delete_On_Files_Files` AFTER DELETE ON `Files_Files` FOR EACH ROW
BEGIN
    CALL Private_File_Delete_Restrict(`OLD`.`SubFile`);
END$$

DROP TRIGGER IF EXISTS `Before_Insert_On_Comments` $$
CREATE TRIGGER `Before_Insert_On_Comments` BEFORE INSERT ON `Comments` FOR EACH ROW
BEGIN
    SET `NEW`.`Comment_Mod` = sysdate();
END$$

DROP TRIGGER IF EXISTS `After_Insert_On_Comments` $$
CREATE TRIGGER `After_Insert_On_Comments` AFTER INSERT ON `Comments` FOR EACH ROW
BEGIN
    /*
    IF (`NEW`.`Comment_Parent` IS NULL)THEN
        SET `NEW`.`Comment_Parent` = `NEW`.`Comment_Id`;
    END IF;
    */
END$$

DROP TRIGGER IF EXISTS `Before_Update_On_Comments` $$
CREATE TRIGGER `Before_Update_On_Comments` BEFORE UPDATE ON `Comments` FOR EACH ROW
BEGIN
    /*UPDATE `Comments` SET `Comment_Upd` = sysdate() WHERE `Comments`.`Comment_Id` = `NEW`.`Comment_Parent`;*/
    IF( NOT (`NEW`.`Comment_Msg`<=>`OLD`.`Comment_Msg`) )THEN
        SET `NEW`.`Comment_Mod` = sysdate();
    END IF;
END$$

DROP TRIGGER IF EXISTS `Before_Delete_On_Comments` $$
CREATE TRIGGER `Before_Delete_On_Comments` BEFORE DELETE ON `Comments` FOR EACH ROW
BEGIN
    /*UPDATE `Comments` SET `Comment_Upd` = sysdate() WHERE `Comments`.`Comment_Id` = `OLD`.`Comment_Parent`;*/ 
    /*DELETE FROM `Comments` WHERE `Comments`.`Comment_Parent` = `OLD`.`Comment_Id`;*/
END$$

DROP TRIGGER IF EXISTS `Before_Insert_On_Blob` $$
CREATE TRIGGER `Before_Insert_On_Blob` BEFORE INSERT ON `Blobs` FOR EACH ROW
BEGIN
    DECLARE `NEW_LENGTH` INT(11) UNSIGNED;
    SELECT dbms_lob.getLength(`NEW`.`Blob_Content`) INTO `NEW_LENGTH`;
    SET `NEW`.`Blob_Length` = `NEW_LENGTH`;
END$$

DROP TRIGGER IF EXISTS `After_Delete_On_Blob_Usage` $$
CREATE TRIGGER `After_Delete_On_Blob_Usage` AFTER DELETE ON `Blob_Usage` FOR EACH ROW
BEGIN
    /*IF (NOT (EXISTS SELECT * FROM `Blob_Usage` WHERE `Blob_Usage`.`Blob` = `OLD`.`Blob`)) THEN*/
    CALL Private_Blob_Clear(`OLD`.`Blob`);
    /*END IF;*/
END$$

DROP TRIGGER IF EXISTS `After_Delete_On_Files_Files` $$
CREATE TRIGGER `After_Delete_On_Files_Files` AFTER DELETE ON `Files_Files` FOR EACH ROW
BEGIN
    IF(
        NOT (
            (EXISTS (SELECT * FROM `Files_Files` WHERE `Files_Files`.`SubFile` = `OLD`.`SubFile`))
             OR 
            (EXISTS (SELECT * FROM `Users` WHERE (`Users`.`User_FileList` = `OLD`.`SubFile`) OR (`Users`.`User_FileList` = `OLD`.`SubFile`))))
        )THEN
        DELETE FROM `Files` WHERE `Files`.`File_Id` = `OLD`.`SubFile`;
    END IF;
END$$

DROP TRIGGER IF EXISTS `After_Delete_On_Files_Tags` $$
/*Tag相关部分暂时不做*/

DELIMITER ;