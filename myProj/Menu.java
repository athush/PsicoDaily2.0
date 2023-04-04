package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;  

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
            window.setBounds(650, 200, 600, 400);
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
                    window.dispose();
                    ManagePatientsWindow new_window = new ManagePatientsWindow(1, main_window, db, psic);
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
                    ManagePatientsWindow new_window = new ManagePatientsWindow(2, main_window, db, psic);
                    // window.setVisible(false);
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
        else if (user instanceof Patient)         // Patient
        {
            Patient patient = (Patient) user;
            window.setTitle("Menu Paciente");
            window.setBounds(650, 200, 600, 400);
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

            User psic_patient = db.get_user(patient.psic_id);
            if(psic_patient != null){
                psicoName.setText("Psicólogo: " + psic_patient.name);
            }else{
                psicoName.setText("Psicólogo: Não vinculado");
            }
            psicoName.setFont(new Font("Arial", Font.PLAIN, 18));
            psicoName.setSize(600, 30);
            psicoName.setLocation(30, 150);
            c.add(psicoName);

            // Next Schedule

            JLabel proxConsulta = new JLabel("Proxima consulta: ");
            proxConsulta.setFont(new Font("Arial", Font.PLAIN, 18));
            proxConsulta.setSize(165, 30);
            proxConsulta.setLocation(30, 175);
            c.add(proxConsulta);


            Boolean temConsulta = patient.checaConsulta;
            JLabel checaConsulta;

            if (!temConsulta)
            {
                checaConsulta = new JLabel("Consulta não marcada.");
                checaConsulta.setFont(new Font("Arial", Font.PLAIN, 18));
                checaConsulta.setSize(350, 30);
                checaConsulta.setLocation(195, 175);
            }
            else {
                Consulta consultaPaciente = db.checa_consulta(patient);
                Date horarioConsulta = consultaPaciente.inicio;
                String pattern = "dd/MM/yyyy à's' HH:mm";
                SimpleDateFormat dataFormato = new SimpleDateFormat(pattern);
                String horarioConsultaString = dataFormato.format(horarioConsulta);

                System.out.println(horarioConsultaString);
                checaConsulta = new JLabel(horarioConsultaString);
                checaConsulta.setFont(new Font("Arial", Font.PLAIN, 18));
                checaConsulta.setSize(350, 30);
                checaConsulta.setLocation(195, 175);
            }

            c.add(checaConsulta);


            // Create records

            JButton criarRegistros = new JButton("Novo Registro");

            criarRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            criarRegistros.setSize(175, 30);
            criarRegistros.setLocation(408, 300);

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
            
            // Show records

            JButton exibirRegistros = new JButton("Exibir Registros");

            exibirRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            exibirRegistros.setSize(175, 30);
            exibirRegistros.setLocation(213, 300);

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

}
