import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Font;

/**<h1>St.Augustine Monopoly Characters Page</h1>
 * Players can decide what colour to represent their character.
 * */
public class snakeplayer extends JPanel{
	
	//Properties
	Font title;
	/**Player selects red*/
	public JButton select1 = new JButton("Select");
	/**Player selects blue*/
	public JButton select2 = new JButton("Select");
	/**Player selects yellow*/
	public JButton select3 = new JButton("Select");
	/**Player selects green*/
	public JButton select4 = new JButton("Select");
	/**After selecting a colour, player can start game*/
	public JButton gameplay = new JButton("Play"); 
	
	boolean blnsta1;
	boolean blnsta2;
	boolean blnsta3;
	boolean blnsta4;
	int intPlayerN = 1; 
	Font text; 
	String strData = "";
	
	//Methods
	public void paintComponent(Graphics g){
		g.setFont(title);
		g.setColor(Color.RED);
		g.fillRect(0,0,1280,720);
		//Character 1
		g.setColor(Color.ORANGE);
		g.fillRect(1100,400,25,25);
	//Character 1
		g.setColor(Color.BLUE);
		g.fillRect(100,400,25,25);
	//Character 2	
		g.setColor(Color.YELLOW);
		g.fillRect(500,400,25,25);
	//Character 3
		g.setColor(Color.GREEN);
		g.fillRect(800,400,25,25);

	//Pick a Player Label
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Serif",Font.BOLD,40));
		g.drawString("Select your player",500,300); 
		
	}
	
	
	//Constructors
	/**Constructor for Snakes and Ladders*/
	public snakeplayer(){
		super();

		//Select 1;
		select1.setSize(100,50);
		select1.setLocation(1100,480);
		//Select 1;
		select2.setSize(100,50);
		select2.setLocation(100,480);
		
		//Select 2;
		select3.setSize(100,50);
		select3.setLocation(500,480);
		
		//Select 3;
		select4.setSize(100,50);
		select4.setLocation(800,480);
		
		// gameplay
		gameplay.setSize(140,100); 
		gameplay.setLocation(1140,620); 
		
		
		// font
		try{
			text = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			text = text.deriveFont(Font.BOLD, 35);
		}
		catch(Exception e){
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		}
		
		
	}

}
