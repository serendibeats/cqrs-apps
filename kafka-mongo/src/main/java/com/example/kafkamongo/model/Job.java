package com.example.kafkamongo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Job {
    private String jobId;
    private Date createdAt;
    private String userId;
    private String name;
}
