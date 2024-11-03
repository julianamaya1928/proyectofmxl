package co.edu.uniquindio.poo;

import java.util.LinkedList;

public class GestionAlquiler {
    private LinkedList<Vehiculo> listaVehiculos = new LinkedList<>();

    public void agregarVehiculo(Vehiculo vehiculo) {
        listaVehiculos.add(vehiculo);
    }

    public double calcularReservaVehiculo(String matricula, int dias) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.matricula.equals(matricula)) {
                return vehiculo.calcularCostoReserva(dias);
            }
        }
        System.out.println("el vehiculo no ha sido registrado.");
        return 0;
    }
} 
