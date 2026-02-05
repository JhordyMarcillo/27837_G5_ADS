import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;


public class SistemaEgresos extends JFrame {

    // Componentes de la UI
    private JRadioButton rbProyectos, rbPresupuesto, rbRol, rbDecimos;
    private JTextField txtBanco, txtCuenta, txtFecha;
    private JTextField txtBeneficiario, txtRuc, txtTelefono, txtCantidad;
    private JButton btnSubirArchivo, btnRegistrar, btnAtras;
    private JLabel lblArchivoSeleccionado;
    private ButtonGroup grupoTipo;
    private File archivoSeleccionado;

    public SistemaEgresos() {
        setTitle("Registro de Egreso - RF3");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblTitulo = new JLabel("Registro de Egreso", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(1, 2, 40, 0)); // 1 Fila, 2 Columnas
        formPanel.setBackground(Color.WHITE);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        leftPanel.add(crearLabel("* Tipo de Egreso"));
        JPanel radioPanel = new JPanel(new GridLayout(2, 2));
        radioPanel.setBackground(Color.WHITE);
        rbProyectos = new JRadioButton("Proyectos");
        rbPresupuesto = new JRadioButton("Presupuesto");
        rbRol = new JRadioButton("Rol de Pago");
        rbDecimos = new JRadioButton("Décimos");

        grupoTipo = new ButtonGroup();
        grupoTipo.add(rbProyectos); grupoTipo.add(rbPresupuesto);
        grupoTipo.add(rbRol); grupoTipo.add(rbDecimos);

        radioPanel.add(rbProyectos); radioPanel.add(rbRol);
        radioPanel.add(rbPresupuesto); radioPanel.add(rbDecimos);
        leftPanel.add(radioPanel);
        leftPanel.add(Box.createVerticalStrut(15));

        // Campos Banco
        leftPanel.add(crearLabel("* Nombre del Banco"));
        txtBanco = crearInput();
        leftPanel.add(txtBanco);

        leftPanel.add(crearLabel("* Cuenta de banco"));
        txtCuenta = crearInput();
        leftPanel.add(txtCuenta);

        leftPanel.add(crearLabel("* Fecha (dd/mm/aaaa)"));
        txtFecha = crearInput();
        txtFecha.setText(LocalDate.now().toString());
        leftPanel.add(txtFecha);

        leftPanel.add(crearLabel("* Comprobante de Pago"));
        btnSubirArchivo = new JButton("Seleccionar archivo (Upload)");
        lblArchivoSeleccionado = new JLabel("Ningún archivo seleccionado");
        lblArchivoSeleccionado.setFont(new Font("Arial", Font.ITALIC, 10));
        leftPanel.add(btnSubirArchivo);
        leftPanel.add(lblArchivoSeleccionado);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(new TitledBorder("Beneficiario"));

        rightPanel.add(crearLabel("* Nombres y Apellidos"));
        txtBeneficiario = crearInput();
        rightPanel.add(txtBeneficiario);

        rightPanel.add(crearLabel("* Número de RUC/Cédula"));
        txtRuc = crearInput();
        rightPanel.add(txtRuc);

        rightPanel.add(crearLabel("* Número de teléfono"));
        txtTelefono = crearInput();
        rightPanel.add(txtTelefono);

        rightPanel.add(crearLabel("* Cantidad ($)"));
        txtCantidad = crearInput();
        rightPanel.add(txtCantidad);

        formPanel.add(leftPanel);
        formPanel.add(rightPanel);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        btnRegistrar = new JButton("Registrar egreso");
        btnRegistrar.setBackground(Color.DARK_GRAY);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setPreferredSize(new Dimension(150, 40));

        btnAtras = new JButton("Atrás");
        btnAtras.setBackground(Color.RED);
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setPreferredSize(new Dimension(150, 40));

        buttonPanel.add(btnRegistrar);
        buttonPanel.add(btnAtras);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnSubirArchivo.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                archivoSeleccionado = fileChooser.getSelectedFile();
                lblArchivoSeleccionado.setText(archivoSeleccionado.getName());
                lblArchivoSeleccionado.setForeground(new Color(0, 128, 0));
            }
        });

        btnRegistrar.addActionListener(e -> {
            try {
                TipoEgreso tipoSel = null;
                if (rbProyectos.isSelected()) tipoSel = TipoEgreso.PROYECTOS;
                else if (rbPresupuesto.isSelected()) tipoSel = TipoEgreso.PRESUPUESTO;
                else if (rbRol.isSelected()) tipoSel = TipoEgreso.ROL_DE_PAGO_MENSUAL;
                else if (rbDecimos.isSelected()) tipoSel = TipoEgreso.DECIMOS;

                Double monto = 0.0;
                try {
                    monto = Double.parseDouble(txtCantidad.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido.");
                    return;
                }

                RegistroEgreso egreso = new RegistroEgreso.EgresoBuilder()
                        .tipo(tipoSel)
                        .banco(txtBanco.getText(), txtCuenta.getText())
                        .fecha(txtFecha.getText())
                        .beneficiario(txtBeneficiario.getText(), txtRuc.getText(), txtTelefono.getText())
                        .monto(monto)
                        .archivo(archivoSeleccionado)
                        .build();

                JOptionPane.showMessageDialog(this, egreso.toString());
                limpiarFormulario();

            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Faltan Datos", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAtras.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Desea cancelar y limpiar el formulario?", "Atrás", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                limpiarFormulario();
            }
        });
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBorder(new EmptyBorder(10, 0, 5, 0));
        return label;
    }

    private JTextField crearInput() {
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(100, 30));
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return input;
    }

    private void limpiarFormulario() {
        grupoTipo.clearSelection();
        txtBanco.setText("");
        txtCuenta.setText("");
        txtBeneficiario.setText("");
        txtRuc.setText("");
        txtTelefono.setText("");
        txtCantidad.setText("");
        lblArchivoSeleccionado.setText("Ningún archivo seleccionado");
        archivoSeleccionado = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemaEgresos().setVisible(true);
        });
    }
}