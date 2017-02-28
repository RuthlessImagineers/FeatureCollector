package com.testvagrant.gradle;


import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class RunDistributionTask extends DefaultTask {

    @TaskAction
    public void taskAction() {
        System.out.println("I am a new task");
    }
}
