package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Paciente;
import logico.vacunacion;

public class Visual_vacunacion extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTextField txtCodeVacu;
    private JTextField txtCodePaciente;
    private JTextField txtNombrePaciente;
    private JButton btnGuardar;
    private JComboBox<String> listVacuna;
    private JSpinner fechaVacu;
    private JSpinner spnCantMl;

    public Visual_vacunacion() {
        // (El constructor y la UI se mantienen igual)
        setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
        setTitle("Registro de Aplicación de Vacuna");
        setBounds(100, 100, 868, 564);
        setLocationRelativeTo(null);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // --- Configuración de la Interfaz Gráfica (se mantiene igual) ---
        JLabel lblCodeVacu = new JLabel("N.O");
        lblCodeVacu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        lblCodeVacu.setBounds(21, 32, 192, 26);
        contentPanel.add(lblCodeVacu);

        JLabel lblCodePaciente = new JLabel("Cédula del Paciente:");
        lblCodePaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        lblCodePaciente.setBounds(21, 86, 192, 26);
        contentPanel.add(lblCodePaciente);

        JLabel lblVacuna = new JLabel("Vacuna:");
        lblVacuna.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        lblVacuna.setBounds(21, 149, 192, 26);
        contentPanel.add(lblVacuna);

        JLabel lblCantMl = new JLabel("Cantidad ml:");
        lblCantMl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        lblCantMl.setBounds(21, 216, 112, 26);
        contentPanel.add(lblCantMl);

        txtCodeVacu = new JTextField();
        txtCodeVacu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        txtCodeVacu.setEditable(false);
        txtCodeVacu.setBounds(154, 29, 186, 32);
        contentPanel.add(txtCodeVacu);

        txtCodePaciente = new JTextField();
        txtCodePaciente.setBounds(174, 83, 186, 32); // Ajustado para no superponer
        contentPanel.add(txtCodePaciente);

        txtNombrePaciente = new JTextField();
        txtNombrePaciente.setEditable(false);
        txtNombrePaciente.setBounds(370, 83, 186, 32);
        contentPanel.add(txtNombrePaciente);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        lblFecha.setBounds(208, 206, 91, 44);
        contentPanel.add(lblFecha);

        fechaVacu = new JSpinner();
        fechaVacu.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        fechaVacu.setBounds(267, 211, 165, 37);
        contentPanel.add(fechaVacu);

        listVacuna = new JComboBox<>();
        listVacuna.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        listVacuna.setBounds(154, 146, 186, 32);
        contentPanel.add(listVacuna);

        spnCantMl = new JSpinner();
        spnCantMl.setModel(new SpinnerNumberModel(1, 0, 10, 1));
        spnCantMl.setBounds(138, 218, 66, 22);
        contentPanel.add(spnCantMl);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
        btnGuardar.addActionListener(this::guardarVacunacion);
        buttonPane.add(btnGuardar);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        // --- Lógica de la Ventana ---

        // Evento para buscar paciente al perder el foco del campo de texto
        txtCodePaciente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarPaciente(txtCodePaciente.getText());
            }
        });

        // Cargar los datos iniciales
        cargarVacunasDisponibles();
        actualizarCodigoVacunacion();
    }

    private void guardarVacunacion(ActionEvent e) {
        try {
            String codigoPaciente = txtCodePaciente.getText();
            if (listVacuna.getSelectedIndex() == 0) {
                 throw new IllegalArgumentException("Debe seleccionar una vacuna de la lista.");
            }
            String tipoVacunaNombre = listVacuna.getSelectedItem().toString();

            Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(codigoPaciente);
            if (paciente == null) {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            vacunacion vacunaDelInventario = null;
            for(vacunacion v : Clinica.getInstance().getInventarioDeVacunas()){
                if(v.getNombre().equalsIgnoreCase(tipoVacunaNombre)){
                    vacunaDelInventario = v;
                    break;
                }
            }

            // <-- CAMBIO: Se obtienen la fecha y los ml de la UI
            Date fechaDeAplicacion = (Date) fechaVacu.getValue();
            int cantidadMl = (Integer) spnCantMl.getValue();

            // <-- CAMBIO: Se llama al método correcto con todos los parámetros
            boolean exito = Clinica.getInstance().administrarVacuna(paciente, vacunaDelInventario, fechaDeAplicacion, cantidadMl);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Vacuna registrada y descontada del inventario.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                clean();
            } else {
                JOptionPane.showMessageDialog(this, "No hay stock suficiente para esta vacuna.", "Error de Inventario", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // (El resto de la clase se mantiene igual)
    private void cargarVacunasDisponibles() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("<Seleccione>");
        
        ArrayList<vacunacion> inventario = Clinica.getInstance().getInventarioDeVacunas();
        if (inventario != null) {
            for (vacunacion v : inventario) {
                // Opcional: solo mostrar vacunas con stock disponible
                if (v.getCantidadDisponible() > 0) {
                    model.addElement(v.getNombre());
                }
            }
        }
        listVacuna.setModel(model);
    }
    
    private void buscarPaciente(String cedulaPaciente) {
        if (cedulaPaciente.trim().isEmpty()) {
            txtNombrePaciente.setText("");
            return;
        }
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(cedulaPaciente);
        if (paciente != null) {
            txtNombrePaciente.setText(paciente.getNombre() + " " + paciente.getApellido());
        } else {
            txtNombrePaciente.setText("No encontrado");
        }
    }
    
    private void clean() {
        fechaVacu.setValue(new Date());
        spnCantMl.setValue(1);
        listVacuna.setSelectedIndex(0);
        txtCodePaciente.setText("");
        txtNombrePaciente.setText("");
        actualizarCodigoVacunacion();
    }
    
    private void actualizarCodigoVacunacion() {
        int proximoId = Clinica.getInstance().getTotalVacunasAplicadas() + 1;
        txtCodeVacu.setText("VA-" + proximoId);
    }
}