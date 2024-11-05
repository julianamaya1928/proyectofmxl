package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.model.Moto;
import co.edu.uniquindio.poo.model.Reserva;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uniquindio.poo.model.Auto;
import co.edu.uniquindio.poo.model.Camioneta;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.GestionAlquiler;
import co.edu.uniquindio.poo.model.Vehiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class AgregarReservaViewController {
    public GestionAlquiler gestionAlquiler=new GestionAlquiler();
    ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();
    ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    Cliente clientes = new Cliente();
    ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
    private Reserva reservaSeleccionada;


    @FXML
    public DatePicker txtFechaIni;
    @FXML
    public DatePicker txtFechaFin;
    @FXML
    public ComboBox SelecClientes;
    @FXML
    public ComboBox SelectVehiculo;
    @FXML
    public TextField LabelCosto;

    @FXML
    public TableView<Reserva>TableReserva;
    @FXML
    public TableColumn<Reserva, String> TcMatriculaReserva;
    @FXML 
    public TableColumn<Reserva, String> TcClienteReserva;
    @FXML 
    public TableColumn<Reserva, String> TcCosto;
    @FXML 
    public TableColumn<Reserva, String> TcFechaIni;
    @FXML 
    public TableColumn<Reserva, String> TcTcFechaFin;

    @FXML
    public void initialize() {
        Cliente cliente1 = new Cliente("Juan Perez", "Calle 123", "123456789", "juan@example.com", "C001");
        Cliente cliente2 = new Cliente("Maria Gomez", "Avenida 456", "987654321", "maria@example.com", "C002");
        gestionAlquiler.agregarCliente(cliente1);
        gestionAlquiler.agregarCliente(cliente2);
        Moto moto2 = new Moto("XYZ78F", "Honda", "CB500", "2022", true);
        Moto moto3 = new Moto("TAY48G", "Suziki", "Dr650", "2024", false);
        gestionAlquiler.agregarVehiculo(moto2);
        gestionAlquiler.agregarVehiculo(moto3);
        Auto auto2 = new Auto("ABC123", "Toyota", "Corolla", "2020", "4");
        Auto auto3 = new Auto("DEF456", "Nissan", "Sky", "1999", "4");
        gestionAlquiler.agregarVehiculo(auto2);
        gestionAlquiler.agregarVehiculo(auto3);

        CargarClientes();
        CargarVehiculos();
        TableReserva.getItems().clear();
        TableReserva.setItems(listaReservas);
        obtenerReserva();
        initDataBinding();
        listenerSelection();
        
        
    }

    

    private void obtenerReserva() {
        listaReservas.addAll(gestionAlquiler.obtenerReservas());
    }

    private void initDataBinding() {
        TcFechaIni.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        TcTcFechaFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaFin().toString()));
        TcCosto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCosto())));
        TcMatriculaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehiculo().matricula));
        TcClienteReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNombre()));

    }

    private void listenerSelection() {
        TableReserva.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reservaSeleccionada = newSelection;
            mostrarInformacionReserva(reservaSeleccionada);
        });
    }
    private void mostrarInformacionReserva(Reserva reserva) {
        if(reserva != null){
            SelectVehiculo.setValue(reserva.getVehiculo().getMatricula());
            SelecClientes.setValue(reserva.getCliente().getNombre());
            txtFechaIni.setValue(reserva.getFechaInicio());
            txtFechaFin.setValue(reserva.getFechaFin());
            double costo = reserva.getCosto();
            LabelCosto.setText(String.valueOf(costo));
        }
    }

    public void CargarClientes(){
        List<Cliente> clientes = gestionAlquiler.obtenerCliente();
        ObservableList<String> nombresClientes = FXCollections.observableArrayList(
            clientes.stream().map(Cliente::getNombre).collect(Collectors.toList())
        );
        SelecClientes.setItems(nombresClientes);
    }

    
    public void CargarVehiculos(){
        List<Vehiculo> vehiculos = gestionAlquiler.obtenerVehiculo();
        ObservableList<String> nombresVehiculos = FXCollections.observableArrayList(
            vehiculos.stream().map(Vehiculo::getMatricula).collect(Collectors.toList())
        );
        SelectVehiculo.setItems(nombresVehiculos);
    }
    public int calcularDiasEntreFechas() {
        if (txtFechaIni.getValue() != null && txtFechaFin.getValue() != null) {
            LocalDate fechaInicio = txtFechaIni.getValue();
            LocalDate fechaFin = txtFechaFin.getValue();
            return (int) (fechaFin.toEpochDay() - fechaInicio.toEpochDay());
        }
        return 0;
    }
    public void AgregarReserva(ActionEvent actionEvent ){
        int diasReserva = calcularDiasEntreFechas();
        String nombreClienteSeleccionado = (String) SelecClientes.getValue(); 

        Cliente clienteSeleccionado = gestionAlquiler.obtenerCliente().stream()
            .filter(cliente -> cliente.getNombre().equals(nombreClienteSeleccionado))
            .findFirst().orElse(null);
        double costoReserva = Double.parseDouble(LabelCosto.getText());
        gestionAlquiler.agregarReserva(SelectVehiculo.getValue().toString(), clienteSeleccionado.getId(),diasReserva,txtFechaIni.getValue(), txtFechaFin.getValue(),costoReserva );
        listaReservas.setAll(gestionAlquiler.obtenerReservas());
        TableReserva.refresh();
        limpiar();
    }

    public void AgregarActualizar(ActionEvent actionEvent) {
        if (reservaSeleccionada != null) {
            String matricula = reservaSeleccionada.getVehiculo().getMatricula();
            LocalDate fechaInicio = txtFechaIni.getValue();
            LocalDate fechaFin = txtFechaFin.getValue();
            int dias = calcularDiasEntreFechas();
            double costo = Double.parseDouble(LabelCosto.getText());
    
            boolean actualizada = gestionAlquiler.actualizarReserva(matricula, fechaInicio, fechaFin, dias, costo);
            
            if (actualizada) {
                System.out.println("Reserva actualizada correctamente.");
                listaReservas.setAll(gestionAlquiler.obtenerReservas());
                TableReserva.refresh();
                limpiar();
            } else {
                System.out.println("Error: no se pudo actualizar la reserva.");
            }
        } else {
            System.out.println("Seleccione una reserva para actualizar.");
        }
    }
    
    public void EliminarReserva(ActionEvent actionEvent) {
        if (reservaSeleccionada != null) {
            String matricula = reservaSeleccionada.getVehiculo().getMatricula();
            LocalDate fechaInicio = reservaSeleccionada.getFechaInicio();
            LocalDate fechaFin = reservaSeleccionada.getFechaFin();
    
            boolean eliminada = gestionAlquiler.eliminarReserva(matricula, fechaInicio, fechaFin);
            
            if (eliminada) {
                System.out.println("Reserva eliminada correctamente.");
                listaReservas.setAll(gestionAlquiler.obtenerReservas());
                TableReserva.refresh();
                limpiar();
            } else {
                System.out.println("Error: no se pudo eliminar la reserva.");
            }
        } else {
            System.out.println("Seleccione una reserva para eliminar.");
        }
    }
        
    public void CalcularCosto(ActionEvent actionEvent) {
        int diasReserva = calcularDiasEntreFechas();
        String matriculaSeleccionada = (String) SelectVehiculo.getValue(); 
    
        Vehiculo vehiculoSeleccionado = gestionAlquiler.obtenerVehiculo().stream()
            .filter(vehiculo -> vehiculo.getMatricula().equals(matriculaSeleccionada))
            .findFirst()
            .orElse(null);
    
        if (vehiculoSeleccionado != null) {
            double costo = vehiculoSeleccionado.calcularCostoReserva(diasReserva);
            LabelCosto.setText(String.valueOf(costo));
        } 
    }

    public void limpiar(){
        txtFechaIni.setValue(null);
        txtFechaFin.setValue(null);
        SelecClientes.getSelectionModel().clearSelection();
        SelectVehiculo.getSelectionModel().clearSelection();
        LabelCosto.setText("");
    }
}
