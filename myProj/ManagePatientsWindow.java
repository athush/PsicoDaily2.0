package myProj;

import java.awt.Container;
import java.util.ArrayList;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;

public class ManagePatientsWindow {
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

    public ManagePatientsWindow(int type, JFrame main_window, Database db, User user) {
        Psic psicologo = (Psic) user;
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                window.dispose();
                Menu menu = new Menu(main_window, user, db);
            }
        });
        window.setTitle("Seus Pacientes");
        window.setBounds(650, 200, 600, 400);
        window.setResizable(false);
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));

        c = window.getContentPane();
        ;
        c.setLayout(null);

        // Exibir Pacientes
        if (type == 1) {
            title = new JLabel("Pacientes", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);

            int altura = 70;

            checaConsulta = new JLabel("");

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
                JButton consultaButton;
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
                    System.out.println("consulta nao encontrada 4");
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
                                ManagePatientsWindow new_window = new ManagePatientsWindow(1, main_window, db,
                                        psicologo);
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
                    public void actionPerformed(ActionEvent actionEvent) {
                        int resposta = JOptionPane.showConfirmDialog(null, "Deseja desvincular " + patient.name + "?");
                        if (resposta == 0) {
                            psicologo.patient_list.remove(patient);
                            patient.psic_id = -1;

                            window.dispose();
                            ManagePatientsWindow new_window = new ManagePatientsWindow(1, main_window, db, psicologo);
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
            id_edit_label.setLocation(73, 100);
            c.add(id_edit_label);

            JTextField id_text_area = new JTextField();
            id_text_area.setFont(new Font("Arial", Font.PLAIN, 20));
            id_text_area.setSize(300, 30);
            id_text_area.setLocation(150, 180);
            c.add(id_text_area);

            returnButton = new JButton("Cancelar");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(125, 285);
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
            submitButton.setLocation(325, 285);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        int idDigitado = Integer.parseInt(id_text_area.getText());
                        Patient patient = (Patient) db.get_user(idDigitado);

                        if (patient.psic_id != -1) {
                            JOptionPane.showMessageDialog(null, "Paciente já possui psicólogo.");
                        } else {
                            patient.setPsico(psicologo.id);
                            psicologo.addPatient(patient);

                            JOptionPane.showMessageDialog(null, "Paciente " + patient.name + " vinculado.");
                            window.dispose();
                            Menu menu = new Menu(main_window, psicologo, db); 
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Digite o ID corretamente");

                    } catch (NullPointerException e){
                        JOptionPane.showMessageDialog(null, "Paciente não encontrado.");
                    }
                }
            });

            c.add(submitButton);

        } else if (type == 3) { //   Profile
            title = new JLabel("Todos os usuários", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);

            returnButton = new JButton("Cancelar");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 15));
            returnButton.setSize(100, 25);
            returnButton.setLocation(460, 320);
            c.add(returnButton);

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    window.dispose();
                    main_window.setVisible(true);
                }
            });

            int altura = 60;

            for (User u : db.database_user) {
                ArrayList<String> resultado = u.profile();
                
                for (String dado:resultado){
                    JLabel inf = new JLabel(dado);
                    inf.setSize(400, 30);
                    inf.setFont(new Font("Arial", Font.BOLD, 16));
                    inf.setLocation(30, altura);
                    c.add(inf);
                    altura+=20;
                }
                
                altura += 10;
            }
        }
        window.setVisible(true);
    }
}
