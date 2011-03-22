package com.lulu.publish.response;

import com.lulu.publish.model.Conversion;

public class ConversionResponse {

    private String status;
    private Conversion conversion;
    private String details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Conversion getConversion() {
        return conversion;
    }

    public void setConversion(Conversion conversion) {
        this.conversion = conversion;
    }

    @Override
    public String toString() {
        return "ConversionResponse {" 
                + "conversion=\'" + conversion 
                + "\', details=\'" + details 
                + "\', status=\'" + status 
                + "}";
    }
    

}
