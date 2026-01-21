package espe.edu.ec.Proyecto_Analisis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDTO {
    private LocalDate fecha;
    private String descripcion;
    private BigDecimal monto;
    private String tipo;
}