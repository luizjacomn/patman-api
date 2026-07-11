package com.luizjacomn.patmanapi;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PatmanApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
	@Test
	void shouldReturnHealthStatusUp() {
        // arrange
        RequestBuilder request = MockMvcRequestBuilders.get("/actuator/health")
            .accept(MediaType.APPLICATION_JSON);

        // act
        ResultActions resultActions = mockMvc.perform(request);

        // assert
        resultActions.andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("UP"));
    }

}
