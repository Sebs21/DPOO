package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.Paciente;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Random;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

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
    private final ButtonGroup buttonGroup = new ButtonGroup(); // Grupo para los radio buttons

    // <-- CAMBIO: Atributos para guardar el doctor y paciente de la consulta actual
    private Doctor doctorActual;
    private Paciente pacienteActual;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Consultar dialog = new Consultar();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Consultar() {
        setTitle("Consulta M�dica");
        setBounds(100, 100, 1047, 723);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        // --- Interfaz Gr�fica (se mantiene igual, solo se agrupan los radio buttons) ---
        JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(175, 238, 238), 4, true), "Detalles de la Consulta",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		JLabel lblNewLabel_2 = new JLabel("Descripci�n");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(331, 49, 107, 23);
		panel_3.add(lblNewLabel_2);

		JLabel lblEnfermedad = new JLabel("Diagn�stico/Enfermedad:");
		lblEnfermedad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblEnfermedad.setBounds(10, 48, 250, 23);
		panel_3.add(lblEnfermedad);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 4, true), "Seguro M�dico",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(176, 224, 230), 4, true), "Informaci�n General",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		lblNombrel.setBounds(35, 134, 78, 23);
		panel_2.add(lblNombrel);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(148, 127, 165, 37);
		panel_2.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(461, 127, 165, 37);
		panel_2.add(txtApellido);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblApellido.setBounds(348, 134, 78, 23);
		panel_2.add(lblApellido);

		JButton btnHistoriaClinica = new JButton("Ver Historia Cl�nica");
		btnHistoriaClinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Este c�digo asume que tienes una ventana llamada HistoriaXPaciente
				// y que funciona correctamente.
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
		        // Obtener la c�dula del paciente que se est� consultando
		        String cedulaPaciente = txtCedula.getText();
		        
		        // Crear una instancia de la ventana de reporte correcta
		        Reporte_Vacuna reporte = new Reporte_Vacuna();
		        
		        // Llamar a los m�todos p�blicos de esa ventana para cargar los datos
		        // y mostrar directamente la tabla, saltando el paso de b�squeda.
		        reporte.load_reporte(cedulaPaciente);
		        reporte.mostrarVista("tabla"); // Asumiendo que este m�todo es p�blico
		        
		        reporte.setVisible(true);
		        reporte.setModal(true); // Para que la ventana de consulta espere
		    }
		});
		
		btnVerVacunas.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnVerVacunas.setBounds(286, 300, 165, 37);
		panel_2.add(btnVerVacunas);

		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setColumns(10);
		txtCedula.setBounds(148, 47, 165, 37);
		panel_2.add(txtCedula);

		JLabel lblCedula = new JLabel("C�dula:");
		lblCedula.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblCedula.setBounds(35, 54, 78, 23);
		panel_2.add(lblCedula);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEdad.setBounds(348, 54, 78, 23);
		panel_2.add(lblEdad);

		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setColumns(10);
		txtEdad.setBounds(461, 47, 165, 37);
		panel_2.add(txtEdad);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 4, true),
				"Prioridad de la Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 438, 336, 122);
		contentPanel.add(panel_4);

		rdbtnNoImportante = new JRadioButton("Normal");
		buttonGroup.add(rdbtnNoImportante);
		rdbtnNoImportante.setSelected(true);
		rdbtnNoImportante.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		rdbtnNoImportante.setBounds(35, 55, 122, 23);
		panel_4.add(rdbtnNoImportante);

		rdbtnImportante = new JRadioButton("Urgente");
		buttonGroup.add(rdbtnImportante);
		rdbtnImportante.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		rdbtnImportante.setBounds(192, 55, 109, 23);
		panel_4.add(rdbtnImportante);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Finalizar Consulta");
		okButton.addActionListener(e -> {
			finalizarConsulta();
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(e -> dispose());
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

    /**
     * Carga los datos del doctor y paciente en la ventana.
     * @param doctor El doctor de la consulta.
     * @param paciente El paciente de la consulta.
     */
	public void actualizarCampos(Doctor doctor, Paciente paciente) {
        // <-- CAMBIO: Se guardan las referencias al doctor y paciente actuales
        this.doctorActual = doctor;
        this.pacienteActual = paciente;

        // Se llenan los campos de texto con la informaci�n
		txtDoctor.setText(doctor.getNombre() + " " + doctor.getApellido());
		txtIdConsulta.setText("C-" + Clinica.getInstance().getIdConsulta());
		txtNombre.setText(paciente.getNombre());
		txtApellido.setText(paciente.getApellido());
		txtCedula.setText(paciente.getCedula());
		txtEdad.setText(paciente.getEdad());
        // Se a�ade una comprobaci�n para evitar errores si el paciente no tiene seguro
        if (paciente.getSeguro() != null) {
		    txtSeguro.setText(paciente.getSeguro().getNombreEmpresa());
        } else {
            txtSeguro.setText("No aplica");
        }
	}

    /**
     * Finaliza la consulta, creando un �nico registro con los datos correctos.
     */
	public void finalizarConsulta() {
		// <-- CAMBIO: Toda la l�gica fue reescrita para ser correcta y segura
        
        // 1. Validar que tenemos un doctor y un paciente activos en la ventana
		if (doctorActual == null || pacienteActual == null) {
			JOptionPane.showMessageDialog(this, "No se ha podido identificar al doctor o al paciente.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
        
        // 2. Recoger los datos del formulario
        String id = txtIdConsulta.getText();
        String descripcion = txtDescripcion.getText();
        String enfermedad = txtEnfermedad.getText();
        Date fecha = (Date) spnFecha.getValue();
        boolean esImportante = rdbtnImportante.isSelected();

        // 3. Crear UNA SOLA instancia de Consulta con los datos correctos
		Consulta nuevaConsulta = new Consulta(
            id,
            0, // El ID de factura deber�a gestionarse en un m�dulo de facturaci�n
            descripcion,
            enfermedad,
            fecha,
            txtSeguro.getText(),
            this.doctorActual,  // Usar el doctor guardado
            this.pacienteActual, // Usar el paciente guardado
            esImportante
        );

        // 4. Agregar la consulta a la lista central de la cl�nica
		Clinica.getInstance().agregarConsulta(nuevaConsulta);

		JOptionPane.showMessageDialog(this, "Consulta registrada exitosamente.", "�xito", JOptionPane.INFORMATION_MESSAGE);
        
        // 5. Cerrar la ventana despu�s de registrar
        dispose();
	}
}