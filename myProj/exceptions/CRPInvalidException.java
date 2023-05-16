package myProj.exceptions;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MaskFormatter;
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

    public static String valid_crp(String crp) {
        crp = crp.replaceAll("/", "");

        String CRP_PATTERN = "[0-9]{6}";
        Pattern pattern = Pattern.compile(CRP_PATTERN);
        Matcher matcher = pattern.matcher(crp);

        if (!matcher.matches()) {
            throw new CRPInvalidException("CRP Inválido!");
        }

        try {
            MaskFormatter mf = new MaskFormatter("##/####");
            mf.setValueContainsLiteralCharacters(false);
            crp = mf.valueToString(crp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return crp;
    }
}
