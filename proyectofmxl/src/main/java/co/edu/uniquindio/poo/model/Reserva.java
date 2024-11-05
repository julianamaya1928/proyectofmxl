package co.edu.uniquindio.poo.model;

import java.time.LocalDate;

public class Reserva {
    private Vehiculo vehiculo;
    private Cliente cliente;
    private int dias;
    private LocalDate fechaInicio;
    private LocalDate FechaFin;
    private double costo;


    public Reserva(Vehiculo vehiculo, Cliente cliente, int dias,LocalDate fechaInicio, LocalDate fechaFin, Double costo) {
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.dias = dias;
        this.fechaInicio = fechaInicio;
        this.FechaFin = fechaFin;
        this.costo = costo;

    }
    

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }


    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public LocalDate getFechaFin() {
        return FechaFin;
    }


    public void setFechaFin(LocalDate fechaFin) {
        FechaFin = fechaFin;
    }


    public double getCosto() {
        return costo;
    }


    public void setCosto(Double costo) {
        this.costo = costo;
    }


    public double calcularCosto() {
        return vehiculo.calcularCostoReserva(dias);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getDias() {
        return dias;
    }

    @Override
    public String toString() {
        return "Reserva [vehiculo=" + vehiculo + ", cliente=" + cliente + ", dias=" + dias + ", costo=" + calcularCosto() + "]";
    }
}
