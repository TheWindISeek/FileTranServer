package com.example.api.pojo;

public class FilesTagsKey {
    private Integer file;//file 的id

    private Integer tag;//文件标签的id

    public FilesTagsKey(Integer file, Integer tag) {
        this.file = file;
        this.tag = tag;
    }

    public FilesTagsKey() {
    }

    @Override
    public String toString() {
        return "FilesTagsKey{" +
                "file=" + file +
                ", tag=" + tag +
                '}';
    }

    public Integer getFile() {
        return file;
    }

    public void setFile(Integer file) {
        this.file = file;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }
}