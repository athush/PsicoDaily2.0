package myProj.exceptions;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MaskFormatter;
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

    public String formatter(String mascara, String numero) {

        try {
            MaskFormatter mf = new MaskFormatter(mascara);
            mf.setValueContainsLiteralCharacters(false);
            numero = mf.valueToString(numero);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(numero);

        return numero;
    }

    public static String validCpf(String cpf) {
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("-", "");

        String CPF_PATTERN2 = "[0-9]{11}";
        Pattern pattern2 = Pattern.compile(CPF_PATTERN2);
        Matcher matcher2 = pattern2.matcher(cpf);

        if (!matcher2.matches()) {
            throw new CPFInvalidException("CPF Inválido!");
        }

        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999"))
            throw new CPFInvalidException("CPF Inválido!");

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 != cpf.charAt(9)) && (dig11 != cpf.charAt(10)))
                throw new CPFInvalidException("CPF não existe!");
        } catch (InputMismatchException erro) {
            throw new CPFInvalidException("CPF Inválido!");
        }

        try {
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            mf.setValueContainsLiteralCharacters(false);
            cpf = mf.valueToString(cpf);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cpf;
    }
}
