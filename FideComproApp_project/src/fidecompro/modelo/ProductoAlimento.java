package fidecompro.modelo;

public class ProductoAlimento extends Producto {
    private String fechaExpiracion;

    public ProductoAlimento(String codigo, String nombre, double precio, int stock, String fechaExpiracion) {
        super(codigo, nombre, precio, stock);
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String getTipo() { return "Alimento"; }

    public String getFechaExpiracion() { return fechaExpiracion; }
}
