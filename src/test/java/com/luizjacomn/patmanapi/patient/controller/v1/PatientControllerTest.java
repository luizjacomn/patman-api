package com.luizjacomn.patmanapi.patient.controller.v1;

import com.luizjacomn.patmanapi.BaseTest;
import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientRequest;
import com.luizjacomn.patmanapi.shared.util.ObjectUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PatientControllerTest extends BaseTest {

    private static final String URI = "/patients";

    @Nested
    @DisplayName("Save patients tests")
    class Save {

        @SneakyThrows
        @ParameterizedTest
        @MethodSource("requestSourceForSave")
        void shouldSaveRequestData(String name, String cpf, String birthDate, String email, String phone) {
            // arrange
            RequestBuilder request = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new PatientRequest(name, cpf, LocalDate.parse(birthDate), email, phone)));

            // act
            ResultActions resultActions = mockMvc.perform(request);

            // assert
            resultActions
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, notNullValue()));
        }

        private static Stream<Arguments> requestSourceForSave() {
            return Stream.of(
                // 1 - "telefone" com padrão (99) 9999-9999
                Arguments.of("Fulano", "100.671.910-53", "2000-01-01", "email@mail.com", "(99) 9999-9999"),
                // 2 - "telefone" com padrão (99) 9 9999-9999
                Arguments.of("Cicrano", "548.030.450-03", "2000-01-01", "email@mail.com", "(99) 9 9999-9999")
            );
        }

        @SneakyThrows
        @ParameterizedTest
        @MethodSource("requestSourceForValidation")
        void shouldValidateRequestData(String name, String cpf, String birthDate, String email, String phone, String message) {
            // arrange
            RequestBuilder request = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new PatientRequest(name, cpf, ObjectUtils.nonNullOrElse(birthDate, LocalDate::parse), email, phone)));

            // act
            ResultActions resultActions = mockMvc.perform(request);

            // assert
            resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[*].message", hasItem(message)));
        }

        private static Stream<Arguments> requestSourceForValidation() {
            return Stream.of(
                // 1 - "name" não informado
                Arguments.of("", "100.671.910-53", "2000-01-01", "email@mail.com", "(99) 9999-9999", "must not be blank"),
                // 2 - "cpf" não informado
                Arguments.of("Fulano", "", "2000-01-01", "email@mail.com", "(99) 9999-9999", "must not be blank"),
                // 3 - "cpf" inválido
                Arguments.of("Fulano", "11122233344", "2000-01-01", "email@mail.com", "(99) 9999-9999", "invalid Brazilian individual taxpayer registry number (CPF)"),
                // 4 - "birthDate" não informado
                Arguments.of("Fulano", "100.671.910-53", null, "email@mail.com", "(99) 9999-9999", "must not be null"),
                // 5 - "birthDate" no futuro
                Arguments.of("Fulano", "100.671.910-53", LocalDate.now().plusDays(1).toString(), "email@mail.com", "(99) 9999-9999", "must be a date in the past or in the present"),
                // 6 - "email" inválido
                Arguments.of("Fulano", "100.671.910-53", "2000-01-01", "email.com", "(99) 9999-9999", "must be a well-formed email address"),
                // 7 - "phone" inválido
                Arguments.of("Fulano", "100.671.910-53", "2000-01-01", "email@mail.com", "9999999999", "deve corresponder aos padrões \"(99) 9999-9999\" ou \"(99) 9 9999-9999\"")
            );
        }

        @SneakyThrows
        @Test
        @Sql(statements = "INSERT INTO patients (name, cpf, birth_date) VALUES ('Beltrano Salvo', '98053710000', NOW())")
        void shouldThrowExceptionWhenCpfExists() {
            // arrange
            PatientRequest patientRequest = new PatientRequest("Beltrano", "980.537.100-00", LocalDate.now(), null, null);
            RequestBuilder request = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(patientRequest));

            // act
            ResultActions resultActions = mockMvc.perform(request);

            // assert
            resultActions
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message" ).value("Erro desconhecido. Por favor, tente novamente em instantes. Caso o erro persista, entre em contato com o suporte!"));
        }

    }

}