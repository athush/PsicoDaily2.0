package myProj;

import java.util.ArrayList;

public class Database 
{
    public ArrayList<Psic> database_psic = new ArrayList<Psic>();
    public ArrayList<Patient> database_patient = new ArrayList<Patient>();

    public Database()
    {
        add_test();
    }

    private void add_test()
    {
        char[] password = {'1', '2', '3'};
    
        Psic psic = new Psic(1, "Luis", "luis", password, "123.456.789-00", "1002000");

        Patient patient = new Patient(1, "Eduardo", "eduardo", password, "234.123.567-76"); 
        
        database_psic.add(psic);
        database_patient.add(patient);
    }

    

    public void add_psico(String name, String email, String cpf, String crp, char[] password){

        int lastId = database_psic.get(database_psic.size()-1).id;

        Psic new_psic = new Psic(lastId+1, name, email, password, cpf, crp);

        database_psic.add(new_psic);
    }

    public void add_patient(String name, String email, String cpf, char[] password) {

        int lastId = database_patient.get(database_patient.size() - 1).id;

        Patient new_patient = new Patient(lastId + 1, name, email, password, cpf);

        database_patient.add(new_patient);
    }

    public Psic get_psic(String email)
    {        
        for (int i = 0; i < database_psic.size(); i++)
        {
            Psic psic_atual = database_psic.get(i);
            
            if (email.equals(psic_atual.email))
                return psic_atual;
        }

        return null;
    }

    public Psic get_psic(int id) {
        for (int i = 0; i < database_psic.size(); i++) {
            Psic psic_atual = database_psic.get(i);

            if (id == psic_atual.id)
                return psic_atual;
        }

        return null;
    }

    public Patient get_patient(String email)
    {        
        for (int i = 0; i < database_patient.size(); i++)
        {
            Patient patient_atual = database_patient.get(i);
            
            if (email.equals(patient_atual.email))
                return patient_atual;
        }

        return null;
    }

    public Patient get_patient(int id) {
        for (int i = 0; i < database_patient.size(); i++) {
            Patient patient_atual = database_patient.get(i);

            if (id == patient_atual.id)
                return patient_atual;
        }

        return null;
    }
}
