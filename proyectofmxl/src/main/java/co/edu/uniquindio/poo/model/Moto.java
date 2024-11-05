package co.edu.uniquindio.poo.model;

public class Moto extends Vehiculo {
    private boolean esAutomatica;
    private static final double TARIFA_BASE = 15.0;
    private static final double TARIFA_EXTRA = 5.0;

    public Moto(String matricula, String marca, String modelo, String anio, boolean esAutomatica) {
        super(matricula, marca, modelo, anio);
        this.esAutomatica = esAutomatica;
    }

    public boolean isEsAutomatica() {
        return esAutomatica;
    }

    
    @Override
    public double calcularCostoReserva(int dias) {
        double costo = TARIFA_BASE * dias;
        if (esAutomatica) {
            costo += TARIFA_EXTRA;
        }
        return costo;
    }

    @Override
    public String toString() {
        return "Moto [esAutomatica=" + esAutomatica + ", matricula=" + matricula + ", marca=" + marca + ", modelo="
                + modelo + ", anio=" + anio + "]";
    }

    public void setEsAutomatica(boolean esAutomatica) {
        this.esAutomatica = esAutomatica;
    }
}


