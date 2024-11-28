package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Paciente;
import logico.Seguro;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class SeguroPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdSeguro;
	private JTextField txtNombreEmpresa;
	private JTextField txtIdPaciente;
	private JButton btnNo;
	private JButton btnBuscar;
	private JButton btnSi;
	private JComboBox cbxTipoDeSeguro;
	private JButton btnConectar;
	private JButton btnCancelar;

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
		setBounds(100, 100, 529, 583);
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
		lblNombreDeLa.setBounds(171, 257, 211, 16);
		contentPanel.add(lblNombreDeLa);
		
		JLabel lblTipoDeSeguro = new JLabel("Tipo de seguro:");
		lblTipoDeSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTipoDeSeguro.setBounds(180, 390, 148, 16);
		contentPanel.add(lblTipoDeSeguro);
		
		txtIdSeguro = new JTextField();
		txtIdSeguro.setEditable(false);
		txtIdSeguro.setEnabled(false);
		txtIdSeguro.setBounds(138, 169, 244, 44);
		contentPanel.add(txtIdSeguro);
		txtIdSeguro.setColumns(10);
		
		txtNombreEmpresa = new JTextField();
		txtNombreEmpresa.setEditable(false);
		txtNombreEmpresa.setEnabled(false);
		txtNombreEmpresa.setColumns(10);
		txtNombreEmpresa.setBounds(138, 307, 244, 44);
		contentPanel.add(txtNombreEmpresa);
		
		JLabel lblIdPaciente = new JLabel("Cedula paciente:");
		lblIdPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblIdPaciente.setBounds(28, 35, 141, 30);
		contentPanel.add(lblIdPaciente);
		
		txtIdPaciente = new JTextField();
		txtIdPaciente.setBounds(181, 41, 116, 22);
		contentPanel.add(txtIdPaciente);
		txtIdPaciente.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Paciente paciente = Clinica.getInstance().buscarPacienteByCedula( txtIdPaciente.getText() );
				
				if ( paciente != null )
				{
					btnSi.setEnabled( true );
					btnNo.setEnabled( true );
				}
				else
				{
					JOptionPane.showMessageDialog( null, "Ingresa la cedula denuevo." );
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
				JOptionPane.showMessageDialog( null,  "No tienes seguro, se te cobrará sin cubrimiento." );
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
				txtNombreEmpresa.setEnabled( true );
				txtNombreEmpresa.setEditable( true );
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
		
		cbxTipoDeSeguro = new JComboBox();
		cbxTipoDeSeguro.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione Seguro >", "Seguro de Responsabilidad M\u00E9dica", "Equipo Medico", "Salud para pacientes"}));
		cbxTipoDeSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		cbxTipoDeSeguro.setEnabled(false);
		cbxTipoDeSeguro.setBounds(138, 436, 244, 22);
		contentPanel.add(cbxTipoDeSeguro);
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
							Seguro seguro = new Seguro ( txtIdSeguro.getText(), txtNombreEmpresa.getText(), cbxTipoDeSeguro.getSelectedItem().toString() );
							Clinica.getInstance().agregarSeguro( seguro );
							JOptionPane.showMessageDialog( null, "Seguro añadido a su sesión." );
							clean();
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
		txtIdPaciente.setText("");
		txtIdSeguro.setText("");
		txtNombreEmpresa.setText("");
		cbxTipoDeSeguro.setSelectedItem( "<Seleccione Seguro>");
	}
	
}
