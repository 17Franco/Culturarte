package logica.DTO;
import logica.DTFecha.DTFecha;
import logica.DTO.DTOPropuesta;

public class DTORegistro_Aporte
{
    private int monto;
    private String retornoEsperado;
    private DTFecha fechaDeAporte;
    private DTOPropuesta propuestaFinanciada;

    public DTORegistro_Aporte()
    {
        //Agrego algo por que me da error el compilador jaja
    }

    public DTORegistro_Aporte(int _monto, String _retornoEsperado, DTFecha _fechaDeAporte, DTOPropuesta _propuestaFinanciada)
    {
        monto = _monto;
        retornoEsperado = _retornoEsperado;
        fechaDeAporte = _fechaDeAporte;
        propuestaFinanciada = _propuestaFinanciada;
    }

    public int getMonto() 
    {
        return monto;
    }

    public String getRetornoEsperado() 
    {
        return retornoEsperado;
    }

    public DTFecha getFechaDeAporte() 
    {
        return fechaDeAporte;
    }

    public DTOPropuesta getPropuestaFinanciada() 
    {
        return propuestaFinanciada;
    }

    public void setMonto(int _monto) 
    {
        monto = _monto;
    }

    public void setRetornoEsperado(String _retornoEsperado) 
    {
        retornoEsperado = _retornoEsperado;
    }

    public void setFechaDeAporte(DTFecha _fechaDeAporte) 
    {
        fechaDeAporte = _fechaDeAporte;
    }

    public void setPropuestaFinanciada(DTOPropuesta _propuestaFinanciada) 
    {
        propuestaFinanciada = _propuestaFinanciada;
    }

}
