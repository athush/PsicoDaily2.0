package myProj;

abstract class User 
{
    String id;
    String name;
    String email;
    char[] password;
    String cpf;

    public User(String id, String name, String email, char[] password, String cpf)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    abstract void profile();
}