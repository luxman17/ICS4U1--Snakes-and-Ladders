import javax.swing.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.awt.event.*; 

/**<h1>Server Page</h1>
 * Player has decided to be the server that starts the game <br>
 * Player must wait for players to type ip to join <br>
 * Player can type their name in the textfield and has to press enter for it to be displayed
 * */
public class start extends JPanel{
	// properties
	BufferedImage background; 
	JButton back; 
	Font title;
	Font bold; 
	String strAddress = "1345";
	JButton gameplay; 
	int intConnections;
	Font subtitle; 
	JTextField TFname; 
	String strName; 
	
	
	
	// methods
	/**Overriding paint component method from JPanel*/
	public void paintComponent(Graphics g){
		g.setColor(Color.BLUE);
		g.setFont(title);  
		g.fillRect(0,0,1280,720); 	
		g.drawImage(background, 0,0, null); 
		g.drawString("Your IP Address is:", 400,150); 
		g.setColor(Color.RED); 
		g.setFont(title); 
		g.drawString(""+strAddress,460,250); 
		g.setColor(Color.WHITE); 
		g.setFont(subtitle); 
		g.drawString("Enter Name", 575, 300);
		
	}

	
	// constructor 
	
	/**Constructor for server page*/
	public start(){
		super(); 
		strAddress = "12345"; 
		
		// Exit button
		back = new JButton("HOMEPAGE"); 
		back.setSize(140, 30); 
		back.setLocation(1160, 0); 
		

		// start game
		gameplay = new JButton("start a game"); 
		gameplay.setSize(140,100); 
		gameplay.setLocation(1140,620); 
		
		// Textfield name button
		TFname = new JTextField(); 
		TFname.setSize(200,25); 
		TFname.setLocation(530,320); 
		
		try{
			background = ImageIO.read(new File("background.jpg")); 
			title = new Font("kabel.ttf", Font.BOLD, 50);
			bold = new Font("kabel.ttf", Font.BOLD, 100); 
			subtitle = new Font("kabel.ttf", Font.BOLD, 20); 
		}catch(IOException e){
			System.out.println("Error"); 
		}
		
		
	}
	

}
