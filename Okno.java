import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
 import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 import javax.imageio.ImageIO;
 import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Okno extends JFrame implements ActionListener, KeyListener, MouseWheelListener, MouseMotionListener, MouseListener {
    
    
    private JMenu[] menu = {new JMenu("Plik"), new JMenu("Edycja"), new JMenu("Pomoc")};
    private JMenuItem[] items = { new JMenuItem("Otwórz"), new JMenuItem("Zapisz"),
                          new JMenuItem("WyjdŸ"),new JMenuItem("Powiêksz"), new JMenuItem("Pomniejsz"), new JMenuItem("Obróæ"),
                          new JMenuItem("Opis"), new JMenuItem("Autor")
                        };
   
    Edycja edycja=new Edycja();
    
    private JLabel label = new JLabel();
    private JButton autor = new JButton("Autor");
    private JButton otworz = new JButton("Otwórz");
    private JButton zapisz = new JButton("Zapisz");
    private JButton bpowieksz = new JButton("Powiêksz");
    private JButton bpomniejsz = new JButton("Pomniejsz");
    private JButton obroc = new JButton("Obróæ");
    private JPanel panel = new JPanel();
  
    private static final long serialVersionUID = 1L;
    int mouseX,mouseY;
    
    Okno() {
            super("Przegladarka zdjec");
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
            panel.setLayout(null);
            panel.add(autor);
            autor.addActionListener(this);
            panel.add(otworz);
            otworz.addActionListener(this);
            panel.add(zapisz);
            zapisz.addActionListener(this);
            panel.setFocusable(true);
            panel.addKeyListener(this);
            panel.addMouseWheelListener(this);
            panel.addMouseListener(this);
            panel.addMouseMotionListener(this);
            panel.add(label);
            panel.add(bpowieksz);
            bpowieksz.addActionListener(this);
            panel.add(bpomniejsz);
            bpomniejsz.addActionListener(this);
            panel.add(obroc);
            obroc.addActionListener(this);
           
            for (int i = 0; i < items.length; i++)
                items[i].addActionListener(this);

            menu[0].add(items[0]);
            menu[0].add(items[1]);
            menu[0].add(items[2]);
            menu[1].add(items[3]);
            menu[1].add(items[4]);
            menu[1].add(items[5]);
            menu[2].add(items[6]);
            menu[2].add(items[7]);
               
            JMenuBar menubar = new JMenuBar();
            for (int i = 0; i < menu.length; i++)
                menubar.add(menu[i]);
            setJMenuBar(menubar);
           
            autor.setBounds(10, 10, 80, 20);
            otworz.setBounds(100, 10, 80, 20);
            zapisz.setBounds(190, 10, 80, 20);
            label.setBounds(10, 40, 1900, 950);
            bpowieksz.setBounds(280, 10, 100, 20);
            bpomniejsz.setBounds(390, 10, 100, 20);
            obroc.setBounds(500, 10, 80, 20);
            setVisible(true);
            setContentPane(panel);
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo=e.getSource();
        
        if(zrodlo==autor){
            JOptionPane.showMessageDialog(this, "Jakub Jaskola");
        }
        else if(zrodlo==otworz){
            edycja.wczytaj();
            
            
        }
        else if(zrodlo==zapisz){
            edycja.zapisz();                   
        }
        else if(zrodlo==bpowieksz){
            edycja.powieksz();           
        }
        else if(zrodlo==bpomniejsz){
            edycja.pomniejsz();           
        }
        else if(zrodlo==obroc){
            edycja.obroc();          
        }
        
        else if(zrodlo==items[0]){
            edycja.wczytaj();
            
        }
        else if(zrodlo==items[1]){
            edycja.zapisz();
            
        }
        else if(zrodlo==items[2]){
            System.exit(0);
        }
        else if(zrodlo==items[3]){
            edycja.powieksz();
        }
        else if(zrodlo==items[4]){
            edycja.pomniejsz();
        }
        else if(zrodlo==items[5]){
            edycja.obroc(); 
        }
        else if(zrodlo==items[6]){
            JOptionPane.showMessageDialog(this, "SKRÓTY: \n ALT+O - otwieranie pliku \n ALT+S - zapis pliku \n + - powiêkszanie zdjêcia \n - - pomniejszanie zdjêcia \n z - obrót o 90 stopni \n Scroll na myszy - powiêkszanie i pomniejszanie zdjêcia");           
        }
        else if(zrodlo==items[7]){
            JOptionPane.showMessageDialog(this, "Jakub Jaskola");
        }
        panel.requestFocus();
    }
    
    @Override
    public void keyPressed(KeyEvent kevt) {
        if(kevt.getKeyChar()=='o') {
            if(kevt.isAltDown()){
                edycja.wczytaj();
                
            }                   
        }       
        if(kevt.getKeyChar()=='s') {
            if(kevt.isAltDown()){
                edycja.zapisz();
                
            }                   
        }       
    }
 
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub       
    }
 
    @Override
    public void keyTyped(KeyEvent kevt) {     
        if(kevt.getKeyChar()=='+'){           
             edycja.powieksz();           
        }
        else if(kevt.getKeyChar()=='-'){           
             edycja.pomniejsz();           
        }
        else if(kevt.getKeyChar()=='z' ){
             edycja.obroc();                     
        }
        
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int a = e.getWheelRotation();
        if(a<0){
            edycja.powieksz();           
        }
        else if(a>0){
            edycja.pomniejsz();           
        }
    } 
    class Edycja {              
    	private BufferedImage img;
        private int skala = 0;
       
        void wczytaj(){
            Rozszerzenie jpg = new Rozszerzenie(".jpg", "Pliki JPG");
            Rozszerzenie gif = new Rozszerzenie(".gif", "Pliki GIF");
            Rozszerzenie png = new Rozszerzenie(".png", "Pliki PNG");
            JFileChooser jfc = new JFileChooser();
            jfc.addChoosableFileFilter(jpg);
            jfc.addChoosableFileFilter(gif);
            jfc.addChoosableFileFilter(png);
            jfc.showOpenDialog(null);
            File plik = jfc.getSelectedFile();
                      
            try {
                img = ImageIO.read(plik);
            } catch (IOException e) {
                e.printStackTrace();
            }                     
            skaluj(); 
        }
  
        
                 
       
              
        void odswiezlabel(Image image ){
            label.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
            //label.setBounds(10, 40, img.getWidth(null), img.getHeight(null));
            ImageIcon newicon = new ImageIcon(image);
            label.setIcon(newicon);
        }
        
        void skaluj(){
            double s = Math.pow(2.0, skala);
            Image newimg = img.getScaledInstance((int)(img.getWidth()*s), (int)(img.getHeight()*s), Image.SCALE_SMOOTH);
            odswiezlabel(newimg);
            
        }
        
        void powieksz(){
            skala++;
            skaluj();
        }
        
        void pomniejsz(){                         
            skala--;
            skaluj();           
        }
          
        void obroc(){          
            BufferedImage newimg = new BufferedImage(img.getHeight(null), img.getWidth(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D bg = (Graphics2D)newimg.getGraphics();

            AffineTransform at = new AffineTransform();
            at.translate(img.getHeight() / 2, img.getWidth() / 2);
            at.rotate(Math.toRadians(90));            
            at.translate(-img.getWidth() / 2, -img.getHeight() / 2);
            bg.drawImage(img, at, null);
            
            odswiezlabel(newimg);                             
            
            img = newimg;
        }
        
        void zapisz(){                      
            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(null);
            File plik = jfc.getSelectedFile();
           
            try {               
                ImageIO.write(img, "png", plik );
            } catch (IOException e) {
                e.printStackTrace();
            }
           
        }
        
            
    }
   
    public static void main(String[] args) {
        new Okno();
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
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		label.setLocation(e.getX()-mouseX, e.getY()-mouseY);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}
  
    
}