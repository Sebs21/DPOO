package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.Paciente;
import logico.Seguro;

import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;

public class ListadoGeneral extends JDialog 
{
	private final JPanel contentPanel = new JPanel();
	private Dimension dim;
	
	private static Object[] pacientesRow;
	private static Object[] doctoresRow;
	private static Object[] segurosRow;
	private static Object[] consultasRow;
	private static Object[] citasRow;
	
	/*
	private int indPacienteModificar = -1;
	private int indDoctorModificar = -1;
	private int indConsultaModificar = -1;
	private int indSeguroModificar = -1;
	private int indCitaModificar = -1;
	
	private Paciente selected = null;
	private Doctor selected01 = null;
	private Seguro selected02 = null;
	private Cita selected03 = null;
	private Consulta selected04 = null;
	
	*/ 
	
	private static DefaultTableModel modelo;
	private static DefaultTableModel modelo1;
	private static DefaultTableModel modelo2;
	private static DefaultTableModel modelo3;
	private static DefaultTableModel modelo4;
	
	private JButton btnCancelar;
	
	private JPanel panelDoctores;
	
	private JScrollPane scrollPane_1;
	
	private JTextField txtDoctoresTotales;
	private JTextField txtSegurosTotales;
	private JTextField txtCitasTotales;
	private JTextField txtConsultasTotales;
	private JTextField txtPacientesTotales;
	private JTable tablePaciente;
	private JTable tableDoctor;
	private JTable tableSeguro;
	private JTable tableConsulta;
	private JTable tableCita;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoGeneral dialog = new ListadoGeneral();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoGeneral() 
	{
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setTitle("Listado General");
		setBounds(100, 100, 903, 812);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		dim = getToolkit().getScreenSize();
		setSize( dim.width, dim.height );
		setLocationRelativeTo(null);
		
		JPanel panelPacientes = new JPanel();
		panelPacientes.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 4, true), "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPacientes.setBounds(12, 13, 697, 295);
		contentPanel.add(panelPacientes);
		panelPacientes.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelPacientes.add(scrollPane);
		
		tablePaciente = new JTable();
		scrollPane.setViewportView( tablePaciente );
		
		modelo = new DefaultTableModel();
		String[] identificadores = { "Codigo", "Cedula", "Nombre", "Apellido", "Edad", "Seguro", "Vacuna" };
		modelo.setColumnIdentifiers( identificadores );
		tablePaciente.setModel( modelo );
		
		{
			panelDoctores = new JPanel();
			panelDoctores.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 4, true), "Doctores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelDoctores.setBounds(12, 321, 697, 295);
			contentPanel.add(panelDoctores);
			panelDoctores.setLayout(new BorderLayout(0, 0));
			{
				scrollPane_1 = new JScrollPane();
				panelDoctores.add(scrollPane_1);
				
				tableDoctor = new JTable();
				scrollPane_1.setViewportView(tableDoctor);
				{
					modelo1 = new DefaultTableModel();
					String[] identificadores01 = { "Cedula", "Nombre", "Apellido", "Especialidad" };
					modelo1.setColumnIdentifiers( identificadores01 );
					tableDoctor.setModel( modelo1 );
				}
			}
		}
		
		JPanel panelSeguros = new JPanel();
		panelSeguros.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 4, true), "Seguros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSeguros.setBounds(721, 13, 697, 295);
		contentPanel.add(panelSeguros);
		panelSeguros.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panelSeguros.add(scrollPane_2);
		
		tableSeguro = new JTable();
		scrollPane_2.setViewportView(tableSeguro);
		modelo2 = new DefaultTableModel();
		String[] identificadores02 = { "ID Seguro", "Nombre de Empresa", "Tipo de Seguro" };
		modelo2.setColumnIdentifiers( identificadores02 );
		tableSeguro.setModel( modelo2 );
		
		JPanel panelConsulta = new JPanel();
		panelConsulta.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 4, true), "Consultas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsulta.setBounds(721, 321, 691, 295);
		contentPanel.add(panelConsulta);
		panelConsulta.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panelConsulta.add(scrollPane_3);
		
		tableConsulta = new JTable();
		scrollPane_3.setViewportView(tableConsulta);
		
		modelo3 = new DefaultTableModel();
		String[] identificadores03 = { "ID", "Enfermedad", "Fecha Consulta", "Descripción", "Importancia" };
		modelo3.setColumnIdentifiers( identificadores03 );
		tableConsulta.setModel( modelo3 );
		
		JPanel panelCita = new JPanel();
		panelCita.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 4, true), "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCita.setBounds(359, 643, 768, 336);
		contentPanel.add(panelCita);
		panelCita.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panelCita.add(scrollPane_4);
		
		tableCita = new JTable();
		scrollPane_4.setViewportView(tableCita);
		
		modelo4 = new DefaultTableModel();
		String[] identificadores04 = { "Doctor", "Paciente", "Fecha de Cita" };
		modelo4.setColumnIdentifiers( identificadores04 );
		tableCita.setModel( modelo4 );
		
		JLabel lblNewLabel = new JLabel("Pacientes totales:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(1604, 48, 135, 16);
		contentPanel.add(lblNewLabel);
		
		txtPacientesTotales = new JTextField();
		txtPacientesTotales.setEditable(false);
		txtPacientesTotales.setBounds(1611, 77, 116, 22);
		contentPanel.add(txtPacientesTotales);
		txtPacientesTotales.setColumns(10);
		
		JLabel lblDoctoresTotales = new JLabel("Doctores totales:");
		lblDoctoresTotales.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblDoctoresTotales.setBounds(1604, 124, 135, 16);
		contentPanel.add(lblDoctoresTotales);
		
		txtDoctoresTotales = new JTextField();
		txtDoctoresTotales.setEditable(false);
		txtDoctoresTotales.setColumns(10);
		txtDoctoresTotales.setBounds(1611, 156, 116, 22);
		contentPanel.add(txtDoctoresTotales);
		
		JLabel lblSegurosTotales = new JLabel("Seguros totales:");
		lblSegurosTotales.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblSegurosTotales.setBounds(1604, 205, 135, 16);
		contentPanel.add(lblSegurosTotales);
		
		txtSegurosTotales = new JTextField();
		txtSegurosTotales.setEditable(false);
		txtSegurosTotales.setColumns(10);
		txtSegurosTotales.setBounds(1611, 234, 116, 22);
		contentPanel.add(txtSegurosTotales);
		
		JLabel lblCitasTotales = new JLabel("Citas totales:");
		lblCitasTotales.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblCitasTotales.setBounds(1604, 269, 135, 16);
		contentPanel.add(lblCitasTotales);
		
		txtCitasTotales = new JTextField();
		txtCitasTotales.setEditable(false);
		txtCitasTotales.setColumns(10);
		txtCitasTotales.setBounds(1614, 298, 116, 22);
		contentPanel.add(txtCitasTotales);
		
		JLabel lblConsultasTotales = new JLabel("Consultas totales:");
		lblConsultasTotales.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblConsultasTotales.setBounds(1604, 342, 135, 16);
		contentPanel.add(lblConsultasTotales);
		
		txtConsultasTotales = new JTextField();
		txtConsultasTotales.setEditable(false);
		txtConsultasTotales.setColumns(10);
		txtConsultasTotales.setBounds(1611, 371, 116, 22);
		contentPanel.add(txtConsultasTotales);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		datosPacientes();
		datosDoctor();
		datosSeguro();
		datosConsulta();
		datosCita();
		
	}
	
	public void datosPacientes ()
	{
		modelo.setRowCount(0);
		pacientesRow = new Object[7];
		ArrayList<Paciente> aux = Clinica.getInstance().getMisPacientes();
		
		for ( Paciente paciente : aux )
		{
			pacientesRow[0] = paciente.getIdCodPaciente();
			pacientesRow[1] = paciente.getCedula();
			pacientesRow[2] = paciente.getNombre();
			pacientesRow[3] = paciente.getApellido();
			pacientesRow[4] = paciente.getEdad();
			pacientesRow[5] = paciente.getSeguro();
			pacientesRow[6] = paciente.getMiVacuna();
			
			modelo.addRow( pacientesRow );
			
		}
		
		txtPacientesTotales.setText( String.valueOf( aux.size() ) );
		
	}
	
	public void datosDoctor ()
	{
		modelo1.setRowCount(0);
		doctoresRow = new Object[4];
		ArrayList<Doctor> aux = Clinica.getInstance().getMisDoctores();
		
		for ( Doctor doctor : aux )
		{
			doctoresRow[0] = doctor.getCedula();
			doctoresRow[1] = doctor.getNombre();
			doctoresRow[2] = doctor.getApellido();
			doctoresRow[3] = doctor.getEspecialidad();
			
			modelo1.addRow( doctoresRow );
			
		}
		
		txtDoctoresTotales.setText( String.valueOf( aux.size() ) );
		
	}
	
	public void datosSeguro ()
	{
		modelo2.setRowCount(0);
		segurosRow = new Object[3];
		ArrayList<Seguro> aux = Clinica.getInstance().getMisSeguros();
		
		for ( Seguro seguro : aux ) 
		{
			segurosRow[0] = seguro.getIdSeguro();
			segurosRow[1] = seguro.getNombreEmpresa();
			segurosRow[2] = seguro.getTipoDeSeguro();
			
			modelo2.addRow( segurosRow );
			
		}
		
		txtSegurosTotales.setText( String.valueOf( aux.size() ) );
		
	}
	
	public void datosConsulta ()
	{
		modelo3.setRowCount(0);
		consultasRow = new Object[5];
		ArrayList<Consulta> aux = Clinica.getInstance().getMisConsultas();
		
		for ( Consulta consulta : aux )
		{
			consultasRow[0] = consulta.getId();
			consultasRow[1] = consulta.getEnfermedad();
			consultasRow[2] = consulta.getFechaConsulta();
			consultasRow[3] = consulta.getDescripcion();
			consultasRow[4] = consulta.isImportancia();
			
			modelo3.addRow( consultasRow );
			
		}
	
		txtConsultasTotales.setText( String.valueOf( aux.size() ) );
		
	}
	
	public void datosCita ()
	{
		modelo4.setRowCount(0);
		citasRow = new Object[3];
		ArrayList<Cita> aux = Clinica.getInstance().getMisCitas();
		
		for ( Cita cita : aux ) 
		{
			citasRow[0] = cita.getDoctor();
			citasRow[1] = cita.getPaciente();
			citasRow[2] = cita.getFechaCita();
			
			modelo4.addRow( citasRow );
			
		}
		
		txtCitasTotales.setText( String.valueOf( aux.size() ) );
		
	}
}
