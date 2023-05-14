package myProj.exceptions;

public class CPFInvalidException extends RuntimeException{
    public CPFInvalidException (){
        super();
    }

    public CPFInvalidException(String message){
        super(message);
    }

    public CPFInvalidException(Throwable throwable){
        super(throwable);
    }

    public CPFInvalidException(String message, Throwable throwable){
        super(message, throwable);
    }
}
