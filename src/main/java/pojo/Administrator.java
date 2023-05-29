package pojo;

public class Administrator {
    private Integer admId;

    private String admName;

    private String admPassword;

    private Integer admPermission;

    public Integer getAdmId() {
        return admId;
    }

    public void setAdmId(Integer admId) {
        this.admId = admId;
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName == null ? null : admName.trim();
    }

    public String getAdmPassword() {
        return admPassword;
    }

    public void setAdmPassword(String admPassword) {
        this.admPassword = admPassword == null ? null : admPassword.trim();
    }

    public Integer getAdmPermission() {
        return admPermission;
    }

    public void setAdmPermission(Integer admPermission) {
        this.admPermission = admPermission;
    }
}