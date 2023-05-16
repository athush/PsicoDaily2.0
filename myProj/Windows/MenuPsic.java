package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
//import java.util.Date;
//import java.text.SimpleDateFormat;  

public class MenuPsic implements Command{
    public boolean isClosed = false;
    JFrame window = new JFrame();
    Invoker invoker = new Invoker();

    JFrame main_window;
    Database db;
    User user;

    public MenuPsic(JFrame main_window, Database db, User user){
        this.main_window = main_window;
        this.db = db;
        this.user = user;
    }

    public void execute()
    {
        window.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowEvent)
            {
               window.dispose();
               main_window.setVisible(true);
            }        
        }); 


        Psic psic = (Psic) user;
        window.setTitle("Menu Psicologo");
        window.setBounds(650, 200, 700, 500);
        window.setResizable(false);

        Container c = window.getContentPane();
        c.setLayout(null);

        //Adding Panel With Layout in Default Window
        JPanel jPanel=new JPanel();

        // Main label
        JLabel main_label = new JLabel("Menu Psicólogo", JLabel.CENTER);
        main_label.setFont(new Font("Arial", Font.BOLD, 30));
        main_label.setSize(600, 60);
        main_label.setLocation(10, 10);
        c.add(main_label);

        // Show psychologist profile
        JLabel user_profile = new JLabel("Seu perfil: ");
        user_profile.setFont(new Font("Arial", Font.BOLD, 18));
        user_profile.setSize(600, 30);
        user_profile.setLocation(30, 75);
        c.add(user_profile);

        // Id
        JLabel idPsicLabel = new JLabel("ID: " + psic.id + "\n");
        idPsicLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        idPsicLabel.setSize(600, 30);
        idPsicLabel.setLocation(30, 100);
        c.add(idPsicLabel);

        // Nome
        JLabel nomePsico = new JLabel("Nome: " + psic.name + "\n");
        nomePsico.setFont(new Font("Arial", Font.PLAIN, 18));
        nomePsico.setSize(600, 30);
        nomePsico.setLocation(30, 125);
        c.add(nomePsico);

        // CRP
        JLabel crpPsico = new JLabel("CRP: "+ psic.crp + "\n");
        crpPsico.setFont(new Font("Arial", Font.PLAIN, 18));
        crpPsico.setSize(600, 30);
        crpPsico.setLocation(30, 150);
        c.add(crpPsico);

        // Num pacientes
        JLabel numPacientes = new JLabel("Quantidade de pacientes: " + psic.patient_list.size());
        numPacientes.setFont(new Font("Arial", Font.PLAIN, 18));
        numPacientes.setSize(600, 30);
        numPacientes.setLocation(30, 175);
        c.add(numPacientes);
        
        // Show pacients
        JButton showPacientes = new JButton("Exibir pacientes");
        showPacientes.setFont(new Font("Arial", Font.PLAIN, 15));
        showPacientes.setSize(175, 30);
        showPacientes.setLocation(213, 300);
        c.add(showPacientes);

        showPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Command managePatientsWindow = new ManagePatientsWindow(main_window, db, psic);
                invoker.setCommand(managePatientsWindow);
                invoker.executeCommand();
                window.dispose();
            }
        });

        // Link Patients
        JButton link_patients = new JButton("Vincular Paciente");

        link_patients.setFont(new Font("Arial", Font.PLAIN, 15));
        link_patients.setSize(175, 30);
        link_patients.setLocation(408, 300);
        
        link_patients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Command vincPatient = new VincPatient(main_window, db, psic);
                invoker.setCommand(vincPatient);
                invoker.executeCommand();
                window.dispose();
            }
        });

        c.add(link_patients);

        // Log out

        JButton logout = new JButton("Sair da conta");

        logout.setFont(new Font("Arial", Font.PLAIN, 15));
        logout.setSize(175, 30);
        logout.setLocation(18, 300);
        c.add(logout);

        logout.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        window.add(jPanel);
        window.setVisible(true);
    }
}
