import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToolBar implements ActionListener {
	//Declaracion de variables	

	private JToolBar toolBar; 	//Variable para  crear un toolBar
	private JButton pencil;		//Variable pencil para dibujar libremente
	private JButton line;		//Variable para dibujar una linea
	private JButton rectangle;	//Variable para dibuajr un rectangulo
	private JButton circle;		//Variable para dibujar un circulo
	private JButton selColor;	//Variable para seleccionar un color
	private Ventana frame;		//Variable para conectar a la clase Ventana
	
	//Metodo constructor para inicializacion de variables
	public ToolBar(Ventana frame) {
		this.frame = frame;
		
		
		toolBar = new JToolBar(JToolBar.VERTICAL);
		toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		toolBar.setFloatable(false);
		toolBar.setLayout(new GridLayout(0,18));
		
		pencil = new JButton("Lapiz");
		line = new JButton("Linea");
		rectangle = new JButton("Rectangulo");
		circle = new JButton("Circulo");
		selColor = new JButton("Color");

		rectangle.addActionListener(this);
		line.addActionListener(this);
		circle.addActionListener(this);
		pencil.addActionListener(this);
		selColor.addActionListener(this);

		 toolBar.add(pencil);
		 toolBar.add(line);
		 toolBar.add(rectangle);
		 toolBar.add(circle);
		 toolBar.add(selColor);
	}

	//Metodo que escucha cuando una herramienta es seleccionada

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();

		if (source == pencil) {
			frame.getDibujo().setTool(0);
		} else if (source == line) {
			frame.getDibujo().setTool(1);
		}  else if (source == rectangle) {
			frame.getDibujo().setTool(2);
		}  else if (source == circle) {
			frame.getDibujo().setTool(3);
		} else if (source == selColor ) {
			Color selectedColor = JColorChooser.showDialog(toolBar, "Elegir Color", Color.black);

				frame.getDibujo().setToolColor(selectedColor,true);


		} 
		
	}

	//Metodos getter

	public JToolBar getToolBar() {
		return this.toolBar;
		
	}

	
}