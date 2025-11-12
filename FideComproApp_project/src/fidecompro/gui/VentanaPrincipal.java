package fidecompro.gui;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Sistema de Facturación - FideCompro");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnClientes = new JButton("Clientes");
        JButton btnProductos = new JButton("Productos");
        JButton btnFacturas = new JButton("Facturación");

        btnClientes.addActionListener(e -> new VentanaClientes().setVisible(true));
        btnProductos.addActionListener(e -> new VentanaProductos().setVisible(true));
        btnFacturas.addActionListener(e -> new VentanaFacturacion().setVisible(true));

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnClientes);
        add(btnProductos);
        add(btnFacturas);
    }
}
