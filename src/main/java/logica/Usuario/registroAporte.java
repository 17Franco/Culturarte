
package logica.Usuario;

import logica.DTO.DTFecha;
import logica.Propuesta.Propuesta;

public class registroAporte {
    private int moto;
    private String retorno;
    private DTFecha fecha;
    private Propuesta colabPropuesta;
    private Colaborador colaborador;
 

    public registroAporte(int moto, String retorno, DTFecha fecha, Propuesta colabPropuesta,Colaborador colaborador) {
        this.moto = moto;
        this.retorno = retorno;
        this.fecha = fecha;
        this.colabPropuesta = colabPropuesta;
        this.colaborador=colaborador;
    }

    public void setMoto(int moto) {
        this.moto = moto;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public void setColabPropuesta(Propuesta colabPropuesta) {
        this.colabPropuesta = colabPropuesta;
    }

    public int getMoto() {
        return moto;
    }

    public String getRetorno() {
        return retorno;
    }

    public Propuesta getColabPropuesta() {
        return colabPropuesta;
    }

    public DTFecha getFecha() {
        return fecha;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }
    
    
}
