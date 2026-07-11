package com.luizjacomn.patmanapi.shared.mapper;

/**
 * Interface que define contrato de conversão entre objetos de duas classes.
 * @param <I> entrada (input)
 * @param <O> saída (output)
 */
public interface Mapper<I, O> {

    /**
     * Define um contrato para conversão de uma entrada ({@code input}) em uma saída {@code output}.
     * @param input Dados de entrada
     * @return Dados de saída
     */
    O to(I input);


}
