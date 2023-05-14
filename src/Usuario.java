import java.util.ArrayList;

// public class Usuario {
//     String id;
//     String name;
//     String password, cpf;

//     public Usuario(String id, String name, String password, String cpf){
//         this.id = id;
//         this.name = name;
//         this.password = password;
//         this.cpf = cpf;
//     }
// }

abstract class Usuario {
    String id;
    String name;
    String password, cpf;

    public Usuario(String id, String name, String password, String cpf){
        this.id = id;
        this.name = name;
        this.password = password;
        this.cpf = cpf;
    }

    abstract void perfil();

    abstract void marcarConsulta(ArrayList<Paciente> listaPaciente, ArrayList<Consulta> consultas);
}