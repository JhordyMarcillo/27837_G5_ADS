package espe.edu.ec.Proyecto_Analisis.strategy;

import espe.edu.ec.Proyecto_Analisis.dto.MovimientoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReporteContext {

    private final Map<String, ReporteStrategy> strategies;

    public List<MovimientoDTO> ejecutarReporte(String tipoReporte, LocalDate inicio, LocalDate fin) {
        ReporteStrategy strategy = strategies.get(tipoReporte);

        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de reporte no válido: " + tipoReporte);
        }

        return strategy.generarReporte(inicio, fin);
    }
}