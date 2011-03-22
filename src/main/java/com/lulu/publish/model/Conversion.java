package com.lulu.publish.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Conversion extends ConversionManifest {

    private Long conversionId;
    private ConversionStatus status;
    private String statusMessage;
    private Date conversionStartDate;
    private Date conversionEndDate;
    private Long outputFileId;
    
    public Long getConversionId() {
        return conversionId;
    }

    public void setConversionId(Long conversionId) {
        this.conversionId = conversionId;
    }

    public ConversionStatus getStatus() {
        return status;
    }

    public void setStatus(ConversionStatus status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Date getConversionStartDate() {
        return conversionStartDate;
    }

    public void setConversionStartDate(Date conversionStartDate) {
        this.conversionStartDate = conversionStartDate;
    }

    public Date getConversionEndDate() {
        return conversionEndDate;
    }

    public void setConversionEndDate(Date conversionEndDate) {
        this.conversionEndDate = conversionEndDate;
    }

    public Long getOutputFileId() {
        return outputFileId;
    }

    public void setOutputFileId(Long outputFileId) {
        this.outputFileId = outputFileId;
    }
    
    @JsonIgnore
    public void setNotice(String notice) {
    }

    @JsonIgnore
    public void setCustomField(String customField) {
    }

    @Override
    public String toString() {
        return "Conversion {" 
                + "conversionEndDate=\'" + conversionEndDate 
                + "\', conversionId=\'" + conversionId 
                + "\', conversionStartDate=\'" + conversionStartDate 
                + "\', outputFileId=\'" + outputFileId 
                + "\', status=\'" + status 
                + "\', statusMessage=\'" + statusMessage 
                + "}";
    }
    

}
