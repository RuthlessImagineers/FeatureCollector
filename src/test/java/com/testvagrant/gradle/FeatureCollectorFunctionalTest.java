package com.testvagrant.gradle;


import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.gradle.util.GFileUtils.writeFile;

public class FeatureCollectorFunctionalTest {

    @Rule public final TemporaryFolder testProjectDir = new TemporaryFolder();
    private File buildFile;

    @Before
    public void setup() throws IOException {
        buildFile = testProjectDir.newFile("build.gradle");
    }

    @Test
    public void testFeatureCollectorTags() {
        Project project = ProjectBuilder.builder().build();
//        Task task = project.getTasks().getByName("com.testvagrant.featureCollector.plugin");
        String buildFileContent = "task featureCollector {"+
                                  "   doLast {"+
//                                    task.getOutputs().getFiles().getAsPath() +
                                    "   }"+
                                    " }";
        writeFile(buildFileContent,buildFile);
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("featureCollector")
                .withPluginClasspath()
                .build();
        System.out.println(result.getOutput());
    }
}
