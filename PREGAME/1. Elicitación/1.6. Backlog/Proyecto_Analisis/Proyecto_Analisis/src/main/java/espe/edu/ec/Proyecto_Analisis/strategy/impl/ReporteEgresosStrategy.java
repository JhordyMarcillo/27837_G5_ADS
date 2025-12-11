package espe.edu.ec.Proyecto_Analisis.strategy.impl;


import espe.edu.ec.Proyecto_Analisis.dto.MovimientoDTO;
import espe.edu.ec.Proyecto_Analisis.repository.EgresoRepository;
import espe.edu.ec.Proyecto_Analisis.strategy.ReporteStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component("reporteEgresos") // Nombre clave
@RequiredArgsConstructor
public class ReporteEgresosStrategy implements ReporteStrategy {

    private final EgresoRepository egresoRepository;

    @Override
    public List<MovimientoDTO> generarReporte(LocalDate inicio, LocalDate fin) {
        return egresoRepository.findByFechaEgresoBetween(inicio, fin).stream()
                .map(egreso -> new MovimientoDTO(
                        egreso.getFechaEgreso(),
                        "Beneficiario: " + egreso.getBeneficiario() + " - " + egreso.getDescripcion(),
                        egreso.getMonto(),
                        "EGRESO"
                ))
                .collect(Collectors.toList());
    }
}