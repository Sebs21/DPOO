package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;

public class RegistroPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEdad;
	private JTextField txtSeguro;
	//
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroPaciente dialog = new RegistroPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroPaciente() {
		setBounds(100, 100, 780, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 128, 128), 4, true), "Informaci\u00F3n paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 744, 366);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel.setBounds(62, 35, 108, 31);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Apellido:");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_1.setBounds(402, 35, 108, 31);
			panel.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.setEnabled(false);
			txtNombre.setEditable(false);
			txtNombre.setBounds(232, 35, 108, 31);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtApellido = new JTextField();
			txtApellido.setEnabled(false);
			txtApellido.setEditable(false);
			txtApellido.setColumns(10);
			txtApellido.setBounds(572, 35, 108, 31);
			panel.add(txtApellido);
			
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblEdad.setBounds(62, 95, 108, 31);
			panel.add(lblEdad);
			
			txtEdad = new JTextField();
			txtEdad.setColumns(10);
			txtEdad.setBounds(232, 96, 108, 31);
			panel.add(txtEdad);
			
			JLabel lblSeguro = new JLabel("Seguro:");
			lblSeguro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblSeguro.setBounds(402, 95, 108, 31);
			panel.add(lblSeguro);
			
			txtSeguro = new JTextField();
			txtSeguro.setEnabled(false);
			txtSeguro.setEditable(false);
			txtSeguro.setColumns(10);
			txtSeguro.setBounds(572, 96, 108, 31);
			panel.add(txtSeguro);
			
			JPanel PanelVacuna = new JPanel();
			PanelVacuna.setBorder(new TitledBorder(new LineBorder(new Color(176, 224, 230), 4, true), "Vacunaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			PanelVacuna.setBounds(62, 160, 278, 182);
			panel.add(PanelVacuna);
			PanelVacuna.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("\u00BFDesea Vacunarse?");
			lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_2.setBounds(70, 28, 138, 23);
			PanelVacuna.add(lblNewLabel_2);
			
			JRadioButton rdbtnSi = new JRadioButton("Si deseo");
			rdbtnSi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
			rdbtnSi.setBounds(84, 79, 109, 23);
			PanelVacuna.add(rdbtnSi);
			
			JRadioButton rdbtnNo = new JRadioButton("No deseo");
			rdbtnNo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
			rdbtnNo.setBounds(84, 130, 109, 23);
			PanelVacuna.add(rdbtnNo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
	}
}
