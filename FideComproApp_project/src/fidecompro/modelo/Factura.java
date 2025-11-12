package fidecompro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Factura implements Serializable {
    private String numero;
    private Cliente cliente;
    private List<DetalleFactura> detalles;

    public Factura(String numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
    }

    public double getTotal() {
        return detalles.stream().mapToDouble(DetalleFactura::getSubtotal).sum();
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void imprimirFactura() {
        System.out.println("Factura N°: " + numero);
        System.out.println("Cliente: " + cliente.getNombre());
        for (DetalleFactura d : detalles) {
            System.out.println(d.getProducto().getNombre() + " x" + d.getCantidad() + " = ₡" + d.getSubtotal());
        }
        System.out.println("Total: ₡" + getTotal());
    }
}
