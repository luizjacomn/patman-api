package com.luizjacomn.patmanapi.shared.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Messages {

        public static final String UNAUTHORIZED = "Você não está autorizado a acessar esse recurso!";

        public static final String RESOURCE_NOT_FOUND = "Recurso não encontrado para o filtro informado: %s";

        public static final String UNKNOWN_ERROR = "Erro desconhecido. Por favor, tente novamente em instantes. Caso o erro persista, entre em contato com o suporte!";

    }

}
