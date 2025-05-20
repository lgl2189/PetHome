package com.pethome.exception;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 16 20:06
 */

public class DataBaseOperatorException extends RuntimeException{
    public DataBaseOperatorException(){
        super();
    }
    public DataBaseOperatorException(String message){
        super(message);
    }
    public DataBaseOperatorException(String message, Throwable cause){
        super(message, cause);
    }
    public DataBaseOperatorException(Throwable cause){
        super(cause);
    }
}