package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ListaVenta;
import logico.Paciente;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;

public class InterfazDoctor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblPacientes;
	private DefaultTableModel modelo;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfazDoctor dialog = new InterfazDoctor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazDoctor() {
		setTitle("Doctor");
		setBounds(100, 100, 1006, 590);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 3, true), "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(255, 11, 725, 496);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);
			
			tblPacientes = new JTable();
			scrollPane.setViewportView(tblPacientes);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnInformacion = new JButton("M\u00E1s Informaci\u00F3n");
			buttonPane.add(btnInformacion);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		modelo = new DefaultTableModel();
        String[] headers = {"Cedula", "Nombre", "Apellido", "Edad", "Seguro", "Enfermedad", "Vacunas"};
        modelo.setColumnIdentifiers(headers);
		
        table = new JTable(modelo);
        table.setBorder(new MatteBorder(4, 6, 4, 6, (Color) new Color(176, 196, 222)));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        
	}
	
	public void actualizarTablaDoctor(ArrayList<Paciente>misPacientes) {
		modelo.setColumnCount(0);
		for (Paciente paciente : misPacientes) {
			Object[] fila = { 
	            	paciente.getCedula(),
	            	paciente.getNombre(),
	            	paciente.getApellido(),
	            	paciente.getEdad(),
	            	paciente.getSeguro().getNombreEmpresa(),
	            	paciente.getEnfermedad(),
	            	paciente.getMiVacuna() //Revisar si la funcion de getVacuna retorna solo 1 o las retorna todas.
	            };
	            modelo.addRow(fila);
		}
	}
	
}
