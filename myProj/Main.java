package myProj;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class MyFrame implements ActionListener 
{
	// Components of the Form
	private JFrame window;
    private Container c;
	private JLabel title;
    private JButton login;
    private JButton register;
    private JButton sair;

	public MyFrame()
	{
		window = new JFrame();
        
        window.setTitle("Home");
		window.setBounds(650, 200, 600, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
        
        window.setLayout(new BoxLayout(window,BoxLayout.PAGE_AXIS));

		c = window.getContentPane();
		c.setLayout(null);

		title = new JLabel("Seja bem-vindo(a) ao PsicoDaily!", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setSize(600, 60);
		title.setLocation(0, 30);
		c.add(title);

        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(300, 30);
        login.setLocation(150, 150);

        login.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                Login login_window = new Login(1, window);
                login_window.setVisible(true);
                
                window.setVisible(false);
            }
        });

        c.add(login);

        JLabel registerLabel = new JLabel("Ainda não tem conta?");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        registerLabel.setSize(300, 30);
        registerLabel.setLocation(225, 200);

        c.add(registerLabel);

        register = new JButton("Sou Psicólogo");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(140, 30);
        register.setLocation(155, 230);
        
        register.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                Login login_window = new Login(2, window);
                login_window.setVisible(true);
                
                window.setVisible(false);
            }
        });
        
        c.add(register);

        register = new JButton("Sou Paciente");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(135, 30);
        register.setLocation(310, 230);
        
        register.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                Login login_window = new Login(3, window);
                login_window.setVisible(true);
                
                window.setVisible(false);
            }
        });
        
        c.add(register);

        sair = new JButton("Sair");
        sair.setFont(new Font("Arial", Font.PLAIN, 15));
        sair.setSize(300, 30);
        sair.setLocation(150, 300);
        
        sair.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                System.exit(0);
            }
        });
        
        c.add(sair);

        // Menus teste

        JLabel teste = new JLabel("Menus (teste)");
		teste.setFont(new Font("Arial", Font.BOLD, 12));
		teste.setSize(600, 60);
		teste.setLocation(35, 100);

        JButton menuPsico = new JButton("Psicólogo");
        menuPsico.setFont(new Font("Arial", Font.PLAIN, 12));
        menuPsico.setSize(100, 30);
        menuPsico.setLocation(25, 150);

        JButton menuPac = new JButton("Paciente");
        menuPac.setFont(new Font("Arial", Font.PLAIN, 12));
        menuPac.setSize(100, 30);
        menuPac.setLocation(25, 190);

        menuPsico.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                Menu menu_window = new Menu(1, window);
                menu_window.setVisible(true);
                
                window.setVisible(false);
            }
        });

        menuPac.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                Menu menu_window = new Menu(2, window);
                menu_window.setVisible(true);
                
                window.setVisible(false);
            }
        });
        
		c.add(teste);
        c.add(menuPsico);
        c.add(menuPac);

		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
	}
}

public class Main implements ActionListener
{   
    public static void main(String[] args)
    {
        new MyFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {}
}