package com.web.FileTran.dto;


import com.web.FileTran.vo.FileVO;
import com.web.FileTran.vo.FolderContentVO;
import com.web.FileTran.vo.FolderVO;

import java.util.List;

public class FolderContentDTO {
    private Integer totalItems;
    private Integer currentPage;
    private List<FileVO> files;
    private List<FolderVO> folders;

    public FolderContentDTO() {
        // Default constructor
    }

    public FolderContentDTO(
            Integer totalItems,
            Integer currentPage,
            List<FileVO> files,
            List<FolderVO> folders) {
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        this.files = files;
        this.folders = folders;
    }

    @Override
    public String toString()
    {
        return "FolderContentDTO{"+
                " totalItems="+totalItems+
                "; currentPage="+currentPage+
                "; files="+files.toString()+
                "; folders="+folders.toString()+"}";
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<FileVO> getFiles() {
        return files;
    }

    public void setFiles(List<FileVO> files)
    {
        this.files = files;
    }

    public List<FolderVO> getFolders() {
        return folders;
    }

    public void setFolder(List<FolderVO> folders)
    {
        this.folders = folders;
    }

    public FolderContentVO convertToFolderContentVO()
    {
        return new FolderContentVO(
                this.getTotalItems(),
                this.getCurrentPage(),
                this.getFiles(),
                this.getFolders()
        );
    }
}
