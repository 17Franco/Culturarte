package logica.DTO;
import logica.DTFecha.DTFecha;
import logica._enum.Estado;

import java.util.HashSet;
import java.util.Set;

public class DTORegistro_Estado 
{
    private DTFecha fechaReg;
    private Estado estados;
    private Set<DTORegistro_Estado> historial;

    public DTORegistro_Estado() 
    {
        historial = new HashSet<>();
    }

    public DTORegistro_Estado(DTFecha _fechaReg, Estado _estados)
    {
        fechaReg = _fechaReg;
        estados = _estados;
        historial = new HashSet<>();
    }

    public DTORegistro_Estado(DTFecha _fechaReg, Estado _estados, Set<DTORegistro_Estado> _historial) 
    {
        fechaReg = _fechaReg;
        estados = _estados;

            if (_historial != null)
            {
                historial = _historial;
            }

            if (_historial == null)
            {
                historial = new HashSet<>();

            }
    }
  
    public DTFecha getFechaReg() 
    {
        return fechaReg;
    }

    public Estado getEstados() 
    {
        return estados;
    }

    public Set<DTORegistro_Estado> getHistorial() 
    {
        return historial;
    }

    public void setFechaReg(DTFecha _fechaReg) 
    {
        fechaReg = _fechaReg;
    }

    public void setEstados(Estado _estados) 
    {
        estados = _estados;
    }

    public void setHistorial(Set<DTORegistro_Estado> _historial) 
    {
        historial = _historial;
    }
}
