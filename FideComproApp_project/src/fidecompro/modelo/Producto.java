package fidecompro.modelo;

import java.io.Serializable;

public abstract class Producto implements Serializable {
    protected String codigo;
    protected String nombre;
    protected double precio;
    protected int stock;

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void reducirStock(int cantidad) { stock -= cantidad; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return nombre + " - ₡" + precio;
    }
}
