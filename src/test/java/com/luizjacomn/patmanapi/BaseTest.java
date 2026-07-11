package com.luizjacomn.patmanapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Log4j2
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@SuppressWarnings("java:S2187")
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected byte[] toJson(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter objeto para json", e);
            throw new RuntimeException(e);
        }
    }

}
