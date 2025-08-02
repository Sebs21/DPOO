package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.border.LineBorder;
import logico.Clinica;
import logico.User;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import logico.ConexionDB; // Se importa la clase de conexión
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesion extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                // --- LÓGICA DE ARRANQUE CON BASE DE DATOS ---

                // PASO 1: Intentar conectar a la base de datos.
                Connection cnx = ConexionDB.obtenerConexion();

                if (cnx == null) {
                    // Si la conexión falla, el programa no puede continuar.
                    JOptionPane.showMessageDialog(null, "Error Crítico: No se pudo conectar a la base de datos.\nEl programa se cerrará.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                    System.exit(1); // Cierra la aplicación
                    return;
                }

                // PASO 2: Verificar si la base de datos está vacía (o si necesita poblarse).
                // Usaremos la tabla 'Especialidad' como indicador.
                boolean dbVacia = false;
                try {
                    String sql = "SELECT COUNT(*) FROM Especialidad";
                    PreparedStatement ps = cnx.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        int count = rs.getInt(1);
                        if (count == 0) {
                            dbVacia = true;
                        }
                    }
                    rs.close();
                    ps.close();

                } catch (SQLException e) {
                    System.err.println("Error al verificar si la DB está vacía. Asumiendo que está vacía para intentar poblar.");
                    e.printStackTrace();
                    dbVacia = true; // Si hay un error, es mejor intentar poblar para no dejar la DB inconsistente
                }

                // PASO 3: Si la base de datos está vacía, poblarla con datos iniciales.
                if (dbVacia) {
                    System.out.println("Base de datos vacía. Poblando con datos iniciales...");
                    Clinica.getInstance().poblarBaseDeDatosInicial(); // Llama al método de "seeding"
                    JOptionPane.showMessageDialog(null, "La base de datos ha sido inicializada con los datos por defecto.", "Primer Arranque", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("La base de datos ya contiene datos. Cargando datos existentes.");
                    // Si la DB no está vacía, Clinica.getInstance() ya se encargará de cargar los datos.
                }

                // PASO 4: Una vez verificada y/o poblada la DB, mostrar la ventana de login.
                try {
                    InicioSesion dialog = new InicioSesion();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Opcional: Cerrar la conexión al finalizar la aplicación.
                    // Esto es importante si la aplicación se cierra completamente.
                    // Si la conexión se mantiene abierta durante toda la vida de la app,
                    // se cerraría en un WindowListener del JFrame principal.
                    // ConexionDB.cerrarConexion();
                }
            }
        });
    }

    public InicioSesion() {
        setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
        setTitle("Inicio de Sesion");
        setBounds(100, 100, 492, 514);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(new LineBorder(new Color(95, 158, 160), 4, true), "Iniciar Sesion",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_4.setBounds(69, 11, 315, 392);
        contentPanel.add(panel_4);
        panel_4.setLayout(null);

        txtNombre = new JTextField();
        txtNombre.setBounds(67, 134, 171, 52);
        panel_4.add(txtNombre);
        txtNombre.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBounds(102, 61, 101, 52);
        panel_4.add(panel);
        panel.setBorder(new LineBorder(UIManager.getColor("activeCaption"), 3, true));

        JLabel lblNewLabel = new JLabel("Nombre:");
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(89, 220, 136, 51);
        panel_4.add(panel_1);
        panel_1.setBorder(new LineBorder(new Color(135, 206, 235), 3, true));

        JLabel lblCedula = new JLabel("Contraseña:");
        panel_1.add(lblCedula);
        lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 23));

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(50, 282, 205, 69);
        panel_4.add(panel_2);
        panel_2.setBorder(new LineBorder(new Color(135, 206, 235), 3, true));
        panel_2.setLayout(null);

        txtPassword = new JTextField();
        txtPassword.setBounds(10, 11, 185, 47);
        panel_2.add(txtPassword);
        txtPassword.setColumns(10);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(50, 124, 205, 75);
        panel_4.add(panel_3);
        panel_3.setBorder(new LineBorder(UIManager.getColor("activeCaption"), 3, true));

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGuest = new JButton("Continuar como Invitado");
        btnGuest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User guestUser = new User("Invitado", "", "Invitado");
                Clinica.getInstance().setLoginUser(guestUser);
                dispose();
                Principal prin = new Principal();
                prin.setVisible(true);
            }
        });
        buttonPane.add(btnGuest);

        JButton okButton = new JButton("Iniciar Sesion");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Clinica.getInstance().ConfirmarLogin(txtNombre.getText(), txtPassword.getText())) {
                    Principal prin = new Principal();
                    dispose();
                    prin.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }
}
