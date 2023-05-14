package myProj.exceptions;

public class EmailInvalideException extends RuntimeException{
    public EmailInvalideException(){
        super();
    }

    public EmailInvalideException(String message){
        super(message);
    }

    public EmailInvalideException(Throwable throwable){
        super(throwable);
    }

    public EmailInvalideException(String message, Throwable throwable){
        super(message, throwable);
    }
}
