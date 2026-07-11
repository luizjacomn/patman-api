package com.luizjacomn.patmanapi.patient.controller.v1.openapi;

import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientDetailResponse;
import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientRequest;
import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientResponse;
import com.luizjacomn.patmanapi.patient.repository.filter.PatientFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(
    name = "Gerenciamento de Pacientes",
    description = "Endpoints relacionados ao gerenciamento de Pacientes"
)
public interface PatientOpenApi {

    @Operation(
        summary = "Cadastrar um novo paciente",
        description = "Cadastra um novo paciente desde que os dados estejam válidos",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Paciente cadastrado com sucesso"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Erro na validação dos dados"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Erro interno"
            )
        }
    )
    ResponseEntity<Void> save(@RequestBody PatientRequest request);

    @Operation(
        summary = "Editar um paciente existente",
        description = "Edita um paciente desde que o mesmo exista",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Paciente editado com sucesso"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Erro na validação dos dados"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Paciente não encontrado"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Erro interno"
            )
        }
    )
    void edit(@Parameter(in = ParameterIn.PATH) UUID id, @RequestBody PatientRequest request);

    @Operation(
        summary = "Listar os pacientes existentes",
        description = "Lista os pacientes (permite filtrar os dados)",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de pacientes"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Erro interno"
            )
        }
    )
    List<PatientResponse> list(@ParameterObject PatientFilter patientFilter);

    @Operation(
        summary = "Buscar um paciente existente",
        description = "Busca um paciente desde que o mesmo exista",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Paciente encontrado com sucesso"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Paciente não encontrado"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Erro interno"
            )
        }
    )
    PatientDetailResponse find(@Parameter(in = ParameterIn.PATH) UUID id);

    @Operation(
        summary = "Deletar um paciente existente",
        description = "Deleta um paciente desde que o mesmo exista",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Paciente deletado com sucesso"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Paciente não encontrado"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Erro interno"
            )
        }
    )
    void delete(@Parameter(in = ParameterIn.PATH) UUID id);

}
