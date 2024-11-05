package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.model.Auto;
import co.edu.uniquindio.poo.model.GestionAlquiler;
import co.edu.uniquindio.poo.model.Vehiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RegistroAutoViewController {
    public GestionAlquiler gestionAlquiler=new GestionAlquiler();
    ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();
    private Vehiculo AutoSeleccionado;

    @FXML
    public TextField txtMatricula;
    @FXML
    public TextField txtMarca;
    @FXML
    public TextField txtModelo;
    @FXML
    public TextField txtAnio;
    @FXML
    public TextField txtNumPuertas;

    
    @FXML
    public TableView<Vehiculo> tableAutos;
    @FXML
    public TableColumn<Vehiculo, String> tcMatricula;
    @FXML 
    public TableColumn<Vehiculo, String> tcMarca;
    @FXML 
    public TableColumn<Vehiculo, String> tcModelo;
    @FXML 
    public TableColumn<Vehiculo, String> tcAnio;
    @FXML 
    public TableColumn<Vehiculo, String> tcNumPuertas;


    @FXML
    public void initialize() {
        Auto auto2 = new Auto("ABC123", "Toyota", "Corolla", "2020", "4");
        Auto auto3 = new Auto("DEF456", "Nissan", "Sky", "1999", "4");
        gestionAlquiler.agregarVehiculo(auto2);
        gestionAlquiler.agregarVehiculo(auto3);
        tableAutos.getItems().clear();
        tableAutos.setItems(listaVehiculos);
        obtenerVehiculo();
        initDataBinding();
        listenerSelection();
        
    }

    

    private void obtenerVehiculo() {
        listaVehiculos.addAll(gestionAlquiler.obtenerVehiculo());
    }

    private void initDataBinding() {
        tcMatricula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatricula()));
        tcMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        tcModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        tcAnio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAnio()));
    
        tcNumPuertas.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Auto) {
                return new SimpleStringProperty(((Auto) cellData.getValue()).getNumeroPuertas());
            }
            return new SimpleStringProperty("");
        });
    }
    

    private void listenerSelection() {
        tableAutos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            AutoSeleccionado = newSelection;
            mostrarInformacionAuto((Auto) AutoSeleccionado);
        });
    }
    
    private void mostrarInformacionAuto(Auto auto) {
        if(auto != null){
            txtMatricula.setText(auto.getMatricula());
            txtMarca.setText(auto.getMarca());
            txtModelo.setText(auto.getModelo());
            txtAnio.setText(auto.getAnio());
            txtNumPuertas.setText(auto.getNumeroPuertas());
        }
    }
    public void AgregarAuto(ActionEvent actionEvent) {
        Auto auto = new Auto(txtMatricula.getText(), txtMarca.getText(), txtModelo.getText(), txtAnio.getText(), txtNumPuertas.getText());
        gestionAlquiler.agregarVehiculo(auto);
        listaVehiculos.add(auto);
        System.out.println("\n" + //
                        "Auto agregado");
        tableAutos.refresh();
        limpiar();
    }

//    public void EditarAuto(ActionEvent actionEvent) {
//        if (AutoSeleccionado != null) {
//            Auto auto = new Auto(txtMatricula.getText(), txtMarca.getText(), txtModelo.getText(), txtAnio.getText(), txtNumPuertas.getText());
//            boolean actualizado = gestionAlquiler.actualizarAuto(auto);
//            if (actualizado) {
//                listaVehiculos.remove(auto);
//                listaVehiculos.add(auto);
//                tableAutos.refresh();
//                limpiar();
//            }
//        }
//    }

    public void EliminarAuto(ActionEvent actionEvent){
        if(AutoSeleccionado != null){
            gestionAlquiler.eliminarVehiculo(AutoSeleccionado.getMatricula());
            listaVehiculos.remove(AutoSeleccionado);
            System.out.println("Auto eliminado1");
            tableAutos.refresh();
            limpiar();
        }
        else{
            System.out.println("Auto no toma");
        }
    }
    public void limpiar(){
        txtMatricula.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtAnio.setText("");
        txtNumPuertas.setText("");
    }

}
