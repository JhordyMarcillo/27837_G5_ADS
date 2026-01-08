package main.java.ec.edu.espe.presentacion;

import main.java.ec.edu.espe.datos.model.Estudiante;
import main.java.ec.edu.espe.logica_negocio.EstudianteService.ResultadoOperacion;
import main.java.ec.edu.espe.presentacion.EstudianteController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class EstudianteUI extends JFrame {
    private final EstudianteController controller;

    private JTextField tfId;
    private JTextField tfNombres;
    private JTextField tfEdad;

    private JButton btnGuardar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private JTable table;
    private DefaultTableModel tableModel;

    public EstudianteUI() {
        controller = new EstudianteController();
        initComponents();
        cargarTabla();
    }

    private void initComponents() {
        setTitle("CRUD Estudiantes - 3 capas + MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID:");
        JLabel lblNombres = new JLabel("Nombres:");
        JLabel lblEdad = new JLabel("Edad:");

        tfId = new JTextField(12);
        tfNombres = new JTextField(25);
        tfEdad = new JTextField(5);

        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        // Panel formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblId, gbc);
        gbc.gridx = 1; formPanel.add(tfId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblNombres, gbc);
        gbc.gridx = 1; formPanel.add(tfNombres, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lblEdad, gbc);
        gbc.gridx = 1; formPanel.add(tfEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(btnGuardar, gbc);
        gbc.gridx = 1; formPanel.add(btnEditar, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(btnEliminar, gbc);
        gbc.gridx = 1; formPanel.add(btnLimpiar, gbc);

        // Tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombres", "Edad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout principal
        getContentPane().setLayout(new BorderLayout(8,8));
        getContentPane().add(formPanel, BorderLayout.WEST);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Listeners
        btnGuardar.addActionListener(e -> guardar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        tfId.setText((String) tableModel.getValueAt(row, 0));
                        tfNombres.setText((String) tableModel.getValueAt(row, 1));
                        tfEdad.setText(String.valueOf(tableModel.getValueAt(row, 2)));
                        tfId.setEditable(false); // ID no editable en edición
                    }
                }
            }
        });
    }

    private void cargarTabla() {
        tableModel.setRowCount(0);
        for (Estudiante s : controller.obtenerTodosLosEstudiantes()) {
            tableModel.addRow(new Object[]{s.getId(), s.getNombres(), s.getEdad()});
        }
    }

    private void guardar() {
        String id = tfId.getText();
        String nombres = tfNombres.getText();
        String edad = tfEdad.getText();

        ResultadoOperacion res = controller.guardarEstudiante(id, nombres, edad);
        if (res.isSuccess()) {
            showInfo(res.getMensaje());
            limpiarFormulario();
            cargarTabla();
        } else {
            showError(res.getMensaje());
        }
    }

    private void editar() {
        String id = tfId.getText();
        String nombres = tfNombres.getText();
        String edad = tfEdad.getText();

        ResultadoOperacion res = controller.editarEstudiante(id, nombres, edad);
        if (res.isSuccess()) {
            showInfo(res.getMensaje());
            limpiarFormulario();
            cargarTabla();
            tfId.setEditable(true);
        } else {
            showError(res.getMensaje());
        }
    }

    private void eliminar() {
        String id = tfId.getText().trim();
        if (id.isEmpty()) {
            showError("Ingrese ID a eliminar.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar estudiante con ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        ResultadoOperacion res = controller.eliminarEstudiante(id);
        if (res.isSuccess()) {
            showInfo(res.getMensaje());
            limpiarFormulario();
            cargarTabla();
            tfId.setEditable(true);
        } else {
            showError(res.getMensaje());
        }
    }

    private void limpiarFormulario() {
        tfId.setText("");
        tfNombres.setText("");
        tfEdad.setText("");
        tfId.setEditable(true);
        table.clearSelection();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EstudianteUI ui = new EstudianteUI();
            ui.setVisible(true);
        });
    }
}
