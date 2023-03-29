package myProj;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;  

public class ManagePatientsWindow 
{
    public boolean isClosed = false;
    JFrame window = new JFrame();

    private Container c;
    private JLabel title;
    private JLabel record_title_label;
    private JTextField record_title;
    private JLabel checaConsulta;
    private JTextArea record_body;
    private JButton submitButton;
    private JButton returnButton;

    public ManagePatientsWindow(int type, JFrame main_window, Database db, Psic psicologo){
        window.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowEvent)
            {
                window.dispose();
                main_window.setVisible(true);
            }        
        });
        window.setTitle("Seus Pacientes");
        window.setBounds(650, 200, 600, 480);
        window.setResizable(false);
        
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));

        c = window.getContentPane();;
        c.setLayout(null);

        // Exibir Pacientes
        if(type == 1)
        {
            title = new JLabel("Pacientes", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);

            int numPatients = psicologo.patient_list.size();
            int altura = 70;

            checaConsulta = new JLabel("");
            SimpleDateFormat f = new SimpleDateFormat("HH:mm dd-MM-yyyy");

            for(int i = 0; i < numPatients; i++){
                Patient patient = psicologo.patient_list.get(i);

                JLabel name = new JLabel("Nome: "+patient.name);
                name.setSize(300, 30);
                name.setFont(new Font("Arial", Font.BOLD, 16));
                name.setLocation(30, altura);

                JLabel email = new JLabel("Email: " + patient.email);
                email.setFont(new Font("Arial", Font.PLAIN, 16));
                email.setSize(350, 30);
                email.setLocation(30, altura + 20);

                JLabel id = new JLabel("ID: " + patient.id);
                id.setFont(new Font("Arial", Font.PLAIN, 16));
                id.setSize(350, 30);
                id.setLocation(30, altura + 40);

                JLabel proxConsulta = new JLabel("Proxima consulta: ");
                proxConsulta.setFont(new Font("Arial", Font.PLAIN, 16));
                proxConsulta.setSize(350, 30);
                proxConsulta.setLocation(30, altura + 60);

                Boolean temConsulta = patient.checaConsulta;

                if (!temConsulta)
                {
                    checaConsulta = new JLabel("Paciente sem consulta marcada.");
                    checaConsulta.setFont(new Font("Arial", Font.PLAIN, 16));
                    checaConsulta.setSize(350, 30);
                    checaConsulta.setLocation(160, altura + 60);
                }
                else {
                    Consulta consultaPaciente = db.checa_consulta(patient);
                    String horarioConsulta = consultaPaciente.inicio.toString();
                    System.out.println(horarioConsulta);
                    checaConsulta = new JLabel(horarioConsulta);
                    checaConsulta.setFont(new Font("Arial", Font.PLAIN, 16));
                    checaConsulta.setSize(350, 30);
                    checaConsulta.setLocation(160, altura + 60);
                }

                // Marcar consulta

                JButton consultaBotao = new JButton("Marcar consulta");
                consultaBotao.setFont(new Font("Arial", Font.PLAIN, 12));
                consultaBotao.setSize(110, 30);
                consultaBotao.setLocation(210, altura + 10);

                consultaBotao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        ConsultasWindow new_window = new ConsultasWindow(1, window, patient, psicologo, db);
                        window.setVisible(false);
                    }
                });

                c.add(consultaBotao);

                JButton recordsButton = new JButton("Registros");
                recordsButton.setFont(new Font("Arial", Font.PLAIN, 12));
                recordsButton.setSize(110, 30);
                recordsButton.setLocation(330, altura + 10);

                recordsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        RecordWindow new_window = new RecordWindow(2, window, patient, 0);
                        window.setVisible(false);
                    }
                });

                JButton unlinkButton = new JButton("Desvincular");
                unlinkButton.setFont(new Font("Arial", Font.PLAIN, 12));
                unlinkButton.setSize(110, 30);
                unlinkButton.setLocation(450, altura + 10);
                unlinkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent){
                        int resposta = JOptionPane.showConfirmDialog(null, "Deseja desvincular " + patient.name +"?");
                        if (resposta == 0){
                            psicologo.patient_list.remove(patient);
                            patient.psic_id = -1;

                            window.dispose();
                            ManagePatientsWindow new_window = new ManagePatientsWindow(1, window, db, psicologo);
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
        }

        else if (type == 2) // Vincular Paciente
        {
            title = new JLabel("Vincular Paciente", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);

            JLabel id_edit_label = new JLabel("ID do paciente:", JLabel.CENTER);
            id_edit_label.setFont(new Font("Arial", Font.PLAIN, 20));
            id_edit_label.setSize(300, 30);
            id_edit_label.setLocation(50, 90);
            c.add(id_edit_label);

            JTextField id_text_area = new JTextField();
            id_text_area.setFont(new Font("Arial", Font.PLAIN, 20));
            id_text_area.setSize(300, 30);
            id_text_area.setLocation(150, 200);
            c.add(id_text_area);

            returnButton = new JButton("Cancelar");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(125, 390);
            c.add(returnButton);

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    window.dispose();
                    main_window.setVisible(true);
                }
            });

            submitButton = new JButton("Vincular");
            submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
            submitButton.setSize(150, 30);
            submitButton.setLocation(325, 390);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int idDigitado = Integer.parseInt(id_text_area.getText());
                    Patient patient = db.get_patient(idDigitado);
                    
                    if (patient == null) {
                        JOptionPane.showMessageDialog(null, "Paciente não encontrado.");
                    }
                    else if (patient.psic_id != -1)
                    {
                        JOptionPane.showMessageDialog(null, "Paciente já possui psicólogo.");
                    }
                    else 
                    {
                        patient.setPsico(psicologo.id);
                        psicologo.addPatient(patient);

                        JOptionPane.showMessageDialog(null, "Paciente "+patient.name+" vinculado." );
                        window.dispose();
                        main_window.setVisible(true);
                    }
                }
            });

            c.add(submitButton);
        }
        window.setVisible(true);
    }
}
