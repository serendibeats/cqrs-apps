package com.example.sessionservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<SessionEntity, Long> {
    SessionEntity findSessionEntityBySessionId(String sessionId);
}
