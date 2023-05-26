import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class Ventana extends JFrame
{
	
	//Declaracion de variables	
	
	private JPanel contentPane;	//Variable  crea un Jpanel
	private Dibujo dibujo;		//Variable de la clase dibujo
	private JMenuBar menuBar;	//Variable crea un menubar
	private JToolBar toolBar;	//Variable crea uan toolBar
	private JScrollPane sp;		////Variable crea un ScrollPane 

	private final int CONTENT_PANE_WIDTH = 1300;//Variables tamano de laa ventana
	private final int CONTENT_PANE_HEIGHT = 700;
	
	private int inkPanelWidth; //Variables Tamano del panel
	private int inkPanelHeight;
	private final Color background = Color.GRAY;// Variable color del panel


	//Metodo constructor para inicializar variables 
	 
	public Ventana()
	{
		inkPanelWidth = 1300 - 150;
		inkPanelHeight = 1300- 160;

		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		//Union de las clases a la Ventana
		menuBar= (new FilesOpSa(this)).getMenuBar();
		toolBar = (new ToolBar(this)).getToolBar();
		dibujo = new Dibujo(0, this, inkPanelWidth, inkPanelHeight);
		
		sp = new JScrollPane();
		sp.setLocation(10, 10);
		sp.setViewportView(dibujo);
		sp.setSize(inkPanelWidth, inkPanelHeight);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(sp);
		contentPane.setBackground(background);

		//Configuracion ventana
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(menuBar);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(contentPane);
		this.setSize(CONTENT_PANE_WIDTH, CONTENT_PANE_HEIGHT);
		this.setPreferredSize(new Dimension(CONTENT_PANE_WIDTH,CONTENT_PANE_HEIGHT));
		
	}
	
	
	
  //Meotodos  getter y setter
    public Dibujo getDibujo()
    {
    	return this.dibujo;
    }

    public Ventana getDrawFrame()
    {
    	return this;
    }
    public JScrollPane getSP()
    {
    	return this.sp;
    }

}
