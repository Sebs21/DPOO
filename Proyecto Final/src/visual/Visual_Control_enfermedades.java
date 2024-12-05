package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logico.Bajo_vigilancia;
import logico.Control_enfermedad;
import logico.Doctor;
import logico.Paciente;

public class Visual_Control_enfermedades extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txt_code_enfe;
    private JTextField txt_code_paciente;
    private JTextField txt_code_doctor;
    private JTextField txt_nombre_paciente;
    private JTextField txt_nombre_doctor;
    private JTextField txt_name_enfemedad;
    private JButton btnGuardar;
    private JTextField txt_expecialidad;
    private JSpinner cant_spinner;
    private JLabel lbl_Cant_hora;
    private JSpinner fecha_enfermeda;

    /**
     * Create the dialog.
     */
    public Visual_Control_enfermedades() {
        setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
        setTitle("Registro Vigilancia");
        setBounds(100, 100, 868, 564);
        setLocationRelativeTo(null);
        setModal(true); 
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel Codigo_fac = new JLabel("N.O");
        Codigo_fac.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        Codigo_fac.setBounds(21, 32, 192, 26);
        contentPanel.add(Codigo_fac);

        JLabel lblCodigoDepaciente = new JLabel("Codigo de Paciente:");
        lblCodigoDepaciente.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblCodigoDepaciente.setBounds(21, 86, 192, 26);
        contentPanel.add(lblCodigoDepaciente);

        JLabel lblCodigoDeEmpleado = new JLabel("Codigo de Doctor:");
        lblCodigoDeEmpleado.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lblCodigoDeEmpleado.setBounds(21, 208, 192, 26);
        contentPanel.add(lblCodigoDeEmpleado);

        JLabel cod_queso_jbl = new JLabel("Enfermedad:");
        cod_queso_jbl.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cod_queso_jbl.setBounds(21, 149, 192, 26);
        contentPanel.add(cod_queso_jbl);

        txt_code_enfe = new JTextField();
        txt_code_enfe.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        txt_code_enfe.setText("VI-" + Control_enfermedad.code_enfe);
        txt_code_enfe.setEditable(false);
        txt_code_enfe.setBounds(234, 29, 186, 32);
        contentPanel.add(txt_code_enfe);

        txt_code_paciente = new JTextField();
        txt_code_paciente.setBounds(234, 83, 186, 32);
        contentPanel.add(txt_code_paciente);

        txt_code_doctor = new JTextField();
        txt_code_doctor.setBounds(234, 202, 186, 32);
        contentPanel.add(txt_code_doctor);

        txt_nombre_paciente = new JTextField();
        txt_nombre_paciente.setEditable(false);
        txt_nombre_paciente.setBounds(430, 83, 186, 32);
        contentPanel.add(txt_nombre_paciente);

        txt_nombre_doctor = new JTextField();
        txt_nombre_doctor.setEditable(false);
        txt_nombre_doctor.setBounds(430, 202, 186, 32);
        contentPanel.add(txt_nombre_doctor);

        txt_name_enfemedad = new JTextField();
        txt_name_enfemedad.setEditable(false);
        txt_name_enfemedad.setBounds(234, 146, 186, 32);
        contentPanel.add(txt_name_enfemedad);

        lbl_Cant_hora = new JLabel("Cantidad hora:");
        lbl_Cant_hora.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        lbl_Cant_hora.setBounds(21, 261, 192, 26);
        contentPanel.add(lbl_Cant_hora);

        cant_spinner = new JSpinner();
        cant_spinner.setModel(new SpinnerNumberModel(1, 1, null, 1));
        cant_spinner.setBounds(234, 258, 186, 32);
        contentPanel.add(cant_spinner);

        txt_expecialidad = new JTextField();
        txt_expecialidad.setEditable(false);
        txt_expecialidad.setBounds(630, 202, 186, 32);
        contentPanel.add(txt_expecialidad);

        JLabel label = new JLabel("Fecha:");
        label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        label.setBounds(440, 255, 91, 44);
        contentPanel.add(label);

        fecha_enfermeda = new JSpinner();
        fecha_enfermeda.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        fecha_enfermeda.setModel(new SpinnerDateModel(new Date(), new Date(), null, Calendar.DAY_OF_YEAR));
        fecha_enfermeda.setBounds(510, 256, 165, 37);
        contentPanel.add(fecha_enfermeda);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnGuardar = new JButton("Registrar");
        btnGuardar.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        btnGuardar.addActionListener(this::guardad_enfermeda);
        buttonPane.add(btnGuardar);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        txt_code_paciente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarPaciente(txt_code_paciente.getText());
            }
        });

        txt_code_doctor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarDoctor(txt_code_doctor.getText());
            }
        });
    }
	
	private void guardad_enfermeda(ActionEvent e)
	{
		try {
			String codigo_enfer = txt_code_enfe.getText();
			String code_pacien = txt_code_paciente.getText();
			String code_doctor = txt_code_doctor.getText();
			String name_enfemer = txt_name_enfemedad.getText();
			float cant_hora = ((Integer)cant_spinner.getValue()).floatValue();
			Date fecha_enferme =(Date)fecha_enfermeda.getValue();
			
			
			if(code_pacien.isEmpty())
			{
			       throw new IllegalArgumentException("El espacio de codigo de paciente esta vacio.");
			}
			if(code_doctor.isEmpty())
			{
			       throw new IllegalArgumentException("El espacio de codigo de doctor esta vacio.");

			}
			
			  Paciente paciente = Control_enfermedad.verificar_code_paciente(code_pacien);
	            if (paciente == null) {
	                JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            Doctor doc = Control_enfermedad.verificar_code_doctor(code_doctor);
	            if (doc == null) {
	                JOptionPane.showMessageDialog(null, "Doctor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
			Bajo_vigilancia enfe = new Bajo_vigilancia(codigo_enfer, name_enfemer, cant_hora, code_pacien, code_doctor, fecha_enferme);
			Control_enfermedad.getVigilancia().add(enfe);
			paciente.agregarenfermedad(enfe);
			
			
			JOptionPane.showMessageDialog(null, " registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            clean();
			
			
			
			
			
			
			
		} catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error al guardar : " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	  private void buscarPaciente(String codigoPaciente)
	    {
	        Paciente paciente = Control_enfermedad.verificar_code_paciente(codigoPaciente);//aqui
	        if (paciente != null) {
	            txt_nombre_paciente.setText(paciente.getNombre());
	           
	        } else {
	        	txt_nombre_paciente.setText("");
	          
	        }
	    }
	  private void buscarDoctor(String codigodoctor)
	    {
	        Doctor doct =Control_enfermedad.verificar_code_doctor(codigodoctor);
	        if (doct != null) {
	        	txt_nombre_doctor.setText(doct.getNombre());
	           
	        } else {
	        	txt_nombre_doctor.setText("");
	          
	        }
	    }
	
	
	  private void clean() {
		    fecha_enfermeda.setValue(new Date()); 
		    cant_spinner.setValue(1); 
		    txt_code_paciente.setText("");
		    txt_nombre_paciente.setText("");
		    txt_code_doctor.setText("");
		    txt_nombre_doctor.setText("");
		    txt_code_enfe.setText("VI-" + Control_enfermedad.code_enfe ); 
		}

	
	
}
