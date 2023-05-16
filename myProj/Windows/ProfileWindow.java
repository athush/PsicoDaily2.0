package myProj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfileWindow implements Command{
    public boolean isClosed = false;
    JFrame window = new JFrame();
    Invoker invoker = new Invoker();

    private Container c;
    private JLabel title;
    private JButton returnButton;

    JFrame main_window;
    Database db;

    public ProfileWindow(JFrame main_window, Database db){
        this.main_window = main_window;
        this.db = db;
    }

    public void execute(){
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        window.setTitle("Seus Pacientes");
        window.setBounds(650, 200, 700, 500);
        window.setResizable(false);
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));

        c = window.getContentPane();
        c.setLayout(null);

        c = window.getContentPane();
        c.setLayout(null);

        title = new JLabel("Todos os usuários", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(600, 60);
        title.setLocation(0, 10);
        c.add(title);

        returnButton = new JButton("Cancelar");
        returnButton.setFont(new Font("Arial", Font.PLAIN, 15));
        returnButton.setSize(100, 25);
        returnButton.setLocation(460, 320);
        c.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.dispose();
                main_window.setVisible(true);
            }
        });

        int altura = 60;

        for (User u : db.database_user) {
            ArrayList<String> resultado = u.profile();

            for (String dado : resultado) {
                JLabel inf = new JLabel(dado);
                inf.setSize(400, 30);
                inf.setFont(new Font("Arial", Font.BOLD, 16));
                inf.setLocation(30, altura);
                c.add(inf);
                altura += 20;
            }

            altura += 10;
        }

        window.setVisible(true);
    }
}
