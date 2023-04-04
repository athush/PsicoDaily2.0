package myProj;

import java.util.ArrayList;

public class Psic extends User
{
    String crp;
    ArrayList<Patient> patient_list = new ArrayList<Patient>();

    public Psic(int id, String name, String email, char[] password, String cpf, String crp)
    {
        super(id, name, email, password, cpf);
        this.crp = crp;
    }

    public void addPatient(Patient patient){
        patient_list.add(patient);
    }

    @Override
    public void profile() 
    {
        System.out.println("Psicólogo: ");

        System.out.println("Nome: " + this.name);
        System.out.println("CPF: " + this.cpf);
        System.out.println("CRP: " + this.crp);

    }

    public void add_user(Database db) {
        
        db.autoinc_user++; 
        db.database_user.add(this);
    }
}