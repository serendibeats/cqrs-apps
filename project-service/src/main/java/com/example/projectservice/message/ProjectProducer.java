package com.example.projectservice.message;

import com.example.projectservice.dto.ProjectDto;
import com.example.projectservice.message.dto.Field;
import com.example.projectservice.message.dto.Payload;
import com.example.projectservice.message.dto.Message;
import com.example.projectservice.message.dto.Schema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ProjectProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(
            new Field("string", false, "project_id"),
            new Field("string", false, "name")
    );
    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("projects")
            .build();

    @Autowired
    public ProjectProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ProjectDto send(String topic, ProjectDto projectDto) {
        Payload payload = Payload.builder()
                .project_id(projectDto.getProjectId())
                .name(projectDto.getName())
                .build();

        Message message = new Message(schema, payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from project-service: " + jsonInString);

        return projectDto;
    }
}
