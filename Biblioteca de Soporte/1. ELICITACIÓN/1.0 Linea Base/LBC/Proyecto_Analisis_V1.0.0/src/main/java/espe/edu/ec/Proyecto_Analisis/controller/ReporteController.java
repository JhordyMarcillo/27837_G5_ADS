package espe.edu.ec.Proyecto_Analisis.controller;

import espe.edu.ec.Proyecto_Analisis.dto.MovimientoDTO;
import espe.edu.ec.Proyecto_Analisis.strategy.ReporteContext;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteContext reporteContext;

    @GetMapping
    public String mostrarReportes(
            @RequestParam(required = false) String tipoReporte,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            Model model
    ) {
        if (tipoReporte != null && fechaInicio != null && fechaFin != null) {

            List<MovimientoDTO> resultados = reporteContext.ejecutarReporte(tipoReporte, fechaInicio, fechaFin);

            model.addAttribute("resultados", resultados);

            model.addAttribute("tipoSeleccionado", tipoReporte);
            model.addAttribute("inicioSeleccionado", fechaInicio);
            model.addAttribute("finSeleccionado", fechaFin);
        }

        return "reportes";
    }
}