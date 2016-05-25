package me4l;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Aplicacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordAdmin;
	private JTextField textFieldNombre_PR;
	private JTextField textFieldNombre_A;
	private JTextField textFieldNombre_MR;
	private JTextField textFieldCantidadPrinc_PR;
	private JTextField textFieldCantidadPrinc_A;
	private JTextField textFieldCantidadPrinc_MR;
	private JTextField textFieldCantidad_A;
	private JTextField textFieldCantidad_PR;
	private JTextField textFieldCantidad_MR;
	private RecetaVO recetaSeleccionada_MR = new RecetaVO();
	// Array/cache que guarda las recetas que el usuario tiene abiertas
	ArrayList<RecetaVO> recetasAbiertas = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> recetasModificar = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> recetasParaValidar = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> recetas_BA = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> recetas_Primero = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> recetas_Segundo = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> recetas_Postre = new ArrayList<RecetaVO>();
	ArrayList<String> ingredientesAplicacion = new ArrayList<String>();
	ArrayList<String> indexIngredientes_PR = new ArrayList<String>();
	ArrayList<String> indexIngredientes_A = new ArrayList<String>();
	ArrayList<String> indexIngredientes_MR = new ArrayList<String>();
	private JTextField textFieldBusqueda_MR;
	// Objeto de operaciones
	private Operaciones o = new Operaciones();
	private boolean identificado = false;
	private JTextField textFieldBusqueda_BA;
	private JTextField textFieldPrimeros;
	private JTextField textFieldSegundos;
	private JTextField textFieldPostres;
	// Menus propuestos en el menu principal
	ArrayList<RecetaVO> menu_1 = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> menu_2 = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> menu_3 = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> menu_4 = new ArrayList<RecetaVO>();

	// Metodo para abrir URL en el navegador del usuario
	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Metodo para abrir URL en el navegador del usuario
	public static void openWebpage(URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	// Metodo que pone todos los botones en blanco
	private static void botonesEnBlanco(JButton botonAvanzada, JButton botonPrimeros, JButton botonSegundos,
			JButton botonPostres, JButton botonDestacados, JButton botonProponer, JButton botonAyuda,
			JButton botonAdmin, JButton botonValidar, JButton botonAnyadir, JButton botonModificar) {
		botonAvanzada.setBackground(new Color(245, 245, 245));
		botonPrimeros.setBackground(new Color(245, 245, 245));
		botonSegundos.setBackground(new Color(245, 245, 245));
		botonPostres.setBackground(new Color(245, 245, 245));
		botonDestacados.setBackground(new Color(245, 245, 245));
		botonProponer.setBackground(new Color(245, 245, 245));
		botonAyuda.setBackground(new Color(245, 245, 245));
		botonAdmin.setBackground(new Color(245, 245, 245));
		botonValidar.setBackground(new Color(245, 245, 245));
		botonAnyadir.setBackground(new Color(245, 245, 245));
		botonModificar.setBackground(new Color(245, 245, 245));
	}

	// Metodo que comprueba si la contraseÃ±a es correcta
	private static boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { 'f', 'a', 't', 'o', 'l', 'a', 'n', 'd' };

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		// Zero out the password.
		Arrays.fill(correctPassword, '0');

		return isCorrect;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Aplicacion() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setBounds(350, 100, 1148, 721);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		setResizable(false);
		// Controlador de cambio de pantallas
		final CardLayout cardLayout = (CardLayout) contentPane.getLayout();

		// Creamos las imagenes
		Image imagenClose = new ImageIcon("images/close.png").getImage();
		Image imagenAdSearch = new ImageIcon("images/adsearch.png").getImage();
		Image imagenFondoApp = new ImageIcon("images/fondoApp.jpg").getImage();
		Image imagenHelp = new ImageIcon("images/help.png").getImage();
		Image imagenIcono = new ImageIcon("images/IconoAppRecortado.png").getImage();
		Image imagenOne = new ImageIcon("images/one.png").getImage();
		Image imagenPlus = new ImageIcon("images/plus.png").getImage();
		Image imagenRefresh = new ImageIcon("images/refresh.png").getImage();
		Image imagenReturn = new ImageIcon("images/return.png").getImage();
		Image imagenSearch = new ImageIcon("images/search.png").getImage();
		Image imagenSignIn = new ImageIcon("images/signIn.png").getImage();
		Image imagenThree = new ImageIcon("images/three.png").getImage();
		Image imagenTwo = new ImageIcon("images/two.png").getImage();
		Image imagenWrite = new ImageIcon("images/write.png").getImage();
		Image imagenAdmin = new ImageIcon("images/admin.png").getImage();
		Image imagenTick = new ImageIcon("images/tick.png").getImage();
		Image imagenModificar = new ImageIcon("images/modificar.png").getImage();
		Image imagenPrimerPlato = new ImageIcon("images/iconoPrimerPlato.png").getImage();
		Image imagenSegundoPlato = new ImageIcon("images/iconoSegundoPlato.png").getImage();
		Image imagenPostre = new ImageIcon("images/iconoPostre.png").getImage();
		Image imagenEquipo = new ImageIcon("images/LogoEquipoTransparente.png").getImage();
		Image imagenEntrante = new ImageIcon("images/entrante.png").getImage();

		Font f = new Font("Bauhaus 93", Font.BOLD, 84);

		// Cargamos los menús propuestos
		//menu_1 = o.menuDelDia();
		//menu_2 = o.menuDelDia();
		//menu_3 = o.menuDelDia();
		//menu_4 = o.menuDelDia();
		/**
		 * COMIENZO DE LA PANTALLA DEL MENU PRINCIPAL
		 **/
		final JPanel menuPrincipal = new JPanel();
		contentPane.add(menuPrincipal, "menuPrincipal");
		menuPrincipal.setLayout(null);

		// Icono de la aplicacion
		JLabel IconoApp3 = new JLabel("");
		f = new Font("Calibri", Font.BOLD, 16);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("gargi", Font.BOLD, 12);
		}

		// JPanel de la pantalla de los Menus
		final JPanel pantallaMenu = new JPanel();
		pantallaMenu.setBounds(262, 152, 868, 521);
		pantallaMenu.setOpaque(false);
		menuPrincipal.add(pantallaMenu);
		pantallaMenu.setLayout(new CardLayout(0, 0));

		// Controlador de los cambios de la zona del menu
		final CardLayout cardMenu = (CardLayout) pantallaMenu.getLayout();

		// Panel Menu Principal
		JPanel panelMenuPrincipal = new JPanel();
		panelMenuPrincipal.setOpaque(false);
		pantallaMenu.add(panelMenuPrincipal, "panelPrincipal");
		panelMenuPrincipal.setLayout(null);

		JLabel labelLinea1_MP = new JLabel(
				"¿Te has quedado sin ideas para el menú de hoy? No te preocupes, nosotros te proponemos unos cuantos menús");
		labelLinea1_MP.setForeground(Color.WHITE);
		labelLinea1_MP.setFont(new Font("Calibri", Font.BOLD, 18));
		labelLinea1_MP.setBounds(12, 44, 856, 23);
		panelMenuPrincipal.add(labelLinea1_MP);

		JLabel labelLinea2_MP = new JLabel(
				"para que elijas el que más te guste o al menos para que te sirvan de inspiración!");
		labelLinea2_MP.setForeground(Color.WHITE);
		labelLinea2_MP.setFont(new Font("Calibri", Font.BOLD, 18));
		labelLinea2_MP.setBounds(12, 66, 856, 23);
		panelMenuPrincipal.add(labelLinea2_MP);

		JSeparator sep3_MP = new JSeparator();
		sep3_MP.setForeground(Color.BLACK);
		sep3_MP.setBackground(Color.BLACK);
		sep3_MP.setBounds(0, 117, 868, 12);
		panelMenuPrincipal.add(sep3_MP);

		JSeparator sep1_MP = new JSeparator();
		sep1_MP.setForeground(Color.BLACK);
		sep1_MP.setBackground(Color.BLACK);
		sep1_MP.setBounds(0, 320, 868, 12);
		panelMenuPrincipal.add(sep1_MP);

		JSeparator sep2_MP = new JSeparator();
		sep2_MP.setOrientation(SwingConstants.VERTICAL);
		sep2_MP.setForeground(Color.BLACK);
		sep2_MP.setBackground(Color.BLACK);
		sep2_MP.setBounds(434, 117, 21, 404);
		panelMenuPrincipal.add(sep2_MP);

		JSeparator sep4_MP = new JSeparator();
		sep4_MP.setForeground(Color.BLACK);
		sep4_MP.setBackground(Color.BLACK);
		sep4_MP.setBounds(0, 519, 868, 12);
		panelMenuPrincipal.add(sep4_MP);

		JLabel labelMenu1 = new JLabel("Menú 1:");
		labelMenu1.setForeground(Color.WHITE);
		labelMenu1.setFont(new Font("Calibri", Font.BOLD, 18));
		labelMenu1.setBounds(10, 125, 104, 23);
		panelMenuPrincipal.add(labelMenu1);

		JLabel labelMenu2 = new JLabel("Menú 2:");
		labelMenu2.setForeground(Color.WHITE);
		labelMenu2.setFont(new Font("Calibri", Font.BOLD, 18));
		labelMenu2.setBounds(448, 125, 104, 23);
		panelMenuPrincipal.add(labelMenu2);

		JLabel labelMenu3 = new JLabel("Menú 3:");
		labelMenu3.setForeground(Color.WHITE);
		labelMenu3.setFont(new Font("Calibri", Font.BOLD, 18));
		labelMenu3.setBounds(12, 326, 104, 23);
		panelMenuPrincipal.add(labelMenu3);

		JLabel labelMenu4 = new JLabel("Menú 4:");
		labelMenu4.setForeground(Color.WHITE);
		labelMenu4.setFont(new Font("Calibri", Font.BOLD, 18));
		labelMenu4.setBounds(448, 326, 104, 23);
		panelMenuPrincipal.add(labelMenu4);

		JLabel labelEntranteMenu1_MP = new JLabel((String) null);
		labelEntranteMenu1_MP.setForeground(new Color(255, 153, 0));
		labelEntranteMenu1_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelEntranteMenu1_MP.setBounds(10, 151, 410, 23);
		//labelEntranteMenu1_MP.setText(menu_1.get(0).getNombre());
		panelMenuPrincipal.add(labelEntranteMenu1_MP);

		JLabel labelPrimeroMenu1_MP = new JLabel((String) null);
		labelPrimeroMenu1_MP.setForeground(new Color(255, 153, 0));
		labelPrimeroMenu1_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPrimeroMenu1_MP.setBounds(10, 187, 410, 23);
		//labelPrimeroMenu1_MP.setText(menu_1.get(1).getNombre());
		panelMenuPrincipal.add(labelPrimeroMenu1_MP);

		JLabel labelSegundoMenu1_MP = new JLabel((String) null);
		labelSegundoMenu1_MP.setForeground(new Color(255, 153, 0));
		labelSegundoMenu1_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelSegundoMenu1_MP.setBounds(10, 225, 410, 23);
		//labelSegundoMenu1_MP.setText(menu_1.get(2).getNombre());
		panelMenuPrincipal.add(labelSegundoMenu1_MP);

		JLabel labelPostreMenu1_MP = new JLabel((String) null);
		labelPostreMenu1_MP.setForeground(new Color(255, 153, 0));
		labelPostreMenu1_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPostreMenu1_MP.setBounds(10, 261, 410, 23);
		//labelPostreMenu1_MP.setText(menu_1.get(3).getNombre());
		panelMenuPrincipal.add(labelPostreMenu1_MP);

		JLabel labelPostreMenu2_MP = new JLabel((String) null);
		labelPostreMenu2_MP.setForeground(new Color(255, 153, 0));
		labelPostreMenu2_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPostreMenu2_MP.setBounds(448, 261, 410, 23);
		//labelPostreMenu2_MP.setText(menu_2.get(3).getNombre());
		panelMenuPrincipal.add(labelPostreMenu2_MP);

		JLabel labelSegundoMenu2_MP = new JLabel((String) null);
		labelSegundoMenu2_MP.setForeground(new Color(255, 153, 0));
		labelSegundoMenu2_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelSegundoMenu2_MP.setBounds(448, 225, 410, 23);
		//labelSegundoMenu2_MP.setText(menu_2.get(2).getNombre());
		panelMenuPrincipal.add(labelSegundoMenu2_MP);

		JLabel labelPrimeroMenu2_MP = new JLabel((String) null);
		labelPrimeroMenu2_MP.setForeground(new Color(255, 153, 0));
		labelPrimeroMenu2_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPrimeroMenu2_MP.setBounds(448, 187, 410, 23);
		//labelPrimeroMenu2_MP.setText(menu_2.get(1).getNombre());
		panelMenuPrincipal.add(labelPrimeroMenu2_MP);

		JLabel labelEntranteMenu2_MP = new JLabel((String) null);
		labelEntranteMenu2_MP.setForeground(new Color(255, 153, 0));
		labelEntranteMenu2_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelEntranteMenu2_MP.setBounds(448, 151, 410, 23);
		//labelEntranteMenu2_MP.setText(menu_2.get(0).getNombre());
		panelMenuPrincipal.add(labelEntranteMenu2_MP);

		JLabel labelPostreMenu3_MP = new JLabel((String) null);
		labelPostreMenu3_MP.setForeground(new Color(255, 153, 0));
		labelPostreMenu3_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPostreMenu3_MP.setBounds(10, 460, 410, 23);
		//labelPostreMenu3_MP.setText(menu_3.get(3).getNombre());
		panelMenuPrincipal.add(labelPostreMenu3_MP);

		JLabel labelSegundoMenu3_MP = new JLabel((String) null);
		labelSegundoMenu3_MP.setForeground(new Color(255, 153, 0));
		labelSegundoMenu3_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelSegundoMenu3_MP.setBounds(10, 424, 410, 23);
		//labelSegundoMenu3_MP.setText(menu_3.get(2).getNombre());
		panelMenuPrincipal.add(labelSegundoMenu3_MP);

		JLabel labelPrimeroMenu3_MP = new JLabel((String) null);
		labelPrimeroMenu3_MP.setForeground(new Color(255, 153, 0));
		labelPrimeroMenu3_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPrimeroMenu3_MP.setBounds(10, 386, 410, 23);
		//labelPrimeroMenu3_MP.setText(menu_3.get(1).getNombre());
		panelMenuPrincipal.add(labelPrimeroMenu3_MP);

		JLabel labelEntranteMenu3_MP = new JLabel((String) null);
		labelEntranteMenu3_MP.setForeground(new Color(255, 153, 0));
		labelEntranteMenu3_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelEntranteMenu3_MP.setBounds(10, 350, 410, 23);
		//labelEntranteMenu3_MP.setText(menu_3.get(0).getNombre());
		panelMenuPrincipal.add(labelEntranteMenu3_MP);

		JLabel labelEntranteMenu4_MP = new JLabel((String) null);
		labelEntranteMenu4_MP.setForeground(new Color(255, 153, 0));
		labelEntranteMenu4_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelEntranteMenu4_MP.setBounds(448, 350, 410, 23);
		//labelEntranteMenu4_MP.setText(menu_4.get(0).getNombre());
		panelMenuPrincipal.add(labelEntranteMenu4_MP);

		JLabel labelPrimeroMenu4_MP = new JLabel((String) null);
		labelPrimeroMenu4_MP.setForeground(new Color(255, 153, 0));
		labelPrimeroMenu4_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPrimeroMenu4_MP.setBounds(448, 386, 410, 23);
		//labelPrimeroMenu4_MP.setText(menu_4.get(1).getNombre());
		panelMenuPrincipal.add(labelPrimeroMenu4_MP);

		JLabel labelSegundoMenu4_MP = new JLabel((String) null);
		labelSegundoMenu4_MP.setForeground(new Color(255, 153, 0));
		labelSegundoMenu4_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelSegundoMenu4_MP.setBounds(448, 424, 410, 23);
		//labelSegundoMenu4_MP.setText(menu_4.get(2).getNombre());
		panelMenuPrincipal.add(labelSegundoMenu4_MP);

		JLabel labelPostreMenu4_MP = new JLabel((String) null);
		labelPostreMenu4_MP.setForeground(new Color(255, 153, 0));
		labelPostreMenu4_MP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPostreMenu4_MP.setBounds(448, 460, 410, 23);
		//labelPostreMenu4_MP.setText(menu_4.get(3).getNombre());
		panelMenuPrincipal.add(labelPostreMenu4_MP);

		// Boton de ver el menu1
		JButton botonVerMenu1_MP = new JButton("Ver Menú");
		botonVerMenu1_MP.setFont(new Font("Calibri", Font.BOLD, 18));
		botonVerMenu1_MP.setBackground(new Color(173, 255, 47));
		botonVerMenu1_MP.setBounds(134, 295, 131, 23);
		panelMenuPrincipal.add(botonVerMenu1_MP);

		// Boton de ver el menu3
		JButton botonVerMenu3_MP = new JButton("Ver Menú");
		botonVerMenu3_MP.setFont(new Font("Calibri", Font.BOLD, 18));
		botonVerMenu3_MP.setBackground(new Color(173, 255, 47));
		botonVerMenu3_MP.setBounds(134, 493, 131, 23);
		panelMenuPrincipal.add(botonVerMenu3_MP);

		// Boton de ver el menu2
		JButton botonVerMenu2_MP = new JButton("Ver Menú");
		botonVerMenu2_MP.setFont(new Font("Calibri", Font.BOLD, 18));
		botonVerMenu2_MP.setBackground(new Color(173, 255, 47));
		botonVerMenu2_MP.setBounds(596, 295, 131, 23);
		panelMenuPrincipal.add(botonVerMenu2_MP);

		// Boton de ver el menu4
		JButton botonVerMenu4_MP = new JButton("Ver Menú");
		botonVerMenu4_MP.setFont(new Font("Calibri", Font.BOLD, 18));
		botonVerMenu4_MP.setBackground(new Color(173, 255, 47));
		botonVerMenu4_MP.setBounds(596, 493, 131, 23);
		panelMenuPrincipal.add(botonVerMenu4_MP);

		// Acciones de los botones
		botonVerMenu1_MP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				RecetaAbierta rmp_entrante = new RecetaAbierta(menu_1.get(0));
				RecetaAbierta rmp_primero = new RecetaAbierta(menu_1.get(1));
				RecetaAbierta rmp_segundo = new RecetaAbierta(menu_1.get(2));
				RecetaAbierta rmp_postre = new RecetaAbierta(menu_1.get(3));
			}
		});

		botonVerMenu2_MP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				RecetaAbierta rmp_entrante = new RecetaAbierta(menu_2.get(0));
				RecetaAbierta rmp_primero = new RecetaAbierta(menu_2.get(1));
				RecetaAbierta rmp_segundo = new RecetaAbierta(menu_2.get(2));
				RecetaAbierta rmp_postre = new RecetaAbierta(menu_2.get(3));
			}
		});

		botonVerMenu1_MP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				RecetaAbierta rmp_entrante = new RecetaAbierta(menu_3.get(0));
				RecetaAbierta rmp_primero = new RecetaAbierta(menu_3.get(1));
				RecetaAbierta rmp_segundo = new RecetaAbierta(menu_3.get(2));
				RecetaAbierta rmp_postre = new RecetaAbierta(menu_3.get(3));
			}
		});
		
		botonVerMenu1_MP.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				RecetaAbierta rmp_entrante = new RecetaAbierta(menu_4.get(0));
				RecetaAbierta rmp_primero = new RecetaAbierta(menu_4.get(1));
				RecetaAbierta rmp_segundo = new RecetaAbierta(menu_4.get(2));
				RecetaAbierta rmp_postre = new RecetaAbierta(menu_4.get(3));
			}
		});

		// TextArea menu principal
		f = new Font("Calibri", Font.BOLD, 16);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("gargi", Font.BOLD, 12);
		}

		// Panel Busqueda Avanzada
		JPanel panelEntrantes = new JPanel();
		panelEntrantes.setOpaque(false);
		pantallaMenu.add(panelEntrantes, "panelEntrantes");
		panelEntrantes.setLayout(null);

		// Text field busqueda avanzada
		textFieldBusqueda_BA = new JTextField();
		textFieldBusqueda_BA.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldBusqueda_BA.setColumns(10);
		textFieldBusqueda_BA.setBounds(12, 15, 703, 36);
		panelEntrantes.add(textFieldBusqueda_BA);

		// Lista de recetas buscadas
		List listaRecetasBuscadas_BA = new List();
		listaRecetasBuscadas_BA.setFont(new Font("Calibri", Font.BOLD, 20));
		listaRecetasBuscadas_BA.setBounds(12, 177, 556, 257);
		panelEntrantes.add(listaRecetasBuscadas_BA);

		// Label ingredientes de busqueda avanzada
		JLabel labelIngrediente_BA = new JLabel("Ingrediente Principal:");
		labelIngrediente_BA.setForeground(Color.WHITE);
		labelIngrediente_BA.setFont(new Font("Calibri", Font.BOLD, 18));
		labelIngrediente_BA.setBounds(12, 100, 174, 23);
		panelEntrantes.add(labelIngrediente_BA);

		// ComboBox de los ingredientes de busqueda avanzada
		JComboBox comboBoxIngrediente_BA = new JComboBox();
		comboBoxIngrediente_BA.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_BA.setLightWeightPopupEnabled(false);
		comboBoxIngrediente_BA.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxIngrediente_BA.setBounds(198, 101, 204, 22);
		panelEntrantes.add(comboBoxIngrediente_BA);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_BA.addItem(ingredientesAplicacion.get(i));
		}

		// CombBox Numero de personas
		JComboBox comboNumPersonas_BA = new JComboBox();
		comboNumPersonas_BA.setModel(new DefaultComboBoxModel(new String[] { "-- Seleccione el número de personas --",
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboNumPersonas_BA.setLightWeightPopupEnabled(false);
		comboNumPersonas_BA.setFont(new Font("Calibri", Font.BOLD, 15));
		comboNumPersonas_BA.setBounds(198, 65, 272, 22);
		panelEntrantes.add(comboNumPersonas_BA);

		// Label numero de personas
		JLabel labelNumPersonas_BA = new JLabel("Número de personas:");
		labelNumPersonas_BA.setForeground(Color.WHITE);
		labelNumPersonas_BA.setFont(new Font("Calibri", Font.BOLD, 18));
		labelNumPersonas_BA.setBounds(12, 64, 174, 23);
		panelEntrantes.add(labelNumPersonas_BA);

		// Label recetas
		JLabel labelRecetas_BA = new JLabel("Recetas:");
		labelRecetas_BA.setForeground(Color.WHITE);
		labelRecetas_BA.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecetas_BA.setBounds(12, 148, 174, 23);
		panelEntrantes.add(labelRecetas_BA);

		// Boton buscar
		JButton botonBuscar_BA = new JButton("Buscar");
		botonBuscar_BA.setFont(new Font("Calibri", Font.BOLD, 23));
		botonBuscar_BA.setBackground(new Color(245, 245, 245));
		botonBuscar_BA.setBounds(727, 12, 116, 39);
		panelEntrantes.add(botonBuscar_BA);

		// Accion del boton buscar
		botonBuscar_BA.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				listaRecetasBuscadas_BA.removeAll();
				String personas = "";
				String ingrediente = "";
				if (comboNumPersonas_BA.getSelectedIndex() != 0) {
					personas = comboNumPersonas_BA.getSelectedItem().toString();
				}
				if (comboBoxIngrediente_BA.getSelectedIndex() != 0) {
					ingrediente = comboBoxIngrediente_BA.getSelectedItem().toString();
				}

				ArrayList<RecetaVO> listaBuscadas_BA = o.busqueda(textFieldBusqueda_BA.getText(), personas, ingrediente,
						"Entrante");
				for (RecetaVO receta : listaBuscadas_BA) {
					listaRecetasBuscadas_BA.add(receta.getNombre());
					recetas_BA.add(receta);
				}
			}
		});

		// Separador
		JSeparator separador_BA = new JSeparator();
		separador_BA.setForeground(new Color(0, 0, 0));
		separador_BA.setBackground(new Color(0, 0, 0));
		separador_BA.setBounds(12, 136, 856, 23);
		panelEntrantes.add(separador_BA);

		// Boton ver receta seleccionada
		JButton botonVerReceta_BA = new JButton("Ver Receta");
		botonVerReceta_BA.setFont(new Font("Calibri", Font.BOLD, 23));
		botonVerReceta_BA.setBackground(new Color(173, 255, 47));
		botonVerReceta_BA.setBounds(182, 448, 220, 60);
		panelEntrantes.add(botonVerReceta_BA);

		// Accion del boton de ver receta seleccionada
		botonVerReceta_BA.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (listaRecetasBuscadas_BA.getSelectedIndex() >= 0) {
					RecetaAbierta ra = new RecetaAbierta(recetas_BA.get(listaRecetasBuscadas_BA.getSelectedIndex()));
					ra.setIconImage(new ImageIcon("images/IconoAppRecortado.png").getImage());
					ra.setVisible(true);
				}
			}
		});

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(574, 136, 36, 385);
		panelEntrantes.add(separator);

		JLabel labelRecomendaciones_BA = new JLabel("Entrantes recomendados:");
		labelRecomendaciones_BA.setForeground(Color.WHITE);
		labelRecomendaciones_BA.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecomendaciones_BA.setBounds(583, 148, 273, 23);
		panelEntrantes.add(labelRecomendaciones_BA);

		JLabel labelTipo_BA = new JLabel("Tipo de plato:");
		labelTipo_BA.setForeground(Color.WHITE);
		labelTipo_BA.setFont(new Font("Calibri", Font.BOLD, 18));
		labelTipo_BA.setBounds(534, 84, 116, 23);
		panelEntrantes.add(labelTipo_BA);

		JLabel labelTODOS = new JLabel("Entrantes");
		labelTODOS.setFont(new Font("Calibri", Font.BOLD, 18));
		labelTODOS.setForeground(new Color(255, 153, 0));
		labelTODOS.setBounds(650, 84, 116, 23);
		panelEntrantes.add(labelTODOS);

		// Panel Primeros
		JPanel panelPrimeros = new JPanel();
		panelPrimeros.setOpaque(false);
		pantallaMenu.add(panelPrimeros, "panelPrimeros");
		panelPrimeros.setLayout(null);

		// Text field Primeros
		textFieldPrimeros = new JTextField();
		textFieldPrimeros.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldPrimeros.setColumns(10);
		textFieldPrimeros.setBounds(12, 15, 703, 36);
		panelPrimeros.add(textFieldPrimeros);

		// Lista de recetas buscadas
		List listaRecetasBuscadas_Pr = new List();
		listaRecetasBuscadas_Pr.setFont(new Font("Calibri", Font.BOLD, 20));
		listaRecetasBuscadas_Pr.setBounds(12, 177, 556, 257);
		panelPrimeros.add(listaRecetasBuscadas_Pr);

		// Label ingredientes de busqueda avanzada
		JLabel labelIngrediente_Pr = new JLabel("Ingrediente Principal:");
		labelIngrediente_Pr.setForeground(Color.WHITE);
		labelIngrediente_Pr.setFont(new Font("Calibri", Font.BOLD, 18));
		labelIngrediente_Pr.setBounds(12, 100, 174, 23);
		panelPrimeros.add(labelIngrediente_Pr);

		// ComboBox de los ingredientes de busqueda avanzada
		JComboBox comboBoxIngrediente_Pr = new JComboBox();
		comboBoxIngrediente_Pr.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_Pr.setLightWeightPopupEnabled(false);
		comboBoxIngrediente_Pr.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxIngrediente_Pr.setBounds(198, 101, 204, 22);
		panelPrimeros.add(comboBoxIngrediente_Pr);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_Pr.addItem(ingredientesAplicacion.get(i));
		}

		// CombBox Numero de personas
		JComboBox comboNumPersonas_Pr = new JComboBox();
		comboNumPersonas_Pr.setModel(new DefaultComboBoxModel(new String[] { "-- Seleccione el número de personas --",
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboNumPersonas_Pr.setLightWeightPopupEnabled(false);
		comboNumPersonas_Pr.setFont(new Font("Calibri", Font.BOLD, 15));
		comboNumPersonas_Pr.setBounds(198, 65, 272, 22);
		panelPrimeros.add(comboNumPersonas_Pr);

		// Label numero de personas
		JLabel labelNumPersonas_Pr = new JLabel("Número de personas:");
		labelNumPersonas_Pr.setForeground(Color.WHITE);
		labelNumPersonas_Pr.setFont(new Font("Calibri", Font.BOLD, 18));
		labelNumPersonas_Pr.setBounds(12, 64, 174, 23);
		panelPrimeros.add(labelNumPersonas_Pr);

		// Label recetas
		JLabel labelRecetas_Pr = new JLabel("Recetas:");
		labelRecetas_Pr.setForeground(Color.WHITE);
		labelRecetas_Pr.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecetas_Pr.setBounds(12, 148, 174, 23);
		panelPrimeros.add(labelRecetas_Pr);

		// Boton buscar
		JButton botonBuscar_Pr = new JButton("Buscar");
		botonBuscar_Pr.setFont(new Font("Calibri", Font.BOLD, 23));
		botonBuscar_Pr.setBackground(new Color(245, 245, 245));
		botonBuscar_Pr.setBounds(727, 12, 116, 39);
		panelPrimeros.add(botonBuscar_Pr);

		// Accion del boton buscar
		botonBuscar_Pr.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				listaRecetasBuscadas_Pr.removeAll();
				String personas = "";
				String ingrediente = "";
				if (comboNumPersonas_Pr.getSelectedIndex() != 0) {
					personas = comboNumPersonas_Pr.getSelectedItem().toString();
				}
				if (comboBoxIngrediente_Pr.getSelectedIndex() != 0) {
					ingrediente = comboBoxIngrediente_Pr.getSelectedItem().toString();
				}
				ArrayList<RecetaVO> listaBuscadas_Pr = o.busqueda(textFieldPrimeros.getText(), personas, ingrediente,
						"Primero");
				for (RecetaVO receta : listaBuscadas_Pr) {
					listaRecetasBuscadas_Pr.add(receta.getNombre());
					recetas_Primero.add(receta);
				}
			}
		});

		// Separador
		JSeparator separador_Pr = new JSeparator();
		separador_Pr.setForeground(new Color(0, 0, 0));
		separador_Pr.setBackground(new Color(0, 0, 0));
		separador_Pr.setBounds(12, 136, 856, 23);
		panelPrimeros.add(separador_Pr);

		// Boton ver receta seleccionada
		JButton botonVerReceta_Pr = new JButton("Ver Receta");
		botonVerReceta_Pr.setFont(new Font("Calibri", Font.BOLD, 23));
		botonVerReceta_Pr.setBackground(new Color(173, 255, 47));
		botonVerReceta_Pr.setBounds(182, 448, 220, 60);
		panelPrimeros.add(botonVerReceta_Pr);

		// Accion del boton de ver receta seleccionada
		botonVerReceta_Pr.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (listaRecetasBuscadas_Pr.getSelectedIndex() >= 0) {
					RecetaAbierta ra = new RecetaAbierta(
							recetas_Primero.get(listaRecetasBuscadas_Pr.getSelectedIndex()));
					ra.setIconImage(new ImageIcon("images/IconoAppRecortado.png").getImage());
					ra.setVisible(true);
				}
			}
		});

		JSeparator separator_Pr = new JSeparator();
		separator_Pr.setOrientation(SwingConstants.VERTICAL);
		separator_Pr.setForeground(Color.BLACK);
		separator_Pr.setBackground(Color.BLACK);
		separator_Pr.setBounds(574, 136, 36, 385);
		panelPrimeros.add(separator_Pr);

		JLabel labelRecomendaciones_Pr = new JLabel("Primeros recomendados:");
		labelRecomendaciones_Pr.setForeground(Color.WHITE);
		labelRecomendaciones_Pr.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecomendaciones_Pr.setBounds(583, 148, 285, 23);
		panelPrimeros.add(labelRecomendaciones_Pr);

		JLabel labelTipo_Pr = new JLabel("Tipo de plato:");
		labelTipo_Pr.setForeground(Color.WHITE);
		labelTipo_Pr.setFont(new Font("Calibri", Font.BOLD, 18));
		labelTipo_Pr.setBounds(534, 84, 116, 23);
		panelPrimeros.add(labelTipo_Pr);

		JLabel labelTODOS_Pr = new JLabel("Primeros");
		labelTODOS_Pr.setFont(new Font("Calibri", Font.BOLD, 18));
		labelTODOS_Pr.setForeground(new Color(255, 153, 0));
		labelTODOS_Pr.setBounds(650, 84, 116, 23);
		panelPrimeros.add(labelTODOS_Pr);

		// Panel Segundos
		JPanel panelSegundos = new JPanel();
		panelSegundos.setOpaque(false);
		pantallaMenu.add(panelSegundos, "panelSegundos");
		panelSegundos.setLayout(null);

		// Text field Primeros
		textFieldSegundos = new JTextField();
		textFieldSegundos.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldSegundos.setColumns(10);
		textFieldSegundos.setBounds(12, 15, 703, 36);
		panelSegundos.add(textFieldSegundos);

		// Lista de recetas buscadas
		List listaRecetasBuscadas_S = new List();
		listaRecetasBuscadas_S.setFont(new Font("Calibri", Font.BOLD, 20));
		listaRecetasBuscadas_S.setBounds(12, 177, 556, 257);
		panelSegundos.add(listaRecetasBuscadas_S);

		// Label ingredientes de busqueda avanzada
		JLabel labelIngrediente_S = new JLabel("Ingrediente Principal:");
		labelIngrediente_S.setForeground(Color.WHITE);
		labelIngrediente_S.setFont(new Font("Calibri", Font.BOLD, 18));
		labelIngrediente_S.setBounds(12, 100, 174, 23);
		panelSegundos.add(labelIngrediente_S);

		// ComboBox de los ingredientes de busqueda avanzada
		JComboBox comboBoxIngrediente_S = new JComboBox();
		comboBoxIngrediente_S.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_S.setLightWeightPopupEnabled(false);
		comboBoxIngrediente_S.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxIngrediente_S.setBounds(198, 101, 204, 22);
		panelSegundos.add(comboBoxIngrediente_S);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_S.addItem(ingredientesAplicacion.get(i));
		}

		// CombBox Numero de personas
		JComboBox comboNumPersonas_S = new JComboBox();
		comboNumPersonas_S.setModel(new DefaultComboBoxModel(new String[] { "-- Seleccione el número de personas --",
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboNumPersonas_S.setLightWeightPopupEnabled(false);
		comboNumPersonas_S.setFont(new Font("Calibri", Font.BOLD, 15));
		comboNumPersonas_S.setBounds(198, 65, 272, 22);
		panelSegundos.add(comboNumPersonas_S);

		// Label numero de personas
		JLabel labelNumPersonas_S = new JLabel("Número de personas:");
		labelNumPersonas_S.setForeground(Color.WHITE);
		labelNumPersonas_S.setFont(new Font("Calibri", Font.BOLD, 18));
		labelNumPersonas_S.setBounds(12, 64, 174, 23);
		panelSegundos.add(labelNumPersonas_S);

		// Label recetas
		JLabel labelRecetas_S = new JLabel("Recetas:");
		labelRecetas_S.setForeground(Color.WHITE);
		labelRecetas_S.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecetas_S.setBounds(12, 148, 174, 23);
		panelSegundos.add(labelRecetas_S);

		// Boton buscar
		JButton botonBuscar_S = new JButton("Buscar");
		botonBuscar_S.setFont(new Font("Calibri", Font.BOLD, 23));
		botonBuscar_S.setBackground(new Color(245, 245, 245));
		botonBuscar_S.setBounds(727, 12, 116, 39);
		panelSegundos.add(botonBuscar_S);

		// Accion del boton buscar
		botonBuscar_S.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				listaRecetasBuscadas_S.removeAll();
				String personas = "";
				String ingrediente = "";
				if (comboNumPersonas_S.getSelectedIndex() != 0) {
					personas = comboNumPersonas_S.getSelectedItem().toString();
				}
				if (comboBoxIngrediente_S.getSelectedIndex() != 0) {
					ingrediente = comboBoxIngrediente_S.getSelectedItem().toString();
				}

				ArrayList<RecetaVO> listaBuscadas_S = o.busqueda(textFieldSegundos.getText(), personas, ingrediente,
						"Segundo");
				for (RecetaVO receta : listaBuscadas_S) {
					listaRecetasBuscadas_S.add(receta.getNombre());
					recetas_Segundo.add(receta);
				}
			}
		});

		// Separador
		JSeparator separador_S = new JSeparator();
		separador_S.setForeground(new Color(0, 0, 0));
		separador_S.setBackground(new Color(0, 0, 0));
		separador_S.setBounds(12, 136, 856, 23);
		panelSegundos.add(separador_S);

		// Boton ver receta seleccionada
		JButton botonVerReceta_S = new JButton("Ver Receta");
		botonVerReceta_S.setFont(new Font("Calibri", Font.BOLD, 23));
		botonVerReceta_S.setBackground(new Color(173, 255, 47));
		botonVerReceta_S.setBounds(182, 448, 220, 60);
		panelSegundos.add(botonVerReceta_S);

		// Accion del boton de ver receta seleccionada
		botonVerReceta_S.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (listaRecetasBuscadas_S.getSelectedIndex() >= 0) {
					RecetaAbierta ra = new RecetaAbierta(
							recetas_Segundo.get(listaRecetasBuscadas_S.getSelectedIndex()));
					ra.setIconImage(new ImageIcon("images/IconoAppRecortado.png").getImage());
					ra.setVisible(true);
				}
			}
		});

		JSeparator separator_S = new JSeparator();
		separator_S.setOrientation(SwingConstants.VERTICAL);
		separator_S.setForeground(Color.BLACK);
		separator_S.setBackground(Color.BLACK);
		separator_S.setBounds(574, 136, 36, 385);
		panelSegundos.add(separator_S);

		JLabel labelRecomendaciones_S = new JLabel("Segundos recomendados:");
		labelRecomendaciones_S.setForeground(Color.WHITE);
		labelRecomendaciones_S.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecomendaciones_S.setBounds(583, 148, 285, 23);
		panelSegundos.add(labelRecomendaciones_S);

		JLabel labelTipo_S = new JLabel("Tipo de plato:");
		labelTipo_S.setForeground(Color.WHITE);
		labelTipo_S.setFont(new Font("Calibri", Font.BOLD, 18));
		labelTipo_S.setBounds(534, 84, 116, 23);
		panelSegundos.add(labelTipo_S);

		JLabel labelTODOS_S = new JLabel("Segundos");
		labelTODOS_S.setFont(new Font("Calibri", Font.BOLD, 18));
		labelTODOS_S.setForeground(new Color(255, 153, 0));
		labelTODOS_S.setBounds(650, 84, 116, 23);
		panelSegundos.add(labelTODOS_S);

		// Panel Postres
		JPanel panelPostres = new JPanel();
		panelPostres.setOpaque(false);
		pantallaMenu.add(panelPostres, "panelPostres");
		panelPostres.setLayout(null);

		// Text field Primeros
		textFieldPostres = new JTextField();
		textFieldPostres.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldPostres.setColumns(10);
		textFieldPostres.setBounds(12, 15, 703, 36);
		panelPostres.add(textFieldPostres);

		// Lista de recetas buscadas
		List listaRecetasBuscadas_Pos = new List();
		listaRecetasBuscadas_Pos.setFont(new Font("Calibri", Font.BOLD, 20));
		listaRecetasBuscadas_Pos.setBounds(12, 177, 556, 257);
		panelPostres.add(listaRecetasBuscadas_Pos);

		// Label ingredientes de busqueda avanzada
		JLabel labelIngrediente_Pos = new JLabel("Ingrediente Principal:");
		labelIngrediente_Pos.setForeground(Color.WHITE);
		labelIngrediente_Pos.setFont(new Font("Calibri", Font.BOLD, 18));
		labelIngrediente_Pos.setBounds(12, 100, 174, 23);
		panelPostres.add(labelIngrediente_Pos);

		// ComboBox de los ingredientes de busqueda avanzada
		JComboBox comboBoxIngrediente_Pos = new JComboBox();
		comboBoxIngrediente_Pos.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_Pos.setLightWeightPopupEnabled(false);
		comboBoxIngrediente_Pos.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxIngrediente_Pos.setBounds(198, 101, 204, 22);
		panelPostres.add(comboBoxIngrediente_Pos);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_Pos.addItem(ingredientesAplicacion.get(i));
		}

		// CombBox Numero de personas
		JComboBox comboNumPersonas_Pos = new JComboBox();
		comboNumPersonas_Pos.setModel(new DefaultComboBoxModel(
				new String[] { "-- Seleccione el número de personas --", "1", "2", "4", "8" }));
		comboNumPersonas_Pos.setLightWeightPopupEnabled(false);
		comboNumPersonas_Pos.setFont(new Font("Calibri", Font.BOLD, 15));
		comboNumPersonas_Pos.setBounds(198, 65, 272, 22);
		panelPostres.add(comboNumPersonas_Pos);

		// Label numero de personas
		JLabel labelNumPersonas_Pos = new JLabel("Número de personas:");
		labelNumPersonas_Pos.setForeground(Color.WHITE);
		labelNumPersonas_Pos.setFont(new Font("Calibri", Font.BOLD, 18));
		labelNumPersonas_Pos.setBounds(12, 64, 174, 23);
		panelPostres.add(labelNumPersonas_Pos);

		// Label recetas
		JLabel labelRecetas_Pos = new JLabel("Recetas:");
		labelRecetas_Pos.setForeground(Color.WHITE);
		labelRecetas_Pos.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecetas_Pos.setBounds(12, 148, 174, 23);
		panelPostres.add(labelRecetas_Pos);

		// Boton buscar
		JButton botonBuscar_Pos = new JButton("Buscar");
		botonBuscar_Pos.setFont(new Font("Calibri", Font.BOLD, 23));
		botonBuscar_Pos.setBackground(new Color(245, 245, 245));
		botonBuscar_Pos.setBounds(727, 12, 116, 39);
		panelPostres.add(botonBuscar_Pos);

		// Accion del boton buscar
		botonBuscar_Pos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				listaRecetasBuscadas_Pos.removeAll();
				String personas = "";
				String ingrediente = "";
				if (comboNumPersonas_Pos.getSelectedIndex() != 0) {
					personas = comboNumPersonas_Pos.getSelectedItem().toString();
				}
				if (comboBoxIngrediente_Pos.getSelectedIndex() != 0) {
					ingrediente = comboBoxIngrediente_Pos.getSelectedItem().toString();
				}

				ArrayList<RecetaVO> listaBuscadas_Pos = o.busqueda(textFieldPostres.getText(), personas, ingrediente,
						"Postre");
				for (RecetaVO receta : listaBuscadas_Pos) {
					listaRecetasBuscadas_Pos.add(receta.getNombre());
					recetas_Postre.add(receta);
				}
			}
		});

		// Separador
		JSeparator separador_Pos = new JSeparator();
		separador_Pos.setForeground(new Color(0, 0, 0));
		separador_Pos.setBackground(new Color(0, 0, 0));
		separador_Pos.setBounds(12, 136, 856, 23);
		panelPostres.add(separador_Pos);

		// Boton ver receta seleccionada
		JButton botonVerReceta_Pos = new JButton("Ver Receta");
		botonVerReceta_Pos.setFont(new Font("Calibri", Font.BOLD, 23));
		botonVerReceta_Pos.setBackground(new Color(173, 255, 47));
		botonVerReceta_Pos.setBounds(182, 448, 220, 60);
		panelPostres.add(botonVerReceta_Pos);

		// Accion del boton de ver receta seleccionada
		botonVerReceta_Pos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (listaRecetasBuscadas_Pos.getSelectedIndex() >= 0) {
					RecetaAbierta ra = new RecetaAbierta(
							recetas_Postre.get(listaRecetasBuscadas_Pos.getSelectedIndex()));
					ra.setIconImage(new ImageIcon("images/IconoAppRecortado.png").getImage());
					ra.setVisible(true);
				}
			}
		});

		JSeparator separator_Pos = new JSeparator();
		separator_Pos.setOrientation(SwingConstants.VERTICAL);
		separator_Pos.setForeground(Color.BLACK);
		separator_Pos.setBackground(Color.BLACK);
		separator_Pos.setBounds(574, 136, 36, 385);
		panelPostres.add(separator_Pos);

		JLabel labelRecomendaciones_Pos = new JLabel("Postres recomendados:");
		labelRecomendaciones_Pos.setForeground(Color.WHITE);
		labelRecomendaciones_Pos.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecomendaciones_Pos.setBounds(583, 148, 188, 23);
		panelPostres.add(labelRecomendaciones_Pos);

		JLabel labelTipo_Pos = new JLabel("Tipo de plato:");
		labelTipo_Pos.setForeground(Color.WHITE);
		labelTipo_Pos.setFont(new Font("Calibri", Font.BOLD, 18));
		labelTipo_Pos.setBounds(534, 84, 116, 23);
		panelPostres.add(labelTipo_Pos);

		JLabel labelTODOS_Pos = new JLabel("Postres");
		labelTODOS_Pos.setFont(new Font("Calibri", Font.BOLD, 18));
		labelTODOS_Pos.setForeground(new Color(255, 153, 0));
		labelTODOS_Pos.setBounds(650, 84, 116, 23);
		panelPostres.add(labelTODOS_Pos);

		// Panel Proponer
		JPanel panelProponer = new JPanel();
		panelProponer.setOpaque(false);
		pantallaMenu.add(panelProponer, "panelProponer");
		panelProponer.setLayout(null);

		f = new Font("Calibri", Font.BOLD, 18);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("Dialog.Italic", Font.BOLD, 14);
		}

		Font f1 = new Font("Calibri", Font.BOLD, 15);
		if (!f1.getFamily().equals("Calibri")) {
			f1 = new Font("Dialog.Italic", Font.BOLD, 12);
		}

		Font f2 = new Font("Calibri", Font.BOLD, 23);
		if (!f2.getFamily().equals("Calibri")) {
			f2 = new Font("Dialog.Italic", Font.BOLD, 18);
		}

		// ComboBox del numero de personas
		final JComboBox comboBoxNumPersonas_PR = new JComboBox();
		comboBoxNumPersonas_PR.setBounds(171, 48, 272, 22);
		comboBoxNumPersonas_PR
				.setModel(new DefaultComboBoxModel(new String[] { "-- Seleccione el número de personas --", "1", "2",
						"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboBoxNumPersonas_PR.setFont(f1);
		panelProponer.add(comboBoxNumPersonas_PR);
		comboBoxNumPersonas_PR.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_PR = new JComboBox();
		comboBoxIngrediente_PR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_PR.setFont(f1);
		comboBoxIngrediente_PR.setBounds(592, 124, 243, 22);
		panelProponer.add(comboBoxIngrediente_PR);
		comboBoxIngrediente_PR.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco1_PR = new JLabel("*");
		labelAsterisco1_PR.setForeground(Color.RED);
		labelAsterisco1_PR.setFont(f);
		labelAsterisco1_PR.setBounds(442, 15, 19, 16);
		panelProponer.add(labelAsterisco1_PR);

		// Text field del nombre de la receta
		textFieldNombre_PR = new JTextField();
		textFieldNombre_PR.setBounds(171, 13, 272, 22);
		textFieldNombre_PR.setFont(f1);
		panelProponer.add(textFieldNombre_PR);
		textFieldNombre_PR.setColumns(10);

		// ComboBox del ingrediente Principal
		final JComboBox comboBoxIngPrinc_PR = new JComboBox();
		comboBoxIngPrinc_PR.setBounds(631, 13, 204, 22);
		comboBoxIngPrinc_PR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngPrinc_PR.setFont(f1);
		panelProponer.add(comboBoxIngPrinc_PR);
		comboBoxIngPrinc_PR.setLightWeightPopupEnabled(false);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngPrinc_PR.addItem(ingredientesAplicacion.get(i));
		}

		// Label del nombre de la receta
		JLabel labelNombre_PR = new JLabel("Nombre de la receta:");
		labelNombre_PR.setBounds(0, 12, 174, 23);
		labelNombre_PR.setForeground(Color.WHITE);
		labelNombre_PR.setFont(f);
		panelProponer.add(labelNombre_PR);

		// Label del ingrediente principal
		JLabel labelIngPrinc_PR = new JLabel("Ingrediente Principal:");
		labelIngPrinc_PR.setBounds(465, 12, 174, 23);
		labelIngPrinc_PR.setForeground(Color.WHITE);
		labelIngPrinc_PR.setFont(f);
		panelProponer.add(labelIngPrinc_PR);

		// Label del numero de personas
		JLabel labelNumPersonas_PR = new JLabel("Número de personas:");
		labelNumPersonas_PR.setBounds(0, 48, 174, 23);
		labelNumPersonas_PR.setForeground(Color.WHITE);
		labelNumPersonas_PR.setFont(f);
		panelProponer.add(labelNumPersonas_PR);

		JComboBox comboBoxUnidades_PR = new JComboBox();
		comboBoxUnidades_PR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione  -", "unidades", "gramos",
				"kilos", "litros", "centilitros", "cucharadas", "pizcas", "vaso" }));
		comboBoxUnidades_PR.setLightWeightPopupEnabled(false);
		comboBoxUnidades_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxUnidades_PR.setBounds(701, 153, 134, 22);
		panelProponer.add(comboBoxUnidades_PR);

		// Label de la URL de la imagen
		JLabel labelTipo_PR = new JLabel("Tipo de plato:");
		labelTipo_PR.setBounds(0, 84, 174, 23);
		labelTipo_PR.setForeground(Color.WHITE);
		labelTipo_PR.setFont(f);
		panelProponer.add(labelTipo_PR);

		// Label descripcion de la receta
		JLabel labelDescripcion_PR = new JLabel("Descripción de la receta:");
		labelDescripcion_PR.setBounds(0, 123, 207, 23);
		labelDescripcion_PR.setForeground(Color.WHITE);
		labelDescripcion_PR.setFont(f);
		panelProponer.add(labelDescripcion_PR);

		// Label Otros ingredientes
		JLabel labelIngredientes_PR = new JLabel("Otros inredientes:");
		labelIngredientes_PR.setBounds(465, 99, 174, 23);
		labelIngredientes_PR.setForeground(Color.WHITE);
		labelIngredientes_PR.setFont(f);
		panelProponer.add(labelIngredientes_PR);

		// Separador Vertical
		JSeparator separadorVertical_PR = new JSeparator();
		separadorVertical_PR.setOrientation(SwingConstants.VERTICAL);
		separadorVertical_PR.setBounds(455, 0, 12, 508);
		panelProponer.add(separadorVertical_PR);

		// Separador horizontal
		JSeparator separadorHorizontal_PR = new JSeparator();
		separadorHorizontal_PR.setBounds(455, 83, 403, 12);
		panelProponer.add(separadorHorizontal_PR);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_PR = new JLabel("Cantidad:");
		labelCantidadPrinc_PR.setForeground(Color.WHITE);
		labelCantidadPrinc_PR.setFont(f);
		labelCantidadPrinc_PR.setBounds(496, 47, 91, 23);
		panelProponer.add(labelCantidadPrinc_PR);

		// Text Field cantidad ingrediente principal
		textFieldCantidadPrinc_PR = new JTextField();
		textFieldCantidadPrinc_PR.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if ((!Character.isDigit(vchar)) || vchar == KeyEvent.VK_DELETE) {
					arg0.consume();
				}
			}
		});
		textFieldCantidadPrinc_PR.setFont(f1);
		textFieldCantidadPrinc_PR.setBounds(592, 48, 97, 22);
		panelProponer.add(textFieldCantidadPrinc_PR);
		textFieldCantidadPrinc_PR.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final JTextArea textAreaDescripcion_PR = new JTextArea();
		textAreaDescripcion_PR.setFont(f1);
		textAreaDescripcion_PR.setLineWrap(true);
		textAreaDescripcion_PR.setWrapStyleWord(true);
		JScrollPane scrollPanel = new JScrollPane(textAreaDescripcion_PR, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel.setFont(f1);
		scrollPanel.setBounds(0, 152, 440, 269);
		panelProponer.add(scrollPanel);

		// Boton de enviar la receta propuesta
		JButton botonEnviarPropuesta_PR = new JButton("Enviar Receta Propuesta");
		botonEnviarPropuesta_PR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonEnviarPropuesta_PR.setBackground(new Color(173, 255, 47));
		botonEnviarPropuesta_PR.setBounds(4, 432, 272, 60);
		panelProponer.add(botonEnviarPropuesta_PR);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_PR.addItem(ingredientesAplicacion.get(i));
		}

		// Label ingrediente
		JLabel labelIngrediente_PR = new JLabel("Ingrediente:");
		labelIngrediente_PR.setForeground(Color.WHITE);
		labelIngrediente_PR.setFont(f);
		labelIngrediente_PR.setBounds(496, 123, 117, 23);
		panelProponer.add(labelIngrediente_PR);

		// Label cantidad del ingrediente
		JLabel labelCantidad_PR = new JLabel("Cantidad:");
		labelCantidad_PR.setForeground(Color.WHITE);
		labelCantidad_PR.setFont(f);
		labelCantidad_PR.setBounds(496, 152, 91, 23);
		panelProponer.add(labelCantidad_PR);

		// TextField cantidad del ingrediente
		textFieldCantidad_PR = new JTextField();
		textFieldCantidad_PR.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
				if ((!Character.isDigit(vchar)) || vchar == KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		textFieldCantidad_PR.setFont(f1);
		textFieldCantidad_PR.setColumns(10);
		textFieldCantidad_PR.setBounds(592, 153, 100, 22);
		panelProponer.add(textFieldCantidad_PR);

		// Boton añadir ingrediente
		JButton botonAnyadirIngrediente_PR = new JButton("Añadir Ingrediente");
		botonAnyadirIngrediente_PR.setFont(f1);
		botonAnyadirIngrediente_PR.setBackground(new Color(245, 245, 245));
		botonAnyadirIngrediente_PR.setBounds(465, 459, 180, 33);
		panelProponer.add(botonAnyadirIngrediente_PR);

		// Boton borrar ingrediente
		JButton botonBorrarIngrediente_PR = new JButton("Borrar Ingrediente");
		botonBorrarIngrediente_PR.setFont(f1);
		botonBorrarIngrediente_PR.setBackground(new Color(245, 245, 245));
		botonBorrarIngrediente_PR.setBounds(667, 459, 180, 33);
		panelProponer.add(botonBorrarIngrediente_PR);

		// Lista con los ingredientes aÃ±adidos
		final List listaIngredientes_PR = new List();
		listaIngredientes_PR.setFont(f1);
		listaIngredientes_PR.setMultipleMode(false);
		listaIngredientes_PR.setBounds(465, 192, 383, 256);
		panelProponer.add(listaIngredientes_PR);

		JLabel labelAsterisco2_PR = new JLabel("*");
		labelAsterisco2_PR.setForeground(Color.RED);
		labelAsterisco2_PR.setFont(f);
		labelAsterisco2_PR.setBounds(442, 48, 19, 16);
		panelProponer.add(labelAsterisco2_PR);

		JLabel labelAsterisco3_PR = new JLabel("*");
		labelAsterisco3_PR.setForeground(Color.RED);
		labelAsterisco3_PR.setFont(f);
		labelAsterisco3_PR.setBounds(442, 141, 19, 16);
		panelProponer.add(labelAsterisco3_PR);

		JLabel labelAsterisco4_PR = new JLabel("*");
		labelAsterisco4_PR.setForeground(Color.RED);
		labelAsterisco4_PR.setFont(f);
		labelAsterisco4_PR.setBounds(839, 15, 19, 16);
		panelProponer.add(labelAsterisco4_PR);

		JLabel labelAsterisco5_PR = new JLabel("*");
		labelAsterisco5_PR.setForeground(Color.RED);
		labelAsterisco5_PR.setFont(f);
		labelAsterisco5_PR.setBounds(839, 50, 19, 16);
		panelProponer.add(labelAsterisco5_PR);

		JLabel labelCampoObligatorio_PR = new JLabel("* Campo obligatorio");
		labelCampoObligatorio_PR.setForeground(Color.RED);
		labelCampoObligatorio_PR.setFont(f);
		labelCampoObligatorio_PR.setBounds(0, 505, 174, 16);
		panelProponer.add(labelCampoObligatorio_PR);

		// ComboBox del tipo de plato
		final JComboBox comboBoxTipo_PR = new JComboBox();
		comboBoxTipo_PR.setModel(new DefaultComboBoxModel(
				new String[] { "-- Seleccione el tipo de plato --", "Entrante", "Primero", "Segundo", "Postre" }));
		comboBoxTipo_PR.setFont(f1);
		comboBoxTipo_PR.setBounds(171, 84, 272, 22);
		panelProponer.add(comboBoxTipo_PR);
		comboBoxTipo_PR.setLightWeightPopupEnabled(false);

		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(f);
		label.setBounds(442, 77, 19, 16);
		panelProponer.add(label);

		// Boton de cancelar receta
		JButton botonCancelarReceta_PR = new JButton("Cancelar");
		botonCancelarReceta_PR.setFont(f2);
		botonCancelarReceta_PR.setBackground(new Color(245, 245, 245));
		botonCancelarReceta_PR.setBounds(288, 432, 155, 60);
		panelProponer.add(botonCancelarReceta_PR);

		JComboBox comboBoxUnidadesPrinc_PR = new JComboBox();
		comboBoxUnidadesPrinc_PR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione  -", "unidades",
				"gramos", "kilos", "litros", "centilitros", "cucharadas", "pizcas", "vaso" }));
		comboBoxUnidadesPrinc_PR.setLightWeightPopupEnabled(false);
		comboBoxUnidadesPrinc_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxUnidadesPrinc_PR.setBounds(701, 47, 134, 22);
		panelProponer.add(comboBoxUnidadesPrinc_PR);

		// Accion del boton de cancelar receta

		botonCancelarReceta_PR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				textFieldNombre_PR.setText("");
				comboBoxNumPersonas_PR.setSelectedIndex(0);
				textAreaDescripcion_PR.setText(" ");
				comboBoxIngPrinc_PR.setSelectedIndex(0);
				textFieldCantidadPrinc_PR.setText("");
				comboBoxIngrediente_PR.setSelectedIndex(0);
				comboBoxTipo_PR.setSelectedIndex(0);
				textFieldCantidad_PR.setText("");
				listaIngredientes_PR.removeAll();
				comboBoxUnidades_PR.setSelectedIndex(0);
				comboBoxUnidadesPrinc_PR.setSelectedIndex(0);
			}
		});

		// Accion del boton de borrar ingrediente
		botonBorrarIngrediente_PR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaIngredientes_PR.getSelectedItem() != null) {
					indexIngredientes_PR.remove(listaIngredientes_PR.getSelectedItem().toString().substring(0,
							listaIngredientes_PR.getSelectedItem().toString().indexOf('-') - 1));
					listaIngredientes_PR.remove(listaIngredientes_PR.getSelectedIndex());
				}
			}
		});

		// Accion del boton de añadir ingrediente
		botonAnyadirIngrediente_PR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if ((comboBoxIngrediente_PR.getSelectedIndex() != 0) && !textFieldCantidad_PR.getText().equals("")
						&& comboBoxUnidades_PR.getSelectedIndex() != 0) {
					if (comboBoxIngPrinc_PR.getSelectedIndex() == comboBoxIngrediente_PR.getSelectedIndex()) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_PR.contains(comboBoxIngrediente_PR.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_PR.add(comboBoxIngrediente_PR.getSelectedItem() + " - "
								+ textFieldCantidad_PR.getText() + " - " + comboBoxUnidades_PR.getSelectedItem());
						indexIngredientes_PR.add(comboBoxIngrediente_PR.getSelectedItem().toString());
					}
				}
			}
		});

		// Accion del boton de enviar la receta propuesta
		botonEnviarPropuesta_PR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (!textFieldNombre_PR.getText().equals("") && comboBoxNumPersonas_PR.getSelectedIndex() != 0
						&& !textAreaDescripcion_PR.getText().equals("") && comboBoxIngPrinc_PR.getSelectedIndex() != 0
						&& !textFieldCantidadPrinc_PR.getText().equals("")
						&& comboBoxUnidadesPrinc_PR.getSelectedIndex() != 0
						&& comboBoxTipo_PR.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_PR.getText().length() > 4
							|| textFieldCantidad_PR.getText().length() > 4) {
						JOptionPane.showMessageDialog(menuPrincipal, "Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes = new ArrayList<String>();
						ArrayList<String> pesoIngredientes = new ArrayList<String>();
						ArrayList<String> unidadIngredientes = new ArrayList<String>();
						String[] s = listaIngredientes_PR.getItems();

						ingredientes.add(comboBoxIngPrinc_PR.getSelectedItem().toString());
						pesoIngredientes.add(textFieldCantidadPrinc_PR.getText());
						unidadIngredientes.add(comboBoxUnidadesPrinc_PR.getSelectedItem().toString());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0, elemento.indexOf('-') - 1);
							String resto = elemento.substring(elemento.indexOf('-') + 1, elemento.length());
							String cantidad = resto.substring(0, resto.indexOf('-') - 1);
							String unidad = resto.substring(resto.indexOf('-') + 1, resto.length());
							ingredientes.add(ingrediente);
							pesoIngredientes.add(cantidad);
							unidadIngredientes.add(unidad);
						}

						o.addReceta(textFieldNombre_PR.getText(), textAreaDescripcion_PR.getText(),
								comboBoxTipo_PR.getSelectedItem().toString(),
								comboBoxNumPersonas_PR.getSelectedItem().toString(), ingredientes, pesoIngredientes,
								unidadIngredientes);

						JOptionPane.showMessageDialog(menuPrincipal,
								"La receta propuesta ha sido enviada correctamente.");
						textFieldNombre_PR.setText("");
						comboBoxNumPersonas_PR.setSelectedIndex(0);
						textAreaDescripcion_PR.setText("");
						comboBoxIngPrinc_PR.setSelectedIndex(0);
						textFieldCantidadPrinc_PR.setText("");
						comboBoxIngrediente_PR.setSelectedIndex(0);
						comboBoxTipo_PR.setSelectedIndex(0);
						textFieldCantidad_PR.setText("");
						indexIngredientes_PR.clear();
						listaIngredientes_PR.removeAll();
						ingredientes.clear();
						pesoIngredientes.clear();
					}

				} else {
					// Si algun campo no esta rellenado correctamente
					JOptionPane.showMessageDialog(menuPrincipal,
							"Uno o mas campos obligatorios no estan rellenados correctamente.");
				}
			}
		});

		// Panel Mas destacados
		JPanel panelDestacados = new JPanel();
		panelDestacados.setOpaque(false);
		pantallaMenu.add(panelDestacados, "panelDestacados");
		panelDestacados.setLayout(null);

		// Panel Ayuda
		JPanel panelAyuda = new JPanel();
		panelAyuda.setOpaque(false);
		pantallaMenu.add(panelAyuda, "panelAyuda");
		panelAyuda.setLayout(null);

		JLabel lblSiNecesitasAyuda = new JLabel(
				"Si necesitas ayuda no dudes en ponerte en contacto con cualquiera de nuestros t\u00E9cnicos, estar\u00E1n encantados");
		lblSiNecesitasAyuda.setForeground(Color.WHITE);
		lblSiNecesitasAyuda.setFont(new Font("Calibri", Font.BOLD, 18));
		lblSiNecesitasAyuda.setBounds(12, 29, 844, 23);
		panelAyuda.add(lblSiNecesitasAyuda);

		JLabel lblDeAtenderte = new JLabel(
				"de atenderte. Para ello puedes enviar un e-mail a cualquiera de los siguientes correos:");
		lblDeAtenderte.setForeground(Color.WHITE);
		lblDeAtenderte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDeAtenderte.setBounds(12, 50, 844, 23);
		panelAyuda.add(lblDeAtenderte);

		JLabel lblunizares = new JLabel("681061@unizar.es");
		lblunizares.setForeground(Color.WHITE);
		lblunizares.setFont(new Font("Calibri", Font.BOLD, 18));
		lblunizares.setForeground(new Color(255, 153, 0));
		lblunizares.setBounds(142, 86, 222, 23);
		panelAyuda.add(lblunizares);

		JLabel lblunizares_1 = new JLabel("681060@unizar.es");
		lblunizares_1.setForeground(Color.WHITE);
		lblunizares_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblunizares_1.setForeground(new Color(255, 153, 0));
		lblunizares_1.setBounds(142, 122, 222, 23);
		panelAyuda.add(lblunizares_1);

		JLabel lblunizares_2 = new JLabel("618051@unizar.es");
		lblunizares_2.setForeground(Color.WHITE);
		lblunizares_2.setFont(new Font("Calibri", Font.BOLD, 18));
		lblunizares_2.setForeground(new Color(255, 153, 0));
		lblunizares_2.setBounds(142, 158, 222, 23);
		panelAyuda.add(lblunizares_2);

		JLabel lblunizares_3 = new JLabel("679609@unizar.es");
		lblunizares_3.setForeground(Color.WHITE);
		lblunizares_3.setFont(new Font("Calibri", Font.BOLD, 18));
		lblunizares_3.setForeground(new Color(255, 153, 0));
		lblunizares_3.setBounds(142, 194, 222, 23);
		panelAyuda.add(lblunizares_3);

		JLabel lblunizares_4 = new JLabel("680182@unizar.es");
		lblunizares_4.setForeground(Color.WHITE);
		lblunizares_4.setFont(new Font("Calibri", Font.BOLD, 18));
		lblunizares_4.setForeground(new Color(255, 153, 0));
		lblunizares_4.setBounds(142, 230, 222, 23);
		panelAyuda.add(lblunizares_4);

		JLabel lblTanProntoComo = new JLabel(
				"Tan pronto como recibamos el e-mail nos pondremos manos a la obra para ayudarte.");
		lblTanProntoComo.setForeground(Color.WHITE);
		lblTanProntoComo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTanProntoComo.setBounds(12, 266, 844, 23);
		panelAyuda.add(lblTanProntoComo);

		JLabel iconoEquipo = new JLabel("");
		iconoEquipo.setBounds(531, 44, 193, 245);
		iconoEquipo.setIcon(new ImageIcon(imagenEquipo.getScaledInstance(193, 245, Image.SCALE_DEFAULT)));
		panelAyuda.add(iconoEquipo);

		JLabel labelSitienesAyuda = new JLabel(
				"Si tienes dudas sobre como utilizar la aplicación o quieres saber más sobre nosotros o sobre la aplicación puedes");
		labelSitienesAyuda.setForeground(Color.WHITE);
		labelSitienesAyuda.setFont(new Font("Calibri", Font.BOLD, 18));
		labelSitienesAyuda.setBounds(12, 323, 844, 23);
		panelAyuda.add(labelSitienesAyuda);

		JLabel lblEcharleUnVistazo = new JLabel(
				"hacer click en el siguiente enlace el cual te llevará a nuestra página web:");
		lblEcharleUnVistazo.setForeground(Color.WHITE);
		lblEcharleUnVistazo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblEcharleUnVistazo.setBounds(12, 345, 844, 23);
		panelAyuda.add(lblEcharleUnVistazo);

		JButton botonWeb = new JButton("www.me4l.ml");
		botonWeb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					openWebpage(new URL("http://www.me4l.ml"));
				} catch (MalformedURLException e) {
					System.out.println("errorcio");
				}
			}
		});
		botonWeb.setHorizontalAlignment(SwingConstants.LEFT);
		botonWeb.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 50));
		botonWeb.setForeground(Color.WHITE);
		botonWeb.setBackground(new Color(255, 153, 0));
		botonWeb.setBounds(212, 395, 360, 100);
		panelAyuda.add(botonWeb);

		// Indicador textual del menu
		final JLabel labelMenu = new JLabel("MENÚ PRINCIPAL");
		labelMenu.setForeground(new Color(255, 153, 0));
		labelMenu.setFont(new Font("Bauhaus 93", Font.BOLD, 80));
		labelMenu.setBounds(262, 12, 858, 162);
		menuPrincipal.add(labelMenu);
		IconoApp3.setIcon(new ImageIcon(imagenIcono.getScaledInstance(200, 169, Image.SCALE_DEFAULT)));
		IconoApp3.setBounds(33, 12, 200, 169);
		menuPrincipal.add(IconoApp3);

		// Botones
		final JButton botonEntrantes = new JButton("Entrantes");
		final JButton botonPrimeros = new JButton("Primeros");
		final JButton botonPostres = new JButton("Postres");
		final JButton botonSegundos = new JButton("Segundos");
		final JButton botonDestacados = new JButton("Más Destacados");
		final JButton botonProponer = new JButton("Proponer Receta");
		final JButton botonAyuda = new JButton("Ayuda");
		final JButton botonImagen = new JButton("");
		final JButton botonAdministrador = new JButton("Administrador");
		final JButton botonModificar = new JButton("Modificar Receta");
		final JButton botonAnyadir = new JButton("Añadir Receta");
		final JButton botonValidar = new JButton("Validar Receta");

		// Boton Imagen
		botonImagen.setBounds(12, 5, 238, 169);
		menuPrincipal.add(botonImagen);
		botonImagen.setOpaque(false);
		botonImagen.setContentAreaFilled(false);
		botonImagen.setBorderPainted(false);

		// Boton Busqueda Avanzada
		f = new Font("Calibri", Font.BOLD, 20);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("Dialog.Italic", Font.BOLD, 14);
		}

		botonEntrantes.setFont(f);
		botonEntrantes.setBackground(new Color(245, 245, 245));
		botonEntrantes.setBounds(12, 193, 235, 60);
		botonEntrantes.setHorizontalAlignment(SwingConstants.LEFT);
		botonEntrantes.setIcon(new ImageIcon(imagenEntrante.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

		menuPrincipal.add(botonEntrantes);

		// Accion del boton de Busqueda Avanzada
		botonEntrantes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("ENTRANTES");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonEntrantes.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelEntrantes");
			}
		});

		// Boton Primeros
		botonPrimeros.setBackground(new Color(245, 245, 245));
		botonPrimeros.setFont(f);
		botonPrimeros.setBounds(12, 253, 235, 60);
		botonPrimeros.setHorizontalAlignment(SwingConstants.LEFT);
		botonPrimeros.setIcon(new ImageIcon(imagenOne.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

		menuPrincipal.add(botonPrimeros);

		// Accion del boton de Primeros
		botonPrimeros.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("PRIMEROS");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonPrimeros.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelPrimeros");
			}
		});

		// Boton Postres
		botonPostres.setBackground(new Color(245, 245, 245));
		botonPostres.setFont(f);
		botonPostres.setBounds(12, 373, 235, 60);
		botonPostres.setHorizontalAlignment(SwingConstants.LEFT);
		botonPostres.setIcon(new ImageIcon(imagenThree.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

		menuPrincipal.add(botonPostres);

		// Accion del boton de Postres
		botonPostres.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("POSTRES");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonPostres.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelPostres");
			}
		});

		// Boton Segundos
		botonSegundos.setBackground(new Color(245, 245, 245));
		botonSegundos.setFont(f);
		botonSegundos.setBounds(12, 313, 235, 60);
		botonSegundos.setHorizontalAlignment(SwingConstants.LEFT);
		botonSegundos.setIcon(new ImageIcon(imagenTwo.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		menuPrincipal.add(botonSegundos);

		// Accion del boton de Segundos
		botonSegundos.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("SEGUNDOS");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonSegundos.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelSegundos");
			}
		});

		// Boton Mas Destacados
		botonDestacados.setBackground(new Color(245, 245, 245));
		botonDestacados.setFont(f);
		botonDestacados.setBounds(12, 493, 235, 60);
		botonDestacados.setHorizontalAlignment(SwingConstants.LEFT);
		botonDestacados.setIcon(new ImageIcon(imagenPlus.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

		menuPrincipal.add(botonDestacados);

		// Accion del boton de Mas Destacados
		botonDestacados.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("MÁS DESTACADOS");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonDestacados.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelDestacados");
			}
		});

		// Boton Proponer
		botonProponer.setBackground(new Color(245, 245, 245));
		botonProponer.setFont(f);
		botonProponer.setBounds(12, 433, 235, 60);
		botonProponer.setHorizontalAlignment(SwingConstants.LEFT);
		botonProponer.setIcon(new ImageIcon(imagenWrite.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		menuPrincipal.add(botonProponer);

		// Accion del boton de Proponer Receta
		botonProponer.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("PROPONER RECETA");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonProponer.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelProponer");
			}
		});

		// Panel del administrador
		JPanel panelAdministrador = new JPanel();
		pantallaMenu.add(panelAdministrador, "panelAdministrador");
		panelAdministrador.setOpaque(false);
		panelAdministrador.setLayout(null);
		// Panel administrador Card Layout
		JPanel panelAdminCard = new JPanel();
		panelAdminCard.setBounds(0, 38, 868, 483);
		panelAdministrador.add(panelAdminCard);
		panelAdminCard.setLayout(new CardLayout(0, 0));
		panelAdminCard.setOpaque(false);

		CardLayout cardAdmin = (CardLayout) panelAdminCard.getLayout();

		// Panel del administrador dentro del card layout
		JPanel panelAdmin = new JPanel();
		panelAdmin.setOpaque(false);
		panelAdminCard.add(panelAdmin, "panelAdmin");
		panelAdmin.setLayout(null);

		// Label informativo para el administrador
		f = new Font("Calibri", Font.BOLD, 20);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("Dialog.Italic", Font.BOLD, 14);
		}
		JLabel labelInfoAdmin = new JLabel("");
		labelInfoAdmin.setForeground(Color.WHITE);
		labelInfoAdmin.setBounds(87, 196, 756, 45);
		labelInfoAdmin.setFont(f);
		panelAdmin.add(labelInfoAdmin);

		// Panel validar dentro del card layout
		JPanel panelValidar = new JPanel();
		panelAdminCard.add(panelValidar, "panelValidar");
		panelValidar.setOpaque(false);
		panelValidar.setLayout(null);

		// Label con texto de recetas pendientes
		JLabel labelRecetasPendientes_VR = new JLabel("Recetas pendientes de ser validadas:");
		labelRecetasPendientes_VR.setForeground(Color.WHITE);
		f = new Font("Calibri", Font.BOLD, 18);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("Dialog.Italic", Font.BOLD, 16);
		}
		labelRecetasPendientes_VR.setFont(f);
		labelRecetasPendientes_VR.setBounds(57, 39, 340, 23);
		panelValidar.add(labelRecetasPendientes_VR);

		// Lista de recetas pendientes de ser validadas
		final List listaRecetasPendientesValidar_VR = new List();
		listaRecetasPendientesValidar_VR.setFont(new Font("Calibri", Font.BOLD, 20));
		listaRecetasPendientesValidar_VR.setBounds(50, 80, 385, 359);
		panelValidar.add(listaRecetasPendientesValidar_VR);

		// Muestra las recetas pendientes de validar
		recetasParaValidar = o.obtenerNoValidadas();
		for (RecetaVO receta : recetasParaValidar) {
			listaRecetasPendientesValidar_VR.add(receta.getNombre());
		}

		// Boton ver la receta pendiente de validar
		JButton botonVer_VR = new JButton("Ver Receta");
		botonVer_VR.setFont(f2);
		botonVer_VR.setBackground(new Color(245, 245, 245));
		botonVer_VR.setBounds(486, 153, 281, 60);
		panelValidar.add(botonVer_VR);

		// Accion del boton ver receta pendiente
		botonVer_VR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaRecetasPendientesValidar_VR.getSelectedIndex() >= 0) {
					RecetaAbierta ra = new RecetaAbierta(
							recetasParaValidar.get(listaRecetasPendientesValidar_VR.getSelectedIndex()));
					ra.setIconImage(new ImageIcon("images/IconoAppRecortado.png").getImage());
					ra.setVisible(true);
				}
			}
		});

		// Boton validar receta seleccionada
		JButton botonValidar_VR = new JButton("Validar Receta");
		botonValidar_VR.setFont(f2);
		botonValidar_VR.setBackground(new Color(173, 255, 47));
		botonValidar_VR.setBounds(486, 306, 281, 60);
		panelValidar.add(botonValidar_VR);

		// Accion del boton de validar la receta
		botonValidar_VR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaRecetasPendientesValidar_VR.getItemCount() > 0) {
					int index = listaRecetasPendientesValidar_VR.getSelectedIndex();
					String id = recetasParaValidar.get(index).getId();
					o.validarReceta(id);
					listaRecetasPendientesValidar_VR.remove(index);
					recetasParaValidar.remove(index);
					JOptionPane.showMessageDialog(menuPrincipal, "La receta ha sido añadida.");
				}
			}
		});

		// Boton rechazar/eliminar receta seleccionada
		JButton botonRechazar_VR = new JButton("Rechazar Receta");
		botonRechazar_VR.setFont(f2);
		botonRechazar_VR.setBackground(new Color(255, 127, 80));
		botonRechazar_VR.setBounds(486, 379, 281, 60);
		panelValidar.add(botonRechazar_VR);

		// Accion del boton de actualizar para validar
		botonRechazar_VR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaRecetasPendientesValidar_VR.getItemCount() > 0) {
					int index = listaRecetasPendientesValidar_VR.getSelectedIndex();
					String id = recetasParaValidar.get(index).getId();
					o.eliminarReceta(id);
					listaRecetasPendientesValidar_VR.remove(index);
					recetasParaValidar.remove(index);
					JOptionPane.showMessageDialog(menuPrincipal, "La receta ha sido rechazada.");
				}
			}
		});

		// Boton actualizar recetas pendientes de validar
		JButton botonActualizar_VR = new JButton("Actualizar");
		botonActualizar_VR.setFont(f2);
		botonActualizar_VR.setBackground(new Color(245, 245, 245));
		botonActualizar_VR.setBounds(486, 80, 281, 60);
		panelValidar.add(botonActualizar_VR);
		botonActualizar_VR.setIcon(new ImageIcon(imagenRefresh.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

		// Accion del boton de actualizar para validar
		botonActualizar_VR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				// Muestra las recetas pendientes de validar
				recetasParaValidar = o.obtenerNoValidadas();
				listaRecetasPendientesValidar_VR.removeAll();
				for (RecetaVO receta : recetasParaValidar) {
					listaRecetasPendientesValidar_VR.add(receta.getNombre());
				}
			}
		});

		// Panel añadir dentro del card layout
		JPanel panelAnyadir = new JPanel();
		panelAdminCard.add(panelAnyadir, "panelAnyadir");
		panelAnyadir.setOpaque(false);
		panelAnyadir.setLayout(null);

		// ComboBox del numero de personas
		final JComboBox comboBoxNumPersonas_A = new JComboBox();
		comboBoxNumPersonas_A.setBounds(171, 48, 272, 22);
		comboBoxNumPersonas_A
				.setModel(new DefaultComboBoxModel(new String[] { "-- Seleccione el n\u00FAmero de personas --", "1",
						"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboBoxNumPersonas_A.setFont(f1);
		panelAnyadir.add(comboBoxNumPersonas_A);
		comboBoxNumPersonas_A.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_A = new JComboBox();
		comboBoxIngrediente_A.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_A.setFont(f1);
		comboBoxIngrediente_A.setBounds(593, 124, 249, 22);
		panelAnyadir.add(comboBoxIngrediente_A);
		comboBoxIngrediente_A.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco1_A = new JLabel("*");
		labelAsterisco1_A.setForeground(Color.RED);
		labelAsterisco1_A.setFont(f);
		labelAsterisco1_A.setBounds(442, 15, 19, 16);
		panelAnyadir.add(labelAsterisco1_A);

		// Text field del nombre de la receta
		textFieldNombre_A = new JTextField();
		textFieldNombre_A.setBounds(171, 13, 272, 22);
		textFieldNombre_A.setFont(f1);
		textFieldNombre_A.setToolTipText("Introduzca el nombre de la receta.");
		panelAnyadir.add(textFieldNombre_A);
		textFieldNombre_A.setColumns(10);

		// ComboBox del ingrediente Principal
		final JComboBox comboBoxIngPrinc_A = new JComboBox();
		comboBoxIngPrinc_A.setBounds(638, 13, 204, 22);
		comboBoxIngPrinc_A.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngPrinc_A.setFont(f1);
		panelAnyadir.add(comboBoxIngPrinc_A);
		comboBoxIngPrinc_A.setLightWeightPopupEnabled(false);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngPrinc_A.addItem(ingredientesAplicacion.get(i));
		}

		// Label del nombre de la receta
		JLabel labelNombre_A = new JLabel("Nombre de la receta:");
		labelNombre_A.setBounds(0, 12, 174, 23);
		labelNombre_A.setForeground(Color.WHITE);
		labelNombre_A.setFont(f);
		panelAnyadir.add(labelNombre_A);

		// Label del ingrediente principal
		JLabel labelIngPrinc_A = new JLabel("Ingrediente Principal:");
		labelIngPrinc_A.setBounds(465, 12, 174, 23);
		labelIngPrinc_A.setForeground(Color.WHITE);
		labelIngPrinc_A.setFont(f);
		panelAnyadir.add(labelIngPrinc_A);

		// Label del numero de personas
		JLabel labelNumPersonas_A = new JLabel("Número de personas:");
		labelNumPersonas_A.setBounds(0, 48, 174, 23);
		labelNumPersonas_A.setForeground(Color.WHITE);
		labelNumPersonas_A.setFont(f);
		panelAnyadir.add(labelNumPersonas_A);

		// Label del tipo de plato
		JLabel labelTipo_A = new JLabel("Tipo de plato:");
		labelTipo_A.setBounds(0, 84, 174, 23);
		labelTipo_A.setForeground(Color.WHITE);
		labelTipo_A.setFont(f);
		panelAnyadir.add(labelTipo_A);

		// Label descripcion de la receta
		JLabel labelDescripcion_A = new JLabel("Descripci\u00F3n de la receta:");
		labelDescripcion_A.setBounds(0, 123, 207, 23);
		labelDescripcion_A.setForeground(Color.WHITE);
		labelDescripcion_A.setFont(f);
		panelAnyadir.add(labelDescripcion_A);

		// Label Otros ingredientes
		JLabel labelIngredientes_A = new JLabel("Otros inredientes:");
		labelIngredientes_A.setBounds(465, 99, 174, 23);
		labelIngredientes_A.setForeground(Color.WHITE);
		labelIngredientes_A.setFont(f);
		panelAnyadir.add(labelIngredientes_A);

		// Separador Vertical
		JSeparator separadorVertical_A = new JSeparator();
		separadorVertical_A.setOrientation(SwingConstants.VERTICAL);
		separadorVertical_A.setBounds(455, 0, 12, 483);
		panelAnyadir.add(separadorVertical_A);

		// Separador horizontal
		JSeparator separadorHorizontal_A = new JSeparator();
		separadorHorizontal_A.setBounds(455, 83, 403, 12);
		panelAnyadir.add(separadorHorizontal_A);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_A = new JLabel("Cantidad:");
		labelCantidadPrinc_A.setForeground(Color.WHITE);
		labelCantidadPrinc_A.setFont(f);
		labelCantidadPrinc_A.setBounds(496, 47, 91, 23);
		panelAnyadir.add(labelCantidadPrinc_A);

		// Text Field cantidad ingrediente principal
		textFieldCantidadPrinc_A = new JTextField();
		textFieldCantidadPrinc_A.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if ((!Character.isDigit(vchar)) || vchar == KeyEvent.VK_DELETE) {
					arg0.consume();
				}
			}
		});
		textFieldCantidadPrinc_A.setFont(f1);
		textFieldCantidadPrinc_A.setBounds(593, 48, 103, 22);
		panelAnyadir.add(textFieldCantidadPrinc_A);
		textFieldCantidadPrinc_A.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final JTextArea textAreaDescripcion_A = new JTextArea();
		textAreaDescripcion_A.setFont(f1);
		textAreaDescripcion_A.setLineWrap(true);
		textAreaDescripcion_A.setWrapStyleWord(true);
		textAreaDescripcion_A.setBounds(0, 152, 440, 236);
		JScrollPane scrollPanel_A = new JScrollPane(textAreaDescripcion_A, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel_A.setFont(f1);
		scrollPanel_A.setBounds(0, 152, 440, 239);
		panelAnyadir.add(scrollPanel_A);

		// Boton de enviar la receta propuesta
		JButton botonEnviarPropuesta_A = new JButton("AÑADIR RECETA");
		botonEnviarPropuesta_A.setFont(f2);
		botonEnviarPropuesta_A.setBackground(new Color(173, 255, 47));
		botonEnviarPropuesta_A.setBounds(0, 394, 281, 60);
		panelAnyadir.add(botonEnviarPropuesta_A);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_A.addItem(ingredientesAplicacion.get(i));
		}

		// Label ingrediente
		JLabel labelIngrediente_A = new JLabel("Ingrediente:");
		labelIngrediente_A.setForeground(Color.WHITE);
		labelIngrediente_A.setFont(f);
		labelIngrediente_A.setBounds(496, 123, 117, 23);
		panelAnyadir.add(labelIngrediente_A);

		// Label cantidad del ingrediente
		JLabel labelCantidad_A = new JLabel("Cantidad:");
		labelCantidad_A.setForeground(Color.WHITE);
		labelCantidad_A.setFont(f);
		labelCantidad_A.setBounds(496, 152, 91, 23);
		panelAnyadir.add(labelCantidad_A);

		// TextField cantidad del ingrediente
		textFieldCantidad_A = new JTextField();
		textFieldCantidad_A.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
				if ((!Character.isDigit(vchar)) || vchar == KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		textFieldCantidad_A.setFont(f1);
		textFieldCantidad_A.setColumns(10);
		textFieldCantidad_A.setBounds(593, 153, 103, 22);
		panelAnyadir.add(textFieldCantidad_A);

		// Boton añadir ingrediente
		JButton botonAnyadirIngrediente_A = new JButton("Añadir Ingrediente");
		botonAnyadirIngrediente_A.setFont(f1);
		botonAnyadirIngrediente_A.setBackground(new Color(245, 245, 245));
		botonAnyadirIngrediente_A.setBounds(465, 421, 180, 33);
		panelAnyadir.add(botonAnyadirIngrediente_A);

		// Boton borrar ingrediente
		JButton botonBorrarIngrediente_A = new JButton("Borrar Ingrediente");
		botonBorrarIngrediente_A.setFont(f1);
		botonBorrarIngrediente_A.setBackground(new Color(245, 245, 245));
		botonBorrarIngrediente_A.setBounds(667, 421, 180, 33);
		panelAnyadir.add(botonBorrarIngrediente_A);

		JComboBox comboBoxUnidades_A = new JComboBox();
		comboBoxUnidades_A.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione  -", "unidades", "gramos",
				"kilos", "litros", "centilitros", "cucharadas", "pizcas", "vaso" }));
		comboBoxUnidades_A.setLightWeightPopupEnabled(false);
		comboBoxUnidades_A.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxUnidades_A.setBounds(708, 153, 134, 22);
		panelAnyadir.add(comboBoxUnidades_A);

		JComboBox comboBoxUnidadesPrinc_A = new JComboBox();
		comboBoxUnidadesPrinc_A.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione  -", "unidades",
				"gramos", "kilos", "litros", "centilitros", "cucharadas", "pizcas", "vaso" }));
		comboBoxUnidadesPrinc_A.setLightWeightPopupEnabled(false);
		comboBoxUnidadesPrinc_A.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxUnidadesPrinc_A.setBounds(708, 48, 134, 22);
		panelAnyadir.add(comboBoxUnidadesPrinc_A);

		// Lista con los ingredientes aÃ±adidos
		final List listaIngredientes_A = new List();
		listaIngredientes_A.setFont(f1);
		listaIngredientes_A.setMultipleMode(false);
		listaIngredientes_A.setBounds(465, 189, 383, 218);
		panelAnyadir.add(listaIngredientes_A);

		JLabel labelAsterisco2_A = new JLabel("*");
		labelAsterisco2_A.setForeground(Color.RED);
		labelAsterisco2_A.setFont(f);
		labelAsterisco2_A.setBounds(442, 48, 19, 16);
		panelAnyadir.add(labelAsterisco2_A);

		JLabel labelAsterisco3_A = new JLabel("*");
		labelAsterisco3_A.setForeground(Color.RED);
		labelAsterisco3_A.setFont(f);
		labelAsterisco3_A.setBounds(442, 141, 19, 16);
		panelAnyadir.add(labelAsterisco3_A);

		JLabel labelAsterisco4_A = new JLabel("*");
		labelAsterisco4_A.setForeground(Color.RED);
		labelAsterisco4_A.setFont(f);
		labelAsterisco4_A.setBounds(849, 15, 19, 16);
		panelAnyadir.add(labelAsterisco4_A);

		JLabel labelAsterisco5_A = new JLabel("*");
		labelAsterisco5_A.setForeground(Color.RED);
		labelAsterisco5_A.setFont(f);
		labelAsterisco5_A.setBounds(849, 50, 19, 16);
		panelAnyadir.add(labelAsterisco5_A);

		JLabel labelCampoObligatorio_A = new JLabel("* Campo obligatorio");
		labelCampoObligatorio_A.setForeground(Color.RED);
		labelCampoObligatorio_A.setFont(f);
		labelCampoObligatorio_A.setBounds(0, 467, 174, 16);
		panelAnyadir.add(labelCampoObligatorio_A);

		// ComboBox del tipo de plato
		final JComboBox comboBoxTipo_A = new JComboBox();
		comboBoxTipo_A.setModel(new DefaultComboBoxModel(
				new String[] { "-- Seleccione el tipo de plato --", "Entrante", "Primero", "Segundo", "Postre" }));
		comboBoxTipo_A.setFont(f1);
		comboBoxTipo_A.setBounds(171, 84, 272, 22);
		panelAnyadir.add(comboBoxTipo_A);
		comboBoxTipo_A.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco6_A = new JLabel("*");
		labelAsterisco6_A.setForeground(Color.RED);
		labelAsterisco6_A.setFont(f);
		labelAsterisco6_A.setBounds(442, 77, 19, 16);
		panelAnyadir.add(labelAsterisco6_A);

		// Accion del boton de borrar ingrediente
		botonBorrarIngrediente_A.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaIngredientes_A.getSelectedItem() != null) {
					indexIngredientes_A.remove(listaIngredientes_A.getSelectedItem().toString().substring(0,
							listaIngredientes_A.getSelectedItem().toString().indexOf('-') - 1));
					listaIngredientes_A.remove(listaIngredientes_A.getSelectedIndex());
				}
			}
		});

		// Accion del boton de añadir ingrediente
		botonAnyadirIngrediente_A.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if ((comboBoxIngrediente_A.getSelectedIndex() != 0) && !textFieldCantidad_A.getText().equals("")
						&& comboBoxUnidades_A.getSelectedIndex() != 0) {
					if (comboBoxIngPrinc_A.getSelectedIndex() == comboBoxIngrediente_A.getSelectedIndex()) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_A.contains(comboBoxIngrediente_A.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_A.add(comboBoxIngrediente_A.getSelectedItem() + " - "
								+ textFieldCantidad_A.getText() + " - " + comboBoxUnidades_A.getSelectedItem());
						indexIngredientes_A.add(comboBoxIngrediente_A.getSelectedItem().toString());
					}
				}
			}
		});

		// Accion del boton de añadir receta
		botonEnviarPropuesta_A.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (!textFieldNombre_A.getText().equals("") && comboBoxNumPersonas_A.getSelectedIndex() != 0
						&& !textAreaDescripcion_A.getText().equals("") && comboBoxIngPrinc_A.getSelectedIndex() != 0
						&& !textFieldCantidadPrinc_A.getText().equals("")
						&& comboBoxUnidadesPrinc_A.getSelectedIndex() != 0 && comboBoxTipo_A.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_A.getText().length() > 4 || textFieldCantidad_A.getText().length() > 4) {
						JOptionPane.showMessageDialog(menuPrincipal, "Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes_A = new ArrayList<String>();
						ArrayList<String> pesoIngredientes_A = new ArrayList<String>();
						ArrayList<String> unidadIngredientes_A = new ArrayList<String>();
						String[] s = listaIngredientes_A.getItems();

						ingredientes_A.add(comboBoxIngPrinc_A.getSelectedItem().toString());
						pesoIngredientes_A.add(textFieldCantidadPrinc_A.getText());
						unidadIngredientes_A.add(comboBoxUnidadesPrinc_A.getSelectedItem().toString());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0, elemento.indexOf('-') - 1);
							String resto = elemento.substring(elemento.indexOf('-') + 1, elemento.length());
							String cantidad = resto.substring(0, resto.indexOf('-') - 1);
							String unidad = resto.substring(resto.indexOf('-') + 1, resto.length());
							ingredientes_A.add(ingrediente);
							pesoIngredientes_A.add(cantidad);
							unidadIngredientes_A.add(unidad);
						}

						o.addRecetaV(textFieldNombre_A.getText(), textAreaDescripcion_A.getText(),
								comboBoxTipo_A.getSelectedItem().toString(),
								comboBoxNumPersonas_A.getSelectedItem().toString(), ingredientes_A, pesoIngredientes_A,
								unidadIngredientes_A);

						JOptionPane.showMessageDialog(menuPrincipal, "La receta ha sido añadida.");
						textFieldNombre_A.setText("");
						comboBoxNumPersonas_A.setSelectedIndex(0);
						textAreaDescripcion_A.setText("");
						comboBoxIngPrinc_A.setSelectedIndex(0);
						textFieldCantidadPrinc_A.setText("");
						comboBoxIngrediente_A.setSelectedIndex(0);
						comboBoxTipo_A.setSelectedIndex(0);
						textFieldCantidad_A.setText("");
						indexIngredientes_A.clear();
						listaIngredientes_A.removeAll();
						ingredientes_A.clear();
						pesoIngredientes_A.clear();
					}

				} else {
					// Si algun campo no esta rellenado correctamente
					JOptionPane.showMessageDialog(menuPrincipal,
							"Uno o mas campos obligatorios no estan rellenados correctamente.");
				}
			}
		});

		// Boton de cancelar receta
		JButton botonCancelarReceta_A = new JButton("Cancelar");
		botonCancelarReceta_A.setFont(f2);
		botonCancelarReceta_A.setBackground(new Color(245, 245, 245));
		botonCancelarReceta_A.setBounds(288, 394, 155, 60);
		panelAnyadir.add(botonCancelarReceta_A);

		// Accion del boton de cancelar receta

		botonCancelarReceta_A.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				textFieldNombre_A.setText("");
				comboBoxNumPersonas_A.setSelectedIndex(0);
				textAreaDescripcion_A.setText(" ");
				comboBoxIngPrinc_A.setSelectedIndex(0);
				textFieldCantidadPrinc_A.setText("");
				comboBoxIngrediente_A.setSelectedIndex(0);
				comboBoxTipo_A.setSelectedIndex(0);
				textFieldCantidad_A.setText("");
				listaIngredientes_A.removeAll();
				comboBoxUnidades_A.setSelectedIndex(0);
				comboBoxUnidadesPrinc_A.setSelectedIndex(0);
			}
		});

		// Panel modificar dentro del card layout
		JPanel panelModificar = new JPanel();
		panelAdminCard.add(panelModificar, "panelModificar");
		panelModificar.setOpaque(false);
		panelModificar.setLayout(null);

		// TextField Busqueda Modificar Receta
		textFieldBusqueda_MR = new JTextField();
		textFieldBusqueda_MR.setFont(f1);
		textFieldBusqueda_MR.setBounds(12, 40, 596, 36);
		panelModificar.add(textFieldBusqueda_MR);
		textFieldBusqueda_MR.setColumns(10);

		// Lista de recetas buscadas
		final List listaBuscada_MR = new List();
		listaBuscada_MR.setFont(new Font("Calibri", Font.BOLD, 20));
		listaBuscada_MR.setBounds(12, 126, 596, 336);
		panelModificar.add(listaBuscada_MR);

		// Boton Buscar Modificar Receta
		JButton botonBuscar_MR = new JButton("Buscar");
		botonBuscar_MR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonBuscar_MR.setBackground(new Color(245, 245, 245));
		botonBuscar_MR.setBounds(626, 40, 104, 36);
		panelModificar.add(botonBuscar_MR);

		// Accion del boton de buscar recetas para modificar
		botonBuscar_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				listaBuscada_MR.removeAll();
				recetasModificar = o.busqueda(textFieldBusqueda_MR.getText(), "", "", "");
				for (RecetaVO receta : recetasModificar) {
					listaBuscada_MR.add(receta.getNombre());
				}
			}
		});

		f = new Font("Calibri", Font.BOLD, 23);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("Dialog.Italic", Font.BOLD, 18);
		}
		// Boton ver receta seleccionada modificar receta
		JButton botonVerReceta_MR = new JButton("Ver Receta");
		botonVerReceta_MR.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (listaBuscada_MR.getSelectedIndex() >= 0) {
					RecetaAbierta ra = new RecetaAbierta(recetasModificar.get(listaBuscada_MR.getSelectedIndex()));
					ra.setIconImage(new ImageIcon("images/IconoAppRecortado.png").getImage());
					ra.setVisible(true);
				}
			}
		});
		botonVerReceta_MR.setFont(f);
		botonVerReceta_MR.setBackground(new Color(245, 245, 245));
		botonVerReceta_MR.setBounds(626, 126, 220, 60);
		panelModificar.add(botonVerReceta_MR);

		// Boton Modificar Receta seleccionada
		JButton botonModificar_MR = new JButton("Modificar Receta");
		botonModificar_MR.setFont(f);
		botonModificar_MR.setBackground(new Color(173, 255, 47));
		botonModificar_MR.setBounds(625, 266, 221, 60);
		panelModificar.add(botonModificar_MR);

		// Boton borrar receta seleccionada
		JButton botonBorrar_MR = new JButton("Borrar Receta");
		botonBorrar_MR.setFont(f);
		botonBorrar_MR.setBackground(new Color(255, 127, 80));
		botonBorrar_MR.setBounds(625, 402, 221, 60);
		panelModificar.add(botonBorrar_MR);

		// Accion del boton de borrar la receta seleccionada
		botonBorrar_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaBuscada_MR.getItemCount() > 0 && listaBuscada_MR.getSelectedItem() != null) {
					int index = listaBuscada_MR.getSelectedIndex();
					String id = recetasModificar.get(index).getId();
					o.eliminarReceta(id);
					listaBuscada_MR.remove(index);
					recetasModificar.remove(index);
					JOptionPane.showMessageDialog(menuPrincipal, "Receta eliminada correctamente.");
				}
			}
		});

		// Panel modificar receta dentro del card Layout
		JPanel panelModificarReceta = new JPanel();
		panelAdminCard.add(panelModificarReceta, "panelModificarReceta");
		panelModificarReceta.setOpaque(false);
		panelModificarReceta.setLayout(null);

		// ComboBox del numero de personas
		final JComboBox comboBoxNumPersonas_MR = new JComboBox();
		comboBoxNumPersonas_MR.setBounds(171, 48, 272, 22);
		comboBoxNumPersonas_MR
				.setModel(new DefaultComboBoxModel(new String[] { "-- Seleccione el número de personas --", "1", "2",
						"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboBoxNumPersonas_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		panelModificarReceta.add(comboBoxNumPersonas_MR);
		comboBoxNumPersonas_MR.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_MR = new JComboBox();
		comboBoxIngrediente_MR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxIngrediente_MR.setBounds(592, 124, 243, 22);
		panelModificarReceta.add(comboBoxIngrediente_MR);
		comboBoxIngrediente_MR.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco1_MR = new JLabel("*");
		labelAsterisco1_MR.setForeground(Color.RED);
		labelAsterisco1_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco1_MR.setBounds(442, 15, 19, 16);
		panelModificarReceta.add(labelAsterisco1_MR);

		// Text field del nombre de la receta
		textFieldNombre_MR = new JTextField();
		textFieldNombre_MR.setBounds(171, 13, 272, 22);
		textFieldNombre_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldNombre_MR.setToolTipText("Introduzca el nombre de la receta.");
		panelModificarReceta.add(textFieldNombre_MR);
		textFieldNombre_MR.setColumns(10);

		// ComboBox del ingrediente Principal
		final JComboBox comboBoxIngPrinc_MR = new JComboBox();
		comboBoxIngPrinc_MR.setBounds(631, 13, 204, 22);
		comboBoxIngPrinc_MR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngPrinc_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		panelModificarReceta.add(comboBoxIngPrinc_MR);
		comboBoxIngPrinc_MR.setLightWeightPopupEnabled(false);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngPrinc_MR.addItem(ingredientesAplicacion.get(i));
		}

		f = new Font("Calibri", Font.BOLD, 18);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("Dialog.Italic", Font.BOLD, 14);
		}

		f1 = new Font("Calibri", Font.BOLD, 15);
		if (!f1.getFamily().equals("Calibri")) {
			f1 = new Font("Dialog.Italic", Font.BOLD, 12);
		}

		f2 = new Font("Calibri", Font.BOLD, 23);
		if (!f2.getFamily().equals("Calibri")) {
			f2 = new Font("Dialog.Italic", Font.BOLD, 18);
		}

		// Label del nombre de la receta
		JLabel labelNombre_MR = new JLabel("Nombre de la receta:");
		labelNombre_MR.setBounds(0, 12, 174, 23);
		labelNombre_MR.setForeground(Color.WHITE);
		labelNombre_MR.setFont(f);
		panelModificarReceta.add(labelNombre_MR);

		// Label del ingrediente principal
		JLabel labelIngPrinc_MR = new JLabel("Ingrediente Principal:");
		labelIngPrinc_MR.setBounds(465, 12, 174, 23);
		labelIngPrinc_MR.setForeground(Color.WHITE);
		labelIngPrinc_MR.setFont(f);
		panelModificarReceta.add(labelIngPrinc_MR);

		// Label del numero de personas
		JLabel labelNumPersonas_MR = new JLabel("N\u00FAmero de personas:");
		labelNumPersonas_MR.setBounds(0, 48, 174, 23);
		labelNumPersonas_MR.setForeground(Color.WHITE);
		labelNumPersonas_MR.setFont(f);
		panelModificarReceta.add(labelNumPersonas_MR);

		// Label del tipo de plato
		JLabel labelTipo_MR = new JLabel("Tipo de plato:");
		labelTipo_MR.setBounds(0, 84, 174, 23);
		labelTipo_MR.setForeground(Color.WHITE);
		labelTipo_MR.setFont(f);
		panelModificarReceta.add(labelTipo_MR);

		// Label descripcion de la receta
		JLabel labelDescripcion_MR = new JLabel("Descripci\u00F3n de la receta:");
		labelDescripcion_MR.setBounds(0, 123, 207, 23);
		labelDescripcion_MR.setForeground(Color.WHITE);
		labelDescripcion_MR.setFont(f);
		panelModificarReceta.add(labelDescripcion_MR);

		// Label Otros ingredientes
		JLabel labelIngredientes_MR = new JLabel("Otros inredientes:");
		labelIngredientes_MR.setBounds(465, 99, 174, 23);
		labelIngredientes_MR.setForeground(Color.WHITE);
		labelIngredientes_MR.setFont(f);
		panelModificarReceta.add(labelIngredientes_MR);

		// Separador Vertical
		JSeparator separadorVertical_MR = new JSeparator();
		separadorVertical_MR.setOrientation(SwingConstants.VERTICAL);
		separadorVertical_MR.setBounds(455, 0, 12, 483);
		panelModificarReceta.add(separadorVertical_MR);

		// Separador horizontal
		JSeparator separadorHorizontal_MR = new JSeparator();
		separadorHorizontal_MR.setBounds(455, 83, 403, 12);
		panelModificarReceta.add(separadorHorizontal_MR);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_MR = new JLabel("Cantidad:");
		labelCantidadPrinc_MR.setForeground(Color.WHITE);
		labelCantidadPrinc_MR.setFont(f);
		labelCantidadPrinc_MR.setBounds(496, 47, 91, 23);
		panelModificarReceta.add(labelCantidadPrinc_MR);

		// Text Field cantidad ingrediente principal
		textFieldCantidadPrinc_MR = new JTextField();
		textFieldCantidadPrinc_MR.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if ((!Character.isDigit(vchar)) || vchar == KeyEvent.VK_DELETE) {
					arg0.consume();
				}
			}
		});
		textFieldCantidadPrinc_MR.setFont(f);
		textFieldCantidadPrinc_MR.setBounds(592, 47, 100, 22);
		panelModificarReceta.add(textFieldCantidadPrinc_MR);
		textFieldCantidadPrinc_MR.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final JTextArea textAreaDescripcion_MR = new JTextArea();
		textAreaDescripcion_MR.setFont(f);
		textAreaDescripcion_MR.setLineWrap(true);
		textAreaDescripcion_MR.setWrapStyleWord(true);
		JScrollPane scrollPanel_MR = new JScrollPane(textAreaDescripcion_MR, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel_MR.setFont(f1);
		scrollPanel_MR.setBounds(0, 152, 440, 229);
		panelModificarReceta.add(scrollPanel_MR);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_MR.addItem(ingredientesAplicacion.get(i));
		}

		// Label ingrediente
		JLabel labelIngrediente_MR = new JLabel("Ingrediente:");
		labelIngrediente_MR.setForeground(Color.WHITE);
		labelIngrediente_MR.setFont(f);
		labelIngrediente_MR.setBounds(496, 123, 117, 23);
		panelModificarReceta.add(labelIngrediente_MR);

		// Label cantidad del ingrediente
		JLabel labelCantidad_MR = new JLabel("Cantidad:");
		labelCantidad_MR.setForeground(Color.WHITE);
		labelCantidad_MR.setFont(f);
		labelCantidad_MR.setBounds(496, 155, 91, 23);
		panelModificarReceta.add(labelCantidad_MR);

		// TextField cantidad del ingrediente
		textFieldCantidad_MR = new JTextField();
		textFieldCantidad_MR.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
				if ((!Character.isDigit(vchar)) || vchar == KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		textFieldCantidad_MR.setFont(f);
		textFieldCantidad_MR.setColumns(10);
		textFieldCantidad_MR.setBounds(592, 159, 100, 22);
		panelModificarReceta.add(textFieldCantidad_MR);

		JComboBox comboBoxUnidadesPrinc_MR = new JComboBox();
		comboBoxUnidadesPrinc_MR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione  -", "unidades",
				"gramos", "kilos", "litros", "centilitros", "cucharadas", "pizcas", "vaso" }));
		comboBoxUnidadesPrinc_MR.setLightWeightPopupEnabled(false);
		comboBoxUnidadesPrinc_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxUnidadesPrinc_MR.setBounds(701, 47, 134, 22);
		panelModificarReceta.add(comboBoxUnidadesPrinc_MR);

		JComboBox comboBoxUnidades_MR = new JComboBox();
		comboBoxUnidades_MR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione  -", "unidades", "gramos",
				"kilos", "litros", "centilitros", "cucharadas", "pizcas", "vaso" }));
		comboBoxUnidades_MR.setLightWeightPopupEnabled(false);
		comboBoxUnidades_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxUnidades_MR.setBounds(701, 159, 134, 22);
		panelModificarReceta.add(comboBoxUnidades_MR);

		// Boton añadir ingrediente
		JButton botonAnyadirIngrediente_MR = new JButton("Añadir Ingrediente");
		botonAnyadirIngrediente_MR.setFont(f1);
		botonAnyadirIngrediente_MR.setBackground(new Color(245, 245, 245));
		botonAnyadirIngrediente_MR.setBounds(465, 422, 180, 33);
		panelModificarReceta.add(botonAnyadirIngrediente_MR);

		// Boton borrar ingrediente
		JButton botonBorrarIngrediente_MR = new JButton("Borrar Ingrediente");
		botonBorrarIngrediente_MR.setFont(f1);
		botonBorrarIngrediente_MR.setBackground(new Color(245, 245, 245));
		botonBorrarIngrediente_MR.setBounds(667, 422, 180, 33);
		panelModificarReceta.add(botonBorrarIngrediente_MR);

		// Lista con los ingredientes añadidos
		final List listaIngredientes_MR = new List();
		listaIngredientes_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		listaIngredientes_MR.setMultipleMode(false);
		listaIngredientes_MR.setBounds(465, 192, 383, 224);
		panelModificarReceta.add(listaIngredientes_MR);

		JLabel labelAsterisco2_MR = new JLabel("*");
		labelAsterisco2_MR.setForeground(Color.RED);
		labelAsterisco2_MR.setFont(f);
		labelAsterisco2_MR.setBounds(442, 48, 19, 16);
		panelModificarReceta.add(labelAsterisco2_MR);

		JLabel labelAsterisco3_MR = new JLabel("*");
		labelAsterisco3_MR.setForeground(Color.RED);
		labelAsterisco3_MR.setFont(f);
		labelAsterisco3_MR.setBounds(442, 141, 19, 16);
		panelModificarReceta.add(labelAsterisco3_MR);

		JLabel labelAsterisco4_MR = new JLabel("*");
		labelAsterisco4_MR.setForeground(Color.RED);
		labelAsterisco4_MR.setFont(f);
		labelAsterisco4_MR.setBounds(839, 15, 19, 16);
		panelModificarReceta.add(labelAsterisco4_MR);

		JLabel labelAsterisco5_MR = new JLabel("*");
		labelAsterisco5_MR.setForeground(Color.RED);
		labelAsterisco5_MR.setFont(f);
		labelAsterisco5_MR.setBounds(839, 50, 19, 16);
		panelModificarReceta.add(labelAsterisco5_MR);

		JLabel labelCampoObligatorio_MR = new JLabel("* Campo obligatorio");
		labelCampoObligatorio_MR.setForeground(Color.RED);
		labelCampoObligatorio_MR.setFont(f);
		labelCampoObligatorio_MR.setBounds(0, 467, 174, 16);
		panelModificarReceta.add(labelCampoObligatorio_MR);

		// ComboBox del tipo de plato
		final JComboBox comboBoxTipo_MR = new JComboBox();
		comboBoxTipo_MR.setModel(new DefaultComboBoxModel(
				new String[] { "-- Seleccione el tipo de plato --", "Entrante", "Primero", "Segundo", "Postre" }));
		comboBoxTipo_MR.setFont(f1);
		comboBoxTipo_MR.setBounds(171, 84, 272, 22);
		panelModificarReceta.add(comboBoxTipo_MR);
		comboBoxTipo_MR.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco6_MR = new JLabel("*");
		labelAsterisco6_MR.setForeground(Color.RED);
		labelAsterisco6_MR.setFont(f);
		labelAsterisco6_MR.setBounds(442, 77, 19, 16);
		panelModificarReceta.add(labelAsterisco6_MR);

		// Accion del boton de borrar ingrediente
		botonBorrarIngrediente_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaIngredientes_MR.getSelectedItem() != null) {
					indexIngredientes_MR.remove(listaIngredientes_MR.getSelectedItem().toString().substring(0,
							listaIngredientes_MR.getSelectedItem().toString().indexOf('-') - 1));
					listaIngredientes_MR.remove(listaIngredientes_MR.getSelectedIndex());
				}
			}
		});

		// Accion del boton de añadir ingrediente
		botonAnyadirIngrediente_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if ((comboBoxIngrediente_MR.getSelectedIndex() != 0 && !textFieldCantidad_PR.equals("")
						&& comboBoxUnidades_MR.getSelectedIndex() != 0)) {
					if (comboBoxIngPrinc_MR.getSelectedIndex() == comboBoxIngrediente_MR.getSelectedIndex()) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_MR.contains(comboBoxIngrediente_MR.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_MR.add(comboBoxIngrediente_MR.getSelectedItem() + " - "
								+ textFieldCantidad_MR.getText() + " - " + comboBoxUnidades_MR.getSelectedItem());
						indexIngredientes_MR.add(comboBoxIngrediente_MR.getSelectedItem().toString());
					}
				}
			}
		});

		// Boton guardar cambios realizados
		JButton botonGuardarCambios_MR = new JButton("Guardar Cambios");
		botonGuardarCambios_MR.setFont(f2);
		botonGuardarCambios_MR.setBackground(new Color(173, 255, 47));
		botonGuardarCambios_MR.setBounds(1, 395, 215, 60);
		panelModificarReceta.add(botonGuardarCambios_MR);

		// Accion del boton de guardar cambios
		botonGuardarCambios_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (!textFieldNombre_MR.getText().equals("") && comboBoxNumPersonas_MR.getSelectedIndex() != 0
						&& !textAreaDescripcion_MR.getText().equals("") && comboBoxIngPrinc_MR.getSelectedIndex() != 0
						&& !textFieldCantidadPrinc_MR.getText().equals("")
						&& comboBoxUnidadesPrinc_MR.getSelectedIndex() != 0
						&& comboBoxTipo_MR.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_MR.getText().length() > 4
							|| textFieldCantidad_MR.getText().length() > 4) {
						JOptionPane.showMessageDialog(menuPrincipal, "Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes = new ArrayList<String>();
						ArrayList<String> pesoIngredientes = new ArrayList<String>();
						ArrayList<String> unidadIngredientes = new ArrayList<String>();
						String[] s = listaIngredientes_MR.getItems();

						ingredientes.add(comboBoxIngPrinc_MR.getSelectedItem().toString());
						pesoIngredientes.add(textFieldCantidadPrinc_MR.getText());
						unidadIngredientes.add(comboBoxUnidadesPrinc_MR.getSelectedItem().toString());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0, elemento.indexOf('-') - 1);
							String resto = elemento.substring(elemento.indexOf('-') + 1, elemento.length());
							String cantidad = resto.substring(resto.indexOf('-') + 1, resto.indexOf('-') - 1);
							String unidad = resto.substring(resto.indexOf('-') + 1, resto.length());
							ingredientes.add(ingrediente);
							pesoIngredientes.add(cantidad);
							unidadIngredientes.add(unidad);
						}

						o.modificarReceta(textFieldNombre_MR.getText(), textAreaDescripcion_MR.getText(),
								comboBoxTipo_MR.getSelectedItem().toString(),
								comboBoxNumPersonas_MR.getSelectedItem().toString(), ingredientes, pesoIngredientes,
								unidadIngredientes, recetaSeleccionada_MR.getId());
						indexIngredientes_MR.clear();

						// Recargamos las recetas buscadas para modificar
						listaBuscada_MR.removeAll();
						for (RecetaVO receta : recetasModificar) {
							listaBuscada_MR.add(receta.getNombre());
						}

						JOptionPane.showMessageDialog(menuPrincipal, "Los cambios en la receta han sido guardados.");
						recetaSeleccionada_MR = null;

						// Ponemos en blanco todos los campos
						textFieldNombre_MR.setText("");
						comboBoxNumPersonas_MR.setSelectedIndex(0);
						textAreaDescripcion_MR.setText("");
						comboBoxIngPrinc_MR.setSelectedIndex(0);
						textFieldCantidadPrinc_MR.setText("");
						comboBoxIngrediente_MR.setSelectedIndex(0);
						comboBoxTipo_MR.setSelectedIndex(0);
						textFieldCantidad_MR.setText("");
						listaIngredientes_MR.removeAll();
						listaBuscada_MR.removeAll();
						recetasModificar = o.busqueda(textFieldBusqueda_MR.getText(), "", "", "");
						for (RecetaVO receta : recetasModificar) {
							listaBuscada_MR.add(receta.getNombre());
						}
						cardAdmin.show(panelAdminCard, "panelModificar");
					}

				} else {
					// Si algun campo no esta rellenado correctamente
					JOptionPane.showMessageDialog(menuPrincipal,
							"Uno o mas campos obligatorios no estan rellenados correctamente.");
				}
			}
		});

		// Boton Volver Modificar Receta
		JButton botonVolver_MR = new JButton("Volver");
		botonVolver_MR.setFont(f2);
		botonVolver_MR.setBackground(new Color(245, 245, 245));
		botonVolver_MR.setBounds(228, 395, 215, 60);
		panelModificarReceta.add(botonVolver_MR);
		botonVolver_MR.setIcon(new ImageIcon(imagenReturn.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));

		// Accion del boton de VOLVER DE MODIFICAR RECETA
		botonVolver_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				textFieldNombre_MR.setText("");
				comboBoxNumPersonas_MR.setSelectedIndex(0);
				textAreaDescripcion_MR.setText("");
				comboBoxIngPrinc_MR.setSelectedIndex(0);
				textFieldCantidadPrinc_MR.setText("");
				comboBoxIngrediente_MR.setSelectedIndex(0);
				comboBoxTipo_MR.setSelectedIndex(0);
				textFieldCantidad_MR.setText("");
				listaIngredientes_MR.removeAll();
				comboBoxUnidades_MR.setSelectedIndex(0);
				comboBoxUnidadesPrinc_MR.setSelectedIndex(0);
				cardAdmin.show(panelAdminCard, "panelModificar");
				recetaSeleccionada_MR = null;
			}
		});

		// Accion del boton de modificar la receta seleccionada
		botonModificar_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaBuscada_MR.getItemCount() > 0 && listaBuscada_MR.getSelectedItem() != null) {
					recetaSeleccionada_MR = recetasModificar.get(listaBuscada_MR.getSelectedIndex());
					cardAdmin.show(panelAdminCard, "panelModificarReceta");
					// Puesta de valores iniciales en los campos
					textFieldNombre_MR.setText(recetaSeleccionada_MR.getNombre());
					comboBoxNumPersonas_MR.setSelectedItem(recetaSeleccionada_MR.getNumPersonas());
					textAreaDescripcion_MR.setText(recetaSeleccionada_MR.getDescripcion());
					comboBoxIngPrinc_MR.setSelectedItem(recetaSeleccionada_MR.getIngredientes().get(0));
					textFieldCantidadPrinc_MR.setText(recetaSeleccionada_MR.getPesoIngredientes().get(0));
					for (int i = 1; i < recetaSeleccionada_MR.getIngredientes().size(); i++) {
						if (recetaSeleccionada_MR.getPesoIngredientes().get(i).equals("0")) {
							listaIngredientes_MR.add(recetaSeleccionada_MR.getIngredientes().get(i) + " - " + "_");
						} else {
							listaIngredientes_MR.add(recetaSeleccionada_MR.getIngredientes().get(i) + " - "
									+ recetaSeleccionada_MR.getPesoIngredientes().get(i));
						}
					}
					comboBoxTipo_MR.setSelectedItem(recetaSeleccionada_MR.getPlato());
				}
			}
		});

		// Boton validar recetas
		botonValidar.setHorizontalAlignment(SwingConstants.LEFT);
		botonValidar.setFont(new Font("Calibri", Font.BOLD, 20));
		botonValidar.setBackground(new Color(245, 245, 245));
		botonValidar.setBounds(76, 0, 235, 38);
		botonValidar.setIcon(new ImageIcon(imagenTick.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		panelAdministrador.add(botonValidar);

		// Accion del boton de Validar receta
		botonValidar.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("VALIDAR RECETA");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonValidar.setBackground(new Color(255, 153, 0));
				cardAdmin.show(panelAdminCard, "panelValidar");
			}
		});

		// Boton añadir recetas
		botonAnyadir.setHorizontalAlignment(SwingConstants.LEFT);
		botonAnyadir.setFont(new Font("Calibri", Font.BOLD, 20));
		botonAnyadir.setBackground(new Color(245, 245, 245));
		botonAnyadir.setBounds(310, 0, 235, 38);
		botonAnyadir.setIcon(new ImageIcon(imagenWrite.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		panelAdministrador.add(botonAnyadir);

		// Accion del boton de Validar receta
		botonAnyadir.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("AÑADIR RECETA");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonAnyadir.setBackground(new Color(255, 153, 0));
				cardAdmin.show(panelAdminCard, "panelAnyadir");
			}
		});

		// Boton modoficar recetas
		botonModificar.setHorizontalAlignment(SwingConstants.LEFT);
		botonModificar.setFont(new Font("Calibri", Font.BOLD, 20));
		botonModificar.setBackground(new Color(245, 245, 245));
		botonModificar.setBounds(544, 0, 235, 38);
		botonModificar.setIcon(new ImageIcon(imagenModificar.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		panelAdministrador.add(botonModificar);

		// Accion del boton de Modificar receta
		botonModificar.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("MODIFICAR RECETA");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonModificar.setBackground(new Color(255, 153, 0));
				cardAdmin.show(panelAdminCard, "panelModificar");
			}
		});

		// Boton Ayuda
		botonAyuda.setBackground(new Color(245, 245, 245));
		botonAyuda.setFont(f);
		botonAyuda.setBounds(12, 553, 235, 60);
		botonAyuda.setHorizontalAlignment(SwingConstants.LEFT);
		botonAyuda.setIcon(new ImageIcon(imagenHelp.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		menuPrincipal.add(botonAyuda);

		// Accion del boton de Ayuda
		botonAyuda.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("AYUDA");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonAyuda.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelAyuda");
			}
		});

		// Boton Administrador
		botonAdministrador.setHorizontalAlignment(SwingConstants.LEFT);
		botonAdministrador.setFont(f);
		botonAdministrador.setBackground(new Color(245, 245, 245));
		botonAdministrador.setBounds(12, 613, 235, 60);
		botonAdministrador.setIcon(new ImageIcon(imagenAdmin.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		menuPrincipal.add(botonAdministrador);

		// Accion del boton de administrador
		botonAdministrador.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (!identificado) {
					cardLayout.show(contentPane, "identiAdminentificacion");
				} else {
					labelMenu.setText("ADMINISTRADOR");
					Font f = new Font("Bauhaus 93", Font.BOLD, 70);
					if (!f.getFamily().equals("Bauhaus 93")) {
						f = new Font("Dialog.Italic", Font.BOLD, 65);
					}
					labelMenu.setFont(f);
					botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
							botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
					botonAdministrador.setBackground(new Color(255, 153, 0));
					cardMenu.show(pantallaMenu, "panelAdministrador");
					cardAdmin.show(panelAdminCard, "panelAdmin");
					int noValidadas = o.obtenerNoValidadas().size();
					labelInfoAdmin.setText("Hay " + noValidadas + " recetas pendientes de ser validadas.");
				}
			}
		});

		// Accion del boton Imagen
		botonImagen.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("MENÚ PRINCIPAL");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				cardMenu.show(pantallaMenu, "panelPrincipal");
			}
		});

		// Fondo de la pantalla
		JLabel fondoApp3 = new JLabel();
		fondoApp3.setBounds(0, 0, 1142, 686);
		fondoApp3.setIcon(new ImageIcon(imagenFondoApp.getScaledInstance(1142, 686, Image.SCALE_DEFAULT)));
		menuPrincipal.add(fondoApp3);

		/**
		 * FINAL DE LA PANTALLA DEL MENU PRINCIPAL
		 **/
		/**
		 * COMIENZO DE LA PANTALLA DE IDENTIFICACION DE ADMINISTRADOR
		 **/
		final JPanel identiAdmin = new JPanel();
		contentPane.add(identiAdmin, "identiAdminentificacion");
		identiAdmin.setLayout(null);

		// Icono de la Aplicacion
		JLabel IconoApp2 = new JLabel("");
		IconoApp2.setIcon(new ImageIcon(imagenIcono.getScaledInstance(319, 288, Image.SCALE_DEFAULT)));
		IconoApp2.setBounds(372, 13, 319, 288);
		identiAdmin.add(IconoApp2);

		// M
		JLabel labelTitulo2 = new JLabel("M");
		labelTitulo2.setForeground(new Color(255, 153, 0));
		f = new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38);
		if (!f.getFamily().equals("Bauhaus 93")) {
			f = new Font("Dialog.italic", Font.BOLD, 35);
		}
		labelTitulo2.setFont(f);
		labelTitulo2.setBounds(312, 371, 37, 86);
		identiAdmin.add(labelTitulo2);

		// y
		JLabel lblY2 = new JLabel("y");
		lblY2.setFont(f);
		lblY2.setBounds(344, 371, 37, 86);
		identiAdmin.add(lblY2);

		// E
		JLabel lblE2 = new JLabel("E");
		lblE2.setForeground(new Color(255, 153, 0));
		lblE2.setFont(f);
		lblE2.setBounds(385, 371, 37, 86);
		identiAdmin.add(lblE2);

		// lection
		JLabel lblMy2 = new JLabel("lection");
		lblMy2.setFont(f);
		lblMy2.setBounds(406, 371, 138, 86);
		identiAdmin.add(lblMy2);

		// F
		JLabel lblF2 = new JLabel("F");
		lblF2.setForeground(new Color(255, 153, 0));
		lblF2.setFont(f);
		lblF2.setBounds(563, 371, 37, 86);
		identiAdmin.add(lblF2);

		// or
		JLabel lblOr2 = new JLabel("or");
		lblOr2.setFont(f);
		lblOr2.setBounds(582, 371, 55, 86);
		identiAdmin.add(lblOr2);

		// L
		JLabel lblL2 = new JLabel("L");
		lblL2.setForeground(new Color(255, 153, 0));
		lblL2.setFont(f);
		lblL2.setBounds(642, 371, 23, 86);
		identiAdmin.add(lblL2);

		// unch
		JLabel lblUnch2 = new JLabel("unch");
		lblUnch2.setFont(f);
		lblUnch2.setBounds(661, 371, 100, 86);
		identiAdmin.add(lblUnch2);

		// Nombre corto
		JLabel lblMel2 = new JLabel("ME4L");

		f = new Font("Bauhaus 93", Font.BOLD, 84);
		if (!f.getFamily().equals("Bauhaus 93")) {
			f = new Font("Dialog.italic", Font.BOLD, 60);
		}
		lblMel2.setFont(f);
		lblMel2.setBounds(438, 266, 211, 162);
		identiAdmin.add(lblMel2);

		// Boton Entrar como Administrador
		JButton botonAdmin2 = new JButton("Entrar como Administrador");
		botonAdmin2.setBackground(new Color(250, 250, 250));
		botonAdmin2.setFont(new Font("Calibri", Font.BOLD, 24));
		botonAdmin2.setBounds(374, 590, 319, 44);
		identiAdmin.add(botonAdmin2);

		// Accion del boton de Entrar como Administrador
		botonAdmin2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				// Execute when button is pressed
				char[] input = passwordAdmin.getPassword();
				if (isPasswordCorrect(input)) {
					cardLayout.show(contentPane, "menuPrincipal");
					labelMenu.setText("ADMINISTRADOR");
					Font f = new Font("Bauhaus 93", Font.BOLD, 70);
					if (!f.getFamily().equals("Bauhaus 93")) {
						f = new Font("Dialog.Italic", Font.BOLD, 65);
					}
					labelMenu.setFont(f);
					botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
							botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
					identificado = true;
					botonAdministrador.setBackground(new Color(255, 153, 0));
					cardMenu.show(pantallaMenu, "panelAdministrador");
					cardMenu.show(pantallaMenu, "panelAdministrador");
					cardAdmin.show(panelAdminCard, "panelAdmin");
					int noValidadas = o.obtenerNoValidadas().size();
					labelInfoAdmin.setText("Hay " + noValidadas + " recetas pendientes de ser validadas.");
				} else {
					// Si la contraseÃ±a no es correcta informamos al usuario
					JOptionPane.showMessageDialog(identiAdmin, "Contraseña INCORRECTA, inténtelo de nuevo.");
					passwordAdmin.setText("");
				}
			}
		});

		// Texto contraseña
		JLabel lblIntroduzcaLaContrasea = new JLabel("Introduzca la contraseña de Administrador:");
		lblIntroduzcaLaContrasea.setForeground(Color.BLACK);
		f = new Font("Calibri", Font.BOLD, 18);
		if (!f.getFamily().equals("Calibri")) {
			f = new Font("Dialog.italic", Font.BOLD, 14);
		}
		lblIntroduzcaLaContrasea.setFont(f);
		lblIntroduzcaLaContrasea.setBounds(372, 493, 365, 23);
		identiAdmin.add(lblIntroduzcaLaContrasea);

		// Campo Password
		passwordAdmin = new JPasswordField(10);
		passwordAdmin.setActionCommand("Entrar como Administrador");
		passwordAdmin.setBounds(374, 529, 319, 37);
		identiAdmin.add(passwordAdmin);

		// Boton de Volver
		JButton botonVolver = new JButton("VOLVER");
		botonVolver.setBackground(new Color(250, 250, 250));
		botonVolver.setFont(new Font("Calibri", Font.BOLD, 18));
		botonVolver.setBounds(104, 577, 144, 51);
		identiAdmin.add(botonVolver);
		botonVolver.setIcon(new ImageIcon(imagenReturn.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));

		// Accion del boton de Volver
		botonVolver.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				cardLayout.show(contentPane, "menuPrincipal");
				cardMenu.show(pantallaMenu, "panelPrincipal");
				labelMenu.setText("MENÚ PRINCIPAL");
				botonesEnBlanco(botonEntrantes, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
			}
		});

		// Fondo de la pantalla
		JLabel fondoApp2 = new JLabel();
		fondoApp2.setIcon(new ImageIcon(imagenFondoApp.getScaledInstance(1142, 686, Image.SCALE_DEFAULT)));
		fondoApp2.setBounds(0, 0, 1142, 686);
		identiAdmin.add(fondoApp2);

		/**
		 * FINAL DE LA PANTALLA DE IDENTIFICACION DE ADMINISTRADOR
		 **/

	}
}
