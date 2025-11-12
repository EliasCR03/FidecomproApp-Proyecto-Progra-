package fidecompro.main;

import fidecompro.gui.VentanaPrincipal;

public class FideComproApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
