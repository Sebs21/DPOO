package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Consulta;
import logico.Facturar;
import logico.Paciente;
import logico.RegistroVacunacion;

public class Facturacion extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtCedulaPaciente;
    private JTextField txtNombrePaciente;
    private JTable tableServicios;
    private DefaultTableModel model;
    private Paciente pacienteSeleccionado = null;
    private JTextField txtSubtotal;
    private JTextField txtDescuento;
    private JTextField txtTotalPagar;
    
    private ArrayList<Consulta> consultasPendientes = new ArrayList<>();
    private ArrayList<RegistroVacunacion> vacunasPendientes = new ArrayList<>();

    public Facturacion() {
        setTitle("Facturación");
        setBounds(100, 100, 800, 600);
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setModal(true);

        JPanel panelBusqueda = new JPanel();
        getContentPane().add(panelBusqueda, BorderLayout.NORTH);
        
        JLabel lblNewLabel = new JLabel("Cédula del Paciente:");
        panelBusqueda.add(lblNewLabel);
        
        txtCedulaPaciente = new JTextField();
        panelBusqueda.add(txtCedulaPaciente);
        txtCedulaPaciente.setColumns(15);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarServiciosPendientes());
        panelBusqueda.add(btnBuscar);
        
        txtNombrePaciente = new JTextField();
        txtNombrePaciente.setEditable(false);
        panelBusqueda.add(txtNombrePaciente);
        txtNombrePaciente.setColumns(20);

        JPanel panelTabla = new JPanel();
        panelTabla.setBorder(new TitledBorder(null, "Servicios Pendientes de Pago", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(panelTabla, BorderLayout.CENTER);
        panelTabla.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panelTabla.add(scrollPane, BorderLayout.CENTER);
        
        model = new DefaultTableModel();
        String[] headers = {"Concepto", "Fecha", "Precio RD$"};
        model.setColumnIdentifiers(headers);
        tableServicios = new JTable(model);
        scrollPane.setViewportView(tableServicios);

        JPanel panelTotales = new JPanel();
        getContentPane().add(panelTotales, BorderLayout.EAST);
        panelTotales.setLayout(null);
        panelTotales.setPreferredSize(new java.awt.Dimension(200, 0));
        
        JLabel lblSubtotal = new JLabel("Subtotal:");
        lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSubtotal.setBounds(10, 30, 80, 25);
        panelTotales.add(lblSubtotal);
        
        txtSubtotal = new JTextField();
        txtSubtotal.setEditable(false);
        txtSubtotal.setBounds(100, 30, 90, 25);
        panelTotales.add(txtSubtotal);
        
        JLabel lblDescuento = new JLabel("Descuento:");
        lblDescuento.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDescuento.setBounds(10, 70, 90, 25);
        panelTotales.add(lblDescuento);
        
        txtDescuento = new JTextField();
        txtDescuento.setEditable(false);
        txtDescuento.setBounds(100, 70, 90, 25);
        panelTotales.add(txtDescuento);
        
        JLabel lblTotalAPagar = new JLabel("TOTAL:");
        lblTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalAPagar.setBounds(10, 110, 80, 25);
        panelTotales.add(lblTotalAPagar);
        
        txtTotalPagar = new JTextField();
        txtTotalPagar.setEditable(false);
        txtTotalPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtTotalPagar.setForeground(Color.BLUE);
        txtTotalPagar.setBounds(100, 110, 90, 25);
        panelTotales.add(txtTotalPagar);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnFacturar = new JButton("Facturar");
        btnFacturar.addActionListener(e -> procesarFactura());
        buttonPane.add(btnFacturar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }
    
    private void buscarServiciosPendientes() {
        String cedula = txtCedulaPaciente.getText();
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una cédula.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        pacienteSeleccionado = Clinica.getInstance().buscarPacienteByCedula(cedula);
        model.setRowCount(0);
        consultasPendientes.clear();
        vacunasPendientes.clear();
        
        if (pacienteSeleccionado == null) {
            txtNombrePaciente.setText("Paciente no encontrado.");
            limpiarCalculos();
            return;
        }
        
        txtNombrePaciente.setText(pacienteSeleccionado.getNombre() + " " + pacienteSeleccionado.getApellido());
        
        double subtotal = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // Cargar consultas pendientes
        if (pacienteSeleccionado.getMiHistoriaClinica() != null) {
            for (Consulta c : pacienteSeleccionado.getMiHistoriaClinica().getMisConsultas()) {
                if (!c.isPagada()) {
                    consultasPendientes.add(c);
                    Object[] row = {"Consulta - " + c.getEnfermedad(), sdf.format(c.getFechaConsulta()), c.getPrecio()};
                    model.addRow(row);
                    subtotal += c.getPrecio();
                }
            }
        }
        
        // Cargar vacunas pendientes
        if (pacienteSeleccionado.getMisVacunasAplicadas() != null) {
            for (RegistroVacunacion r : pacienteSeleccionado.getMisVacunasAplicadas()) {
                if (!r.isPagada()) {
                    vacunasPendientes.add(r);
                    Object[] row = {"Vacuna - " + r.getTipoVacuna().getNombre(), sdf.format(r.getFechaAplicacion()), r.getTipoVacuna().getPrecio()};
                    model.addRow(row);
                    subtotal += r.getTipoVacuna().getPrecio();
                }
            }
        }
        
        calcularTotales(subtotal);
    }
    
    private void calcularTotales(double subtotal) {
        double porcientoDescuento = 0;
        if (pacienteSeleccionado.getSeguro() != null) {
            porcientoDescuento = pacienteSeleccionado.getSeguro().getDescuento();
        }
        
        double montoDescuento = subtotal * porcientoDescuento;
        double totalAPagar = subtotal - montoDescuento;
        
        txtSubtotal.setText(String.format("%.2f", subtotal));
        txtDescuento.setText(String.format("%.2f (%.0f%%)", montoDescuento, porcientoDescuento * 100));
        txtTotalPagar.setText(String.format("%.2f", totalAPagar));
    }
    
    private void procesarFactura() {
        if (pacienteSeleccionado == null || (consultasPendientes.isEmpty() && vacunasPendientes.isEmpty())) {
            JOptionPane.showMessageDialog(this, "No hay servicios pendientes para facturar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Marcar todos los servicios como pagados
        for (Consulta c : consultasPendientes) {
            c.setPagada(true);
        }
        for (RegistroVacunacion r : vacunasPendientes) {
            r.setPagada(true);
        }

        // Crear y guardar la factura (lógica simplificada)
        String idFactura = "FACT-" + Clinica.getIdFactura();
        double subtotal = Double.parseDouble(txtSubtotal.getText());
        double totalPagado = Double.parseDouble(txtTotalPagar.getText());
        double descuento = subtotal - totalPagado;
        
        Facturar nuevaFactura = new Facturar(idFactura, pacienteSeleccionado, new Date(), consultasPendientes, vacunasPendientes, subtotal, descuento, totalPagado);
        Clinica.getInstance().agregarFactura(nuevaFactura); // Asumiendo que existe este método

        JOptionPane.showMessageDialog(this, "Factura generada y servicios marcados como pagados.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        // Limpiar la interfaz
        pacienteSeleccionado = null;
        txtCedulaPaciente.setText("");
        txtNombrePaciente.setText("");
        model.setRowCount(0);
        limpiarCalculos();
    }
    
    private void limpiarCalculos() {
        txtSubtotal.setText("");
        txtDescuento.setText("");
        txtTotalPagar.setText("");
    }
}