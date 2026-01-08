package main.java.ec.edu.espe.logica_negocio;

import main.java.ec.edu.espe.datos.model.Estudiante;
import main.java.ec.edu.espe.datos.repository.EstudianteRepository;
import java.util.List;
import java.util.Optional;

public class EstudianteService {
    private final EstudianteRepository repo;

    public EstudianteService() {
        this.repo = EstudianteRepository.getInstance();
    }

    public ResultadoOperacion agregar(Estudiante e) {
        if (e == null) return ResultadoOperacion.error("Estudiante nulo.");
        if (e.getId() == null || e.getId().trim().isEmpty()) return ResultadoOperacion.error("ID vacío.");
        if (e.getNombres() == null || e.getNombres().trim().isEmpty()) return ResultadoOperacion.error("Nombres vacíos.");
        if (e.getEdad() <= 0) return ResultadoOperacion.error("Edad debe ser mayor a 0.");
        if (repo.findById(e.getId()).isPresent()) return ResultadoOperacion.error("ID ya registrado.");

        boolean ok = repo.agregar(e);
        return ok ? ResultadoOperacion.ok("Estudiante agregado.") : ResultadoOperacion.error("No se pudo agregar.");
    }

    public ResultadoOperacion editar(Estudiante e) {
        if (e == null) return ResultadoOperacion.error("Estudiante nulo.");
        if (e.getId() == null || e.getId().trim().isEmpty()) return ResultadoOperacion.error("ID vacío.");
        if (e.getNombres() == null || e.getNombres().trim().isEmpty()) return ResultadoOperacion.error("Nombres vacíos.");
        if (e.getEdad() <= 0) return ResultadoOperacion.error("Edad debe ser mayor a 0.");
        if (!repo.findById(e.getId()).isPresent()) return ResultadoOperacion.error("ID no existe.");

        boolean ok = repo.editar(e);
        return ok ? ResultadoOperacion.ok("Estudiante editado.") : ResultadoOperacion.error("No se pudo editar.");
    }

    public ResultadoOperacion eliminar(String id) {
        if (id == null || id.trim().isEmpty()) return ResultadoOperacion.error("ID vacío.");
        if (!repo.findById(id).isPresent()) return ResultadoOperacion.error("ID no existe.");

        boolean ok = repo.eliminar(id);
        return ok ? ResultadoOperacion.ok("Estudiante eliminado.") : ResultadoOperacion.error("No se pudo eliminar.");
    }

    public List<Estudiante> listar() {
        return repo.listar();
    }

    public Optional<Estudiante> buscarPorId(String id) {
        return repo.findById(id);
    }

    // Clase anidada simple para resultados (mensaje + éxito)
    public static class ResultadoOperacion {
        private final boolean success;
        private final String mensaje;

        private ResultadoOperacion(boolean success, String mensaje) {
            this.success = success;
            this.mensaje = mensaje;
        }

        public static ResultadoOperacion ok(String mensaje) {
            return new ResultadoOperacion(true, mensaje);
        }

        public static ResultadoOperacion error(String mensaje) {
            return new ResultadoOperacion(false, mensaje);
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
