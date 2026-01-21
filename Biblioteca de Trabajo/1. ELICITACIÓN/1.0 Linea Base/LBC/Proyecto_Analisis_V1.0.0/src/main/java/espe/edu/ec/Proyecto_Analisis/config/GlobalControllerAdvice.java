package espe.edu.ec.Proyecto_Analisis.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    // Esto hace que la variable 'currentUri' esté disponible en TODOS los HTML automáticamente
    @ModelAttribute("currentUri")
    public String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
}