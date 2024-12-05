package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.border.LineBorder;

import logico.Clinica;
import logico.User;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class InicioSesion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtPassword;
	private boolean loginsuccesful = false;
	private static final String clinica_info = "Clinica_info.dat";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream clinica = null;
				ObjectInputStream clinicaRead = null;
				FileOutputStream clinica2 = null;
				ObjectOutputStream clinicaWrite = null;

				try {
					clinica = new FileInputStream("Clinica.dat");
					clinicaRead = new ObjectInputStream(clinica);
					Clinica aux = (Clinica) clinicaRead.readObject();
					Clinica.setClinica(aux);
					clinica.close();
					clinicaRead.close();
				} catch (FileNotFoundException e) {
					try {
						clinica2 = new FileOutputStream("Clinica.dat");
						clinicaWrite = new ObjectOutputStream(clinica2);
						User user = new User("Admin", "Admin", "Administrador");
						Clinica.getInstance().agregarUsuario(user);
						clinicaWrite.writeObject(Clinica.getInstance());
						clinicaWrite.close();
						clinica2.close();
					} catch (IOException ex) {

						ex.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

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
		setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setTitle("Inicio de Sesion");
		setBounds(100, 100, 492, 514);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(95, 158, 160), 4, true), "Iniciar Sesion",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(69, 11, 315, 420);
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

		JLabel lblCedula = new JLabel("Contrase\u00F1a:");
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Iniciar Sesion");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (Clinica.getInstance().ConfirmarLogin(txtNombre.getText(), txtPassword.getText())
								|| (txtPassword.equals("Admin") && txtNombre.equals("Admin"))) {
							Principal prin = new Principal();
							loginsuccesful = true;
							dispose();
							
							Clinica clinica = Clinica.cargarClinica();

							if (clinica != null) {
								Clinica.setClinica(clinica);
							}

							prin.setVisible(true);
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

	public boolean isLoginsuccesful() {
		return loginsuccesful;
	}

	public void setLoginsuccesful(boolean loginsuccesful) {
		this.loginsuccesful = loginsuccesful;
	}

}
