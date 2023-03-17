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

        register = new JButton("Novo usuário");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(300, 30);
        register.setLocation(150, 190);
        
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

        sair = new JButton("Sair");
        sair.setFont(new Font("Arial", Font.PLAIN, 15));
        sair.setSize(300, 30);
        sair.setLocation(150, 230);
        
        sair.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                System.exit(0);
            }
        });
        
        c.add(sair);

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