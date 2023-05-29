package pojo;

public class Blobs {
    private Integer blobId;

    private Integer blobLength;

    private Integer blobNext;

    private byte[] blobContent;

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