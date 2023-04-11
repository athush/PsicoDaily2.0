package myProj.exceptions;

public class CRPInvalidException extends RuntimeException{
    public CRPInvalidException (){
        super();
    }

    public CRPInvalidException(String message){
        super(message);
    }

    public CRPInvalidException(Throwable throwable){
        super(throwable);
    }

    public CRPInvalidException(String message, Throwable throwable){
        super(message, throwable);
    }
}
