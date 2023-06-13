package com.web.FileTran.pojo.po;

public class FilesFilesKey {
    private Integer parent;//父文件的files id

    private Integer subfile;//子文件的files id

    public FilesFilesKey(Integer parent, Integer subfile) {
        this.parent = parent;
        this.subfile = subfile;
    }

    public FilesFilesKey() {
    }

    @Override
    public String toString() {
        return "FilesFilesKey{" +
                "parent=" + parent +
                ", subfile=" + subfile +
                '}';
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getSubfile() {
        return subfile;
    }

    public void setSubfile(Integer subfile) {
        this.subfile = subfile;
    }
}