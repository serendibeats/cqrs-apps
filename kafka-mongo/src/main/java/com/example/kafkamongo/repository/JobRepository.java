package com.example.kafkamongo.repository;

import com.example.kafkamongo.model.JobEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<JobEntity, ObjectId> {
    JobEntity findJobEntityByJobId(String jobId);
}
