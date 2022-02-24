package com.example.kafkamongo.model.schema.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    Schema schema;
    Payload payload;
}
