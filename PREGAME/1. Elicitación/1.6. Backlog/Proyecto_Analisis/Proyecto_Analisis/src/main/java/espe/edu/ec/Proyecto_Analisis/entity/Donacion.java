package espe.edu.ec.Proyecto_Analisis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "donaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campos originales
    private String donante;
    private BigDecimal monto;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaDonacion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String tipoDonacion; // Valores: "EFECTIVO" o "ESPECIE"

    private String numeroDocumento;
    private String ruc;
    private String cuentaBanco;

    // Auditoría
    private LocalDate fechaRegistro = LocalDate.now();
}