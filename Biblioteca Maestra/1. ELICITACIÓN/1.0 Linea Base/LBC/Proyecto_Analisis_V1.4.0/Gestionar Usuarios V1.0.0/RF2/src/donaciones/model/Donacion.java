package donaciones.model;

public class Donacion {

    private String donante;
    private String tipo;
    private int cantidad;
    private double valorUnitario;
    private double subtotal;
    private double total;

    public Donacion(String donante, String tipo, int cantidad, double valorUnitario) {
        this.donante = donante;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.subtotal = cantidad * valorUnitario;
        this.total = this.subtotal;
    }

    // Getters
    public String getDonante() { return donante; }
    public String getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public double getValorUnitario() { return valorUnitario; }
    public double getSubtotal() { return subtotal; }
    public double getTotal() { return total; }
}
