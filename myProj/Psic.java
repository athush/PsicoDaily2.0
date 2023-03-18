package myProj;

public class Psic extends User
{
    String crp;

    public Psic(String id, String name, String email, char[] password, String cpf, String crp)
    {
        super(id, name, email, password, cpf);
        this.crp = crp;
    }

    @Override
    public void profile() 
    {
        System.out.println("Psicólogo: ");

        System.out.println("Nome: " + this.name);
        System.out.println("CPF: " + this.cpf);
        System.out.println("CRP: " + this.crp);

    }
}