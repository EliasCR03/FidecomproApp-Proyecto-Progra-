package fidecompro.gui;

import fidecompro.modelo.*;
import fidecompro.excepciones.StockInsuficienteException;
import javax.swing.*;
import java.awt.*;

public class VentanaFacturacion extends JFrame {
    private JComboBox<Cliente> cmbClientes;
    private JComboBox<Producto> cmbProductos;
    private JTextField txtCantidad;
    private JTextArea txtFactura;
    private Factura facturaActual;
    private Inventario inventario = new Inventario();

    public VentanaFacturacion() {
        setTitle("Generar Factura");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        for (Producto p : VentanaProductos.getProductos()) {
            inventario.agregarProducto(p);
        }

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Cliente:"));
        cmbClientes = new JComboBox<>(VentanaClientes.getClientes().toArray(new Cliente[0]));
        panel.add(cmbClientes);

        panel.add(new JLabel("Producto:"));
        cmbProductos = new JComboBox<>(VentanaProductos.getProductos().toArray(new Producto[0]));
        panel.add(cmbProductos);

        panel.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panel.add(txtCantidad);

        JButton btnAgregar = new JButton("Agregar a Factura");
        btnAgregar.addActionListener(e -> agregarDetalle());
        panel.add(btnAgregar);

        JButton btnMostrar = new JButton("Mostrar Factura");
        btnMostrar.addActionListener(e -> mostrarFactura());
        panel.add(btnMostrar);

        txtFactura = new JTextArea();
        txtFactura.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(txtFactura), BorderLayout.CENTER);

        facturaActual = new Factura("F" + System.currentTimeMillis(), (Cliente) cmbClientes.getSelectedItem());
    }

    private void agregarDetalle() {
        Producto p = (Producto) cmbProductos.getSelectedItem();
        int cantidad = Integer.parseInt(txtCantidad.getText());

        try {
            inventario.reducirStock(p.getCodigo(), cantidad);
            facturaActual.agregarDetalle(new DetalleFactura(p, cantidad));
            JOptionPane.showMessageDialog(this, "Producto agregado ✅");
        } catch (StockInsuficienteException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void mostrarFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura generada\n");
        sb.append("Cliente: ").append(((Cliente)cmbClientes.getSelectedItem()).getNombre()).append("\n\n");

        for (DetalleFactura d : facturaActual.getDetalles()) {
            sb.append(d.getProducto().getNombre())
              .append(" x").append(d.getCantidad())
              .append(" = ₡").append(d.getSubtotal()).append("\n");
        }

        sb.append("\nTOTAL: ₡").append(facturaActual.getTotal());
        txtFactura.setText(sb.toString());
    }
}
