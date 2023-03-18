package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menu extends JFrame 
{
    public boolean isClosed = false;

    public Menu(int type, JFrame main_window) 
    {
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowEvent)
            {
               dispose();
               main_window.setVisible(true);
            }        
        });

        if (type == 1)              // Psicologo
        {
            setTitle("Menu Psicologo");
            setBounds(640, 200, 640, 400);
            setResizable(false);

            Container c = getContentPane();
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

            JLabel nomePsico = new JLabel("Nome: \n");
            nomePsico.setFont(new Font("Arial", Font.PLAIN, 15));
            nomePsico.setSize(600, 30);
            nomePsico.setLocation(15, 90);
            c.add(nomePsico);

            JLabel crpPsico = new JLabel("CRP: \n");
            crpPsico.setFont(new Font("Arial", Font.PLAIN, 15));
            crpPsico.setSize(600, 30);
            crpPsico.setLocation(15, 115);
            c.add(crpPsico);

            JLabel numPacientes = new JLabel("Quantidade de pacientes: \n");
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

            // Show pacient records

            JButton showRegistros = new JButton("Exibir registro");

            showRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            showRegistros.setSize(175, 30);
            showRegistros.setLocation(425, 300);
            c.add(showRegistros);

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
                    dispose();
                    main_window.setVisible(true);
                }
            });


            add(jPanel);
        }
        else if (type == 2)         // Paciente
        {
            setTitle("Menu Paciente");
            setBounds(640, 200, 640, 400);
            setResizable(false);

            Container c = getContentPane();
            c.setLayout(null);

            //Adding Panel With Layout in Default Window
            JPanel jPanel=new JPanel();

            // Main label
            JLabel main_label = new JLabel("Menu Paciente", JLabel.CENTER);
            main_label.setFont(new Font("Arial", Font.BOLD, 30));
            main_label.setSize(600, 60);
            main_label.setLocation(10, 10);
            c.add(main_label);

            // Show pacient profile
            JLabel user_profile = new JLabel("Seu perfil: ");
            user_profile.setFont(new Font("Arial", Font.BOLD, 15));
            user_profile.setSize(600, 30);
            user_profile.setLocation(15, 60);
            c.add(user_profile);

            // Name

            JLabel nomePsico = new JLabel("Nome: \n");
            nomePsico.setFont(new Font("Arial", Font.PLAIN, 15));
            nomePsico.setSize(600, 30);
            nomePsico.setLocation(15, 90);
            c.add(nomePsico);

            // Psychologis name

            JLabel psicoName = new JLabel("Psicólogo: \n");
            psicoName.setFont(new Font("Arial", Font.PLAIN, 15));
            psicoName.setSize(600, 30);
            psicoName.setLocation(15, 115);
            c.add(psicoName);
            
            // Show pacients

            JButton exibirRegistros = new JButton("Exibir seus registros");

            exibirRegistros.setFont(new Font("Arial", Font.PLAIN, 15));
            exibirRegistros.setSize(175, 30);
            exibirRegistros.setLocation(225, 300);
            c.add(exibirRegistros);

            // Show pacient records

            JButton mostrarConsultas = new JButton("Consultas Marcadas");

            mostrarConsultas.setFont(new Font("Arial", Font.PLAIN, 15));
            mostrarConsultas.setSize(175, 30);
            mostrarConsultas.setLocation(425, 300);
            c.add(mostrarConsultas);

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
                    dispose();
                    main_window.setVisible(true);
                }
            });


            add(jPanel);
        }
    }

}
