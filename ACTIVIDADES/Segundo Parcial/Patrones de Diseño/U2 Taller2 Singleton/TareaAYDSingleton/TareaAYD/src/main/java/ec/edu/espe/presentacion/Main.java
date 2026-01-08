package main.java.ec.edu.espe.presentacion;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EstudianteUI ui = new EstudianteUI();
            ui.setVisible(true);
        });
    }
}
