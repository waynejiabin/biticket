package com.biticket.common.exception;

import com.biticket.common.exception.base.BaseException;

/**
 * @author
 */
public class UsernameIsExitedException extends BaseException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    /*public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }*/
}