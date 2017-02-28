package com.testvagrant.gradle;

import com.testvagrant.core.FeatureFilter;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.OutputFiles;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

@CacheableTask
public class FeatureCollectorTask extends DefaultTask{

    private Collection<File> featureFiles = new ArrayList<>();
    private File runnableFeaturesDirectory = new File(getProject().getBuildDir(),"RunnableFeatures");

    @TaskAction
    public void collectFeatures() {
        FeatureCollectorExtension featureCollectorExtension = getProject().getExtensions().findByType(FeatureCollectorExtension.class);
        if(featureCollectorExtension ==null) {
            featureCollectorExtension = new FeatureCollectorExtension();
        }
        System.out.println(featureCollectorExtension);
        System.out.println("Feature Tags -- "+featureCollectorExtension.getTags());
        List<String> tags = getTags(featureCollectorExtension.getTags());
        FeatureFilter featureFilter = new FeatureFilter(tags);
        List<File> featureFilesList = featureFilter.collectAllFeatureFilesInProject(getProject().getProjectDir().listFiles());
        featureFiles = featureFilter.getFilteredFeatures(featureFilesList);
        featureFiles.forEach(file -> System.out.println(file.getName()));
        createRunnableFeaturesDirectory();

    }

    private void createRunnableFeaturesDirectory() {
        if(!runnableFeaturesDirectory.exists()) {
            boolean mkdirs = runnableFeaturesDirectory.mkdirs();
        }
        featureFiles.forEach(file -> {
            try {
                Files.copy(file.toPath(), new File(runnableFeaturesDirectory + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OutputDirectory
    public File getRunnableFeaturesDirectory() {
        return runnableFeaturesDirectory;
    }

    public void setRunnableFeaturesDirectory(File runnableFeaturesDirectory) {
        this.runnableFeaturesDirectory = runnableFeaturesDirectory;
    }

    private List<String> getTags(String tags) {
        if(!tags.contains(",")) {
            List<String> tagsList = new ArrayList<>();
            tagsList.add(tags);
            return tagsList;
        }
        List<String> tagsList = Arrays.asList(tags.split(","));
        tagsList.forEach(String::trim);
        return tagsList;
    }


}
