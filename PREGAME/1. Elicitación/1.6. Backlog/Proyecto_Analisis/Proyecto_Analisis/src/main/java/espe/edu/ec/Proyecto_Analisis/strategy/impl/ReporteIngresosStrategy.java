package espe.edu.ec.Proyecto_Analisis.strategy.impl;


import espe.edu.ec.Proyecto_Analisis.dto.MovimientoDTO;
import espe.edu.ec.Proyecto_Analisis.repository.DonacionRepository;
import espe.edu.ec.Proyecto_Analisis.strategy.ReporteStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component("reporteIngresos")
@RequiredArgsConstructor
public class ReporteIngresosStrategy implements ReporteStrategy {

    private final DonacionRepository donacionRepository;

    @Override
    public List<MovimientoDTO> generarReporte(LocalDate inicio, LocalDate fin) {
        return donacionRepository.findByFechaDonacionBetween(inicio, fin).stream()
                .map(donacion -> new MovimientoDTO(
                        donacion.getFechaDonacion(),
                        "Donante: " + donacion.getDonante() + " - " + donacion.getDescripcion(),
                        donacion.getMonto(),
                        "INGRESO"
                ))
                .collect(Collectors.toList());
    }
}