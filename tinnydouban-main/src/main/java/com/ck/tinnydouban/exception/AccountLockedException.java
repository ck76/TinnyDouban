package com.ck.tinnydouban.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

public class AccountLockedException extends AuthenticationException {


    public AccountLockedException(String msg) {
        super("当前账户不可用");
    }

    public AccountLockedException(String msg, Throwable t) {
        super(msg, t);
    }
}
