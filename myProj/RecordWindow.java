package myProj;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class RecordWindow 
{
    public boolean isClosed = false;
    JFrame window = new JFrame();

    private Container c;
	private JLabel title;
    private JLabel record_title_label;
    private JTextField record_title;
    private JTextArea record_body;
    private JButton submitButton;
	private JButton returnButton;
    private JComboBox ids;
    private JScrollPane pane;

	public RecordWindow(int method, JFrame main_window, Patient patient, int index)
	{
        window.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowEvent)
            {
               window.dispose();
               main_window.setVisible(true);
            }        
        }); 
            
        window.setTitle("Tela de Registros");
        window.setBounds(650, 200, 600, 480);
        window.setResizable(false);
        
        window.setLayout(new BoxLayout(window,BoxLayout.PAGE_AXIS));

        c = window.getContentPane();
        c.setLayout(null);            

        if (method == 1)
        {
            title = new JLabel("Registros", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);

            record_title_label = new JLabel("Título do registro:");
            record_title_label.setFont(new Font("Arial", Font.PLAIN, 16));
            record_title_label.setSize(300, 30);
            record_title_label.setLocation(50, 70);
            c.add(record_title_label);
            
            record_title = new JTextField();
            record_title.setFont(new Font("Arial", Font.PLAIN, 20));
            record_title.setSize(250, 30);
            record_title.setLocation(50, 100);
            c.add(record_title);

            record_title_label = new JLabel("Registro:");
            record_title_label.setFont(new Font("Arial", Font.PLAIN, 16));
            record_title_label.setSize(300, 30);
            record_title_label.setLocation(50, 135);
            c.add(record_title_label);
            
            record_body = new JTextArea();
            record_body.setFont(new Font("Arial", Font.PLAIN, 20));
            record_body.setSize(500, 200);
            record_body.setLocation(50, 170);
            record_body.setLineWrap(true);
            c.add(record_body);

            returnButton = new JButton("Cancelar");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(125, 390);
            c.add(returnButton);

            returnButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    window.dispose();
                    main_window.setVisible(true);
                }
            });

            submitButton = new JButton("Registrar");
            submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
            submitButton.setSize(150, 30);
            submitButton.setLocation(325, 390);
            c.add(submitButton);

            submitButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    if (record_title.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Nenhum registro feito.");
                    }
                    else
                    {
                        String title = record_title.getText();
                        String text = record_body.getText();
                        int numRecords = patient.records.size();
                        patient.addRecord(title, text, numRecords + 1);
                        
                        JOptionPane.showMessageDialog(null, "Registro feito com sucesso.");
                        window.dispose();
                        main_window.setVisible(true);
                    }

                }
            });
        }
        else if (method == 2)
        {
            title = new JLabel("Registros", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);
            
            int numRegistros = patient.records.size();
            int altura = 70;

            for (int i = 0; i < numRegistros; i++)
            {
                String tituloRegistro = patient.records.get(i).getTitle();
                JLabel tituloLabel = new JLabel("Titulo registro: " + tituloRegistro);
                tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
                tituloLabel.setSize(300, 30);
                tituloLabel.setLocation(30, altura);

                String textoRegistro = patient.records.get(i).getText();
                JLabel textoLabel = new JLabel("Registro: " + textoRegistro);
                textoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                textoLabel.setSize(350, 30);
                textoLabel.setLocation(30, altura + 20);

                int idRegistro = patient.records.get(i).getId();
                JLabel idLabel = new JLabel("ID do Registro: " + idRegistro);
                idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                idLabel.setSize(350, 30);
                idLabel.setLocation(30, altura + 40);

                c.add(idLabel);
                c.add(textoLabel);
                c.add(tituloLabel);
                altura += 70;
            }
        }
        else if (method == 3)       // Excluir
        {
            title = new JLabel("Registros", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);

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

            returnButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    window.dispose();
                    main_window.setVisible(true);
                }
            });

            returnButton = new JButton("Excluir");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(325, 390);
            c.add(returnButton);
        }
        else if (method == 4)       // Editar
        {
            title = new JLabel("Registros", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);

            JLabel id_edit_label = new JLabel("Id do registro para editar:", JLabel.CENTER);
            id_edit_label.setFont(new Font("Arial", Font.PLAIN, 20));
            id_edit_label.setSize(300, 30);
            id_edit_label.setLocation(50, 90);
            c.add(id_edit_label);
            
            JTextField id_text_area = new JTextField();
            id_text_area.setFont(new Font("Arial", Font.PLAIN, 20));
            id_text_area.setSize(300, 30);
            id_text_area.setLocation(150, 200);
            c.add(id_text_area);

            returnButton = new JButton("Cancelar");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(125, 390);
            c.add(returnButton);

			returnButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    window.dispose();
                    main_window.setVisible(true);
                }
            });

            submitButton = new JButton("Editar");
            submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
            submitButton.setSize(150, 30);
            submitButton.setLocation(325, 390);

            submitButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    int idDigitado = Integer.parseInt(id_text_area.getText());
                    if (idDigitado > patient.records.size() || idDigitado == 0)
                    {
                        
                        JOptionPane.showMessageDialog(null, "Registro não encontrado.");
                    }
                    else
                    {
                        RecordWindow new_window = new RecordWindow(5, window, patient, idDigitado);
                        window.setVisible(false);
                    }
                }
            });

            c.add(submitButton);
        }
        else if (method == 5)   // Editar tela
        {
            title = new JLabel("Editar Registro", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 30));
            title.setSize(600, 60);
            title.setLocation(0, 10);
            c.add(title);

            record_title_label = new JLabel("Título do registro:");
            record_title_label.setFont(new Font("Arial", Font.PLAIN, 16));
            record_title_label.setSize(300, 30);
            record_title_label.setLocation(50, 70);
            c.add(record_title_label);
            
            record_title = new JTextField();
            record_title.setFont(new Font("Arial", Font.PLAIN, 20));
            record_title.setSize(250, 30);
            record_title.setLocation(50, 100);
            String tituloOriginal = patient.records.get(index - 1).getTitle();
            record_title.setText(tituloOriginal);
            c.add(record_title);

            record_title_label = new JLabel("Registro:");
            record_title_label.setFont(new Font("Arial", Font.PLAIN, 16));
            record_title_label.setSize(300, 30);
            record_title_label.setLocation(50, 135);
            c.add(record_title_label);
            
            record_body = new JTextArea();
            record_body.setFont(new Font("Arial", Font.PLAIN, 20));
            record_body.setSize(500, 200);
            record_body.setLocation(50, 170);
            record_body.setLineWrap(true);
            String registroOriginal = patient.records.get(index - 1).getText();
            record_body.setText(registroOriginal);
            c.add(record_body);

            returnButton = new JButton("Cancelar");
            returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
            returnButton.setSize(150, 30);
            returnButton.setLocation(125, 390);
            c.add(returnButton);

            returnButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    window.dispose();
                    main_window.setVisible(true);
                }
            });

            submitButton = new JButton("Editar");
            submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
            submitButton.setSize(150, 30);
            submitButton.setLocation(325, 390);
            c.add(submitButton);

            submitButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    if (record_title.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Nenhum registro feito.");
                    }
                    else
                    {
                        String title = record_title.getText();
                        String text = record_body.getText();

                        patient.editRecord(title, text, index);
                        
                        JOptionPane.showMessageDialog(null, "Registro editado com sucesso.");
                        window.dispose();
                        main_window.setVisible(true);
                    }

                }
            });
        }
        window.setVisible(true);
	}    
}
