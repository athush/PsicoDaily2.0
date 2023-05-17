package myProj;

import java.util.Date;

public class TimeInterval {
    Date dataInicio;
    Date dataFim;

    public TimeInterval(Date inicio, Date fim)
    {
        this.dataInicio = inicio;
        this.dataFim = fim;
    }

    public Date getInicio() {
        return this.dataInicio;
    }

    public Date getFim() {
        return this.dataFim;
    }
}
