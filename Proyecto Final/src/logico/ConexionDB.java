package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

 
    public static Connection obtenerConexion() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            
            String server = "localhost"; 
            String port = "1433";        
            String databaseName = "Clinica";
            
            
            String url = String.format(
                "jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true;encrypt=true;trustServerCertificate=true;",
                server, port, databaseName
            );
            
            Connection cnx = DriverManager.getConnection(url);
            System.out.println("Conexión local a la base de datos 'Clinica' establecida exitosamente.");
            return cnx;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de SQL Server. Asegúrate de que el archivo .jar esté añadido a las librerías del proyecto.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Error ");          
            e.printStackTrace();
            return null;
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Error");          
            e.printStackTrace();
            return null;
        }
    }

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