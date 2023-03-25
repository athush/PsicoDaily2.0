package myProj;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class RecordWindow 
{
    private JFrame window;
    private Container c;
	private JLabel title;
    private JLabel record_title_label;
    private JTextField record_title;
    private JTextArea record_body;
    private JButton ok;
	private JButton returnButton;
    private JComboBox ids;

	public RecordWindow(int method, Database db)
	{
        window = new JFrame();
            
        window.setTitle("Tela de Registros");
        window.setBounds(650, 200, 600, 480);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        window.setLayout(new BoxLayout(window,BoxLayout.PAGE_AXIS));

        c = window.getContentPane();
        c.setLayout(null);            
		title = new JLabel("Registros", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(600, 60);
        title.setLocation(0, 10);
        c.add(title);
        
		/*

		*/

        if (method == 1)
        {
            record_title_label = new JLabel("Título do registro:");
            record_title_label.setFont(new Font("Arial", Font.PLAIN, 20));
            record_title_label.setSize(300, 30);
            record_title_label.setLocation(50, 90);
            c.add(record_title_label);
            
            record_title = new JTextField();
            record_title.setFont(new Font("Arial", Font.PLAIN, 20));
            record_title.setSize(250, 30);
            record_title.setLocation(50, 120);
            c.add(record_title);
            
            record_body = new JTextArea();
            record_body.setFont(new Font("Arial", Font.PLAIN, 20));
            record_body.setSize(500, 200);
            record_body.setLocation(50, 170);
            record_body.setLineWrap(true);
            c.add(record_body);

            ok = new JButton("Confirmar");
            ok.setFont(new Font("Arial", Font.PLAIN, 20));
            ok.setSize(150, 30);
            ok.setLocation(225, 390);
            c.add(ok);
        }
        else if (method == 2)
        {
            record_title_label = new JLabel("ID do registro", JLabel.CENTER);
            record_title_label.setFont(new Font("Arial", Font.PLAIN, 30));
            record_title_label.setSize(600, 30);
            record_title_label.setLocation(0, 150);
            c.add(record_title_label);

            record_title = new JTextField();
            record_title.setFont(new Font("Arial", Font.PLAIN, 20));
            record_title.setSize(300, 30);
            record_title.setLocation(150, 200);
            c.add(record_title);

			returnButton = new JButton("Cancelar");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(150, 390);
            c.add(returnButton);

            returnButton = new JButton("Excluir");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(325, 390);
            c.add(returnButton);
        }
        else if (method == 3)
        {
            // Pega os ids dos registros do banco de dados
            // ids = new JComboBox(lista_de_ids);
            
            record_title_label = new JLabel("Título do registro:");
            record_title_label.setFont(new Font("Arial", Font.PLAIN, 20));
            record_title_label.setSize(300, 30);
            record_title_label.setLocation(50, 90);
            c.add(record_title_label);
            
            record_title = new JTextField();
            record_title.setFont(new Font("Arial", Font.PLAIN, 20));
            record_title.setSize(250, 30);
            record_title.setLocation(50, 120);
            c.add(record_title);
            
            record_body = new JTextArea();
            record_body.setFont(new Font("Arial", Font.PLAIN, 20));
            record_body.setSize(500, 200);
            record_body.setLocation(50, 170);
            record_body.setLineWrap(true);
            c.add(record_body);

            returnButton = new JButton("Cancelar");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(150, 390);
            c.add(returnButton);

			// returnButton.addActionListener(new ActionListener() 
            // {
            //     @Override
            //     public void actionPerformed(ActionEvent actionEvent) 
            //     {
            //         window.dispose();
            //         main_window.setVisible(true);
            //     }
            // });

            ok = new JButton("Editar");
            ok.setFont(new Font("Arial", Font.PLAIN, 20));
            ok.setSize(150, 30);
            ok.setLocation(325, 390);
            c.add(ok);
        }
        
        window.setVisible(true);
	}    
}
