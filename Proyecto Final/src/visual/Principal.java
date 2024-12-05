
package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import logico.Clinica;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
	private JMenu btnResumenes;
	private JMenu btnEnfermedadesVigilancia;
	private JMenu btnVacunacion;
	private JMenu btnAdministracion;
	private JMenuItem btnSeguro;
	private JMenuItem btnCita;
	private JMenuItem mntmNewMenuItem;
	private JButton btnReporteDeVacuna ;
	private JButton btnregistro_vacuna ;

	//
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Principal Frame = new Principal();
			Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				FileOutputStream clinica2;
				ObjectOutputStream clinicaWrite;
				try {
					clinica2 = new FileOutputStream("Clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinica2);
					clinicaWrite.writeObject(Clinica.getInstance());

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		setTitle("SIGIC");
		setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 35);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(UIManager.getColor("activeCaption"));
		menuBar.setBackground(UIManager.getColor("activeCaption"));
		setJMenuBar(menuBar);

		btnAdministracion = new JMenu("Administraci\u00F3n");
		btnAdministracion.setForeground(Color.CYAN);

		btnAdministracion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnAdministracion);

		JMenuItem btnInterfaz = new JMenuItem("Listado de citas");
		btnInterfaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazDoctor interDoc = new InterfazDoctor();
				interDoc.setVisible(true);
			}
		});
		btnInterfaz.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(btnInterfaz);

		JMenuItem btnFacturar = new JMenuItem("Facturar");
		btnFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Facturacion factura = new Facturacion();
				factura.setVisible(true);
			}
		});
		btnFacturar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(btnFacturar);

		btnResumenes = new JMenu("Datos Ingresados");
		btnResumenes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnResumenes);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Lista General");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoGeneral lista = new ListadoGeneral();
				lista.setVisible(true);
				 
			}
		});
		btnResumenes.add(mntmNewMenuItem_2);

		btnEnfermedadesVigilancia = new JMenu("Enfermedades Vigiladas");
		btnEnfermedadesVigilancia.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnEnfermedadesVigilancia);

		JButton btn_Registro_Vigilancia = new JButton("Registro Vigilancia");
		btn_Registro_Vigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btn_Registro_Vigilancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Visual_Control_enfermedades enfe = new Visual_Control_enfermedades();
				enfe.setVisible(true);

			}
		});
		btnEnfermedadesVigilancia.add(btn_Registro_Vigilancia);

		JButton btn_Reporte_Vigilancia = new JButton("Reporte Vigilancia");
		btn_Reporte_Vigilancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reporte_control_enfermedades repor_enfe  = new Reporte_control_enfermedades();
				repor_enfe.setVisible(true);

			}
		});
		btn_Reporte_Vigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEnfermedadesVigilancia.add(btn_Reporte_Vigilancia);

		btnVacunacion = new JMenu("Vacunaci\u00F3n");
		btnVacunacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnVacunacion);
		
		btnregistro_vacuna = new JButton("Registro vacuna");
		btnregistro_vacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visual_vacunacion vacu = new Visual_vacunacion();
				vacu.setVisible(true);
				
				
				
			}
		});
		btnregistro_vacuna.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnVacunacion.add(btnregistro_vacuna);
		
		btnReporteDeVacuna= new JButton("Reporte de vacuna");
		btnReporteDeVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reporte_Vacuna repor = new Reporte_Vacuna();
				repor.setVisible(true);
				
			}
		});
		btnReporteDeVacuna.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnVacunacion.add(btnReporteDeVacuna);

		mntmNewMenuItem = new JMenuItem("Cerrar sesion");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CerrarSesion cerrar = new CerrarSesion();
				cerrar.setVisible(true); 
				dispose();
			}
		});

		btnCita = new JMenuItem("Realizar cita");
		btnCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cita cita = new Cita();
				cita.setVisible(true);
			}
		});

		btnSeguro = new JMenuItem("Agregar Seguro");
		btnSeguro.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnSeguro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SeguroPaciente segPaciente = new SeguroPaciente();
				segPaciente.setVisible(true);
			}
		});
		menuBar.add(btnSeguro);

		btnCita.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(btnCita);
		mntmNewMenuItem.setForeground(Color.RED);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mntmNewMenuItem);

		if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Paciente") )
		{
			btnAdministracion.setEnabled(false);
			btnResumenes.setEnabled(false);
			btnEnfermedadesVigilancia.setEnabled(false);
			btnVacunacion.setEnabled(true);
			btnSeguro.setEnabled(true);
			btnFacturar.setEnabled(false);

		} else if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Doctor")) {
			btnAdministracion.setEnabled(true);
			btnResumenes.setEnabled(false);
			btnEnfermedadesVigilancia.setEnabled(true);
			btnVacunacion.setEnabled(true);
			btnFacturar.setEnabled(false);
			btnCita.setEnabled(false);
			btnSeguro.setEnabled(false);

		} else if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
			btnAdministracion.setEnabled(true);
			btnResumenes.setEnabled(true);
			btnEnfermedadesVigilancia.setEnabled(true);
			btnVacunacion.setEnabled(true);
			btnFacturar.setEnabled(true);
			btnInterfaz.setEnabled(false);
			btnCita.setEnabled(false);
			btnSeguro.setEnabled(false);
		}

	}
}
