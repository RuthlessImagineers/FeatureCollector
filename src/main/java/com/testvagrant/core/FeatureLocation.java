package com.testvagrant.core;


import java.io.File;
import java.util.List;

public class FeatureLocation extends File {

    private List<File> files;

    public FeatureLocation(String pathname) {
        super(pathname);
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<File> getFiles() {
        return files;
    }
}
