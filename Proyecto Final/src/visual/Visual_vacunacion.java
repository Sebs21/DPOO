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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class Visual_vacunacion extends JFrame {

	 private final JPanel contentPanel = new JPanel();
	    private JTextField txt_code_vacu;
	    private JTextField txt_code_paciente;

	   
	    private JTextField txt_nombre_paciente;
	    private JTextField txt_numero_paciente;
	    private JButton btnGuardar;
	    //
	 
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
		 setTitle("Registro Vacuna");
	        setBounds(100, 100, 868, 564);
	        setLocationRelativeTo(null); 
	        getContentPane().setLayout(new BorderLayout());
			contentPanel.setToolTipText("");
	    
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        getContentPane().add(contentPanel, BorderLayout.CENTER);
	        contentPanel.setLayout(null); 

	        JLabel Codigo_vacu = new JLabel("N.O" );
	        Codigo_vacu.setBounds(21, 32, 192, 26);
	        contentPanel.add(Codigo_vacu);

	        JLabel lblCodigoDepaciente = new JLabel("Codigo de Paciente:");
	        lblCodigoDepaciente.setBounds(21, 86, 192, 26);
	        contentPanel.add(lblCodigoDepaciente);

	        JLabel lblCodigoDeEmpleado = new JLabel("Vacuna:");
	        lblCodigoDeEmpleado.setBounds(21, 149, 192, 26);
	        contentPanel.add(lblCodigoDeEmpleado);

	        JLabel cant_ml = new JLabel("Cantidad ml:");
	        cant_ml.setBounds(21, 216, 112, 26);
	        contentPanel.add(cant_ml);

	        txt_code_vacu = new JTextField();
	        txt_code_vacu.setText("VA-");
	        txt_code_vacu.setEditable(false);
	        txt_code_vacu.setBounds(154, 29, 186, 32);
	        contentPanel.add(txt_code_vacu);

	        txt_code_paciente = new JTextField();
	        txt_code_paciente.setBounds(154, 83, 186, 32);
	        contentPanel.add(txt_code_paciente);

	       
	        txt_nombre_paciente = new JTextField();
	        txt_nombre_paciente.setEditable(false);
	        txt_nombre_paciente.setBounds(350, 83, 186, 32);
	        contentPanel.add(txt_nombre_paciente);

	        txt_numero_paciente = new JTextField();
	        txt_numero_paciente.setEditable(false);
	        txt_numero_paciente.setBounds(546, 83, 186, 32);
	        contentPanel.add(txt_numero_paciente);
	        
	        JSpinner spinner_cant_ml = new JSpinner();
	        spinner_cant_ml.setBounds(117, 213, 61, 32);
	        contentPanel.add(spinner_cant_ml);
	        
	        
	        JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblFecha.setBounds(208, 206, 91, 44);
			contentPanel.add(lblFecha);
			
			JSpinner fecha_vacu = new JSpinner();
			fecha_vacu.setModel(new SpinnerDateModel(new Date(1732593600000L), new Date(1732593600000L), null, Calendar.DAY_OF_YEAR));
			fecha_vacu.setBounds(267, 211, 165, 37);
			contentPanel.add(fecha_vacu);
			
			 JComboBox<Object> tipo_queso = new JComboBox<>();
		        tipo_queso.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Seleccione>"}));
		        tipo_queso.setBounds(154, 146, 186, 32);
		        contentPanel.add(tipo_queso);


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
