package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menu
{
    public boolean isClosed = false;
    JFrame window = new JFrame();

    public Menu(JFrame main_window, User user, Database db)
    {
        window.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowEvent)
            {
               window.dispose();
               main_window.setVisible(true);
            }        
        }); 

        if (user instanceof Psic)              // Psicologo
        {
            Psic psic = (Psic) user;
            window.setTitle("Menu Psicologo");
            window.setBounds(640, 200, 640, 400);
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
            user_profile.setFont(new Font("Arial", Font.BOLD, 15));
            user_profile.setSize(600, 30);
            user_profile.setLocation(15, 60);
            c.add(user_profile);

            // Nome

            JLabel nomePsico = new JLabel("Nome: " + psic.name + "\n");
            nomePsico.setFont(new Font("Arial", Font.PLAIN, 15));
            nomePsico.setSize(600, 30);
            nomePsico.setLocation(15, 90);
            c.add(nomePsico);

            JLabel crpPsico = new JLabel("CRP: "+ psic.crp + "\n");
            crpPsico.setFont(new Font("Arial", Font.PLAIN, 15));
            crpPsico.setSize(600, 30);
            crpPsico.setLocation(15, 115);
            c.add(crpPsico);

            JLabel numPacientes = new JLabel("Quantidade de pacientes: " + psic.patient_list.size()+ "\n");
            numPacientes.setFont(new Font("Arial", Font.PLAIN, 15));
            numPacientes.setSize(600, 30);
            numPacientes.setLocation(15, 140);
            c.add(numPacientes);
            
            // Show pacients

            JButton showPacientes = new JButton("Exibir pacientes");

            showPacientes.setFont(new Font("Arial", Font.PLAIN, 15));
            showPacientes.setSize(175, 30);
            showPacientes.setLocation(225, 300);
            c.add(showPacientes);

            showPacientes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ManagePatientsWindow new_window = new ManagePatientsWindow(1, window, db, psic);
                    window.setVisible(false);
                    window.revalidate();
                }
            });

            // Show pacient records

            JButton showRegistros = new JButton("Exibir registro");

            showRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            showRegistros.setSize(175, 30);
            showRegistros.setLocation(425, 300);
            c.add(showRegistros);

            // Link Patients
            JButton link_patients = new JButton("Vincular Paciente");

            link_patients.setFont(new Font("Arial", Font.PLAIN, 15));
            link_patients.setSize(175, 30);
            link_patients.setLocation(25, 250);
            
            link_patients.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ManagePatientsWindow new_window = new ManagePatientsWindow(2, window, db, psic);
                    window.setVisible(false);
                }
            });

            c.add(link_patients);

            // Log out

            JButton logout = new JButton("Sair da conta");

            logout.setFont(new Font("Arial", Font.PLAIN, 15));
            logout.setSize(175, 30);
            logout.setLocation(25, 300);
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
        else if (user instanceof Patient)         // Patient
        {
            Patient patient = (Patient) user;
            window.setTitle("Menu Paciente");
            window.setBounds(640, 200, 640, 400);
            window.setResizable(false);

            Container c = window.getContentPane();
            c.setLayout(null);

            //Adding Panel With Layout in Default Window
            JPanel jPanel=new JPanel();

            // Main label
            JLabel main_label = new JLabel("Menu Paciente", JLabel.CENTER);
            main_label.setFont(new Font("Arial", Font.BOLD, 30));
            main_label.setSize(600, 60);
            main_label.setLocation(10, 0);
            c.add(main_label);

            // Show pacient profile
            JLabel user_profile = new JLabel("Seu perfil: ");
            user_profile.setFont(new Font("Arial", Font.BOLD, 15));
            user_profile.setSize(600, 30);
            user_profile.setLocation(30, 75);
            c.add(user_profile);

            // Name

            JLabel nomePsico = new JLabel("Nome: " + patient.name + "\n");
            nomePsico.setFont(new Font("Arial", Font.PLAIN, 15));
            nomePsico.setSize(600, 30);
            nomePsico.setLocation(30, 100);
            c.add(nomePsico);

            // Psychologist name
            JLabel psicoName = new JLabel();

            User psic_patient = db.get_user(patient.psic_id);
            if(psic_patient != null){
                psicoName.setText("Psicólogo: " + psic_patient.name);
            }else{
                psicoName.setText("Psicólogo: Não vinculado");
            }
            psicoName.setFont(new Font("Arial", Font.PLAIN, 15));
            psicoName.setSize(600, 30);
            psicoName.setLocation(30, 125);
            c.add(psicoName);

            JLabel idPacLabel = new JLabel("Seu id: " + patient.id + "\n");
            idPacLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            idPacLabel.setSize(600, 30);
            idPacLabel.setLocation(30, 150);
            c.add(idPacLabel);

            // Create records

            JButton criarRegistros = new JButton("Novo Registro");

            criarRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            criarRegistros.setSize(175, 30);
            criarRegistros.setLocation(415, 300);

            criarRegistros.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    RecordWindow new_window = new RecordWindow(1, window, patient, 0);
                    window.dispose();
                }
            });

            c.add(criarRegistros);

            // Edit records

            JButton editarRegistros = new JButton("Editar Registro");

            editarRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            editarRegistros.setSize(175, 30);
            editarRegistros.setLocation(25, 120);

            editarRegistros.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    RecordWindow new_window = new RecordWindow(4, window, patient, 0);
                    window.setVisible(false);
                }
            });

            //c.add(editarRegistros);


            // Delete records

            JButton excluirRegistros = new JButton("Excluir Registro");

            excluirRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            excluirRegistros.setSize(175, 30);
            excluirRegistros.setLocation(25, 165);

            excluirRegistros.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    RecordWindow new_window = new RecordWindow(3, window, patient, 0);
                    window.setVisible(false);
                }
            });

            //c.add(excluirRegistros);
            
            // Show records

            JButton exibirRegistros = new JButton("Exibir seus registros");

            exibirRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            exibirRegistros.setSize(175, 30);
            exibirRegistros.setLocation(220, 300);

            exibirRegistros.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    RecordWindow new_window = new RecordWindow(2, window, patient, 0);
                    window.setVisible(false);
                }
            });

            c.add(exibirRegistros);

            // Show pacient records

            JButton mostrarConsultas = new JButton("Consultas Marcadas");

            mostrarConsultas.setFont(new Font("Arial", Font.PLAIN, 15));
            mostrarConsultas.setSize(175, 30);
            mostrarConsultas.setLocation(25, 255);
            //c.add(mostrarConsultas);

            // Log out

            JButton logout = new JButton("Sair da conta");

            logout.setFont(new Font("Arial", Font.PLAIN, 15));
            logout.setSize(175, 30);
            logout.setLocation(25, 300);
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

}
