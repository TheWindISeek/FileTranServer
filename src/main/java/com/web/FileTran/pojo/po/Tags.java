package com.web.FileTran.pojo.po;

public class Tags {
    private Integer tagId;//标签的id

    private String tagName;//标签的内容

    private Integer tagType;//标签的种类

    public Tags() {
    }

    @Override
    public String toString() {
        return "Tags{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagType=" + tagType +
                '}';
    }

    public Tags(Integer tagId, String tagName, Integer tagType) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagType = tagType;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Integer getTagType() {
        return tagType;
    }

    public void setTagType(Integer tagType) {
        this.tagType = tagType;
    }
}