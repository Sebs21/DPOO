package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Paciente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Facturacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtIdPaciente;
	private JTextField txtEnfermedad;
	private JTextField txtEdad;
	private JTextField txtSeguro;
	private JTextField txtIdFactura;
	private JTextField txtCedulaPaciente;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Facturacion dialog = new Facturacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Facturacion () 
	{
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setTitle("Facturaci\u00F3n");
		setBounds(100, 100, 619, 347);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cedula paciente:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(23, 31, 107, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblIdFactura = new JLabel("ID Factura:");
		lblIdFactura.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIdFactura.setBounds(23, 60, 93, 16);
		contentPanel.add(lblIdFactura);
		
		txtCedulaPaciente = new JTextField();
		txtCedulaPaciente.setForeground(Color.CYAN);
		txtCedulaPaciente.setBackground(Color.WHITE);
		txtCedulaPaciente.setBounds(142, 28, 116, 22);
		contentPanel.add(txtCedulaPaciente);
		txtCedulaPaciente.setColumns(10);
		
		txtIdFactura = new JTextField();
		txtIdFactura.setForeground(Color.CYAN);
		txtIdFactura.setEditable(false);
		txtIdFactura.setEnabled(false);
		txtIdFactura.setBounds(142, 57, 116, 22);
		contentPanel.add(txtIdFactura);
		txtIdFactura.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNombre.setBounds(23, 89, 67, 16);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(142, 86, 116, 22);
		contentPanel.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblApellido.setBounds(23, 118, 67, 16);
		contentPanel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(142, 115, 116, 22);
		contentPanel.add(txtApellido);
		
		JLabel lblIdPaciente = new JLabel("ID paciente:");
		lblIdPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIdPaciente.setBounds(23, 147, 93, 16);
		contentPanel.add(lblIdPaciente);
		
		txtIdPaciente = new JTextField();
		txtIdPaciente.setEditable(false);
		txtIdPaciente.setColumns(10);
		txtIdPaciente.setBounds(142, 144, 116, 22);
		contentPanel.add(txtIdPaciente);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad:");
		lblEnfermedad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEnfermedad.setBounds(23, 205, 93, 16);
		contentPanel.add(lblEnfermedad);
		
		txtEnfermedad = new JTextField();
		txtEnfermedad.setEditable(false);
		txtEnfermedad.setColumns(10);
		txtEnfermedad.setBounds(142, 202, 116, 22);
		contentPanel.add(txtEnfermedad);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEdad.setBounds(23, 176, 93, 16);
		contentPanel.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setColumns(10);
		txtEdad.setBounds(142, 173, 116, 22);
		contentPanel.add(txtEdad);
		
		JLabel lblSeguro = new JLabel("Seguro:");
		lblSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblSeguro.setBounds(23, 234, 93, 16);
		contentPanel.add(lblSeguro);
		
		txtSeguro = new JTextField();
		txtSeguro.setEditable(false);
		txtSeguro.setColumns(10);
		txtSeguro.setBounds(142, 231, 116, 22);
		contentPanel.add(txtSeguro);
		
		JLabel lblFacturacin = new JLabel("Facturaci\u00F3n:");
		lblFacturacin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblFacturacin.setBounds(380, 31, 131, 16);
		contentPanel.add(lblFacturacin);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//Paciente paciente = Clinica.getInstance();
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setBackground(Color.CYAN);
		btnBuscar.setActionCommand("OK");
		btnBuscar.setBounds(270, 27, 81, 25);
		contentPanel.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnFacturar = new JButton("Facturar");
				btnFacturar.setActionCommand("OK");
				buttonPane.add(btnFacturar);
				getRootPane().setDefaultButton(btnFacturar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
