package com.example.domain.analyze.exception;

import com.example.domain.common.BaseException;

public class ArchSystemException extends BaseException {
    public ArchSystemException(Type type, String message) {
        super(type, message);
    }

    public static ArchSystemException notFound() {
        return new ArchSystemException(Type.NOT_FOUND, "arch_system_not_found");
    }

}
