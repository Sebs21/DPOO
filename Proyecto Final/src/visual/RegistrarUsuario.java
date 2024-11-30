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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class RegistrarUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtCedula;
	private JTextField txtConfirmarCedula;

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
		setBounds(100, 100, 640, 405);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JComboBox cbxTipos = new JComboBox();
		cbxTipos.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Doctor", "Paciente"}));
		cbxTipos.setBounds(124, 208, 110, 34);
		contentPanel.add(cbxTipos);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblTipoDeUsuario.setBounds(124, 163, 110, 34);
		contentPanel.add(lblTipoDeUsuario);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(175, 238, 238), 4, true), "Informaci\u00F3n usuario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 604, 311);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(112, 103, 110, 34);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(112, 58, 110, 34);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		
		txtCedula = new JTextField();
		txtCedula.setBounds(345, 103, 141, 34);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblIngresarContrasea = new JLabel("Ingresar Cedula:");
		lblIngresarContrasea.setBounds(345, 58, 141, 34);
		panel.add(lblIngresarContrasea);
		lblIngresarContrasea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		
		txtConfirmarCedula = new JTextField();
		txtConfirmarCedula.setBounds(345, 198, 141, 34);
		panel.add(txtConfirmarCedula);
		txtConfirmarCedula.setColumns(10);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Cedula:");
		lblConfirmarContrasea.setBounds(345, 148, 141, 34);
		panel.add(lblConfirmarContrasea);
		lblConfirmarContrasea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Su contrase\u00F1a sera su cedula de identificaci\u00F3n");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(177, 260, 249, 40);
		panel.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(112, 103, 86, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(340, 103, 86, 20);
		panel.add(txtCedula);
		
		txtConfirmarCedula = new JTextField();
		txtConfirmarCedula.setColumns(10);
		txtConfirmarCedula.setBounds(340, 193, 86, 20);
		panel.add(txtConfirmarCedula);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
}
