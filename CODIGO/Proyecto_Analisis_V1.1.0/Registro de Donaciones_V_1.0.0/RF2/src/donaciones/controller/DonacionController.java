package donaciones.controller;

import donaciones.model.Donacion;
import donaciones.service.DonacionService;
import donaciones.view.DonacionView;

import javax.swing.*;

public class DonacionController {

    private DonacionView view;
    private DonacionService service;

    public DonacionController(DonacionView view, DonacionService service) {
        this.view = view;
        this.service = service;

        iniciarControl();
    }

    private void iniciarControl() {
        view.btnGuardar.addActionListener(e -> guardar());
        view.btnEliminar.addActionListener(e -> eliminarDonacion());
        view.btnLimpiarTabla.addActionListener(e -> limpiarTabla());
    }

    private void guardar() {
        try {
            String donante = view.txtDonante.getText();
            String tipo = view.txtTipo.getText();
            int cantidad = Integer.parseInt(view.txtCantidad.getText());
            double valorUnitario = Double.parseDouble(view.txtValorUnitario.getText());

            if (donante.isEmpty() || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Todos los campos son obligatorios");
                return;
            }

            Donacion d = new Donacion(donante, tipo, cantidad, valorUnitario);
            service.guardarDonacion(d);

            Object[] fila = {
                    d.getDonante(),
                    d.getTipo(),
                    d.getCantidad(),
                    d.getValorUnitario(),
                    d.getSubtotal()
            };

            view.tableModel.addRow(fila);

            // Limpiar campos
            view.txtDonante.setText("");
            view.txtTipo.setText("");
            view.txtCantidad.setText("");
            view.txtValorUnitario.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Cantidad y Valor Unitario deben ser números.");
        }
    }

    private void eliminarDonacion() {
        int fila = view.tableDonaciones.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "Selecciona una fila para eliminar.");
            return;
        }

        view.tableModel.removeRow(fila);
        service.obtenerDonaciones().remove(fila);

        JOptionPane.showMessageDialog(view, "Donación eliminada.");
    }

    private void limpiarTabla() {
        view.tableModel.setRowCount(0);
        service.obtenerDonaciones().clear();
        JOptionPane.showMessageDialog(view, "Tabla limpiada.");
    }
}
