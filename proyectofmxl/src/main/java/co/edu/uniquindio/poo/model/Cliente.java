package co.edu.uniquindio.poo.model;

public class Cliente {
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String id;

    public Cliente(String nombre, String direccion, String telefono, String email, String id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.id=id;
    }

    public Cliente(){}

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email
                + ", id=" + id + "";
    }

    
}
