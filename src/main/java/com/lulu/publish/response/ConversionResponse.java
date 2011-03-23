package com.lulu.publish.response;

import com.lulu.publish.model.Conversion;

public class ConversionResponse {

    private Conversion conversion;

    public Conversion getConversion() {
        return conversion;
    }

    public void setConversion(Conversion conversion) {
        this.conversion = conversion;
    }

    @Override
    public String toString() {
        return "ConversionResponse{"
                + "conversion=" + conversion
                + '}';
    }
}
