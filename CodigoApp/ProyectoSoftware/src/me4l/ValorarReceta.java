package me4l;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.omg.CORBA.portable.InputStream;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

public class ValorarReceta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Operaciones o = new Operaciones();

	// Metodo que devuelve la IP del dispositivo
	public static String getIP() {
		String publicIP = "";
		try {
			URL tempURL = new URL("http://www.whatismyip.org/");
			HttpURLConnection tempConn = (HttpURLConnection) tempURL.openConnection();
			java.io.InputStream tempInStream = tempConn.getInputStream();
			InputStreamReader tempIsr = new InputStreamReader(tempInStream);
			BufferedReader tempBr = new BufferedReader(tempIsr);

			for (int i = 0; i < 41; i++) {
				tempBr.readLine();
			}
			String linea = tempBr.readLine();
			int inicio = linea.indexOf("\">");
			int fin = linea.indexOf("</span");
			publicIP = linea.substring(inicio + 2, fin);

			tempBr.close();
			tempInStream.close();

		} catch (Exception ex) {
			publicIP = "<No es posible resolver la direccion IP>";
		}
		return publicIP;
	}

	public ValorarReceta(String id, Rectangle R) {
		int rx = R.getBounds().x;
		int ry = R.getBounds().y;
		int rw = R.getBounds().width;
		int rh = R.getBounds().height;

		setBounds(rx + (rw / 2) - 132, (ry + (rh / 2)) - 131, 264, 262);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setUndecorated(true);
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(255, 153, 0));

		JLabel label = new JLabel("Valoración: ");
		label.setBackground(new Color(255, 255, 255));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Calibri", Font.BOLD, 22));
		label.setBounds(12, 13, 136, 23);
		contentPanel.add(label);

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "- Seleccione una nota -", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		comboBox.setFont(new Font("Calibri", Font.BOLD, 18));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(28, 68, 206, 30);
		contentPanel.add(comboBox);

		// Boton de enviar valoracion
		JButton button = new JButton("Enviar Valoración");
		button.setFont(new Font("Calibri", Font.BOLD, 23));
		button.setBackground(new Color(173, 255, 47));
		button.setBounds(12, 137, 233, 63);
		contentPanel.add(button);

		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (comboBox.getSelectedIndex() != 0) {
					boolean exito = o.addPuntuacion(id, getIP(), comboBox.getSelectedItem().toString());
					if (!exito) {
						JOptionPane.showMessageDialog(contentPanel, "No puedes votar dos veces la misma receta!");
					} else {
						JOptionPane.showMessageDialog(contentPanel, "Gracias por votar!");
					}
					dispose();
				}
			}
		});

		// Boton de volver
		JButton button_1 = new JButton("Volver");
		button_1.setFont(new Font("Calibri", Font.BOLD, 23));
		button_1.setBackground(new Color(245, 245, 245));
		button_1.setBounds(49, 219, 162, 30);
		contentPanel.add(button_1);

		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
	}

}
