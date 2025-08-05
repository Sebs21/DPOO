package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

 
    public static Connection obtenerConexion() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");           
            
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Clinica;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            
            Connection cnx = DriverManager.getConnection(url);
            System.out.println("Conexión local a la base de datos Clinica.");
            return cnx;
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {       
            e.printStackTrace();
            return null;
        } catch (UnsatisfiedLinkError e) {     
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