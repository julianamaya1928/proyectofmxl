package co.edu.uniquindio.poo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        GestionAlquiler gestion = new GestionAlquiler();

        Auto auto = new Auto("ABC123", "Toyota", "Corolla", 2020, 4);
        Moto moto = new Moto("XYZ78F", "Honda", "CB500", 2022, true);
        Camioneta camioneta = new Camioneta("LMN456", "Ford", "Ranger", 2021, 1.5);

        gestion.agregarVehiculo(auto);
        gestion.agregarVehiculo(moto);
        gestion.agregarVehiculo(camioneta);

        System.out.println("Costo reserva Auto: " + gestion.calcularReservaVehiculo("ABC123", 3));
        System.out.println("Costo reserva Moto: " + gestion.calcularReservaVehiculo("XYZ789", 3));
        System.out.println("Costo reserva Camioneta: " + gestion.calcularReservaVehiculo("LMN456", 3));

    }
}