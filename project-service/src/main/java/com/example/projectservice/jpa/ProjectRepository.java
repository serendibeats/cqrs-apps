package com.example.projectservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity getProjectEntityByProjectId(String projectId);
}
