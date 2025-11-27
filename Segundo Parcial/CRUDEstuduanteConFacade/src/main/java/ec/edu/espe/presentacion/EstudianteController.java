package main.java.ec.edu.espe.presentacion;

import main.java.ec.edu.espe.datos.model.Estudiante;
import main.java.ec.edu.espe.logica_negocio.EstudianteFacade;
import main.java.ec.edu.espe.logica_negocio.EstudianteService.ResultadoOperacion;
import java.util.List;

/**
 * Controlador que maneja las acciones del usuario.
 * Utiliza el patrón FACADE para interactuar con la lógica de negocio.
 */
public class EstudianteController {
    private final EstudianteFacade facade;
    
    public EstudianteController() {
        this.facade = new EstudianteFacade();
    }
    
    public ResultadoOperacion guardarEstudiante(String id, String nombres, String edadStr) {
        return facade.agregarEstudiante(id, nombres, edadStr);
    }
    
    public ResultadoOperacion editarEstudiante(String id, String nombres, String edadStr) {
        return facade.editarEstudiante(id, nombres, edadStr);
    }
    
    public ResultadoOperacion eliminarEstudiante(String id) {
        return facade.eliminarEstudiante(id);
    }
    
    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return facade.listarEstudiantes();
    }
}
