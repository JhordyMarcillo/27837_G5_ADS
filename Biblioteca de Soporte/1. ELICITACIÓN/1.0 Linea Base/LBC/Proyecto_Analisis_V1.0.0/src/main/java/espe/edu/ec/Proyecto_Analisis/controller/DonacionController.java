package espe.edu.ec.Proyecto_Analisis.controller;

import espe.edu.ec.Proyecto_Analisis.entity.Donacion;
import espe.edu.ec.Proyecto_Analisis.repository.DonacionRepository;
import espe.edu.ec.Proyecto_Analisis.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/donaciones")
@RequiredArgsConstructor
public class DonacionController {

    private final TransaccionService transaccionService;

    @GetMapping
    public String mostrarPaginaDonaciones(Model model) {

        model.addAttribute("listaDonaciones", transaccionService.obtenerTodasDonaciones());
        model.addAttribute("donacion", new Donacion());

        return "donacion";
    }

    @PostMapping
    public String guardarDonacion(@ModelAttribute Donacion donacion) {
        if (donacion.getFechaDonacion() == null) {
            donacion.setFechaDonacion(LocalDate.now());
        }

        transaccionService.guardarDonacion(donacion);
        return "redirect:/donaciones";
    }
}