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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class Facturacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private boolean existePaciente;
	private Paciente updated = null;
	private JTextField txtPrecio;
	private JTextField txtPrecioSeguro;
	private JTextField txtPrecioTotalPagar;
	private JTextField txtPagoPaciente;

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
		setBounds(100, 100, 635, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);
		
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
		txtIdFactura.setText("F - " +Clinica.getInstance().idFactura );
		
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
		btnBuscar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Paciente paciente = Clinica.getInstance().buscarPacienteByCedula( txtCedulaPaciente.getText() );
				
				if ( paciente != null )
				{
					txtNombre.setText( paciente.getNombre() );
					txtApellido.setText( paciente.getApellido() );
					txtIdPaciente.setText( String.valueOf( paciente.getIdCodPaciente() ) );
					txtEdad.setText( String.valueOf(paciente.getEdad() ) );
					txtEnfermedad.setText( paciente.getEnfermedad() );
					txtSeguro.setText( paciente.getSeguro().getTipoDeSeguro() );
					existePaciente = true;
				}
				else
				{
					JOptionPane.showMessageDialog( null, "Su ID no ha sido encontrado, inicie sesión nuevamente. " );
					existePaciente = false;
					dispose();
				}
				
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setBackground(Color.CYAN);
		btnBuscar.setActionCommand("OK");
		btnBuscar.setBounds(270, 27, 81, 25);
		contentPanel.add(btnBuscar);
		
		JLabel lblNewLabel_1 = new JLabel("Precio consulta:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_1.setBounds(381, 73, 142, 16);
		contentPanel.add(lblNewLabel_1);
		
		txtPrecio = new JTextField();
		txtPrecio.setEnabled(false);
		txtPrecio.setEditable(false);
		txtPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtPrecio.setBounds(317, 104, 241, 22);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Porciento aplicado por el seguro:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_2.setBounds(317, 139, 241, 16);
		contentPanel.add(lblNewLabel_2);
		
		txtPrecioSeguro = new JTextField();
		txtPrecioSeguro.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtPrecioSeguro.setEnabled(false);
		txtPrecioSeguro.setEditable(false);
		txtPrecioSeguro.setColumns(10);
		txtPrecioSeguro.setBounds(317, 169, 241, 22);
		contentPanel.add(txtPrecioSeguro);
		
		JLabel lblPrecioAPagar = new JLabel("Precio a pagar:");
		lblPrecioAPagar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblPrecioAPagar.setBounds(317, 204, 124, 20);
		contentPanel.add(lblPrecioAPagar);
		
		txtPrecioTotalPagar = new JTextField();
		txtPrecioTotalPagar.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtPrecioTotalPagar.setEnabled(false);
		txtPrecioTotalPagar.setEditable(false);
		txtPrecioTotalPagar.setColumns(10);
		txtPrecioTotalPagar.setBounds(317, 231, 116, 22);
		contentPanel.add(txtPrecioTotalPagar);
		
		JLabel lblPago = new JLabel("Pago del paciente:");
		lblPago.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblPago.setBounds(445, 202, 131, 20);
		contentPanel.add(lblPago);
		
		txtPagoPaciente = new JTextField();
		txtPagoPaciente.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtPagoPaciente.setColumns(10);
		txtPagoPaciente.setBounds(445, 231, 116, 22);
		contentPanel.add(txtPagoPaciente);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblFecha.setBounds(23, 263, 93, 16);
		contentPanel.add(lblFecha);
		
		JTextField txtFecha = new JTextField();
		txtFecha.setText("");
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(142, 266, 116, 22);
		contentPanel.add(txtFecha);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnFacturar = new JButton("Facturar");
				btnFacturar.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						
					}
				});
				btnFacturar.setBackground(Color.CYAN);
				btnFacturar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnFacturar.setActionCommand("OK");
				buttonPane.add(btnFacturar);
				getRootPane().setDefaultButton(btnFacturar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						dispose();
					}
				});
				btnCancelar.setBackground(Color.CYAN);
				btnCancelar.setForeground(Color.BLACK);
				btnCancelar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		loadDatosPaciente();
		
	}
	
	private void loadDatosPaciente ()
	{
		if ( updated != null )
		{
			txtCedulaPaciente.setText( updated.getCedula() );
			
		}
	}
	
	private void clean ()
	{
		txtIdPaciente.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEdad.setText("");
		txtEnfermedad.setText("");
		txtSeguro.setText("");
		txtIdFactura.setText(" F - " +Clinica.getInstance().idFactura );
	}
}
