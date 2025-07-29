package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import logico.Clinica;
import logico.vacunacion;

public class AgregarVacuna extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTextField txtNombreVacuna;
    private JTextField txtFabricante;
    private JSpinner spnCantidad;

    public AgregarVacuna() {
        setTitle("Agregar Nueva Vacuna al Inventario");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "Datos de la Vacuna", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        JLabel lblNombre = new JLabel("Nombre de la Vacuna:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(30, 50, 150, 25);
        contentPanel.add(lblNombre);

        txtNombreVacuna = new JTextField();
        txtNombreVacuna.setBounds(190, 50, 200, 25);
        contentPanel.add(txtNombreVacuna);
        txtNombreVacuna.setColumns(10);

        JLabel lblFabricante = new JLabel("Fabricante:");
        lblFabricante.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFabricante.setBounds(30, 90, 150, 25);
        contentPanel.add(lblFabricante);

        txtFabricante = new JTextField();
        txtFabricante.setBounds(190, 90, 200, 25);
        contentPanel.add(txtFabricante);
        txtFabricante.setColumns(10);

        JLabel lblCantidad = new JLabel("Cantidad Inicial (Stock):");
        lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCantidad.setBounds(30, 130, 150, 25);
        contentPanel.add(lblCantidad);

        spnCantidad = new JSpinner();
        spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
        spnCantidad.setBounds(190, 130, 80, 25);
        contentPanel.add(spnCantidad);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Agregar al Inventario");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarVacuna();
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }

    private void agregarVacuna() {
        String nombre = txtNombreVacuna.getText();
        String fabricante = txtFabricante.getText();
        int cantidad = (Integer) spnCantidad.getValue();

        if (nombre.trim().isEmpty() || fabricante.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        String idVacuna = "VAC-" + (Clinica.getInstance().getInventarioDeVacunas().size() + 1);

        vacunacion nuevaVacuna = new vacunacion(idVacuna, nombre, fabricante);
        
        Clinica.getInstance().agregarStockVacuna(nuevaVacuna, cantidad);

        JOptionPane.showMessageDialog(this, "Vacuna '" + nombre + "' agregada al inventario con " + cantidad + " unidades.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}