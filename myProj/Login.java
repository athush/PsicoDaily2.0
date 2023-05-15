package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;

public class Login implements Command
{
    public Boolean isClosed = false;
    JFrame window = new JFrame();
    Invoker invoker = new Invoker();
    JFrame main_window;
    Database db;

    public Login(JFrame main_window, Database db){
        this.main_window = main_window;
        this.db = db;
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
        
        window.setTitle("Login");
        window.setBounds(650, 200, 700, 500);
        window.setResizable(false);

        Container c = window.getContentPane();
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
        submit.setLocation(225, 300);

        submit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                try{
                    String email = emailInput.getText();
                    char[] password = passwordInput.getPassword();

                    User found = db.get_user(email);

                    Boolean compare = Arrays.equals(password, found.password);

                    if (compare) {
                        if(found instanceof Psic){
                            Command menuPsic = new MenuPsic(main_window, db, found);
                            invoker.setCommand(menuPsic);
                            
                        }else if(found instanceof Patient){
                            Command menuPatient = new MenuPatient(main_window, db, found);
                            invoker.setCommand(menuPatient);
                        }
                        
                        invoker.executeCommand();
                        window.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta!");
                    }
                }catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });

        //Adding Item into panel

        c.add(main_label);
        c.add(emailLabel);
        c.add(emailInput);
        c.add(passwordLabel);
        c.add(passwordInput);
        c.add(submit);

        window.add(jPanel);
        window.setVisible(true);
    }
}
