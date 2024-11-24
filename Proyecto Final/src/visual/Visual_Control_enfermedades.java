package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class Visual_Control_enfermedades extends JFrame {

	  private final JPanel contentPanel = new JPanel();
	    private JTextField txt_fact;
	    private JTextField txt_code_cliente;
	    private JTextField txt_code_empleado;
	    private JTextField cod_queso_txt1;

	   
	    private JTextField txt_nombre_cliente;
	    private JTextField txt_numero_cliente;
	    private JTextField txt_nombre_empleado;
	    private JTextField txt_item_producto;
	    private JTextField txt_costo_producto;
	    private JButton btnGuardar;
	    private JLabel monitoreo;
	    private JTextField textField;
	    private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visual_Control_enfermedades frame = new Visual_Control_enfermedades();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Visual_Control_enfermedades() {
		 setTitle("Registro Vigilancia");
	        setBounds(100, 100, 868, 564);
	        setLocationRelativeTo(null); 
	        getContentPane().setLayout(new BorderLayout());
	        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        getContentPane().add(contentPanel, BorderLayout.CENTER);
	        contentPanel.setLayout(null); 

	        JLabel Codigo_fac = new JLabel("N.O" );
	        Codigo_fac.setBounds(21, 32, 192, 26);
	        contentPanel.add(Codigo_fac);

	        JLabel lblCodigoDepaciente = new JLabel("Codigo de Paciente:");
	        lblCodigoDepaciente.setBounds(21, 86, 192, 26);
	        contentPanel.add(lblCodigoDepaciente);

	        JLabel lblCodigoDeEmpleado = new JLabel("Codigo de Doctor:");
	        lblCodigoDeEmpleado.setBounds(21, 149, 192, 26);
	        contentPanel.add(lblCodigoDeEmpleado);

	        JLabel cod_queso_jbl = new JLabel("Enfermedad:");
	        cod_queso_jbl.setBounds(21, 254, 192, 26);
	        contentPanel.add(cod_queso_jbl);

	        txt_fact = new JTextField();
	        txt_fact.setText("VI-");
	        txt_fact.setEditable(false);
	        txt_fact.setBounds(234, 29, 186, 32);
	        contentPanel.add(txt_fact);

	        txt_code_cliente = new JTextField();
	        txt_code_cliente.setBounds(234, 83, 186, 32);
	        contentPanel.add(txt_code_cliente);

	        txt_code_empleado = new JTextField();
	        txt_code_empleado.setBounds(234, 146, 186, 32);
	        contentPanel.add(txt_code_empleado);

	        cod_queso_txt1 = new JTextField();
	        cod_queso_txt1.setBounds(234, 245, 186, 32);
	        contentPanel.add(cod_queso_txt1);

	       
	        txt_nombre_cliente = new JTextField();
	        txt_nombre_cliente.setEditable(false);
	        txt_nombre_cliente.setBounds(430, 83, 186, 32);
	        contentPanel.add(txt_nombre_cliente);

	        txt_numero_cliente = new JTextField();
	        txt_numero_cliente.setEditable(false);
	        txt_numero_cliente.setBounds(630, 83, 186, 32);
	        contentPanel.add(txt_numero_cliente);

	        txt_nombre_empleado = new JTextField();
	        txt_nombre_empleado.setEditable(false);
	        txt_nombre_empleado.setBounds(430, 146, 186, 32);
	        contentPanel.add(txt_nombre_empleado);

	        txt_item_producto = new JTextField();
	        txt_item_producto.setEditable(false);
	        txt_item_producto.setBounds(430, 245, 186, 32);
	        contentPanel.add(txt_item_producto);

	        txt_costo_producto = new JTextField();
	        txt_costo_producto.setEditable(false);
	        txt_costo_producto.setBounds(630, 248, 186, 32);
	        contentPanel.add(txt_costo_producto);

	        JLabel lbl_Cant_hora = new JLabel("Cantidad hora:");
	        lbl_Cant_hora.setBounds(21, 301, 192, 26);
	        contentPanel.add(lbl_Cant_hora);

	        JSpinner cant_spinner = new JSpinner();
	        cant_spinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
	        cant_spinner.setBounds(234, 298, 186, 32);
	        contentPanel.add(cant_spinner);
	        
	        monitoreo = new JLabel("Monitoreo:");
	        monitoreo.setBounds(21, 350, 192, 26);
	        contentPanel.add(monitoreo);
	        
	        JCheckBox chckbxNewCheckBox = new JCheckBox("Activo");
	        chckbxNewCheckBox.setBounds(241, 346, 179, 35);
	        contentPanel.add(chckbxNewCheckBox);
	        
	        JCheckBox chckbxInactivo = new JCheckBox("Inactivo");
	        chckbxInactivo.setBounds(430, 346, 179, 35);
	        contentPanel.add(chckbxInactivo);
	        
	        textField = new JTextField();
	        textField.setEditable(false);
	        textField.setBounds(430, 192, 186, 32);
	        contentPanel.add(textField);
	        
	        textField_1 = new JTextField();
	        textField_1.setBounds(234, 192, 186, 32);
	        contentPanel.add(textField_1);
	        
	        JLabel lblCodigoDeEnfermera = new JLabel("Codigo de Enfermera:");
	        lblCodigoDeEnfermera.setBounds(21, 196, 206, 26);
	        contentPanel.add(lblCodigoDeEnfermera);

	        JPanel buttonPane = new JPanel();
	        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        getContentPane().add(buttonPane, BorderLayout.SOUTH);

	     
	        
	        btnGuardar = new JButton("Guardar");
	        btnGuardar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        	}
	        });
	        btnGuardar.setActionCommand("Guardar");
	        buttonPane.add(btnGuardar);
	        
	        JButton cancelButton = new JButton("Cancelar");
	        cancelButton.setActionCommand("Cancel");
	        buttonPane.add(cancelButton);

	       
	        txt_code_cliente.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	             //   buscarCliente(txt_code_cliente.getText());
	            }
	        });

	        txt_code_empleado.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	                //buscarEmpleado(txt_code_empleado.getText());
	            }
	        });

	        cod_queso_txt1.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	              //  buscarProducto(cod_queso_txt1.getText());
	            }
	        });
	}
}
