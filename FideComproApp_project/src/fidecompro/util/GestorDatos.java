package fidecompro.util;

import fidecompro.modelo.Cliente;
import fidecompro.modelo.Producto;
import java.io.*;
import java.util.ArrayList;

public class GestorDatos {

    public static void guardarClientes(ArrayList<Cliente> clientes, String ruta) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(clientes);
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }

    public static ArrayList<Cliente> cargarClientes(String ruta) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            return (ArrayList<Cliente>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void guardarProductos(ArrayList<Producto> productos, String ruta) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(productos);
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }

    public static ArrayList<Producto> cargarProductos(String ruta) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            return (ArrayList<Producto>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
