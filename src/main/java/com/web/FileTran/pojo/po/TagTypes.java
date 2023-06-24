package com.web.FileTran.pojo.po;

public class TagTypes {
    private Integer tagTypeId;//标签类型的 id？ what?

    private String tagTypeName;//标签类型的名字

    public TagTypes() {
    }

    @Override
    public String toString() {
        return "TagTypes{" +
                "tagTypeId=" + tagTypeId +
                ", tagTypeName='" + tagTypeName + '\'' +
                '}';
    }

    public TagTypes(Integer tagTypeId, String tagTypeName) {
        this.tagTypeId = tagTypeId;
        this.tagTypeName = tagTypeName;
    }

    public Integer getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(Integer tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    public String getTagTypeName() {
        return tagTypeName;
    }

    public void setTagTypeName(String tagTypeName) {
        this.tagTypeName = tagTypeName == null ? null : tagTypeName.trim();
    }
}