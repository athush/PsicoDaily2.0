package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class VincPatient implements Command{
    
    public boolean isClosed = false;
    JFrame window = new JFrame();
    Invoker invoker = new Invoker();

    private Container c;
    private JLabel title;
    private JButton submitButton;
    private JButton returnButton;

    JFrame main_window;
    Database db;
    User user;

    public VincPatient(JFrame main_window, Database db, User user){
        this.main_window = main_window;
        this.db = db;
        this.user = user;
    }

    public void execute(){
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                Command menuPsic = new MenuPsic(main_window, db, user);
                invoker.setCommand(menuPsic);
                invoker.executeCommand();
                window.dispose();
            }
        });

        Psic psicologo = (Psic) user;
        
        window.setTitle("Seus Pacientes");
        window.setBounds(650, 200, 700, 500);
        window.setResizable(false);
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));

        c = window.getContentPane();
        c.setLayout(null);

        title = new JLabel("Vincular Paciente", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(600, 60);
        title.setLocation(0, 10);
        c.add(title);

        JLabel id_edit_label = new JLabel("ID do paciente:", JLabel.CENTER);
        id_edit_label.setFont(new Font("Arial", Font.PLAIN, 20));
        id_edit_label.setSize(300, 30);
        id_edit_label.setLocation(73, 100);
        c.add(id_edit_label);

        JTextField id_text_area = new JTextField();
        id_text_area.setFont(new Font("Arial", Font.PLAIN, 20));
        id_text_area.setSize(300, 30);
        id_text_area.setLocation(150, 180);
        c.add(id_text_area);

        returnButton = new JButton("Cancelar");
        returnButton.setFont(new Font("Arial", Font.PLAIN, 20));
        returnButton.setSize(150, 30);
        returnButton.setLocation(125, 285);
        c.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Command menuPsic = new MenuPsic(main_window, db, user);
                invoker.setCommand(menuPsic);
                invoker.executeCommand();
                window.dispose();
            }
        });

        submitButton = new JButton("Vincular");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setSize(150, 30);
        submitButton.setLocation(325, 285);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int idDigitado = Integer.parseInt(id_text_area.getText());
                    Patient patient = (Patient) db.get_user(idDigitado);

                    if (patient.vinculo.estaVinculado()) {
                        JOptionPane.showMessageDialog(null, "Paciente já possui psicólogo.");
                    } else {
                        patient.setPsico(psicologo.id);
                        psicologo.addPatient(patient);

                        JOptionPane.showMessageDialog(null, "Paciente " + patient.name + " vinculado.");
                        window.dispose();
                        Command menuPsic = new MenuPsic(main_window, db, user);
                        invoker.setCommand(menuPsic);
                        invoker.executeCommand();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite o ID corretamente");

                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Paciente não encontrado.");
                } catch (ClassCastException e) {
                    JOptionPane.showMessageDialog(null, "Usuário Inválido.");
                }
            }
        });

        c.add(submitButton);

        window.setVisible(true);

    }
}
