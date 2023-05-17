package myProj;

import javax.swing.JOptionPane;

public class DesmarConsulta implements Command{
    Invoker invoker = new Invoker();
    private Patient patient;
    private Database db;

    public DesmarConsulta(Database db, Patient patient){
        this.db = db;
        this.patient = patient;
    }

    public void execute() {
        try {
            db.desmarca_consulta(db.checa_consulta(patient), patient);
            patient.estadoConsulta = new ConsultaDesmarcada();

            JOptionPane.showMessageDialog(null, "Consulta desmarcada.");

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
