package myProj;

import java.util.ArrayList;

public class Database 
{
    public ArrayList<User> database_user = new ArrayList<User>();
    // public ArrayList<Psic> database_psic = new ArrayList<Psic>();
    // public ArrayList<Patient> database_patient = new ArrayList<Patient>();
    public ArrayList<Consulta> database_consulta = new ArrayList<Consulta>();
    int autoinc_user, autoinc_consulta;

    public Database()
    {
        autoinc_consulta = autoinc_user = 0;
        add_test();
    }

    private void add_test()
    {
        char[] password = {'1', '2', '3'};
    
        Psic psic = new Psic(9, "Luis", "luis", password, "123.456.789-00", "1002000");

        Patient patient = new Patient(10, "Eduardo", "eduardo", password, "234.123.567-76"); 
        
        database_user.add(psic);
        database_user.add(patient);
    }

    // public void add_psico(String name, String email, String cpf, String crp, char[] password){


    //     Psic new_psic = new Psic(autoinc_psic, name, email, password, cpf, crp);
    //     autoinc_psic++;
    //     database_psic.add(new_psic);
    // }

    // public void add_patient(String name, String email, String cpf, char[] password) {
    //     Patient new_patient = new Patient(autoinc_patient, name, email, password, cpf);
    //     autoinc_patient++;
    
    //     database_patient.add(new_patient);
    // }

    public User get_user(String email)
    {        
        for (int i = 0; i < database_user.size(); i++)
        {
            User user_atual = database_user.get(i);
            
            if (email.equals(user_atual.email))
                return user_atual;
        }

        return null;
    }

    public User get_user(int id) {
        for (int i = 0; i < database_user.size(); i++) {
            User user_atual = database_user.get(i);

            if (id == user_atual.id)
                return user_atual;
        }

        return null;
    }

    // public Patient get_patient(String email)
    // {        
    //     for (int i = 0; i < database_patient.size(); i++)
    //     {
    //         Patient patient_atual = database_patient.get(i);
            
    //         if (email.equals(patient_atual.email))
    //             return patient_atual;
    //     }

    //     return null;
    // }

    // public Patient get_patient(int id) {
    //     for (int i = 0; i < database_patient.size(); i++) {
    //         Patient patient_atual = database_patient.get(i);

    //         if (id == patient_atual.id)
    //             return patient_atual;
    //     }

    //     return null;
    // }

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
