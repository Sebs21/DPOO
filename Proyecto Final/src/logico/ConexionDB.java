package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    /**
     * Intenta establecer y devolver una nueva conexión a la base de datos remota.
     * @return Un objeto Connection si la conexión es exitosa, de lo contrario null.
     */
    public static Connection obtenerConexion() {
        try {
            // Se carga la clase del driver de SQL Server.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // --- CADENA DE CONEXIÓN PARA SERVIDOR REMOTO ---
            // Configuración para conectar a la base de datos de tu compañero
            String serverIP = "16.0.1000.6"; // IP de tu compañero
            String port = "1433"; // Puerto por defecto de SQL Server
            String databaseName = "Clinica";
            String username = "Ariel"; // Usuario SQL Server de tu compañero
            String password = "12345"; // Contraseña (tu compañero debe proporcionarla)
            
            // Opción 1: Con autenticación SQL Server
            String url = String.format(
                "jdbc:sqlserver:\\%s:%s;databaseName=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=true;",
                serverIP, port, databaseName, username, password
            );
            
            // Opción 2: Si tu compañero permite autenticación integrada remota (menos común)
            // String url = String.format(
            //     "jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true;encrypt=true;trustServerCertificate=true;",
            //     serverIP, port, databaseName
            // );
            
            // Opción 3: Para instancia nombrada (como SQLEXPRESS)
            // String url = String.format(
            //     "jdbc:sqlserver://%s\\SQLEXPRESS;databaseName=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=true;",
            //     serverIP, databaseName, username, password
            // );
            
            Connection cnx = DriverManager.getConnection(url);
            System.out.println("Conexión remota a la base de datos 'Clinica' establecida exitosamente.");
            return cnx;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de SQL Server. Asegúrate de que el archivo .jar esté añadido a las librerías del proyecto.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Error de SQL al conectar a la base de datos remota:");
            System.err.println("Mensaje: " + e.getMessage());
            System.err.println("Código de error: " + e.getErrorCode());
            System.err.println("Estado SQL: " + e.getSQLState());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cierra una conexión de forma segura.
     * @param cnx La conexión a cerrar.
     */
    public static void cerrarConexion(Connection cnx) {
        if (cnx != null) {
            try {
                cnx.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión:");
                e.printStackTrace();
            }
        }
    }
}