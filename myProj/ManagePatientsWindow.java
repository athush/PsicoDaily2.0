package myProj;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ManagePatientsWindow implements Command{
    public boolean isClosed = false;
    JFrame window = new JFrame();
    Invoker invoker = new Invoker();

    private Container c;
    private JLabel title;

    public void execute(JFrame main_window, Database db, User user) {
        
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                Command menuPsic = new MenuPsic();
                invoker.setCommand(menuPsic);
                invoker.executeCommand(main_window, db, user);
                window.dispose();
            }
        });

        Psic psicologo = (Psic) user;

        window.setTitle("Seus Pacientes");
        window.setBounds(650, 200, 700, 500);
        window.setResizable(false);
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));

        c = window.getContentPane();
        c.setLayout(null);
        
        // Exibir Pacientes
        title = new JLabel("Pacientes", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(600, 60);
        title.setLocation(0, 10);
        c.add(title);

        int altura = 70;

        for (Patient patient : psicologo.patient_list) {
            JLabel name = new JLabel("Nome: " + patient.name);
            name.setSize(300, 30);
            name.setFont(new Font("Arial", Font.BOLD, 16));
            name.setLocation(30, altura);

            JLabel email = new JLabel("Email: " + patient.email);
            email.setFont(new Font("Arial", Font.PLAIN, 16));
            email.setSize(350, 30);
            email.setLocation(30, altura + 40);

            JLabel id = new JLabel("ID: " + patient.id);
            id.setFont(new Font("Arial", Font.PLAIN, 16));
            id.setSize(350, 30);
            id.setLocation(30, altura + 20);

            JLabel proxConsulta = new JLabel("Proxima consulta: ");
            proxConsulta.setFont(new Font("Arial", Font.PLAIN, 16));
            proxConsulta.setSize(350, 30);
            proxConsulta.setLocation(30, altura + 60);

            //Boolean temConsulta = patient.checaConsulta;

            JLabel checaConsulta;
            //JButton consultaButton;
            String horarioConsultaString = "";
            Boolean marcado = false;
            try {
                Consulta consultaPaciente = db.checa_consulta(patient);
                Date horarioConsulta = consultaPaciente.inicio;
                String pattern = "dd/MM/yyyy à's' HH:mm";
                SimpleDateFormat dataFormato = new SimpleDateFormat(pattern);
                horarioConsultaString = dataFormato.format(horarioConsulta);
                marcado = true;
            } catch (NullPointerException e) {
                horarioConsultaString = e.getMessage();

            } finally {
                System.out.println("consulta nao encontrada 10");
                checaConsulta = new JLabel(horarioConsultaString);
                checaConsulta.setFont(new Font("Arial", Font.PLAIN, 16));
                checaConsulta.setSize(350, 30);
                checaConsulta.setLocation(175, altura + 60);
            }

            // Marcar consulta
            if (!marcado) {
                JButton consultaBotao = new JButton("Marcar consulta");
                consultaBotao.setFont(new Font("Arial", Font.PLAIN, 12));
                consultaBotao.setSize(110, 30);
                consultaBotao.setLocation(210, altura + 10);

                consultaBotao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        ConsultasWindow new_window = new ConsultasWindow(1, main_window, patient, psicologo, db);
                        window.setVisible(false);
                    }
                });

                c.add(consultaBotao);
            } else {
                JButton consultaBotao = new JButton("Desmarcar consulta");
                consultaBotao.setFont(new Font("Arial", Font.PLAIN, 12));
                consultaBotao.setSize(110, 30);
                consultaBotao.setLocation(210, altura + 10);

                consultaBotao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            db.desmarca_consulta(db.checa_consulta(patient), patient);

                            JOptionPane.showMessageDialog(null, "Consulta desmarcada.");
                            window.dispose();

                            Command managePatientsWindow = new ManagePatientsWindow();
                            invoker.setCommand(managePatientsWindow);
                            invoker.executeCommand(main_window, db, psicologo);
                        } catch (RuntimeException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                });

                c.add(consultaBotao);
            }

            JButton recordsButton = new JButton("Registros");
            recordsButton.setFont(new Font("Arial", Font.PLAIN, 12));
            recordsButton.setSize(110, 30);
            recordsButton.setLocation(330, altura + 10);

            recordsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Command viewRecord = new ViewRecord();
                    invoker.setCommand(viewRecord);
                    invoker.executeCommand(main_window, db, patient);

                    //RecordWindow new_window = new RecordWindow(2, window, patient, 0);
                    window.setVisible(false);
                }
            });

            JButton unlinkButton = new JButton("Desvincular");
            unlinkButton.setFont(new Font("Arial", Font.PLAIN, 12));
            unlinkButton.setSize(110, 30);
            unlinkButton.setLocation(450, altura + 10);
            unlinkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja desvincular " + patient.name + "?");
                    if (resposta == 0) {
                        psicologo.patient_list.remove(patient);
                        patient.psic_id = -1;

                        window.dispose();
                        
                        Command managePatientsWindow = new ManagePatientsWindow();
                        invoker.setCommand(managePatientsWindow);
                        invoker.executeCommand(main_window, db, psicologo);
                    }
                }
            });

            c.add(recordsButton);
            c.add(unlinkButton);
            c.add(name);
            c.add(email);
            c.add(id);
            c.add(proxConsulta);
            c.add(checaConsulta);
            altura += 85;
        }
        
        window.setVisible(true);
    }
}
