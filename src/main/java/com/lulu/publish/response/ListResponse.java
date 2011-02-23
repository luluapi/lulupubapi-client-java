package com.lulu.publish.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents a response from the list call.
 */
public class ListResponse {

    @JsonProperty("content_ids")
    private List<Integer> contentIds;

    public List<Integer> getContentIds() {
        return contentIds;
    }

    public void setContentIds(List<Integer> contentIds) {
        this.contentIds = contentIds;
    }

    @Override
    public String toString() {
        return "ListResponse{"
                + "contentIds=" + contentIds
                + '}';
    }
}
