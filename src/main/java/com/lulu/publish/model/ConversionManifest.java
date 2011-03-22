package com.lulu.publish.model;

import java.util.ArrayList;
import java.util.Collection;

public class ConversionManifest {

    private String outputFormat;
    private Dimensions outputDimensions;
    private String callbackUrl;
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

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
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
                + "callbackUrl=\'" + callbackUrl 
                + "\', conversionFiles=\'" + conversionFiles 
                + "\', outputDimensions=\'" + outputDimensions 
                + "\', outputFormat=\'" + outputFormat 
                + "}";
    }

    
    

}
