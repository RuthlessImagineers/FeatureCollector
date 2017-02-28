package com.testvagrant;


import com.testvagrant.gradle.FeatureCollectorTask;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeatureCollectorTaskTest {

    @Test
    public void featureCollectorPluginAddsFeatureCollectorToTasks() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("com.testvagrant.featureCollector.plugin");
        assertTrue(project.getTasks().getByName("featureCollector") instanceof FeatureCollectorTask);
    }
}
