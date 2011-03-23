package com.lulu.publish.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents a file that has been uploaded to Lulu.
 */
public class LuluFile {

    @JsonProperty("file_id")
    private long fileId;
    @JsonProperty("filename")
    private String filename;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "LuluFile{"
                + "fileId=" + fileId
                + ", filename=" + filename
                + '}';
    }
}
