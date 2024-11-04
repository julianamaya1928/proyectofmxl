package co.edu.uniquindio.poo.model;


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
        System.out.printf("se creo el cliente",cliente.getNombre());
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
    public LinkedList<Cliente> obtenerCliente(){
        return listaClientes;
    }

    public boolean actualizarCliente(Cliente clienteActualizado) {
        Cliente clienteExistente = buscarCliente(clienteActualizado.getId());
        
        if (clienteExistente != null) {
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setDireccion(clienteActualizado.getDireccion());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            clienteExistente.setEmail(clienteActualizado.getEmail());
            System.out.printf("Cliente con cédula %s actualizado.\n", clienteActualizado.getId());
            return true;
        } else {
            System.out.printf("Cliente con cédula %s no encontrado.\n", clienteActualizado.getId());
            return false;
        }
    }
    
    public boolean eliminarCliente(String idCliente) {
        Cliente cliente = buscarCliente(idCliente);
        
        if (cliente != null) {
            listaClientes.remove(cliente);
            System.out.printf("Cliente con cédula %s eliminado.\n", idCliente);
            return true;
        } else {
            System.out.printf("Cliente con cédula %s no encontrado.\n", idCliente);
            return false;
        }
    }
}