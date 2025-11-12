package fidecompro.gui;

import fidecompro.modelo.Cliente;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import fidecompro.util.GestorDatos;
import java.nio.file.Paths;

public class VentanaClientes extends JFrame {
    private JTextField txtId, txtNombre, txtCorreo, txtTelefono;
    private static ArrayList<Cliente> clientes = GestorDatos.cargarClientes(Paths.get("clientes.dat").toAbsolutePath().toString());

    public VentanaClientes() {
        setTitle("Registro de Clientes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panel.add(txtCorreo);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        JButton btnGuardar = new JButton("Guardar Cliente");
        btnGuardar.addActionListener(e -> guardarCliente());
        panel.add(btnGuardar);

        add(panel);
    }

    private void guardarCliente() {
        Cliente c = new Cliente(
            txtId.getText(),
            txtNombre.getText(),
            txtCorreo.getText(),
            txtTelefono.getText()
        );
        clientes.add(c);
        GestorDatos.guardarClientes(clientes, Paths.get("clientes.dat").toAbsolutePath().toString());
        JOptionPane.showMessageDialog(this, "Cliente registrado correctamente ✅");
        txtId.setText(""); txtNombre.setText(""); txtCorreo.setText(""); txtTelefono.setText("");
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }
}
