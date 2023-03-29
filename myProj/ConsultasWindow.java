package myProj;

import javax.swing.*;
import javax.xml.crypto.Data;

import java.awt.event.*;
import java.awt.*;


public class ConsultasWindow {
    
    public Boolean isClosed = false;
    JFrame window = new JFrame();

    private String dates[]
		= { "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10",
			"11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25",
			"26", "27", "28", "29", "30",
			"31" };
	private String months[]
		= { "01", "02", "03", "04",
			"05", "06", "07", "08",
			"09", "10", "11", "12" };
	private String years[]
		= { "2020", "2021", "2022", "2023",
			"2024" };

	private String hours[]
	= { "06", "07", "08", "09", "10",
		"11", "12", "13", "14", "15",
		"16", "17", "18", "19", "20",
		"21", "22" };

	private String minutes[]
	= { "00", "10","20", "30", "40", "50"};

    private JLabel title;
    private JLabel dataLabel;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
	private JComboBox horasInicio;
    private JComboBox minutosInicio;
	private JComboBox horasFim;
    private JComboBox minutosFim;
    private JLabel add;
    private JTextArea tadd;
    private JButton returnButton;
    private JButton confirmButton;

    
    public ConsultasWindow(int type, JFrame main_window, Patient pacient, Psic psicologo, Database db) 
    {
        window.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowEvent)
            {
               window.dispose();
               main_window.setVisible(true);
            }        
        }); 

        window.setTitle("Consultas");
		window.setBounds(300, 90, 600, 600);
		window.setResizable(false);

        Container c = window.getContentPane();
		c.setLayout(null);
    

		title = new JLabel("Marcar consultas", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setSize(600, 60);
		title.setLocation(0, 30);
		c.add(title);

        dataLabel = new JLabel("Data da consulta", JLabel.CENTER);
		dataLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dataLabel.setSize(600, 100);
		dataLabel.setLocation(0, 180);
		c.add(dataLabel);

		date = new JComboBox(dates);
		date.setFont(new Font("Arial", Font.PLAIN, 15));
		date.setSize(60, 20);
		date.setLocation(200, 250);
		c.add(date);
		month = new JComboBox(months);
		month.setFont(new Font("Arial", Font.PLAIN, 15));
		month.setSize(60, 20);
		month.setLocation(270, 250);
		c.add(month);
		year = new JComboBox(years);
		year.setFont(new Font("Arial", Font.PLAIN, 15));
		year.setSize(60, 20);
		year.setLocation(340, 250);
		c.add(year);

		JLabel inicioLabel = new JLabel("Horario de inicio: ");
		inicioLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		inicioLabel.setSize(150, 20);
		inicioLabel.setLocation(110, 300);
		c.add(inicioLabel);

		horasInicio = new JComboBox(hours);
		horasInicio.setFont(new Font("Arial", Font.PLAIN, 15));
		horasInicio.setSize(60, 20);
		horasInicio.setLocation(230, 300);
		c.add(horasInicio);
		minutosInicio = new JComboBox(minutes);
		minutosInicio.setFont(new Font("Arial", Font.PLAIN, 15));
		minutosInicio.setSize(60, 20);
		minutosInicio.setLocation(310, 300);
		c.add(minutosInicio);

		JLabel fimLabel = new JLabel("Horario de fim: ");
		fimLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		fimLabel.setSize(150, 20);
		fimLabel.setLocation(110, 340);
		c.add(fimLabel);

		horasFim = new JComboBox(hours);
		horasFim.setFont(new Font("Arial", Font.PLAIN, 15));
		horasFim.setSize(60, 20);
		horasFim.setLocation(230, 340);
		c.add(horasFim);

		minutosFim = new JComboBox(minutes);
		minutosFim.setFont(new Font("Arial", Font.PLAIN, 15));
		minutosFim.setSize(60, 20);
		minutosFim.setLocation(310, 340);
		c.add(minutosFim);

		// Retornar

		returnButton = new JButton("Cancelar");
		returnButton.setFont(new Font("Arial", Font.PLAIN, 15));
		returnButton.setSize(100, 20);
		returnButton.setLocation(190, 450);

        returnButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent) 
                {
                    window.dispose();
                    main_window.setVisible(true);
                }
            });

		c.add(returnButton);

        confirmButton = new JButton("Marcar");
		confirmButton.setFont(new Font("Arial", Font.PLAIN, 15));
		confirmButton.setSize(100, 20);
		confirmButton.setLocation(310, 450);
		c.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
				// String dia = diaInput.getText();
				// String mes = mesInput.getText();
				// String ano = anoInput.getText();

				String dia = date.getSelectedItem().toString();
				String mes = month.getSelectedItem().toString();
				String ano = year.getSelectedItem().toString();

				String horaInicio = horasInicio.getSelectedItem().toString();
				String minutoInicio = minutosFim.getSelectedItem().toString();
				String horaFim = horasFim.getSelectedItem().toString();
				String minutoFim = minutosFim.getSelectedItem().toString();

				Consulta novaConsulta = new Consulta(pacient.id, psicologo.id, -1);

				String horarioDataInico = horaInicio + ":" + minutoInicio + " " + dia + "-" + mes + "-" + ano;
				String horarioDataFim = horaFim + ":" + minutoFim + " " + dia + "-" + mes + "-" + ano;

				System.out.println(horarioDataInico);
				System.out.println(horarioDataFim);

				boolean horaValida = novaConsulta.setHorario(horarioDataInico, horarioDataFim, db.database_consulta);
				if (horaValida)
				{
					JOptionPane.showMessageDialog(null, "Consulta marcada.");
					db.add_consulta(novaConsulta);
					pacient.checaConsulta = true;

					window.dispose();
					main_window.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Horarios conflitantes");
				}
            }
        });

        window.setVisible(true);
    }
}
