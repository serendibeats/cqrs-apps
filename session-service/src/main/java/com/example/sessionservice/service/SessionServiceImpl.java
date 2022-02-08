package com.example.sessionservice.service;

import com.example.sessionservice.dto.SessionDto;
import com.example.sessionservice.jpa.SessionEntity;
import com.example.sessionservice.jpa.SessionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Iterable<SessionEntity> getSessionByAll() {
        return sessionRepository.findAll();
    }

    @Override
    public SessionDto getSessionBySessionId(String sessionId) {
        SessionEntity sessionEntity = sessionRepository.findSessionEntityBySessionId(sessionId);
        if (sessionEntity == null)
            throw new EntityNotFoundException(sessionId);

        SessionDto sessionDto = new ModelMapper().map(sessionEntity, SessionDto.class);

        return sessionDto;
    }
}
