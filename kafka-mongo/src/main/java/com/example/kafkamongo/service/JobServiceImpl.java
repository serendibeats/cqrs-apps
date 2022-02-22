package com.example.kafkamongo.service;

import com.example.kafkamongo.model.Job;
import com.example.kafkamongo.model.JobEntity;
import com.example.kafkamongo.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobEntity> getJobByAll() {
        JobEntity entity = new JobEntity();
        entity.setJobId(UUID.randomUUID().toString());
        entity.setName("hello-world-job");
        jobRepository.save(entity);
        return jobRepository.findAll();
    }

    @Override
    public Job getJobByJobId(String jobId) {
        JobEntity jobEntity = jobRepository.findJobEntityByJobId(jobId);
        /* TODO: Handle EntityNotFoundException */
        Job jobDto = new ModelMapper().map(jobEntity, Job.class);
        return jobDto;
    }
}
