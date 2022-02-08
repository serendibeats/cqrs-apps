package com.example.sessionservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionResponse {
    private Integer timeout;
    private String sessionId;
    private Date createdAt;
}
