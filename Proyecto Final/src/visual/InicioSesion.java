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
import javax.swing.border.TitledBorder;

public class InicioSesion extends JDialog {

	/**
	 * 
	 *///
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
	public InicioSesion() 
	{
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setTitle("Inicio de Sesion");
		setBounds(100, 100, 1202, 514);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(95, 158, 160), 4, true), "Iniciar Sesion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(69, 11, 315, 420);
		contentPanel.add(panel_4);
		panel_4.setLayout(null);
		
				txtNombre = new JTextField();
				txtNombre.setBounds(67, 134, 171, 52);
				panel_4.add(txtNombre);
				txtNombre.setColumns(10);
				
						txtCedula = new JPasswordField();
						txtCedula.setBounds(67, 291, 171, 52);
						panel_4.add(txtCedula);
						
								JPanel panel = new JPanel();
								panel.setBounds(102, 61, 101, 52);
								panel_4.add(panel);
								panel.setBorder(new LineBorder(UIManager.getColor("activeCaption"), 3, true));
								
										JLabel lblNewLabel = new JLabel("Nombre:");
										panel.add(lblNewLabel);
										lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
										
												JPanel panel_1 = new JPanel();
												panel_1.setBounds(102, 220, 101, 51);
												panel_4.add(panel_1);
												panel_1.setBorder(new LineBorder(new Color(135, 206, 235), 3, true));
												
														JLabel lblCedula = new JLabel("Cedula:");
														panel_1.add(lblCedula);
														lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 23));
														
																JPanel panel_2 = new JPanel();
																panel_2.setBounds(50, 282, 205, 69);
																panel_4.add(panel_2);
																panel_2.setBorder(new LineBorder(new Color(135, 206, 235), 3, true));
																
																		JPanel panel_3 = new JPanel();
																		panel_3.setBounds(50, 124, 205, 75);
																		panel_4.add(panel_3);
																		panel_3.setBorder(new LineBorder(UIManager.getColor("activeCaption"), 3, true));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 4, true), "Registrar Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(453, 117, 332, 207);
		contentPanel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JButton btnRegistrar = new JButton("Registrar un nuevo Doctor");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroDoctor regisDoc = new RegistroDoctor();
				regisDoc.setVisible(true);
				regisDoc.setModal(true);
			}
		});
		btnRegistrar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		panel_5.add(btnRegistrar);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 4, true), "Registrar Seguro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(797, 117, 332, 207);
		contentPanel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JButton btnRegistrarSuSeguro = new JButton("Registrar su Seguro Medico");
		btnRegistrarSuSeguro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				SeguroPaciente seguro = new SeguroPaciente ();
				seguro.setVisible( true );
				seguro.setModal( true );
			}
		});
		btnRegistrarSuSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		panel_6.add(btnRegistrarSuSeguro, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Iniciar Sesion");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String cedula = new String(txtCedula.getPassword());
						InterfazDoctor interDoc = new InterfazDoctor();
						if (Clinica.getInstance().buscarDoctorByCedula(cedula) != null) {
							interDoc.setVisible(true);
							interDoc.setModal(true);
							interDoc.actualizarTablaConsultas(cedula);
							
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

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JPasswordField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JPasswordField txtCedula) {
		this.txtCedula = txtCedula;
	}
}
