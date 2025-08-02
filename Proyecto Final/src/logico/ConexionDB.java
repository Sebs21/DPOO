package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    /**
     * Intenta establecer y devolver una nueva conexión a la base de datos local.
     * @return Un objeto Connection si la conexión es exitosa, de lo contrario null.
     */
    public static Connection obtenerConexion() {
        try {
            // Se carga la clase del driver de SQL Server.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // --- CADENA DE CONEXIÓN PARA SERVIDOR LOCAL CON AUTENTICACIÓN DE WINDOWS ---
            String server = "localhost"; // O el nombre de tu instancia local, ej: "localhost\\SQLEXPRESS"
            String port = "1433";        // Puerto por defecto de SQL Server
            String databaseName = "Clinica";
            
            // 'integratedSecurity=true' le indica al driver que use las credenciales
            // del usuario de Windows que está ejecutando el programa.
            String url = String.format(
                "jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true;encrypt=true;trustServerCertificate=true;",
                server, port, databaseName
            );
            
            // Se establece la conexión. No se necesita usuario ni contraseña en getConnection().
            Connection cnx = DriverManager.getConnection(url);
            System.out.println("Conexión local a la base de datos 'Clinica' establecida exitosamente.");
            return cnx;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de SQL Server. Asegúrate de que el archivo .jar esté añadido a las librerías del proyecto.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Error de SQL al conectar a la base de datos local:");
            System.err.println("Verifica que el servicio de SQL Server esté en ejecución y que la base de datos 'Clinica' exista.");
            e.printStackTrace();
            return null;
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Error Crítico de Autenticación Integrada: No se encontró la DLL.");
            System.err.println("Asegúrate de haber configurado el argumento de la VM '-Djava.library.path' para que apunte a la carpeta 'auth' del driver JDBC.");
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