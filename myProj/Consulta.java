package myProj;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Consulta {
    int id_paciente, id_psicologo, id_consulta;
    Date inicio, termino;

    public Consulta(int id_paciente, int id_psicologo, int id_consulta) {
        this.id_paciente = id_paciente;
        this.id_psicologo = id_psicologo;
        this.id_consulta = id_consulta;
    }

    public boolean batendo(Date ini, Date fin, Consulta consulta) {
        boolean inicioOverlapping = ini.compareTo(consulta.inicio) >= 0 && ini.compareTo(consulta.termino) < 0;
        boolean terminoOverlapping = fin.compareTo(consulta.inicio) > 0 && fin.compareTo(consulta.termino) <= 0;
        
        return (inicioOverlapping || terminoOverlapping);
    }

    public boolean checkHorario(ArrayList<Consulta> consultas, Date ini, Date fin) {
        for(Consulta consulta : consultas) {
            if(consulta.id_paciente == this.id_paciente && consulta.id_psicologo == this.id_psicologo) {
                //checar se o horário bate
                if(this.batendo(ini, fin, consulta)) return false;
            }
        }
        return true;
    }

    public boolean setHorario(Date dia, String horario_inicio, String horario_fim, ArrayList<Consulta> consultas) {
        // retorna false se deu errado       
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String toDate = f.format(dia);
        
        Date date_inicio, date_termino;

        try 
        {    
            SimpleDateFormat aux = new SimpleDateFormat("HH:mm dd-MM-yyyy");
            
            date_inicio = aux.parse(horario_inicio + " " + toDate);
            date_termino = aux.parse(horario_fim + " " + toDate);
        }
        catch (Exception e) 
        {
            System.out.println("Horário inválido");
            return false;
        }
        
        if(checkHorario(consultas, date_inicio, date_termino)) 
        {
            this.inicio = date_inicio;
            this.termino = date_termino;
            return true;
        }
        return false;
    }

    public String getHorario()
    {
        return inicio.toString();
    }
}
