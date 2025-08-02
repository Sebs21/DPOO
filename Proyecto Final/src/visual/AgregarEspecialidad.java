package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import logico.Clinica;
import logico.Especialidad;

public class AgregarEspecialidad extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombreEspecialidad;

    public AgregarEspecialidad() {
        setTitle("Gestionar Especialidades Médicas");
        setBounds(100, 100, 450, 220);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "Nueva Especialidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        JLabel lblNombre = new JLabel("Nombre de la Especialidad:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(30, 60, 180, 25);
        contentPanel.add(lblNombre);

        txtNombreEspecialidad = new JTextField();
        txtNombreEspecialidad.setBounds(220, 60, 180, 25);
        contentPanel.add(txtNombreEspecialidad);
        txtNombreEspecialidad.setColumns(10);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Agregar");
        okButton.addActionListener(e -> agregarNuevaEspecialidad());
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }

    private void agregarNuevaEspecialidad() {
        String nombre = txtNombreEspecialidad.getText();

        if (nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nuevoId = "ESP-" + Clinica.getIdEspecialidad();
        Especialidad nuevaEspecialidad = new Especialidad(nuevoId, nombre);

        Clinica.getInstance().agregarEspecialidad(nuevaEspecialidad);

        JOptionPane.showMessageDialog(this, "Especialidad '" + nombre + "' agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}