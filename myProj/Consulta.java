package myProj;

import java.util.Date;
import java.text.SimpleDateFormat;
import myProj.exceptions.TimeInvalidException;
import java.util.ArrayList;

public class Consulta {
    int id_paciente, id_psicologo, id_consulta;
    TimeInterval intervaloConsulta;

    public Consulta(int id_paciente, int id_psicologo, int id_consulta) {
        this.id_paciente = id_paciente;
        this.id_psicologo = id_psicologo;
        this.id_consulta = id_consulta;
        // this.intervaloConsulta = new TimeInterval(inicio, termino);
    }

    public boolean checkHorario(ArrayList<Consulta> consultas, Date ini, Date fin) {
        for(Consulta consulta : consultas) {
            try 
            {
                if (consulta.id_psicologo == this.id_psicologo)
                {
                    String horasMarcada = Integer.toString(consulta.intervaloConsulta.dataInicio.getHours());
                    System.out.println("Horainicio: " + horasMarcada);
                    String minutosMarcada = Integer.toString(consulta.intervaloConsulta.dataInicio.getMinutes());
                    if (minutosMarcada.equals("0"))
                        minutosMarcada = "00";
                    String horarioInicioMarcada = horasMarcada + minutosMarcada;
    
                    String horasNova = Integer.toString(ini.getHours());
                    String minutosNova = Integer.toString(ini.getMinutes());
                    if (minutosNova.equals("0"))
                        minutosNova = "00";
                    String horarioInicioNova = horasNova + minutosNova;
                    int horarioMarcada = Integer.parseInt(horarioInicioMarcada);
                    int horarioNova = Integer.parseInt(horarioInicioNova);

                    if (consulta.intervaloConsulta.dataInicio.getDay() == ini.getDay())
                    {
                        int diferencaHoras = Math.max(horarioMarcada, horarioNova) - Math.min(horarioMarcada, horarioNova);
                        if (diferencaHoras < 100)
                        {
                            throw new TimeInvalidException("Horário já reservado.");
                        }
                    }
                }
            } 
            catch (TimeInvalidException e)
            {
                throw new TimeInvalidException("Horário já reservado.");
            }
            catch (Exception e)
            {
                throw new TimeInvalidException("Formato de hora errado.");
            }
        }
        return true;
    }

    public boolean setHorario(Date dia, String horario_inicio, String horario_fim, ArrayList<Consulta> consultas) {    
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        String toDate = f.format(dia);
        
        Date date_inicio, date_termino;

        try {    
            SimpleDateFormat aux = new SimpleDateFormat("HH:mm dd-MM-yyyy");
            
            date_inicio = aux.parse(horario_inicio + " " + toDate);
            date_termino = aux.parse(horario_fim + " " + toDate);
        }
        catch (Exception e) 
        {
            throw new TimeInvalidException("Formato errado.");
        }
        
        System.out.println("Checa horario: ");
        if(checkHorario(consultas, date_inicio, date_termino)) 
        {
            System.out.println("Checou");
            this.intervaloConsulta = new TimeInterval(date_inicio, date_termino);
            // this.intervaloConsulta.dataFim = date_termino;
            return true;
        }
        else {
            throw new TimeInvalidException("Horário já reservado.");    
        }
    }

    public String getHorario()
    {
        return intervaloConsulta.dataInicio.toString();
    }
}
