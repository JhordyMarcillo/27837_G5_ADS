package main.java.ec.edu.espe.presentacion;

import main.java.ec.edu.espe.datos.model.Estudiante;
import main.java.ec.edu.espe.datos.model.EstudianteFactory;
import main.java.ec.edu.espe.logica_negocio.EstudianteService;
import main.java.ec.edu.espe.logica_negocio.EstudianteService.ResultadoOperacion;
import java.util.List;

/**
 * Controlador que maneja las acciones del usuario y coordina entre la Vista y la capa de Negocio.
 * Implementa el patrón NVC (Negocio-Vista-Control).
 * Este controlador utiliza el ServicioEstudiante que a su vez usa el Singleton EstudianteRepository.
 */
public class EstudianteController {
    private final EstudianteService service;

    public EstudianteController() {
        this.service = new EstudianteService();
    }

    /**
     * Guarda un nuevo estudiante validando los datos de entrada.
     */
    public ResultadoOperacion guardarEstudiante(String id, String nombres, String edadStr) {
        try {
            int edad = Integer.parseInt(edadStr.trim());

            // USANDO FACTORY
            Estudiante e = EstudianteFactory.crear(id.trim(), nombres.trim(), edad);

            return service.agregar(e);
        } catch (NumberFormatException ex) {
            return ResultadoOperacion.error("Edad inválida. Debe ser un número entero.");
        }
    }

    /**
     * Edita un estudiante existente validando los datos de entrada.
     */
    public ResultadoOperacion editarEstudiante(String id, String nombres, String edadStr) {
        try {
            int edad = Integer.parseInt(edadStr.trim());

            // USANDO FACTORY
            Estudiante e = EstudianteFactory.crear(id.trim(), nombres.trim(), edad);

            return service.editar(e);
        } catch (NumberFormatException ex) {
            return ResultadoOperacion.error("Edad inválida. Debe ser un número entero.");
        }
    }

    /**
     * Elimina un estudiante por su ID.
     */
    public ResultadoOperacion eliminarEstudiante(String id) {
        return service.eliminar(id.trim());
    }

    /**
     * Obtiene la lista de todos los estudiantes.
     */
    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return service.listar();
    }
}
