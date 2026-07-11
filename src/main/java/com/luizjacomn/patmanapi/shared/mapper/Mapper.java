package com.luizjacomn.patmanapi.shared.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

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

    default Stream<O> toStream(Collection<I> inputCollection) {
        return inputCollection.stream().map(this::to);
    }

    default List<O> toList(Collection<I> inputCollection) {
        return this.toStream(inputCollection).toList();
    }

}
