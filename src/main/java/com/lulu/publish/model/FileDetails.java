package com.lulu.publish.model;

public class FileDetails {
    public static final String MIME_TYPE_PDF = "application/x-pdf";

    private String mimetype;
    private String filename;

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "FileDetails{"
                + "filename='" + filename + '\''
                + ", mimetype='" + mimetype + '\''
                + '}';
    }
}
