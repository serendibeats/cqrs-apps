package com.example.sessionservice.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SessionRequest {
    @NotNull(message = "Timeout cannot be null")
    @Min(600)
    private Integer timeout;
}
