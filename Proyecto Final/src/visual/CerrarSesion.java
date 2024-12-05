package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CerrarSesion extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CerrarSesion dialog = new CerrarSesion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CerrarSesion() {
		setTitle("Opciones");
		setBounds(100, 100, 578, 238);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		
		JButton btnNewButton = new JButton("Iniciar Sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			InicioSesion iniSe = new InicioSesion();
			iniSe.setVisible(true);
			setModal(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(67, 67, 180, 55);
		contentPanel.add(btnNewButton);
		
		JButton btnRegistrarNuevoUsuario = new JButton("Registrar Nuevo Usuario");
		btnRegistrarNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			RegistrarUsuario regUser = new RegistrarUsuario();
			regUser.setVisible(true);
			setModal(true);
			}
		});
		btnRegistrarNuevoUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnRegistrarNuevoUsuario.setBounds(314, 67, 180, 55);
		contentPanel.add(btnRegistrarNuevoUsuario);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
