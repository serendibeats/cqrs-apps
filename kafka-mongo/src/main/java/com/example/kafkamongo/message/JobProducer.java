package com.example.kafkamongo.message;

import com.example.kafkamongo.model.Job;
import com.example.kafkamongo.model.JobEntity;
import com.example.kafkamongo.model.schema.mongo.Field;
import com.example.kafkamongo.model.schema.mongo.Message;
import com.example.kafkamongo.model.schema.mongo.Payload;
import com.example.kafkamongo.model.schema.mongo.Schema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class JobProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(
            new Field("string", true, "jobId"),
            new Field("string", true, "name"),
            new Field("string", true, "_class")
    );
    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("jobs")
            .build();

    @Autowired
    public JobProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Job send(String topic, Job jobDto) {
        Payload payload = Payload.builder()
                .jobId(jobDto.getJobId())
                .name(jobDto.getName())
                ._class(JobEntity.class.toString())
                .build();

        Message message = new Message(schema, payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonMessage = "";

        try {
            jsonMessage = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonMessage);
        log.info("Kafka Producer send data from job-service: " + jsonMessage);

        return jobDto;
    }
}
