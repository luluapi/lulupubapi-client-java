package com.lulu.publish.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class CreateResponse {

    private static final Logger LOG = LoggerFactory.getLogger(CreateResponse.class); // NOPMD

    @JsonProperty("content_id")
    private int contentId;

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "CreateResponse{"
                + "contentId=" + contentId
                + '}';
    }

}
