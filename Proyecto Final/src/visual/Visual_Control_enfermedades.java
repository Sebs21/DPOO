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
import java.awt.Toolkit;

public class Visual_Control_enfermedades extends JFrame {

	  private final JPanel contentPanel = new JPanel();
	    private JTextField txt_code_enfe;
	    private JTextField txt_code_paciente;
	    private JTextField txt_code_doctor;
	    private JTextField cod_;

	   
	    private JTextField txt_nombre_paciente;
	    private JTextField txt_numero_paciente;
	    private JTextField txt_nombre_doctor;
	    private JTextField txt_item_producto;
	    private JTextField txt_costo_producto;
	    private JButton btnGuardar;
	    private JLabel monitoreo;
	    private JTextField txt_expecialidad;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\esmil\\OneDrive\\Documentos\\SIGIC_logo.jpg"));
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
	        cod_queso_jbl.setBounds(21, 214, 192, 26);
	        contentPanel.add(cod_queso_jbl);

	        txt_code_enfe = new JTextField();
	        txt_code_enfe.setText("VI-");
	        txt_code_enfe.setEditable(false);
	        txt_code_enfe.setBounds(234, 29, 186, 32);
	        contentPanel.add(txt_code_enfe);

	        txt_code_paciente = new JTextField();
	        txt_code_paciente.setBounds(234, 83, 186, 32);
	        contentPanel.add(txt_code_paciente);

	        txt_code_doctor = new JTextField();
	        txt_code_doctor.setBounds(234, 146, 186, 32);
	        contentPanel.add(txt_code_doctor);

	        cod_ = new JTextField();
	        cod_.setBounds(234, 205, 186, 32);
	        contentPanel.add(cod_);

	       
	        txt_nombre_paciente = new JTextField();
	        txt_nombre_paciente.setEditable(false);
	        txt_nombre_paciente.setBounds(430, 83, 186, 32);
	        contentPanel.add(txt_nombre_paciente);

	        txt_numero_paciente = new JTextField();
	        txt_numero_paciente.setEditable(false);
	        txt_numero_paciente.setBounds(630, 83, 186, 32);
	        contentPanel.add(txt_numero_paciente);

	        txt_nombre_doctor = new JTextField();
	        txt_nombre_doctor.setEditable(false);
	        txt_nombre_doctor.setBounds(430, 146, 186, 32);
	        contentPanel.add(txt_nombre_doctor);

	        txt_item_producto = new JTextField();
	        txt_item_producto.setEditable(false);
	        txt_item_producto.setBounds(430, 205, 186, 32);
	        contentPanel.add(txt_item_producto);

	        txt_costo_producto = new JTextField();
	        txt_costo_producto.setEditable(false);
	        txt_costo_producto.setBounds(630, 208, 186, 32);
	        contentPanel.add(txt_costo_producto);

	        JLabel lbl_Cant_hora = new JLabel("Cantidad hora:");
	        lbl_Cant_hora.setBounds(21, 261, 192, 26);
	        contentPanel.add(lbl_Cant_hora);

	        JSpinner cant_spinner = new JSpinner();
	        cant_spinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
	        cant_spinner.setBounds(234, 258, 186, 32);
	        contentPanel.add(cant_spinner);
	        
	        monitoreo = new JLabel("Monitoreo:");
	        monitoreo.setBounds(21, 310, 192, 26);
	        contentPanel.add(monitoreo);
	        
	        JCheckBox chckbxNewCheckBox = new JCheckBox("Activo");
	        chckbxNewCheckBox.setBounds(241, 306, 179, 35);
	        contentPanel.add(chckbxNewCheckBox);
	        
	        JCheckBox chckbxInactivo = new JCheckBox("Inactivo");
	        chckbxInactivo.setBounds(430, 306, 179, 35);
	        contentPanel.add(chckbxInactivo);
	        
	        txt_expecialidad = new JTextField();
	        txt_expecialidad.setEditable(false);
	        txt_expecialidad.setBounds(630, 146, 186, 32);
	        contentPanel.add(txt_expecialidad);

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

	       
	        txt_code_paciente.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	             //   buscarCliente(txt_code_cliente.getText());
	            }
	        });

	        txt_code_doctor.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	                //buscarEmpleado(txt_code_empleado.getText());
	            }
	        });

	        cod_.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	              //  buscarProducto(cod_queso_txt1.getText());
	            }
	        });
	}
}
