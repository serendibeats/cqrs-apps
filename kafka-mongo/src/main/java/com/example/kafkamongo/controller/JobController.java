package com.example.kafkamongo.controller;

import com.example.kafkamongo.model.JobEntity;
import com.example.kafkamongo.model.ResponseJob;
import com.example.kafkamongo.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class JobController {
    private JobService jobService;
    private Environment env;

    public JobController(JobService jobService, Environment env) {
        this.jobService = jobService;
        this.env = env;
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's working in JobService on PORT %s",
                env.getProperty("local.server.port"));
    }

    @GetMapping("/jobs")
    ResponseEntity<List<ResponseJob>> getJobs() {
        Iterable<JobEntity> jobList = jobService.getJobByAll();

        List<ResponseJob> result = new ArrayList<>();
        jobList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseJob.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
