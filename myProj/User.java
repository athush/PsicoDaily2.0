package myProj;

import java.util.ArrayList;

abstract class User {
    int id;
    String name;
    String email;
    char[] password;
    String cpf;

    public User(int id, String name, String email, char[] password, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    abstract ArrayList<String> profile();
}