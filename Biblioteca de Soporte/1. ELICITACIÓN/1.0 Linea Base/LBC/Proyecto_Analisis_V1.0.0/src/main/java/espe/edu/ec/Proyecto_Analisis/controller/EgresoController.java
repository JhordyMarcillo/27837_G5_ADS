package espe.edu.ec.Proyecto_Analisis.controller;

import espe.edu.ec.Proyecto_Analisis.entity.Egreso;
import espe.edu.ec.Proyecto_Analisis.repository.EgresoRepository;
import espe.edu.ec.Proyecto_Analisis.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/egresos")
@RequiredArgsConstructor
public class EgresoController {

    private final TransaccionService transaccionService;

    @GetMapping
    public String mostrarPaginaEgresos(Model model) {
        model.addAttribute("egreso", new Egreso());
        model.addAttribute("listaEgresos", transaccionService.obtenerTodosEgresos());
        return "egreso";
    }

    @PostMapping
    public String guardarEgreso(@ModelAttribute Egreso egreso) {
        transaccionService.guardarEgreso(egreso);
        return "redirect:/egresos";
    }
}