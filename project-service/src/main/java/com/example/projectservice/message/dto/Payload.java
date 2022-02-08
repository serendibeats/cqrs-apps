package com.example.projectservice.message.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
    private String project_id;
    private String name;
}
