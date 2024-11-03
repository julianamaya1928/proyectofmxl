package co.edu.uniquindio.poo;

public class Auto extends Vehiculo {
    private int numeroPuertas;
    private static final double TARIFA_BASE = 20.0;

    public Auto(String matricula, String marca, String modelo, int anio, int numeroPuertas) {
        super(matricula, marca, modelo, anio);
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public double calcularCostoReserva(int dias) {
        return TARIFA_BASE * dias;
    }
}