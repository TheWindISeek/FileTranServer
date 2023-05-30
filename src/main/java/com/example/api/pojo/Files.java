package com.example.api.pojo;

import java.util.Date;

public class Files {
    private Integer fileId;//文件id

    private Integer fileType;//文件类型

    private String fileName;//文件名称

    private Integer fileCreator;//文件创建者

    private Date fileUpdate;//文件更新时间

    private Integer fileDownloadCount;//文件下载次数

    private Integer fileReferenceCount;//文件引用次数

    private Integer fileSize;//文件大小

    private Integer fileLevel;//what

    private Integer fileIcon;//文件图标

    private Integer fileIconCustom;//文件的图标的id？

    private Integer fileComments;//文件评论的id

    private Integer fileData;//文件对应的blob id


    @Override
    public String toString() {
        return "Files{" +
                "fileId=" + fileId +
                ", fileType=" + fileType +
                ", fileName='" + fileName + '\'' +
                ", fileCreator=" + fileCreator +
                ", fileUpdate=" + fileUpdate +
                ", fileDownloadCount=" + fileDownloadCount +
                ", fileReferenceCount=" + fileReferenceCount +
                ", fileSize=" + fileSize +
                ", fileLevel=" + fileLevel +
                ", fileIcon=" + fileIcon +
                ", fileIconCustom=" + fileIconCustom +
                ", fileComments=" + fileComments +
                ", fileData=" + fileData +
                '}';
    }

    public Files(Integer fileId, Integer fileType, String fileName, Integer fileCreator, Date fileUpdate, Integer fileDownloadCount, Integer fileReferenceCount, Integer fileSize, Integer fileLevel, Integer fileIcon, Integer fileIconCustom, Integer fileComments, Integer fileData) {
        this.fileId = fileId;
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileCreator = fileCreator;
        this.fileUpdate = fileUpdate;
        this.fileDownloadCount = fileDownloadCount;
        this.fileReferenceCount = fileReferenceCount;
        this.fileSize = fileSize;
        this.fileLevel = fileLevel;
        this.fileIcon = fileIcon;
        this.fileIconCustom = fileIconCustom;
        this.fileComments = fileComments;
        this.fileData = fileData;
    }

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