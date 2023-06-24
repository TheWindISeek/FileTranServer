package com.web.FileTran.newdao;

import com.web.FileTran.newvo.UserVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;
    private static UserDAO instance = null;
    public static UserDAO getInstance()
    {
        return instance;
    }

    public UserDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        instance = this;
    }

    // Register a new user
    public void registerUser(String userName, String userPassword) {
        String query = "CALL RegisterUser(?, ?, @userId)";
        jdbcTemplate.update(query, userName, userPassword);
    }

    // Delete a user
    public void deleteUser(int userId) {
        String query = "CALL DeleteUser(?)";
        jdbcTemplate.update(query, userId);
    }

    // Rename a user
    public boolean renameUser(int userId, String newUserName) {
        String query = "CALL RenameUser(?, ?, @success)";
        Map<String, Object> result = jdbcTemplate.call(connection -> {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, userId);
            callableStatement.setString(2, newUserName);
            callableStatement.registerOutParameter(3, Types.BOOLEAN);
            return callableStatement;
        }, Collections.emptyList());

        return (boolean) result.get("success");
    }

    // Change user password
    public boolean changeUserPassword(int userId, String newUserPassword) {
        String query = "CALL ChangeUserPassword(?, ?, @success)";
        Map<String, Object> result = jdbcTemplate.call(connection -> {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, userId);
            callableStatement.setString(2, newUserPassword);
            callableStatement.registerOutParameter(3, Types.BOOLEAN);
            return callableStatement;
        }, Collections.emptyList());

        return (boolean) result.get("success");
    }

    // Verify user login credentials
    public UserLoginResult verifyUserLogin(String username, String password) {
        String query = "SELECT id, password FROM users WHERE username = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, username);
        if (rows.isEmpty()) {
            return new UserLoginResult(0, UserLoginStatus.INVALID_USERNAME);
        }
        Map<String, Object> row = rows.get(0);
        String storedPassword = (String) row.get("password");
        if (password.equals(storedPassword)) {
            int userId = (int) row.get("id");
            return new UserLoginResult(userId, UserLoginStatus.SUCCESS);
        } else {
            return new UserLoginResult(0, UserLoginStatus.INVALID_PASSWORD);
        }
    }

    // Get user information by ID
    public UserVO getUserById(int userId) {
        String query = "SELECT id, username, user_directory_id, favorites_folder_id FROM users WHERE id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, userId);
        if (!rows.isEmpty()) {
            Map<String, Object> row = rows.get(0);
            int id = (int) row.get("id");
            String username = (String) row.get("username");
            int userDirectoryId = (int) row.get("user_directory_id");
            int favoritesFolderId = (int) row.get("favorites_folder_id");

            UserVO user = new UserVO();
            user.setUserId(id);
            user.setUsername(username);
            user.setUserDirectoryId(userDirectoryId);
            user.setFavorites(favoritesFolderId);

            return user;
        }
        return null;
    }
    public class UserLoginResult {
        private final int userId;
        private final UserLoginStatus status;

        public UserLoginResult(int userId, UserLoginStatus status) {
            this.userId = userId;
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public UserLoginStatus getStatus() {
            return status;
        }
    }

    public enum UserLoginStatus {
        SUCCESS,
        INVALID_USERNAME,
        INVALID_PASSWORD
    }

}
