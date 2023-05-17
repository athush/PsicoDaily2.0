package myProj;

import javax.swing.JOptionPane;

public class ExcluirUser implements Command{
    
    private User user;  
    private Database db;

    public ExcluirUser(Database db, User user){
        this.db = db;
        this.user = user;
    }

    public void execute(){
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o perfil de " + user.name + "?");
        if (resposta == 0) {
            db.delete_user(user);
        }
    }
}
