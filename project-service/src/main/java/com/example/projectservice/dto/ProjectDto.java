package com.example.projectservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDto {
    private String projectId;
    private Date createdAt;
    private String userId;
    private String name;
}
