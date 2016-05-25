package me4l;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.TextArea;
import javax.swing.JSeparator;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;

public class RecetaAbierta extends JFrame {

	private JPanel contentPane;
	private static RecetaVO receta = new RecetaVO();
	private Operaciones o = new Operaciones();
	private ArrayList<ComentarioVO> coment = new ArrayList<ComentarioVO>();

	/**
	 * Create the frame.
	 */
	public RecetaAbierta(RecetaVO r) {
		receta = r;
		getContentPane().setLayout(new BorderLayout());
		setBounds(700, 100, 792, 800);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setBackground(new Color(126, 198, 217));
		contentPane.setLayout(null);

		Image imagenFondoApp = new ImageIcon("images/fondoApp.jpg").getImage();
		Image imagenValorar = new ImageIcon("images/valorar.png").getImage();
		Image imagenComentarios = new ImageIcon("images/comentarios.png").getImage();
		Image imagenReturn = new ImageIcon("images/return.png").getImage();

		JPanel paneles = new JPanel();
		paneles.setBounds(0, 75, 786, 690);
		contentPane.add(paneles);
		paneles.setOpaque(false);
		paneles.setLayout(new CardLayout(0, 0));

		CardLayout cardPaneles = (CardLayout) paneles.getLayout();

		// Panel muestra datos de receta
		JPanel panelReceta = new JPanel();
		paneles.add(panelReceta, "panelReceta");
		panelReceta.setOpaque(false);
		panelReceta.setLayout(null);

		// Labels indicadores de parametro
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(12, 13, 101, 23);
		panelReceta.add(labelNombre);
		labelNombre.setForeground(Color.WHITE);
		labelNombre.setFont(new Font("Calibri", Font.BOLD, 22));

		JLabel labelPersonas = new JLabel("Personas:");
		labelPersonas.setBounds(12, 57, 101, 23);
		panelReceta.add(labelPersonas);
		labelPersonas.setForeground(Color.WHITE);
		labelPersonas.setFont(new Font("Calibri", Font.BOLD, 22));

		JLabel labelTipo = new JLabel("Tipo:");
		labelTipo.setBounds(12, 101, 57, 23);
		panelReceta.add(labelTipo);
		labelTipo.setForeground(Color.WHITE);
		labelTipo.setFont(new Font("Calibri", Font.BOLD, 22));

		// Nombre de la receta
		JLabel lNombre = new JLabel("");
		lNombre.setBounds(116, 13, 587, 23);
		panelReceta.add(lNombre);
		lNombre.setFont(new Font("Calibri", Font.BOLD, 22));
		lNombre.setForeground(new Color(255, 153, 0));
		lNombre.setText(receta.getNombre());

		// Tipo de la receta
		JLabel lTipo = new JLabel((String) null);
		lTipo.setBounds(116, 101, 125, 23);
		panelReceta.add(lTipo);
		lTipo.setForeground(Color.WHITE);
		lTipo.setForeground(new Color(255, 153, 0));
		lTipo.setText(receta.getPlato());
		lTipo.setFont(new Font("Calibri", Font.BOLD, 22));

		// Numero de personas de la receta
		JLabel lPersonas = new JLabel((String) null);
		lPersonas.setBounds(116, 57, 125, 23);
		panelReceta.add(lPersonas);
		lPersonas.setForeground(Color.WHITE);
		lPersonas.setFont(new Font("Calibri", Font.BOLD, 22));
		lPersonas.setForeground(new Color(255, 153, 0));
		lPersonas.setText(receta.getNumPersonas());

		JSeparator seHorizontal_1 = new JSeparator();
		seHorizontal_1.setBackground(Color.BLACK);
		seHorizontal_1.setForeground(Color.BLACK);
		seHorizontal_1.setBounds(-10, 312, 796, 23);
		panelReceta.add(seHorizontal_1);

		// Boton de valorar
		JButton botonValorar = new JButton("Valorar");
		botonValorar.setBounds(12, 636, 137, 41);
		panelReceta.add(botonValorar);
		botonValorar.setHorizontalAlignment(SwingConstants.CENTER);
		botonValorar.setFont(new Font("Calibri", Font.BOLD, 20));
		botonValorar.setIcon(new ImageIcon(imagenValorar.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		botonValorar.setBackground(new Color(245, 245, 245));

		// Boton seccion de comentarios
		JButton botonComentarios = new JButton("Comentarios");
		botonComentarios.setBounds(161, 636, 178, 41);
		panelReceta.add(botonComentarios);
		botonComentarios.setHorizontalAlignment(SwingConstants.CENTER);
		botonComentarios.setFont(new Font("Calibri", Font.BOLD, 20));
		botonComentarios.setIcon(new ImageIcon(imagenComentarios.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		botonComentarios.setBackground(new Color(245, 245, 245));

		// Text Area de descripcion de la receta
		JTextArea textDescripcion = new JTextArea();
		textDescripcion.setEditable(false);
		textDescripcion.setFont(new Font("Calibri", Font.BOLD, 22));
		textDescripcion.setLineWrap(true);
		textDescripcion.setWrapStyleWord(true);
		JScrollPane scrollPanel = new JScrollPane(textDescripcion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel.setFont(new Font("Calibri", Font.BOLD, 22));
		scrollPanel.setBounds(10, 361, 766, 269);
		panelReceta.add(scrollPanel);
		textDescripcion.setText(receta.getDescripcion());

		JLabel labelDescipcion = new JLabel("Descripción:");
		labelDescipcion.setForeground(Color.WHITE);
		labelDescipcion.setFont(new Font("Calibri", Font.BOLD, 22));
		labelDescipcion.setBounds(12, 332, 299, 23);
		panelReceta.add(labelDescipcion);

		JLabel labelPrincipal = new JLabel("Ingrediente pricipal: ");
		labelPrincipal.setForeground(Color.WHITE);
		labelPrincipal.setFont(new Font("Calibri", Font.BOLD, 22));
		labelPrincipal.setBounds(12, 172, 200, 23);
		panelReceta.add(labelPrincipal);

		JLabel lPrincipal = new JLabel((String) null);
		lPrincipal.setFont(new Font("Calibri", Font.BOLD, 22));
		lPrincipal.setBounds(201, 172, 178, 23);
		lPrincipal.setForeground(new Color(255, 153, 0));
		lPrincipal.setText(receta.getIngredientes().get(0));
		panelReceta.add(lPrincipal);

		JLabel labelCantidadP = new JLabel("Cantidad:");
		labelCantidadP.setForeground(Color.WHITE);
		labelCantidadP.setFont(new Font("Calibri", Font.BOLD, 22));
		labelCantidadP.setBounds(110, 223, 113, 23);
		panelReceta.add(labelCantidadP);

		JLabel lCantidadP = new JLabel((String) null);
		lCantidadP.setForeground(Color.WHITE);
		lCantidadP.setFont(new Font("Calibri", Font.BOLD, 22));
		lCantidadP.setForeground(new Color(255, 153, 0));
		lCantidadP.setBounds(201, 223, 67, 23);
		lCantidadP.setText(receta.getPesoIngredientes().get(0));
		panelReceta.add(lCantidadP);

		JLabel lblGramos = new JLabel("");
		lblGramos.setForeground(Color.WHITE);
		lblGramos.setFont(new Font("Calibri", Font.BOLD, 22));
		lblGramos.setBounds(270, 223, 109, 23);
		panelReceta.add(lblGramos);
		lblGramos.setText(receta.getUnidad().get(0));

		JSeparator seVertical = new JSeparator();
		seVertical.setOrientation(SwingConstants.VERTICAL);
		seVertical.setForeground(Color.BLACK);
		seVertical.setBackground(Color.BLACK);
		seVertical.setBounds(379, 49, 17, 263);
		panelReceta.add(seVertical);

		JSeparator seHorizontal_2 = new JSeparator();
		seHorizontal_2.setForeground(Color.BLACK);
		seHorizontal_2.setBackground(Color.BLACK);
		seHorizontal_2.setBounds(0, 149, 379, 23);
		panelReceta.add(seHorizontal_2);

		JLabel lblIngredientes = new JLabel("Ingredientes:");
		lblIngredientes.setForeground(Color.WHITE);
		lblIngredientes.setFont(new Font("Calibri", Font.BOLD, 22));
		lblIngredientes.setBounds(389, 57, 272, 23);
		panelReceta.add(lblIngredientes);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(379, 48, 407, 23);
		panelReceta.add(separator);

		// Lista de ingredientes
		List listaIngredientes = new List();
		listaIngredientes.setMultipleMode(false);
		listaIngredientes.setFont(new Font("Calibri", Font.BOLD, 15));
		listaIngredientes.setBounds(391, 88, 383, 218);
		for (int i = 1; i < receta.getIngredientes().size(); i++) {
			listaIngredientes.add(receta.getIngredientes().get(i) + " - " + receta.getPesoIngredientes().get(i) + " - "
					+ receta.getUnidad().get(i));
		}
		panelReceta.add(listaIngredientes);

		// Boton de reportar la receta
		JButton botonReportar = new JButton("Reportar Receta");
		botonReportar.setHorizontalAlignment(SwingConstants.CENTER);
		botonReportar.setFont(new Font("Calibri", Font.BOLD, 20));
		botonReportar.setBackground(new Color(255, 127, 80));
		botonReportar.setBounds(584, 636, 190, 41);
		panelReceta.add(botonReportar);

		JPanel panelComentarios = new JPanel();
		paneles.add(panelComentarios, "panelComentarios");
		panelComentarios.setOpaque(false);
		panelComentarios.setLayout(null);

		JTextArea textAreaNuevo = new JTextArea();
		textAreaNuevo.setFont(new Font("Calibri", Font.BOLD, 22));
		textAreaNuevo.setWrapStyleWord(true);
		textAreaNuevo.setLineWrap(true);
		JScrollPane scrollPanel_N = new JScrollPane(textAreaNuevo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel_N.setFont(new Font("Calibri", Font.BOLD, 22));
		scrollPanel_N.setBounds(12, 485, 518, 192);
		panelComentarios.add(scrollPanel_N);

		JButton botonAnyadir = new JButton("Añadir Comentario");
		botonAnyadir.setHorizontalAlignment(SwingConstants.CENTER);
		botonAnyadir.setFont(new Font("Calibri", Font.BOLD, 20));
		botonAnyadir.setBackground(new Color(245, 245, 245));
		botonAnyadir.setBounds(537, 485, 241, 74);
		panelComentarios.add(botonAnyadir);

		JTextArea textAreaComentarios = new JTextArea();
		textAreaComentarios.setFont(new Font("Calibri", Font.BOLD, 22));
		textAreaComentarios.setEditable(false);
		textAreaComentarios.setWrapStyleWord(true);
		textAreaComentarios.setLineWrap(true);
		JScrollPane scrollPanel_C = new JScrollPane(textAreaComentarios, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel_C.setFont(new Font("Calibri", Font.BOLD, 22));
		scrollPanel_C.setBounds(12, 13, 766, 459);
		panelComentarios.add(scrollPanel_C);

		// Boton voler
		JButton botonVolver = new JButton("Volver");
		botonVolver.setFont(new Font("Calibri", Font.BOLD, 23));
		botonVolver.setBackground(new Color(245, 245, 245));
		botonVolver.setBounds(550, 617, 215, 60);
		botonVolver.setIcon(new ImageIcon(imagenReturn.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		panelComentarios.add(botonVolver);

		// Accion del boton de volver
		botonVolver.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				cardPaneles.show(paneles, "panelReceta");
				textAreaNuevo.setText("");
				textAreaComentarios.setText("");
			}
		});

		// Accion del boton Comentarios
		botonComentarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				cardPaneles.show(paneles, "panelComentarios");
				coment = o.comentariosReceta(receta.getId());
				if (coment.size() != 0) {
					textAreaComentarios.setText("");
					for (ComentarioVO comentario : coment) {
						textAreaComentarios.append(comentario.getContenido());
						textAreaComentarios.append(
								"----------------------------------------------------------------------------------------------------------\n");
					}
				}

			}
		});

		// Accion del boton de añadir comentario
		botonAnyadir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Date date = Calendar.getInstance().getTime();
				if (!textAreaNuevo.getText().equals("")) {
					o.addComentario(receta.getId(), textAreaNuevo.getText() + " - " + date.toString() + "\n");
					textAreaNuevo.setText("");
					textAreaComentarios.setText("");
					coment = o.comentariosReceta(receta.getId());
					for (ComentarioVO comentario : coment) {
						textAreaComentarios.append(comentario.getContenido());
						textAreaComentarios.append(
								"----------------------------------------------------------------------------------------------------------\n");
					}
				}
			}
		});

		// Indicador textual de RECETA
		final JLabel labelMenu = new JLabel("RECETA");
		labelMenu.setForeground(new Color(255, 153, 0));
		labelMenu.setFont(new Font("Bauhaus 93", Font.BOLD, 80));
		labelMenu.setBounds(12, -15, 389, 105);
		contentPane.add(labelMenu);

		JLabel labelFondo = new JLabel("");
		labelFondo.setBackground(Color.WHITE);
		labelFondo.setBounds(0, 0, 792, 765);
		contentPane.add(labelFondo);
		labelFondo.setIcon(new ImageIcon(imagenFondoApp.getScaledInstance(792, 800, Image.SCALE_DEFAULT)));
	}
}
