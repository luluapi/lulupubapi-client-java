package com.lulu.publish.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Project {

    private int contentId;
    private boolean allowRatings;
    private ProjectType projectType;
    private String programCode;
    private AccessType accessType;
    private List<DistributionChannel> distribution;
    private Bibliography bibliography;
    private Isbn isbn;
    private PhysicalAttributes physicalAttributes;
    private boolean isDrm;
    private List<Pricing> pricingList;
    private FileInfo fileInfo;

    @JsonProperty("content_id")
    public int getContentId() {
        return contentId;
    }

    @JsonProperty("content_id")
    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @JsonProperty("allow_ratings")
    public boolean isAllowRatings() {
        return allowRatings;
    }

    @JsonProperty("allow_ratings")
    public void setAllowRatings(boolean allowRatings) {
        this.allowRatings = allowRatings;
    }

    @JsonProperty("project_type")
    public ProjectType getProjectType() {
        return projectType;
    }

    @JsonProperty("project_type")
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    @JsonProperty("program_code")
    public String getProgramCode() {
        return programCode;
    }

    @JsonProperty("program_code")
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    @JsonProperty("access")
    public AccessType getAccessType() {
        return accessType;
    }

    @JsonProperty("access")
    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    @JsonProperty("distribution")
    public List<DistributionChannel> getDistribution() {
        return distribution;
    }

    @JsonProperty("distribution")
    public void setDistribution(List<DistributionChannel> distribution) {
        this.distribution = distribution;
    }

    @JsonProperty("bibliography")
    public Bibliography getBibliography() {
        return bibliography;
    }

    @JsonProperty("bibliography")
    public void setBibliography(Bibliography bibliography) {
        this.bibliography = bibliography;
    }

    @JsonProperty("isbn")
    public Isbn getIsbn() {
        return isbn;
    }

    @JsonProperty("isbn")
    public void setIsbn(Isbn isbn) {
        this.isbn = isbn;
    }

    @JsonProperty("physical_attributes")
    public PhysicalAttributes getPhysicalAttributes() {
        return physicalAttributes;
    }

    @JsonProperty("physical_attributes")
    public void setPhysicalAttributes(PhysicalAttributes physicalAttributes) {
        this.physicalAttributes = physicalAttributes;
    }

    @JsonProperty("drm")
    public boolean isDrm() {
        return isDrm;
    }

    @JsonProperty("drm")
    public void setDrm(boolean drm) {
        isDrm = drm;
    }

    @JsonProperty("pricing")
    public List<Pricing> getPricingList() {
        return pricingList;
    }

    @JsonProperty("pricing")
    public void setPricingList(List<Pricing> pricingList) {
        this.pricingList = pricingList;
    }

    @JsonProperty("file_info")
    public FileInfo getFileInfo() {
        return fileInfo;
    }

    @JsonProperty("file_info")
    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    @Override
    public String toString() {
        return "Project{"
                + "accessType=" + accessType
                + ", contentId=" + contentId
                + ", allowRatings=" + allowRatings
                + ", projectType=" + projectType
                + ", programCode='" + programCode + '\''
                + ", distribution=" + distribution
                + ", bibliography=" + bibliography
                + ", isbn=" + isbn
                + ", physicalAttributes=" + physicalAttributes
                + ", isDrm=" + isDrm
                + ", pricingList=" + pricingList
                + ", fileInfo=" + fileInfo
                + '}';
    }
}
