package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.Paciente;
import logico.Seguro;

public class ListadoGeneral extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private DefaultTableModel modeloPacientes;
	private DefaultTableModel modeloDoctores;
	private DefaultTableModel modeloSeguros;
	private DefaultTableModel modeloConsultas;
	private DefaultTableModel modeloCitas;
	
	private JTable tablePaciente;
	private JTable tableDoctor;
	private JTable tableSeguro;
	private JTable tableConsulta;
	private JTable tableCita;

	public ListadoGeneral() 
	{
		setTitle("Listado General del Sistema");
		setBounds(100, 100, 1450, 850);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
        setModal(true);
		
		JPanel panelPacientes = new JPanel();
		panelPacientes.setBorder(new TitledBorder(null, "Pacientes Registrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPacientes.setBounds(12, 13, 697, 350);
		contentPanel.add(panelPacientes);
		panelPacientes.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelPacientes.add(scrollPane);
		
		tablePaciente = new JTable();
		scrollPane.setViewportView(tablePaciente);
		
		modeloPacientes = new DefaultTableModel();
		String[] identificadoresPacientes = { "Codigo", "Cedula", "Nombre", "Apellido", "Edad", "Seguro", "Vacunas Recibidas" };
		modeloPacientes.setColumnIdentifiers(identificadoresPacientes);
		tablePaciente.setModel(modeloPacientes);
		
		JPanel panelDoctores = new JPanel();
		panelDoctores.setBorder(new TitledBorder(null, "Doctores Registrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDoctores.setBounds(12, 380, 697, 350);
		contentPanel.add(panelDoctores);
		panelDoctores.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelDoctores.add(scrollPane_1);
		
		tableDoctor = new JTable();
		scrollPane_1.setViewportView(tableDoctor);
		
		modeloDoctores = new DefaultTableModel();
		String[] identificadoresDoctores = { "Cedula", "Nombre", "Apellido", "Especialidad" };
		modeloDoctores.setColumnIdentifiers(identificadoresDoctores);
		tableDoctor.setModel(modeloDoctores);
		
		JPanel panelSeguros = new JPanel();
		panelSeguros.setBorder(new TitledBorder(null, "Seguros Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSeguros.setBounds(721, 13, 697, 150);
		contentPanel.add(panelSeguros);
		panelSeguros.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panelSeguros.add(scrollPane_2);
		
		tableSeguro = new JTable();
		scrollPane_2.setViewportView(tableSeguro);
		modeloSeguros = new DefaultTableModel();
		String[] identificadoresSeguros = { "ID Seguro", "Nombre de Empresa", "Tipo de Seguro", "Descuento" };
		modeloSeguros.setColumnIdentifiers(identificadoresSeguros);
		tableSeguro.setModel(modeloSeguros);
		
		JPanel panelConsulta = new JPanel();
		panelConsulta.setBorder(new TitledBorder(null, "Historial de Consultas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConsulta.setBounds(721, 175, 691, 280);
		contentPanel.add(panelConsulta);
		panelConsulta.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panelConsulta.add(scrollPane_3);
		
		tableConsulta = new JTable();
		scrollPane_3.setViewportView(tableConsulta);
		
		modeloConsultas = new DefaultTableModel();
		String[] identificadoresConsultas = { "ID", "Paciente", "Enfermedad", "Fecha", "Importancia" };
		modeloConsultas.setColumnIdentifiers(identificadoresConsultas);
		tableConsulta.setModel(modeloConsultas);
		
		JPanel panelCita = new JPanel();
		panelCita.setBorder(new TitledBorder(null, "Historial de Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCita.setBounds(721, 467, 691, 263);
		contentPanel.add(panelCita);
		panelCita.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panelCita.add(scrollPane_4);
		
		tableCita = new JTable();
		scrollPane_4.setViewportView(tableCita);
		
		modeloCitas = new DefaultTableModel();
		String[] identificadoresCitas = { "Doctor", "Paciente", "Fecha de Cita", "Estado" };
		modeloCitas.setColumnIdentifiers(identificadoresCitas);
		tableCita.setModel(modeloCitas);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnCancelar = new JButton("Cerrar");
		btnCancelar.addActionListener(e -> dispose());
		buttonPane.add(btnCancelar);
		
		cargarTodosLosDatos();
	}
	
	private void cargarTodosLosDatos() {
		datosPacientes();
		datosDoctores();
		datosSeguros();
		datosConsultas();
		datosCitas();
	}
	
	public void datosPacientes() {
		modeloPacientes.setRowCount(0);
		ArrayList<Paciente> pacientes = Clinica.getInstance().getMisPacientes();
		
		for (Paciente paciente : pacientes) {
			Object[] fila = new Object[7];
			fila[0] = paciente.getIdCodPaciente();
			fila[1] = paciente.getCedula();
			fila[2] = paciente.getNombre();
			fila[3] = paciente.getApellido();
			fila[4] = paciente.getEdad();
            // <-- CAMBIO: Se obtiene el nombre de la empresa del seguro, o "N/A" si no tiene.
			fila[5] = (paciente.getSeguro() != null) ? paciente.getSeguro().getNombreEmpresa() : "N/A";
            // <-- CAMBIO: Se muestra la cantidad de vacunas en lugar de la lista de objetos.
			fila[6] = (paciente.getMisVacunasAplicadas() != null) ? paciente.getMisVacunasAplicadas().size() : 0;
			
			modeloPacientes.addRow(fila);
		}
	}
	
	public void datosDoctores() {
		modeloDoctores.setRowCount(0);
		ArrayList<Doctor> doctores = Clinica.getInstance().getMisDoctores();
		
		for (Doctor doctor : doctores) {
			Object[] fila = { doctor.getCedula(), doctor.getNombre(), doctor.getApellido(), doctor.getEspecialidad() };
			modeloDoctores.addRow(fila);
		}
	}
	
	public void datosSeguros() {
		modeloSeguros.setRowCount(0);
		ArrayList<Seguro> seguros = Clinica.getInstance().getMisSeguros();
		
		for (Seguro seguro : seguros) {
			Object[] fila = { seguro.getIdSeguro(), seguro.getNombreEmpresa(), seguro.getTipoDeSeguro(), seguro.getDescuento() * 100 + "%" };
			modeloSeguros.addRow(fila);
		}
	}
	
	public void datosConsultas() {
		modeloConsultas.setRowCount(0);
		ArrayList<Consulta> consultas = Clinica.getInstance().getMisConsultas();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (Consulta consulta : consultas) {
			Object[] fila = {
                consulta.getId(),
                // <-- CAMBIO: Se muestra el nombre completo del paciente.
                consulta.getPaciente().getNombre() + " " + consulta.getPaciente().getApellido(),
                consulta.getEnfermedad(),
                sdf.format(consulta.getFechaConsulta()),
                consulta.isImportancia() ? "Urgente" : "Normal"
            };
			modeloConsultas.addRow(fila);
		}
	}
	
	public void datosCitas() {
		modeloCitas.setRowCount(0);
		ArrayList<Cita> citas = Clinica.getInstance().getMisCitas();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (Cita cita : citas) {
			Object[] fila = {
                // <-- CAMBIO: Se muestra el nombre completo del doctor.
                cita.getDoctor().getNombre() + " " + cita.getDoctor().getApellido(),
                // <-- CAMBIO: Se muestra el nombre completo del paciente.
                cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido(),
                sdf.format(cita.getFechaCita()),
                cita.getEstado()
            };
			modeloCitas.addRow(fila);
		}
	}
}