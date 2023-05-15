package myProj;

import javax.swing.*;

public class Invoker {
    private Command command;

    public void setCommand(Command c) {
        this.command = c;
    }

    public void executeCommand(JFrame main_window, Database db, User user) {
        this.command.execute(main_window, db ,user);
    }
}
