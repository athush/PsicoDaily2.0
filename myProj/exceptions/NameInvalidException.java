package myProj.exceptions;

public class NameInvalidException extends RuntimeException{
    public NameInvalidException (){
        super();
    }

    public NameInvalidException(String message){
        super(message);
    }

    public NameInvalidException(Throwable throwable){
        super(throwable);
    }

    public NameInvalidException(String message, Throwable throwable){
        super(message, throwable);
    }
}
