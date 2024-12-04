package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;
import logico.User;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Cita extends JDialog 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEdadPaciente;
	private JSpinner spnFechaCita;
	private JComboBox cbxDoctor;
	
	private boolean existePaciente;
	private JTextField txtIdCita;
	private JTextField txtPacienteSesion;
	
	private User pacienteSelect = Clinica.getInstance().getLoginUser();
	private Paciente pacienteCita = Clinica.getInstance().buscarPacienteByCedula( pacienteSelect.getPass() );

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Cita dialog = new Cita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Cita() 
	{
		
		if ( Clinica.getInstance().getMisDoctores().isEmpty() || Clinica.getInstance().getMisPacientes().isEmpty() )
		{
			JOptionPane.showMessageDialog( null, "Debe haber registrado un doctor y un paciente para poder proceder a cita." );
			dispose();
			return;
		}
		
		setTitle("Registrar Cita");
		setBounds(100, 100, 334, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo( null );
		
		JLabel lblNewLabel = new JLabel("Doctor:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(40, 66, 56, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblPaciente.setBounds(40, 95, 59, 16);
		contentPanel.add(lblPaciente);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEdad.setBounds(37, 124, 59, 16);
		contentPanel.add(lblEdad);
		
		txtEdadPaciente = new JTextField();
		txtEdadPaciente.setText( pacienteCita.getEdad() );
		txtEdadPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtEdadPaciente.setEditable(false);
		txtEdadPaciente.setEnabled(false);
		txtEdadPaciente.setColumns(10);
		txtEdadPaciente.setBounds(133, 121, 155, 22);
		contentPanel.add(txtEdadPaciente);
		
		JLabel lblFechaDeLa = new JLabel("Fecha de la Cita:");
		lblFechaDeLa.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblFechaDeLa.setBounds(90, 176, 118, 16);
		contentPanel.add(lblFechaDeLa);
		
		spnFechaCita = new JSpinner();
		spnFechaCita.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		spnFechaCita.setBounds(60, 205, 165, 37);
		spnFechaCita.setModel( new SpinnerDateModel( new Date(), null, null, Calendar.DAY_OF_MONTH ) );
		
		contentPanel.add(spnFechaCita);
		
		cbxDoctor = new JComboBox();
		cbxDoctor.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione  >  "}));
		cbxDoctor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		cbxDoctor.setBounds(133, 63, 155, 22);
		contentPanel.add(cbxDoctor);
		
		JLabel lblIdCita = new JLabel("ID cita:");
		lblIdCita.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIdCita.setBounds(40, 37, 56, 16);
		contentPanel.add(lblIdCita);
		
		txtIdCita = new JTextField();
		txtIdCita.setText( " C - " +Clinica.getInstance().idCita );
		txtIdCita.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtIdCita.setEnabled(false);
		txtIdCita.setEditable(false);
		txtIdCita.setColumns(10);
		txtIdCita.setBounds(133, 34, 155, 22);
		contentPanel.add(txtIdCita);
		
		txtPacienteSesion = new JTextField();
		txtPacienteSesion.setText( pacienteSelect.getUsuario() );
		txtPacienteSesion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtPacienteSesion.setEnabled(false);
		txtPacienteSesion.setEditable(false);
		txtPacienteSesion.setColumns(10);
		txtPacienteSesion.setBounds(133, 92, 155, 22);
		contentPanel.add(txtPacienteSesion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null ) );
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener( new ActionListener() 
				{
					public void actionPerformed( ActionEvent arg0 ) 
					{
						if ( cbxDoctor.getSelectedIndex() == 0 )
						{
							JOptionPane.showMessageDialog( null, "Debe ingresar un Doctor y un Paciente." );
						}
						else
						{
							
							String idCita = txtIdCita.getText();
							Doctor doctorSelect = ( Doctor ) cbxDoctor.getSelectedItem();
							String cedulaPaciente = pacienteCita.getCedula();
							Date fechaCita = ( Date ) spnFechaCita.getValue();
							
							if ( pacienteCita == null )
							{
								JOptionPane.showMessageDialog( null, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE );
								return;
							}
							
							logico.Cita cita = new logico.Cita( idCita, doctorSelect, pacienteCita, fechaCita );
							Clinica.getInstance().agregarCita( cita );
							JOptionPane.showMessageDialog( null, " Registro Exitoso ", "Información", JOptionPane.INFORMATION_MESSAGE );
							clean();
						}
						
					}
				});
				btnRegistrar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						dispose();
					}
				});
				btnCancelar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		updateCombosBox ();
		datos ();
		clean ();
		
	}	
	
	public void datos() 
	{	
		
		User user = Clinica.getLoginUser();
		
		if ( user == null ) 
		{
		   JOptionPane.showMessageDialog( null, "No se ha encontrado el usuario.", "Error", JOptionPane.ERROR_MESSAGE );
		   return;
		}

		    String cedula = user.getPass();
		    
		    if ( cedula == null || cedula.isEmpty() ) 
		    {
		        JOptionPane.showMessageDialog( null, "La cédula del usuario no es correcta.", "Error", JOptionPane.ERROR_MESSAGE );
		        return;
		    }

		    Paciente paciente = Clinica.getInstance().buscarPacienteByCedula( cedula );
		    
		    if ( paciente == null ) 
		    {
		        JOptionPane.showMessageDialog( null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE );
		        return;
		    }

		    txtPacienteSesion.setText( paciente.getNombre() );
		    txtEdadPaciente.setText( String.valueOf( paciente.getEdad() ) );
		    
	}
	
	private void updateCombosBox ()
	{
		
		ArrayList<Doctor> doctores = Clinica.getInstance().getMisDoctores();
		cbxDoctor.removeAllItems();
		cbxDoctor.addItem( " < Seleccione > " );
		
		for ( Doctor doctor : doctores ) 
		{
			cbxDoctor.addItem( doctor );
		}
		
	}
	
	public void clean ()
	{
		txtIdCita.setText( " C - " +Clinica.getInstance().idCita );
		cbxDoctor.setSelectedItem( " < Seleccione > " );
		txtPacienteSesion.setText("");
		txtEdadPaciente.setText("");
	}
}

