package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

public class Reporte_control_enfermedades extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel model;
    private ArrayList<Bajo_vigilancia> vigilanciasRef;
    
    public Reporte_control_enfermedades(ArrayList<Bajo_vigilancia> vigilancias) {
        setTitle("Reporte de Enfermedades Bajo Vigilancia");
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        setModal(true);
        
        vigilanciasRef = vigilancias;
        
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new TitledBorder(null, "Vigilancias del Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        model = new DefaultTableModel();
        String[] headers = {"Paciente", "Enfermedad", "Doctor", "Horas", "Fecha Inicio", "Estado"};
        model.setColumnIdentifiers(headers);
        table = new JTable(model);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnFinalizar = new JButton("Finalizar Vigilancia Seleccionada");
        btnFinalizar.addActionListener(e -> finalizarVigilancia());
        buttonPane.add(btnFinalizar);

        JButton btnEliminar = new JButton("Eliminar Vigilancia Seleccionada");
        btnEliminar.addActionListener(e -> eliminarVigilancia());
        buttonPane.add(btnEliminar);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        cargarDatos(vigilancias);
    }

    private void cargarDatos(ArrayList<Bajo_vigilancia> vigilancias) {
        model.setRowCount(0);
        for (Bajo_vigilancia vigi : vigilancias) {
            Object[] row = {
                vigi.getPaciente().getNombre() + " " + vigi.getPaciente().getApellido(),
                vigi.getEnfermedad().getNombre(),
                vigi.getDoctorResponsable().getNombre() + " " + vigi.getDoctorResponsable().getApellido(),
                vigi.getHorasVigilancia(),
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(vigi.getFechaInicio()),
                vigi.getEstado()
            };
            model.addRow(row);
        }
    }
    
    private void finalizarVigilancia() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Bajo_vigilancia registroSeleccionado = vigilanciasRef.get(selectedRow);
            int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea finalizar la vigilancia para el paciente " + registroSeleccionado.getPaciente().getNombre() + "?",
                "Confirmar Acción",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                Clinica.getInstance().finalizarVigilancia(registroSeleccionado);
                JOptionPane.showMessageDialog(this, "La vigilancia ha sido marcada como 'Finalizada'.", "Éxito", JOptionPane.INFORMATION_MESSAGE);               
                cargarDatos(vigilanciasRef);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una vigilancia de la tabla para finalizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarVigilancia() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Bajo_vigilancia registroSeleccionado = vigilanciasRef.get(selectedRow);
            int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea ELIMINAR la vigilancia para el paciente " + registroSeleccionado.getPaciente().getNombre() + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                Clinica.getInstance().eliminarVigilancia(registroSeleccionado); // Borra de la BD y de memoria
                vigilanciasRef.remove(selectedRow); // Borra de la lista local
                JOptionPane.showMessageDialog(this, "La vigilancia ha sido eliminada de la base de datos.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                cargarDatos(vigilanciasRef);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una vigilancia de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
}