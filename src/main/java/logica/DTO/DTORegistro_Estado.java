package logica.DTO;
import logica._enum.Estado;
import logica.Propuesta.Registro_Estado;

public class DTORegistro_Estado 
{
    private DTFecha fechaReg;
    private Estado estado;

    public DTORegistro_Estado() 
    {
    }

    public DTORegistro_Estado(DTFecha _fechaReg, Estado _estados)
    {
        fechaReg = _fechaReg;
        estado = _estados;
    }
    
    public DTFecha getFechaReg() 
    {
        if(fechaReg == null)    //No debería pasar
        {
            DTFecha a = new DTFecha(30,12,9999);
            return a;    //Parche temporal
        }
        
        return fechaReg;
    }

    public Estado getEstado() 
    {
        if(estado == null)  //No debería pasar, parche para debug sólo
        {
            return Estado.CANCELADA;    //Parche temporal
        }
        
        return estado;
    }

    public void setFechaReg(DTFecha _fechaReg) 
    {
        fechaReg = _fechaReg;
    }

    public void setEstado(Estado _estados) 
    {
        estado = _estados;
    }   
    
    public void extraerDatos(Registro_Estado i)
    {
        estado = i.getEstado();
        fechaReg = i.getFechaReg();
    }
    
    public void DTOextraerDatos(DTORegistro_Estado i)
    {
        estado = i.getEstado();
        fechaReg = i.getFechaReg();
    }
    
    public String getEstadoString() //Me sirve más asi para mostrar
    {
        if (estado == null) //No debería pasar
        {

            return Estado.CANCELADA.toString();    //Parche temporal
        }
        
        return estado.toString();
    }
    
    public String getFechaRegString() //Lo mismo
    {
        if (fechaReg == null) //No debería pasar
        {
            
            return "31/12/9999 (ERROR)";    //Parche temporal
        }
        return fechaReg.getDay() + "/" + fechaReg.getMonth() + "/"+ fechaReg.getYear();
    }
    
}
