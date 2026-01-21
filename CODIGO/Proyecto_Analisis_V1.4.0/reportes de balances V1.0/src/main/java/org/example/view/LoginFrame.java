package org.example.view;

import org.example.model.User;
import org.example.service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private AuthService authService;
    private JTextField userField;
    private JPasswordField passField;

    public LoginFrame() {
        this.authService = new AuthService();
        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Absolute positioning for simplicity in this prototype

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(50, 50, 80, 25);
        panel.add(userLabel);

        userField = new JTextField();
        userField.setBounds(140, 50, 200, 25);
        panel.add(userField);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(50, 100, 80, 25);
        panel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(140, 100, 200, 25);
        panel.add(passField);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(140, 160, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        panel.add(loginButton);

        add(panel);
    }

    private void performLogin() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        // Validation Step 1: Check for empty fields
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Campos vacíos o datos incorrectos", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            // Highlight fields (simple implementation: set background color)
            if (username.isEmpty()) userField.setBackground(Color.PINK);
            if (password.isEmpty()) passField.setBackground(Color.PINK);
            return;
        } else {
            // Reset colors
            userField.setBackground(Color.WHITE);
            passField.setBackground(Color.WHITE);
        }

        // Validation Step 2: Authenticate
        User user = authService.authenticate(username, password);

        if (user != null) {
            // Success
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
            MainFrame mainFrame = new MainFrame(user);
            mainFrame.setVisible(true);
            this.dispose(); // Close login window
        } else {
            // Failure
            JOptionPane.showMessageDialog(this, 
                "Usuario o contraseña incorrectos", 
                "Alerta de Seguridad", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
