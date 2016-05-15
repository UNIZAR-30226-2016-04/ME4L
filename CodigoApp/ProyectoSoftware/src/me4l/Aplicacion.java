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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

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
	// Array/cache que guarda las recetas que el usuario tiene abiertas
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
	private boolean identificado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion frame = new Aplicacion();
					frame.setIconImage(new ImageIcon("images/IconoAppRecortado.png").getImage());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	/**
	 * Create the frame.
	 * 
	 * @param frame
	 */
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

		Font f = new Font("Bauhaus 93", Font.BOLD, 84);

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
		comboBoxNumPersonas_PR.setModel(new DefaultComboBoxModel(
				new String[] { "-- Seleccione el n\u00FAmero de personas --", "1", "2", "4", "8" }));
		comboBoxNumPersonas_PR.setFont(f1);
		panelProponer.add(comboBoxNumPersonas_PR);
		comboBoxNumPersonas_PR.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_PR = new JComboBox();
		comboBoxIngrediente_PR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_PR.setFont(f1);
		comboBoxIngrediente_PR.setBounds(631, 124, 204, 22);
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
		textFieldNombre_PR.setToolTipText("Introduzca el nombre de la receta.");
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
		JLabel labelNumPersonas_PR = new JLabel("N\u00FAmero de personas:");
		labelNumPersonas_PR.setBounds(0, 48, 174, 23);
		labelNumPersonas_PR.setForeground(Color.WHITE);
		labelNumPersonas_PR.setFont(f);
		panelProponer.add(labelNumPersonas_PR);

		// Label de la URL de la imagen
		JLabel labelTipo_PR = new JLabel("Tipo de plato:");
		labelTipo_PR.setBounds(0, 84, 174, 23);
		labelTipo_PR.setForeground(Color.WHITE);
		labelTipo_PR.setFont(f);
		panelProponer.add(labelTipo_PR);

		// Label descripcion de la receta
		JLabel labelDescripcion_PR = new JLabel("Descripci\u00F3n de la receta:");
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
		separadorVertical_PR.setBounds(455, 0, 12, 421);
		panelProponer.add(separadorVertical_PR);

		// Separador horizontal
		JSeparator separadorHorizontal_PR = new JSeparator();
		separadorHorizontal_PR.setBounds(455, 83, 403, 12);
		panelProponer.add(separadorHorizontal_PR);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_PR = new JLabel("Cantidad:");
		labelCantidadPrinc_PR.setForeground(Color.WHITE);
		labelCantidadPrinc_PR.setFont(f);
		labelCantidadPrinc_PR.setBounds(553, 48, 91, 23);
		panelProponer.add(labelCantidadPrinc_PR);

		// Label gramos
		JLabel labelGramos_PR = new JLabel("gramos");
		labelGramos_PR.setForeground(Color.WHITE);
		labelGramos_PR.setFont(f);
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
		textFieldCantidadPrinc_PR.setFont(f1);
		textFieldCantidadPrinc_PR.setBounds(631, 48, 137, 22);
		panelProponer.add(textFieldCantidadPrinc_PR);
		textFieldCantidadPrinc_PR.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final TextArea textAreaDescripcion_PR = new TextArea();
		textAreaDescripcion_PR.setFont(f1);
		textAreaDescripcion_PR.setBounds(0, 152, 440, 269);
		panelProponer.add(textAreaDescripcion_PR);

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
		labelIngrediente_PR.setBounds(535, 123, 117, 23);
		panelProponer.add(labelIngrediente_PR);

		// Label cantidad del ingrediente
		JLabel labelCantidad_PR = new JLabel("Cantidad:");
		labelCantidad_PR.setForeground(Color.WHITE);
		labelCantidad_PR.setFont(f);
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
		textFieldCantidad_PR.setFont(f1);
		textFieldCantidad_PR.setColumns(10);
		textFieldCantidad_PR.setBounds(631, 160, 137, 22);
		panelProponer.add(textFieldCantidad_PR);

		// Label gramos 2
		JLabel labelGramos2_PR = new JLabel("gramos");
		labelGramos2_PR.setForeground(Color.WHITE);
		labelGramos2_PR.setFont(f);
		labelGramos2_PR.setBounds(780, 160, 66, 23);
		panelProponer.add(labelGramos2_PR);

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
				new String[] { "-- Seleccione el tipo de plato --", "Primero", "Segundo", "Postre" }));
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

		// Accion del boton de cancelar receta

		botonCancelarReceta_PR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				textFieldNombre_PR.setText("");
				comboBoxNumPersonas_PR.setSelectedIndex(0);
				textAreaDescripcion_PR.setText("");
				comboBoxIngPrinc_PR.setSelectedIndex(0);
				textFieldCantidadPrinc_PR.setText("");
				comboBoxIngrediente_PR.setSelectedIndex(0);
				comboBoxTipo_PR.setSelectedIndex(0);
				textFieldCantidad_PR.setText("");
				listaIngredientes_PR.removeAll();
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

		// Accion del boton de aÃ±adir ingrediente
		botonAnyadirIngrediente_PR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if ((comboBoxIngrediente_PR.getSelectedIndex() != 0) && (!textFieldCantidad_PR.getText().equals("")
						&& Integer.parseInt(textFieldCantidad_PR.getText()) > 0)) {
					if (comboBoxIngPrinc_PR.getSelectedIndex() == comboBoxIngrediente_PR.getSelectedIndex()) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_PR.contains(comboBoxIngrediente_PR.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_PR
								.add(comboBoxIngrediente_PR.getSelectedItem() + " - " + textFieldCantidad_PR.getText());
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
						&& !textFieldCantidadPrinc_PR.getText().equals("") && comboBoxTipo_PR.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_PR.getText().length() > 4
							|| textFieldCantidad_PR.getText().length() > 4) {
						JOptionPane.showMessageDialog(menuPrincipal, "Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes = new ArrayList<String>();
						ArrayList<String> pesoIngredientes = new ArrayList<String>();
						String[] s = listaIngredientes_PR.getItems();

						ingredientes.add(comboBoxIngPrinc_PR.getSelectedItem().toString());
						pesoIngredientes.add(textFieldCantidadPrinc_PR.getText());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0, elemento.indexOf('-') - 1);
							String cantidad = elemento.substring(elemento.indexOf('-') + 1, elemento.length());
							ingredientes.add(ingrediente);
							pesoIngredientes.add(cantidad);
						}

						o.addReceta(textFieldNombre_PR.getText(), textAreaDescripcion_PR.getText(),
								comboBoxTipo_PR.getSelectedItem().toString(),
								comboBoxNumPersonas_PR.getSelectedItem().toString(), ingredientes, pesoIngredientes);

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
		final JButton botonAvanzada = new JButton("Búsqueda Avanzada");
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

		botonAvanzada.setFont(f);
		botonAvanzada.setBackground(new Color(245, 245, 245));
		botonAvanzada.setBounds(12, 193, 235, 60);
		botonAvanzada.setHorizontalAlignment(SwingConstants.LEFT);
		botonAvanzada.setIcon(new ImageIcon(imagenAdSearch.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));

		menuPrincipal.add(botonAvanzada);

		// Accion del boton de Busqueda Avanzada
		botonAvanzada.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				labelMenu.setText("BÚSQUEDA AVANZADA");
				Font f = new Font("Bauhaus 93", Font.BOLD, 70);
				if (!f.getFamily().equals("Bauhaus 93")) {
					f = new Font("Dialog.Italic", Font.BOLD, 65);
				}
				labelMenu.setFont(f);
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonAvanzada.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelAvanzada");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonAvanzada.getBackground().equals(new Color(255, 153, 0))) {
					botonAvanzada.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonAvanzada.getBackground().equals(new Color(255, 153, 0))) {
					botonAvanzada.setBackground(new Color(245, 245, 245));
				}
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonPrimeros.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelPrimeros");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonPrimeros.getBackground().equals(new Color(255, 153, 0))) {
					botonPrimeros.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonPrimeros.getBackground().equals(new Color(255, 153, 0))) {
					botonPrimeros.setBackground(new Color(245, 245, 245));
				}
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonPostres.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelPostres");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonPostres.getBackground().equals(new Color(255, 153, 0))) {
					botonPostres.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonPostres.getBackground().equals(new Color(255, 153, 0))) {
					botonPostres.setBackground(new Color(245, 245, 245));
				}
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonSegundos.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelSegundos");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonSegundos.getBackground().equals(new Color(255, 153, 0))) {
					botonSegundos.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonSegundos.getBackground().equals(new Color(255, 153, 0))) {
					botonSegundos.setBackground(new Color(245, 245, 245));
				}
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonDestacados.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelDestacados");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonDestacados.getBackground().equals(new Color(255, 153, 0))) {
					botonDestacados.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonDestacados.getBackground().equals(new Color(255, 153, 0))) {
					botonDestacados.setBackground(new Color(245, 245, 245));
				}
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonProponer.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelProponer");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonProponer.getBackground().equals(new Color(255, 153, 0))) {
					botonProponer.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonProponer.getBackground().equals(new Color(255, 153, 0))) {
					botonProponer.setBackground(new Color(245, 245, 245));
				}
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
		comboBoxNumPersonas_A.setModel(new DefaultComboBoxModel(
				new String[] { "-- Seleccione el n\u00FAmero de personas --", "1", "2", "4", "8" }));
		comboBoxNumPersonas_A.setFont(f1);
		panelAnyadir.add(comboBoxNumPersonas_A);
		comboBoxNumPersonas_A.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_A = new JComboBox();
		comboBoxIngrediente_A.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
		comboBoxIngrediente_A.setFont(f1);
		comboBoxIngrediente_A.setBounds(631, 124, 204, 22);
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
		comboBoxIngPrinc_A.setBounds(631, 13, 204, 22);
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
		separadorVertical_A.setBounds(455, 0, 12, 421);
		panelAnyadir.add(separadorVertical_A);

		// Separador horizontal
		JSeparator separadorHorizontal_A = new JSeparator();
		separadorHorizontal_A.setBounds(455, 83, 403, 12);
		panelAnyadir.add(separadorHorizontal_A);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_A = new JLabel("Cantidad:");
		labelCantidadPrinc_A.setForeground(Color.WHITE);
		labelCantidadPrinc_A.setFont(f);
		labelCantidadPrinc_A.setBounds(553, 48, 91, 23);
		panelAnyadir.add(labelCantidadPrinc_A);

		// Label gramos
		JLabel labelGramos_A = new JLabel("gramos");
		labelGramos_A.setForeground(Color.WHITE);
		labelGramos_A.setFont(f);
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
		textFieldCantidadPrinc_A.setFont(f1);
		textFieldCantidadPrinc_A.setBounds(631, 48, 137, 22);
		panelAnyadir.add(textFieldCantidadPrinc_A);
		textFieldCantidadPrinc_A.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final TextArea textAreaDescripcion_A = new TextArea();
		textAreaDescripcion_A.setFont(f1);
		textAreaDescripcion_A.setBounds(0, 152, 440, 236);
		panelAnyadir.add(textAreaDescripcion_A);

		// Boton de enviar la receta propuesta
		JButton botonEnviarPropuesta_A = new JButton("A\u00D1ADIR RECETA");
		botonEnviarPropuesta_A.setFont(f2);
		botonEnviarPropuesta_A.setBackground(new Color(173, 255, 47));
		botonEnviarPropuesta_A.setBounds(84, 394, 281, 60);
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
		labelIngrediente_A.setBounds(535, 123, 117, 23);
		panelAnyadir.add(labelIngrediente_A);

		// Label cantidad del ingrediente
		JLabel labelCantidad_A = new JLabel("Cantidad:");
		labelCantidad_A.setForeground(Color.WHITE);
		labelCantidad_A.setFont(f);
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
		textFieldCantidad_A.setFont(f1);
		textFieldCantidad_A.setColumns(10);
		textFieldCantidad_A.setBounds(631, 160, 137, 22);
		panelAnyadir.add(textFieldCantidad_A);

		// Label gramos 2
		JLabel labelGramos2_A = new JLabel("gramos");
		labelGramos2_A.setForeground(Color.WHITE);
		labelGramos2_A.setFont(f);
		labelGramos2_A.setBounds(780, 160, 66, 23);
		panelAnyadir.add(labelGramos2_A);

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
		labelAsterisco4_A.setBounds(839, 15, 19, 16);
		panelAnyadir.add(labelAsterisco4_A);

		JLabel labelAsterisco5_A = new JLabel("*");
		labelAsterisco5_A.setForeground(Color.RED);
		labelAsterisco5_A.setFont(f);
		labelAsterisco5_A.setBounds(839, 50, 19, 16);
		panelAnyadir.add(labelAsterisco5_A);

		JLabel labelCampoObligatorio_A = new JLabel("* Campo obligatorio");
		labelCampoObligatorio_A.setForeground(Color.RED);
		labelCampoObligatorio_A.setFont(f);
		labelCampoObligatorio_A.setBounds(0, 467, 174, 16);
		panelAnyadir.add(labelCampoObligatorio_A);

		// ComboBox del tipo de plato
		final JComboBox comboBoxTipo_A = new JComboBox();
		comboBoxTipo_A.setModel(new DefaultComboBoxModel(
				new String[] { "-- Seleccione el tipo de plato --", "Primero", "Segundo", "Postre" }));
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
				if ((comboBoxIngrediente_A.getSelectedIndex() != 0) && (!textFieldCantidad_A.getText().equals("")
						&& Integer.parseInt(textFieldCantidad_A.getText()) > 0)) {
					if (comboBoxIngPrinc_A.getSelectedIndex() == comboBoxIngrediente_A.getSelectedIndex()) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_A.contains(comboBoxIngrediente_A.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_A
								.add(comboBoxIngrediente_A.getSelectedItem() + " - " + textFieldCantidad_A.getText());
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
						&& !textFieldCantidadPrinc_A.getText().equals("") && comboBoxTipo_A.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_A.getText().length() > 4 || textFieldCantidad_A.getText().length() > 4) {
						JOptionPane.showMessageDialog(menuPrincipal, "Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes_A = new ArrayList<String>();
						ArrayList<String> pesoIngredientes_A = new ArrayList<String>();
						String[] s = listaIngredientes_A.getItems();

						ingredientes_A.add(comboBoxIngPrinc_A.getSelectedItem().toString());
						pesoIngredientes_A.add(textFieldCantidadPrinc_A.getText());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0, elemento.indexOf('-') - 1);
							String cantidad = elemento.substring(elemento.indexOf('-') + 1, elemento.length());
							ingredientes_A.add(ingrediente);
							pesoIngredientes_A.add(cantidad);
						}

						o.addRecetaV(textFieldNombre_A.getText(), textAreaDescripcion_A.getText(),
								comboBoxTipo_A.getSelectedItem().toString(),
								comboBoxNumPersonas_A.getSelectedItem().toString(), ingredientes_A, pesoIngredientes_A);

						JOptionPane.showMessageDialog(menuPrincipal, "La receta ha sido añadida.");
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
					JOptionPane.showMessageDialog(menuPrincipal,
							"Uno o mas campos obligatorios no estan rellenados correctamente.");
				}
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
		JButton botonBuscar_MR = new JButton("");
		botonBuscar_MR.setFont(f);
		botonBuscar_MR.setBackground(new Color(245, 245, 245));
		botonBuscar_MR.setBounds(626, 42, 46, 34);
		botonBuscar_MR.setIcon(new ImageIcon(imagenSearch.getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		panelModificar.add(botonBuscar_MR);

		// Accion del boton de buscar recetas para modificar
		botonBuscar_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				listaBuscada_MR.removeAll();
				recetasModificar = o.obtenerValidadas();
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
		comboBoxNumPersonas_MR.setModel(new DefaultComboBoxModel(
				new String[] { "-- Seleccione el n\u00FAmero de personas --", "1", "2", "4", "8" }));
		comboBoxNumPersonas_MR.setFont(new Font("Calibri", Font.BOLD, 15));
		panelModificarReceta.add(comboBoxNumPersonas_MR);
		comboBoxNumPersonas_MR.setLightWeightPopupEnabled(false);

		// ComboBox seleccionar ingredientes
		final JComboBox comboBoxIngrediente_MR = new JComboBox();
		comboBoxIngrediente_MR.setModel(new DefaultComboBoxModel(new String[] { "- Seleccione el ingrediente -" }));
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
		separadorVertical_MR.setBounds(455, 0, 12, 421);
		panelModificarReceta.add(separadorVertical_MR);

		// Separador horizontal
		JSeparator separadorHorizontal_MR = new JSeparator();
		separadorHorizontal_MR.setBounds(455, 83, 403, 12);
		panelModificarReceta.add(separadorHorizontal_MR);

		// Label cantidad del ingrediente principal
		JLabel labelCantidadPrinc_MR = new JLabel("Cantidad:");
		labelCantidadPrinc_MR.setForeground(Color.WHITE);
		labelCantidadPrinc_MR.setFont(f);
		labelCantidadPrinc_MR.setBounds(553, 48, 91, 23);
		panelModificarReceta.add(labelCantidadPrinc_MR);

		// Label gramos
		JLabel labelGramos_MR = new JLabel("gramos");
		labelGramos_MR.setForeground(Color.WHITE);
		labelGramos_MR.setFont(f);
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
		textFieldCantidadPrinc_MR.setFont(f);
		textFieldCantidadPrinc_MR.setBounds(631, 48, 137, 22);
		panelModificarReceta.add(textFieldCantidadPrinc_MR);
		textFieldCantidadPrinc_MR.setColumns(10);

		// TextArea de la descripcion libre de la receta
		final TextArea textAreaDescripcion_MR = new TextArea();
		textAreaDescripcion_MR.setFont(f);
		textAreaDescripcion_MR.setBounds(0, 152, 440, 237);
		panelModificarReceta.add(textAreaDescripcion_MR);

		// LLenado del comboBox
		ingredientesAplicacion = o.obtenerIngredientes();
		for (int i = 0; i < ingredientesAplicacion.size(); i++) {
			comboBoxIngrediente_MR.addItem(ingredientesAplicacion.get(i));
		}

		// Label ingrediente
		JLabel labelIngrediente_MR = new JLabel("Ingrediente:");
		labelIngrediente_MR.setForeground(Color.WHITE);
		labelIngrediente_MR.setFont(f);
		labelIngrediente_MR.setBounds(535, 123, 117, 23);
		panelModificarReceta.add(labelIngrediente_MR);

		// Label cantidad del ingrediente
		JLabel labelCantidad_MR = new JLabel("Cantidad:");
		labelCantidad_MR.setForeground(Color.WHITE);
		labelCantidad_MR.setFont(f);
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
		textFieldCantidad_MR.setFont(f);
		textFieldCantidad_MR.setColumns(10);
		textFieldCantidad_MR.setBounds(631, 160, 137, 22);
		panelModificarReceta.add(textFieldCantidad_MR);

		// Label gramos 2
		JLabel labelGramos2_MR = new JLabel("gramos");
		labelGramos2_MR.setForeground(Color.WHITE);
		labelGramos2_MR.setFont(f);
		labelGramos2_MR.setBounds(780, 160, 66, 23);
		panelModificarReceta.add(labelGramos2_MR);

		// Boton añadir ingrediente
		JButton botonAnyadirIngrediente_MR = new JButton("AÃ±adir Ingrediente");
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
				new String[] { "-- Seleccione el tipo de plato --", "Primero", "Segundo", "Postre" }));
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

		// Accion del boton de aÃ±adir ingrediente
		botonAnyadirIngrediente_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if ((comboBoxIngrediente_MR.getSelectedIndex() != 0) && (!textFieldCantidad_MR.getText().equals("")
						&& Integer.parseInt(textFieldCantidad_MR.getText()) > 0)) {
					if (comboBoxIngPrinc_MR.getSelectedIndex() == comboBoxIngrediente_MR.getSelectedIndex()) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente principal no puede volver a aparecer en la lista de ingredientes.");
					} else if (indexIngredientes_MR.contains(comboBoxIngrediente_MR.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(menuPrincipal,
								"El ingrediente ya esta en la lista de ingredientes.");
					} else {
						listaIngredientes_MR
								.add(comboBoxIngrediente_MR.getSelectedItem() + " - " + textFieldCantidad_MR.getText());
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
						&& !textFieldCantidadPrinc_MR.getText().equals("") && comboBoxTipo_MR.getSelectedIndex() != 0) {
					if (textFieldCantidadPrinc_MR.getText().length() > 4
							|| textFieldCantidad_MR.getText().length() > 4) {
						JOptionPane.showMessageDialog(menuPrincipal, "Las cantidades no pueden superar las 4 cifras.");
					} else {
						ArrayList<String> ingredientes = new ArrayList<String>();
						ArrayList<String> pesoIngredientes = new ArrayList<String>();
						String[] s = listaIngredientes_MR.getItems();

						ingredientes.add(comboBoxIngPrinc_MR.getSelectedItem().toString());
						pesoIngredientes.add(textFieldCantidadPrinc_MR.getText());

						for (int i = 0; i < s.length; i++) {
							String elemento = s[i];
							String ingrediente = elemento.substring(0, elemento.indexOf('-') - 1);
							String cantidad = elemento.substring(elemento.indexOf('-') + 1, elemento.length());
							ingredientes.add(ingrediente);
							pesoIngredientes.add(cantidad);
						}

						o.modificarReceta(textFieldNombre_MR.getText(), textAreaDescripcion_MR.getText(),
								comboBoxTipo_MR.getSelectedItem().toString(),
								comboBoxNumPersonas_MR.getSelectedItem().toString(), ingredientes, pesoIngredientes,
								recetaSeleccionada_MR.getId());

						// Recargamos las recetas buscadas para modificar
						listaBuscada_MR.removeAll();
						recetasModificar = o.obtenerValidadas();
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
						cardMenu.show(pantallaMenu, "panelModificar");
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
				cardMenu.show(pantallaMenu, "panelModificar");
				recetaSeleccionada_MR = null;
			}
		});

		// Accion del boton de modificar la receta seleccionada
		botonModificar_MR.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				if (listaBuscada_MR.getItemCount() > 0 && listaBuscada_MR.getSelectedItem() != null) {
					recetaSeleccionada_MR = recetasModificar.get(listaBuscada_MR.getSelectedIndex());
					cardMenu.show(pantallaMenu, "panelModificarReceta");
					// Puesta de valores iniciales en los campos
					textFieldNombre_MR.setText(recetaSeleccionada_MR.getNombre());
					comboBoxNumPersonas_MR.setSelectedItem(recetaSeleccionada_MR.getNumPersonas());
					textAreaDescripcion_MR.setText(recetaSeleccionada_MR.getDescripcion());
					comboBoxIngPrinc_MR.setSelectedItem(recetaSeleccionada_MR.getIngredientes().get(0));
					textFieldCantidadPrinc_MR.setText(recetaSeleccionada_MR.getPesoIngredientes().get(0));
					for (int i = 1; i < recetaSeleccionada_MR.getIngredientes().size(); i++) {
						listaIngredientes_MR.add(recetaSeleccionada_MR.getIngredientes().get(i) + " - "
								+ recetaSeleccionada_MR.getPesoIngredientes().get(i));
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonValidar.setBackground(new Color(255, 153, 0));
				cardAdmin.show(panelAdminCard, "panelValidar");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonValidar.getBackground().equals(new Color(255, 153, 0))) {
					botonValidar.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonValidar.getBackground().equals(new Color(255, 153, 0))) {
					botonValidar.setBackground(new Color(245, 245, 245));
				}
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonAnyadir.setBackground(new Color(255, 153, 0));
				cardAdmin.show(panelAdminCard, "panelAnyadir");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonAnyadir.getBackground().equals(new Color(255, 153, 0))) {
					botonAnyadir.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonAnyadir.getBackground().equals(new Color(255, 153, 0))) {
					botonAnyadir.setBackground(new Color(245, 245, 245));
				}
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonModificar.setBackground(new Color(255, 153, 0));
				cardAdmin.show(panelAdminCard, "panelModificar");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonModificar.getBackground().equals(new Color(255, 153, 0))) {
					botonModificar.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonModificar.getBackground().equals(new Color(255, 153, 0))) {
					botonModificar.setBackground(new Color(245, 245, 245));
				}
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
						botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
				botonAyuda.setBackground(new Color(255, 153, 0));
				cardMenu.show(pantallaMenu, "panelAyuda");
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonAyuda.getBackground().equals(new Color(255, 153, 0))) {
					botonAyuda.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonAyuda.getBackground().equals(new Color(255, 153, 0))) {
					botonAyuda.setBackground(new Color(245, 245, 245));
				}
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
					botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
							botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
					botonAdministrador.setBackground(new Color(255, 153, 0));
					cardMenu.show(pantallaMenu, "panelAdministrador");
					cardAdmin.show(panelAdminCard, "panelAdmin");
					int noValidadas = o.obtenerNoValidadas().size();
					labelInfoAdmin.setText("Hay " + noValidadas + " recetas pendientes de ser validadas.");
				}
			}

			public void mouseEntered(MouseEvent arg0) {
				if (!botonAdministrador.getBackground().equals(new Color(255, 153, 0))) {
					botonAdministrador.setBackground(new Color(215, 215, 215));
				}
			}

			public void mouseExited(MouseEvent arg0) {
				if (!botonAdministrador.getBackground().equals(new Color(255, 153, 0))) {
					botonAdministrador.setBackground(new Color(245, 245, 245));
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
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
					cardMenu.show(pantallaMenu, "panelPrincipal");
					labelMenu.setText("MENÚ PRINCIPAL");
					Font f = new Font("Bauhaus 93", Font.BOLD, 70);
					if (!f.getFamily().equals("Bauhaus 93")) {
						f = new Font("Dialog.Italic", Font.BOLD, 65);
					}
					labelMenu.setFont(f);
					botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
							botonProponer, botonAyuda, botonAdministrador, botonValidar, botonAnyadir, botonModificar);
					identificado = true;
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
				botonesEnBlanco(botonAvanzada, botonPrimeros, botonSegundos, botonPostres, botonDestacados,
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
