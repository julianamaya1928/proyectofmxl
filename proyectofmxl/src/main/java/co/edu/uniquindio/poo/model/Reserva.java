package co.edu.uniquindio.poo.model;

public class Reserva {
    private Vehiculo vehiculo;
    private Cliente cliente;
    private int dias;

    public Reserva(Vehiculo vehiculo, Cliente cliente, int dias) {
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.dias = dias;
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
