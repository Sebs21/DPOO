package visual;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import logico.Clinica;
import logico.Control_vacunacion;
import logico.Paciente;
import logico.vacunacion;

public class VacunaXPaciente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            VacunaXPaciente dialog = new VacunaXPaciente();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public VacunaXPaciente() {
        setTitle("Vacunas del Paciente");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        String[] columnNames = {"Nombre de Vacuna", "ML", "Fecha"};
        tableModel.setColumnIdentifiers(columnNames);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        
        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.setLayout(new BorderLayout(0, 0));
        buttonPane.add(closeButton, BorderLayout.EAST);
    }
    
    public void ListVacunas(String id) {
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(id);
        if (paciente != null) {

            ArrayList<vacunacion> vacunas = paciente.getMiVacuna();
            for (vacunacion vacuna : vacunas) {
               
                Object[] rowData = {
                    vacuna.getTipoVacuna(),
                    vacuna.getCant_ml(),
                    vacuna.getFecha()
                };
                tableModel.addRow(rowData);
            }
        }
    }

}
