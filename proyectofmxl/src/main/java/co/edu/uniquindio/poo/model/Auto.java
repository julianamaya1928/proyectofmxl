package co.edu.uniquindio.poo.model;


public class Auto extends Vehiculo {
    private String numeroPuertas;
    private static final double TARIFA_BASE = 20.0;

    public Auto(String matricula, String marca, String modelo, String anio, String numeroPuertas) {
        super(matricula, marca, modelo, anio);
        this.numeroPuertas = numeroPuertas;
    }

    public String getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(String numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public String toString() {
        return "Auto [numeroPuertas=" + numeroPuertas + ", matricula=" + matricula + ", marca=" + marca + ", modelo="
                + modelo + ", anio=" + anio + "]";
    }

    @Override
    public double calcularCostoReserva(int dias) {
        return TARIFA_BASE * dias;
    }
}