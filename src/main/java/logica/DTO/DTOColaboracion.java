package logica.DTO;

import logica._enum.TipoRetorno;


public class DTOColaboracion {
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

    public DTOColaboracion(TipoRetorno tipoRetorno, int monto, DTOColaborador colaborador, DTOPropuesta propuesta, DTFecha creado) {
        this.tipoRetorno = tipoRetorno;
        this.monto = monto;
        this.colaborador = colaborador;
        this.propuesta = propuesta;
        this.creado = creado;
    }
    
    private TipoRetorno tipoRetorno;
    
    private int monto;
    
    private DTOColaborador colaborador;
    
    private DTOPropuesta propuesta;
    
    private DTFecha creado;

    /**
     * Get the value of creado
     *
     * @return the value of creado
     */
    public DTFecha getCreado() {
        return creado;
    }

    /**
     * Set the value of creado
     *
     * @param creado new value of creado
     */
    public void setCreado(DTFecha creado) {
        this.creado = creado;
    }


    /**
     * Get the value of propuesta
     *
     * @return the value of propuesta
     */
    public DTOPropuesta getPropuesta() {
        return propuesta;
    }

    /**
     * Set the value of propuesta
     *
     * @param propuesta new value of propuesta
     */
    public void setPropuesta(DTOPropuesta propuesta) {
        this.propuesta = propuesta;
    }


    /**
     * Get the value of colaborador
     *
     * @return the value of colaborador
     */
    public DTOColaborador getColaborador() {
        return colaborador;
    }

    /**
     * Set the value of colaborador
     *
     * @param colaborador new value of colaborador
     */
    public void setColaborador(DTOColaborador colaborador) {
        this.colaborador = colaborador;
    }


    /**
     * Get the value of monto
     *
     * @return the value of monto
     */
    public int getMonto() {
        return monto;
    }

    /**
     * Set the value of monto
     *
     * @param monto new value of monto
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }


    /**
     * Get the value of tipoRetorno
     *
     * @return the value of tipoRetorno
     */
    public TipoRetorno getTipoRetorno() {
        return tipoRetorno;
    }

    /**
     * Set the value of tipoRetorno
     *
     * @param tipoRetorno new value of tipoRetorno
     */
    public void setTipoRetorno(TipoRetorno tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

}