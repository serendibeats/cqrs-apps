package com.example.projectservice.controller;

import com.example.projectservice.dto.ProjectDto;
import com.example.projectservice.dto.ProjectRequest;
import com.example.projectservice.dto.ProjectResponse;
import com.example.projectservice.jpa.ProjectEntity;
import com.example.projectservice.message.ProjectProducer;
import com.example.projectservice.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("project-service")
public class ProjectController {
    private ProjectService projectService;
    private ProjectProducer projectProducer;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectProducer projectProducer) {
        this.projectService = projectService;
        this.projectProducer = projectProducer;
    }

    @GetMapping("/projects")
    ResponseEntity<List<ProjectResponse>> getProjects() {
        Iterable<ProjectEntity> projectList = projectService.getProjectByAll();

        List<ProjectResponse> result = new ArrayList<>();
        projectList.forEach(v -> {
            result.add(new ModelMapper().map(v, ProjectResponse.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/projects")
    ResponseEntity<ProjectResponse> sendCreateProjectMessage(@RequestBody ProjectRequest project) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ProjectDto projectDto = mapper.map(project, ProjectDto.class);
        projectDto.setProjectId(UUID.randomUUID().toString());

        ProjectResponse responseProject = new ModelMapper().map(projectDto, ProjectResponse.class);

        /* Send a project to the Kafka */
        projectProducer.send("projects", projectDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseProject);
    }
}
