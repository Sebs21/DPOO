package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import logico.Bajo_vigilancia;
import logico.Clinica;
import logico.User;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu btnResumenes;
	private JMenu btnEnfermedadesVigilancia;
	private JMenu btnVacunacion;
	private JMenu btnAdministracion;
	private JMenuItem btnCita;
	
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
		});

		setTitle("SIGIC - Sistema de Gestión de Información Clínica");
		setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(70, 130, 180));
		setJMenuBar(menuBar);

		btnAdministracion = new JMenu("Administración");
		btnAdministracion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnAdministracion);

		JMenuItem btnInterfaz = new JMenuItem("Interfaz del Doctor");
		btnInterfaz.addActionListener(e -> {
			InterfazDoctor interDoc = new InterfazDoctor();
			interDoc.setVisible(true);
		});
		btnAdministracion.add(btnInterfaz);

		JMenuItem btnFacturar = new JMenuItem("Facturar");
		btnFacturar.addActionListener(e -> {
			Facturacion factura = new Facturacion();
			factura.setVisible(true);
		});
		btnAdministracion.add(btnFacturar);

        JMenuItem btnAgregarVacuna = new JMenuItem("Gestionar Inventario de Vacunas");
        btnAgregarVacuna.addActionListener(e -> {
            AgregarVacuna dialog = new AgregarVacuna();
            dialog.setVisible(true);
        });
        btnAdministracion.add(btnAgregarVacuna);

        JMenuItem btnAgregarSeguro = new JMenuItem("Gestionar Seguros");
        btnAgregarSeguro.addActionListener(e -> {
            AgregarSeguro dialog = new AgregarSeguro();
            dialog.setVisible(true);
        });
        btnAdministracion.add(btnAgregarSeguro);
        
        JMenuItem btnAgregarEspecialidad = new JMenuItem("Gestionar Especialidades");
        btnAgregarEspecialidad.addActionListener(e -> {
            AgregarEspecialidad dialog = new AgregarEspecialidad();
            dialog.setVisible(true);
        });
        btnAdministracion.add(btnAgregarEspecialidad);

		btnResumenes = new JMenu("Datos Ingresados");
		btnResumenes.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnResumenes);

		JMenuItem mntmListadoGeneral = new JMenuItem("Lista General");
		mntmListadoGeneral.addActionListener(e -> {
			ListadoGeneral lista = new ListadoGeneral();
			lista.setVisible(true);
		});
		btnResumenes.add(mntmListadoGeneral);

		btnEnfermedadesVigilancia = new JMenu("Enfermedades Vigiladas");
		btnEnfermedadesVigilancia.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnEnfermedadesVigilancia);


		JMenuItem btnReporteVigilancia = new JMenuItem("Reporte de Vigilancia");
		btnReporteVigilancia.addActionListener(e -> {
		    User currentUser = Clinica.getInstance().getLoginUser();
		    if (currentUser != null && currentUser.getTipo().equalsIgnoreCase("Doctor")) {
		        String cedulaDoctor = currentUser.getPass();
		        ArrayList<Bajo_vigilancia> vigilancias = Clinica.getInstance().obtenerVigilanciasDelDoctor(cedulaDoctor);
		        Reporte_control_enfermedades reporDoc = new Reporte_control_enfermedades(vigilancias);
		        reporDoc.setVisible(true);
		    } else {
		        Reporte_control_enfermedades repor_enfe = new Reporte_control_enfermedades(new ArrayList<>());
		        repor_enfe.setVisible(true);
		    }
		});
		btnEnfermedadesVigilancia.add(btnReporteVigilancia);

		btnVacunacion = new JMenu("Vacunación");
		btnVacunacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(btnVacunacion);
		
		JMenuItem btnRegistroVacuna = new JMenuItem("Registrar Aplicación");
		btnRegistroVacuna.addActionListener(e -> {
			Visual_vacunacion vacu = new Visual_vacunacion();
			vacu.setVisible(true);
		});
		btnVacunacion.add(btnRegistroVacuna);
		
		JMenuItem btnReporteDeVacuna = new JMenuItem("Reporte de Vacunas");
		btnReporteDeVacuna.addActionListener(e -> {
		    Reporte_Vacuna repor = new Reporte_Vacuna(this);
		    repor.setVisible(true);
		});
		btnVacunacion.add(btnReporteDeVacuna);
		
		menuBar.add(Box.createHorizontalGlue());

		JMenu menuCita = new JMenu("Citas");
		menuCita.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnCita = new JMenuItem("Realizar Cita");
		btnCita.addActionListener(e -> {
			Cita cita = new Cita();
			cita.setVisible(true);
		});
		menuCita.add(btnCita);
		menuBar.add(menuCita);
		
		JMenu menuSesion = new JMenu("Sesión");
		menuSesion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(menuSesion);
		
		JMenuItem mntmRegistrarDoctor = new JMenuItem("Registrar Doctor");
		mntmRegistrarDoctor.addActionListener(e -> {
			RegistrarUsuario regUser = new RegistrarUsuario();
			regUser.setVisible(true);
		});
		menuSesion.add(mntmRegistrarDoctor);
		
		menuSesion.addSeparator();
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesión");
		mntmCerrarSesion.addActionListener(e -> {
			dispose();
			InicioSesion login = new InicioSesion();
			login.setVisible(true);
		});
		mntmCerrarSesion.setForeground(Color.RED);
		menuSesion.add(mntmCerrarSesion);
	
		
		if (Clinica.getInstance().getLoginUser() == null) {
			dispose();
			return;
		}

		String userType = Clinica.getInstance().getLoginUser().getTipo();

		if (userType.equalsIgnoreCase("Paciente")) {
		    btnAdministracion.setEnabled(true);
		    btnResumenes.setEnabled(false);
		    btnEnfermedadesVigilancia.setEnabled(false);
		    btnVacunacion.setEnabled(true);
		    menuCita.setEnabled(true);
		    btnInterfaz.setEnabled(false);
		    btnAgregarVacuna.setEnabled(false);
		    btnAgregarSeguro.setEnabled(false);
		    btnAgregarEspecialidad.setVisible(false);

		} else if (userType.equalsIgnoreCase("Doctor")) {
		    btnAdministracion.setEnabled(true);
            btnAgregarVacuna.setVisible(false);
            btnAgregarSeguro.setVisible(false);
		    btnResumenes.setEnabled(false);
		    btnEnfermedadesVigilancia.setEnabled(true);
		    btnVacunacion.setEnabled(false);
		    btnAgregarEspecialidad.setVisible(false);
		    menuCita.setEnabled(false);
		    btnFacturar.setEnabled(false);
		    mntmRegistrarDoctor.setEnabled(false);

		} else if (userType.equalsIgnoreCase("Administrador")) {
		    btnAdministracion.setEnabled(true);
		    btnResumenes.setEnabled(true);
		    btnEnfermedadesVigilancia.setEnabled(true);
		    btnVacunacion.setEnabled(true);
		    btnInterfaz.setEnabled(false);
		    btnFacturar.setEnabled(false);
		    menuCita.setEnabled(false);
		
		} else if (userType.equalsIgnoreCase("Invitado")) {
			btnAdministracion.setEnabled(false);
			btnResumenes.setEnabled(false);
			btnEnfermedadesVigilancia.setEnabled(false);
			btnVacunacion.setEnabled(false);
			menuCita.setEnabled(true);
		}
	}
}