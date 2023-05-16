package myProj;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DesvincPatient implements Command{

    Invoker invoker = new Invoker();
    private JFrame main_window;
    private Patient patient;
    private Psic psicologo;
    private Database db;


    public DesvincPatient(JFrame main_window, Database db, Psic psicologo, Patient patient){
        this.main_window = main_window;
        this.db = db;
        this.psicologo = psicologo;
        this.patient = patient;
    }

    public void execute(){
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja desvincular " + patient.name + "?");
        if (resposta == 0) {
            psicologo.patient_list.remove(patient);
            patient.psic_id = -1;
        }
        
        Command managePatientsWindow = new ManagePatientsWindow(main_window, db, psicologo);
        invoker.setCommand(managePatientsWindow);
        invoker.executeCommand();
    }
}
