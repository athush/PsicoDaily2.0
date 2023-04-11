package myProj.exceptions;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException (){
        super();
    }

    public PasswordInvalidException(String message){
        super(message);
    }

    public PasswordInvalidException(Throwable throwable){
        super(throwable);
    }

    public PasswordInvalidException(String message, Throwable throwable){
        super(message, throwable);
    }
}
