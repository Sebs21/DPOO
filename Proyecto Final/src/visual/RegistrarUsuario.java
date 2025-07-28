package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Doctor;
import logico.User;

public class RegistrarUsuario extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtUsuario; // Nombre del Doctor
    private JTextField txtApellido;
    private JTextField txtConfirmarCedula;
    private JTextField txtCedula;
    private JTextField txtEspecialidad;
    private JTextField txtEdad;
    
    public static void main(String[] args) { try { 
    	RegistrarUsuario dialog = new RegistrarUsuario(); 
    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
    	dialog.setVisible(true); } 
    	catch (Exception e) { 
    		e.printStackTrace(); 
    	} 
    }

    public RegistrarUsuario() {
        setTitle("Registro de Doctor"); // <-- CAMBIO: Título actualizado
        setBounds(100, 100, 850, 400); // Tamaño ajustado
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(175, 238, 238), 4, true), "Información del Doctor",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(10, 11, 814, 300);
        contentPanel.add(panel);
        panel.setLayout(null);

        // --- Interfaz Gráfica (simplificada para solo doctores) ---
        txtUsuario = new JTextField();
        txtUsuario.setBounds(40, 103, 150, 34);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        JLabel lblNewLabel = new JLabel("Nombre:");
        lblNewLabel.setBounds(40, 68, 110, 34);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));

        txtApellido = new JTextField();
        txtApellido.setBounds(220, 103, 150, 34);
        panel.add(txtApellido);
        txtApellido.setColumns(10);

        JLabel lblIngresarContrasea = new JLabel("Apellido:");
        lblIngresarContrasea.setBounds(220, 68, 141, 34);
        panel.add(lblIngresarContrasea);
        lblIngresarContrasea.setFont(new Font("Times New Roman", Font.BOLD, 15));

        JLabel lblIngresarEspecialidad = new JLabel("Especialidad:");
        lblIngresarEspecialidad.setBounds(40, 175, 161, 34);
        panel.add(lblIngresarEspecialidad);
        lblIngresarEspecialidad.setFont(new Font("Times New Roman", Font.BOLD, 15));

        txtEspecialidad = new JTextField();
        txtEspecialidad.setColumns(10);
        txtEspecialidad.setBounds(40, 210, 161, 34);
        panel.add(txtEspecialidad);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblEdad.setBounds(400, 68, 141, 34);
        panel.add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setColumns(10);
        txtEdad.setBounds(400, 103, 141, 34);
        panel.add(txtEdad);
        
        JLabel lblCedula = new JLabel("Cédula (será la contraseña):");
        lblCedula.setBounds(580, 68, 200, 34);
        panel.add(lblCedula);
        lblCedula.setFont(new Font("Times New Roman", Font.BOLD, 15));

        txtCedula = new JTextField();
        txtCedula.setColumns(10);
        txtCedula.setBounds(580, 103, 180, 34);
        panel.add(txtCedula);

        JLabel lblConfirmarContrasea = new JLabel("Confirmar Cédula:");
        lblConfirmarContrasea.setBounds(580, 175, 141, 34);
        panel.add(lblConfirmarContrasea);
        lblConfirmarContrasea.setFont(new Font("Times New Roman", Font.BOLD, 15));

        txtConfirmarCedula = new JTextField();
        txtConfirmarCedula.setBounds(580, 210, 180, 34);
        panel.add(txtConfirmarCedula);
        txtConfirmarCedula.setColumns(10);

        // --- Botones ---
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Registrar Doctor");
        okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validacion()) {
                    // <-- CAMBIO: Se crea un User tipo "Doctor" directamente
                    User usuario = new User(txtUsuario.getText(), txtCedula.getText(), "Doctor");
                    Clinica.getInstance().agregarUsuario(usuario);

                    // <-- CAMBIO: Se crea el Doctor y se agrega a la clínica
                    Doctor doctor = new Doctor(txtCedula.getText(), txtUsuario.getText(),
                            txtApellido.getText(), txtEspecialidad.getText(), txtEdad.getText(), usuario);
                    Clinica.getInstance().agregarDoctor(doctor);
                    
                    JOptionPane.showMessageDialog(null, "Doctor registrado con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    clean();
                }
            }
        });
        buttonPane.add(okButton);
        
        JButton btnNewButton = new JButton("Cerrar");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Principal prin = new Principal();
        		prin.setVisible(true);
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        buttonPane.add(btnNewButton);
        // ... (resto de los botones)
    }

    private boolean validacion() {
        if (txtUsuario.getText().isEmpty() || txtApellido.getText().isEmpty() || txtCedula.getText().isEmpty() || txtEspecialidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!txtCedula.getText().equals(txtConfirmarCedula.getText())) {
            JOptionPane.showMessageDialog(this, "Las cédulas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clean() {
        txtUsuario.setText("");
        txtApellido.setText("");
        txtConfirmarCedula.setText("");
        txtEspecialidad.setText("");
        txtEdad.setText("");
        txtCedula.setText("");
    }
}