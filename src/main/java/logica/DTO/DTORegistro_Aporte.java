package logica.DTO;
import logica.DTO.DTOPropuesta;

public class DTORegistro_Aporte
{
    private int monto;
    private String retornoEsperado;
    private DTFecha fechaDeAporte;
    private String propuestaFinanciada; // guardo solo el titulo
    private String colaborador; // guardo solo el nick 

    public DTORegistro_Aporte()
    {
        //Agrego algo por que me da error el compilador jaja
    }

    public DTORegistro_Aporte(int _monto, String _retornoEsperado, DTFecha _fechaDeAporte, String _propuestaFinanciada, String _colaborador)
    {
        monto = _monto;
        retornoEsperado = _retornoEsperado;
        fechaDeAporte = _fechaDeAporte;
        propuestaFinanciada = _propuestaFinanciada;
        colaborador=_colaborador;
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

    public String getPropuestaFinanciada() {
        return propuestaFinanciada;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setPropuestaFinanciada(String propuestaFinanciada) {
        this.propuestaFinanciada = propuestaFinanciada;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    

    
    
}
