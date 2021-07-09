package com.duroflex.neuma.app.exception.exceptionutils;

/**
 * @author Deepak Walve
 *
 */
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}
