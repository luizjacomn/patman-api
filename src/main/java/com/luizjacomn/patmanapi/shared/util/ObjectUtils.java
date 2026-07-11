package com.luizjacomn.patmanapi.shared.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObjectUtils {

    public static <T, P> P nonNullOrElse(T input, Function<T, P> getProperty) {
        if (input == null) {
            return null;
        }

        return getProperty.apply(input);
    }

}
