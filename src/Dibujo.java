import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;



public class Dibujo extends JPanel implements MouseListener,MouseMotionListener
	{
		
	//Declaracion de variables	
		private BufferedImage canvas;  //Variable para cargar imagen
		private Graphics2D graphics2D; // Variable para dibujar imagen
		private int activeTool = 0;	//Variable para capturar herramienta
		private Ventana frame;		//Variable para conectar la clase al Jpanel
		private Color color;		//Color de la herramienta
		private int x1=0, y1=0, x2=0, y2=0; //variables para capturar la posicion del mouse
		private int inkPanelWidth;  //variables para estabblecer el tamanoo de la pantalla
		private int inkPanelHeight;

		
	//Metodo constructor 

		public Dibujo(int f, Ventana frame, int width, int height){

			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
			inkPanelWidth = dim.width - 150;
			inkPanelHeight = dim.height- 160;
			this.setSize(inkPanelWidth - 3, inkPanelHeight - 3); 
			this.setPreferredSize(new Dimension(inkPanelWidth - 3,inkPanelHeight - 3));
			this.setLayout(null);
			setDoubleBuffered(true);
			setLocation(10, 10);
			setBackground(Color.WHITE);
			setFocusable(true);
			requestFocus();
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.frame = frame;
			
		
		}
		
	//Metodo para mostrar la imagen cargada

	public void setImage(BufferedImage image) {
		graphics2D.dispose();
		canvas = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		graphics2D = canvas.createGraphics();
		graphics2D.drawImage(image, 0, 0, null);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	// Metodo para dibujar en el jpanel 		
		public void paintComponent(Graphics g){
			if(canvas == null){
				canvas = new BufferedImage(inkPanelWidth, inkPanelHeight,BufferedImage.TYPE_INT_ARGB);
				graphics2D = canvas.createGraphics();
				graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			}
			g.drawImage(canvas, 0, 0, null);
			 Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			graphics2D.setStroke(new BasicStroke(4));
			
		}
		
		//metodo para asignar la herramienta desde la clase ToolBar
		public void setTool(int tool) {
			this.activeTool = tool;
		}
		//metodo para asignar el color a la herramienta desde la clase ToolBar
		public void setToolColor(Color color ,Boolean fill) {	
				graphics2D.setColor(color);
				this.color=color;
		}

		public Color getColor(){
			return color;
		}
		
		

		
	//Metodos  que capturan acciones del mouse
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			
			if (activeTool == 0) {//Captura la herramienta lapiz 
				x2 = e.getX();
				y2 = e.getY();
		
				repaint();
				x1 = x2;
				y1 = y2;
				graphics2D.drawLine(x1, y1, x2,y2);
				repaint();
					
			}
			
			
		}



		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		
			x1 = e.getX();
			y1 = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

				x2=e.getX();
				y2=e.getY();
		
			if (activeTool == 1){
				graphics2D.drawLine( x1,y1, x2 , y2 );	//Dibuja una linea
			}

			if (activeTool == 2){
				graphics2D.drawRect( x1,y1, x2 - x1, y2 - y1);//dibuja  un rectangulo	

			}

			if (activeTool == 3){
				graphics2D.drawOval( x1,y1, x2 - x1, y2 - y1);	//Dibuja un circulo

			}

			repaint();
		}
		
	 
	    
	    
	}
	    
	   