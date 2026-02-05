package org.example.service;

import org.example.model.User;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users = new HashMap<>();

    public AuthService() {
        // Simulating a database with hardcoded users as per requirements
        // "Las credenciales... deben estar registradas en una base de datos"
        // For Sprint 0/Prototype, we use in-memory storage.
        users.put("tesorera", new User("tesorera", "12345", "Tesorera"));
        users.put("coordinadora", new User("coordinadora", "admin", "Coordinadora"));
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
