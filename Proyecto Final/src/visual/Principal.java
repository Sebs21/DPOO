package visual;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("SIGIC");
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(UIManager.getColor("activeCaption"));
		menuBar.setBackground(UIManager.getColor("activeCaption"));
		setJMenuBar(menuBar);
		
		JMenu btnAdministracion = new JMenu("Administraci\u00F3n");
		btnAdministracion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnAdministracion);
		
		JMenuItem btnIniciarSesion = new JMenuItem("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InicioSesion iniSe = new InicioSesion();
				iniSe.setVisible(true);
				iniSe.setModal(true);
			
			}
		});
		btnIniciarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(btnIniciarSesion);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar Paciente");
		mntmNewMenuItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				RegistroPaciente regisPa = new RegistroPaciente();
				regisPa.setVisible(true);
				regisPa.setModal(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(mntmNewMenuItem);
		
		JMenu btnResumenes = new JMenu("Datos Ingresados");
		btnResumenes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnResumenes);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Lista General");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ListadoGeneral lista = new ListadoGeneral ();
				lista.setVisible( true );
				lista.setModal( true );
			}
		});
		btnResumenes.add(mntmNewMenuItem_2);
		
		JMenu menu = new JMenu("Generar Resumenes Consultas");
		menu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(menu);
		
		JMenu btnEnfermedadesVigilancia = new JMenu("Enfermedades Vigiladas");
		btnEnfermedadesVigilancia.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnEnfermedadesVigilancia);
		
		JButton btn_Registro_Vigilancia = new JButton("Registro Vigilancia");
		btn_Registro_Vigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btn_Registro_Vigilancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{		
				
			}
		});
		btnEnfermedadesVigilancia.add(btn_Registro_Vigilancia);
		
		JButton btn_Reporte_Vigilancia = new JButton("Reporte Vigilancia");
		btn_Reporte_Vigilancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btn_Reporte_Vigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEnfermedadesVigilancia.add(btn_Reporte_Vigilancia);
		
		JMenu btnVacunacion = new JMenu("Vacunaci\u00F3n");
		btnVacunacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnVacunacion);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-35);
		setLocationRelativeTo(null);
	}
}
