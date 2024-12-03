package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Paciente;
import logico.User;
import logico.vacunacion;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;

public class RegistroPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEdad;
	private JTextField txtSeguro;
	private JTextField txtCedula;
	//
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroPaciente dialog = new RegistroPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroPaciente() {
		setTitle("Registro Paciente");
		setBounds(100, 100, 602, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 128, 128), 4, true), "Informaci\u00F3n paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 566, 296);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel.setBounds(26, 39, 108, 31);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Apellido:");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_1.setBounds(294, 39, 108, 31);
			panel.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(160, 40, 108, 31);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(428, 40, 108, 31);
			panel.add(txtApellido);
			
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblEdad.setBounds(26, 128, 108, 31);
			panel.add(lblEdad);
			
			txtEdad = new JTextField();
			txtEdad.setColumns(10);
			txtEdad.setBounds(160, 129, 108, 31);
			panel.add(txtEdad);
			
			JLabel lblSeguro = new JLabel("Seguro:");
			lblSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblSeguro.setBounds(294, 128, 108, 31);
			panel.add(lblSeguro);
			
			txtSeguro = new JTextField();
			txtSeguro.setColumns(10);
			txtSeguro.setBounds(428, 129, 108, 31);
			panel.add(txtSeguro);
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblCedula.setBounds(294, 216, 108, 31);
			panel.add(lblCedula);
			
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(428, 216, 108, 31);
			panel.add(txtCedula);
			
			JLabel lblNewLabel_2 = new JLabel("Su nombre y cedula ser\u00E1 su contrase\u00F1a para iniciar sesi\u00F3n");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			lblNewLabel_2.setForeground(Color.RED);
			lblNewLabel_2.setBounds(77, 271, 411, 14);
			panel.add(lblNewLabel_2);
			
			JLabel label = new JLabel("Vacunado:");
			label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			label.setBounds(26, 216, 68, 31);
			panel.add(label);
			
			JButton btnSi = new JButton("Si");
			btnSi.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					
				}
			});
			btnSi.setActionCommand("OK");
			btnSi.setBounds(100, 219, 43, 25);
			panel.add(btnSi);
			
			JButton btnNo = new JButton("No");
			btnNo.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					
				}
			});
			btnNo.setActionCommand("OK");
			btnNo.setBounds(146, 219, 53, 25);
			panel.add(btnNo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						Clinica.getInstance();
						Paciente paciente = new Paciente(txtCedula.getText(),txtNombre.getText(),txtApellido.getText(),Clinica.idPersona );
						User aux = new User(txtNombre.getText(),txtCedula.getText(),"Paciente");
					
						Clinica.getInstance().agregarPaciente(paciente);
						paciente.setUser(aux);
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtEdad() {
		return txtEdad;
	}

	public void setTxtEdad(JTextField txtEdad) {
		this.txtEdad = txtEdad;
	}

	public JTextField getTxtSeguro() {
		return txtSeguro;
	}

	public void setTxtSeguro(JTextField txtSeguro) {
		this.txtSeguro = txtSeguro;
	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JTextField txtCedula) {
		this.txtCedula = txtCedula;
	}
}
