package com.testvagrant.gradle;


import com.testvagrant.core.FeatureFilter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.OutputFiles;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.util.*;

public class FeatureCollectorTask extends DefaultTask{

    private Collection<File> featureFiles = new ArrayList<>();

    @TaskAction
    public void collectFeatures() {
        FeatureCollectorExtension featureCollectorExtension = getProject().getExtensions().findByType(FeatureCollectorExtension.class);
        if(featureCollectorExtension ==null) {
            featureCollectorExtension = new FeatureCollectorExtension();
        }
        System.out.println("Feature Tags -- "+featureCollectorExtension.getTags());
        List<String> tags = getTags(featureCollectorExtension.getTags());
        FeatureFilter featureFilter = new FeatureFilter(tags);
        List<File> featureFilesList = featureFilter.collectAllFeatureFilesInProject(getProject().getProjectDir().listFiles());
        featureFiles = featureFilter.getFilteredFeatures(featureFilesList);
        featureFiles.forEach(file -> System.out.println(file.getName()));
    }

    @OutputFiles
    public Collection<File> getFeatureFiles() {
        return featureFiles;
    }

    public void setFeatureFiles(Collection<File> featureFiles) {
        this.featureFiles = featureFiles;
    }

    private List<String> getTags(String tags) {
        if(!tags.contains(",")) {
            List<String> tagsList = new ArrayList<>();
            tagsList.add(tags);
            return tagsList;
        }
        return Arrays.asList(tags.split(","));
    }


}
