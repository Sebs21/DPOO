package logico;

import java.sql.*;

public class ConexionDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Clinica;encrypt=false;trustServerCertificate=true";
    private static final String USER = "Sebastian";
    private static final String PASSWORD = "12345";
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión exitosa a SQL Server");
            }
            return connection;
        } catch (Exception e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // ✅ AGREGAR este método para compatibilidad
    public static Connection obtenerConexion() {
        return getConnection();
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}