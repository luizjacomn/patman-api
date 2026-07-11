package com.luizjacomn.patmanapi.shared.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    public static final String EMPTY = "";

    public static String onlyNumbers(String input) {
        return input.replaceAll("\\D", EMPTY);
    }

}
