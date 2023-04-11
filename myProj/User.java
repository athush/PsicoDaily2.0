package myProj;

import java.util.ArrayList;

import myProj.exceptions.CPFInvalidException;
import myProj.exceptions.EmailInvalideException;
import myProj.exceptions.NameInvalidException;
import myProj.exceptions.PasswordInvalidException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class User {
    int id;
    String name;
    String email;
    char[] password;
    String cpf;

    public User(int id, String name, String email, char[] password, String cpf) throws NameInvalidException, CPFInvalidException, EmailInvalideException, PasswordInvalidException{
        valid_name(name);
        valid_email(email);
        valid_password(password);
        valid_cpf(cpf);

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    public void valid_name(String name) {

    }

    public void valid_email(String email) {
        String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new EmailInvalideException("Email Inválido!");
        }
    }

    public void valid_password(char[] password) {
        String str = String.valueOf(password);

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            throw new PasswordInvalidException("Senha Inválida!");
        }
    }

    public void valid_cpf(String cpf) {
        String CPF_PATTERN1 ="^([0-9]{3}\\.?){3}-?[0-9]{2}$";
        String CPF_PATTERN2 = "[0-9]{11}";

        Pattern pattern1 = Pattern.compile(CPF_PATTERN1);
        Pattern pattern2 = Pattern.compile(CPF_PATTERN2);

        Matcher matcher1 = pattern1.matcher(cpf);
        Matcher matcher2 = pattern2.matcher(cpf);
        if (!matcher1.matches() && !matcher2.matches()) {
            throw new CPFInvalidException("CPF Inválido!"); 
        }
        // else if (!matcher2.matches()){
        //     //Lógica da validação de CPF
        // }

    }

    abstract ArrayList<String> profile();
}