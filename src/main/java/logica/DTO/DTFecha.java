package logica.DTO;

import java.time.LocalDate;

public class DTFecha
{
    private int d;
    private int mm;
    private int yyyy;

     @Override
    public String toString() {
        return "" + this.d + "/" + this.mm + "/" + this.yyyy;
    }// para que me aparesca un formato mas parecido a una fecha fntostring

    
    public DTFecha() 
    {
    }
    public DTFecha(LocalDate fecha) {
    this.d = fecha.getDayOfMonth();
    this.mm = fecha.getMonthValue();
    this.yyyy = fecha.getYear();
}
    public DTFecha(int _d, int _mm, int _yyyy) 
    {
        d = _d;
        mm = _mm;
        yyyy = _yyyy;
    }

    public int getDay() 
    {
        return d;
    }

    public int getMonth() 
    {
        return mm;
    }

    public int getYear() 
    {
        return yyyy;
    }

    public void setDay(int _d) 
    {
        d = _d;
    }

    public void setMonth(int _mm) 
    {
        mm = _mm;
    }

    public void setYear(int _yyyy) 
    {
        yyyy = _yyyy;
    }
    
    public String getFechaString()
    {
        return d + "/" + mm + "/" + yyyy;
    }

}