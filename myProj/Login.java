package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Login extends JFrame
{
    public Boolean isClosed = false;
    
    public Login(int type, JFrame main_window) 
    {
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowEvent)
            {
               dispose();
               main_window.setVisible(true);
            }        
        }); 
        
        if (type == 1)
        {
            setTitle("Login");
            setBounds(650, 200, 600, 400);
            setResizable(false);

            Container c = getContentPane();
            c.setLayout(null);

            //Adding Panel With Layout in Default Window
            JPanel jPanel=new JPanel();

            // Input text
            JLabel main_label = new JLabel("Login", JLabel.CENTER);
            main_label.setFont(new Font("Arial", Font.BOLD, 30));
            main_label.setSize(600, 60);
            main_label.setLocation(0, 30);

            // Email Field
            JLabel emailLabel=new JLabel("Email: ");
            JTextField emailInput=new JTextField();

            emailInput.setFont(new Font("Arial", Font.PLAIN, 15));
            emailInput.setSize(300, 30);
            emailInput.setLocation(150, 140);

            emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            emailLabel.setSize(300, 30);
            emailLabel.setLocation(150, 110);

            // Password Field
            JLabel passwordLabel=new JLabel("Senha: ");
            JPasswordField passwordInput=new JPasswordField();

            passwordInput.setFont(new Font("Arial", Font.PLAIN, 15));
            passwordInput.setSize(300, 30);
            passwordInput.setLocation(150, 210);

            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            passwordLabel.setSize(300, 30);
            passwordLabel.setLocation(150, 180);

            // RadioButton (Psichologist or pacient)
            JRadioButton psicho = new JRadioButton("Psicólogo");
            JRadioButton pacient = new JRadioButton("Paciente");

            psicho.setFont(new Font("Arial", Font.PLAIN, 12));
            psicho.setSelected(true);
            psicho.setSize(80, 20);
            psicho.setLocation(150, 250);

            pacient.setFont(new Font("Arial", Font.PLAIN, 12));
            pacient.setSelected(false);
            pacient.setSize(80, 20);
            pacient.setLocation(240, 250);

            ButtonGroup group = new ButtonGroup();
            group.add(psicho);
            group.add(pacient);

            // Submit Button
            JButton submit = new JButton("Confirmar");

            submit.setFont(new Font("Arial", Font.PLAIN, 15));
            submit.setSize(150, 30);
            submit.setLocation(225, 300);

            //Adding Item into panel

            c.add(main_label);
            c.add(emailLabel);
            c.add(emailInput);
            c.add(passwordLabel);
            c.add(passwordInput);
            c.add(pacient);
            c.add(psicho);
            c.add(submit);

            add(jPanel);
        }
        else if (type == 2)
        {
            setTitle("Registro");
            setBounds(650, 200, 600, 520);
            setResizable(false);

            Container c = getContentPane();
            c.setLayout(null);

            //Adding Panel With Layout in Default Window
            JPanel jPanel=new JPanel();

            // Input text
            JLabel main_label = new JLabel("Registro Psicólogo", JLabel.CENTER);
            main_label.setFont(new Font("Arial", Font.BOLD, 30));
            main_label.setSize(600, 60);
            main_label.setLocation(0, 0);

            // Name Field

            JLabel nameLabel=new JLabel("Nome: ");
            JTextField nameInput=new JTextField();

            nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            nameLabel.setSize(300, 30);
            nameLabel.setLocation(150, 60);

            nameInput.setFont(new Font("Arial", Font.PLAIN, 15));
            nameInput.setSize(300, 30);
            nameInput.setLocation(150, 90);

            // Email Field
            JLabel emailLabel=new JLabel("Email: ");
            JTextField emailInput=new JTextField();

            emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            emailLabel.setSize(300, 30);
            emailLabel.setLocation(150, 130);

            emailInput.setFont(new Font("Arial", Font.PLAIN, 15));
            emailInput.setSize(300, 30);
            emailInput.setLocation(150, 160);

            // CPF Label

            JLabel cpfLabel=new JLabel("CPF: ");
            JPasswordField cpfInput=new JPasswordField();

            cpfLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            cpfLabel.setSize(300, 30);
            cpfLabel.setLocation(150, 200);

            cpfInput.setFont(new Font("Arial", Font.PLAIN, 15));
            cpfInput.setSize(300, 30);
            cpfInput.setLocation(150, 230);

            // CRP Label

            JLabel crpLabel = new JLabel("CRP: ");
            JTextField crpInput = new JTextField();

            crpLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            crpLabel.setSize(300, 30);
            crpLabel.setLocation(150, 270);

            crpInput.setFont(new Font("Arial", Font.PLAIN, 15));
            crpInput.setSize(300, 30);
            crpInput.setLocation(150, 300);

            // Password Field
            
            JLabel passwordLabel=new JLabel("Senha: ");
            JPasswordField passwordInput=new JPasswordField();

            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            passwordLabel.setSize(300, 30);
            passwordLabel.setLocation(150, 340);

            passwordInput.setFont(new Font("Arial", Font.PLAIN, 15));
            passwordInput.setSize(300, 30);
            passwordInput.setLocation(150, 370);

            // Submit Button
            
            JButton submit = new JButton("Confirmar");

            submit.setFont(new Font("Arial", Font.PLAIN, 15));
            submit.setSize(135, 30);
            submit.setLocation(310, 430);

            // Return Button
            
            JButton returnButton = new JButton("Retornar");

            returnButton.setFont(new Font("Arial", Font.PLAIN, 15));
            returnButton.setSize(140, 30);
            returnButton.setLocation(155, 430);

            returnButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    dispose();
                    main_window.setVisible(true);
                }
            });

            //Adding itens into panel

            c.add(main_label);
            c.add(nameLabel);
            c.add(nameInput);
            c.add(emailLabel);
            c.add(emailInput);
            c.add(cpfLabel);
            c.add(cpfInput);
            c.add(crpLabel);
            c.add(crpInput);
            c.add(passwordLabel);
            c.add(passwordInput);
            c.add(submit);
            c.add(returnButton);

            add(jPanel);
        }
        else if (type == 3)
        {
            setTitle("Registro");
            setBounds(650, 200, 600, 450);
            setResizable(false);

            Container c = getContentPane();
            c.setLayout(null);

            //Adding Panel With Layout in Default Window
            JPanel jPanel=new JPanel();

            // Input text
            JLabel main_label = new JLabel("Registro Paciente", JLabel.CENTER);
            main_label.setFont(new Font("Arial", Font.BOLD, 30));
            main_label.setSize(600, 60);
            main_label.setLocation(0, 0);

            // Name Field

            JLabel nameLabel=new JLabel("Nome: ");
            JTextField nameInput=new JTextField();

            nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            nameLabel.setSize(300, 30);
            nameLabel.setLocation(150, 60);

            nameInput.setFont(new Font("Arial", Font.PLAIN, 15));
            nameInput.setSize(300, 30);
            nameInput.setLocation(150, 90);

            // Email Field
            JLabel emailLabel=new JLabel("Email: ");
            JTextField emailInput=new JTextField();

            emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            emailLabel.setSize(300, 30);
            emailLabel.setLocation(150, 130);

            emailInput.setFont(new Font("Arial", Font.PLAIN, 15));
            emailInput.setSize(300, 30);
            emailInput.setLocation(150, 160);

            // CPF Label

            JLabel cpfLabel=new JLabel("CPF: ");
            JPasswordField cpfInput=new JPasswordField();

            cpfLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            cpfLabel.setSize(300, 30);
            cpfLabel.setLocation(150, 200);

            cpfInput.setFont(new Font("Arial", Font.PLAIN, 15));
            cpfInput.setSize(300, 30);
            cpfInput.setLocation(150, 230);

            // Password Field
            
            JLabel passwordLabel=new JLabel("Senha: ");
            JPasswordField passwordInput=new JPasswordField();

            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            passwordLabel.setSize(300, 30);
            passwordLabel.setLocation(150, 270);

            passwordInput.setFont(new Font("Arial", Font.PLAIN, 15));
            passwordInput.setSize(300, 30);
            passwordInput.setLocation(150, 300);

            // Submit Button
            
            JButton submit = new JButton("Confirmar");

            submit.setFont(new Font("Arial", Font.PLAIN, 15));
            submit.setSize(135, 30);
            submit.setLocation(310, 360);

            // Return Button
            
            JButton returnButton = new JButton("Retornar");

            returnButton.setFont(new Font("Arial", Font.PLAIN, 15));
            returnButton.setSize(140, 30);
            returnButton.setLocation(155, 360);

            returnButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    dispose();
                    main_window.setVisible(true);
                }
            });

            //Adding itens into panel

            c.add(main_label);
            c.add(nameLabel);
            c.add(nameInput);
            c.add(emailLabel);
            c.add(emailInput);
            c.add(cpfLabel);
            c.add(cpfInput);
            c.add(passwordLabel);
            c.add(passwordInput);
            c.add(submit);
            c.add(returnButton);

            add(jPanel);
        }
    }
}
