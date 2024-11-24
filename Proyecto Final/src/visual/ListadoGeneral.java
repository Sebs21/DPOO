package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
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
	
	private JRadioButton btnPacientes;
	private JRadioButton btnDoctores;
	private JRadioButton btnSeguros;
	private JRadioButton btnConsultas;
	private JRadioButton btnCitas;
	
	private JTable tablePaciente;
	
	private static DefaultTableModel modelo;
	private static DefaultTableModel modelo1;
	private static DefaultTableModel modelo2;
	private static DefaultTableModel modelo3;
	private static DefaultTableModel modelo4;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnCancelar;
	

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
		setBounds(100, 100, 671, 525);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnPacientes = new JRadioButton("Pacientes");
		btnPacientes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnPacientes.setBounds(21, 38, 127, 25);
		contentPanel.add(btnPacientes);
		
		btnDoctores = new JRadioButton("Doctores");
		btnDoctores.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnDoctores.setBounds(152, 38, 127, 25);
		contentPanel.add(btnDoctores);
		
		btnSeguros = new JRadioButton("Seguros");
		btnSeguros.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnSeguros.setBounds(283, 38, 127, 25);
		contentPanel.add(btnSeguros);
		
		btnConsultas = new JRadioButton("Consultas");
		btnConsultas.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnConsultas.setBounds(414, 38, 127, 25);
		contentPanel.add(btnConsultas);
		
		btnCitas = new JRadioButton("Citas");
		btnCitas.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnCitas.setBounds(545, 38, 127, 25);
		contentPanel.add(btnCitas);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 89, 602, 295);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 602, 295);
		panel.add(scrollPane);
		
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
		
		scrollPane.setViewportView(tablePaciente);
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
	}
}
