
package logica.DTO;

import logica.Colaboracion.Colaboracion;
import logica.Usuario.Colaborador;
import logica._enum.TipoRetorno;


public class DTOColaboracion {
      private TipoRetorno tipoRetorno;

        private int monto;

        private String colaborador;

        private String propuesta;

        private DTFecha creado;
    
    
    public boolean equals(DTOColaboracion obj) {
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

    public DTOColaboracion(TipoRetorno tipoRetorno, int monto, String colaborador, String propuesta, DTFecha creado) {
        this.tipoRetorno = tipoRetorno;
        this.monto = monto;
        this.colaborador = colaborador;
        this.propuesta = propuesta;
        this.creado = creado;
    }
    
     public DTOColaboracion(Colaboracion colaboracion){
         tipoRetorno=colaboracion.getTipoRetorno();
         monto=colaboracion.getMonto();
         creado=colaboracion.getCreado();
         colaborador=colaboracion.getColaborador().getNickname();
         propuesta=colaboracion.getPropuesta().getTitulo();
     }

     
    public DTFecha getCreado() {
        return creado;
    }

    
    public void setCreado(DTFecha creado) {
        this.creado = creado;
    }


    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }


   
    public String getColaborador() {
        return colaborador;
    }

    
    public void setColaborador(String  colaborador) {
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
