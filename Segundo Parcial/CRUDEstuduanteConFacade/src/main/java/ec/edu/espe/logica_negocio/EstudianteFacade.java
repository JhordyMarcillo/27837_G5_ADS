package main.java.ec.edu.espe.logica_negocio;

import main.java.ec.edu.espe.datos.model.Estudiante;
import main.java.ec.edu.espe.datos.repository.EstudianteRepository;
import java.util.List;
import java.util.Optional;



public class EstudianteFacade {
    private final EstudianteRepository repo;
    private final EstudianteService service;

    public EstudianteFacade() {
        // La Facade se encarga de inicializar el subsistema
        this.repo = new EstudianteRepository();
        this.service = new EstudianteService(repo);
    }

    public EstudianteService.ResultadoOperacion agregarEstudiante(String id, String nombres, String edadStr) {
        try {
            int edad = Integer.parseInt(edadStr.trim());
            Estudiante e = new Estudiante(id.trim(), nombres.trim(), edad);
            return service.agregar(e);
        } catch (NumberFormatException ex) {
            return EstudianteService.ResultadoOperacion.error("Edad inválida. Debe ser un número entero.");
        }
    }

    public EstudianteService.ResultadoOperacion editarEstudiante(String id, String nombres, String edadStr) {
        try {
            int edad = Integer.parseInt(edadStr.trim());
            Estudiante e = new Estudiante(id.trim(), nombres.trim(), edad);
            return service.editar(e);
        } catch (NumberFormatException ex) {
            return EstudianteService.ResultadoOperacion.error("Edad inválida. Debe ser un número entero.");
        }
    }

    public EstudianteService.ResultadoOperacion eliminarEstudiante(String id) {
        return service.eliminar(id.trim());
    }

    public List<Estudiante> listarEstudiantes() {
        return service.listar();
    }
}
