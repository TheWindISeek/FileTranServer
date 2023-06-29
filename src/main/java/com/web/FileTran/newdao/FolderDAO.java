package com.web.FileTran.newdao;

import com.web.FileTran.newvo.FolderContentVO;
import com.web.FileTran.newvo.FolderVO;
import com.web.FileTran.newvo.ShortcutVO;
import com.web.FileTran.newvo.UserVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Collections;
import java.util.Map;

@Repository
public class FolderDAO {
    /*
    private final JdbcTemplate jdbcTemplate;
    private static FolderDAO instance = null;
    public static FolderDAO getInstance()
    {
        return instance;
    }

    public FolderDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        instance = this;
    }

    // Create folder
    public int createFolder(int parentFolderId, int creatorId) {
        String query = "CALL CreateFolder(?, ?, @newFolderId)";
        Map<String, Object> result = jdbcTemplate.call(connection -> {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, parentFolderId);
            callableStatement.setInt(2, creatorId);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            return callableStatement;
        }, Collections.emptyList());

        return (int) result.get("newFolderId");
    }

    // Rename folder
    public boolean renameFolder(int folderId, String newFolderName) {
        String query = "CALL RenameFolder(?, ?, @success)";
        Map<String, Object> result = jdbcTemplate.call(connection -> {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, folderId);
            callableStatement.setString(2, newFolderName);
            callableStatement.registerOutParameter(3, Types.BOOLEAN);
            return callableStatement;
        }, Collections.emptyList());

        return (boolean) result.get("success");
    }

    // Create folder shortcut
    public int createFolderShortcut(int parentFolderId, String shortcutName, int creatorId, int targetFolderId) {
        String query = "CALL CreateFolderShortcut(?, ?, ?, ?, @newShortcutId)";
        Map<String, Object> result = jdbcTemplate.call(connection -> {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, parentFolderId);
            callableStatement.setString(2, shortcutName);
            callableStatement.setInt(3, creatorId);
            callableStatement.setInt(4, targetFolderId);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            return callableStatement;
        }, Collections.emptyList());

        return (int) result.get("newShortcutId");
    }

    // Delete folder
    public void deleteFolder(int folderId) {
        String query = "CALL DeleteFolder(?)";
        jdbcTemplate.update(query, folderId);
    }

    // Get folder content (sub-folders and files) by folderId, page, and pageSize
    public FolderContentVO getFolderContent(int folderId, int page, int pageSize) {
        int startIndex = page * pageSize;

        // Retrieve the folder content based on startIndex and pageSize
        String query = "SELECT * FROM folders WHERE parent_folder_id = ? LIMIT ?, ?";
        List<Folder> subFolders = jdbcTemplate.query(query, new Object[]{folderId, startIndex, pageSize},
                new BeanPropertyRowMapper<>(Folder.class));

        query = "SELECT * FROM files WHERE folder_id = ? LIMIT ?, ?";
        List<File> files = jdbcTemplate.query(query, new Object[]{folderId, startIndex, pageSize},
                new BeanPropertyRowMapper<>(File.class));

        FolderContentVO folderContentVO = new FolderContentVO();
        folderContentVO.setSubFolders(subFolders);
        folderContentVO.setFiles(files);

        return folderContentVO;
    }

    // Get folder and file list by folder ID
    public FolderContentVO getFolderContent(int folderId) {
        String query = "SELECT f.id, f.name, f.folder_type, f.permission, f.creator_id, " +
                "IFNULL((SELECT COUNT(*) FROM folders WHERE parent_folder_id = f.id), 0) AS num_folders, " +
                "IFNULL((SELECT COUNT(*) FROM files WHERE folder_id = f.id), 0) AS num_files " +
                "FROM folders f " +
                "WHERE f.id = ?";
        FolderContentVO folderContent = jdbcTemplate.queryForObject(query, new Object[]{folderId}, (rs, rowNum) -> {
            long folderId = rs.getInt("id");
            //String folderName = rs.getString("name");
            //UserVO creator =
            //folderType
             //       accessPermission
            //shortcut
            FolderVO folder = new FolderVO();
            folder.setFolderId(rs.getInt("id"));
            folder.setFolderName(rs.getString("name"));
            folder.setFolderType(rs.getString("folder_type"));
            folder.setAccessPermission(rs.getString("permission"));
            folder.setCreator(UserDAO.getInstance().getUserById(rs.getInt("creator_id")));

            int numFolders = rs.getInt("num_folders");
            int numFiles = rs.getInt("num_files");

            return new FolderContentVO(folder, numFolders, numFiles);
        });

        return folderContent;
    }

    // Get folder content count by folderId
    public int getFolderContentCount(int folderId) {
        String query = "SELECT COUNT(*) FROM folders WHERE parent_folder_id = ?";
        int subFolderCount = jdbcTemplate.queryForObject(query, Integer.class, folderId);

        query = "SELECT COUNT(*) FROM files WHERE folder_id = ?";
        int fileCount = jdbcTemplate.queryForObject(query, Integer.class, folderId);

        return subFolderCount + fileCount;
    }

    // 两层递归的文件夹信息查询,当查询到快捷方式时会再进行一次查询
    // Get folder information by folder ID
    public FolderVO getFolderById(int folderId) {
        String query = "SELECT f.id, f.name, f.creator_id, f.folder_type, f.permission, " +
                "s.id AS shortcut_id, s.name AS shortcut_name, s.folder_type AS shortcut_folder_type " +
                "FROM folders f " +
                "LEFT JOIN folders s ON f.shortcut_destination = s.id " +
                "WHERE f.id = ?";
        FolderVO folder = jdbcTemplate.queryForObject(query, new Object[]{folderId}, (rs, rowNum) -> {
            FolderVO folderVO = new FolderVO();
            folderVO.setFolderId(rs.getInt("id"));
            folderVO.setFolderName(rs.getString("name"));
            folderVO.setCreator(UserDAO.getInstance().getUserById(rs.getInt("creator_id")));
            folderVO.setFolderType(rs.getString("folder_type"));
            folderVO.setAccessPermission(rs.getString("permission"));

            int targetId = rs.getInt("shortcut_id");
            if (targetId != 0) {
                // 递归查询一次目标文件获取更详细的信息
                FolderVO targetVO = getFolderById_skip_shortcut(targetId);
                // 后面的信息以target的为准
                String targetName = targetVO.getFolderName();
                UserVO targetCreator = targetVO.getCreator();
                ShortcutVO shortcut = new ShortcutVO(targetId,targetName,targetCreator);
                folderVO.setShortcut(shortcut);
            }

            return folderVO;
        });
        return folder;
    }

    // Get folder information by folder ID skip shortcuts
    public FolderVO getFolderById_skip_shortcut(int folderId) {
        String query = "SELECT f.id, f.name, f.creator_id, f.folder_type, f.permission, " +
                "s.id AS shortcut_id, s.name AS shortcut_name, s.folder_type AS shortcut_folder_type " +
                "FROM folders f " +
                "LEFT JOIN folders s ON f.shortcut_destination = s.id " +
                "WHERE f.id = ?";
        FolderVO folder = jdbcTemplate.queryForObject(query, new Object[]{folderId}, (rs, rowNum) -> {
            FolderVO folderVO = new FolderVO();
            folderVO.setFolderId(rs.getInt("id"));
            folderVO.setFolderName(rs.getString("name"));
            folderVO.setCreator(UserDAO.getInstance().getUserById(rs.getInt("creator_id")));
            folderVO.setFolderType(rs.getString("folder_type"));
            folderVO.setAccessPermission(rs.getString("permission"));
            folderVO.setShortcut(null);
            return folderVO;
        });
        return folder;
    }
    */
}