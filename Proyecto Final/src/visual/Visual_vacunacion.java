package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Visual_vacunacion extends JFrame {

	 private final JPanel contentPanel = new JPanel();
	    private JTextField txt_code_enfe;
	    private JTextField txt_code_paciente;

	   
	    private JTextField txt_nombre_paciente;
	    private JTextField txt_numero_paciente;
	    private JButton btnGuardar;
	    private JLabel monitoreo;
	    private JTextField textField;
	    private JTextField textField_1;

	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visual_vacunacion frame = new Visual_vacunacion();
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
	public Visual_vacunacion() {
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
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

	        JLabel lblCodigoDeEmpleado = new JLabel("Vacuna:");
	        lblCodigoDeEmpleado.setBounds(21, 149, 192, 26);
	        contentPanel.add(lblCodigoDeEmpleado);

	        JLabel cod_queso_jbl = new JLabel("Cantidad ml:");
	        cod_queso_jbl.setBounds(21, 214, 192, 26);
	        contentPanel.add(cod_queso_jbl);

	        txt_code_enfe = new JTextField();
	        txt_code_enfe.setText("VA-");
	        txt_code_enfe.setEditable(false);
	        txt_code_enfe.setBounds(234, 29, 186, 32);
	        contentPanel.add(txt_code_enfe);

	        txt_code_paciente = new JTextField();
	        txt_code_paciente.setBounds(234, 83, 186, 32);
	        contentPanel.add(txt_code_paciente);

	       
	        txt_nombre_paciente = new JTextField();
	        txt_nombre_paciente.setEditable(false);
	        txt_nombre_paciente.setBounds(430, 83, 186, 32);
	        contentPanel.add(txt_nombre_paciente);

	        txt_numero_paciente = new JTextField();
	        txt_numero_paciente.setEditable(false);
	        txt_numero_paciente.setBounds(630, 83, 186, 32);
	        contentPanel.add(txt_numero_paciente);
	        
	        monitoreo = new JLabel("Monitoreo:");
	        monitoreo.setBounds(21, 310, 192, 26);
	        contentPanel.add(monitoreo);
	        
	        JCheckBox chckbxNewCheckBox = new JCheckBox("Activo");
	        chckbxNewCheckBox.setBounds(241, 306, 179, 35);
	        contentPanel.add(chckbxNewCheckBox);
	        
	        JCheckBox chckbxInactivo = new JCheckBox("Inactivo");
	        chckbxInactivo.setBounds(430, 306, 179, 35);
	        contentPanel.add(chckbxInactivo);
	        
	        JButton btnNewButton = new JButton("\" \"");
	        btnNewButton.setBounds(430, 145, 56, 35);
	        contentPanel.add(btnNewButton);
	        
	        textField = new JTextField();
	        textField.setEditable(false);
	        textField.setBounds(501, 146, 186, 32);
	        contentPanel.add(textField);
	        
	        textField_1 = new JTextField();
	        textField_1.setBounds(234, 146, 186, 32);
	        contentPanel.add(textField_1);
	        
	        JSpinner spinner = new JSpinner();
	        spinner.setBounds(234, 211, 186, 32);
	        contentPanel.add(spinner);
	        
	        
	        JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblFecha.setBounds(32, 160, 91, 44);
			panel.add(lblFecha);
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1732593600000L), new Date(1732593600000L), null, Calendar.DAY_OF_YEAR));
			spinner.setBounds(133, 167, 165, 37);
			panel.add(spinner);

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
	}
}
