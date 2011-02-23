package com.lulu.publish.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class PhysicalAttributes {

    private BindingType bindingType;
    private TrimSize trimSize;
    private PaperType paperType;
    private boolean isColor;

    @JsonProperty("binding_type")
    public BindingType getBindingType() {
        return bindingType;
    }

    @JsonProperty("binding_type")
    public void setBindingType(BindingType bindingType) {
        this.bindingType = bindingType;
    }

    @JsonProperty("trim_size")
    public TrimSize getTrimSize() {
        return trimSize;
    }

    @JsonProperty("trim_size")
    public void setTrimSize(TrimSize trimSize) {
        this.trimSize = trimSize;
    }

    @JsonProperty("paper_type")
    public PaperType getPaperType() {
        return paperType;
    }

    @JsonProperty("paper_type")
    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    @JsonProperty("color")
    public boolean isColor() {
        return isColor;
    }

    @JsonProperty("color")
    public void setColor(boolean color) {
        isColor = color;
    }

    @Override
    public String toString() {
        return "PhysicalAttributes{"
                + "bindingType=" + bindingType
                + ", trimSize=" + trimSize
                + ", paperType=" + paperType
                + ", isColor=" + isColor
                + '}';
    }
}
