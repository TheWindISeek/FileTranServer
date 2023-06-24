package com.web.FileTran.pojo.po;

import java.util.Arrays;

public class Blobs {
    private Integer blobId;//文件id

    private Integer blobLength;//文件长度

    private Integer blobNext;//下一个文件的id

    private byte[] blobContent;//文件的内容

    public Blobs(int blobId, int blobLength, int blobNext, byte[] blobContent) {
        this.blobId = blobId;
        this.blobLength = blobLength;
        this.blobNext = blobNext;
        this.blobContent = blobContent;
    }

    public Blobs() {
    }

    @Override
    public String toString() {
        return "Blobs{" +
                "blobId=" + blobId +
                ", blobLength=" + blobLength +
                ", blobNext=" + blobNext +
                ", blobContent=" + Arrays.toString(blobContent) +
                '}';
    }

    public Integer getBlobId() {
        return blobId;
    }

    public void setBlobId(Integer blobId) {
        this.blobId = blobId;
    }

    public Integer getBlobLength() {
        return blobLength;
    }

    public void setBlobLength(Integer blobLength) {
        this.blobLength = blobLength;
    }

    public Integer getBlobNext() {
        return blobNext;
    }

    public void setBlobNext(Integer blobNext) {
        this.blobNext = blobNext;
    }

    public byte[] getBlobContent() {
        return blobContent;
    }

    public void setBlobContent(byte[] blobContent) {
        this.blobContent = blobContent;
    }
}