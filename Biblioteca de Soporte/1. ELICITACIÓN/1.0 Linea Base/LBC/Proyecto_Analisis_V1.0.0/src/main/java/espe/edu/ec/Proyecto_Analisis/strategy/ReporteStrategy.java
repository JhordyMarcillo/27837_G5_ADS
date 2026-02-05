package espe.edu.ec.Proyecto_Analisis.strategy;

import espe.edu.ec.Proyecto_Analisis.dto.MovimientoDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReporteStrategy {
    List<MovimientoDTO> generarReporte(LocalDate inicio, LocalDate fin);
}