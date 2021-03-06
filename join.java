import javax.swing.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.awt.event.*; 

/** <h1>Client (Joining a game)</h1>
 * The player has decided to join (client) an existing game <br>
 * Player must input the IP address of another player (and press enter) <br>
 * Player must also input their own name as well (and press enter) <br>
 * */
public class join extends JPanel{
	// properties
	BufferedImage background; 
	/**Go back to start page to decide whether or not to be server/client*/
	public JButton back;
	/**Player inputs IP address of another player (server) and must press enter for it to be valid*/
	public JTextField TF; 
	Font title;
	JButton gameplay; 
	String strCode = "132"; 
	Font subtitle; 
	/**Player inputs/types their name (must press enter for it to be valid)*/
	public JTextField TFname;
	String strName; 
	
	
	// methods
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE); 
		g.fillRect(0,0,1280,720); 	
		g.drawImage(background, 0, 0, null); 
		g.setFont(title); 
		g.drawString("Enter IP Address:", 400,250);
		g.setFont(subtitle); 
		g.drawString("Enter Name", 560, 360); 
		
	}

	
	// constructor 
	public join(){
		super(); 
				
		// Exit button
		back = new JButton("HOMEPAGE"); 
		back.setSize(140, 30); 
		back.setLocation(1160, 0); 
		
		// textfield
		
		TF = new JTextField();
		TF.setSize(500,50); 
		TF.setLocation(370, 275);
		
		// start game
		gameplay = new JButton("start game"); 
		gameplay.setSize(140,100); 
		gameplay.setLocation(1140,620); 
		
		// Textfield name
		TFname = new JTextField(); 
		TFname.setSize(200,25); 
		TFname.setLocation(520,380); 
		
		
		try{
			background = ImageIO.read(new File("background.jpg")); 
			title = new Font("kabel.ttf", Font.BOLD, 50);
			subtitle = new Font("kabel.ttf", Font.BOLD, 20); 
			
		}catch(IOException e){
			System.out.println("Error"); 
		}
		
		
	}
	

}
