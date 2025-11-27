package main.java.ec.edu.espe.test;

import main.java.ec.edu.espe.presentacion.EstudianteController;
import main.java.ec.edu.espe.datos.model.Estudiante;
import main.java.ec.edu.espe.logica_negocio.EstudianteService.ResultadoOperacion;

/**
 * Clase de prueba para demostrar que el patrón Singleton funciona correctamente
 * en el EstudianteRepository, garantizando que múltiples controladores comparten
 * la misma instancia del repositorio y, por tanto, los mismos datos.
 */
public class TestSingleton {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  PRUEBA DE PATRÓN SINGLETON - PERSISTENCIA COMPARTIDA         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        // Crear dos controladores diferentes
        System.out.println("➤ Creando Controller 1...");
        EstudianteController controller1 = new EstudianteController();
        
        System.out.println("➤ Creando Controller 2...\n");
        EstudianteController controller2 = new EstudianteController();
        
        // Estado inicial
        System.out.println("════════════════════════════════════════════════════════════════");
        System.out.println("1. ESTADO INICIAL - Controller 1");
        System.out.println("════════════════════════════════════════════════════════════════");
        listarEstudiantes(controller1, "Controller 1");
        
        // Agregar estudiante con controller1
        System.out.println("\n════════════════════════════════════════════════════════════════");
        System.out.println("2. AGREGANDO ESTUDIANTE CON CONTROLLER 1");
        System.out.println("════════════════════════════════════════════════════════════════");
        System.out.println("➤ Agregando: ID=TEST001, Nombre=Juan Pérez, Edad=20");
        ResultadoOperacion res1 = controller1.guardarEstudiante("TEST001", "Juan Pérez", "20");
        System.out.println("   Resultado: " + res1.getMensaje());
        
        System.out.println("\n➤ Agregando: ID=TEST002, Nombre=María González, Edad=22");
        ResultadoOperacion res2 = controller1.guardarEstudiante("TEST002", "María González", "22");
        System.out.println("   Resultado: " + res2.getMensaje());
        
        // Listar con controller1
        System.out.println("\n════════════════════════════════════════════════════════════════");
        System.out.println("3. LISTANDO ESTUDIANTES CON CONTROLLER 1");
        System.out.println("════════════════════════════════════════════════════════════════");
        listarEstudiantes(controller1, "Controller 1");
        
        // Listar con controller2 (debe ver los mismos estudiantes)
        System.out.println("\n════════════════════════════════════════════════════════════════");
        System.out.println("4. LISTANDO ESTUDIANTES CON CONTROLLER 2 (sin agregar nada)");
        System.out.println("════════════════════════════════════════════════════════════════");
        listarEstudiantes(controller2, "Controller 2");
        
        // Agregar más con controller2
        System.out.println("\n════════════════════════════════════════════════════════════════");
        System.out.println("5. AGREGANDO ESTUDIANTE CON CONTROLLER 2");
        System.out.println("════════════════════════════════════════════════════════════════");
        System.out.println("➤ Agregando: ID=TEST003, Nombre=Carlos Rodríguez, Edad=21");
        ResultadoOperacion res3 = controller2.guardarEstudiante("TEST003", "Carlos Rodríguez", "21");
        System.out.println("   Resultado: " + res3.getMensaje());
        
        // Verificar con controller1 de nuevo
        System.out.println("\n════════════════════════════════════════════════════════════════");
        System.out.println("6. VERIFICACIÓN FINAL CON CONTROLLER 1");
        System.out.println("════════════════════════════════════════════════════════════════");
        listarEstudiantes(controller1, "Controller 1");
        

        // Limpiar datos de prueba
        System.out.println("════════════════════════════════════════════════════════════════");
        System.out.println("LIMPIANDO DATOS DE PRUEBA...");
        System.out.println("════════════════════════════════════════════════════════════════");
        controller1.eliminarEstudiante("TEST001");
        controller1.eliminarEstudiante("TEST002");
        controller1.eliminarEstudiante("TEST003");
        System.out.println("✓ Datos de prueba eliminados.\n");
    }
    
    private static void listarEstudiantes(EstudianteController controller, String nombreController) {
        var estudiantes = controller.obtenerTodosLosEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("   [" + nombreController + "] Lista vacía - No hay estudiantes.");
        } else {
            System.out.println("   [" + nombreController + "] Total de estudiantes: " + estudiantes.size());
            for (Estudiante e : estudiantes) {
                System.out.println("   • ID: " + e.getId() + 
                                   " | Nombre: " + e.getNombres() + 
                                   " | Edad: " + e.getEdad());
            }
        }
    }
}
