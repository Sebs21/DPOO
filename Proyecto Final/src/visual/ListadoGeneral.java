package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoGeneral extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	private Dimension dim;
	
	private static Object[] pacientesRow;
	private static Object[] doctoresRow;
	private static Object[] segurosRow;
	private static Object[] consultasRow;
	private static Object[] citasRow;
	
	private int indPacienteEliminar = -1;
	private int indPacienteModificar = -1;
	
	private int indDoctorEliminar = -1;
	private int indDoctorModificar = -1;
	
	private int indConsultaEliminar = -1;
	private int indConsultaModificar = -1;
	
	private int indSeguroEliminar = -1;
	private int indSeguroModificar = -1;
	
	private int indCitaEliminar = -1;
	private int indCitaModificar = -1; 
	
	private JTable tablePaciente;
	
	private static DefaultTableModel modelo;
	private static DefaultTableModel modelo1;
	private static DefaultTableModel modelo2;
	private static DefaultTableModel modelo3;
	private static DefaultTableModel modelo4;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JPanel panelDoctores;
	private JScrollPane scrollPane_1;
	private JTable tableDoctores;
	private JTable tableConsulta;
	private JTable tableCita;
	private JTable tableSeguro;
	

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
	public ListadoGeneral() {
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
		panelPacientes.setBounds(12, 13, 697, 295);
		contentPanel.add(panelPacientes);
		panelPacientes.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 697, 295);
		panelPacientes.add(scrollPane);
		
		tablePaciente = new JTable();
		tablePaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				indPacienteEliminar = tablePaciente.getSelectedRow();
				indPacienteModificar = tablePaciente.getSelectedRow();
				
				if ( indPacienteEliminar >= 0 )
				{
					btnEliminar.setEnabled( true );
					btnModificar.setEnabled( true );
				}
				else
				{
					btnEliminar.setEnabled( false );
					btnModificar.setEnabled( false );
				}
				
			}
		});
		
		tablePaciente.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		modelo = new DefaultTableModel();
		String[] identificadores = { "Codigo", "Cedula", "Nombre", "Apellido", "Edad", "Seguro", "Vacuna" };
		modelo.setColumnIdentifiers( identificadores );
		tablePaciente.setModel( modelo );
		
		scrollPane.setViewportView(tablePaciente);
		{
			panelDoctores = new JPanel();
			panelDoctores.setBounds(12, 321, 697, 295);
			contentPanel.add(panelDoctores);
			panelDoctores.setLayout(null);
			{
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(0, 0, 697, 295);
				panelDoctores.add(scrollPane_1);
				{
					tableDoctores = new JTable();
					tableDoctores.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) 
						{
							
							indDoctorEliminar = tableDoctores.getSelectedRow();
							indDoctorModificar = tableDoctores.getSelectedRow();
							
							if ( indDoctorEliminar >= 0 )
							{
								btnEliminar.setEnabled( true );
								btnModificar.setEnabled( true );
							}
							else
							{
								btnEliminar.setEnabled( false );
								btnModificar.setEnabled( false );
							}
						}
					});
					
					tableDoctores.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
					modelo1 = new DefaultTableModel();
					String[] identificadores01 = { "Cedula", "Nombre", "Apellido", "Especialidad" };
					modelo1.setColumnIdentifiers( identificadores01 );
					tablePaciente.setModel( modelo1 );
					scrollPane_1.setViewportView( tableDoctores );
				}
			}
		}
		
		JPanel panelSeguros = new JPanel();
		panelSeguros.setBounds(721, 13, 697, 295);
		contentPanel.add(panelSeguros);
		panelSeguros.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 697, 295);
		panelSeguros.add(scrollPane_2);
		
		tableSeguro = new JTable();
		tableSeguro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
				indSeguroEliminar = tableSeguro.getSelectedRow();
				indSeguroModificar = tableSeguro.getSelectedRow();
				
				if ( indSeguroEliminar >= 0 )
				{
					btnEliminar.setEnabled( true );
					btnModificar.setEnabled( true );
				}
				else
				{
					btnEliminar.setEnabled( false );
					btnModificar.setEnabled( false );
				}
			}
		});
		
		scrollPane_2.setViewportView( tableSeguro );
		modelo2 = new DefaultTableModel();
		String[] identificadores02 = { "Cedula", "Nombre", "Apellido", "Especialidad" };
		modelo2.setColumnIdentifiers( identificadores02 );
		tablePaciente.setModel( modelo2 );
		
		JPanel panelConsulta = new JPanel();
		panelConsulta.setBounds(721, 321, 691, 295);
		contentPanel.add(panelConsulta);
		panelConsulta.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 691, 295);
		panelConsulta.add(scrollPane_3);
		
		tableConsulta = new JTable();
		tableConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				indConsultaEliminar = tableConsulta.getSelectedRow();
				indConsultaModificar = tableConsulta.getSelectedRow();
				
				if ( indConsultaEliminar >= 0 )
				{
					btnEliminar.setEnabled( true );
					btnModificar.setEnabled( true );
				}
				else
				{
					btnEliminar.setEnabled( false );
					btnModificar.setEnabled( false );
				}
			}
		});
		
		modelo3 = new DefaultTableModel();
		String[] identificadores03 = { "ID", "Enfermedad", "Fecha Consulta", "Descripción", "Importancia" };
		modelo3.setColumnIdentifiers( identificadores03 );
		tableConsulta.setModel( modelo3 );
		scrollPane_3.setViewportView(tableConsulta);
		
		JPanel panelCita = new JPanel();
		panelCita.setBounds(359, 643, 768, 336);
		contentPanel.add(panelCita);
		panelCita.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 0, 768, 336);
		panelCita.add(scrollPane_4);
		
		tableCita = new JTable();
		tableCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
				indCitaEliminar = tableCita.getSelectedRow();
				indCitaModificar = tableCita.getSelectedRow();
				
				if ( indCitaEliminar >= 0 )
				{
					btnEliminar.setEnabled( true );
					btnModificar.setEnabled( true );
				}
				else
				{
					btnEliminar.setEnabled( false );
					btnModificar.setEnabled( false );
				}
			}
		});
		
		modelo4 = new DefaultTableModel();
		String[] identificadores04 = { "" };
		modelo4.setColumnIdentifiers( identificadores04 );
		tableCita.setModel( modelo4 );
		scrollPane_4.setViewportView(tableCita);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				btnCancelar = new JButton("Cancelar");
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
		pacientesRow = new Object[tablePaciente.getColumnCount()];
		
		for ( Paciente paciente : Clinica.getInstance().pacientesSeleccionados() )
		{
			pacientesRow[0] = paciente.getIdCodPaciente();
			pacientesRow[1] = paciente.getCedula();
			pacientesRow[2] = paciente.getNombre();
			pacientesRow[3] = paciente.getApellido();
			pacientesRow[4] = paciente.getEdad();
			pacientesRow[5] = paciente.getSeguro();
			pacientesRow[6] = paciente.getMiVacuna();
		}
		
	}
	
	public void datosDoctor ()
	{
		modelo1.setRowCount(0);
		doctoresRow = new Object[tableDoctores.getColumnCount()];
		
		for ( Doctor doctor : Clinica.getInstance().doctoresSeleccionados() )
		{
			doctoresRow[0] = doctor.getCedula();
			doctoresRow[1] = doctor.getNombre();
			doctoresRow[2] = doctor.getApellido();
			doctoresRow[3] = doctor.getEspecialidad();
		}
	}
	
	public void datosSeguro ()
	{
		modelo2.setRowCount(0);
		segurosRow = new Object[tableSeguro.getColumnCount()];
		
		for ( Seguro seguro : Clinica.getInstance().segurosSeleccionados() )
		{
			segurosRow[0] = seguro.getIdSeguro();
			segurosRow[1] = seguro.getNombreEmpresa();
			segurosRow[2] = seguro.getTipoDeSeguro();
			segurosRow[3] = seguro.getPago();
		}
		
	}
	
	public void datosConsulta ()
	{
		modelo3.setRowCount(0);
		consultasRow = new Object[tableConsulta.getColumnCount()];
		
		for ( Consulta consulta : Clinica.getInstance().consultasSeleccionadas() )
		{
			consultasRow[0] = consulta.getId();
			consultasRow[1] = consulta.getEnfermedad();
			consultasRow[2] = consulta.getFechaConsulta();
			consultasRow[3] = consulta.getDescripcion();
			consultasRow[4] = consulta.isImportancia();
		}
		
	}
	
	public void datosCita ()
	{
		modelo4.setRowCount(0);
		citasRow = new Object[tableCita.getColumnCount()];
		
		for ( Cita cita : Clinica.getInstance().citasSeleccionadas() ) 
		{
			citasRow[0] = cita.getDoctor();
			citasRow[1] = cita.getPersona();
			citasRow[2] = cita.getFechaCita();
		}
		
	}
	
	public void clean ()
	{
		
	}
}
