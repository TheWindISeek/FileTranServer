package com.web.FileTran.vo;


import java.util.List;

public class FolderContentVO {
    private int totalItems;
    private int currentPage;
    private List<FileVO> files;
    private List<FolderVO> folders;

    public FolderContentVO() {
        // Default constructor
    }

    public FolderContentVO(int totalItems, int currentPage, List<FileVO> files, List<FolderVO> folders) {
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        this.files = files;
        this.folders = folders;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<FileVO> getFiles() {
        return files;
    }

    public void setFiles(List<FileVO> files) {
        this.files = files;
    }

    public List<FolderVO> getFolders() {
        return folders;
    }

    public void setFolders(List<FolderVO> folders) {
        this.folders = folders;
    }
}
