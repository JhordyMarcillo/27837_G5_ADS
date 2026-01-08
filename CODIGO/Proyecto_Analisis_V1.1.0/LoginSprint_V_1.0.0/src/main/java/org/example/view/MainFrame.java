package org.example.view;

import org.example.model.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private User currentUser;

    public MainFrame(User user) {
        this.currentUser = user;
        setTitle("Sistema de Gestión - Panel Principal");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bienvenida, " + currentUser.getRole() + " (" + currentUser.getUsername() + ")", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(welcomeLabel, BorderLayout.CENTER);

        // Placeholder for "Registrar donación, registrar egresos, generar reportes"
        JPanel actionsPanel = new JPanel();
        actionsPanel.add(new JButton("Registrar Donación"));
        actionsPanel.add(new JButton("Registrar Egresos"));
        actionsPanel.add(new JButton("Generar Reportes"));
        
        panel.add(actionsPanel, BorderLayout.SOUTH);

        add(panel);
    }
}
