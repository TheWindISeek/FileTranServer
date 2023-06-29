package com.web.FileTran.dto;


import com.web.FileTran.vo.FileVO;
import com.web.FileTran.vo.FolderVO;

import java.util.ArrayList;
import java.util.List;

public class FolderContentDTO {
    private int totalItems;
    private int currentPage;

    // 包括子文件夹和子文件
    private List<Object> subItems;

    public FolderContentDTO() {
        // Default constructor
    }

    public FolderContentDTO(int totalItems, int currentPage, List<FileVO> files, List<FolderVO> folders) {
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        // TODO 需要在此处把两个表合并
        this.subItems = new ArrayList<>();
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

    public List<Object> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<Object> subItems) {
        this.subItems = subItems;
    }
}
