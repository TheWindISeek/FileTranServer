package com.web.FileTran.dto;
import java.sql.Blob;

public class DownloadInfoDTO {
    private String fileName;
    private Blob fileContent;

    public DownloadInfoDTO(String fileName, Blob fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Blob getFileContent() {
        return fileContent;
    }

    public void setFileContent(Blob fileContent) {
        this.fileContent = fileContent;
    }
}
