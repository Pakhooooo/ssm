package com.ssm.common.exception;

public class RoleAlreadyExistsException extends RuntimeException {
    
    public RoleAlreadyExistsException(String message) {
        super(message);
    }
}
