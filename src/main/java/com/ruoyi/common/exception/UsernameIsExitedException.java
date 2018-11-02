package com.ruoyi.common.exception;

import com.ruoyi.common.exception.base.BaseException;

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