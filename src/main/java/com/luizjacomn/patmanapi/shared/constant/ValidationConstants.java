package com.luizjacomn.patmanapi.shared.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationConstants {

    // (99) 9999-9999 ou (99) 9 9999-9999
    public static final String PHONE_REGEX = "\\(\\d{2}\\)\\s(?:9\\s)?\\d{4}-\\d{4}";

}
