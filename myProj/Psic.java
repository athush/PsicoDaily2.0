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

        // addTest();
    }

    // public void addTest(){
    //     Patient new_patient = new Patient(1, "Eduardo", "eduardo", password, "234.123.567-76");

    //     patient_list.add(new_patient);
    // }

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
}