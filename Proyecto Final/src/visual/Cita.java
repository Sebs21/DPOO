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

public class Cita extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEdadPaciente;
	private JTextField txtVacunado;
	private JTextField txtEnfermedad;
	private JSpinner spnFechaCita;
	private JComboBox cbxDoctor;
	
	private boolean existePaciente;
	private JTextField txtIdCita;
	private JTextField txtPacienteSesion;

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
		setTitle("Registrar Cita");
		setBounds(100, 100, 334, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
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
		txtEdadPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtEdadPaciente.setEditable(false);
		txtEdadPaciente.setEnabled(false);
		txtEdadPaciente.setColumns(10);
		txtEdadPaciente.setBounds(133, 121, 155, 22);
		contentPanel.add(txtEdadPaciente);
		
		JLabel lblVacunado = new JLabel("Vacunado:");
		lblVacunado.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblVacunado.setBounds(37, 153, 68, 16);
		contentPanel.add(lblVacunado);
		
		txtVacunado = new JTextField();
		txtVacunado.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtVacunado.setEnabled(false);
		txtVacunado.setEditable(false);
		txtVacunado.setColumns(10);
		txtVacunado.setBounds(133, 150, 155, 22);
		contentPanel.add(txtVacunado);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad:");
		lblEnfermedad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEnfermedad.setBounds(37, 182, 84, 16);
		contentPanel.add(lblEnfermedad);
		
		txtEnfermedad = new JTextField();
		txtEnfermedad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtEnfermedad.setEnabled(false);
		txtEnfermedad.setEditable(false);
		txtEnfermedad.setColumns(10);
		txtEnfermedad.setBounds(133, 182, 155, 22);
		contentPanel.add(txtEnfermedad);
		
		JLabel lblFechaDeLa = new JLabel("Fecha de la Cita:");
		lblFechaDeLa.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblFechaDeLa.setBounds(102, 217, 118, 16);
		contentPanel.add(lblFechaDeLa);
		
		spnFechaCita = new JSpinner();
		spnFechaCita.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		spnFechaCita.setBounds(75, 257, 165, 37);
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
		txtPacienteSesion.setText("");
		txtPacienteSesion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtPacienteSesion.setEnabled(false);
		txtPacienteSesion.setEditable(false);
		txtPacienteSesion.setColumns(10);
		txtPacienteSesion.setBounds(133, 92, 155, 22);
		contentPanel.add(txtPacienteSesion);
		{
			JPanel buttonPane = new JPanel();
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
							String cedulaPaciente = txtPacienteSesion.getText();
							Paciente pacienteSelect = Clinica.getInstance().buscarPacienteByCedula( cedulaPaciente );
							Date fechaCita = ( Date ) spnFechaCita.getValue();
							
							if ( pacienteSelect == null )
							{
								JOptionPane.showMessageDialog( null, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE );
								return;
							}
							
							logico.Cita cita = new logico.Cita( idCita, doctorSelect, pacienteSelect, fechaCita );
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
	
	public void datos ()
	{	
	
		ArrayList<Paciente> aux = Clinica.getInstance().getMisPacientes();
		
		for ( Paciente paciente : aux ) 
		{
			txtPacienteSesion.setText( paciente.getNombre() );
			txtEdadPaciente.setText( String.valueOf( paciente.getEdad() ) );
			txtEnfermedad.setText( paciente.getEnfermedad() );
		}
	}
	
	private void updateCombosBox ()
	{
		
		ArrayList<Paciente> pacientes = Clinica.getInstance().getMisPacientes();
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
		txtEnfermedad.setText("");
		txtVacunado.setText("");
	}
}
