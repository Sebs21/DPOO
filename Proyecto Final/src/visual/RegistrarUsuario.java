package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import logico.Especialidad;
import logico.User;

public class RegistrarUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTextField txtUsuario;
    private JTextField txtApellido;
    private JTextField txtConfirmarCedula;
    private JTextField txtCedula;
    private JTextField txtEdad;
    private JComboBox<Especialidad> cbxEspecialidad;
    private JComboBox<String> cbxSexo; // <-- CAMBIO: Se añade el ComboBox para el sexo

    public RegistrarUsuario() {
        setTitle("Registro de Doctor");
        setBounds(100, 100, 850, 450); // Se ajusta la altura
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(175, 238, 238), 4, true), "Información del Doctor",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(10, 11, 814, 350); // Se ajusta la altura
        contentPanel.add(panel);
        panel.setLayout(null);

        // (El resto de la UI se mantiene igual)
        txtUsuario = new JTextField();
        txtUsuario.setBounds(40, 103, 150, 34);
        panel.add(txtUsuario);
        
        JLabel lblNewLabel = new JLabel("Nombre:");
        lblNewLabel.setBounds(40, 68, 110, 34);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        
        txtApellido = new JTextField();
        txtApellido.setBounds(220, 103, 150, 34);
        panel.add(txtApellido);
        
        JLabel lblIngresarContrasea = new JLabel("Apellido:");
        lblIngresarContrasea.setBounds(220, 68, 141, 34);
        panel.add(lblIngresarContrasea);
        lblIngresarContrasea.setFont(new Font("Times New Roman", Font.BOLD, 15));
        
        JLabel lblIngresarEspecialidad = new JLabel("Especialidad:");
        lblIngresarEspecialidad.setBounds(40, 175, 161, 34);
        panel.add(lblIngresarEspecialidad);
        lblIngresarEspecialidad.setFont(new Font("Times New Roman", Font.BOLD, 15));
        
        cbxEspecialidad = new JComboBox<>();
        cbxEspecialidad.setBounds(40, 210, 161, 34);
        panel.add(cbxEspecialidad);
        
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblEdad.setBounds(400, 68, 141, 34);
        panel.add(lblEdad);
        
        txtEdad = new JTextField();
        txtEdad.setBounds(400, 103, 141, 34);
        panel.add(txtEdad);
        
        // <-- CAMBIO: Se añade el ComboBox para el sexo -->
        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblSexo.setBounds(220, 175, 141, 34);
        panel.add(lblSexo);
        
        cbxSexo = new JComboBox<>();
        cbxSexo.setModel(new DefaultComboBoxModel<>(new String[] {"<Seleccione>", "Masculino", "Femenino"}));
        cbxSexo.setBounds(220, 210, 150, 34);
        panel.add(cbxSexo);

        JLabel lblCedula = new JLabel("Cédula (será la contraseña):");
        lblCedula.setBounds(580, 68, 200, 34);
        panel.add(lblCedula);
        lblCedula.setFont(new Font("Times New Roman", Font.BOLD, 15));
        
        txtCedula = new JTextField();
        txtCedula.setBounds(580, 103, 180, 34);
        panel.add(txtCedula);
        
        JLabel lblConfirmarContrasea = new JLabel("Confirmar Cédula:");
        lblConfirmarContrasea.setBounds(580, 175, 141, 34);
        panel.add(lblConfirmarContrasea);
        lblConfirmarContrasea.setFont(new Font("Times New Roman", Font.BOLD, 15));
        
        txtConfirmarCedula = new JTextField();
        txtConfirmarCedula.setBounds(580, 210, 180, 34);
        panel.add(txtConfirmarCedula);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Registrar Doctor");
        okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validacion()) {
                    User usuario = new User(txtUsuario.getText(), txtCedula.getText(), "Doctor");
                    Clinica.getInstance().agregarUsuario(usuario);

                    String especialidadSeleccionada = ((Especialidad) cbxEspecialidad.getSelectedItem()).getNombre();
                    String sexoSeleccionado = cbxSexo.getSelectedItem().toString(); // <-- Se obtiene el sexo
                    
                    Doctor doctor = new Doctor(txtCedula.getText(), txtUsuario.getText(),
                            txtApellido.getText(), especialidadSeleccionada, txtEdad.getText(), sexoSeleccionado, usuario); // <-- Se pasa al constructor
                    Clinica.getInstance().agregarDoctor(doctor);
                    
                    JOptionPane.showMessageDialog(null, "Doctor registrado con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    clean();
                }
            }
        });
        buttonPane.add(okButton);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 16));
        buttonPane.add(btnCerrar);
        
        cargarEspecialidades();
    }
    
    private void cargarEspecialidades() {
        DefaultComboBoxModel<Especialidad> model = new DefaultComboBoxModel<>();
        ArrayList<Especialidad> especialidades = Clinica.getInstance().getMisEspecialidades();
        for (Especialidad esp : especialidades) {
            model.addElement(esp);
        }
        cbxEspecialidad.setModel(model);
    }

    private boolean validacion() {
        if (txtUsuario.getText().isEmpty() || txtApellido.getText().isEmpty() || txtCedula.getText().isEmpty() || cbxEspecialidad.getSelectedIndex() < 0 || cbxSexo.getSelectedIndex() <= 0) { // <-- Se valida el sexo
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!txtCedula.getText().equals(txtConfirmarCedula.getText())) {
            JOptionPane.showMessageDialog(this, "Las cédulas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (Clinica.getInstance().buscarDoctorByCedula(txtCedula.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un doctor con esa cédula.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clean() {
        txtUsuario.setText("");
        txtApellido.setText("");
        txtConfirmarCedula.setText("");
        cbxEspecialidad.setSelectedIndex(0);
        cbxSexo.setSelectedIndex(0); // <-- Se limpia el sexo
        txtEdad.setText("");
        txtCedula.setText("");
    }
}