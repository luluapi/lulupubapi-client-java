package com.lulu.publish.model;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
@JsonPropertyOrder({ "widthInPoints", "heightInPoints" })
public class Dimensions {

    private Float widthInPoints;
    private Float heightInPoints;

    /**
     * Construct with size 0x0.
     */
    public Dimensions() {
        this(0, 0);
    }

    public Dimensions(Integer widthInPoints, Integer heightInPoints) {
        setWidthInPoints(widthInPoints.floatValue());
        setHeightInPoints(heightInPoints.floatValue());
    }

	public Float getWidthInPoints() {
    	return widthInPoints;
    }

	public void setWidthInPoints(Float widthInPoints) {
    	this.widthInPoints = widthInPoints;
    }

	public Float getHeightInPoints() {
    	return heightInPoints;
    }

	public void setHeightInPoints(Float heightInPoints) {
    	this.heightInPoints = heightInPoints;
    }

    @Override
    public String toString() {
        return "Dimensions {" 
                + "heightInPoints=\'" + heightInPoints 
                + "\', widthInPoints=\'" + widthInPoints 
                + "}";
    }




}
