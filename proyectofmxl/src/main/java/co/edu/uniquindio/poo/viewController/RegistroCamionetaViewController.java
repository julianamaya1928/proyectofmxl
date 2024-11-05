package co.edu.uniquindio.poo.viewController;


import co.edu.uniquindio.poo.model.Camioneta;
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


public class RegistroCamionetaViewController {

    public GestionAlquiler gestionAlquiler=new GestionAlquiler();
    ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();
    private Vehiculo CamionetaSeleccionado;

    @FXML
    public TextField txtMatricula;
    @FXML
    public TextField txtMarca;
    @FXML
    public TextField txtModelo;
    @FXML
    public TextField txtAnio;
    @FXML
    public TextField txtCapacidadCarga;

    
    @FXML
    public TableView<Vehiculo> tableCamioneta;
    @FXML
    public TableColumn<Vehiculo, String> tcMatricula;
    @FXML 
    public TableColumn<Vehiculo, String> tcMarca;
    @FXML 
    public TableColumn<Vehiculo, String> tcModelo;
    @FXML 
    public TableColumn<Vehiculo, String> tcAnio;
    @FXML 
    public TableColumn<Vehiculo, String> tcCapacidadCarga;


    @FXML
    public void initialize() {
        Camioneta camioneta1 = new Camioneta("LMN456", "Ford", "Ranger", "2021", 1.5);
        Camioneta camioneta2 = new Camioneta("HJP678", "Toyota", "Hilux", "2025", 2.1);
        gestionAlquiler.agregarVehiculo(camioneta1);
        gestionAlquiler.agregarVehiculo(camioneta2);
        tableCamioneta.getItems().clear();
        tableCamioneta.setItems(listaVehiculos);
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
    
        tcCapacidadCarga.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Camioneta) {
                return new SimpleStringProperty(String.valueOf(((Camioneta) cellData.getValue()).getCapacidadCarga()));
            }
            return new SimpleStringProperty("");
        });
    }
    
    

    private void listenerSelection() {
        tableCamioneta.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            CamionetaSeleccionado = newSelection;
            mostrarInformacionCamioneta((Camioneta) CamionetaSeleccionado);
        });
    }
    private void mostrarInformacionCamioneta(Camioneta camioneta) {
        if(camioneta != null){
            txtMatricula.setText(camioneta.getMatricula());
            txtMarca.setText(camioneta.getMarca());
            txtModelo.setText(camioneta.getModelo());
            txtAnio.setText(camioneta.getAnio());
            txtCapacidadCarga.setText(String.valueOf(camioneta.getCapacidadCarga())); 
        }
    }
    public void AgregarCamioneta(ActionEvent actionEvent) {
        double capacidadCarga = Double.parseDouble(txtCapacidadCarga.getText()); 
        Camioneta camioneta = new Camioneta(txtMatricula.getText(), txtMarca.getText(), txtModelo.getText(), txtAnio.getText(), capacidadCarga);
        gestionAlquiler.agregarVehiculo(camioneta);
        listaVehiculos.add(camioneta);
        tableCamioneta.refresh();
        limpiar();
    }
    

    public void EliminarCamioneta(ActionEvent actionEvent){
        if(CamionetaSeleccionado != null){
            gestionAlquiler.eliminarVehiculo(CamionetaSeleccionado.getMatricula());
            listaVehiculos.remove(CamionetaSeleccionado);
            tableCamioneta.refresh();
            System.out.println("Camioneta eliminado2");
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
        txtCapacidadCarga.setText("");
    }

}


