package com.example.kafkamongo.model.schema.mongo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Schema {
    String type;
    private List<Field> fields;
    private boolean optional;
    private String name;
}
