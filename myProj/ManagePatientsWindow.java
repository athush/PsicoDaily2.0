package myProj;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ManagePatientsWindow 
{
    public boolean isClosed = false;
    JFrame window = new JFrame();

    private Container c;
    private JLabel title;
    private JLabel record_title_label;
    private JTextField record_title;
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

                JButton recordsButton = new JButton("Registros");
                recordsButton.setFont(new Font("Arial", Font.PLAIN, 12));
                recordsButton.setSize(120, 30);
                recordsButton.setLocation(320, altura + 10);

                recordsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        RecordWindow new_window = new RecordWindow(2, window, patient, 0);
                        window.setVisible(false);
                    }
                });

                JButton unlinkButton = new JButton("Desvincular");
                unlinkButton.setFont(new Font("Arial", Font.PLAIN, 12));
                unlinkButton.setSize(120, 30);
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
                altura += 70;
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
                    } else {
                        
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
