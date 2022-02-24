package com.example.kafkamongo.service;

import com.example.kafkamongo.message.JobProducer;
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
    private JobProducer jobProducer;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobProducer jobProducer) {
        this.jobRepository = jobRepository;
        this.jobProducer = jobProducer;
    }

    @Override
    public List<JobEntity> getJobByAll() {
        JobEntity entity = new JobEntity();
        entity.setJobId(UUID.randomUUID().toString());
        return jobRepository.findAll();
    }

    @Override
    public Job getJobByJobId(String jobId) {
        JobEntity jobEntity = jobRepository.findJobEntityByJobId(jobId);
        /* TODO: Handle EntityNotFoundException */
        Job jobDto = new ModelMapper().map(jobEntity, Job.class);
        return jobDto;
    }

    @Override
    public Job createJob(Job job) {
        job.setJobId(UUID.randomUUID().toString());
        jobProducer.send("job-service.sink", job);
        /* TODO: Will be implemented with Mongo Connector */
        return job;
    }
}
