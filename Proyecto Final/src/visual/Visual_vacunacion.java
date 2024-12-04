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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import logico.Control_vacunacion;
import logico.vacunacion;
import javax.swing.SpinnerNumberModel;

public class Visual_vacunacion extends JFrame {

	 private final JPanel contentPanel = new JPanel();
	    private JTextField txt_code_vacu;
	    private JTextField txt_code_paciente;

	   
	    private JTextField txt_nombre_paciente;
	    private JTextField txt_numero_paciente;
	    private JButton btnGuardar;
	    private  JComboBox<Object> list_vacuna ;
	    private JSpinner fecha_vacu ;
	    private JSpinner spn_cant_ml;
	    
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
	        txt_code_vacu.setText("VA-" + logico.Control_vacunacion.code_vacu);
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
	        
	        
	        JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblFecha.setBounds(208, 206, 91, 44);
			contentPanel.add(lblFecha);

			fecha_vacu = new JSpinner();
			fecha_vacu.setModel( new SpinnerDateModel( new Date(), null, null, Calendar.DAY_OF_MONTH ) );
			fecha_vacu.setBounds(267, 211, 165, 37);
			contentPanel.add(fecha_vacu);
			
			 list_vacuna = new JComboBox<>();
			 list_vacuna.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Influenza", "Papiloma Humano", "Neumon\u00EDa", "Meningitis"}));
			 list_vacuna.setBounds(154, 146, 186, 32);
		        contentPanel.add(list_vacuna);
		        
		        spn_cant_ml = new JSpinner();
		        spn_cant_ml.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		        spn_cant_ml.setBounds(108, 218, 66, 22);
		        contentPanel.add(spn_cant_ml);


	        JPanel buttonPane = new JPanel();
	        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        getContentPane().add(buttonPane, BorderLayout.SOUTH);

	     
	        
	        btnGuardar = new JButton("Guardar");
	        btnGuardar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		try {
	        			
	        			int codigo =Integer.parseInt(txt_code_vacu.getText().replace("VA-", ""));
	        			 String codigo_paciente = txt_code_paciente.getText();
	        			String tipo_vacuna = list_vacuna.getSelectedItem().toString();
	        			float cant_ml =(float)  spinner_cant_ml.getValue();
	                    Date fecha_Vacunacion = (Date) ((SpinnerDateModel) fecha_vacu.getModel()).getValue();

	                    if ("<Seleccione>".equals(tipo_vacuna)) {
	                        throw new IllegalArgumentException("Debe seleccionar algun tipo de vacuna.");
	                    }
	                     Control_vacunacion.verificar_code_paciente(codigo_paciente);
	                     if(codigo_paciente==null)
	                     {
	                    	 JOptionPane.showMessageDialog(null, "No existe.", "Error", JOptionPane.ERROR_MESSAGE);
	                         return;
	                     }
	                     vacunacion newvacu = new vacunacion(codigo, tipo_vacuna, fecha_Vacunacion, true, codigo_paciente, cant_ml);
	                     Control_vacunacion.getVacunaciones().add(newvacu);
	                     String rutaArchivo = "registro_vacunacion.txt";
	                     Control_vacunacion guar = new Control_vacunacion();
	                     guar.Guarda_vacuna(rutaArchivo);
	                     
	                     JOptionPane.showMessageDialog(null, "Vacunación registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

	                        			
	  
						
					} catch (Exception e2) {
	                    JOptionPane.showMessageDialog(null, "Error al guardar la vacunación: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
	        		
	        	}
	        });
	        btnGuardar.setActionCommand("Guardar");
	        buttonPane.add(btnGuardar);
	        
	        JButton cancelButton = new JButton("Cancelar");
	        cancelButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) 
	        	{
	        		dispose();
	        	}
	        });
	        cancelButton.setActionCommand("Cancel");
	        buttonPane.add(cancelButton);

	       
	        txt_code_paciente.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	             //   buscarCliente(txt_code_cliente.getText());
	            }
	        });
	}
	
	public void niñosVacuna ()
	{
		
	}
}

