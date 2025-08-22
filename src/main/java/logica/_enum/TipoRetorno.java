package logica._enum;

public enum TipoRetorno
{   
    EntradaGratis("Entrada Gratis"),
    PorcentajeGanancia("Porcentaje de Ganancia");

    private final String descripcion;

    TipoRetorno(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion; // esto se verá en el JComboBox
    }
}
