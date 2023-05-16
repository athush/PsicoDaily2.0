package myProj.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static char[] validPassword(char[] password){
        String str = String.valueOf(password);

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            throw new PasswordInvalidException("Senha Inválida!");
        }

        return password;
    }
}
