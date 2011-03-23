package com.lulu.publish.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lulu.publish.model.LuluFile;

/**
 *
 */
public class UploadResponse {

    private static final Logger LOG = LoggerFactory.getLogger(UploadResponse.class); // NOPMD

    @JsonProperty("written_files")
    private List<String> writtenFiles;
    @JsonProperty("uploaded")
    private List<LuluFile> uploaded;

    public List<LuluFile> getUploaded() {
        return uploaded;
    }

    public void setUploaded(List<LuluFile> uploaded) {
        this.uploaded = uploaded;
    }

    public List<String> getWrittenFiles() {
        return writtenFiles;
    }

    public void setWrittenFiles(List<String> writtenFiles) {
        this.writtenFiles = writtenFiles;
    }

    @Override
    public String toString() {
        return "UploadResponse{"
                + "uploaded=" + uploaded
                + ", writtenFiles=" + writtenFiles
                + '}';
    }
}
