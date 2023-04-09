package myProj;

import java.util.ArrayList;

public class Patient extends User
{
    int psic_id;
    Boolean checaConsulta;
    ArrayList<Record> records;
    
    public Patient(int id, String name, String email, char[] password, String cpf)
    {
        super(id, name, email, password, cpf);

        this.checaConsulta = false;
        this.records = new ArrayList<Record>();
        this.psic_id = -1;
    }

    @Override
    public ArrayList<String> profile() {
        ArrayList<String> dados = new ArrayList<String>();

        dados.add("Paciente: " + name);
        dados.add("Email: " + email);
        dados.add("CPF: " + cpf);

        return dados;
    }

    public boolean addRecord(String title, String text, int id)
    {
        Record newRecord = new Record(title, text, id);

        return records.add(newRecord);
    }

    public boolean editRecord(String title, String text, int id)
    {
        Record newRecord = new Record(title, text, id);

        records.set(id - 1, newRecord);
        return true;
    }

    public void setPsico(int id){
        this.psic_id = id;
    }
}