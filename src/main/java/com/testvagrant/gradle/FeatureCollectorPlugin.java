package com.testvagrant.gradle;


import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class FeatureCollectorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions().create("featureTags",FeatureCollectorExtension.class);
        project.getTasks().create("featureCollector",FeatureCollectorTask.class);
    }
}
