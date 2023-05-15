package myProj;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class MyFrame implements ActionListener 
{

	// Components of the Form
	private JFrame window;
    private Container c;
	private JLabel title;
    private JButton login;
    private JButton register;
    private JButton sair;
    private JButton exibir;
    Invoker invoker = new Invoker();

	public MyFrame()
	{
        Database db = new Database();
        
		window = new JFrame();
        
        window.setTitle("Home");
		window.setBounds(700, 200, 700, 500);
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
                Command login = new Login();
                invoker.setCommand(login);
                invoker.executeCommand(window, db, null);
                
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
                Command registerPsic = new RegisterPsic();
                invoker.setCommand(registerPsic);
                invoker.executeCommand(window, db, null);
                
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
                Command registerPatient = new RegisterPatient();
                invoker.setCommand(registerPatient);
                invoker.executeCommand(window, db, null);
                
                window.setVisible(false);
            }
        });
        
        c.add(register);

        sair = new JButton("Sair");
        sair.setFont(new Font("Arial", Font.PLAIN, 15));
        sair.setSize(135, 30);
        sair.setLocation(155, 300);
        
        sair.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                System.exit(0);
            }
        });
        
        c.add(sair);
        
        exibir = new JButton("Usuários");
        exibir.setFont(new Font("Arial", Font.PLAIN, 15));
        exibir.setSize(135, 30);
        exibir.setLocation(310, 300);

        exibir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Command profileWindow = new ProfileWindow();
                invoker.setCommand(profileWindow);
                invoker.executeCommand(window, db, null);
            }
        });

        c.add(exibir);
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