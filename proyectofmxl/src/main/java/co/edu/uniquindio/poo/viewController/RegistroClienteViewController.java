package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.GestionAlquiler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class RegistroClienteViewController {
    public GestionAlquiler gestionAlquiler=new GestionAlquiler();
    ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private Cliente clienteSeleccionado;

    @FXML
    public TextField txtCedula;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtTelefono;
    @FXML
    public TextField txtCorreo;
    @FXML
    public TextField txtDireccion;

    @FXML
    public TableView<Cliente> tableClientes;
    @FXML
    public TableColumn<Cliente, String> tcCedula;
    @FXML
    public TableColumn<Cliente, String> tcNombre;
    @FXML
    public TableColumn<Cliente, String> tcTelefono;
    @FXML
    public TableColumn<Cliente, String> tcCorreo;
    @FXML
    public TableColumn<Cliente, String> tDireccion;

    @FXML
    public void initialize() {
        Cliente cliente1 = new Cliente("Juan Perez", "Calle 123", "123456789", "juan@example.com", "C001");
        Cliente cliente2 = new Cliente("Maria Gomez", "Avenida 456", "987654321", "maria@example.com", "C002");

        gestionAlquiler.agregarCliente(cliente1);
        gestionAlquiler.agregarCliente(cliente2);

        tableClientes.getItems().clear();
        tableClientes.setItems(listaClientes);
        obtenerCliente();
        initDataBinding();
        listenerSelection();
        
    }

    

    private void obtenerCliente() {
        listaClientes.addAll(gestionAlquiler.obtenerCliente());
    }

    private void initDataBinding() {
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
    }

    private void listenerSelection() {
        tableClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clienteSeleccionado = newSelection;
            mostrarInformacionCliente(clienteSeleccionado);
        });
    }
    private void mostrarInformacionCliente(Cliente cliente) {
        if(cliente != null){
            txtNombre.setText(cliente.getNombre());
            txtCedula.setText(cliente.getId());
            txtCorreo.setText(cliente.getEmail());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
        }
    }

    public void AgregarClientes(ActionEvent actionEvent){
        Cliente cliente = new Cliente(txtNombre.getText(),txtDireccion.getText(),txtTelefono.getText(),txtCorreo.getText(),txtCedula.getText());
        gestionAlquiler.agregarCliente(cliente);
        listaClientes.add(cliente);
        tableClientes.refresh();
        limpiar();

    }

    public void EditarClientes(ActionEvent actionEvent){
        if(clienteSeleccionado != null){
            Cliente cliente= new Cliente(txtNombre.getText(),txtDireccion.getText(),txtTelefono.getText(),txtCorreo.getText(),txtCedula.getText());
            boolean actualizar= gestionAlquiler.actualizarCliente(cliente);
            if(actualizar){
                listaClientes.remove(clienteSeleccionado);
                listaClientes.add(cliente);
                tableClientes.refresh();
                limpiar();
            }
        }
    }

    public void EliminarClientes(ActionEvent actionEvent){
        if(clienteSeleccionado != null){
            gestionAlquiler.eliminarCliente(clienteSeleccionado.getId());
            listaClientes.remove(clienteSeleccionado);
            tableClientes.refresh();
            limpiar();
        }
    }

    public void limpiar(){
        txtNombre.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}
