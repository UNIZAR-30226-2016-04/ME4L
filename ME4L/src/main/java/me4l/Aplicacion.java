package me4l;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.List;

public class Aplicacion extends JFrame {

	/**
	 * 
	 */
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
	// Array/cach� que guarda las recetas que el usuario tiene abiertas
	ArrayList<RecetaVO> recetasAbiertas = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> recetasModificar = new ArrayList<RecetaVO>();
	ArrayList<RecetaVO> recetasParaValidar = new ArrayList<RecetaVO>();
	ArrayList<String> ingredientesAplicacion = new ArrayList<String>();
	ArrayList<String> indexIngredientes_PR = new ArrayList<String>();
	ArrayList<String> indexIngredientes_A = new ArrayList<String>();
	ArrayList<String> indexIngredientes_MR = new ArrayList<String>();
	private JTextField textFieldBusqueda_MR;
	// Objeto de operaciones
	private Operaciones o = new Operaciones();
	
	final String algo="/images/";
	
	final String adsearch = algo + "adsearch.png";
	final String close = algo + "close.png";
	final String fondo = algo + "fondoApp.jpg";
	final String help = algo + "help.png";
	final String iconoApp = algo + "IconoAppRecortado.png";
	final String one = algo + "one.png";
	final String plus = algo + "plus.png";
	final String refresh = algo + "refresh.png";
	final String ret = algo + "return.png";
	final String search = algo + "search.png";
	final String signin = algo + "signIn.png";
	final String three = algo + "three.png";
	final String two = algo + "two.png";
	final String write = algo + "write.png";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion frame = new Aplicacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Metodo que pone todos los botones en blanco
	private static void botonesEnBlanco(JButton botonAvanzada,
			JButton botonPrimeros, JButton botonSegundos, JButton botonPostres,
			JButton botonDestacados, JButton botonProponer, JButton botonAyuda) {
		botonAvanzada.setBackground(new Color(245, 245, 245));
		botonPrimeros.setBackground(new Color(245, 245, 245));
		botonSegundos.setBackground(new Color(245, 245, 245));
		botonPostres.setBackground(new Color(245, 245, 245));
		botonDestacados.setBackground(new Color(245, 245, 245));
		botonProponer.setBackground(new Color(245, 245, 245));
		botonAyuda.setBackground(new Color(245, 245, 245));
	}

	// Metodo que comprueba si la contrase�a es correcta
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

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Aplicacion() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1148, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		setResizable(false);

		// Controlador de cambio de pantallas
		final CardLayout cardLayout = (CardLayout) contentPane.getLayout();

		/**
		 * COMIENZO DE LA PANTALLA DE INICIO
		 **/
		// Panel de la pantalla de inicio
		JPanel inicio = new JPanel();
		contentPane.add(inicio, "pantallaInicio");
		inicio.setLayout(null);

		// Nombre abreviado
		JLabel lblMel1 = new JLabel("ME4L");
		lblMel1.setFont(new Font("Bauhaus 93", Font.BOLD, 84));
		lblMel1.setBounds(438, 266, 211, 162);
		inicio.add(lblMel1);

		// Icono de la Aplicacion
		JLabel IconoApp1 = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource(
				iconoApp)).getImage();
		IconoApp1.setIcon(new ImageIcon(img1.getScaledInstance(319, 288,
				Image.SCALE_DEFAULT)));
		IconoApp1.setBounds(372, 13, 319, 288);
		inicio.add(IconoApp1);

		// Boton Entrar
		JButton botonEntrar1 = new JButton("Entrar");
		botonEntrar1.setBackground(new Color(250, 250, 250));
		botonEntrar1.setFont(new Font("Calibri", Font.BOLD, 48));
		botonEntrar1.setBounds(372, 472, 319, 69);
		try {
			Image img2 = ImageIO.read(getClass().getResource(signin));
			botonEntrar1.setIcon(new ImageIcon(img2.getScaledInstance(60, 60,
					Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		inicio.add(botonEntrar1);

		// Accion del boton de Entrar
		botonEntrar1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "menuPrincipal");
			}
		});

		// M
		JLabel labelTitulo1 = new JLabel("M");
		labelTitulo1.setForeground(new Color(255, 153, 0));
		labelTitulo1
				.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		labelTitulo1.setBounds(312, 371, 37, 86);
		inicio.add(labelTitulo1);

		// y
		JLabel lblY1 = new JLabel("y");
		lblY1.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblY1.setBounds(344, 371, 37, 86);
		inicio.add(lblY1);

		// E
		JLabel lblE1 = new JLabel("E");
		lblE1.setForeground(new Color(255, 153, 0));
		lblE1.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblE1.setBounds(385, 371, 37, 86);
		inicio.add(lblE1);

		// lection
		JLabel lblMy1 = new JLabel("lection");
		lblMy1.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblMy1.setBounds(406, 371, 138, 86);
		inicio.add(lblMy1);

		// F
		JLabel lblF1 = new JLabel("F");
		lblF1.setForeground(new Color(255, 153, 0));
		lblF1.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblF1.setBounds(563, 371, 37, 86);
		inicio.add(lblF1);

		// or
		JLabel lblOr1 = new JLabel("or");
		lblOr1.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblOr1.setBounds(582, 371, 55, 86);
		inicio.add(lblOr1);

		// L
		JLabel lblL1 = new JLabel("L");
		lblL1.setForeground(new Color(255, 153, 0));
		lblL1.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblL1.setBounds(642, 371, 23, 86);
		inicio.add(lblL1);

		// unch
		JLabel lblUnch1 = new JLabel("unch");
		lblUnch1.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblUnch1.setBounds(661, 371, 100, 86);
		inicio.add(lblUnch1);

		// Fondo de la pantalla
		JLabel fondoApp1 = new JLabel();
		Image img3 = new ImageIcon(this.getClass().getResource(fondo))
				.getImage();
		fondoApp1.setIcon(new ImageIcon(img3.getScaledInstance(1132, 611,
				Image.SCALE_DEFAULT)));
		fondoApp1.setBounds(0, 0, 1132, 611);
		inicio.add(fondoApp1);

		/**
		 * FINAL DE LA PANTALLA DE INICIO
		 **/

		/**
		 * COMIENZO DE LA PANTALLA DEL MENU PRINCIPAL
		 **/

		final JPanel menuPrincipal = new JPanel();
		contentPane.add(menuPrincipal, "menuPrincipal");
		menuPrincipal.setLayout(null);

		// Icono de la aplicacion
		JLabel IconoApp3 = new JLabel("");
		Image img7 = new ImageIcon(this.getClass().getResource(
				iconoApp)).getImage();

		// Panel de Administrador
		final JPanel barraAdmin = new JPanel();
		barraAdmin.setBounds(859, 12, 261, 30);
		menuPrincipal.add(barraAdmin);
		barraAdmin.setLayout(new CardLayout(0, 0));

		// Controlador de los cambios de la barra del administrador
		final CardLayout cardAdmin = (CardLayout) barraAdmin.getLayout();

		// Si no se ha identificado el administrador
		JPanel estadoSinIdentificar = new JPanel();
		barraAdmin.add(estadoSinIdentificar, "estadoSinIdentificar");
		estadoSinIdentificar.setLayout(null);

		// Boton de identificarte como administrador
		JButton botonIdentificate = new JButton(
				"Identificate como Administrador");
		botonIdentificate.setBackground(new Color(245, 245, 245));
		botonIdentificate.setFont(new Font("Calibri", Font.BOLD, 16));
		botonIdentificate.setBounds(0, 0, 261, 30);
		estadoSinIdentificar.add(botonIdentificate);

		// Accion del boton de identificarte como Administrador
		botonIdentificate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "identiAdminentificacion");
			}
		});

		// Si se ha identificado el administrador
		JPanel estadoIdentificado = new JPanel();
		barraAdmin.add(estadoIdentificado, "estadoIdentificado");
		estadoIdentificado.setLayout(null);

		// Menu Administrador
		JMenuBar menuAdmin = new JMenuBar();
		menuAdmin.setFont(new Font("Calibri", Font.BOLD, 16));
		menuAdmin.setBounds(0, 0, 261, 30);
		estadoIdentificado.add(menuAdmin);

		// Pesta�a Menu Administrador
		JMenu botonAdmin = new JMenu("Men� Administrador");
		botonAdmin.setHorizontalAlignment(SwingConstants.TRAILING);
		botonAdmin.setFont(new Font("Calibri", Font.BOLD, 16));
		botonAdmin.setBounds(0, 0, 261, 30);
		menuAdmin.add(botonAdmin);

		// Boton validar receta
		JMenuItem botonValidar = new JMenuItem("Validar Receta");
		botonAdmin.add(botonValidar);

		// Boton a�adir receta
		JMenuItem botonAnyadir = new JMenuItem("A�adir Receta");
		botonAdmin.add(botonAnyadir);

		// Boton modificar receta
		JMenuItem botonModificar = new JMenuItem("Modificar Receta");
		botonAdmin.add(botonModificar);

		// JPanel de la pantalla de los Menus
		final JPanel pantallaMenu = new JPanel();
		pantallaMenu.setBounds(262, 185, 858, 421);
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

		// Panel Busqueda Avanzada
		JPanel panelAvanzada = new JPanel();
		panelAvanzada.setOpaque(false);
		pantallaMenu.add(panelAvanzada, "panelAvanzada");
		panelAvanzada.setLayout(null);

		// Panel Primeros
		JPanel panelPrimeros = new JPanel();
		panelPrimeros.setOpaque(false);
		pantallaMenu.add(panelPrimeros, "panelPrimeros");
		panelPrimeros.setLayout(null);

		// Panel Segundos
		JPanel panelSegundos = new JPanel();
		panelSegundos.setOpaque(false);
		pantallaMenu.add(panelSegundos, "panelSegundos");
		panelSegundos.setLayout(null);

		// Panel Postres
		JPanel panelPostres = new JPanel();
		panelPostres.setOpaque(false);
		pantallaMenu.add(panelPostres, "panelPostres");
		panelPostres.setLayout(null);

		// Panel Receta Abierta
		JPanel panelReceta = new JPanel();
		panelReceta.setOpaque(false);
		pantallaMenu.add(panelReceta, "panelReceta");
		panelReceta.setLayout(null);

		// Panel Proponer
		JPanel panelProponer = new JPanel();
		panelProponer.setOpaque(false);
		pantallaMenu.add(panelProponer, "panelProponer");
		panelProponer.setLayout(null);

		// ComboBox del numero de personas
		final JComboBox comboBoxNumPersonas_PR = new JComboBox();
		comboBoxNumPersonas_PR.setBounds(171, 48, 272, 22);
		comboBoxNumPersonas_PR.setModel(new DefaultComboBoxModel(new String[] {
				"-- Seleccione el n\u00FAmero de personas --", "1", "2", "4",
				"8" }));
		comboBoxNumPersonas_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		panelProponer.add(comboBoxNumPersonas_PR);
		comboBoxNumPersonas_PR.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_PR = new JComboBox();
		comboBoxIngrediente_PR.setModel(new DefaultComboBoxModel(
				new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxIngrediente_PR.setBounds(631, 124, 204, 22);
		panelProponer.add(comboBoxIngrediente_PR);
		comboBoxIngrediente_PR.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco1_PR = new JLabel("*");
		labelAsterisco1_PR.setForeground(Color.RED);
		labelAsterisco1_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco1_PR.setBounds(442, 15, 19, 16);
		panelProponer.add(labelAsterisco1_PR);

		// Text field del nombre de la receta
		textFieldNombre_PR = new JTextField();
		textFieldNombre_PR.setBounds(171, 13, 272, 22);
		textFieldNombre_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldNombre_PR.setToolTipText("Introduzca el nombre de la receta.");
		panelProponer.add(textFieldNombre_PR);
		textFieldNombre_PR.setColumns(10);

		// ComboBox del ingrediente Principal
		final JComboBox comboBoxIngPrinc_PR = new JComboBox();
		comboBoxIngPrinc_PR.setBounds(631, 13, 204, 22);
		comboBoxIngPrinc_PR.setModel(new DefaultComboBoxModel(
				new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngPrinc_PR.setFont(new Font("Calibri", Font.BOLD, 15));
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
		labelNombre_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelProponer.add(labelNombre_PR);

		// Label del ingrediente principal
		JLabel labelIngPrinc_PR = new JLabel("Ingrediente Principal:");
		labelIngPrinc_PR.setBounds(465, 12, 174, 23);
		labelIngPrinc_PR.setForeground(Color.WHITE);
		labelIngPrinc_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelProponer.add(labelIngPrinc_PR);

		// Label del numero de personas
		JLabel labelNumPersonas_PR = new JLabel("N\u00FAmero de personas:");
		labelNumPersonas_PR.setBounds(0, 48, 174, 23);
		labelNumPersonas_PR.setForeground(Color.WHITE);
		labelNumPersonas_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelProponer.add(labelNumPersonas_PR);

		// Label de la URL de la imagen
		JLabel labelTipo_PR = new JLabel("Tipo de plato:");
		labelTipo_PR.setBounds(0, 84, 174, 23);
		labelTipo_PR.setForeground(Color.WHITE);
		labelTipo_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelProponer.add(labelTipo_PR);

		// Label descripcion de la receta
		JLabel labelDescripcion_PR = new JLabel(
				"Descripci\u00F3n de la receta:");
		labelDescripcion_PR.setBounds(0, 123, 207, 23);
		labelDescripcion_PR.setForeground(Color.WHITE);
		labelDescripcion_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelProponer.add(labelDescripcion_PR);

		// Label Otros ingredientes
		JLabel labelIngredientes_PR = new JLabel("Otros inredientes:");
		labelIngredientes_PR.setBounds(465, 99, 174, 23);
		labelIngredientes_PR.setForeground(Color.WHITE);
		labelIngredientes_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelProponer.add(labelIngredientes_PR);

		// Separador Vertical
		JSeparator separadorVertical_PR = new JSeparator();
		separadorVertical_PR.setOrientation(SwingConstants.VERTICAL);
		separadorVertical_PR.setBounds(455, 0, 12, 421);
		panelProponer.add(separadorVertical_PR);

		// Separador horizontal
		JSeparator separadorHorizontal_PR = new JSeparator();
		separadorHorizontal_PR.setBounds(455, 83, 403, 12);
		panelProponer.add(separadorHorizontal_PR);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_PR = new JLabel("Cantidad:");
		labelCantidadPrinc_PR.setForeground(Color.WHITE);
		labelCantidadPrinc_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCantidadPrinc_PR.setBounds(553, 48, 91, 23);
		panelProponer.add(labelCantidadPrinc_PR);

		// Label gramos
		JLabel labelGramos_PR = new JLabel("gramos");
		labelGramos_PR.setForeground(Color.WHITE);
		labelGramos_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelGramos_PR.setBounds(780, 48, 66, 23);
		panelProponer.add(labelGramos_PR);

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
		textFieldCantidadPrinc_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldCantidadPrinc_PR.setBounds(631, 48, 137, 22);
		panelProponer.add(textFieldCantidadPrinc_PR);
		textFieldCantidadPrinc_PR.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final TextArea textAreaDescripcion_PR = new TextArea();
		textAreaDescripcion_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		textAreaDescripcion_PR.setBounds(0, 152, 440, 180);
		panelProponer.add(textAreaDescripcion_PR);

		// Boton de enviar la receta propuesta
		JButton botonEnviarPropuesta_PR = new JButton("Enviar Receta Propuesta");
		botonEnviarPropuesta_PR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonEnviarPropuesta_PR.setBackground(new Color(173, 255, 47));
		botonEnviarPropuesta_PR.setBounds(85, 338, 281, 60);
		panelProponer.add(botonEnviarPropuesta_PR);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_PR.addItem(ingredientesAplicacion.get(i));
		}

		// Label ingrediente
		JLabel labelIngrediente_PR = new JLabel("Ingrediente:");
		labelIngrediente_PR.setForeground(Color.WHITE);
		labelIngrediente_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelIngrediente_PR.setBounds(535, 123, 117, 23);
		panelProponer.add(labelIngrediente_PR);

		// Label cantidad del ingrediente
		JLabel labelCantidad_PR = new JLabel("Cantidad:");
		labelCantidad_PR.setForeground(Color.WHITE);
		labelCantidad_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCantidad_PR.setBounds(553, 160, 91, 23);
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
		textFieldCantidad_PR.setFont(new Font("Calibri", Font.BOLD, 16));
		textFieldCantidad_PR.setColumns(10);
		textFieldCantidad_PR.setBounds(631, 160, 137, 22);
		panelProponer.add(textFieldCantidad_PR);

		// Label gramos 2
		JLabel labelGramos2_PR = new JLabel("gramos");
		labelGramos2_PR.setForeground(Color.WHITE);
		labelGramos2_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelGramos2_PR.setBounds(780, 160, 66, 23);
		panelProponer.add(labelGramos2_PR);

		// Boton a�adir ingrediente
		JButton botonAnyadirIngrediente_PR = new JButton("A�adir Ingrediente");
		botonAnyadirIngrediente_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		botonAnyadirIngrediente_PR.setBackground(new Color(245, 245, 245));
		botonAnyadirIngrediente_PR.setBounds(465, 375, 180, 33);
		panelProponer.add(botonAnyadirIngrediente_PR);

		// Boton borrar ingrediente
		JButton botonBorrarIngrediente_PR = new JButton("Borrar Ingrediente");
		botonBorrarIngrediente_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		botonBorrarIngrediente_PR.setBackground(new Color(245, 245, 245));
		botonBorrarIngrediente_PR.setBounds(667, 375, 180, 33);
		panelProponer.add(botonBorrarIngrediente_PR);

		// Lista con los ingredientes a�adidos
		final List listaIngredientes_PR = new List();
		listaIngredientes_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		listaIngredientes_PR.setMultipleMode(false);
		listaIngredientes_PR.setBounds(465, 192, 383, 174);
		panelProponer.add(listaIngredientes_PR);

		JLabel labelAsterisco2_PR = new JLabel("*");
		labelAsterisco2_PR.setForeground(Color.RED);
		labelAsterisco2_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco2_PR.setBounds(442, 48, 19, 16);
		panelProponer.add(labelAsterisco2_PR);

		JLabel labelAsterisco3_PR = new JLabel("*");
		labelAsterisco3_PR.setForeground(Color.RED);
		labelAsterisco3_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco3_PR.setBounds(442, 141, 19, 16);
		panelProponer.add(labelAsterisco3_PR);

		JLabel labelAsterisco4_PR = new JLabel("*");
		labelAsterisco4_PR.setForeground(Color.RED);
		labelAsterisco4_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco4_PR.setBounds(839, 15, 19, 16);
		panelProponer.add(labelAsterisco4_PR);

		JLabel labelAsterisco5_PR = new JLabel("*");
		labelAsterisco5_PR.setForeground(Color.RED);
		labelAsterisco5_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco5_PR.setBounds(839, 50, 19, 16);
		panelProponer.add(labelAsterisco5_PR);

		JLabel labelCampoObligatorio_PR = new JLabel("* Campo obligatorio");
		labelCampoObligatorio_PR.setForeground(Color.RED);
		labelCampoObligatorio_PR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCampoObligatorio_PR.setBounds(0, 404, 174, 16);
		panelProponer.add(labelCampoObligatorio_PR);

		// ComboBox del tipo de plato
		final JComboBox comboBoxTipo_PR = new JComboBox();
		comboBoxTipo_PR.setModel(new DefaultComboBoxModel(new String[] {
				"-- Seleccione el tipo de plato --", "Primero", "Segundo",
				"Postre" }));
		comboBoxTipo_PR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxTipo_PR.setBounds(171, 84, 272, 22);
		panelProponer.add(comboBoxTipo_PR);
		comboBoxTipo_PR.setLightWeightPopupEnabled(false);

		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		label.setBounds(442, 77, 19, 16);
		panelProponer.add(label);

		// Accion del boton de borrar ingrediente
		botonBorrarIngrediente_PR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (listaIngredientes_PR.getSelectedItem() != null) {
					listaIngredientes_PR.remove(listaIngredientes_PR
							.getSelectedIndex());
					indexIngredientes_PR.remove(listaIngredientes_PR.getSelectedItem().toString().substring(0,listaIngredientes_PR.getSelectedItem().toString()
							.indexOf('-') - 1));
				}
			}
		});

		// Accion del boton de a�adir ingrediente
		botonAnyadirIngrediente_PR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if ((comboBoxIngrediente_PR.getSelectedIndex() != 0)
						&& (!textFieldCantidad_PR.getText().equals("") && Integer
								.parseInt(textFieldCantidad_PR.getText()) > 0)) {
					if (comboBoxIngPrinc_PR.getSelectedIndex() == comboBoxIngrediente_PR
							.getSelectedIndex()) {
						JOptionPane
								.showMessageDialog(
										menuPrincipal,
										"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_PR
							.contains(comboBoxIngrediente_PR.getSelectedItem())) {
						JOptionPane
								.showMessageDialog(menuPrincipal,
										"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_PR.add(comboBoxIngrediente_PR
								.getSelectedItem()
								+ " - "
								+ textFieldCantidad_PR.getText());
						indexIngredientes_PR.add(comboBoxIngrediente_PR.getSelectedItem().toString());
					}
				}
			}
		});

		// Accion del boton de enviar la receta propuesta
		botonEnviarPropuesta_PR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!textFieldNombre_PR.getText().equals("")
						&& comboBoxNumPersonas_PR.getSelectedIndex() != 0
						&& !textAreaDescripcion_PR.getText().equals("")
						&& comboBoxIngPrinc_PR.getSelectedIndex() != 0
						&& !textFieldCantidadPrinc_PR.getText().equals("")
						&& comboBoxTipo_PR.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_PR.getText().length() > 4
							|| textFieldCantidad_PR.getText().length() > 4) {
						JOptionPane
								.showMessageDialog(menuPrincipal,
										"Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes = new ArrayList<String>();
						ArrayList<String> pesoIngredientes = new ArrayList<String>();
						String[] s = listaIngredientes_PR.getItems();

						ingredientes.add(comboBoxIngPrinc_PR.getSelectedItem()
								.toString());
						pesoIngredientes.add(textFieldCantidadPrinc_PR
								.getText());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0,
									elemento.indexOf('-') - 1);
							String cantidad = elemento.substring(
									elemento.indexOf('-') + 1,
									elemento.length());
							ingredientes.add(ingrediente);
							pesoIngredientes.add(cantidad);
						}

						o.addReceta(textFieldNombre_PR.getText(),
								textAreaDescripcion_PR.getText(),
								comboBoxTipo_PR.getSelectedItem().toString(),
								comboBoxNumPersonas_PR.getSelectedItem()
										.toString(), ingredientes,
								pesoIngredientes);

						JOptionPane
								.showMessageDialog(menuPrincipal,
										"La receta propuesta ha sido enviada correctamente.");
						textFieldNombre_PR.setText("");
						comboBoxNumPersonas_PR.setSelectedIndex(0);
						textAreaDescripcion_PR.setText("");
						comboBoxIngPrinc_PR.setSelectedIndex(0);
						textFieldCantidadPrinc_PR.setText("");
						comboBoxIngrediente_PR.setSelectedIndex(0);
						comboBoxTipo_PR.setSelectedIndex(0);
						textFieldCantidad_PR.setText("");
						listaIngredientes_PR.removeAll();
						ingredientes.clear();
						pesoIngredientes.clear();
					}

				} else {
					// Si algun campo no esta rellenado correctamente
					JOptionPane
							.showMessageDialog(menuPrincipal,
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
		pantallaMenu.add(panelAyuda, "panelAyud");
		panelAyuda.setLayout(null);

		// Panel A�adir Receta
		JPanel panelAnyadir = new JPanel();
		panelAnyadir.setOpaque(false);
		pantallaMenu.add(panelAnyadir, "panelAnyadir");
		panelAnyadir.setLayout(null);

		// ComboBox del numero de personas
		final JComboBox comboBoxNumPersonas_A = new JComboBox();
		comboBoxNumPersonas_A.setBounds(171, 48, 272, 22);
		comboBoxNumPersonas_A.setModel(new DefaultComboBoxModel(new String[] {
				"-- Seleccione el n\u00FAmero de personas --", "1", "2", "4",
				"8" }));
		comboBoxNumPersonas_A.setFont(new Font("Calibri", Font.BOLD, 15));
		panelAnyadir.add(comboBoxNumPersonas_A);
		comboBoxNumPersonas_A.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_A = new JComboBox();
		comboBoxIngrediente_A.setModel(new DefaultComboBoxModel(
				new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_A.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxIngrediente_A.setBounds(631, 124, 204, 22);
		panelAnyadir.add(comboBoxIngrediente_A);
		comboBoxIngrediente_A.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco1_A = new JLabel("*");
		labelAsterisco1_A.setForeground(Color.RED);
		labelAsterisco1_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco1_A.setBounds(442, 15, 19, 16);
		panelAnyadir.add(labelAsterisco1_A);

		// Text field del nombre de la receta
		textFieldNombre_A = new JTextField();
		textFieldNombre_A.setBounds(171, 13, 272, 22);
		textFieldNombre_A.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldNombre_A.setToolTipText("Introduzca el nombre de la receta.");
		panelAnyadir.add(textFieldNombre_A);
		textFieldNombre_A.setColumns(10);

		// ComboBox del ingrediente Principal
		final JComboBox comboBoxIngPrinc_A = new JComboBox();
		comboBoxIngPrinc_A.setBounds(631, 13, 204, 22);
		comboBoxIngPrinc_A.setModel(new DefaultComboBoxModel(
				new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngPrinc_A.setFont(new Font("Calibri", Font.BOLD, 15));
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
		labelNombre_A.setFont(new Font("Calibri", Font.BOLD, 18));
		panelAnyadir.add(labelNombre_A);

		// Label del ingrediente principal
		JLabel labelIngPrinc_A = new JLabel("Ingrediente Principal:");
		labelIngPrinc_A.setBounds(465, 12, 174, 23);
		labelIngPrinc_A.setForeground(Color.WHITE);
		labelIngPrinc_A.setFont(new Font("Calibri", Font.BOLD, 18));
		panelAnyadir.add(labelIngPrinc_A);

		// Label del numero de personas
		JLabel labelNumPersonas_A = new JLabel("N\u00FAmero de personas:");
		labelNumPersonas_A.setBounds(0, 48, 174, 23);
		labelNumPersonas_A.setForeground(Color.WHITE);
		labelNumPersonas_A.setFont(new Font("Calibri", Font.BOLD, 18));
		panelAnyadir.add(labelNumPersonas_A);

		// Label del tipo de plato
		JLabel labelTipo_A = new JLabel("Tipo de plato:");
		labelTipo_A.setBounds(0, 84, 174, 23);
		labelTipo_A.setForeground(Color.WHITE);
		labelTipo_A.setFont(new Font("Calibri", Font.BOLD, 18));
		panelAnyadir.add(labelTipo_A);

		// Label descripcion de la receta
		JLabel labelDescripcion_A = new JLabel("Descripci\u00F3n de la receta:");
		labelDescripcion_A.setBounds(0, 123, 207, 23);
		labelDescripcion_A.setForeground(Color.WHITE);
		labelDescripcion_A.setFont(new Font("Calibri", Font.BOLD, 18));
		panelAnyadir.add(labelDescripcion_A);

		// Label Otros ingredientes
		JLabel labelIngredientes_A = new JLabel("Otros inredientes:");
		labelIngredientes_A.setBounds(465, 99, 174, 23);
		labelIngredientes_A.setForeground(Color.WHITE);
		labelIngredientes_A.setFont(new Font("Calibri", Font.BOLD, 18));
		panelAnyadir.add(labelIngredientes_A);

		// Separador Vertical
		JSeparator separadorVertical_A = new JSeparator();
		separadorVertical_A.setOrientation(SwingConstants.VERTICAL);
		separadorVertical_A.setBounds(455, 0, 12, 421);
		panelAnyadir.add(separadorVertical_A);

		// Separador horizontal
		JSeparator separadorHorizontal_A = new JSeparator();
		separadorHorizontal_A.setBounds(455, 83, 403, 12);
		panelAnyadir.add(separadorHorizontal_A);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_A = new JLabel("Cantidad:");
		labelCantidadPrinc_A.setForeground(Color.WHITE);
		labelCantidadPrinc_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCantidadPrinc_A.setBounds(553, 48, 91, 23);
		panelAnyadir.add(labelCantidadPrinc_A);

		// Label gramos
		JLabel labelGramos_A = new JLabel("gramos");
		labelGramos_A.setForeground(Color.WHITE);
		labelGramos_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelGramos_A.setBounds(780, 48, 66, 23);
		panelAnyadir.add(labelGramos_A);

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
		textFieldCantidadPrinc_A.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldCantidadPrinc_A.setBounds(631, 48, 137, 22);
		panelAnyadir.add(textFieldCantidadPrinc_A);
		textFieldCantidadPrinc_A.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final TextArea textAreaDescripcion_A = new TextArea();
		textAreaDescripcion_A.setFont(new Font("Calibri", Font.BOLD, 15));
		textAreaDescripcion_A.setBounds(0, 152, 440, 180);
		panelAnyadir.add(textAreaDescripcion_A);

		// Boton de enviar la receta propuesta
		JButton botonEnviarPropuesta_A = new JButton("A\u00D1ADIR RECETA");
		botonEnviarPropuesta_A.setFont(new Font("Calibri", Font.BOLD, 23));
		botonEnviarPropuesta_A.setBackground(new Color(173, 255, 47));
		botonEnviarPropuesta_A.setBounds(85, 338, 281, 60);
		panelAnyadir.add(botonEnviarPropuesta_A);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_A.addItem(ingredientesAplicacion.get(i));
		}

		// Label ingrediente
		JLabel labelIngrediente_A = new JLabel("Ingrediente:");
		labelIngrediente_A.setForeground(Color.WHITE);
		labelIngrediente_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelIngrediente_A.setBounds(535, 123, 117, 23);
		panelAnyadir.add(labelIngrediente_A);

		// Label cantidad del ingrediente
		JLabel labelCantidad_A = new JLabel("Cantidad:");
		labelCantidad_A.setForeground(Color.WHITE);
		labelCantidad_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCantidad_A.setBounds(553, 160, 91, 23);
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
		textFieldCantidad_A.setFont(new Font("Calibri", Font.BOLD, 16));
		textFieldCantidad_A.setColumns(10);
		textFieldCantidad_A.setBounds(631, 160, 137, 22);
		panelAnyadir.add(textFieldCantidad_A);

		// Label gramos 2
		JLabel labelGramos2_A = new JLabel("gramos");
		labelGramos2_A.setForeground(Color.WHITE);
		labelGramos2_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelGramos2_A.setBounds(780, 160, 66, 23);
		panelAnyadir.add(labelGramos2_A);

		// Boton a�adir ingrediente
		JButton botonAnyadirIngrediente_A = new JButton("A�adir Ingrediente");
		botonAnyadirIngrediente_A.setFont(new Font("Calibri", Font.BOLD, 18));
		botonAnyadirIngrediente_A.setBackground(new Color(245, 245, 245));
		botonAnyadirIngrediente_A.setBounds(465, 375, 180, 33);
		panelAnyadir.add(botonAnyadirIngrediente_A);

		// Boton borrar ingrediente
		JButton botonBorrarIngrediente_A = new JButton("Borrar Ingrediente");
		botonBorrarIngrediente_A.setFont(new Font("Calibri", Font.BOLD, 18));
		botonBorrarIngrediente_A.setBackground(new Color(245, 245, 245));
		botonBorrarIngrediente_A.setBounds(667, 375, 180, 33);
		panelAnyadir.add(botonBorrarIngrediente_A);

		// Lista con los ingredientes a�adidos
		final List listaIngredientes_A = new List();
		listaIngredientes_A.setFont(new Font("Calibri", Font.BOLD, 15));
		listaIngredientes_A.setMultipleMode(false);
		listaIngredientes_A.setBounds(465, 192, 383, 174);
		panelAnyadir.add(listaIngredientes_A);

		JLabel labelAsterisco2_A = new JLabel("*");
		labelAsterisco2_A.setForeground(Color.RED);
		labelAsterisco2_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco2_A.setBounds(442, 48, 19, 16);
		panelAnyadir.add(labelAsterisco2_A);

		JLabel labelAsterisco3_A = new JLabel("*");
		labelAsterisco3_A.setForeground(Color.RED);
		labelAsterisco3_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco3_A.setBounds(442, 141, 19, 16);
		panelAnyadir.add(labelAsterisco3_A);

		JLabel labelAsterisco4_A = new JLabel("*");
		labelAsterisco4_A.setForeground(Color.RED);
		labelAsterisco4_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco4_A.setBounds(839, 15, 19, 16);
		panelAnyadir.add(labelAsterisco4_A);

		JLabel labelAsterisco5_A = new JLabel("*");
		labelAsterisco5_A.setForeground(Color.RED);
		labelAsterisco5_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco5_A.setBounds(839, 50, 19, 16);
		panelAnyadir.add(labelAsterisco5_A);

		JLabel labelCampoObligatorio_A = new JLabel("* Campo obligatorio");
		labelCampoObligatorio_A.setForeground(Color.RED);
		labelCampoObligatorio_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCampoObligatorio_A.setBounds(0, 404, 174, 16);
		panelAnyadir.add(labelCampoObligatorio_A);

		// ComboBox del tipo de plato
		final JComboBox comboBoxTipo_A = new JComboBox();
		comboBoxTipo_A.setModel(new DefaultComboBoxModel(new String[] {
				"-- Seleccione el tipo de plato --", "Primero", "Segundo",
				"Postre" }));
		comboBoxTipo_A.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxTipo_A.setBounds(171, 84, 272, 22);
		panelAnyadir.add(comboBoxTipo_A);
		comboBoxTipo_A.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco6_A = new JLabel("*");
		labelAsterisco6_A.setForeground(Color.RED);
		labelAsterisco6_A.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco6_A.setBounds(442, 77, 19, 16);
		panelAnyadir.add(labelAsterisco6_A);

		// Accion del boton de borrar ingrediente
		botonBorrarIngrediente_A.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (listaIngredientes_A.getSelectedItem() != null) {
					indexIngredientes_A.remove(listaIngredientes_A.getSelectedItem().toString().substring(0,listaIngredientes_A.getSelectedItem().toString()
							.indexOf('-') - 1));
					listaIngredientes_A.remove(listaIngredientes_A
							.getSelectedIndex());
				}
			}
		});

		// Accion del boton de a�adir ingrediente
		botonAnyadirIngrediente_A.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if ((comboBoxIngrediente_A.getSelectedIndex() != 0)
						&& (!textFieldCantidad_A.getText().equals("") && Integer
								.parseInt(textFieldCantidad_A.getText()) > 0)) {
					if (comboBoxIngPrinc_A.getSelectedIndex() == comboBoxIngrediente_A
							.getSelectedIndex()) {
						JOptionPane
								.showMessageDialog(
										menuPrincipal,
										"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_A
							.contains(comboBoxIngrediente_A.getSelectedItem())) {
						JOptionPane
								.showMessageDialog(menuPrincipal,
										"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_A.add(comboBoxIngrediente_A
								.getSelectedItem()
								+ " - "
								+ textFieldCantidad_A.getText());
						indexIngredientes_A.add(comboBoxIngrediente_A.getSelectedItem().toString());
					}
				}
			}
		});

		// Accion del boton de a�adir receta
		botonEnviarPropuesta_A.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!textFieldNombre_A.getText().equals("")
						&& comboBoxNumPersonas_A.getSelectedIndex() != 0
						&& !textAreaDescripcion_A.getText().equals("")
						&& comboBoxIngPrinc_A.getSelectedIndex() != 0
						&& !textFieldCantidadPrinc_A.getText().equals("")
						&& comboBoxTipo_A.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_A.getText().length() > 4
							|| textFieldCantidad_A.getText().length() > 4) {
						JOptionPane
								.showMessageDialog(menuPrincipal,
										"Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes_A = new ArrayList<String>();
						ArrayList<String> pesoIngredientes_A = new ArrayList<String>();
						String[] s = listaIngredientes_A.getItems();

						ingredientes_A.add(comboBoxIngPrinc_A.getSelectedItem()
								.toString());
						pesoIngredientes_A.add(textFieldCantidadPrinc_A
								.getText());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0,
									elemento.indexOf('-') - 1);
							String cantidad = elemento.substring(
									elemento.indexOf('-') + 1,
									elemento.length());
							ingredientes_A.add(ingrediente);
							pesoIngredientes_A.add(cantidad);
						}

						o.addRecetaV(textFieldNombre_A.getText(),
								textAreaDescripcion_A.getText(), comboBoxTipo_A
										.getSelectedItem().toString(),
								comboBoxNumPersonas_A.getSelectedItem()
										.toString(), ingredientes_A,
								pesoIngredientes_A);

						JOptionPane.showMessageDialog(menuPrincipal,
								"La receta ha sido a�adida.");
						textFieldNombre_A.setText("");
						comboBoxNumPersonas_A.setSelectedIndex(0);
						textAreaDescripcion_A.setText("");
						comboBoxIngPrinc_A.setSelectedIndex(0);
						textFieldCantidadPrinc_A.setText("");
						comboBoxIngrediente_A.setSelectedIndex(0);
						comboBoxTipo_A.setSelectedIndex(0);
						textFieldCantidad_A.setText("");
						listaIngredientes_A.removeAll();
						ingredientes_A.clear();
						pesoIngredientes_A.clear();
					}

				} else {
					// Si algun campo no esta rellenado correctamente
					JOptionPane
							.showMessageDialog(menuPrincipal,
									"Uno o mas campos obligatorios no estan rellenados correctamente.");
				}
			}
		});

		// Panel Modificar Receta
		JPanel panelModificar = new JPanel();
		panelModificar.setOpaque(false);
		pantallaMenu.add(panelModificar, "panelModificar");
		panelModificar.setLayout(null);

		// TextField Busqueda Modificar Receta
		textFieldBusqueda_MR = new JTextField();
		textFieldBusqueda_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldBusqueda_MR.setBounds(12, 13, 596, 36);
		panelModificar.add(textFieldBusqueda_MR);
		textFieldBusqueda_MR.setColumns(10);

		// Lista de recetas buscadas
		final List listaBuscada_MR = new List();
		listaBuscada_MR.setFont(new Font("Calibri", Font.BOLD, 20));
		listaBuscada_MR.setBounds(12, 72, 596, 336);
		panelModificar.add(listaBuscada_MR);

		// Boton Buscar Modificar Receta
		JButton botonBuscar_MR = new JButton("");
		botonBuscar_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		botonBuscar_MR.setBackground(new Color(245, 245, 245));
		botonBuscar_MR.setBounds(625, 15, 46, 34);
		try {
			Image img16 = ImageIO.read(getClass().getResource(search));
			botonBuscar_MR.setIcon(new ImageIcon(img16.getScaledInstance(40,
					40, Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		panelModificar.add(botonBuscar_MR);

		// Accion del boton de buscar recetas para modificar
		botonBuscar_MR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				listaBuscada_MR.removeAll();
				recetasModificar = o.obtenerValidadas();
				for (RecetaVO receta : recetasModificar) {
					listaBuscada_MR.add(receta.getNombre());
				}
			}
		});

		// Boton ver receta seleccionada modificar receta
		JButton botonVerReceta_MR = new JButton("Ver Receta");
		botonVerReceta_MR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonVerReceta_MR.setBackground(new Color(245, 245, 245));
		botonVerReceta_MR.setBounds(626, 72, 220, 60);
		panelModificar.add(botonVerReceta_MR);

		// Boton Modificar Receta seleccionada
		JButton botonModificar_MR = new JButton("Modificar Receta");
		botonModificar_MR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonModificar_MR.setBackground(new Color(173, 255, 47));
		botonModificar_MR.setBounds(625, 198, 221, 60);
		panelModificar.add(botonModificar_MR);

		// Boton borrar receta seleccionada
		JButton botonBorrar_MR = new JButton("Borrar Receta");
		botonBorrar_MR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonBorrar_MR.setBackground(new Color(255, 127, 80));
		botonBorrar_MR.setBounds(625, 348, 221, 60);
		panelModificar.add(botonBorrar_MR);

		// Accion del boton de borrar la receta seleccionada
		botonBorrar_MR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (listaBuscada_MR.getItemCount() > 0
						&& listaBuscada_MR.getSelectedItem() != null) {
					int index = listaBuscada_MR.getSelectedIndex();
					String id = recetasModificar.get(index).getId();
					o.eliminarReceta(id);
					listaBuscada_MR.remove(index);
					recetasModificar.remove(index);
					JOptionPane.showMessageDialog(menuPrincipal,
							"Receta eliminada correctamente.");
				}
			}
		});

		// Panel Receta abierta para modificar
		JPanel panelModificarReceta = new JPanel();
		panelModificarReceta.setOpaque(false);
		pantallaMenu.add(panelModificarReceta, "panelModificarReceta");
		panelModificarReceta.setLayout(null);

		// ComboBox del numero de personas
		final JComboBox comboBoxNumPersonas_MR = new JComboBox();
		comboBoxNumPersonas_MR.setBounds(171, 48, 272, 22);
		comboBoxNumPersonas_MR.setModel(new DefaultComboBoxModel(new String[] {
				"-- Seleccione el n\u00FAmero de personas --", "1", "2", "4",
				"8" }));
		comboBoxNumPersonas_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		panelModificarReceta.add(comboBoxNumPersonas_MR);
		comboBoxNumPersonas_MR.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_MR = new JComboBox();
		comboBoxIngrediente_MR.setModel(new DefaultComboBoxModel(
				new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxIngrediente_MR.setBounds(631, 124, 204, 22);
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
		comboBoxIngPrinc_MR.setModel(new DefaultComboBoxModel(
				new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngPrinc_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		panelModificarReceta.add(comboBoxIngPrinc_MR);
		comboBoxIngPrinc_MR.setLightWeightPopupEnabled(false);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngPrinc_MR.addItem(ingredientesAplicacion.get(i));
		}

		// Label del nombre de la receta
		JLabel labelNombre_MR = new JLabel("Nombre de la receta:");
		labelNombre_MR.setBounds(0, 12, 174, 23);
		labelNombre_MR.setForeground(Color.WHITE);
		labelNombre_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelModificarReceta.add(labelNombre_MR);

		// Label del ingrediente principal
		JLabel labelIngPrinc_MR = new JLabel("Ingrediente Principal:");
		labelIngPrinc_MR.setBounds(465, 12, 174, 23);
		labelIngPrinc_MR.setForeground(Color.WHITE);
		labelIngPrinc_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelModificarReceta.add(labelIngPrinc_MR);

		// Label del numero de personas
		JLabel labelNumPersonas_MR = new JLabel("N\u00FAmero de personas:");
		labelNumPersonas_MR.setBounds(0, 48, 174, 23);
		labelNumPersonas_MR.setForeground(Color.WHITE);
		labelNumPersonas_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelModificarReceta.add(labelNumPersonas_MR);

		// Label del tipo de plato
		JLabel labelTipo_MR = new JLabel("Tipo de plato:");
		labelTipo_MR.setBounds(0, 84, 174, 23);
		labelTipo_MR.setForeground(Color.WHITE);
		labelTipo_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelModificarReceta.add(labelTipo_MR);

		// Label descripcion de la receta
		JLabel labelDescripcion_MR = new JLabel(
				"Descripci\u00F3n de la receta:");
		labelDescripcion_MR.setBounds(0, 123, 207, 23);
		labelDescripcion_MR.setForeground(Color.WHITE);
		labelDescripcion_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelModificarReceta.add(labelDescripcion_MR);

		// Label Otros ingredientes
		JLabel labelIngredientes_MR = new JLabel("Otros inredientes:");
		labelIngredientes_MR.setBounds(465, 99, 174, 23);
		labelIngredientes_MR.setForeground(Color.WHITE);
		labelIngredientes_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		panelModificarReceta.add(labelIngredientes_MR);

		// Separador Vertical
		JSeparator separadorVertical_MR = new JSeparator();
		separadorVertical_MR.setOrientation(SwingConstants.VERTICAL);
		separadorVertical_MR.setBounds(455, 0, 12, 421);
		panelModificarReceta.add(separadorVertical_MR);

		// Separador horizontal
		JSeparator separadorHorizontal_MR = new JSeparator();
		separadorHorizontal_MR.setBounds(455, 83, 403, 12);
		panelModificarReceta.add(separadorHorizontal_MR);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_MR = new JLabel("Cantidad:");
		labelCantidadPrinc_MR.setForeground(Color.WHITE);
		labelCantidadPrinc_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCantidadPrinc_MR.setBounds(553, 48, 91, 23);
		panelModificarReceta.add(labelCantidadPrinc_MR);

		// Label gramos
		JLabel labelGramos_MR = new JLabel("gramos");
		labelGramos_MR.setForeground(Color.WHITE);
		labelGramos_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelGramos_MR.setBounds(780, 48, 66, 23);
		panelModificarReceta.add(labelGramos_MR);

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
		textFieldCantidadPrinc_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		textFieldCantidadPrinc_MR.setBounds(631, 48, 137, 22);
		panelModificarReceta.add(textFieldCantidadPrinc_MR);
		textFieldCantidadPrinc_MR.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final TextArea textAreaDescripcion_MR = new TextArea();
		textAreaDescripcion_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		textAreaDescripcion_MR.setBounds(0, 152, 440, 180);
		panelModificarReceta.add(textAreaDescripcion_MR);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_MR.addItem(ingredientesAplicacion.get(i));
		}

		// Label ingrediente
		JLabel labelIngrediente_MR = new JLabel("Ingrediente:");
		labelIngrediente_MR.setForeground(Color.WHITE);
		labelIngrediente_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelIngrediente_MR.setBounds(535, 123, 117, 23);
		panelModificarReceta.add(labelIngrediente_MR);

		// Label cantidad del ingrediente
		JLabel labelCantidad_MR = new JLabel("Cantidad:");
		labelCantidad_MR.setForeground(Color.WHITE);
		labelCantidad_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCantidad_MR.setBounds(553, 160, 91, 23);
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
		textFieldCantidad_MR.setFont(new Font("Calibri", Font.BOLD, 16));
		textFieldCantidad_MR.setColumns(10);
		textFieldCantidad_MR.setBounds(631, 160, 137, 22);
		panelModificarReceta.add(textFieldCantidad_MR);

		// Label gramos 2
		JLabel labelGramos2_MR = new JLabel("gramos");
		labelGramos2_MR.setForeground(Color.WHITE);
		labelGramos2_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelGramos2_MR.setBounds(780, 160, 66, 23);
		panelModificarReceta.add(labelGramos2_MR);

		// Boton a�adir ingrediente
		JButton botonAnyadirIngrediente_MR = new JButton("A�adir Ingrediente");
		botonAnyadirIngrediente_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		botonAnyadirIngrediente_MR.setBackground(new Color(245, 245, 245));
		botonAnyadirIngrediente_MR.setBounds(465, 375, 180, 33);
		panelModificarReceta.add(botonAnyadirIngrediente_MR);

		// Boton borrar ingrediente
		JButton botonBorrarIngrediente_MR = new JButton("Borrar Ingrediente");
		botonBorrarIngrediente_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		botonBorrarIngrediente_MR.setBackground(new Color(245, 245, 245));
		botonBorrarIngrediente_MR.setBounds(667, 375, 180, 33);
		panelModificarReceta.add(botonBorrarIngrediente_MR);

		// Lista con los ingredientes a�adidos
		final List listaIngredientes_MR = new List();
		listaIngredientes_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		listaIngredientes_MR.setMultipleMode(false);
		listaIngredientes_MR.setBounds(465, 192, 383, 174);
		panelModificarReceta.add(listaIngredientes_MR);

		JLabel labelAsterisco2_MR = new JLabel("*");
		labelAsterisco2_MR.setForeground(Color.RED);
		labelAsterisco2_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco2_MR.setBounds(442, 48, 19, 16);
		panelModificarReceta.add(labelAsterisco2_MR);

		JLabel labelAsterisco3_MR = new JLabel("*");
		labelAsterisco3_MR.setForeground(Color.RED);
		labelAsterisco3_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco3_MR.setBounds(442, 141, 19, 16);
		panelModificarReceta.add(labelAsterisco3_MR);

		JLabel labelAsterisco4_MR = new JLabel("*");
		labelAsterisco4_MR.setForeground(Color.RED);
		labelAsterisco4_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco4_MR.setBounds(839, 15, 19, 16);
		panelModificarReceta.add(labelAsterisco4_MR);

		JLabel labelAsterisco5_MR = new JLabel("*");
		labelAsterisco5_MR.setForeground(Color.RED);
		labelAsterisco5_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco5_MR.setBounds(839, 50, 19, 16);
		panelModificarReceta.add(labelAsterisco5_MR);

		JLabel labelCampoObligatorio_MR = new JLabel("* Campo obligatorio");
		labelCampoObligatorio_MR.setForeground(Color.RED);
		labelCampoObligatorio_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelCampoObligatorio_MR.setBounds(0, 404, 174, 16);
		panelModificarReceta.add(labelCampoObligatorio_MR);

		// ComboBox del tipo de plato
		final JComboBox comboBoxTipo_MR = new JComboBox();
		comboBoxTipo_MR.setModel(new DefaultComboBoxModel(new String[] {
				"-- Seleccione el tipo de plato --", "Primero", "Segundo",
				"Postre" }));
		comboBoxTipo_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		comboBoxTipo_MR.setBounds(171, 84, 272, 22);
		panelModificarReceta.add(comboBoxTipo_MR);
		comboBoxTipo_MR.setLightWeightPopupEnabled(false);

		JLabel labelAsterisco6_MR = new JLabel("*");
		labelAsterisco6_MR.setForeground(Color.RED);
		labelAsterisco6_MR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelAsterisco6_MR.setBounds(442, 77, 19, 16);
		panelModificarReceta.add(labelAsterisco6_MR);

		// Accion del boton de borrar ingrediente
		botonBorrarIngrediente_MR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (listaIngredientes_MR.getSelectedItem() != null) {
					listaIngredientes_MR.remove(listaIngredientes_MR
							.getSelectedIndex());
					indexIngredientes_MR.remove(listaIngredientes_MR.getSelectedItem().toString().substring(0,listaIngredientes_MR.getSelectedItem().toString()
							.indexOf('-') - 1));
				}
			}
		});
		
		// Accion del boton de a�adir ingrediente
		botonAnyadirIngrediente_MR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if ((comboBoxIngrediente_MR.getSelectedIndex() != 0)
						&& (!textFieldCantidad_MR.getText().equals("") && Integer
								.parseInt(textFieldCantidad_MR.getText()) > 0)) {
					if (comboBoxIngPrinc_MR.getSelectedIndex() == comboBoxIngrediente_MR
							.getSelectedIndex()) {
						JOptionPane
								.showMessageDialog(
										menuPrincipal,
										"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_MR
							.contains(comboBoxIngrediente_MR.getSelectedItem())) {
						JOptionPane
								.showMessageDialog(menuPrincipal,
										"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_MR.add(comboBoxIngrediente_MR
								.getSelectedItem()
								+ " - "
								+ textFieldCantidad_MR.getText());
						indexIngredientes_MR.add(comboBoxIngrediente_MR.getSelectedItem().toString());
					}
				}
			}
		});

		// Boton guardar cambios realizados
		JButton botonGuardarCambios_MR = new JButton("Guardar Cambios");
		botonGuardarCambios_MR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonGuardarCambios_MR.setBackground(new Color(173, 255, 47));
		botonGuardarCambios_MR.setBounds(0, 338, 215, 60);
		panelModificarReceta.add(botonGuardarCambios_MR);

		// Accion del boton de guardar cambios
		botonGuardarCambios_MR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!textFieldNombre_MR.getText().equals("")
						&& comboBoxNumPersonas_MR.getSelectedIndex() != 0
						&& !textAreaDescripcion_MR.getText().equals("")
						&& comboBoxIngPrinc_MR.getSelectedIndex() != 0
						&& !textFieldCantidadPrinc_MR.getText().equals("")
						&& comboBoxTipo_MR.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_MR.getText().length() > 4
							|| textFieldCantidad_MR.getText().length() > 4) {
						JOptionPane
								.showMessageDialog(menuPrincipal,
										"Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes = new ArrayList<String>();
						ArrayList<String> pesoIngredientes = new ArrayList<String>();
						String[] s = listaIngredientes_MR.getItems();

						ingredientes.add(comboBoxIngPrinc_MR.getSelectedItem()
								.toString());
						pesoIngredientes.add(textFieldCantidadPrinc_MR
								.getText());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0,
									elemento.indexOf('-') - 1);
							String cantidad = elemento.substring(
									elemento.indexOf('-') + 1,
									elemento.length());
							ingredientes.add(ingrediente);
							pesoIngredientes.add(cantidad);
						}

						o.modificarReceta(textFieldNombre_MR.getText(),
								textAreaDescripcion_MR.getText(),
								comboBoxTipo_MR.getSelectedItem().toString(),
								comboBoxNumPersonas_MR.getSelectedItem()
										.toString(), ingredientes,
								pesoIngredientes, recetaSeleccionada_MR.getId());
						
						// Recargamos las recetas buscadas para modificar
						listaBuscada_MR.removeAll();
						recetasModificar = o.obtenerValidadas();
						for (RecetaVO receta : recetasModificar) {
							listaBuscada_MR.add(receta.getNombre());
						}

						JOptionPane.showMessageDialog(menuPrincipal,
								"Los cambios en la receta han sido guardados.");
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
						cardMenu.show(pantallaMenu, "panelModificar");
					}

				} else {
					// Si algun campo no esta rellenado correctamente
					JOptionPane
							.showMessageDialog(menuPrincipal,
									"Uno o mas campos obligatorios no estan rellenados correctamente.");
				}
			}
		});

		// Boton Volver Modificar Receta
		JButton botonVolver_MR = new JButton("Volver");
		botonVolver_MR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonVolver_MR.setBackground(new Color(245, 245, 245));
		botonVolver_MR.setBounds(227, 338, 215, 60);
		panelModificarReceta.add(botonVolver_MR);
		try {
			Image img15 = ImageIO.read(getClass().getResource(ret));
			botonVolver_MR.setIcon(new ImageIcon(img15.getScaledInstance(20,
					20, Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}

		// Accion del boton de VOLVER DE MODIFICAR RECETA
		botonVolver_MR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textFieldNombre_MR.setText("");
				comboBoxNumPersonas_MR.setSelectedIndex(0);
				textAreaDescripcion_MR.setText("");
				comboBoxIngPrinc_MR.setSelectedIndex(0);
				textFieldCantidadPrinc_MR.setText("");
				comboBoxIngrediente_MR.setSelectedIndex(0);
				comboBoxTipo_MR.setSelectedIndex(0);
				textFieldCantidad_MR.setText("");
				listaIngredientes_MR.removeAll();
				cardMenu.show(pantallaMenu, "panelModificar");
				recetaSeleccionada_MR = null;
			}
		});

		// Accion del boton de modificar la receta seleccionada
		botonModificar_MR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (listaBuscada_MR.getItemCount() > 0
						&& listaBuscada_MR.getSelectedItem() != null) {
					recetaSeleccionada_MR = recetasModificar
							.get(listaBuscada_MR.getSelectedIndex());
					cardMenu.show(pantallaMenu, "panelModificarReceta");
					// Puesta de valores iniciales en los campos
					textFieldNombre_MR.setText(recetaSeleccionada_MR
							.getNombre());
					comboBoxNumPersonas_MR
							.setSelectedItem(recetaSeleccionada_MR
									.getNumPersonas());
					textAreaDescripcion_MR.setText(recetaSeleccionada_MR
							.getDescripcion());
					comboBoxIngPrinc_MR.setSelectedItem(recetaSeleccionada_MR
							.getIngredientes().get(0));
					textFieldCantidadPrinc_MR.setText(recetaSeleccionada_MR
							.getPesoIngredientes().get(0));
					for (int i = 1; i < recetaSeleccionada_MR.getIngredientes()
							.size(); i++) {
						listaIngredientes_MR.add(recetaSeleccionada_MR
								.getIngredientes().get(i) + " - " + recetaSeleccionada_MR
								.getPesoIngredientes().get(i));
					}
					comboBoxTipo_MR.setSelectedItem(recetaSeleccionada_MR.getPlato());
				}
			}
		});

		// Panel Validar Receta
		JPanel panelValidar = new JPanel();
		panelValidar.setOpaque(false);
		pantallaMenu.add(panelValidar, "panelValidar");
		panelValidar.setLayout(null);

		// Label con texto de recetas pendientes
		JLabel labelRecetasPendientes_VR = new JLabel(
				"Recetas pendientes de ser validadas:");
		labelRecetasPendientes_VR.setForeground(Color.WHITE);
		labelRecetasPendientes_VR.setFont(new Font("Calibri", Font.BOLD, 18));
		labelRecetasPendientes_VR.setBounds(12, 13, 340, 23);
		panelValidar.add(labelRecetasPendientes_VR);

		// Lista de recetas pendientes de ser validadas
		final List listaRecetasPendientesValidar_VR = new List();
		listaRecetasPendientesValidar_VR.setFont(new Font("Calibri", Font.BOLD,20));
		listaRecetasPendientesValidar_VR.setBounds(12, 49, 385, 359);
		panelValidar.add(listaRecetasPendientesValidar_VR);

		// Muestra las recetas pendientes de validar
		recetasParaValidar = o.obtenerNoValidadas();
		for (RecetaVO receta : recetasParaValidar) {
			listaRecetasPendientesValidar_VR.add(receta.getNombre());
		}

		// Boton ver la receta pendiente de validar
		JButton botonVer_VR = new JButton("Ver Receta");
		botonVer_VR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonVer_VR.setBackground(new Color(245, 245, 245));
		botonVer_VR.setBounds(446, 153, 281, 60);
		panelValidar.add(botonVer_VR);

		// Boton validar receta seleccionada
		JButton botonValidar_VR = new JButton("Validar Receta");
		botonValidar_VR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonValidar_VR.setBackground(new Color(173, 255, 47));
		botonValidar_VR.setBounds(446, 253, 281, 60);
		panelValidar.add(botonValidar_VR);

		// Accion del boton de validar la receta
		botonValidar_VR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (listaRecetasPendientesValidar_VR.getItemCount() > 0) {
					int index = listaRecetasPendientesValidar_VR
							.getSelectedIndex();
					String id = recetasParaValidar.get(index).getId();
					o.validarReceta(id);
					listaRecetasPendientesValidar_VR.remove(index);
					recetasParaValidar.remove(index);
					JOptionPane.showMessageDialog(menuPrincipal,
							"La receta ha sido a�adida.");
				}
			}
		});

		// Boton rechazar/eliminar receta seleccionada
		JButton botonRechazar_VR = new JButton("Rechazar Receta");
		botonRechazar_VR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonRechazar_VR.setBackground(new Color(255, 127, 80));
		botonRechazar_VR.setBounds(446, 348, 281, 60);
		panelValidar.add(botonRechazar_VR);

		// Accion del boton de actualizar para validar
		botonRechazar_VR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (listaRecetasPendientesValidar_VR.getItemCount() > 0) {
					int index = listaRecetasPendientesValidar_VR
							.getSelectedIndex();
					String id = recetasParaValidar.get(index).getId();
					o.eliminarReceta(id);
					listaRecetasPendientesValidar_VR.remove(index);
					recetasParaValidar.remove(index);
					JOptionPane.showMessageDialog(menuPrincipal,
							"La receta ha sido rechazada.");
				}
			}
		});

		// Boton actualizar recetas pendientes de validar
		JButton botonActualizar_VR = new JButton("Actualizar");
		botonActualizar_VR.setFont(new Font("Calibri", Font.BOLD, 23));
		botonActualizar_VR.setBackground(new Color(245, 245, 245));
		botonActualizar_VR.setBounds(446, 48, 281, 60);
		panelValidar.add(botonActualizar_VR);
		try {
			Image img14 = ImageIO.read(getClass().getResource(refresh));
			botonActualizar_VR.setIcon(new ImageIcon(img14.getScaledInstance(
					25, 25, Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}

		// Accion del boton de actualizar para validar
		botonActualizar_VR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Muestra las recetas pendientes de validar
				recetasParaValidar = o.obtenerNoValidadas();
				listaRecetasPendientesValidar_VR.removeAll();
				for (RecetaVO receta : recetasParaValidar) {
					listaRecetasPendientesValidar_VR.add(receta.getNombre());
				}
			}
		});

		// Indicador textual del menu
		final JLabel labelMenu = new JLabel("MEN� PRINCIPAL");
		labelMenu.setForeground(new Color(255, 153, 0));
		labelMenu.setFont(new Font("Bauhaus 93", Font.BOLD, 80));
		labelMenu.setBounds(262, 12, 858, 162);
		menuPrincipal.add(labelMenu);
		IconoApp3.setIcon(new ImageIcon(img7.getScaledInstance(200, 169,
				Image.SCALE_DEFAULT)));
		IconoApp3.setBounds(30, 5, 200, 169);
		menuPrincipal.add(IconoApp3);

		// Botones
		final JButton botonAvanzada = new JButton("B\u00FAsqueda Avanzada");
		final JButton botonPrimeros = new JButton("Primeros");
		final JButton botonPostres = new JButton("Postres");
		final JButton botonSegundos = new JButton("Segundos");
		final JButton botonDestacados = new JButton("M\u00E1s Destacados");
		final JButton botonProponer = new JButton("Proponer Receta");
		final JButton botonAyuda = new JButton("Ayuda");
		final JButton botonImagen = new JButton("");

		// Boton Imagen
		botonImagen.setBounds(12, 5, 238, 169);
		menuPrincipal.add(botonImagen);
		botonImagen.setOpaque(false);
		botonImagen.setContentAreaFilled(false);
		botonImagen.setBorderPainted(false);

		// Boton Busqueda Avanzada
		botonAvanzada.setFont(new Font("Calibri", Font.BOLD, 20));
		botonAvanzada.setBackground(new Color(245, 245, 245));
		botonAvanzada.setBounds(12, 185, 235, 60);
		botonAvanzada.setHorizontalAlignment(SwingConstants.LEFT);
		try {
			Image img8 = ImageIO.read(getClass().getResource(adsearch));
			botonAvanzada.setIcon(new ImageIcon(img8.getScaledInstance(25, 25,
					Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		menuPrincipal.add(botonAvanzada);

		// Accion del boton de Busqueda Avanzada
		botonAvanzada.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("B�SQUEDA AVANZADA");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				botonAvanzada.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelAvanzada");
			}
		});

		// Boton Primeros
		botonPrimeros.setBackground(new Color(245, 245, 245));
		botonPrimeros.setFont(new Font("Calibri", Font.BOLD, 20));
		botonPrimeros.setBounds(12, 245, 235, 60);
		botonPrimeros.setHorizontalAlignment(SwingConstants.LEFT);
		try {
			Image img9 = ImageIO.read(getClass().getResource(one));
			botonPrimeros.setIcon(new ImageIcon(img9.getScaledInstance(25, 25,
					Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		menuPrincipal.add(botonPrimeros);

		// Accion del boton de Primeros
		botonPrimeros.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("PRIMEROS");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				botonPrimeros.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelPrimeros");
			}
		});

		// Boton Postres
		botonPostres.setBackground(new Color(245, 245, 245));
		botonPostres.setFont(new Font("Calibri", Font.BOLD, 20));
		botonPostres.setBounds(12, 365, 235, 60);
		botonPostres.setHorizontalAlignment(SwingConstants.LEFT);
		try {
			Image img10 = ImageIO.read(getClass().getResource(three));
			botonPostres.setIcon(new ImageIcon(img10.getScaledInstance(25, 25,
					Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		menuPrincipal.add(botonPostres);

		// Accion del boton de Postres
		botonPostres.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("POSTRES");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				botonPostres.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelPostres");
			}
		});

		// Boton Segundos
		botonSegundos.setBackground(new Color(245, 245, 245));
		botonSegundos.setFont(new Font("Calibri", Font.BOLD, 20));
		botonSegundos.setBounds(12, 305, 235, 60);
		botonSegundos.setHorizontalAlignment(SwingConstants.LEFT);
		try {
			Image img11 = ImageIO.read(getClass().getResource(two));
			botonSegundos.setIcon(new ImageIcon(img11.getScaledInstance(25, 25,
					Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		menuPrincipal.add(botonSegundos);

		// Accion del boton de Segundos
		botonSegundos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("SEGUNDOS");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				botonSegundos.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelSegundos");
			}
		});

		// Boton Mas Destacados
		botonDestacados.setBackground(new Color(245, 245, 245));
		botonDestacados.setFont(new Font("Calibri", Font.BOLD, 20));
		botonDestacados.setBounds(12, 485, 235, 60);
		botonDestacados.setHorizontalAlignment(SwingConstants.LEFT);
		try {
			Image img12 = ImageIO.read(getClass().getResource(plus));
			botonDestacados.setIcon(new ImageIcon(img12.getScaledInstance(25,
					25, Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		menuPrincipal.add(botonDestacados);

		// Accion del boton de Mas Destacados
		botonDestacados.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("M�S DESTACADOS");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				botonDestacados.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelDestacados");
			}
		});

		// Boton Proponer
		botonProponer.setBackground(new Color(245, 245, 245));
		botonProponer.setFont(new Font("Calibri", Font.BOLD, 20));
		botonProponer.setBounds(12, 425, 235, 60);
		botonProponer.setHorizontalAlignment(SwingConstants.LEFT);
		try {
			Image img13 = ImageIO.read(getClass().getResource(write));
			botonProponer.setIcon(new ImageIcon(img13.getScaledInstance(25, 25,
					Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		menuPrincipal.add(botonProponer);

		// Accion del boton de Proponer Receta
		botonProponer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("PROPONER RECETA");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				botonProponer.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelProponer");
			}
		});

		// Boton Ayuda
		botonAyuda.setBackground(new Color(245, 245, 245));
		botonAyuda.setFont(new Font("Calibri", Font.BOLD, 20));
		botonAyuda.setBounds(12, 545, 235, 60);
		botonAyuda.setHorizontalAlignment(SwingConstants.LEFT);
		try {
			Image img14 = ImageIO.read(getClass().getResource(help));
			botonAyuda.setIcon(new ImageIcon(img14.getScaledInstance(25, 25,
					Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}
		menuPrincipal.add(botonAyuda);

		// Accion del boton de Ayuda
		botonAyuda.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("AYUDA");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				botonAyuda.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelAyuda");
			}
		});

		// Accion del boton Imagen
		botonImagen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("MEN� PRINCIPAL");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				cardMenu.show(pantallaMenu, "panelPrincipal");
			}
		});

		// Accion del boton de validar receta
		botonValidar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("VALIDAR RECETA");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				cardMenu.show(pantallaMenu, "panelValidar");
			}
		});

		// Accion del boton de a�adir receta
		botonAnyadir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("A�ADIR RECETA");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				cardMenu.show(pantallaMenu, "panelAnyadir");
			}
		});

		// Accion del boton de modificar receta
		botonModificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				labelMenu.setText("MODIFICAR RECETA");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
				cardMenu.show(pantallaMenu, "panelModificar");
			}
		});

		// Fondo de la pantalla
		JLabel fondoApp3 = new JLabel();
		Image img6 = new ImageIcon(this.getClass().getResource(fondo))
				.getImage();

		fondoApp3.setIcon(new ImageIcon(img6.getScaledInstance(1132, 611,
				Image.SCALE_DEFAULT)));
		fondoApp3.setBounds(0, 0, 1132, 611);
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
		Image img4 = new ImageIcon(this.getClass().getResource(
				iconoApp)).getImage();
		IconoApp2.setIcon(new ImageIcon(img4.getScaledInstance(319, 288,
				Image.SCALE_DEFAULT)));
		IconoApp2.setBounds(372, 13, 319, 288);
		identiAdmin.add(IconoApp2);

		// M
		JLabel labelTitulo2 = new JLabel("M");
		labelTitulo2.setForeground(new Color(255, 153, 0));
		labelTitulo2
				.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		labelTitulo2.setBounds(312, 371, 37, 86);
		identiAdmin.add(labelTitulo2);

		// y
		JLabel lblY2 = new JLabel("y");
		lblY2.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblY2.setBounds(344, 371, 37, 86);
		identiAdmin.add(lblY2);

		// E
		JLabel lblE2 = new JLabel("E");
		lblE2.setForeground(new Color(255, 153, 0));
		lblE2.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblE2.setBounds(385, 371, 37, 86);
		identiAdmin.add(lblE2);

		// lection
		JLabel lblMy2 = new JLabel("lection");
		lblMy2.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblMy2.setBounds(406, 371, 138, 86);
		identiAdmin.add(lblMy2);

		// F
		JLabel lblF2 = new JLabel("F");
		lblF2.setForeground(new Color(255, 153, 0));
		lblF2.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblF2.setBounds(563, 371, 37, 86);
		identiAdmin.add(lblF2);

		// or
		JLabel lblOr2 = new JLabel("or");
		lblOr2.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblOr2.setBounds(582, 371, 55, 86);
		identiAdmin.add(lblOr2);

		// L
		JLabel lblL2 = new JLabel("L");
		lblL2.setForeground(new Color(255, 153, 0));
		lblL2.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblL2.setBounds(642, 371, 23, 86);
		identiAdmin.add(lblL2);

		// unch
		JLabel lblUnch2 = new JLabel("unch");
		lblUnch2.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38));
		lblUnch2.setBounds(661, 371, 100, 86);
		identiAdmin.add(lblUnch2);

		// Nombre corto
		JLabel lblMel2 = new JLabel("ME4L");
		lblMel2.setFont(new Font("Bauhaus 93", Font.BOLD, 84));
		lblMel2.setBounds(438, 266, 211, 162);
		identiAdmin.add(lblMel2);

		// Boton Entrar como Administrador
		JButton botonAdmin2 = new JButton("Entrar como Administrador");
		botonAdmin2.setBackground(new Color(250, 250, 250));
		botonAdmin2.setFont(new Font("Calibri", Font.BOLD, 24));
		botonAdmin2.setBounds(372, 554, 319, 44);
		identiAdmin.add(botonAdmin2);

		// Accion del boton de Entrar como Administrador
		botonAdmin2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				char[] input = passwordAdmin.getPassword();
				if (isPasswordCorrect(input)) {
					cardLayout.show(contentPane, "menuPrincipal");
					cardMenu.show(pantallaMenu, "panelPrincipal");
					cardAdmin.show(barraAdmin, "estadoIdentificado");
					labelMenu.setText("MEN� PRINCIPAL");
					botonesEnBlanco(botonAvanzada, botonPrimeros,
							botonSegundos, botonPostres, botonDestacados,
							botonProponer, botonAyuda);
				} else {
					// Si la contrase�a no es correcta informamos al usuario
					JOptionPane.showMessageDialog(identiAdmin,
							"Contrase�a INCORRECTA, int�ntelo de nuevo.");
					passwordAdmin.setText("");
				}
			}
		});

		// Texto contrase�a
		JLabel lblIntroduzcaLaContrasea = new JLabel(
				"Introduzca la contrase\u00F1a de Administrador:");
		lblIntroduzcaLaContrasea.setForeground(Color.BLACK);
		lblIntroduzcaLaContrasea.setFont(new Font("Calibri", Font.BOLD, 18));
		lblIntroduzcaLaContrasea.setBounds(370, 457, 365, 23);
		identiAdmin.add(lblIntroduzcaLaContrasea);

		// Campo Password
		passwordAdmin = new JPasswordField(10);
		passwordAdmin.setActionCommand("Entrar como Administrador");
		passwordAdmin.setBounds(372, 493, 319, 37);
		identiAdmin.add(passwordAdmin);

		// Boton de Volver
		JButton botonVolver = new JButton("VOLVER");
		botonVolver.setBackground(new Color(250, 250, 250));
		botonVolver.setFont(new Font("Calibri", Font.BOLD, 18));
		botonVolver.setBounds(100, 516, 144, 51);
		identiAdmin.add(botonVolver);
		try {
			Image img5 = ImageIO.read(getClass().getResource(ret));
			botonVolver.setIcon(new ImageIcon(img5.getScaledInstance(20, 20,
					Image.SCALE_DEFAULT)));
		} catch (IOException ex) {
		}

		// Accion del boton de Volver
		botonVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "menuPrincipal");
				cardMenu.show(pantallaMenu, "panelPrincipal");
				labelMenu.setText("MEN� PRINCIPAL");
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos,
						botonPostres, botonDestacados, botonProponer,
						botonAyuda);
			}
		});

		// Fondo de la pantalla
		JLabel fondoApp2 = new JLabel();
		Image img5 = new ImageIcon(this.getClass().getResource(fondo))
				.getImage();
		fondoApp2.setIcon(new ImageIcon(img5.getScaledInstance(1132, 611,
				Image.SCALE_DEFAULT)));
		fondoApp2.setBounds(0, 0, 1132, 611);
		identiAdmin.add(fondoApp2);

		/**
		 * FINAL DE LA PANTALLA DE IDENTIFICACION DE ADMINISTRADOR
		 **/

	}
}
