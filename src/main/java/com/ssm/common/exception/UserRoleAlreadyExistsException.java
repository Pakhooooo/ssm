package com.ssm.common.exception;

public class UserRoleAlreadyExistsException extends RuntimeException {
    
    public UserRoleAlreadyExistsException(String message) {
        super(message);
    }
}
