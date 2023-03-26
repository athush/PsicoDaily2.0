package myProj;

import java.util.ArrayList;

public class Patient extends User
{
    String psic_id;
    ArrayList<Record> records;
    
    public Patient(String id, String name, String email, char[] password, String cpf)
    {
        super(id, name, email, password, cpf);

        this.records = new ArrayList<Record>();
        this.psic_id = "null";
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


    @Override
    public void profile() 
    {
        System.out.println("Paciente: ");

        System.out.println("Nome: " + this.name);
        System.out.println("CPF: " + this.cpf);
    }
}