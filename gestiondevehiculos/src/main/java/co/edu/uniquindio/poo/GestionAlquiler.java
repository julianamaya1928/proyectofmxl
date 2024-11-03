package co.edu.uniquindio.poo;

import java.util.LinkedList;

public class GestionAlquiler {
    private LinkedList<Vehiculo> listaVehiculos = new LinkedList<>();
    private LinkedList<Cliente> listaClientes = new LinkedList<>();
    private LinkedList<Reserva> listaReservas = new LinkedList<>();

    public void agregarVehiculo(Vehiculo vehiculo) {
        listaVehiculos.add(vehiculo);
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void agregarReserva(String matricula, String idCliente, int dias) {
        Vehiculo vehiculo = buscarVehiculo(matricula);
        Cliente cliente = buscarCliente(idCliente);

        if (vehiculo != null && cliente != null) {
            Reserva reserva = new Reserva(vehiculo, cliente, dias);
            listaReservas.add(reserva);
            System.out.println("Reserva creada exitosamente: " + reserva);
        } else {
            System.out.println("Vehículo o cliente no encontrado.");
        }
    }

    public Vehiculo buscarVehiculo(String matricula) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.matricula.equals(matricula)) {
                return vehiculo;
            }
        }
        return null;
    }

    public Cliente buscarCliente(String idCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
    }
    
    public void mostrarReservas() {
        for (Reserva reserva : listaReservas) {
            System.out.println(reserva);
        }
    }
}
