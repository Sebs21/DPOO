package visual;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import logico.Clinica;

import java.awt.Color;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
	private JMenu btnResumenes;
	private JMenu btnGenerarResumenese;
	private JMenu btnEnfermedadesVigilancia;
	private JMenu btnVacunacion;
	private JMenu btnAdministracion;

	//
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			InicioSesion login = new InicioSesion();
			login.setModal( true );
			login.setVisible( true );
			
			if ( login.isLoginsuccesful() )
			{
				Principal Frame = new Principal();
				Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Frame.setVisible(true);
			}
			else
			{
				JOptionPane.showConfirmDialog( null, "Inicio de sesión fallido." );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Principal() 
	{
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

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(UIManager.getColor("activeCaption"));
		menuBar.setBackground(UIManager.getColor("activeCaption"));
		setJMenuBar(menuBar);

		btnAdministracion = new JMenu("Administraci\u00F3n");
		btnAdministracion.setForeground(Color.CYAN);

		btnAdministracion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Paciente")) 
				{
					btnAdministracion.setEnabled(false);
					btnResumenes.setEnabled(false);
					btnEnfermedadesVigilancia.setEnabled(false);
					btnVacunacion.setEnabled(true);
					
				} 
				else if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Paciente")) 
				{
					btnAdministracion.setEnabled(true);
					btnResumenes.setEnabled(true);
					btnEnfermedadesVigilancia.setEnabled(true);
					btnVacunacion.setEnabled(true);

				}
				else if (Clinica.getLoginUser().getTipo().equalsIgnoreCase("Administracion")) 
				{
					btnAdministracion.setEnabled(true);
					btnResumenes.setEnabled(true);
					btnEnfermedadesVigilancia.setEnabled(true);
					btnVacunacion.setEnabled(true);
				}
			}
		});

		btnAdministracion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnAdministracion);

		JMenuItem btnInterfaz = new JMenuItem("Listado de citas");
		btnInterfaz.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				InterfazDoctor interDoc = new InterfazDoctor();
				interDoc.setVisible(true);
				interDoc.setModal(true);
			}
		});
		btnInterfaz.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(btnInterfaz);

		JMenuItem btnFacturar = new JMenuItem("Facturar");
		btnFacturar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Facturacion factura = new Facturacion();
				factura.setVisible(true);
				factura.setModal(true);
			}
		});
		btnFacturar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(btnFacturar);

		btnResumenes = new JMenu("Datos Ingresados");
		btnResumenes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnResumenes);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Lista General");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ListadoGeneral lista = new ListadoGeneral();
				lista.setVisible(true);
				lista.setModal(true);
			}
		});
		btnResumenes.add(mntmNewMenuItem_2);

		btnGenerarResumenese = new JMenu("Generar Resumenes Consultas");
		btnGenerarResumenese.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnGenerarResumenese);

		btnEnfermedadesVigilancia = new JMenu("Enfermedades Vigiladas");
		btnEnfermedadesVigilancia.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnEnfermedadesVigilancia);

		JButton btn_Registro_Vigilancia = new JButton("Registro Vigilancia");
		btn_Registro_Vigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btn_Registro_Vigilancia.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{

			}
		});
		btnEnfermedadesVigilancia.add(btn_Registro_Vigilancia);

		JButton btn_Reporte_Vigilancia = new JButton("Reporte Vigilancia");
		btn_Reporte_Vigilancia.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

			}
		});
		btn_Reporte_Vigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEnfermedadesVigilancia.add(btn_Reporte_Vigilancia);

		btnVacunacion = new JMenu("Vacunaci\u00F3n");
		btnVacunacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnVacunacion);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cerrar sesion");
		mntmNewMenuItem_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				CerrarSesion cerrar = new CerrarSesion();
				cerrar.setVisible(true);
				cerrar.setModal(true);
				dispose();
			}
		});

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Realizar cita");
		mntmNewMenuItem_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Cita cita = new Cita();
				cita.setVisible(true);
				cita.setModal(true);
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mntmNewMenuItem_3);
		mntmNewMenuItem_1.setForeground(Color.RED);
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 35);
		setLocationRelativeTo(null);
		
	}
	
}
