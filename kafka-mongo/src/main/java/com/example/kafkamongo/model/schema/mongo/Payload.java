package com.example.kafkamongo.model.schema.mongo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
    private String jobId;
    private String name;
    private String _class;
}
