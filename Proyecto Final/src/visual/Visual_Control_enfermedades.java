package visual;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
// <-- CAMBIO: Se importan las clases lógicas correctas
import logico.Clinica;
import logico.Doctor;
import logico.Paciente;
import logico.Bajo_vigilancia; // <-- CAMBIO: Usamos la nueva clase Vigilancia

public class Visual_Control_enfermedades extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    
    // (El resto de tu constructor y la UI se mantienen igual...)
    
	private void guardad_enfermeda(ActionEvent e) {
		try {
			String code_pacien = txt_code_paciente.getText();
			String code_doctor = txt_code_doctor.getText();
			String name_enfemer = txt_name_enfemedad.getText();
			Date fecha_enferme =(Date)fecha_enfermeda.getValue();
			
			if(code_pacien.isEmpty() || code_doctor.isEmpty() || name_enfemer.isEmpty()) {
			       throw new IllegalArgumentException("Todos los campos son obligatorios.");
			}
			
            // <-- CAMBIO: Se busca directamente en la instancia de Clinica
            Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(code_pacien);
	        if (paciente == null) {
	            JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	            
            // <-- CAMBIO: Se busca directamente en la instancia de Clinica
	        Doctor doctor = Clinica.getInstance().buscarDoctorByCedula(code_doctor);
	        if (doctor == null) {
	            JOptionPane.showMessageDialog(null, "Doctor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

            // <-- CAMBIO: Se crea un objeto Vigilancia y se añade a la lista central de Clinica
	        Bajo_vigilancia nuevaVigilancia = new Bajo_vigilancia(paciente, name_enfemer, doctor, fecha_enferme, null); // Se pasa null para consultaOrigen
			Clinica.getInstance().getMisVigilancias().add(nuevaVigilancia);
			
			JOptionPane.showMessageDialog(null, "Vigilancia registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            clean();
			
		} catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buscarPaciente(String codigoPaciente) {
        // <-- CAMBIO: Se busca directamente en la instancia de Clinica
	    Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(codigoPaciente);
	    if (paciente != null) {
	        txt_nombre_paciente.setText(paciente.getNombre());
	    } else {
	        txt_nombre_paciente.setText("");
	    }
	}

	private void buscarDoctor(String codigodoctor) {
        // <-- CAMBIO: Se busca directamente en la instancia de Clinica
	    Doctor doct = Clinica.getInstance().buscarDoctorByCedula(codigodoctor);
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
        txt_name_enfemedad.setText("");
		// <-- CAMBIO: El ID se gestionaría desde Clinica
	    txt_code_enfe.setText("VI-" + (Clinica.getInstance().getMisVigilancias().size() + 1)); 
	}
}