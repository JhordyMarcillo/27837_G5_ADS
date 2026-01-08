package main.java.ec.edu.espe.datos.repository;


import main.java.ec.edu.espe.datos.model.Estudiante;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EstudianteRepository {
    private static final String STORAGE_FILE = "students.ser";
    private static EstudianteRepository instance;
    private final List<Estudiante> estudiantes;

    private EstudianteRepository() {
        this.estudiantes = loadFromFile();
    }

    public static synchronized EstudianteRepository getInstance() {
        if (instance == null) {
            instance = new EstudianteRepository();
        }
        return instance;
    }

    public synchronized boolean agregar(Estudiante e) {
        if (e == null) return false;
        boolean added = estudiantes.add(e);
        if (added) saveToFile();
        return added;
    }

    public synchronized boolean editar(Estudiante e) {
        if (e == null) return false;
        Optional<Estudiante> found = estudiantes.stream()
                .filter(st -> st.getId().equals(e.getId()))
                .findFirst();
        if (found.isPresent()) {
            Estudiante orig = found.get();
            orig.setNombres(e.getNombres());
            orig.setEdad(e.getEdad());
            saveToFile();
            return true;
        }
        return false;
    }

    public synchronized boolean eliminar(String id) {
        boolean removed = estudiantes.removeIf(st -> st.getId().equals(id));
        if (removed) saveToFile();
        return removed;
    }

    public synchronized List<Estudiante> listar() {
        return Collections.unmodifiableList(new ArrayList<>(estudiantes));
    }

    public synchronized Optional<Estudiante> findById(String id) {
        return estudiantes.stream().filter(st -> st.getId().equals(id)).findFirst();
    }

    private List<Estudiante> loadFromFile() {
        File f = new File(STORAGE_FILE);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                // unchecked cast but safe because we control writing
                return (List<Estudiante>) obj;
            }
        } catch (Exception ex) {
            System.err.println("No se pudo cargar almacenamiento, iniciando vacío: " + ex.getMessage());
        }
        return new ArrayList<>();
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            oos.writeObject(new ArrayList<>(estudiantes));
        } catch (IOException ex) {
            System.err.println("Error guardando estudiantes: " + ex.getMessage());
        }
    }
}
