package com.example.sessionservice.service;

import com.example.sessionservice.dto.SessionDto;
import com.example.sessionservice.jpa.SessionEntity;

public interface SessionService {
    Iterable<SessionEntity> getSessionByAll();
    SessionDto getSessionBySessionId(String sessionId);
}
