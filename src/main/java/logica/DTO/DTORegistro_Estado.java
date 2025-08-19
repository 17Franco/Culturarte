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
        return fechaReg;
    }

    public Estado getEstado() 
    {
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
    
}
