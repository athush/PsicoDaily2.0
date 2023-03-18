package myProj;

import java.util.ArrayList;

public class Database 
{
    static ArrayList<Psic> database_psic = new ArrayList<Psic>();

    public Database()
    {
        add_test();
    }

    private void add_test()
    {
        char[] password = {'1', '2', '3'};
    
        Psic test = new Psic("1", "Luis", "luis@gmail.com", password, "123.456.789.00", "1002000");
        
        database_psic.add(test);
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
}
