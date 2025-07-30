package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import logico.Bajo_vigilancia;
import logico.Clinica;
import logico.Doctor;
import logico.Paciente;

public class Visual_Control_enfermedades extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodeVigilancia;
    private JTextField txtCedulaPaciente;
    private JTextField txtCedulaDoctor;
    private JTextField txtNombrePaciente;
    private JTextField txtNombreDoctor;
    private JTextField txtNombreEnfermedad;
    private JSpinner spnHoraVigilancia;
    private JSpinner spnFecha;

    public Visual_Control_enfermedades() {
        setTitle("Registrar Paciente en Vigilancia");
        setBounds(100, 100, 650, 450);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "Datos de Vigilancia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        // --- (La creación de la UI se mantiene igual) ---
        JLabel lblCode = new JLabel("ID Vigilancia:");
        lblCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCode.setBounds(30, 40, 100, 25);
        contentPanel.add(lblCode);

        txtCodeVigilancia = new JTextField();
        txtCodeVigilancia.setEditable(false);
        txtCodeVigilancia.setBounds(140, 40, 150, 25);
        contentPanel.add(txtCodeVigilancia);

        JLabel lblCedulaPaciente = new JLabel("Cédula Paciente:");
        lblCedulaPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCedulaPaciente.setBounds(30, 80, 120, 25);
        contentPanel.add(lblCedulaPaciente);

        txtCedulaPaciente = new JTextField();
        txtCedulaPaciente.setBounds(140, 80, 150, 25);
        contentPanel.add(txtCedulaPaciente);

        txtNombrePaciente = new JTextField();
        txtNombrePaciente.setEditable(false);
        txtNombrePaciente.setBounds(300, 80, 200, 25);
        contentPanel.add(txtNombrePaciente);

        JLabel lblCedulaDoctor = new JLabel("Cédula Doctor:");
        lblCedulaDoctor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCedulaDoctor.setBounds(30, 120, 120, 25);
        contentPanel.add(lblCedulaDoctor);

        txtCedulaDoctor = new JTextField();
        txtCedulaDoctor.setBounds(140, 120, 150, 25);
        contentPanel.add(txtCedulaDoctor);

        txtNombreDoctor = new JTextField();
        txtNombreDoctor.setEditable(false);
        txtNombreDoctor.setBounds(300, 120, 200, 25);
        contentPanel.add(txtNombreDoctor);
        
        JLabel lblEnfermedad = new JLabel("Enfermedad:");
        lblEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEnfermedad.setBounds(30, 160, 100, 25);
        contentPanel.add(lblEnfermedad);
        
        txtNombreEnfermedad = new JTextField();
        txtNombreEnfermedad.setBounds(140, 160, 200, 25);
        contentPanel.add(txtNombreEnfermedad);

        JLabel lblHoras = new JLabel("Horas de Vigilancia:");
        lblHoras.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblHoras.setBounds(30, 200, 150, 25);
        contentPanel.add(lblHoras);
        
        spnHoraVigilancia = new JSpinner();
        spnHoraVigilancia.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        spnHoraVigilancia.setBounds(180, 200, 80, 25);
        contentPanel.add(spnHoraVigilancia);
        
        JLabel lblFecha = new JLabel("Fecha de Inicio:");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFecha.setBounds(30, 240, 120, 25);
        contentPanel.add(lblFecha);
        
        spnFecha = new JSpinner();
        spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spnFecha.setBounds(140, 240, 150, 25);
        contentPanel.add(spnFecha);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Guardar");
        okButton.addActionListener(e -> guardarVigilancia());
        buttonPane.add(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        txtCedulaPaciente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarPaciente(txtCedulaPaciente.getText());
            }
        });
        
        txtCedulaDoctor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarDoctor(txtCedulaDoctor.getText());
            }
        });

        actualizarId();
    }
    
    private void guardarVigilancia() {
        try {
            String cedulaPaciente = txtCedulaPaciente.getText();
            String cedulaDoctor = txtCedulaDoctor.getText();
            String enfermedad = txtNombreEnfermedad.getText();
            int horas = (Integer) spnHoraVigilancia.getValue();
            Date fecha = (Date) spnFecha.getValue();
            
            if (cedulaPaciente.isEmpty() || cedulaDoctor.isEmpty() || enfermedad.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }
            
            Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(cedulaPaciente);
            if (paciente == null) {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Doctor doctor = Clinica.getInstance().buscarDoctorByCedula(cedulaDoctor);
            if (doctor == null) {
                JOptionPane.showMessageDialog(this, "Doctor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Se crea el objeto Bajo_vigilancia con los datos correctos
            Bajo_vigilancia nuevaVigilancia = new Bajo_vigilancia(paciente, enfermedad, doctor, fecha, null, horas);
            
            // <-- CAMBIO CRÍTICO: Se llama al nuevo método para guardar el objeto directamente -->
            Clinica.getInstance().agregarVigilancia(nuevaVigilancia);
            
            JOptionPane.showMessageDialog(this, "Vigilancia registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            clean();
            
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // --- (El resto de la clase se mantiene igual) ---
    private void buscarPaciente(String cedula) {
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(cedula);
        if (paciente != null) {
            txtNombrePaciente.setText(paciente.getNombre());
        } else {
            txtNombrePaciente.setText("");
        }
    }

    private void buscarDoctor(String cedula) {
        Doctor doctor = Clinica.getInstance().buscarDoctorByCedula(cedula);
        if (doctor != null) {
            txtNombreDoctor.setText(doctor.getNombre());
        } else {
            txtNombreDoctor.setText("");
        }
    }

    private void clean() {
        txtCedulaPaciente.setText("");
        txtNombrePaciente.setText("");
        txtCedulaDoctor.setText("");
        txtNombreDoctor.setText("");
        txtNombreEnfermedad.setText("");
        spnHoraVigilancia.setValue(1);
        spnFecha.setValue(new Date());
        actualizarId();
    }
    
    private void actualizarId() {
        int nextId = (Clinica.getInstance().getMisVigilancias() != null ? Clinica.getInstance().getMisVigilancias().size() : 0) + 1;
        txtCodeVigilancia.setText("VIGI-" + nextId);
    }
}