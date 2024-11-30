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
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEdadPaciente;
	private JTextField txtVacunado;
	private JTextField txtEnfermedad;
	private JComboBox cbxPaciente;
	private JSpinner spnFechaCita;
	private JComboBox cbxDoctor;
	
	private boolean existePaciente;

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
		lblNewLabel.setBounds(37, 37, 56, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblPaciente.setBounds(37, 66, 59, 16);
		contentPanel.add(lblPaciente);
		
		cbxPaciente = new JComboBox();
		cbxPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		cbxPaciente.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >"}));
		cbxPaciente.setBounds(133, 63, 155, 22);
		contentPanel.add(cbxPaciente);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEdad.setBounds(34, 95, 59, 16);
		contentPanel.add(lblEdad);
		
		txtEdadPaciente = new JTextField();
		txtEdadPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtEdadPaciente.setEditable(false);
		txtEdadPaciente.setEnabled(false);
		txtEdadPaciente.setColumns(10);
		txtEdadPaciente.setBounds(133, 92, 155, 22);
		contentPanel.add(txtEdadPaciente);
		
		JLabel lblVacunado = new JLabel("Vacunado:");
		lblVacunado.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblVacunado.setBounds(34, 124, 68, 16);
		contentPanel.add(lblVacunado);
		
		txtVacunado = new JTextField();
		txtVacunado.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtVacunado.setEnabled(false);
		txtVacunado.setEditable(false);
		txtVacunado.setColumns(10);
		txtVacunado.setBounds(133, 121, 155, 22);
		contentPanel.add(txtVacunado);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad:");
		lblEnfermedad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEnfermedad.setBounds(37, 153, 84, 16);
		contentPanel.add(lblEnfermedad);
		
		txtEnfermedad = new JTextField();
		txtEnfermedad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtEnfermedad.setEnabled(false);
		txtEnfermedad.setEditable(false);
		txtEnfermedad.setColumns(10);
		txtEnfermedad.setBounds(133, 150, 155, 22);
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
		cbxDoctor.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >"}));
		cbxDoctor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		cbxDoctor.setBounds(133, 34, 155, 22);
		contentPanel.add(cbxDoctor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						Paciente paciente = null;
						Doctor doctor = null;
						
						Date fecha = ( Date ) spnFechaCita.getValue();
						
						for ( Paciente pacientes : Clinica.getInstance().getMisPacientes() )
						{
							for ( Doctor doctores : Clinica.getInstance().getMisDoctores() )
							{
								
							}
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
				btnCancelar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		updateCombosBox ();
		datos ();
		
	}
	
	public void datos ()
	{	
	
		ArrayList<Paciente> aux = Clinica.getInstance().getMisPacientes();
		
		for ( Paciente paciente : aux ) 
		{
			txtEdadPaciente.setText( String.valueOf( paciente.getEdad() ) );
			//txtVacunado resolviendo
			txtEnfermedad.setText( paciente.getEnfermedad() );
		}
	}
	
	private void updateCombosBox ()
	{
		
		ArrayList<Paciente> pacientes = Clinica.getInstance().getMisPacientes();
		ArrayList<Doctor> doctores = Clinica.getInstance().getMisDoctores();
		
		cbxPaciente.removeAllItems();
		cbxDoctor.removeAllItems();
		
		cbxPaciente.addItem( " < Seleccione > ");
		cbxDoctor.addItem( " < Seleccione > " );
		
		for ( Paciente paciente : pacientes ) 
		{
			cbxPaciente.addItem( paciente );
		}
		
		for ( Doctor doctor : doctores ) 
		{
			cbxDoctor.addItem( doctor );
		}
		
	}
	
}
