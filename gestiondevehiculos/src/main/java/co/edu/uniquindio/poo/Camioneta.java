package co.edu.uniquindio.poo;

import java.util.LinkedList;

public class Camioneta extends Vehiculo {
    private double capacidadCarga;
    private static final double TARIFA_BASE = 30.0;
    private static final double PORCENTAJE_EXTRA = 0.1;

    public Camioneta(String matricula, String marca, String modelo, int anio, double capacidadCarga) {
        super(matricula, marca, modelo, anio);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public double calcularCostoReserva(int dias) {
        return TARIFA_BASE * dias * (1 + PORCENTAJE_EXTRA * capacidadCarga);
    }

    @Override
    public String toString() {
        return "Camioneta [matricula=" + matricula + ", capacidadCarga=" + capacidadCarga + ", marca=" + marca
                + ", modelo=" + modelo + ", anio=" + anio + "]";
    }
}

