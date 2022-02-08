package com.example.projectservice.service;

import com.example.projectservice.dto.ProjectDto;
import com.example.projectservice.jpa.ProjectEntity;

public interface ProjectService {
    Iterable<ProjectEntity> getProjectByAll();
    ProjectDto getProjectByProjectId(String projectId);
}
