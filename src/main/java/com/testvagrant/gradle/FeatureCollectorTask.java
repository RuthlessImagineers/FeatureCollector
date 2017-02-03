package com.testvagrant.gradle;


import com.testvagrant.core.FeatureFilter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.util.*;

public class FeatureCollectorTask extends DefaultTask{

    @TaskAction
    public Collection<File> collectFeatures() {
        FeatureCollectorExtension featureCollectorExtension = getProject().getExtensions().findByType(FeatureCollectorExtension.class);
        if(featureCollectorExtension ==null) {
            featureCollectorExtension = new FeatureCollectorExtension();
        }
        List<String> tags = getTags(featureCollectorExtension.getTags());
        FeatureFilter featureFilter = new FeatureFilter(tags);
        List<File> featureFiles = featureFilter.collectAllFeatureFilesInProject(getProject().getProjectDir().listFiles());
        List<File> files = featureFilter.getFilteredFeatures(featureFiles);
        files.forEach(file -> System.out.println(file.getName()));
        return files;
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
