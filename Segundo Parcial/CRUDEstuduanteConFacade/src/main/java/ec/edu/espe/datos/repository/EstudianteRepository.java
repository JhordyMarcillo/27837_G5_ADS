package main.java.ec.edu.espe.datos.repository;

import main.java.ec.edu.espe.datos.model.Estudiante;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de Estudiantes.

 * Ahora funciona como un Data Access Object (DAO) sin estado en memoria,
 *.
 */
public class EstudianteRepository {
    private static final String STORAGE_FILE = "students.ser";

    public EstudianteRepository() {
        // Constructor público estándar
    }

    public synchronized boolean agregar(Estudiante e) {
        if (e == null) return false;
        List<Estudiante> estudiantes = loadFromFile();
        boolean added = estudiantes.add(e);
        if (added) saveToFile(estudiantes);
        return added;
    }

    public synchronized boolean editar(Estudiante e) {
        if (e == null) return false;
        List<Estudiante> estudiantes = loadFromFile();
        Optional<Estudiante> found = estudiantes.stream()
                .filter(st -> st.getId().equals(e.getId()))
                .findFirst();
        if (found.isPresent()) {
            Estudiante orig = found.get();
            orig.setNombres(e.getNombres());
            orig.setEdad(e.getEdad());
            saveToFile(estudiantes);
            return true;
        }
        return false;
    }

    public synchronized boolean eliminar(String id) {
        List<Estudiante> estudiantes = loadFromFile();
        boolean removed = estudiantes.removeIf(st -> st.getId().equals(id));
        if (removed) saveToFile(estudiantes);
        return removed;
    }

    public synchronized List<Estudiante> listar() {
        return loadFromFile();
    }

    public synchronized Optional<Estudiante> findById(String id) {
        return loadFromFile().stream().filter(st -> st.getId().equals(id)).findFirst();
    }

    @SuppressWarnings("unchecked")
    private List<Estudiante> loadFromFile() {
        File f = new File(STORAGE_FILE);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                return (List<Estudiante>) obj;
            }
        } catch (Exception ex) {
            System.err.println("No se pudo cargar almacenamiento: " + ex.getMessage());
        }
        return new ArrayList<>();
    }

    private void saveToFile(List<Estudiante> estudiantes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            oos.writeObject(new ArrayList<>(estudiantes));
        } catch (IOException ex) {
            System.err.println("Error guardando estudiantes: " + ex.getMessage());
        }
    }
}
