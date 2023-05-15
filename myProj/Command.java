package myProj;

import javax.swing.*;

interface Command {
    //void execute(JFrame main_window, Database db);

    void execute(JFrame main_window, Database db, User user);    
}

