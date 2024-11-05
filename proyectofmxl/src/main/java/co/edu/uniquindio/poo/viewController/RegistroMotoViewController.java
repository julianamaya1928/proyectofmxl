package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.model.Moto;
import co.edu.uniquindio.poo.model.Camioneta;
import co.edu.uniquindio.poo.model.GestionAlquiler;
import co.edu.uniquindio.poo.model.Vehiculo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RegistroMotoViewController {
    
    public GestionAlquiler gestionAlquiler=new GestionAlquiler();
    ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();
    private Vehiculo MotoSeleccionado;

    @FXML
    public TextField txtMatricula;
    @FXML
    public TextField txtMarca;
    @FXML
    public TextField txtModelo;
    @FXML
    public TextField txtAnio;
    @FXML
    public CheckBox txtAutomatica;

    
    @FXML
    public TableView<Vehiculo> tableMoto;
    @FXML
    public TableColumn<Vehiculo, String> tcMatricula;
    @FXML 
    public TableColumn<Vehiculo, String> tcMarca;
    @FXML 
    public TableColumn<Vehiculo, String> tcModelo;
    @FXML 
    public TableColumn<Vehiculo, String> tcAnio;
    @FXML 
    public TableColumn<Vehiculo, String> tcAutomatica;


    @FXML
    public void initialize() {
        Moto moto2 = new Moto("XYZ78F", "Honda", "CB500", "2022", true);
        Moto moto3 = new Moto("TAY48G", "Suziki", "Dr650", "2024", false);
        gestionAlquiler.agregarVehiculo(moto2);
        gestionAlquiler.agregarVehiculo(moto3);
        tableMoto.getItems().clear();
        tableMoto.setItems(listaVehiculos);
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
        
        tcAutomatica.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Moto) {
                boolean esAutomatica = ((Moto) cellData.getValue()).isEsAutomatica();
                return new SimpleStringProperty(esAutomatica ? "Sí" : "No");
            }
            return new SimpleStringProperty("");
        });
    }
        
    

    private void listenerSelection() {
        tableMoto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            MotoSeleccionado = newSelection;
            mostrarInformacionMoto((Moto) MotoSeleccionado);
        });
    }
    private void mostrarInformacionMoto(Moto moto) {
        if (moto != null) {
            txtMatricula.setText(moto.getMatricula());
            txtMarca.setText(moto.getMarca());
            txtModelo.setText(moto.getModelo());
            txtAnio.setText(moto.getAnio());
            txtAutomatica.setSelected(moto.isEsAutomatica()); 
        }
    }
    
    public void AgregarMoto(ActionEvent actionEvent) {
        Moto moto = new Moto(txtMatricula.getText(), txtMarca.getText(), txtModelo.getText(), txtAnio.getText(), txtAutomatica.isSelected());
        gestionAlquiler.agregarVehiculo(moto);
        listaVehiculos.add(moto);
        tableMoto.refresh();
        limpiar();
    }
    

    public void EliminarMoto(ActionEvent actionEvent){
        if(MotoSeleccionado != null){
            gestionAlquiler.eliminarVehiculo(MotoSeleccionado.getMatricula());
            listaVehiculos.remove(MotoSeleccionado);
            tableMoto.refresh();
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
        txtAutomatica.setSelected(false);
    }

}
