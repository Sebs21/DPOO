package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import logico.Clinica;
import logico.Doctor;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;

public class RegistroDoctor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEspecialidad;
	//
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
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setTitle("Registro Doctor");
		setBounds(100, 100, 585, 565);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblIngreseNombre = new JLabel("Ingrese Nombre:");
			lblIngreseNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblIngreseNombre.setBounds(110, 177, 129, 27);
			contentPanel.add(lblIngreseNombre);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(176, 224, 230), 4, true), "Informaci\u00F3n Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(82, 48, 407, 394);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese Cedula:");
		lblNewLabel.setBounds(33, 44, 119, 27);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtCedula = new JTextField();
		txtCedula.setBounds(215, 46, 158, 27);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		{
			JLabel lblIngreseApellido = new JLabel("Ingrese Apellido:");
			lblIngreseApellido.setBounds(23, 219, 129, 27);
			panel.add(lblIngreseApellido);
			lblIngreseApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		{
			txtApellido = new JTextField();
			txtApellido.setBounds(215, 221, 158, 27);
			panel.add(txtApellido);
			txtApellido.setColumns(10);
		}
		
		JLabel lblIngreseEspecialidad = new JLabel("Ingrese Especialidad:");
		lblIngreseEspecialidad.setBounds(33, 312, 158, 27);
		panel.add(lblIngreseEspecialidad);
		lblIngreseEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		{
			txtEspecialidad = new JTextField();
			txtEspecialidad.setBounds(215, 314, 158, 27);
			panel.add(txtEspecialidad);
			txtEspecialidad.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(215, 128, 158, 27);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
					Doctor doct = new Doctor(txtCedula.getText(), txtNombre.getText(),txtApellido.getText(),txtEspecialidad.getText());
						Clinica.getInstance().agregarDoctor(doct);
						JOptionPane.showMessageDialog(null, "Registro Exitoso!");
						clean();
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
	
	private void clean() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEspecialidad.setText("");
	}
	
}
