import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class RegistroEgreso {
    private TipoEgreso tipo;
    private String nombreBanco;
    private String cuentaBanco;
    private String fecha;
    private String nombreBeneficiario;
    private String rucCedula;
    private String telefono;
    private Double cantidad;
    private File comprobante;

    // Constructor
    private RegistroEgreso(EgresoBuilder builder) {
        this.tipo = builder.tipo;
        this.nombreBanco = builder.nombreBanco;
        this.cuentaBanco = builder.cuentaBanco;
        this.fecha = builder.fecha;
        this.nombreBeneficiario = builder.nombreBeneficiario;
        this.rucCedula = builder.rucCedula;
        this.telefono = builder.telefono;
        this.cantidad = builder.cantidad;
        this.comprobante = builder.comprobante;
    }

    @Override
    public String toString() {
        return "REGISTRO VALIDO:\n" +
                "Pago a: " + nombreBeneficiario + "\n" +
                "RUC/CI: " + rucCedula + "\n" +
                "Monto: $" + cantidad + "\n" +
                "Banco: " + nombreBanco + " (" + cuentaBanco + ")";
    }

    public static class EgresoBuilder {
        private TipoEgreso tipo;
        private String nombreBanco;
        private String cuentaBanco;
        private String fecha;
        private String nombreBeneficiario;
        private String rucCedula;
        private String telefono;
        private Double cantidad;
        private File comprobante;

        public EgresoBuilder tipo(TipoEgreso tipo) { this.tipo = tipo; return this; }

        public EgresoBuilder banco(String nombre, String cuenta) {
            this.nombreBanco = nombre;
            this.cuentaBanco = cuenta;
            return this;
        }

        public EgresoBuilder fecha(String fecha) { this.fecha = fecha; return this; }

        public EgresoBuilder beneficiario(String nombre, String ruc, String telf) {
            this.nombreBeneficiario = nombre;
            this.rucCedula = ruc;
            this.telefono = telf;
            return this;
        }

        public EgresoBuilder monto(Double cantidad) { this.cantidad = cantidad; return this; }

        public EgresoBuilder archivo(File archivo) { this.comprobante = archivo; return this; }


        public RegistroEgreso build() {
            if (cuentaBanco == null || !cuentaBanco.matches("\\d{10,11}")) {
                throw new IllegalStateException("Error: La cuenta bancaria debe tener entre 10 y 11 dígitos numéricos.");
            }

            if(comprobante == null) {
                throw new IllegalStateException("Error: debe de subir un comprobante (pdf o imagen)");
            }

            validarFecha(fecha);

            if (nombreBeneficiario == null || !nombreBeneficiario.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$")) {
                throw new IllegalStateException("Error: El nombre debe contener solo letras.");
            }

            if (!esIdentificacionValida(rucCedula)) {
                throw new IllegalStateException("Error: La Cédula o RUC ingresado no es válido");
            }

            if (telefono == null || !telefono.matches("^0\\d{9}$")) {
                throw new IllegalStateException("Error: El teléfono debe tener 10 dígitos.");
            }

            if (cantidad == null || cantidad <= 0) {
                throw new IllegalStateException("Error: La cantidad debe ser mayor a 0.");
            }

            return new RegistroEgreso(this);
        }
        
        private void validarFecha(String fechaStr) {
            try {
                LocalDate fechaInput = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
                if (fechaInput.isAfter(LocalDate.now())) {
                    throw new IllegalStateException("Error: La fecha no puede ser futura.");
                }
            } catch (DateTimeParseException e) {
                throw new IllegalStateException("Error: Formato de fecha inválido. Use AAAA-MM-DD.");
            }
        }

        private boolean esIdentificacionValida(String id) {
            if (id == null) return false;

            if (!id.matches("\\d{10}|\\d{13}")) return false;

            String baseCedula = id.substring(0, 10);

            int provincia = Integer.parseInt(baseCedula.substring(0, 2));
            int digitoTres = Integer.parseInt(baseCedula.substring(2, 3));

            if ((provincia < 1 || provincia > 24) && provincia != 30) return false;
            if (digitoTres >= 6) return false;

            int total = 0;
            int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};

            for (int i = 0; i < coeficientes.length; i++) {
                int valor = Character.getNumericValue(baseCedula.charAt(i)) * coeficientes[i];
                total += (valor >= 10) ? valor - 9 : valor;
            }

            int digitoVerificador = Character.getNumericValue(baseCedula.charAt(9));
            int decenaSuperior = (total % 10 == 0) ? total : (total / 10 + 1) * 10;
            int calculado = decenaSuperior - total;

            if (calculado != digitoVerificador) return false;

            if (id.length() == 13 && !id.endsWith("001")) return false;

            return true;
        }
    }
}