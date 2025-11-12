package fidecompro.modelo;

public class ProductoElectronico extends Producto {
    private int garantiaMeses;

    public ProductoElectronico(String codigo, String nombre, double precio, int stock, int garantiaMeses) {
        super(codigo, nombre, precio, stock);
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipo() { return "Electrónico"; }

    public int getGarantiaMeses() { return garantiaMeses; }
}
