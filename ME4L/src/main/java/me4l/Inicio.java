package me4l;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					Aplicacion aplicacion = new Aplicacion();
					aplicacion.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setBounds(100, 100, 1148, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		setResizable(false);

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
		Font f = new Font("Bauhaus 93", Font.BOLD, 84);

		/**
		 * COMIENZO DE LA PANTALLA DE INICIO
		 **/
		// Panel de la pantalla de inicio
		JPanel inicio = new JPanel();
		contentPane.add(inicio, "pantallaInicio");
		inicio.setLayout(null);
		// Nombre abreviado
		JLabel lblMel1 = new JLabel("ME4L");

		if (!f.getFamily().equals("Bauhaus 93")) {
			f = new Font("Dialog.italic", Font.BOLD, 60);
		}
		lblMel1.setFont(f);

		lblMel1.setBounds(438, 266, 211, 162);
		inicio.add(lblMel1);

		// Icono de la Aplicacion
		JLabel IconoApp1 = new JLabel("");
		IconoApp1.setIcon(new ImageIcon(imagenIcono.getScaledInstance(319, 288, Image.SCALE_DEFAULT)));
		IconoApp1.setBounds(372, 13, 319, 288);
		inicio.add(IconoApp1);

		// M
		JLabel labelTitulo1 = new JLabel("M");
		labelTitulo1.setForeground(new Color(255, 153, 0));
		f = new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 38);
		if (!f.getFamily().equals("Bauhaus 93")) {
			f = new Font("Dialog.italic", Font.BOLD, 35);
		}
		labelTitulo1.setFont(f);
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
		lblMy1.setFont(f);
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

		lblL1.setFont(f);
		lblL1.setBounds(642, 371, 23, 86);
		inicio.add(lblL1);

		// unch
		JLabel lblUnch1 = new JLabel("unch");
		lblUnch1.setFont(f);
		lblUnch1.setBounds(661, 371, 100, 86);
		inicio.add(lblUnch1);

		// Fondo de la pantalla
		JLabel fondoApp1 = new JLabel();
		fondoApp1.setIcon(new ImageIcon(imagenFondoApp.getScaledInstance(1132, 611, Image.SCALE_DEFAULT)));
		fondoApp1.setBounds(0, 0, 1132, 611);
		inicio.add(fondoApp1);

		/**
		 * FINAL DE LA PANTALLA DE INICIO
		 **/

	}

}
