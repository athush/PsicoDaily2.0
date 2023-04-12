package myProj.exceptions;

public class TimeInvalidException extends RuntimeException{
    public TimeInvalidException (){
        super();
    }

    public TimeInvalidException(String message){
        super(message);
    }

    public TimeInvalidException(Throwable throwable){
        super(throwable);
    }

    public TimeInvalidException(String message, Throwable throwable){
        super(message, throwable);
    }
}
