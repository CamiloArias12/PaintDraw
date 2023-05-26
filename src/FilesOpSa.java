import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//Clase para  abr
public class FilesOpSa  implements ActionListener{
	
	//Declaracion de variables	

	private Ventana frame; 		//Variable para conectar con la clase Ventana
	private JMenuBar menuBar; 	//variable del menubar
	private File f;				//Variable para leer o abrir archivo
	private JMenu  menu;		//Variable para agregar un menu
	private JMenuItem save, open;//Variables para abrir y  guardar imagen
	private JFileChooser fc;	//Variable para mostrar el menu de carpetas

	
//Metodo constructor donde se inicializan las variables
	public FilesOpSa (Ventana frame){
		this.frame=frame;
		menuBar= new JMenuBar();
		menu= new JMenu("Archivo");
		save= new JMenuItem("guardar");
		open =new JMenuItem("abrir");
		fc = new JFileChooser(new File("."));
		save.addActionListener(this);
		open.addActionListener(this);

		menu.add(save);
		menu.add(open);
		menuBar.add(menu);

	

	}

	//Metodo para abrir imagen
    private void openFile(File f) {

		try {
			frame.getDibujo().setImage(ImageIO.read(f));
			setDimensions(1300, 700);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	//Metodo para guardar imagen
	private void saveFile(File f) throws IOException {

		BufferedImage im = makePanel(frame.getDibujo());
		ImageIO.write(im, "png", f);
	}

	//Metodo para capturar imagen del jpanel
	private BufferedImage makePanel(JPanel panel)
	{
	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.print(g);
	    return bi;
	}

	//Metodo para  darle dimensiones a la imagen a cargar
	private void setDimensions(int width, int height)
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		if(height > dim.height - 160 && width > dim.width - 150)
		{
			frame.getSP().setSize(dim.width - 150, dim.height - 160);
		}	
		else if(width > dim.width - 150)
		{
			frame.getSP().setSize(dim.width - 150, height);
		}
		else if(height > dim.height - 160)
		{
			frame.getSP().setSize(width, dim.height - 160);
		}
		else
		{
			frame.getSP().setSize(width, height);
		}
	}

	//Metodo que escucha los eventos de los botones save y open

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();

		if (source == open) {
			if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
				f = fc.getSelectedFile();
				openFile(f);
			}
		} else if (source == save) {
			if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
				f = new File(fc.getSelectedFile() + ".png");									
				try {
					saveFile(f);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}	
		
	}

	//Metodo getter

	public JMenuBar getMenuBar() {
		return menuBar;
	}

}
