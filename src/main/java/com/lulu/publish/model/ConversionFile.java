package com.lulu.publish.model;

import java.net.URI;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ConversionFile {

    public static final String MIME_TYPE_PDF = "application/pdf";

    private Long conversionFileId;
    private URI uri;
    private Integer index;
    private String mimeType;
    private String status;
    private String statusMessage;
    private String converterHostname;
    private String downloadStartDate;
    private String downloadEndDate;
    private String conversionStartDate;
    private String conversionEndDate;
    private List<Notification> notifications;

    public Long getConversionFileId() {
        return conversionFileId;
    }

    public void setConversionFileId(Long conversionFileId) {
        this.conversionFileId = conversionFileId;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getConverterHostname() {
        return converterHostname;
    }

    public void setConverterHostname(String converterHostname) {
        this.converterHostname = converterHostname;
    }

    public String getDownloadStartDate() {
        return downloadStartDate;
    }

    public void setDownloadStartDate(String downloadStartDate) {
        this.downloadStartDate = downloadStartDate;
    }

    public String getDownloadEndDate() {
        return downloadEndDate;
    }

    public void setDownloadEndDate(String downloadEndDate) {
        this.downloadEndDate = downloadEndDate;
    }

    public String getConversionStartDate() {
        return conversionStartDate;
    }

    public void setConversionStartDate(String conversionStartDate) {
        this.conversionStartDate = conversionStartDate;
    }

    public String getConversionEndDate() {
        return conversionEndDate;
    }

    public void setConversionEndDate(String conversionEndDate) {
        this.conversionEndDate = conversionEndDate;
    }

    @JsonIgnore
    public void setNotice(String notice) {
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "ConversionFile{"
                + "conversionEndDate='" + conversionEndDate + '\''
                + ", conversionFileId=" + conversionFileId
                + ", uri=" + uri
                + ", index=" + index
                + ", mimeType='" + mimeType + '\''
                + ", status='" + status + '\''
                + ", statusMessage='" + statusMessage + '\''
                + ", converterHostname='" + converterHostname + '\''
                + ", downloadStartDate='" + downloadStartDate + '\''
                + ", downloadEndDate='" + downloadEndDate + '\''
                + ", conversionStartDate='" + conversionStartDate + '\''
                + ", notifications=" + notifications
                + '}';
    }
}
