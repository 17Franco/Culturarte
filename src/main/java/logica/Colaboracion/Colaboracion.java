package logica.Colaboracion;

import logica.DTO.DTFecha;
import logica.DTO.DTOColaboracion;
import logica.Propuesta.Propuesta;
import logica.Usuario.Colaborador;
import logica._enum.TipoRetorno;


public class Colaboracion {
    
        private TipoRetorno tipoRetorno;

        private int monto;

        private Colaborador colaborador;

        private Propuesta propuesta;

        private DTFecha creado;
    
    
    public boolean equals(Colaboracion obj) {
        if (this.monto != obj.monto) {
            return false;
        }
        if (this.tipoRetorno != obj.tipoRetorno) {
            return false;
        }
        if (!this.colaborador.equals(obj.colaborador)) {
            return false;
        }
        if (this.propuesta.equals(obj.propuesta) ) {
            return false;
        }
        return this.creado.equals(obj.creado);
    }

     public Colaboracion(TipoRetorno tipoRetorno, int monto, Colaborador colaborador, Propuesta propuesta, DTFecha creado) {
        this.tipoRetorno = tipoRetorno;
        this.monto = monto;
        this.colaborador = colaborador;
        this.propuesta = propuesta;
        this.creado = creado;
    }
     public Colaboracion(DTOColaboracion colaboracion,Colaborador c, Propuesta p){
         tipoRetorno=colaboracion.getTipoRetorno();
         monto=colaboracion.getMonto();
         creado=colaboracion.getCreado();
         colaborador=c;
         propuesta=p;
     }
     
    public DTFecha getCreado() {
        return creado;
    }

    
    public void setCreado(DTFecha creado) {
        this.creado = creado;
    }


    public Propuesta getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(Propuesta propuesta) {
        this.propuesta = propuesta;
    }


   
    public Colaborador getColaborador() {
        return colaborador;
    }

    
    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }


    public int getMonto() {
        return monto;
    }

  
    public void setMonto(int monto) {
        this.monto = monto;
    }


    public TipoRetorno getTipoRetorno() {
        return tipoRetorno;
    }

    
    public void setTipoRetorno(TipoRetorno tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

}