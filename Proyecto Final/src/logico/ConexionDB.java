package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    /**
     * Intenta establecer y devolver una nueva conexión a la base de datos.
     * @return Un objeto Connection si la conexión es exitosa, de lo contrario null.
     */
    public static Connection obtenerConexion() {
        try {
            // Se carga la clase del driver de SQL Server.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // --- CADENA DE CONEXIÓN PARA AUTENTICACIÓN INTEGRADA (WINDOWS) ---
            // 'integratedSecurity=true' le indica al driver que use las credenciales
            // del usuario de Windows que está ejecutando el programa.
            // Para que esto funcione, el argumento de la VM debe apuntar a la DLL de autenticación.
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Clinica;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            
            // Se establece la conexión. No se necesita usuario ni contraseña aquí.
            Connection cnx = DriverManager.getConnection(url);
            System.out.println("Conexión a la base de datos 'Clinica' establecida exitosamente.");
            return cnx;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de SQL Server. Asegúrate de que el archivo .jar esté añadido a las librerías del proyecto.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos. Verifica lo siguiente:");
            System.err.println("1. El servicio de SQL Server está en ejecución.");
            System.err.println("2. El nombre de la base de datos 'Clinica' es correcto.");
            System.err.println("3. Tu usuario de Windows tiene permisos para acceder a la base de datos.");
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}