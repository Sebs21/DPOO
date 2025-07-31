package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.Paciente;

public class Consultar extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtDoctor;
    private JTextField txtIdConsulta;
    private JTextField txtSeguro;
    private JTextField txtDescripcion;
    private JTextField txtEnfermedad;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtEdad;
    private JRadioButton rdbtnNoImportante;
    private JRadioButton rdbtnImportante;
    private JTextField txtIdfactura;
    private JSpinner spnFecha;
    private JTextField txtSexo;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    private JCheckBox chckbxEnviarAVigilancia;

    private Doctor doctorActual;
    private Paciente pacienteActual;
    private boolean consultaFinalizada = false;

    public Consultar(JDialog parent) {
        super(parent, true);

        setTitle("Consulta Médica");
        setBounds(100, 100, 1047, 723);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(parent);

        // --- (El resto de la UI se mantiene igual) ---
        JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(175, 238, 238), 4, true), "Detalles de la Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(356, 370, 665, 270);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescripcion.setBounds(331, 82, 324, 178);
		panel_3.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		txtEnfermedad = new JTextField();
		txtEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEnfermedad.setBounds(10, 82, 311, 177);
		panel_3.add(txtEnfermedad);
		txtEnfermedad.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Descripción");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(331, 49, 107, 23);
		panel_3.add(lblNewLabel_2);

		JLabel lblEnfermedad = new JLabel("Diagnóstico/Enfermedad:");
		lblEnfermedad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblEnfermedad.setBounds(10, 48, 250, 23);
		panel_3.add(lblEnfermedad);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 4, true), "Seguro Médico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 292, 336, 135);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Seguro:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(31, 56, 78, 23);
		panel_1.add(lblNewLabel_1);

		txtSeguro = new JTextField();
		txtSeguro.setEditable(false);
		txtSeguro.setBounds(140, 49, 165, 37);
		panel_1.add(txtSeguro);
		txtSeguro.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(176, 224, 230), 4, true), "Información General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 336, 270);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Doctor:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(32, 160, 91, 44);
		panel.add(lblNewLabel);

		txtDoctor = new JTextField();
		txtDoctor.setEditable(false);
		txtDoctor.setBounds(133, 165, 165, 37);
		panel.add(txtDoctor);
		txtDoctor.setColumns(10);

		txtIdConsulta = new JTextField();
		txtIdConsulta.setEditable(false);
		txtIdConsulta.setColumns(10);
		txtIdConsulta.setBounds(131, 30, 165, 37);
		panel.add(txtIdConsulta);

		JLabel lblIdConsulta = new JLabel("ID Consulta:");
		lblIdConsulta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIdConsulta.setBounds(32, 24, 91, 44);
		panel.add(lblIdConsulta);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblFecha.setBounds(32, 215, 91, 44);
		panel.add(lblFecha);

		spnFecha = new JSpinner();
		spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spnFecha.setBounds(133, 222, 165, 37);
		panel.add(spnFecha);

		JLabel lblIdFactura = new JLabel("Id Factura:");
		lblIdFactura.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIdFactura.setBounds(32, 90, 91, 44);
		panel.add(lblIdFactura);

		txtIdfactura = new JTextField();
		txtIdfactura.setEditable(false);
		txtIdfactura.setColumns(10);
		txtIdfactura.setBounds(133, 95, 165, 37);
		panel.add(txtIdfactura);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 4, true), "Datos del Paciente",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(356, 11, 665, 348);
		contentPanel.add(panel_2);

		JLabel lblNombrel = new JLabel("Nombre:");
		lblNombrel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNombrel.setBounds(35, 80, 78, 23);
		panel_2.add(lblNombrel);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(148, 76, 165, 37);
		panel_2.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setBounds(461, 76, 165, 37);
		panel_2.add(txtApellido);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblApellido.setBounds(348, 80, 78, 23);
		panel_2.add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        lblSexo.setBounds(348, 124, 78, 23);
        panel_2.add(lblSexo);
        
        txtSexo = new JTextField();
        txtSexo.setEditable(false);
        txtSexo.setBounds(461, 124, 165, 37);
        panel_2.add(txtSexo);

		JButton btnHistoriaClinica = new JButton("Ver Historia Clínica");
		btnHistoriaClinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoriaXPaciente hXp = new HistoriaXPaciente();
				hXp.actualizarTabla(txtCedula.getText());
				hXp.setVisible(true);
			}
		});
		btnHistoriaClinica.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnHistoriaClinica.setBounds(461, 300, 194, 37);
		panel_2.add(btnHistoriaClinica);

		JButton btnVerVacunas = new JButton("Ver Vacunas");
		btnVerVacunas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        String cedulaPaciente = txtCedula.getText();
		        Reporte_Vacuna reporte = new Reporte_Vacuna(Consultar.this);
		        reporte.load_reporte(cedulaPaciente);
		        reporte.mostrarVista("tabla");
		        reporte.setVisible(true);
		    }
		});
		
		btnVerVacunas.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnVerVacunas.setBounds(286, 300, 165, 37);
		panel_2.add(btnVerVacunas);

		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setBounds(148, 29, 165, 37);
		panel_2.add(txtCedula);

		JLabel lblCedula = new JLabel("Cédula:");
		lblCedula.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblCedula.setBounds(35, 36, 78, 23);
		panel_2.add(lblCedula);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEdad.setBounds(35, 124, 78, 23);
		panel_2.add(lblEdad);

		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setBounds(148, 124, 165, 37);
		panel_2.add(txtEdad);

        // --- CAMBIO: Panel de Acciones rediseñado y limpiado ---
        JPanel panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 4, true), "Acciones Adicionales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_4.setBounds(10, 438, 336, 195);
        contentPanel.add(panel_4);
        
        JLabel lblPrioridad = new JLabel("Prioridad de la Consulta:");
        lblPrioridad.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblPrioridad.setBounds(35, 30, 200, 23);
        panel_4.add(lblPrioridad);

        rdbtnNoImportante = new JRadioButton("Normal");
        buttonGroup.add(rdbtnNoImportante);
        rdbtnNoImportante.setSelected(true);
        rdbtnNoImportante.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        rdbtnNoImportante.setBounds(35, 55, 122, 23);
        panel_4.add(rdbtnNoImportante);

        rdbtnImportante = new JRadioButton("Urgente");
        buttonGroup.add(rdbtnImportante);
        rdbtnImportante.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        rdbtnImportante.setBounds(192, 55, 109, 23);
        panel_4.add(rdbtnImportante);

        chckbxEnviarAVigilancia = new JCheckBox("Enviar Paciente a Vigilancia");
        chckbxEnviarAVigilancia.setFont(new Font("Times New Roman", Font.BOLD, 15));
        chckbxEnviarAVigilancia.setBounds(35, 100, 250, 23);
        panel_4.add(chckbxEnviarAVigilancia);
        
        // --- Botones Finalizar y Cancelar ---
        JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Finalizar Consulta");
		okButton.addActionListener(e -> finalizarConsulta());
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(e -> dispose());
		buttonPane.add(cancelButton);
    }
    
    public boolean isConsultaFinalizada() {
        return consultaFinalizada;
    }

    public void actualizarCampos(Doctor doctor, Paciente paciente) {
        this.doctorActual = doctor;
        this.pacienteActual = paciente;
		txtDoctor.setText(doctor.getNombre() + " " + doctor.getApellido());
		txtIdConsulta.setText("C-" + Clinica.getIdConsulta());
		txtNombre.setText(paciente.getNombre());
		txtApellido.setText(paciente.getApellido());
		txtCedula.setText(paciente.getCedula());
		txtEdad.setText(paciente.getEdad());
        txtSexo.setText(paciente.getSexo()); // <-- CAMBIO: Se muestra el sexo del paciente
        if (paciente.getSeguro() != null) {
		    txtSeguro.setText(paciente.getSeguro().getNombreEmpresa());
        } else {
            txtSeguro.setText("No aplica");
        }
    }

    public void finalizarConsulta() {
        if (doctorActual == null || pacienteActual == null) {
            JOptionPane.showMessageDialog(this, "No se ha podido identificar al doctor o al paciente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String id = txtIdConsulta.getText();
        String descripcion = txtDescripcion.getText();
        String enfermedad = txtEnfermedad.getText();
        Date fecha = (Date) spnFecha.getValue();
        boolean esImportante = rdbtnImportante.isSelected();
        double precioConsulta = 0;

        // <-- CAMBIO: Se asigna un precio fijo según la importancia -->
        if (esImportante) {
            precioConsulta = 3500.00; // Precio para consulta urgente
        } else {
            precioConsulta = 1500.00; // Precio para consulta normal
        }

        Consulta nuevaConsulta = new Consulta(
            id, 
            Clinica.getIdFactura(), 
            descripcion, 
            enfermedad, 
            fecha, 
            txtSeguro.getText(), 
            this.doctorActual, 
            this.pacienteActual, 
            esImportante, 
            precioConsulta // Se pasa el precio al constructor
        );
        Clinica.getInstance().agregarConsulta(nuevaConsulta);
        pacienteActual.agregarConsultaAlHistorial(nuevaConsulta);

        if (!this.doctorActual.getMisPacientes().contains(this.pacienteActual)) {
            this.doctorActual.getMisPacientes().add(this.pacienteActual);
        }
        
        this.pacienteActual.setEnfermedad(enfermedad);

        // --- CAMBIO CRÍTICO: La lógica de vigilancia ahora depende del CheckBox ---
        if (chckbxEnviarAVigilancia.isSelected()) {
            boolean horasValidas = false;
            int horas = 0;
            while (!horasValidas) {
                String horasStr = JOptionPane.showInputDialog(this, "La consulta es importante.\nIngrese las horas de vigilancia:", "Vigilancia Requerida", JOptionPane.QUESTION_MESSAGE);
                try {
                    if (horasStr == null) break; // Si el usuario presiona "Cancelar"
                    horas = Integer.parseInt(horasStr);
                    if (horas > 0) {
                        horasValidas = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de horas mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (horasValidas) {
                // Se llama al método de la clínica para iniciar la vigilancia
                Clinica.getInstance().iniciarVigilancia(nuevaConsulta, horas);
                JOptionPane.showMessageDialog(this, "Paciente enviado a vigilancia por " + horas + " horas.", "Vigilancia Iniciada", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(this, "Consulta registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        this.consultaFinalizada = true;
        dispose();
    }
}