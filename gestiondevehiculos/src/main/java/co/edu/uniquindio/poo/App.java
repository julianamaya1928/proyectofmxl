package co.edu.uniquindio.poo;

public class App {
    public static void main(String[] args) {
        GestionAlquiler gestion = new GestionAlquiler();

        Auto auto = new Auto("ABC123", "Toyota", "Corolla", 2020, 4);
        Moto moto = new Moto("XYZ78F", "Honda", "CB500", 2022, false);
        Camioneta camioneta = new Camioneta("LMN456", "Ford", "Ranger", 2021, 1.5);

        gestion.agregarVehiculo(auto);
        gestion.agregarVehiculo(moto);
        gestion.agregarVehiculo(camioneta);

        Cliente cliente1 = new Cliente("Juan Perez", "Calle 123", "123456789", "juan@example.com", "C001");
        Cliente cliente2 = new Cliente("Maria Gomez", "Avenida 456", "987654321", "maria@example.com", "C002");

        gestion.agregarCliente(cliente1);
        gestion.agregarCliente(cliente2);

        gestion.agregarReserva("ABC123", "C001", 3);
        gestion.agregarReserva("XYZ78F", "C002", 5);

        System.out.println("Reservas");
        gestion.mostrarReservas();
    }
}
