package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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

    public static void main(String[] args) {
        try {
            InterfazDoctor dialog = new InterfazDoctor();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InterfazDoctor() {
        setTitle("Listado del Doctor");
        setBounds(100, 100, 1275, 803);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setModal(true);

        doctorLogin = doctorLogin();

        if (doctorLogin == null) {
            dispose();
            return;
        }

        JPanel panelPacientes = new JPanel();
        panelPacientes.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 3, true), "Pacientes Consultados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
                    consultar = new Consultar();
                    cita = Clinica.getInstance().getMisCitas().get(filaSeleccionada);
                    paciente = cita.getPaciente();

                    consultar.actualizarCampos(doctorLogin, paciente);
                    consultar.setVisible(true);
                    consultar.setModal(true);

                    actualizarTablaConsultas(doctorLogin.getCedula());
                    actualizarTablaCitas();
                }
            }
        });
        buttonPane.add(btnConsulta);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(cancelButton);

        modelo = new DefaultTableModel();
        String[] headersConsultas = {"Cédula", "Edad", "Nombre", "Apellido", "Seguro", "Enfermedad"};
        modelo.setColumnIdentifiers(headersConsultas);
        tblPacientes.setModel(modelo);

        modeloCitas = new DefaultTableModel();
        String[] headersCitas = {"Edad", "Nombre", "Apellido", "Seguro"};
        modeloCitas.setColumnIdentifiers(headersCitas);
        tblCitas.setModel(modeloCitas);

        actualizarTablaConsultas(doctorLogin.getCedula());
        actualizarTablaCitas();
    }

    public void actualizarTablaCitas() {
        modeloCitas.setRowCount(0);
        for (Cita cita : Clinica.getInstance().getMisCitas()) {
            if (cita.getDoctor().equals(doctorLogin)) {
                Paciente paciente = cita.getPaciente();
                Object[] fila = {
                    paciente.getEdad(),
                    paciente.getNombre(),
                    paciente.getApellido(),
                    paciente.getSeguro().getNombreEmpresa()
                };
                modeloCitas.addRow(fila);
            }
        }
    }

    public void actualizarTablaConsultas(String cedulaDoctor) {
        modelo.setRowCount(0);
        Doctor aux = Clinica.getInstance().buscarDoctorByCedula(cedulaDoctor);
        if (aux != null) {
            for (Paciente paciente : aux.getMisPacientes()) {
                if (paciente.getSeleccionado()) {
                    Object[] fila = {
                        paciente.getCedula(),
                        paciente.getEdad(),
                        paciente.getNombre(),
                        paciente.getApellido(),
                        paciente.getSeguro().getNombreEmpresa(),
                        paciente.getEnfermedad()
                    };
                    modelo.addRow(fila);
                }
            }
        }
    }

	private Doctor doctorLogin() {
		User usuario = Clinica.getInstance().getLoginUser();
		Doctor doc = Clinica.getInstance().buscarDoctorByCedula(usuario.getPass());
	    if (doc != null) {
	        return doc;
	    }
	    return null;
	}
	
}
