package myProj;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class NewRecord implements Command{
    public boolean isClosed = false;
    JFrame window = new JFrame();
    Invoker invoker = new Invoker();

    private Container c;
    private JLabel title;
    private JLabel record_title_label;
    private JTextField record_title;
    private JTextArea record_body;
    private JButton submitButton;
    private JButton returnButton;

    JFrame main_window;
    User user;

    public NewRecord(JFrame main_window, User user){
        this.main_window = main_window;
        this.user = user;
    }

    public void execute(){
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        window.setTitle("Tela de Registros");
        window.setBounds(650, 200, 740, 500);
        window.setResizable(false);

        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));

        c = window.getContentPane();
        c.setLayout(null);
        title = new JLabel("Registros", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(600, 60);
        title.setLocation(0, 10);
        c.add(title);

        record_title_label = new JLabel("Título do registro:");
        record_title_label.setFont(new Font("Arial", Font.PLAIN, 16));
        record_title_label.setSize(300, 30);
        record_title_label.setLocation(50, 50);
        c.add(record_title_label);

        record_title = new JTextField();
        record_title.setFont(new Font("Arial", Font.PLAIN, 20));
        record_title.setSize(250, 30);
        record_title.setLocation(50, 80);
        c.add(record_title);

        record_title_label = new JLabel("Registro:");
        record_title_label.setFont(new Font("Arial", Font.PLAIN, 16));
        record_title_label.setSize(300, 30);
        record_title_label.setLocation(50, 115);
        c.add(record_title_label);

        record_body = new JTextArea();
        record_body.setFont(new Font("Arial", Font.PLAIN, 20));
        record_body.setSize(500, 150);
        record_body.setLocation(50, 150);
        record_body.setLineWrap(true);
        c.add(record_body);

        returnButton = new JButton("Cancelar");
        returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
        returnButton.setSize(150, 30);
        returnButton.setLocation(125, 315);
        c.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        submitButton = new JButton("Registrar");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setSize(150, 30);
        submitButton.setLocation(325, 315);
        c.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (record_title.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum registro feito.");
                } else {
                    Patient patient = (Patient) user;
                    String title = record_title.getText();
                    String text = record_body.getText();
                    int numRecords = patient.records.size();

                    Record newRecord = new Record(title, text, numRecords + 1);

                    patient.addRecord(newRecord);

                    JOptionPane.showMessageDialog(null, "Registro feito com sucesso.");
                    window.dispose();
                    main_window.setVisible(true);
                }

            }
        });
        window.setVisible(true);
    }

    
}
