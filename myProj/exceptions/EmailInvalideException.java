package myProj.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static String validEmail(String email) throws EmailInvalideException{
        String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new EmailInvalideException("Email Inválido!");
        }
        return email;
    }
}
