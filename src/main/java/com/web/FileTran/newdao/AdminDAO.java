package com.web.FileTran.newdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AdminDAO {
    private final JdbcTemplate jdbcTemplate;
    private static AdminDAO instance = null;
    public static AdminDAO getInstance()
    {
        return instance;
    }

    public AdminDAO(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        instance = this;
    }

    // Register a new admin
    public void registerAdmin(String adminName, String adminPassword) {
        String query = "CALL RegisterAdmin(?, ?, @adminId)";
        jdbcTemplate.update(query, adminName, adminPassword);
    }

    // Delete an admin
    public void deleteAdmin(int adminId) {
        String query = "CALL DeleteAdmin(?)";
        jdbcTemplate.update(query, adminId);
    }

    // Verify admin login credentials
    public AdminLoginResult verifyAdminLogin(String username, String password) {
        String query = "SELECT id, password FROM admins WHERE username = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, username);
        if (rows.isEmpty()) {
            return new AdminLoginResult(0, AdminLoginStatus.INVALID_USERNAME);
        }
        Map<String, Object> row = rows.get(0);
        String storedPassword = (String) row.get("password");
        if (password.equals(storedPassword)) {
            int adminId = (int) row.get("id");
            return new AdminLoginResult(adminId, AdminLoginStatus.SUCCESS);
        } else {
            return new AdminLoginResult(0, AdminLoginStatus.INVALID_PASSWORD);
        }
    }
    public class AdminLoginResult {
        private int adminId;
        private AdminLoginStatus status;

        public AdminLoginResult(int adminId, AdminLoginStatus status) {
            this.adminId = adminId;
            this.status = status;
        }

        public int getAdminId() {
            return adminId;
        }

        public AdminLoginStatus getStatus() {
            return status;
        }
    }

    public enum AdminLoginStatus {
        SUCCESS,
        INVALID_USERNAME,
        INVALID_PASSWORD
    }

}
