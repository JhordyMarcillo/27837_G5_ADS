package donaciones.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DonacionView extends JFrame {

    public JTextField txtDonante = new JTextField();
    public JTextField txtTipo = new JTextField();
    public JTextField txtCantidad = new JTextField();
    public JTextField txtValorUnitario = new JTextField();

    public JButton btnGuardar = new JButton("Guardar Donación");
    public JButton btnEliminar = new JButton("Eliminar Seleccionada");
    public JButton btnLimpiarTabla = new JButton("Limpiar Tabla");

    public DefaultTableModel tableModel;
    public JTable tableDonaciones;

    public DonacionView() {
        setTitle("Registro de Donaciones");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // FORMULARIO
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));

        panelForm.add(new JLabel("Donante:"));
        panelForm.add(txtDonante);

        panelForm.add(new JLabel("Tipo:"));
        panelForm.add(txtTipo);

        panelForm.add(new JLabel("Cantidad:"));
        panelForm.add(txtCantidad);

        panelForm.add(new JLabel("Valor Unitario:"));
        panelForm.add(txtValorUnitario);

        panelForm.add(new JLabel(""));
        panelForm.add(btnGuardar);

        add(panelForm, BorderLayout.NORTH);

        // TABLA
        String[] columnas = {"Donante", "Tipo", "Cantidad", "Valor Unitario", "Subtotal"};
        tableModel = new DefaultTableModel(columnas, 0);
        tableDonaciones = new JTable(tableModel);

        add(new JScrollPane(tableDonaciones), BorderLayout.CENTER);

        // PANEL DE BOTONES INFERIOR
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiarTabla);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }
}
