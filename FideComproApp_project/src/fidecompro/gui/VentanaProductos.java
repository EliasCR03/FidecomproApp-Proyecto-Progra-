package fidecompro.gui;

import fidecompro.modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import fidecompro.util.GestorDatos;
import java.nio.file.Paths;

public class VentanaProductos extends JFrame {
    private JTextField txtCodigo, txtNombre, txtPrecio, txtStock, txtExtra;
    private JComboBox<String> cmbTipo;
    private static ArrayList<Producto> productos = GestorDatos.cargarProductos(Paths.get("productos.dat").toAbsolutePath().toString());

    public VentanaProductos() {
        setTitle("Registro de Productos");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));

        panel.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panel.add(txtCodigo);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panel.add(txtPrecio);

        panel.add(new JLabel("Stock:"));
        txtStock = new JTextField();
        panel.add(txtStock);

        panel.add(new JLabel("Tipo de producto:"));
        cmbTipo = new JComboBox<>(new String[]{"Alimento", "Electrónico"});
        panel.add(cmbTipo);

        panel.add(new JLabel("Dato adicional:"));
        txtExtra = new JTextField();
        panel.add(txtExtra);

        JButton btnGuardar = new JButton("Guardar Producto");
        btnGuardar.addActionListener(e -> guardarProducto());
        panel.add(btnGuardar);

        add(panel);
    }

    private void guardarProducto() {
    String codigo = txtCodigo.getText();
    String nombre = txtNombre.getText();
    String precioTxt = txtPrecio.getText();
    String stockTxt = txtStock.getText();
    String tipo = (String) cmbTipo.getSelectedItem();
    String extra = txtExtra.getText();

    if (codigo.isEmpty() || nombre.isEmpty() || precioTxt.isEmpty() || stockTxt.isEmpty() || extra.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        double precio = Double.parseDouble(precioTxt);
        int stock = Integer.parseInt(stockTxt);

        Producto p;
        if (tipo.equals("Alimento")) {
            p = new ProductoAlimento(codigo, nombre, precio, stock, extra);
        } else {
            try {
                int garantia = Integer.parseInt(extra);
                p = new ProductoElectronico(codigo, nombre, precio, stock, garantia);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La garantía debe ser un número (en meses).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        productos.add(p);
        JOptionPane.showMessageDialog(this, "Producto agregado correctamente ✅");

        // Limpiar campos
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtExtra.setText("");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El precio y el stock deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public static ArrayList<Producto> getProductos() {
        return productos;
    }
}
