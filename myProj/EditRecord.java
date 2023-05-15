package myProj;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class EditRecord implements Command{
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

    public void execute(JFrame main_window, Database db, User user) {
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




        window.setVisible(true);
    }
    
}
