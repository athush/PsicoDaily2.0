package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MenuPatient implements Command {
    public boolean isClosed = false;
    JFrame window = new JFrame();
    Invoker invoker = new Invoker();

    JFrame main_window;
    Database db;
    User user;

    public MenuPatient(JFrame main_window, Database db, User user){
        this.main_window = main_window;
        this.db = db;
        this.user = user;
    }

    public void execute(){
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        
        Patient patient = (Patient) user;
        window.setTitle("Menu Paciente");
        window.setBounds(650, 200, 700, 500);
        window.setResizable(false);
        
        System.out.println("Consulta: " + patient.estadoConsulta.temConsulta());
        // System.out.println("Vinculo: " + patient.vinculo.estaVinculado());

        System.out.println("Tem registro: " + patient.existeRegistro.existeRegistro());

        Container c = window.getContentPane();
        c.setLayout(null);

        // Adding Panel With Layout in Default Window
        JPanel jPanel = new JPanel();

        // Main label
        JLabel main_label = new JLabel("Menu Paciente", JLabel.CENTER);
        main_label.setFont(new Font("Arial", Font.BOLD, 30));
        main_label.setSize(600, 60);
        main_label.setLocation(10, 0);
        c.add(main_label);

        // Show pacient profile
        JLabel user_profile = new JLabel("Seu perfil: ");
        user_profile.setFont(new Font("Arial", Font.BOLD, 18));
        user_profile.setSize(600, 30);
        user_profile.setLocation(30, 75);
        c.add(user_profile);

        // Id

        JLabel idPacLabel = new JLabel("ID: " + patient.id + "\n");
        idPacLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        idPacLabel.setSize(600, 30);
        idPacLabel.setLocation(30, 100);
        c.add(idPacLabel);

        // Name

        JLabel nomePsico = new JLabel("Nome: " + patient.name + "\n");
        nomePsico.setFont(new Font("Arial", Font.PLAIN, 18));
        nomePsico.setSize(600, 30);
        nomePsico.setLocation(30, 125);
        c.add(nomePsico);

        // Psychologist name
        JLabel psicoName = new JLabel();
        try {
            User psic_patient = db.get_user(patient.psic_id);
            psicoName.setText("Psicólogo: " + psic_patient.name);
        } catch (NullPointerException e) {
            psicoName.setText("Psicólogo: Não vinculado");
        } finally {
            psicoName.setFont(new Font("Arial", Font.PLAIN, 18));
            psicoName.setSize(600, 30);
            psicoName.setLocation(30, 150);
            c.add(psicoName);
        }

        // Next Schedule

        JLabel proxConsulta = new JLabel("Proxima consulta: ");
        proxConsulta.setFont(new Font("Arial", Font.PLAIN, 18));
        proxConsulta.setSize(165, 30);
        proxConsulta.setLocation(30, 175);
        c.add(proxConsulta);

        JLabel checaConsulta;
        String horarioConsultaString = "";
        try {
            Consulta consultaPaciente = db.checa_consulta(patient);
            Date horarioConsulta = consultaPaciente.inicio;
            String pattern = "dd/MM/yyyy à's' HH:mm";
            SimpleDateFormat dataFormato = new SimpleDateFormat(pattern);
            horarioConsultaString = dataFormato.format(horarioConsulta);
        } catch (NullPointerException e) {
            horarioConsultaString = "Consulta não marcada.";
        }

        checaConsulta = new JLabel(horarioConsultaString);
        checaConsulta.setFont(new Font("Arial", Font.PLAIN, 18));
        checaConsulta.setSize(350, 30);
        checaConsulta.setLocation(195, 175);
        c.add(checaConsulta);

        // Create records

        JButton criarRegistros = new JButton("Novo Registro");

        criarRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
        criarRegistros.setSize(175, 30);
        criarRegistros.setLocation(408, 300);

        criarRegistros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Command newRecord = new NewRecord(window, patient);
                invoker.setCommand(newRecord);
                invoker.executeCommand();
                window.dispose();
            }
        });

        c.add(criarRegistros);

        // Show records

        JButton exibirRegistros = new JButton("Exibir Registros");

        exibirRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
        exibirRegistros.setSize(175, 30);
        exibirRegistros.setLocation(213, 300);

        exibirRegistros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Command viewRecord = new ViewRecord(window, patient);
                invoker.setCommand(viewRecord);
                invoker.executeCommand();
                window.setVisible(false);
            }
        });

        c.add(exibirRegistros);

        // Log out

        JButton logout = new JButton("Sair da conta");

        logout.setFont(new Font("Arial", Font.PLAIN, 15));
        logout.setSize(175, 30);
        logout.setLocation(18, 300);
        c.add(logout);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        window.add(jPanel);
        window.setVisible(true);
    }
}
