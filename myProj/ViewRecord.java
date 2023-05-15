package myProj;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ViewRecord implements Command{
    public boolean isClosed = false;
    JFrame window = new JFrame();
    Invoker invoker = new Invoker();

    private Container c;
    private JLabel title;

    JFrame main_window;
    User user;

    public ViewRecord(JFrame main_window, User user){
        this.main_window = main_window;
        this.user = user;
    }

    public void execute() {
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

        Patient patient = (Patient) user;
        int numRegistros = patient.records.size();
        int altura = 70;

        for (int i = 0; i < numRegistros; i++) {
            Record record = patient.records.get(i);
            String tituloRegistro = record.getTitle();
            JLabel tituloLabel = new JLabel("Titulo registro: " + tituloRegistro);
            tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
            tituloLabel.setSize(300, 30);
            tituloLabel.setLocation(40, altura);

            String textoRegistro = record.getText();
            JLabel textoLabel = new JLabel("Registro: " + textoRegistro);
            textoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            textoLabel.setSize(350, 30);
            textoLabel.setLocation(40, altura + 20);

            int idRegistro = record.getId();
            JLabel idLabel = new JLabel("ID do Registro: " + idRegistro);
            idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            idLabel.setSize(350, 30);
            idLabel.setLocation(40, altura + 40);

            // Edit Button
            JButton editButton = new JButton("Editar");
            editButton.setFont(new Font("Arial", Font.PLAIN, 12));
            editButton.setSize(80, 30);
            editButton.setLocation(380, altura + 10);

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    window.dispose();
                    
                    Command editRecord = new EditRecord(main_window, patient, record.getId());
                    invoker.setCommand(editRecord);
                    invoker.executeCommand();
                }
            });

            // Delete Button
            JButton deleteButton = new JButton("Excluir");
            deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
            deleteButton.setSize(80, 30);
            deleteButton.setLocation(480, altura + 10);

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir esse registro?");
                    if (resposta == 0) {
                        patient.records.remove(record);
                        window.dispose();

                        Command viewRecord = new ViewRecord(main_window, patient);
                        invoker.setCommand(viewRecord);
                        invoker.executeCommand();
                        //RecordWindow new_window = new RecordWindow(2, main_window, patient, record.getId());

                    }
                }
            });

            c.add(editButton);
            c.add(deleteButton);
            c.add(idLabel);
            c.add(textoLabel);
            c.add(tituloLabel);
            altura += 70;
        }
        window.setVisible(true);
    }
    
}
