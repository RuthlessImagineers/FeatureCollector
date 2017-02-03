package com.testvagrant.gradle;


public class FeatureCollectorExtension {

    private String tags = "@smoke, @regression";

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
