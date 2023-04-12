package myProj;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.*;
import java.nio.channels.SelectableChannel;
import java.awt.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.sql.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;

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
		= { "2023", "2024", "2025" };

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
		window.setBounds(650, 200, 600, 400);
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
		dataLabel.setLocation(0, 80);
		c.add(dataLabel);

		UtilDateModel model = new UtilDateModel();
		model.setDate(2023, 5, 11);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		//datePanel.setSize(300, 300);
		datePanel.setPreferredSize(new Dimension(250, 250));
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setLocation(210, 150);
		datePicker.setSize(200, 40);
		
		
		c.add(datePicker);

		JLabel inicioLabel = new JLabel("Horário", JLabel.CENTER);
		inicioLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		inicioLabel.setSize(600, 20);
		inicioLabel.setLocation(0, 200);
		c.add(inicioLabel);

		horasInicio = new JComboBox(hours);
		horasInicio.setFont(new Font("Arial", Font.PLAIN, 15));
		horasInicio.setSize(60, 20);
		horasInicio.setLocation(230, 230);
		c.add(horasInicio);
		minutosInicio = new JComboBox(minutes);
		minutosInicio.setFont(new Font("Arial", Font.PLAIN, 15));
		minutosInicio.setSize(60, 20);
		minutosInicio.setLocation(310, 230);
		c.add(minutosInicio);

		// Retornar

		returnButton = new JButton("Cancelar");
		returnButton.setFont(new Font("Arial", Font.PLAIN, 15));
		returnButton.setSize(100, 20);
		returnButton.setLocation(190, 300);

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
		confirmButton.setLocation(310, 300);
		c.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
				Date now = new Date();
				Date selectedDate = (Date) datePicker.getModel().getValue();
        
				if (selectedDate.compareTo(now) <= 0)
				{
					System.out.println("");
					JOptionPane.showMessageDialog(null, "Data inválida!");
					
				}
				else
				{
					String horaInicio = horasInicio.getSelectedItem().toString();
					String minutoInicio = minutosInicio.getSelectedItem().toString();

					Consulta novaConsulta = new Consulta(pacient.id, psicologo.id, -1);

					String horarioDataInicio = horaInicio + ":" + minutoInicio;
					
					// Encontrando horário de fim
					int aux = Integer.parseInt(horaInicio) + 1;
					String horarioDataFim = "";
					
					if (aux < 10)
					{
						horarioDataFim = "0" + String.valueOf(aux) + ":" + minutoInicio;
					}
					else
					{
						horarioDataFim = String.valueOf(aux) + ":" + minutoInicio;
					}  

					boolean horaValida = novaConsulta.setHorario(selectedDate, horarioDataInicio, horarioDataFim, db.database_consulta);

					
					if (horaValida)
					{
						JOptionPane.showMessageDialog(null, "Consulta marcada.");

						System.out.println(novaConsulta.inicio);
						// novaConsulta.horariosConflitantes(db.database_consulta, novaConsulta.inicio);


						db.add_consulta(novaConsulta);

						window.dispose();
						main_window.dispose();
						ManagePatientsWindow new_window = new ManagePatientsWindow(1, main_window, db, psicologo);
						// main_window.setVisible(true);
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Horário inválido!");
					}
				}
            }
        });

        window.setVisible(true);
    }
}
