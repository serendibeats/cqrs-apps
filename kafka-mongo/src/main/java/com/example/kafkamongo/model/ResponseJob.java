package com.example.kafkamongo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseJob {
    private String name;
    private String jobId;
    private Date createdAt;
}
