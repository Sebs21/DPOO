package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombreVacuna;
    private JTextField txtFabricante;
    private JSpinner spnCantidad;
    private JSpinner spnPrecio; 

    public AgregarVacuna() {
        setTitle("Agregar Nueva Vacuna al Inventario");
        setBounds(100, 100, 480, 350); 
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
        txtNombreVacuna.setBounds(220, 50, 200, 25);
        contentPanel.add(txtNombreVacuna);

        JLabel lblFabricante = new JLabel("Fabricante:");
        lblFabricante.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFabricante.setBounds(30, 90, 150, 25);
        contentPanel.add(lblFabricante);

        txtFabricante = new JTextField();
        txtFabricante.setBounds(220, 90, 200, 25);
        contentPanel.add(txtFabricante);

        JLabel lblPrecio = new JLabel("Precio Unitario (RD$):");
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrecio.setBounds(30, 130, 150, 25);
        contentPanel.add(lblPrecio);

        spnPrecio = new JSpinner();
        spnPrecio.setModel(new SpinnerNumberModel(100.0, 0.0, 100000.0, 50.0));
        spnPrecio.setBounds(220, 130, 100, 25);
        contentPanel.add(spnPrecio);

        JLabel lblCantidad = new JLabel("Cantidad Inicial (Stock):");
        lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCantidad.setBounds(30, 170, 150, 25);
        contentPanel.add(lblCantidad);

        spnCantidad = new JSpinner();
        spnCantidad.setModel(new SpinnerNumberModel(1, 1, null, 1));
        spnCantidad.setBounds(220, 170, 100, 25);
        contentPanel.add(spnCantidad);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Agregar al Inventario");
        okButton.addActionListener(e -> agregarVacuna());
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }

    private void agregarVacuna() {
        String nombre = txtNombreVacuna.getText();
        String fabricante = txtFabricante.getText();
        double precio = (Double) spnPrecio.getValue(); 
        int cantidad = (Integer) spnCantidad.getValue();

        if (nombre.trim().isEmpty() || fabricante.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String idVacuna = "VAC-" + (Clinica.getInstance().getInventarioDeVacunas().size() + 1);

        vacunacion nuevaVacuna = new vacunacion(idVacuna, nombre, fabricante, precio);
        
        Clinica.getInstance().agregarStockVacuna(nuevaVacuna, cantidad);

        JOptionPane.showMessageDialog(this, "Vacuna '" + nombre + "' agregada al inventario.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}