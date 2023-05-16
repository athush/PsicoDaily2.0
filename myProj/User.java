package myProj;

import java.util.ArrayList;

import myProj.exceptions.CPFInvalidException;
import myProj.exceptions.EmailInvalideException;
import myProj.exceptions.PasswordInvalidException;


abstract class User {
    int id;
    String name;
    String email;
    char[] password;
    String cpf;

    public User(int id, String name, String email, char[] password, String cpf)
            throws CPFInvalidException, EmailInvalideException, PasswordInvalidException {

        this.id = id;
        this.name = name;
        this.email = EmailInvalideException.validEmail(email);
        this.cpf = CPFInvalidException.validCpf(cpf);
        this.password = PasswordInvalidException.validPassword(password);
    }

    abstract ArrayList<String> profile();
}