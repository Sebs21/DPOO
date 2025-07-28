package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import logico.Clinica;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private Dimension dim;
	private JMenu btnResumenes;
	private JMenu btnEnfermedadesVigilancia;
	private JMenu btnVacunacion;
	private JMenu btnAdministracion;
	private JMenuItem btnSeguro;
	private JMenuItem btnCita;
	private JMenuItem mntmNewMenuItem;

	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Guardar el estado de la clínica al cerrar la ventana principal
				try (FileOutputStream fos = new FileOutputStream("Clinica_info.dat");
					 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
					oos.writeObject(Clinica.getInstance());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		setTitle("SIGIC - Sistema de Gestión de Información Clínica");
		setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 40); // Ajuste para la barra de tareas
		setLocationRelativeTo(null);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(70, 130, 180)); // Un color más profesional
		setJMenuBar(menuBar);

		// --- Menú Administración ---
		btnAdministracion = new JMenu("Administración");
		btnAdministracion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnAdministracion);

		JMenuItem btnInterfaz = new JMenuItem("Interfaz del Doctor");
		btnInterfaz.addActionListener(e -> {
			InterfazDoctor interDoc = new InterfazDoctor();
			interDoc.setVisible(true);
		});
		btnInterfaz.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(btnInterfaz);

		JMenuItem btnFacturar = new JMenuItem("Facturar");
		btnFacturar.addActionListener(e -> {
			Facturacion factura = new Facturacion();
			factura.setVisible(true);
		});
		btnFacturar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdministracion.add(btnFacturar);

		// --- Menú Datos Ingresados / Resúmenes ---
		btnResumenes = new JMenu("Datos Ingresados");
		btnResumenes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnResumenes);

		JMenuItem mntmListadoGeneral = new JMenuItem("Lista General");
		mntmListadoGeneral.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmListadoGeneral.addActionListener(e -> {
			ListadoGeneral lista = new ListadoGeneral();
			lista.setVisible(true);
		});
		btnResumenes.add(mntmListadoGeneral);

		// --- Menú Enfermedades Vigiladas ---
		btnEnfermedadesVigilancia = new JMenu("Enfermedades Vigiladas");
		btnEnfermedadesVigilancia.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnEnfermedadesVigilancia);

		JMenuItem btnRegistroVigilancia = new JMenuItem("Registrar Vigilancia"); // Usando JMenuItem en lugar de JButton
		btnRegistroVigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnRegistroVigilancia.addActionListener(e -> {
			Visual_Control_enfermedades enfe = new Visual_Control_enfermedades();
			enfe.setVisible(true);
		});
		btnEnfermedadesVigilancia.add(btnRegistroVigilancia);

		JMenuItem btnReporteVigilancia = new JMenuItem("Reporte de Vigilancia");
		btnReporteVigilancia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnReporteVigilancia.addActionListener(e -> {
			Reporte_control_enfermedades repor_enfe = new Reporte_control_enfermedades();
			repor_enfe.setVisible(true);
		});
		btnEnfermedadesVigilancia.add(btnReporteVigilancia);

		// --- Menú Vacunación ---
		btnVacunacion = new JMenu("Vacunación");
		btnVacunacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnVacunacion);
		
		JMenuItem btnRegistroVacuna = new JMenuItem("Registrar Aplicación");
		btnRegistroVacuna.addActionListener(e -> {
			Visual_vacunacion vacu = new Visual_vacunacion();
			vacu.setVisible(true);
		});
		btnRegistroVacuna.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnVacunacion.add(btnRegistroVacuna);
		
		JMenuItem btnReporteDeVacuna = new JMenuItem("Reporte de Vacunas");
		btnReporteDeVacuna.addActionListener(e -> {
			Reporte_Vacuna repor = new Reporte_Vacuna();
			repor.setVisible(true);
		});
		btnReporteDeVacuna.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnVacunacion.add(btnReporteDeVacuna);

		// --- Botones directos en la barra de menú ---
		btnSeguro = new JMenuItem("Agregar Seguro");
		btnSeguro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnSeguro.addActionListener(e -> {
			SeguroPaciente segPaciente = new SeguroPaciente();
			segPaciente.setVisible(true);
		});
		menuBar.add(btnSeguro);

		btnCita = new JMenuItem("Realizar Cita");
		btnCita.addActionListener(e -> {
			Cita cita = new Cita();
			cita.setVisible(true);
		});
		btnCita.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnCita);
		
		// --- Menú de Sesión ---
		JMenu menuSesion = new JMenu("Sesión");
		menuSesion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(menuSesion);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesión");
		mntmCerrarSesion.addActionListener(e -> {
			dispose(); // Cierra la ventana principal
			InicioSesion login = new InicioSesion(); // Vuelve a la ventana de login
			login.setVisible(true);
		});
		mntmCerrarSesion.setForeground(Color.RED);
		mntmCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuSesion.add(mntmCerrarSesion);
		
		JMenuItem mntmRegistrarDoctor = new JMenuItem("Registrar Doctor");
		mntmRegistrarDoctor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmRegistrarDoctor.addActionListener(e -> {
			RegistrarUsuario regUser = new RegistrarUsuario();
			regUser.setVisible(true);
		});
		menuSesion.add(mntmRegistrarDoctor);


		// --- Bloque de Control de Permisos ---
		// Este bloque habilita o deshabilita los menús según el tipo de usuario logueado.
		if (Clinica.getInstance().getLoginUser() == null) {
			// Si no hay usuario (ej., al ejecutar esta clase directamente), se cierra.
			dispose();
			return;
		}

		String userType = Clinica.getInstance().getLoginUser().getTipo();

		if (userType.equalsIgnoreCase("Paciente")) {
		    btnAdministracion.setEnabled(false);
		    btnResumenes.setEnabled(false);
		    btnEnfermedadesVigilancia.setEnabled(false);
		    btnVacunacion.setEnabled(true);
		    btnSeguro.setEnabled(true);
		    btnCita.setEnabled(true);

		} else if (userType.equalsIgnoreCase("Doctor")) {
		    btnAdministracion.setEnabled(true);
		    btnResumenes.setEnabled(true);
		    btnEnfermedadesVigilancia.setEnabled(true);
		    btnVacunacion.setEnabled(true);
		    btnCita.setEnabled(false);
		    btnSeguro.setEnabled(false);

		} else if (userType.equalsIgnoreCase("Administrador")) {
		    btnAdministracion.setEnabled(true);
		    btnResumenes.setEnabled(true);
		    btnEnfermedadesVigilancia.setEnabled(true);
		    btnVacunacion.setEnabled(true);
		    // Los administradores no realizan citas ni se asignan seguros para sí mismos
		    btnCita.setEnabled(false);
		    btnSeguro.setEnabled(false);
		
		} else if (userType.equalsIgnoreCase("Invitado")) {
			// El invitado solo puede realizar una cita.
			btnAdministracion.setEnabled(false);
			btnResumenes.setEnabled(false);
			btnEnfermedadesVigilancia.setEnabled(false);
			btnVacunacion.setEnabled(false);
			btnSeguro.setEnabled(false);
			btnCita.setEnabled(true); // La única opción habilitada
		}
	}
}