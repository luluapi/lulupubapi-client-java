package com.lulu.publish.model;

/**
 *
 */
public enum DistributionChannel {
    LULU_MARKETPLACE("lulu_marketplace"),
    RETAIL_CHANNELS("retail_channels");

    private String channelName;

    private DistributionChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return channelName;
    }
}
