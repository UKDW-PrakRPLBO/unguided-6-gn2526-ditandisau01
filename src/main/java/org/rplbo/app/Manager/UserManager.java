package org.rplbo.app.Manager;

import org.rplbo.app.Data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Connection connection;

    public UserManager(Connection connection) {
        this.connection = connection;
    }

    public UserManager() {
    }

    // --- METHOD CARI USER SESUAI ROLE ---
    public List<User> getUsersByRole(String role) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, role);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Constructor User: password, role, username, email
                    User user = new User(
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("username"),
                            rs.getString("email")
                    );
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error get user by role: " + e.getMessage());
        }
        return userList;
    }

    // --- METHOD REGISTER (INSERT) ---
    public boolean registerUser(String username, String password, String email, String role) {
        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, role);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error register user: " + e.getMessage());
            return false;
        }
    }

    // --- METHOD LOGIN (SELECT) ---
    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                // Jika ada data (next() == true), berarti username & password valid
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error authenticate user: " + e.getMessage());
            return false;
        }
    }

    // --- METHOD CARI ROLE USER (Sudah Terisi dari soal) ---
    public String getUserRole(String username) {
        String query = "SELECT role FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error ambil role: " + e.getMessage());
        }
        return "guest";
    }
}