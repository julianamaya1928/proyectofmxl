package co.edu.uniquindio.poo.model;

public abstract class Vehiculo {
    public String matricula;
    public String marca;
    public String modelo;
    public String anio;

    public Vehiculo(String matricula, String marca, String modelo, String anio) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }
    public Vehiculo(){}

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public abstract double calcularCostoReserva(int dias);

    @Override
    public String toString() {
        return "Vehiculo [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + "]";
    }
}
