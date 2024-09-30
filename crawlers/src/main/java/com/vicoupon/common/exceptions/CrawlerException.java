package com.vicoupon.common.exceptions;

public class CrawlerException extends RuntimeException {
    public CrawlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
