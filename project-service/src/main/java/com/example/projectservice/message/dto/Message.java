package com.example.projectservice.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private Schema schema;
    private Payload payload;
}
