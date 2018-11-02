package com.biticket.common.exception;

import com.biticket.common.exception.base.BaseException;

/**
 * 描述：
 * <p>
 *
 * @author:
 * @date: 2018/4/11 10:24
 */
public class TokenException extends BaseException {

    private static final long serialVersionUID = 1L;

    public TokenException(String message) {
        super(message);
    }
}
