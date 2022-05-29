package com.ck.tinnydouban.exception;

import org.springframework.security.core.AuthenticationException;


public class TokenErrorException extends AuthenticationException {

    public TokenErrorException(Throwable cause) {
        super("token异常:" + cause.getMessage(), cause);
    }

    public TokenErrorException() {
        super("token异常");
    }
}
