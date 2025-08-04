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
                Connection cnx = ConexionDB.obtenerConexion();

                if (cnx == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    return;
                }

                try {
                    boolean dbVacia = false;
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

                    if (dbVacia) {
                        Clinica.getInstance().poblarBaseDeDatosInicial();
                        JOptionPane.showMessageDialog(null, "La base de datos ha sido inicializada.", "Primer Arranque", JOptionPane.INFORMATION_MESSAGE);
                    } 
                } catch (SQLException e) {                  
                    e.printStackTrace();
                }
                try {
                    InicioSesion dialog = new InicioSesion();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
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
