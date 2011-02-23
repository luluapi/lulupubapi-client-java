package com.lulu.publish.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class FileInfo {

    private List<FileDetails> cover;
    private List<FileDetails> contents;

    @JsonProperty("cover")
    public List<FileDetails> getCover() {
        return cover;
    }

    @JsonProperty("cover")
    public void setCover(List<FileDetails> cover) {
        this.cover = cover;
    }

    @JsonProperty("contents")
    public List<FileDetails> getContents() {
        return contents;
    }

    @JsonProperty("contents")
    public void setContents(List<FileDetails> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "FileInfo{"
                + "contents=" + contents
                + ", cover=" + cover
                + '}';
    }
}
