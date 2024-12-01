package visual;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
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

import com.sun.glass.events.WindowEvent;

import logico.Clinica;
import java.awt.Color;

public class Principal extends JFrame {

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
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
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
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FileOutputStream clinica;
				ObjectOutputStream clinicaWrite;
				try {
					clinica = new FileOutputStream("Clinica.dat");
					clinicaWrite = new ObjectOutputStream(clinica);
					clinicaWrite.writeObject(Clinica.getInstance());
				}catch(FileNotFoundException e1) {
					e1.printStackTrace();
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		setTitle("SIGIC");
		setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(UIManager.getColor("activeCaption"));
		menuBar.setBackground(UIManager.getColor("activeCaption"));
		setJMenuBar(menuBar);
		
		btnAdministracion = new JMenu("Administraci\u00F3n");
		
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
		        else 
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
		
		JMenuItem btnIniciarSesion = new JMenuItem("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
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
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar Cita");
		mntmNewMenuItem_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Cita cita = new Cita();
				cita.setVisible( true );
				cita.setModal( true );
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar Consulta");
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Consultar consulta = new Consultar();
				consulta.setVisible( true );
				consulta.setModal( true );
			}
		});
		btnAdministracion.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Facturaci\u00F3n");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(mntmNewMenuItem_3);
		
		btnResumenes = new JMenu("Datos Ingresados");
		btnResumenes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnResumenes);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Lista General");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ListadoGeneral lista = new ListadoGeneral();
				lista.setVisible( true );
				lista.setModal( true );
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
		btn_Registro_Vigilancia.addActionListener(new ActionListener() {
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
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Cerrar sesi\u00F3n");
		mntmNewMenuItem_5.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				CerrarSesion cerrar = new CerrarSesion();
				cerrar.setVisible(true);
				cerrar.setModal(true);
				dispose();
			}
		});
		mntmNewMenuItem_5.setForeground(Color.RED);
		menuBar.add(mntmNewMenuItem_5);
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
