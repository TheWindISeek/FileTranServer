package com.web.FileTran.pojo.po;

public class BlobUsageKey {
    private Integer blob;//引用文件的id

    private Integer usageAsFileContent;//作为文件内容使用

    private Integer usageAsFileIcon;//作为文件图标使用

    public BlobUsageKey(int blob, int usageAsFileContent, int usageAsFileIcon) {
        this.blob = blob;
        this.usageAsFileIcon = usageAsFileIcon;
        this.usageAsFileContent = usageAsFileContent;
    }

    public BlobUsageKey() {
    }

    @Override
    public String toString() {
        return "BlobUsageKey{" +
                "blob=" + blob +
                ", usageAsFileContent=" + usageAsFileContent +
                ", usageAsFileIcon=" + usageAsFileIcon +
                '}';
    }

    public Integer getBlob() {
        return blob;
    }

    public void setBlob(Integer blob) {
        this.blob = blob;
    }

    public Integer getUsageAsFileContent() {
        return usageAsFileContent;
    }

    public void setUsageAsFileContent(Integer usageAsFileContent) {
        this.usageAsFileContent = usageAsFileContent;
    }

    public Integer getUsageAsFileIcon() {
        return usageAsFileIcon;
    }

    public void setUsageAsFileIcon(Integer usageAsFileIcon) {
        this.usageAsFileIcon = usageAsFileIcon;
    }
}