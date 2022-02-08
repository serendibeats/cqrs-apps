package com.example.sessionservice.controller;

import com.example.sessionservice.dto.SessionDto;
import com.example.sessionservice.dto.SessionRequest;
import com.example.sessionservice.dto.SessionResponse;
import com.example.sessionservice.jpa.SessionEntity;
import com.example.sessionservice.service.SessionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("session-service")
public class SessionController {
    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sessions")
    ResponseEntity<List<SessionResponse>> getSessions() {
        Iterable<SessionEntity> sessionList = sessionService.getSessionByAll();
        List<SessionResponse> result = new ArrayList<>();

        sessionList.forEach(v -> {
            result.add(new ModelMapper().map(v, SessionResponse.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/sessions")
    ResponseEntity<SessionResponse> sendCreateSessionMessage(@RequestBody SessionRequest session) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        SessionDto sessionDto = mapper.map(session, SessionDto.class);
        sessionDto.setSessionId(UUID.randomUUID().toString());

        SessionResponse responseSession = new ModelMapper().map(sessionDto, SessionResponse.class);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseSession);
    }
}
