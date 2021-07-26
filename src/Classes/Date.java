package Classes;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {
    private int dia;
    private int mes;
    private int ano;

    public Date() {
        GregorianCalendar g = new GregorianCalendar();
        this.dia = g.get(Calendar.DAY_OF_MONTH);
        this.mes = g.get(Calendar.MONTH) + 1;
        this.ano = g.get(Calendar.YEAR);
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return dia + "-" + mes + "-" + ano;
    }
}
