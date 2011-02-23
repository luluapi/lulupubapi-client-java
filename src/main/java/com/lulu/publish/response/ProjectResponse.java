package com.lulu.publish.response;

import org.codehaus.jackson.annotate.JsonProperty;

import com.lulu.publish.model.Project;

/**
 * Represents an API project response, such as from a read call.
 */
public class ProjectResponse {

    @JsonProperty("project")
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "ProjectResponse{"
                + "project=" + project
                + '}';
    }
}
