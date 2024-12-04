package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import logico.Paciente;
import logico.User;

public class RegistrarUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtApellido;
	private JTextField txtConfirmarCedula;
	private JComboBox cbxTipo;
	private JTextField txtCedula;
	private JTextField txtEspecialidad;
	private JTextField txtEdad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			RegistrarUsuario dialog = new RegistrarUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarUsuario() {

		setTitle("Registro de usuario");
		setBounds(100, 100, 926, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(175, 238, 238), 4, true), "Informaci\u00F3n usuario",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 890, 381);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(71, 103, 110, 34);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(71, 58, 110, 34);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));

		txtApellido = new JTextField();
		txtApellido.setBounds(252, 103, 141, 34);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblIngresarContrasea = new JLabel("Ingresar Apellido:");
		lblIngresarContrasea.setBounds(252, 58, 141, 34);
		panel.add(lblIngresarContrasea);
		lblIngresarContrasea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));

		txtConfirmarCedula = new JTextField();
		txtConfirmarCedula.setBounds(308, 212, 141, 34);
		panel.add(txtConfirmarCedula);
		txtConfirmarCedula.setColumns(10);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar Cedula:");
		lblConfirmarContrasea.setBounds(308, 167, 141, 34);
		panel.add(lblConfirmarContrasea);
		lblConfirmarContrasea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));

		JLabel lblNewLabel_1 = new JLabel("Su contrase\u00F1a sera su cedula de identificaci\u00F3n");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(279, 330, 332, 40);
		panel.add(lblNewLabel_1);

		JLabel label = new JLabel("Ingresar Cedula:");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		label.setBounds(676, 58, 141, 34);
		panel.add(label);

		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(676, 103, 141, 34);
		panel.add(txtCedula);

		JLabel lblIngresarEspecialidad = new JLabel("Ingresar Especialidad:");
		lblIngresarEspecialidad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIngresarEspecialidad.setBounds(548, 167, 161, 34);
		panel.add(lblIngresarEspecialidad);

		txtEspecialidad = new JTextField();
		txtEspecialidad.setEnabled(false);
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(548, 212, 161, 34);
		panel.add(txtEspecialidad);

		cbxTipo = new JComboBox();
		cbxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbxTipo.getSelectedItem().equals("Doctor")) {
					txtEspecialidad.setEnabled(true);
				} else {
					txtEspecialidad.setEnabled(false);
				}
			}
		});
		cbxTipo.setBounds(99, 204, 126, 47);
		panel.add(cbxTipo);
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Doctor", "Paciente" }));

		JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setBounds(99, 167, 126, 34);
		panel.add(lblTipoDeUsuario);
		lblTipoDeUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));

		JLabel lblIngresarEdad = new JLabel("Ingresar Edad:");
		lblIngresarEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIngresarEdad.setBounds(464, 58, 141, 34);
		panel.add(lblIngresarEdad);

		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(464, 103, 141, 34);
		panel.add(txtEdad);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (validacion()) {
							User usuario = new User(txtUsuario.getText(), txtCedula.getText(),
									cbxTipo.getSelectedItem().toString());
							Clinica.getInstance().agregarUsuario(usuario);

							if (cbxTipo.getSelectedItem().equals("Paciente")) {
								int codPaciente = Clinica.getIdPaciente();
								Paciente paciente = new Paciente(txtCedula.getText(), txtUsuario.getText(),
										txtApellido.getText(), codPaciente, txtEdad.getText().toString(), usuario);
								Clinica.getInstance().agregarPaciente(paciente);
							} else if (cbxTipo.getSelectedItem().equals("Doctor")){
								Doctor doctor = new Doctor(txtCedula.getText(), txtUsuario.getText(),
										txtApellido.getText(), txtEspecialidad.getText(), txtEdad.getText(), usuario);
								Clinica.getInstance().agregarDoctor(doctor);
							}		
							clean();			
						}
					}
				});
				
				JButton btnNewButton = new JButton("Iniciar Sesion");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						InicioSesion iniSe = new InicioSesion();
						iniSe.setVisible(true);
					}
				});
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnNewButton.setForeground(Color.GREEN);
				buttonPane.add(btnNewButton);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				cancelButton.setForeground(new Color(255, 0, 0));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private boolean validacion() {
	    if (txtUsuario.getText().isEmpty() || txtCedula.getText().isEmpty() || cbxTipo.getSelectedIndex() == 0) {
	        JOptionPane.showMessageDialog(this, "Debe llenar todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!txtCedula.getText().equals(txtConfirmarCedula.getText())) {
	        JOptionPane.showMessageDialog(this, "Las cédulas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (cbxTipo.getSelectedItem().equals("Doctor") && txtEspecialidad.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe ingresar la especialidad del doctor.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    if(!txtConfirmarCedula.getText().equals(txtCedula.getText())) {
	        JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}

	private void clean() {
		txtUsuario.setText("");
		txtApellido.setText("");
		cbxTipo.setSelectedItem("<Seleccione>");
		txtConfirmarCedula.setText("");
		txtEspecialidad.setText("");
		txtEdad.setText("");
		txtCedula.setText("");

	}
}
