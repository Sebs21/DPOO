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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import logico.Clinica;
import logico.Paciente;
import logico.Seguro;

public class SeguroPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdSeguro;
	private JTextField txtCedulaPaciente;
	private JButton btnNo;
	private JButton btnBuscar;
	private JButton btnSi;
	private JComboBox<String> cbxTipoDeSeguro;
	private JButton btnConectar;
	private JButton btnCancelar;
	private JComboBox<String> cbxNombreEmpresa;
	//
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeguroPaciente dialog = new SeguroPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeguroPaciente() 
	{
		
		setTitle("Seguro del paciente");
		setBounds(100, 100, 529, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID seguro:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(198, 126, 117, 30);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la empresa:");
		lblNombreDeLa.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNombreDeLa.setBounds(158, 224, 211, 16);
		contentPanel.add(lblNombreDeLa);
		
		JLabel lblTipoDeSeguro = new JLabel("Tipo de seguro:");
		lblTipoDeSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTipoDeSeguro.setBounds(181, 307, 148, 16);
		contentPanel.add(lblTipoDeSeguro);
		
		txtIdSeguro = new JTextField();
		txtIdSeguro.setEditable(false);
		txtIdSeguro.setEnabled(false);
		txtIdSeguro.setBounds(138, 169, 237, 30);
		contentPanel.add(txtIdSeguro);
		txtIdSeguro.setColumns(10);
		
		JLabel lblIdPaciente = new JLabel("Cedula paciente:");
		lblIdPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblIdPaciente.setBounds(28, 35, 141, 30);
		contentPanel.add(lblIdPaciente);
		
		txtCedulaPaciente = new JTextField();
		txtCedulaPaciente.setBounds(181, 41, 116, 22);
		contentPanel.add(txtCedulaPaciente);
		txtCedulaPaciente.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Paciente paciente = Clinica.getInstance().buscarPacienteByCedula( txtCedulaPaciente.getText() );
				
				if ( paciente != null )
				{
					btnSi.setEnabled( true );
					btnNo.setEnabled( true );
				}
				else
				{
					JOptionPane.showMessageDialog( null, "Ingresa la cedula denuevo." );
					clearCedula();
				}
				
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnBuscar.setBackground(Color.CYAN);
		btnBuscar.setActionCommand("OK");
		btnBuscar.setBounds(309, 40, 83, 25);
		contentPanel.add(btnBuscar);
		
		JLabel lblPoseeSeguro = new JLabel("\u00BFContiene seguro?");
		lblPoseeSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblPoseeSeguro.setBounds(28, 83, 165, 30);
		contentPanel.add(lblPoseeSeguro);
		
		btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog( null,  "No tienes seguro, se te cobrar� sin cubrimiento." );
				dispose();
			}
		});
		btnNo.setEnabled(false);
		btnNo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnNo.setBackground(Color.CYAN);
		btnNo.setActionCommand("OK");
		btnNo.setBounds(262, 88, 52, 25);
		contentPanel.add(btnNo);
		
		btnSi = new JButton("Si");
		btnSi.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtIdSeguro.setEnabled( true );
				txtIdSeguro.setEditable( true );
				cbxNombreEmpresa.setEnabled( true );
				cbxNombreEmpresa.setEditable( true );
				cbxTipoDeSeguro.setEnabled( true );
				cbxTipoDeSeguro.setEditable( true );
				btnConectar.setEnabled( true );
				
			}
		});
		btnSi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnSi.setEnabled(false);
		btnSi.setBackground(Color.CYAN);
		btnSi.setActionCommand("OK");
		btnSi.setBounds(198, 88, 52, 25);
		contentPanel.add(btnSi);
		
		cbxTipoDeSeguro = new JComboBox<String>();
		cbxTipoDeSeguro.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione Seguro >", "Seguro de Responsabilidad M\u00E9dica", "Equipo Medico", "Salud para pacientes"}));
		cbxTipoDeSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		cbxTipoDeSeguro.setEnabled(false);
		cbxTipoDeSeguro.setBounds(133, 336, 259, 22);
		contentPanel.add(cbxTipoDeSeguro);
		
		cbxNombreEmpresa = new JComboBox<String>();
		cbxNombreEmpresa.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione Nombre de Empresa >", "ARS Humano", "ARS Universal", "Seguro Reservas"}));
		cbxNombreEmpresa.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		cbxNombreEmpresa.setEnabled(false);
		cbxNombreEmpresa.setBounds(133, 253, 254, 22);
		contentPanel.add(cbxNombreEmpresa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnConectar = new JButton("Conectar");
				btnConectar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if ( btnConectar == null )
						{
							Seguro seguro = new Seguro ( txtIdSeguro.getText(), cbxNombreEmpresa.getSelectedItem().toString(), cbxTipoDeSeguro.getSelectedItem().toString() );
							Clinica.getInstance().agregarSeguro( seguro );
							JOptionPane.showMessageDialog( null, "Seguro a�adido a su sesi�n." );
							dispose();
						}
					}
				});
				btnConectar.setEnabled(false);
				btnConectar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnConectar.setBackground(Color.CYAN);
				btnConectar.setActionCommand("OK");
				buttonPane.add(btnConectar);
				getRootPane().setDefaultButton(btnConectar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
				});
				btnCancelar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnCancelar.setBackground(Color.CYAN);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	public void clean ()
	{
		txtCedulaPaciente.setText("");
		txtIdSeguro.setText("");
		cbxNombreEmpresa.setSelectedItem( " < Seleccione Nombre de Empresa > " );
		cbxTipoDeSeguro.setSelectedItem( " < Seleccione Seguro > ");
	}
	
	public void clearCedula ()
	{
		txtCedulaPaciente.setText("");
	}
	
}
