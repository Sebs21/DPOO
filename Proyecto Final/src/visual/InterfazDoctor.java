package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Doctor;
import logico.Enfermedad;
import logico.Paciente;
import logico.User;

public class InterfazDoctor extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable tblPacientes;
    private DefaultTableModel modeloPacientes;
    private JTable tblCitas;
    private DefaultTableModel modeloCitas;
    private Doctor doctorLogin = null;

    public InterfazDoctor() {
        setTitle("Interfaz del Doctor");
        setBounds(100, 100, 1275, 803);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        doctorLogin = obtenerDoctorLogueado();

        if (doctorLogin == null) {
            JOptionPane.showMessageDialog(this, "No se pudo identificar al doctor. Asegúrese de iniciar sesión como un doctor.", "Error de Usuario", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        JPanel panelPacientes = new JPanel();
        panelPacientes.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 3, true), "Historial de Pacientes Atendidos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelPacientes.setBounds(513, 11, 736, 709);
        contentPanel.add(panelPacientes);
        panelPacientes.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPanePacientes = new JScrollPane();
        panelPacientes.add(scrollPanePacientes, BorderLayout.CENTER);

        tblPacientes = new JTable();
        scrollPanePacientes.setViewportView(tblPacientes);

        JPanel panelCitas = new JPanel();
        panelCitas.setBorder(new TitledBorder(new LineBorder(new Color(135, 206, 250), 3, true), "Citas Pendientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelCitas.setBounds(10, 11, 460, 709);
        contentPanel.add(panelCitas);
        panelCitas.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPaneCitas = new JScrollPane();
        panelCitas.add(scrollPaneCitas, BorderLayout.CENTER);

        tblCitas = new JTable();
        scrollPaneCitas.setViewportView(tblCitas);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGestionarVigilancia = new JButton("Gestionar Vigilancias");
        btnGestionarVigilancia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Reporte_control_enfermedades Gv = new Reporte_control_enfermedades(doctorLogin);
                Gv.setVisible(true);
            }
        });
        buttonPane.add(btnGestionarVigilancia);

        JButton btnConsulta = new JButton("Proceder a Consulta");
        btnConsulta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    int filaSeleccionada = tblCitas.getSelectedRow();
        	    if (filaSeleccionada != -1) {
        	        ArrayList<Cita> misCitasPendientes = obtenerCitasPendientes(doctorLogin);
        	        if (filaSeleccionada < misCitasPendientes.size()) {
        	            Cita citaSeleccionada = misCitasPendientes.get(filaSeleccionada);
        	            
        	            Consultar consultar = new Consultar(InterfazDoctor.this); 
        	            consultar.actualizarCampos(citaSeleccionada.getDoctor(), citaSeleccionada.getPaciente());
        	            consultar.setVisible(true);

        	            if (consultar.isConsultaFinalizada()) {
        	                citaSeleccionada.setEstado("Completada");

        	                Clinica.getInstance().actualizarEstadoCita(citaSeleccionada);

        	                actualizarTablaHistorial(doctorLogin.getCedula());
        	                actualizarTablaCitasPendientes();
        	            }
        	        }
        	    } else {
        	        JOptionPane.showMessageDialog(null, "Debe seleccionar una cita para proceder.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        	    }
        	}
        });
        buttonPane.add(btnConsulta);

        JButton cancelButton = new JButton("Cerrar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        modeloPacientes = new DefaultTableModel();
        String[] headersConsultas = {"Cédula", "Nombre", "Apellido", "Edad", "Seguro", "Último Diagnóstico"};
        modeloPacientes.setColumnIdentifiers(headersConsultas);
        tblPacientes.setModel(modeloPacientes);

        modeloCitas = new DefaultTableModel();
        String[] headersCitas = {"Nombre Paciente", "Apellido", "Edad", "Seguro"};
        modeloCitas.setColumnIdentifiers(headersCitas);
        tblCitas.setModel(modeloCitas);
        
        actualizarTablaHistorial(doctorLogin.getCedula());
        actualizarTablaCitasPendientes();
    }

    public void actualizarTablaCitasPendientes() {
        modeloCitas.setRowCount(0);
        ArrayList<Cita> misCitasPendientes = obtenerCitasPendientes(doctorLogin);

        for (Cita cita : misCitasPendientes) {
            Paciente paciente = cita.getPaciente();
            Object[] fila = {
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getEdad(),
                paciente.getSeguro() != null ? paciente.getSeguro().getNombreEmpresa() : "No especificado"
            };
            modeloCitas.addRow(fila);
        }
    }

    private ArrayList<Cita> obtenerCitasPendientes(Doctor doctor) {
        ArrayList<Cita> citasPendientes = new ArrayList<>();
        for (Cita cita : Clinica.getInstance().getMisCitas()) {
            if (cita.getDoctor().getCedula().equalsIgnoreCase(doctor.getCedula()) && cita.getEstado().equalsIgnoreCase("Pendiente")) {
                citasPendientes.add(cita);
            }
        }
        return citasPendientes;
    }

    public void actualizarTablaHistorial(String cedulaDoctor) {
        modeloPacientes.setRowCount(0);
        Doctor doctor = Clinica.getInstance().buscarDoctorByCedula(cedulaDoctor);
        if (doctor != null && doctor.getMisPacientes() != null) {
            for (Paciente paciente : doctor.getMisPacientes()) {
                
                String enfermedadesStr = "N/A";
                if (paciente.getHistorialDeEnfermedades() != null && !paciente.getHistorialDeEnfermedades().isEmpty()) {
                    enfermedadesStr = paciente.getHistorialDeEnfermedades().stream()
                                            .map(Enfermedad::getNombre)
                                            .collect(Collectors.joining(", "));
                }

                Object[] fila = {
                    paciente.getCedula(),
                    paciente.getNombre(),
                    paciente.getApellido(),
                    paciente.getEdad(),
                    paciente.getSeguro() != null ? paciente.getSeguro().getNombreEmpresa() : "N/A",
                    enfermedadesStr
                };
                modeloPacientes.addRow(fila);
            }
        }
    }

    private Doctor obtenerDoctorLogueado() {
        User usuario = Clinica.getInstance().getLoginUser();
        if (usuario == null || !usuario.getTipo().equalsIgnoreCase("Doctor")) {
            return null; 
        }
        return Clinica.getInstance().buscarDoctorByCedula(usuario.getPass());
    }
}