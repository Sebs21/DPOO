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

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InicioSesion extends JDialog {

	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JPasswordField txtCedula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InicioSesion dialog = new InicioSesion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InicioSesion() {
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setTitle("Inicio de Sesion");
		setBounds(100, 100, 658, 414);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		txtNombre = new JTextField();
		txtNombre.setBounds(235, 96, 171, 52);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		txtCedula = new JPasswordField();
		txtCedula.setBounds(235, 253, 171, 52);
		contentPanel.add(txtCedula);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("activeCaption"), 3, true));
		panel.setBounds(270, 23, 101, 52);
		contentPanel.add(panel);

		JLabel lblNewLabel = new JLabel("Nombre:");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(135, 206, 235), 3, true));
		panel_1.setBounds(270, 182, 101, 51);
		contentPanel.add(panel_1);

		JLabel lblCedula = new JLabel("Cedula:");
		panel_1.add(lblCedula);
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 23));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(135, 206, 235), 3, true));
		panel_2.setBounds(218, 244, 205, 69);
		contentPanel.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(UIManager.getColor("activeCaption"), 3, true));
		panel_3.setBounds(218, 86, 205, 75);
		contentPanel.add(panel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Iniciar Sesion");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (Clinica.getInstance().buscarDoctorByCedula(txtCedula.getPassword().toString()) != null) {
							InterfazDoctor interDoc = new InterfazDoctor();
							interDoc.setVisible(true);
							interDoc.setModal(true);
							interDoc.actualizarTablaConsultas(txtCedula.getPassword().toString());
							//interDoc.NombreDoc(txtCedula.getPassword().toString());
						} else {
							JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña válida.");
						}
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
