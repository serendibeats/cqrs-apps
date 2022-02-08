package com.example.sessionservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SessionDto {
    private String sessionId;
    private Integer timeout;
    private Date createdAt;
}
