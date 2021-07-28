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

    public Date(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
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

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean before(Date that) {
        if(ano<that.ano) {
            return true;
        }
        if(ano>that.ano) {
            return false;
        }
        if(mes<that.mes) {
            return true;
        }
        if(mes>that.mes) {
            return false;
        }
        if(dia<that.dia) {
            return true;
        }
        if(dia>that.dia) {
            return false;
        }
        return true;
    }

    public static Date nextDate(Date dt) {
        int d= dt.getDia(), m=dt.getMes(),a=dt.getAno();

        switch (dt.getMes()) {
            case 1, 3, 5, 7, 9, 11 -> {
                if (dt.getDia() == 31) {
                    d = 1;
                    m = dt.getMes() + 1;
                }else {
                    d++;
                }
            }
            case 2 -> {
                if (dt.getDia() == 28) {
                    d = 1;
                    m = dt.getMes() + 1;
                }else {
                    d++;
                }
            }
            case 4, 6, 8, 10 -> {
                if (dt.getDia() == 30) {
                    d = 1;
                    m = dt.getMes() + 1;
                }else {
                    d++;
                }
            }
            case 12 -> {
                if (dt.getDia() == 30) {
                    d = 1;
                    m = 1;
                    a = dt.getAno() + 1;
                }else {
                    d++;
                }
            }
        }
        return new Date(d,m,a);
    }

    @Override
    public String toString() {
        return dia + "-" + mes + "-" + ano;
    }
}
