package com.example.projectservice.service;

import com.example.projectservice.dto.ProjectDto;
import com.example.projectservice.jpa.ProjectEntity;
import com.example.projectservice.jpa.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService{
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Iterable<ProjectEntity> getProjectByAll() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectDto getProjectByProjectId(String projectId) {
        ProjectEntity projectEntity = projectRepository.getProjectEntityByProjectId(projectId);

        if (projectEntity == null)
            throw new EntityNotFoundException(projectId);

        ProjectDto projectDto = new ModelMapper().map(projectEntity, ProjectDto.class);

        return projectDto;
    }
}
