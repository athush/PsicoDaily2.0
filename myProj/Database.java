package myProj;

import java.util.ArrayList;

public class Database 
{
    static ArrayList<Psic> database_psic = new ArrayList<Psic>();
    static ArrayList<Patient> database_patient = new ArrayList<Patient>();

    public Database()
    {
        add_test();
    }

    private void add_test()
    {
        char[] password = {'1', '2', '3'};
    
        Psic psic = new Psic("1", "Luis", "luis@gmail.com", password, "123.456.789-00", "1002000");
        Patient patient = new Patient("1", "Eduardo", "eduardo@gmail.com", password, "234.123.567-76"); 
        
        database_psic.add(psic);
        database_patient.add(patient);
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

    public Patient get_patient(String email)
    {        
        for (int i = 0; i < database_patient.size(); i++)
        {
            Patient pat_atual = database_patient.get(i);
            
            if (email.equals(pat_atual.email))
                return pat_atual;
        }

        return null;
    }
}
