package com.example.kafkamongo.service;

import com.example.kafkamongo.model.Job;
import com.example.kafkamongo.model.JobEntity;

import java.util.List;

public interface JobService {
    List<JobEntity> getJobByAll();
    Job getJobByJobId(String jobId);
    Job createJob(Job job);
}
