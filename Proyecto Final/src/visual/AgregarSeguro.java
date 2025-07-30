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
import logico.Seguro;

public class AgregarSeguro extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombreEmpresa;
    private JTextField txtTipoSeguro;
    private JSpinner spnDescuento;

    public AgregarSeguro() {
        setTitle("Agregar Nuevo Seguro");
        setBounds(100, 100, 450, 320);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "Datos del Seguro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        JLabel lblNombre = new JLabel("Nombre de la Empresa:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(30, 50, 150, 25);
        contentPanel.add(lblNombre);

        txtNombreEmpresa = new JTextField();
        txtNombreEmpresa.setBounds(190, 50, 200, 25);
        contentPanel.add(txtNombreEmpresa);
        txtNombreEmpresa.setColumns(10);

        JLabel lblTipo = new JLabel("Tipo de Seguro:");
        lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTipo.setBounds(30, 90, 150, 25);
        contentPanel.add(lblTipo);

        txtTipoSeguro = new JTextField();
        txtTipoSeguro.setBounds(190, 90, 200, 25);
        contentPanel.add(txtTipoSeguro);
        txtTipoSeguro.setColumns(10);

        JLabel lblDescuento = new JLabel("Descuento (%):");
        lblDescuento.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescuento.setBounds(30, 130, 150, 25);
        contentPanel.add(lblDescuento);

        spnDescuento = new JSpinner();
        // Modelo para permitir valores de 1 a 100 (porcentaje)
        spnDescuento.setModel(new SpinnerNumberModel(50, 1, 100, 1));
        spnDescuento.setBounds(190, 130, 80, 25);
        contentPanel.add(spnDescuento);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("Agregar Seguro");
        okButton.addActionListener(e -> agregarNuevoSeguro());
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }

    private void agregarNuevoSeguro() {
        String nombre = txtNombreEmpresa.getText();
        String tipo = txtTipoSeguro.getText();
        int porcientoDescuento = (Integer) spnDescuento.getValue();

        if (nombre.trim().isEmpty() || tipo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convertir el porcentaje a un valor decimal para los cálculos
        double descuentoDecimal = porcientoDescuento / 100.0;

        String idSeguro = "S-" + Clinica.getIdSeguro();
        Seguro nuevoSeguro = new Seguro(idSeguro, nombre, tipo, descuentoDecimal);
        Clinica.getInstance().agregarSeguro(nuevoSeguro);

        JOptionPane.showMessageDialog(this, "Seguro '" + nombre + "' agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}