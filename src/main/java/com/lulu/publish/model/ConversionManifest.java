package com.lulu.publish.model;

import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConversionManifest {

    private String outputFormat;
    private Dimensions outputDimensions;
    private Callback callback;
    private Collection<ConversionFile> conversionFiles;

    /**
     * Default constructor.
     */
    public ConversionManifest() {
        conversionFiles = new ArrayList<ConversionFile>();
        outputDimensions = new Dimensions();
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public Dimensions getOutputDimensions() {
        return outputDimensions;
    }

    public void setOutputDimensions(Dimensions outputDimensions) {
        this.outputDimensions = outputDimensions;
    }

    @JsonProperty("callback")
    public Callback getCallback() {
        return callback;
    }

    @JsonProperty("callback")
    public void setCallbackUrl(Callback callback) {
        this.callback = callback;
    }

    public Collection<ConversionFile> getConversionFiles() {
        return conversionFiles;
    }

    public void setConversionFiles(Collection<ConversionFile> conversionFiles) {
        this.conversionFiles = conversionFiles;
    }

    public void addConversionFiles(ConversionFile jobFile) {
        conversionFiles.add(jobFile);
    }

    @Override
    public String toString() {
        return "ConversionManifest {" 
                + "callbackUrl=\'" + callback.getUrl()
                + "\', conversionFiles=\'" + conversionFiles 
                + "\', outputDimensions=\'" + outputDimensions 
                + "\', outputFormat=\'" + outputFormat 
                + "}";
    }

    
    

}
