package com.luizjacomn.patmanapi.shared.exception;

import com.luizjacomn.patmanapi.shared.constant.ErrorConstants;
import lombok.Getter;

import java.io.Serial;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2378128803994048575L;

    private final String filter;

    public ResourceNotFoundException(String filter) {
        super(ErrorConstants.Messages.RESOURCE_NOT_FOUND.formatted(filter));
        this.filter = filter;
    }

}
