package src.visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import logico.Clinica;
import logico.Doctor;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RegistroDoctor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEspecialidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroDoctor dialog = new RegistroDoctor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroDoctor() {
		setTitle("Registrar Doctor");
		setBounds(100, 100, 798, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(224, 255, 255), 4, true));
		panel.setBounds(10, 11, 762, 381);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Cedula:");
			lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
			lblNewLabel.setBounds(226, 44, 97, 41);
			panel.add(lblNewLabel);
		}
		
		txtCedula = new JTextField();
		txtCedula.setBounds(358, 43, 124, 41);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNombe = new JLabel("Nombre:");
		lblNombe.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNombe.setBounds(226, 127, 103, 41);
		panel.add(lblNombe);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(358, 127, 124, 41);
		panel.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblApellido.setBounds(226, 211, 97, 41);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(358, 211, 124, 41);
		panel.add(txtApellido);
		
		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblEspecialidad.setBounds(224, 295, 124, 41);
		panel.add(lblEspecialidad);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(358, 295, 124, 41);
		panel.add(txtEspecialidad);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					Doctor doctor = new Doctor(txtCedula.getText(),txtNombre.getText(), txtApellido.getText(), txtEspecialidad.getText());
					Clinica.getInstance().agregarDoctor(doctor);
					InicioSesion iniSesion = new InicioSesion();
					iniSesion.setVisible(true);
					iniSesion.setModal(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
