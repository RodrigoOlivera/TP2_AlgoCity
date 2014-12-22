package algo3.algocity.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import algo3.algocity.controller.AccionMouseComenzarConJugadorExistente;
import algo3.algocity.controller.AccionMouseVolverMenuJugadorNuevoOExistente;
import algo3.algocity.model.Juego;
import algo3.algocity.model.RegistroUsuarios;
import algo3.algocity.model.excepciones.NoSeEncontroElFicheroException;

public class VistaJugadorExistenteMenu extends JPanel {

	private static final long serialVersionUID = 7513949772637913496L;

	VentanaInicial ventanaPortadora;
	Image imagenFondo;

	JButton bComenzar;
	JButton bVolver;

	JLabel etiquetaMensaje;

	public VistaJugadorExistenteMenu(VentanaInicial ventana) {
		this.ventanaPortadora = ventana;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// setMinimumSize(new Dimension(25, 50));
		// setMaximumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));

		try {
			setContenido();
		} catch (NoSeEncontroElFicheroException e) {
			e.printStackTrace();
		}
	}

	private void setContenido() throws NoSeEncontroElFicheroException {

		/* Configura la etiqueta Elija un Usuario */
		this.etiquetaMensaje = new JLabel("Elija un Usuario", JLabel.CENTER);
		this.etiquetaMensaje.setPreferredSize(new Dimension(200, 25));
		this.etiquetaMensaje.setMaximumSize(new Dimension(200, 25));
		this.etiquetaMensaje
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.etiquetaMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.etiquetaMensaje.setAlignmentY(Component.CENTER_ALIGNMENT);

		this.etiquetaMensaje.setOpaque(true);

		this.etiquetaMensaje.setForeground(Color.black);
		// this.etiquetaMensaje.setBackground(null);

		/* Configura el panel de usuarios existentes */

		// Obtengo los usuarios existentes
		RegistroUsuarios ru = new RegistroUsuarios();
		ArrayList<String> nombres = ru.nombresUsuarios();
		String[] datos = new String[nombres.size()];
		Iterator<String> it = nombres.iterator();
		int i = 0;
		while (it.hasNext()) {
			datos[i] = it.next();
			i++;
		}

		JList<String> lista = new JList<String>(datos);
		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lista.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		JScrollPane panelDesplazamiento = new JScrollPane(lista);

		panelDesplazamiento.setPreferredSize(new Dimension(175, 85));
		panelDesplazamiento.setMaximumSize(new Dimension(175, 85));
		panelDesplazamiento
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelDesplazamiento.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelDesplazamiento.setAlignmentY(Component.CENTER_ALIGNMENT);

		/* Ajusto los botones */
		this.bComenzar = new JButton("� Comenzar !");
		this.bComenzar.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.bComenzar.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.bComenzar
				.addActionListener(new AccionMouseComenzarConJugadorExistente(
						this.ventanaPortadora, lista));

		this.bVolver = new JButton("Volver");
		this.bVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.bVolver.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.bVolver
				.addActionListener(new AccionMouseVolverMenuJugadorNuevoOExistente(
						this.ventanaPortadora));

		add(Box.createRigidArea(new Dimension(0, 250)));
		add(this.etiquetaMensaje);
		add(Box.createRigidArea(new Dimension(0, 25)));
		add(panelDesplazamiento);
		add(Box.createRigidArea(new Dimension(0, 25)));
		add(bComenzar);
		add(Box.createRigidArea(new Dimension(0, 25)));
		add(bVolver);
		add(Box.createRigidArea(new Dimension(0, 150)));

	}

	/* Setea la imagen de fondo del panel */
	@Override
	public void paintComponent(Graphics g) {
		Dimension tamanio = getSize();
		this.imagenFondo = new ImageIcon("img/fondoAlgoCity.jpg").getImage();
		g.drawImage(this.imagenFondo, 0, 0, getWidth(), getHeight(), null);
		setOpaque(false);
		super.paintComponent(g);
	}
}