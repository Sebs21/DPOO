package visual;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

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
			
				if(Clinica.getInstance().getMisDoctores().isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay doctores registrados en el sistema.");
			}
				else {
					InicioSesion iniSe = new InicioSesion();
					iniSe.setVisible(true);
					iniSe.setModal(true);
				}
			}
		});
		btnIniciarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(btnIniciarSesion);
		
		JMenu btnResumenes = new JMenu("Generar Resumenes Consultas");
		btnResumenes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnResumenes);
		
		JMenu menu = new JMenu("Generar Resumenes Consultas");
		menu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(menu);
		
		JMenu btnEnfermedadesVigilancia = new JMenu("Enfermedades Vigiladas");
		btnEnfermedadesVigilancia.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnEnfermedadesVigilancia);
		
		JButton btn_Registro_Vigilancia = new JButton("Registro Vigilancia");
		btn_Registro_Vigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btn_Registro_Vigilancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
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
