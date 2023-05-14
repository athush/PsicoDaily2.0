package myProj;

import java.util.ArrayList;
import myProj.exceptions.CPFInvalidException;
import myProj.exceptions.CRPInvalidException;
import myProj.exceptions.EmailInvalideException;
import myProj.exceptions.NameInvalidException;
import myProj.exceptions.PasswordInvalidException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Psic extends User {
    String crp;
    ArrayList<Patient> patient_list = new ArrayList<Patient>();

    public Psic(int id, String name, String email, char[] password, String cpf, String crp) throws NameInvalidException, CPFInvalidException, CRPInvalidException, EmailInvalideException, PasswordInvalidException{
        super(id, name, email, password, cpf);
        this.crp = valid_crp(crp);
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

    public String valid_crp(String crp){
        crp = crp.replaceAll("/", "");

        String CRP_PATTERN = "[0-9]{6}";
        Pattern pattern = Pattern.compile(CRP_PATTERN);
        Matcher matcher = pattern.matcher(crp);

        if (!matcher.matches()) {
            throw new CRPInvalidException("CRP Inválido!");
        }

        crp = formatter("##/####", crp);
        return crp;
    }

    public void addPatient(Patient patient) {
        patient_list.add(patient);
    }

}