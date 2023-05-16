package myProj;

import java.util.ArrayList;
import myProj.exceptions.CPFInvalidException;
import myProj.exceptions.EmailInvalideException;
import myProj.exceptions.PasswordInvalidException;

public class Patient extends User
{
    int psic_id;
    PatientState vinculo;
    ArrayList<Record> records;
    
    public Patient(int id, String name, String email, char[] password, String cpf) throws  CPFInvalidException, EmailInvalideException, PasswordInvalidException{
        super(id, name, email, password, cpf);

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

    public void addRecord(Record newRecord)
    {
        records.add(newRecord);
    }

    public void editRecord(Record newRecord)
    {
        records.set(newRecord.getId() - 1, newRecord);
    }

    public void updateVinculo()
    {
        if (this.psic_id == -1)
        {
            this.vinculo = new PatientNaoVinculadoState();
            // System.out.println("Sem psico");
        }
        else
        {
            this.vinculo = new PatientVinculadoState();
            // System.out.println("Com psico");
        }
    }

    public void setPsico(int id){
        this.psic_id = id;
        updateVinculo();
    }
}