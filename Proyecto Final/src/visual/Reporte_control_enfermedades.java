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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

public class Reporte_control_enfermedades extends JFrame {

	  private final JPanel contentPanel = new JPanel();
	    private JTextField txt_code_paciente;

	   
	    private JTextField txt_nombre_paciente;
	    private JButton btnGuardar;
	    //
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte_control_enfermedades frame = new Reporte_control_enfermedades();
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
	public Reporte_control_enfermedades() {
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		 setTitle("Reporte vigilancia");
	        setBounds(100, 100, 868, 564);
	        setLocationRelativeTo(null); 
	        getContentPane().setLayout(new BorderLayout());
	        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        getContentPane().add(contentPanel, BorderLayout.CENTER);
	        contentPanel.setLayout(null);

	        JLabel lblCodigoDepaciente = new JLabel("Codigo de Paciente:");
	        lblCodigoDepaciente.setBounds(21, 174, 192, 26);
	        contentPanel.add(lblCodigoDepaciente);

	        txt_code_paciente = new JTextField();
	        txt_code_paciente.setBounds(234, 171, 186, 32);
	        contentPanel.add(txt_code_paciente);

	       
	        txt_nombre_paciente = new JTextField();
	        txt_nombre_paciente.setEditable(false);
	        txt_nombre_paciente.setBounds(430, 171, 186, 32);
	        contentPanel.add(txt_nombre_paciente);
	        
	        JLabel lblFechaInicial = new JLabel("Fecha Inicial:");
	        lblFechaInicial.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
	        lblFechaInicial.setBounds(21, 41, 91, 44);
	        contentPanel.add(lblFechaInicial);
	        
	    	JSpinner fecha_vacu = new JSpinner();
			fecha_vacu.setModel(new SpinnerDateModel(new Date(1732593600000L), new Date(1732593600000L), null, Calendar.DAY_OF_YEAR));
			fecha_vacu.setBounds(133, 48, 165, 37);
			contentPanel.add(fecha_vacu);
			
			JLabel lblFechaFinal = new JLabel("Fecha final:");
			lblFechaFinal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblFechaFinal.setBounds(21, 106, 91, 44);
			contentPanel.add(lblFechaFinal);
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerDateModel(new Date(1732593600000L), new Date(1732593600000L), null, Calendar.DAY_OF_YEAR));
			spinner.setBounds(133, 113, 165, 37);
			contentPanel.add(spinner);


	        JPanel buttonPane = new JPanel();
	        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        getContentPane().add(buttonPane, BorderLayout.SOUTH);

	     
	        
	        btnGuardar = new JButton("Ver Reporte");
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
