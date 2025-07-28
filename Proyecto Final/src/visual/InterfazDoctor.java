package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import logico.Paciente;
import logico.User;

public class InterfazDoctor extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable tblPacientes;
    private DefaultTableModel modelo;
    private JTable tblCitas;
    private DefaultTableModel modeloCitas;
    private Consultar consultar = null;
    private Paciente paciente = null;
    private Doctor doctorLogin = null;
    private Cita cita = null;

    public InterfazDoctor() {
        setTitle("Interfaz del Doctor");
        setBounds(100, 100, 1275, 803);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        // Se obtiene el doctor que ha iniciado sesión
        doctorLogin = obtenerDoctorLogueado();

        // Si no se encuentra un doctor (p. ej., si un admin abre la ventana), se cierra.
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

        JButton btnConsulta = new JButton("Proceder a Consulta");
        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tblCitas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Se busca la cita correcta en la lista filtrada
                    ArrayList<Cita> misCitas = obtenerCitasDelDoctor(doctorLogin);
                    if(filaSeleccionada < misCitas.size()){
                        cita = misCitas.get(filaSeleccionada);
                        paciente = cita.getPaciente();
                        
                        consultar = new Consultar();
                        consultar.actualizarCampos(doctorLogin, paciente);
                        consultar.setVisible(true);

                        // Se actualizan las tablas después de cerrar la ventana de consulta
                        actualizarTablaConsultas(doctorLogin.getCedula());
                        actualizarTablaCitas();
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

        modelo = new DefaultTableModel();
        String[] headersConsultas = {"Cédula", "Nombre", "Apellido", "Edad", "Seguro", "Último Diagnóstico"};
        modelo.setColumnIdentifiers(headersConsultas);
        tblPacientes.setModel(modelo);

        modeloCitas = new DefaultTableModel();
        String[] headersCitas = {"Nombre", "Apellido", "Edad", "Seguro"};
        modeloCitas.setColumnIdentifiers(headersCitas);
        tblCitas.setModel(modeloCitas);
        
        actualizarTablaConsultas(doctorLogin.getCedula());
        actualizarTablaCitas();
    }

    public void actualizarTablaCitas() {
        modeloCitas.setRowCount(0);
        ArrayList<Cita> misCitas = obtenerCitasDelDoctor(doctorLogin);

        for (Cita cita : misCitas) {
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

    private ArrayList<Cita> obtenerCitasDelDoctor(Doctor doctor) {
        ArrayList<Cita> citasDelDoctor = new ArrayList<>();
        for (Cita cita : Clinica.getInstance().getMisCitas()) {
            // <-- CAMBIO CRÍTICO: Se compara por cédula en lugar de por objeto
            if (cita.getDoctor().getCedula().equalsIgnoreCase(doctor.getCedula())) {
                citasDelDoctor.add(cita);
            }
        }
        return citasDelDoctor;
    }

    public void actualizarTablaConsultas(String cedulaDoctor) {
        modelo.setRowCount(0);
        Doctor aux = Clinica.getInstance().buscarDoctorByCedula(cedulaDoctor);
        if (aux != null && aux.getMisPacientes() != null) {
            for (Paciente paciente : aux.getMisPacientes()) {
                // Asumiendo que quieres mostrar todos los pacientes que el doctor ha visto
                Object[] fila = {
                    paciente.getCedula(),
                    paciente.getNombre(),
                    paciente.getApellido(),
                    paciente.getEdad(),
                    paciente.getSeguro() != null ? paciente.getSeguro().getNombreEmpresa() : "N/A",
                    paciente.getEnfermedad() // El último diagnóstico registrado
                };
                modelo.addRow(fila);
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