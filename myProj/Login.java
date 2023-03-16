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

            // Submit Button
            JButton submit = new JButton("Confirmar");

            submit.setFont(new Font("Arial", Font.PLAIN, 15));
            submit.setSize(150, 30);
            submit.setLocation(225, 280);

            //Adding Item into panel

            c.add(main_label);
            c.add(emailLabel);
            c.add(emailInput);
            c.add(passwordLabel);
            c.add(passwordInput);
            c.add(submit);

            add(jPanel);
        }
        else
        {
            setTitle("Login");
            setBounds(650, 200, 600, 400);
            setResizable(false);

            Container c = getContentPane();
            c.setLayout(null);

            //Adding Panel With Layout in Default Window
            JPanel jPanel=new JPanel();

            // Input text
            JLabel main_label = new JLabel("Registro", JLabel.CENTER);
            main_label.setFont(new Font("Arial", Font.BOLD, 30));
            main_label.setSize(600, 60);
            main_label.setLocation(0, 30);

            // Name Field

            JLabel nameLabel=new JLabel("Nome: ");
            JTextField nameInput=new JTextField();

            nameInput.setFont(new Font("Arial", Font.PLAIN, 15));
            nameInput.setSize(300, 30);
            nameInput.setLocation(150, 120);

            nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            nameLabel.setSize(300, 30);
            nameLabel.setLocation(150, 90);

            // Email Field
            JLabel emailLabel=new JLabel("Email: ");
            JTextField emailInput=new JTextField();

            emailInput.setFont(new Font("Arial", Font.PLAIN, 15));
            emailInput.setSize(300, 30);
            emailInput.setLocation(150, 190);

            emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            emailLabel.setSize(300, 30);
            emailLabel.setLocation(150, 160);

            // Password Field
            
            JLabel passwordLabel=new JLabel("Senha: ");
            JPasswordField passwordInput=new JPasswordField();

            passwordInput.setFont(new Font("Arial", Font.PLAIN, 15));
            passwordInput.setSize(300, 30);
            passwordInput.setLocation(150, 260);

            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            passwordLabel.setSize(300, 30);
            passwordLabel.setLocation(150, 230);

            // Submit Button
            
            JButton submit = new JButton("Confirmar");

            submit.setFont(new Font("Arial", Font.PLAIN, 15));
            submit.setSize(150, 30);
            submit.setLocation(225, 310);

            //Adding itens into panel

            c.add(main_label);
            c.add(nameLabel);
            c.add(nameInput);
            c.add(emailLabel);
            c.add(emailInput);
            c.add(passwordLabel);
            c.add(passwordInput);
            c.add(submit);

            add(jPanel);
        }
    }
}
