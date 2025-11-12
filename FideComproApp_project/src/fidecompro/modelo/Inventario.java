package fidecompro.modelo;

import fidecompro.excepciones.StockInsuficienteException;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void reducirStock(String codigo, int cantidad) throws StockInsuficienteException {
        Producto p = buscarProducto(codigo);
        if (p == null) throw new StockInsuficienteException("Producto no encontrado");
        if (p.getStock() < cantidad)
            throw new StockInsuficienteException("Stock insuficiente para " + p.getNombre());
        p.reducirStock(cantidad);
    }

    public List<Producto> getProductos() { return productos; }
}
