package co.edu.uniquindio.poo.model;

import java.util.LinkedList;

public abstract class Vehiculo {
    public String matricula;
    public String marca;
    public String modelo;
    public int anio;

    public Vehiculo(String matricula, String marca, String modelo, int anio) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    public abstract double calcularCostoReserva(int dias);

    @Override
    public String toString() {
        return "Vehiculo [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + "]";
    }
}
