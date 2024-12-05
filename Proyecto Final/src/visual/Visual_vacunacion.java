package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;

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

import logico.Control_vacunacion;
import logico.Paciente;
import logico.vacunacion;

public class Visual_vacunacion extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodeVacu;
    private JTextField txtCodePaciente;
    private JTextField txtNombrePaciente;
    private JButton btnGuardar;
    private JComboBox<Object> listVacuna;
    private JSpinner fechaVacu;
    private JSpinner spnCantMl;

    public Visual_vacunacion() {
        setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
        setTitle("Registro Vacuna");
        setBounds(100, 100, 868, 564);
        setLocationRelativeTo(null);
        setModal(true); 
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCodeVacu = new JLabel("N.O");
        lblCodeVacu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        lblCodeVacu.setBounds(21, 32, 192, 26);
        contentPanel.add(lblCodeVacu);

        JLabel lblCodePaciente = new JLabel("Código de Paciente:");
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
        txtCodeVacu.setText("VA-" + Control_vacunacion.code_vacu);
        txtCodeVacu.setEditable(false);
        txtCodeVacu.setBounds(154, 29, 186, 32);
        contentPanel.add(txtCodeVacu);

        txtCodePaciente = new JTextField();
        txtCodePaciente.setBounds(154, 83, 186, 32);
        contentPanel.add(txtCodePaciente);

        txtNombrePaciente = new JTextField();
        txtNombrePaciente.setEditable(false);
        txtNombrePaciente.setBounds(350, 83, 186, 32);
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
        listVacuna.setModel(new DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Influenza", "Papiloma Humano", "Neumonía", "Meningitis" }));
        listVacuna.setBounds(154, 146, 186, 32);
        contentPanel.add(listVacuna);

        spnCantMl = new JSpinner();
        spnCantMl.setModel(new SpinnerNumberModel(1, 0, 10, 1));
        spnCantMl.setBounds(108, 218, 66, 22);
        contentPanel.add(spnCantMl);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
        btnGuardar.addActionListener(this::guardarVacunacion);
        btnGuardar.setActionCommand("Guardar");
        buttonPane.add(btnGuardar);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
        cancelButton.addActionListener(e -> dispose());
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        txtCodePaciente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarPaciente(txtCodePaciente.getText());
            }
        });
    }

    private void guardarVacunacion(ActionEvent e) {
        try {
            String codigoPaciente = txtCodePaciente.getText();
            String tipoVacuna = listVacuna.getSelectedItem().toString();
            float cantMl = ((Integer) spnCantMl.getValue()).floatValue();
            int codigo = Integer.parseInt(txtCodeVacu.getText().replace("VA-", ""));
            Date fechaVacunacion = (Date) fechaVacu.getValue();

            if (codigoPaciente.isEmpty()) {
                throw new IllegalArgumentException("El espacio de codigo de paciente esta vacio.");
            }
            if ("<Seleccione>".equals(tipoVacuna)) {
                throw new IllegalArgumentException("Debe seleccionar algun tipo de vacuna.");
            }

            Paciente paciente = Control_vacunacion.verificar_code_paciente(codigoPaciente);
            if (paciente == null) {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            vacunacion newVacu = new vacunacion(codigo, tipoVacuna, fechaVacunacion, true, codigoPaciente, cantMl);
            Control_vacunacion.getVacunaciones().add(newVacu);
            paciente.agregarVacuna(newVacu);

            JOptionPane.showMessageDialog(null, "Registrada exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            clean();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPaciente(String codigoPaciente) {
        Paciente paciente = Control_vacunacion.verificar_code_paciente(codigoPaciente);
        if (paciente != null) {
            txtNombrePaciente.setText(paciente.getNombre());
        } else {
            txtNombrePaciente.setText("");
        }
    }

    private void clean() {
        fechaVacu.setValue(new Date());
        spnCantMl.setValue(0);
        listVacuna.setSelectedIndex(0);
        txtCodePaciente.setText("");
        txtNombrePaciente.setText("");
        txtCodeVacu.setText("VA-" + Control_vacunacion.code_vacu);
    }
}
