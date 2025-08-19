package logica.Propuesta;
import logica.DTO.DTFecha;
import logica._enum.Estado;


/**
 *
 * @author klaas
 */
public class Registro_Estado {

    private DTFecha fechaReg;
    private Estado estado = Estado.INGRESADA;

    public Registro_Estado() 
    {
    }

    public Registro_Estado(DTFecha _fechaReg, Estado _estado)
    {
        fechaReg = _fechaReg;
        estado = _estado;

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

    public void setEstados(Estado _estado) 
    {
        estado = _estado;
    }

}
