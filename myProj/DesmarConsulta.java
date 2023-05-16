package myProj;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DesmarConsulta implements Command{
    Invoker invoker = new Invoker();
    private JFrame main_window;
    private Patient patient;
    private Psic psicologo;
    private Database db;

    public DesmarConsulta(JFrame main_window, Database db, Psic psicologo, Patient patient){
        this.main_window = main_window;
        this.db = db;
        this.psicologo = psicologo;
        this.patient = patient;
    }

    public void execute() {
        try {
            db.desmarca_consulta(db.checa_consulta(patient), patient);

            JOptionPane.showMessageDialog(null, "Consulta desmarcada.");

            Command managePatientsWindow = new ManagePatientsWindow(main_window, db, psicologo);
            invoker.setCommand(managePatientsWindow);
            invoker.executeCommand();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
