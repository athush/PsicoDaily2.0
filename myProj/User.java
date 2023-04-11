package myProj;

import java.text.ParseException;
import java.util.ArrayList;

import myProj.exceptions.CPFInvalidException;
import myProj.exceptions.EmailInvalideException;
import myProj.exceptions.NameInvalidException;
import myProj.exceptions.PasswordInvalidException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MaskFormatter;

abstract class User {
    int id;
    String name;
    String email;
    char[] password;
    String cpf;

    public User(int id, String name, String email, char[] password, String cpf)
            throws NameInvalidException, CPFInvalidException, EmailInvalideException, PasswordInvalidException {

        this.id = id;
        this.name = valid_name(name);
        this.email = valid_email(email);
        this.password = valid_password(password);
        this.cpf = valid_cpf(cpf);
    }

    public String valid_name(String name) {
        return name;
    }

    public String valid_email(String email) {
        String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new EmailInvalideException("Email Inválido!");
        }
        return email;
    }

    public char[] valid_password(char[] password) {
        String str = String.valueOf(password);

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            throw new PasswordInvalidException("Senha Inválida!");
        }

        return password;
    }

    public String valid_cpf(String cpf) {
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

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

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

        formatter("###.###.###-##", cpf);

        return cpf;
    } 
    
    public String formatter (String mascara, String numero){

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
    abstract ArrayList<String> profile();
}