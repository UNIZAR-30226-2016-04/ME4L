package me4l;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Inicio frame = new Inicio();
		frame.setIconImage(new ImageIcon("images/IconoAppRecortado.png").getImage());
		frame.setVisible(true);
		Aplicacion app = new Aplicacion();
		frame.setVisible(false);
		app.setVisible(true);	
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		getContentPane().setLayout(new BorderLayout());
		setBounds(700, 100, 550, 700);
		setUndecorated(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setBackground(new Color(126, 198, 217));
		contentPane.setLayout(null);
		
		Image imagenIcono = new ImageIcon("images/IconoAppRecortado.png").getImage();
		Image imagenFondoApp = new ImageIcon("images/fondoApp.jpg").getImage();
		
		JLabel labelIcono = new JLabel("");
		labelIcono.setBounds(72, 76, 412, 356);
		contentPane.add(labelIcono);
		labelIcono.setIcon(new ImageIcon(imagenIcono.getScaledInstance(412, 356, Image.SCALE_DEFAULT)));
		
		JLabel labelLoading = new JLabel("Cargando...");
		labelLoading.setForeground(Color.WHITE);
		labelLoading.setFont(new Font("Bauhaus 93", Font.PLAIN, 60));
		labelLoading.setBounds(115, 515, 362, 106);
		contentPane.add(labelLoading);
		
		JLabel labelFondo = new JLabel("");
		labelFondo.setBackground(Color.WHITE);
		labelFondo.setBounds(0, 0, 550, 700);
		contentPane.add(labelFondo);	
		labelFondo.setIcon(new ImageIcon(imagenFondoApp.getScaledInstance(550, 700, Image.SCALE_DEFAULT)));	

	}
}
