package myProj;

import java.util.ArrayList;
import myProj.exceptions.CPFInvalidException;
import myProj.exceptions.CRPInvalidException;
import myProj.exceptions.EmailInvalideException;
import myProj.exceptions.PasswordInvalidException;

public class Psic extends User {
    String crp;
    ArrayList<Patient> patient_list = new ArrayList<Patient>();

    public Psic(int id, String name, String email, char[] password, String cpf, String crp) throws CPFInvalidException, CRPInvalidException, EmailInvalideException, PasswordInvalidException{
        super(id, name, email, password, cpf);
        this.crp = CRPInvalidException.valid_crp(crp);
    }

    @Override
    public ArrayList<String> profile() {
        ArrayList<String> dados = new ArrayList<String>();

        dados.add("Psicólogo: " + name);
        dados.add("Email: " + email);
        dados.add("CPF: " + cpf);
        dados.add("CRP: " + crp);

        return dados;
    }


    public void addPatient(Patient patient) {
        patient_list.add(patient);
    }

}