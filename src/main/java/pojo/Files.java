package pojo;

import java.util.Date;

public class Files {
    private Integer fileId;

    private Integer fileType;

    private String fileName;

    private Integer fileCreator;

    private Date fileUpdate;

    private Integer fileDownloadCount;

    private Integer fileReferenceCount;

    private Integer fileSize;

    private Integer fileLevel;

    private Integer fileIcon;

    private Integer fileIconCustom;

    private Integer fileComments;

    private Integer fileData;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getFileCreator() {
        return fileCreator;
    }

    public void setFileCreator(Integer fileCreator) {
        this.fileCreator = fileCreator;
    }

    public Date getFileUpdate() {
        return fileUpdate;
    }

    public void setFileUpdate(Date fileUpdate) {
        this.fileUpdate = fileUpdate;
    }

    public Integer getFileDownloadCount() {
        return fileDownloadCount;
    }

    public void setFileDownloadCount(Integer fileDownloadCount) {
        this.fileDownloadCount = fileDownloadCount;
    }

    public Integer getFileReferenceCount() {
        return fileReferenceCount;
    }

    public void setFileReferenceCount(Integer fileReferenceCount) {
        this.fileReferenceCount = fileReferenceCount;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileLevel() {
        return fileLevel;
    }

    public void setFileLevel(Integer fileLevel) {
        this.fileLevel = fileLevel;
    }

    public Integer getFileIcon() {
        return fileIcon;
    }

    public void setFileIcon(Integer fileIcon) {
        this.fileIcon = fileIcon;
    }

    public Integer getFileIconCustom() {
        return fileIconCustom;
    }

    public void setFileIconCustom(Integer fileIconCustom) {
        this.fileIconCustom = fileIconCustom;
    }

    public Integer getFileComments() {
        return fileComments;
    }

    public void setFileComments(Integer fileComments) {
        this.fileComments = fileComments;
    }

    public Integer getFileData() {
        return fileData;
    }

    public void setFileData(Integer fileData) {
        this.fileData = fileData;
    }
}