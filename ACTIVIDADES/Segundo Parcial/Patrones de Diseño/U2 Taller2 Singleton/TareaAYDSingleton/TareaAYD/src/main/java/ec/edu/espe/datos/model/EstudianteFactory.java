package main.java.ec.edu.espe.datos.model;

public class EstudianteFactory {

    public static Estudiante crear(String id, String nombres, int edad) {
        return new Estudiante(id, nombres, edad);
    }

}
