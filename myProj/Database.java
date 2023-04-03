package myProj;

import java.util.ArrayList;

public class Database 
{
    public ArrayList<Psic> database_psic = new ArrayList<Psic>();
    public ArrayList<Patient> database_patient = new ArrayList<Patient>();
    public ArrayList<Consulta> database_consulta = new ArrayList<Consulta>();
    int autoinc_psic, autoinc_patient, autoinc_consulta;

    public Database()
    {
        autoinc_consulta = autoinc_patient = autoinc_psic = 0;
        add_test();
    }

    private void add_test()
    {
        char[] password = {'1', '2', '3'};
    
        Psic psic = new Psic(9, "Luis", "luis@gmail.com", password, "123.456.789-00", "1002000");

        Patient patient = new Patient(10, "Eduardo", "eduardo@gmail.com", password, "234.123.567-76"); 
        
        database_psic.add(psic);
        database_patient.add(patient);
    }

    public void add_psico(String name, String email, String cpf, String crp, char[] password){


        Psic new_psic = new Psic(autoinc_psic, name, email, password, cpf, crp);
        autoinc_psic++;
        database_psic.add(new_psic);
    }

    public void add_patient(String name, String email, String cpf, char[] password) {
        Patient new_patient = new Patient(autoinc_patient, name, email, password, cpf);
        autoinc_patient++;
    
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

    public void add_consulta(Consulta consulta) {
        //so adicione se der true
        consulta.id_consulta = autoinc_consulta;
        autoinc_consulta++;
        database_consulta.add(consulta);
    }

    public Consulta checa_consulta (Patient patient)
    {
        for (Consulta consulta:database_consulta)
        {
            if (consulta.id_paciente == patient.id)
            {
                System.out.println(consulta.inicio.toString());
                return consulta;
            }
        }
        return null;
    }
}
