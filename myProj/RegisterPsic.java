package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RegisterPsic implements Command {
    public Boolean isClosed = false;
    JFrame window = new JFrame();

    public void execute(JFrame main_window, Database db, User user){
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        window.setTitle("Registro");
        window.setBounds(650, 200, 700, 520);
        window.setResizable(false);

        Container c = window.getContentPane();
        c.setLayout(null);

        // Adding Panel With Layout in Default Window
        JPanel jPanel = new JPanel();

        // Input text
        JLabel main_label = new JLabel("Registro Psicólogo",
                JLabel.CENTER);
        main_label.setFont(new Font("Arial", Font.BOLD, 30));
        main_label.setSize(600, 60);
        main_label.setLocation(0, 0);

        // Name Field

        JLabel nameLabel = new JLabel("Nome: ");
        JTextField nameInput = new JTextField();

        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameLabel.setSize(300, 30);
        nameLabel.setLocation(150, 60);

        nameInput.setFont(new Font("Arial", Font.PLAIN, 15));
        nameInput.setSize(300, 30);
        nameInput.setLocation(150, 90);

        // Email Field
        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailInput = new JTextField();

        emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        emailLabel.setSize(300, 30);
        emailLabel.setLocation(150, 130);

        emailInput.setFont(new Font("Arial", Font.PLAIN, 15));
        emailInput.setSize(300, 30);
        emailInput.setLocation(150, 160);

        // CPF Label

        JLabel cpfLabel = new JLabel("CPF: ");
        JTextField cpfInput = new JTextField();

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

        JLabel passwordLabel = new JLabel("Senha: ");
        JPasswordField passwordInput = new JPasswordField();

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

        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String name = nameInput.getText();
                    String email = emailInput.getText();
                    db.email_cadastrado(email);
                    String cpf = cpfInput.getText();
                    String crp = crpInput.getText();
                    char[] password = passwordInput.getPassword();

                    User new_psic = new Psic(db.autoinc_user, name, email, password, cpf, crp);

                    db.add_user(new_psic);
                    JOptionPane.showMessageDialog(null, "Psicólogo Cadastrado!");
                    window.dispose();
                    main_window.setVisible(true);
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }
        });

        // Return Button

        JButton returnButton = new JButton("Retornar");

        returnButton.setFont(new Font("Arial", Font.PLAIN, 15));
        returnButton.setSize(140, 30);
        returnButton.setLocation(155, 430);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        // Adding itens into panel

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

        window.add(jPanel);
        window.setVisible(true);
    }

    
}
