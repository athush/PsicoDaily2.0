package myProj;

import java.util.ArrayList;

public class Database 
{
    public ArrayList<User> database_user = new ArrayList<User>();
    public ArrayList<Consulta> database_consulta = new ArrayList<Consulta>();
    int autoinc_user, autoinc_consulta;

    public Database()
    {
        autoinc_consulta = autoinc_user = 2;
        add_test();
    }

    private void add_test()
    {
        char[] password = {'1', '2', '3'};
    
        Psic psic = new Psic(0, "Luis", "luis@gmail.com", password, "123.456.789-00", "1002000");

        Patient patient = new Patient(1, "Eduardo", "eduardo@gmail.com", password, "234.123.567-76"); 
        
        database_user.add(psic);
        database_user.add(patient);
    }

    public User get_user(String email)
    {        
        for (User user_atual : database_user){
            if (email.equals(user_atual.email))
                return user_atual;
        }

        throw new NullPointerException("Usuário não existe!");
    }

    public User     get_user(int id) {
        for (User user_atual : database_user) {
            if (id == user_atual.id)
                return user_atual;
        }
        throw new NullPointerException("Usuário não existe!");
    }
    
    public void add_user(User user){
        autoinc_user++;
        database_user.add(user);
    }

    public void add_consulta(Consulta consulta) {
        //so adicione se der true
        consulta.id_consulta = autoinc_consulta;
        autoinc_consulta++;
        database_consulta.add(consulta);
    }

    public void desmarca_consulta(Consulta consulta, Patient patient)
    {
        for (Consulta c : database_consulta)
        {
            if (c.id_paciente == patient.id){
                database_consulta.remove(c);
                return ;
            }
        }
        throw new RuntimeException("Consulta não marcada!");
    }

    public Consulta checa_consulta (Patient patient)
    {
        for (Consulta consulta:database_consulta)
        {
            if (consulta.id_paciente == patient.id)
            {
                //System.out.println(consulta.inicio.toString());
                return consulta;
            }
        }
        //throw new NullPointerException();
        return null;
    }
}
