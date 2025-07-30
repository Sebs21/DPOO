package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Bajo_vigilancia;
import logico.Clinica;

public class GestionarVigilancia extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTable tableVigilancia;
    private DefaultTableModel model;
    private ArrayList<Bajo_vigilancia> vigilanciasActivas;

    public GestionarVigilancia(JDialog parent) {
        super(parent, true);
        setTitle("Gestionar Pacientes en Vigilancia");
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new TitledBorder(null, "Pacientes Actualmente en Vigilancia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        model = new DefaultTableModel();
        String[] headers = {"Paciente", "Enfermedad", "Doctor Responsable", "Horas", "Fecha Inicio", "Estado"};
        model.setColumnIdentifiers(headers);
        tableVigilancia = new JTable(model);
        scrollPane.setViewportView(tableVigilancia);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnFinalizar = new JButton("Finalizar Vigilancia Seleccionada");
        btnFinalizar.addActionListener(e -> finalizarVigilancia());
        buttonPane.add(btnFinalizar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        buttonPane.add(btnCerrar);

        cargarVigilanciasActivas();
    }

    private void cargarVigilanciasActivas() {
        model.setRowCount(0);
        vigilanciasActivas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Bajo_vigilancia vigi : Clinica.getInstance().getMisVigilancias()) {
            // Solo mostramos las vigilancias que están "Activas"
            if (vigi.getEstado().equalsIgnoreCase("Activa")) {
                vigilanciasActivas.add(vigi); // Guardamos la referencia
                Object[] row = {
                    vigi.getPaciente().getNombre() + " " + vigi.getPaciente().getApellido(),
                    vigi.getEnfermedad(),
                    vigi.getDoctorResponsable().getNombre(),
                    vigi.getHorasVigilancia(),
                    sdf.format(vigi.getFechaInicio()),
                    vigi.getEstado()
                };
                model.addRow(row);
            }
        }
    }

    private void finalizarVigilancia() {
        int selectedRow = tableVigilancia.getSelectedRow();
        if (selectedRow != -1) {
            // Obtenemos el registro de vigilancia de nuestra lista filtrada
            Bajo_vigilancia registroSeleccionado = vigilanciasActivas.get(selectedRow);

            int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea finalizar la vigilancia para el paciente " + registroSeleccionado.getPaciente().getNombre() + "?",
                "Confirmar Acción",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Llamamos al método de la clínica para cambiar el estado
                Clinica.getInstance().finalizarVigilancia(registroSeleccionado);
                JOptionPane.showMessageDialog(this, "La vigilancia ha sido marcada como 'Finalizada'.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarVigilanciasActivas(); // Recargamos la tabla para que el registro desaparezca
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una vigilancia de la tabla para finalizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}