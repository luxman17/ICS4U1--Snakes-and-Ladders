import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/** This is the animation panel for the whole game that includes all the graphics and designs */
public class SnakeGameplay extends JPanel{
	//Properties
	//-card variables
	BufferedImage gameboard;
	BufferedImage winner;
	BufferedImage loser;
	//-chatbox
	/**This textfield (from javaswing) allows you to type and chat with other players in the game*/
	public JTextField textfield = new JTextField();
	Timer timer;
	//-chat history
	/**This text area (from javaswing) displays the chat history with other players throughout the game*/
	public JTextArea textarea = new JTextArea();
	/**The scroll in the text area allows you scroll through the chat history*/
	public JScrollPane scroll; 
	/**This button allows you to roll the die and move around the board - there are two die*/
	public JButton rolldie;
	//-property variables
	//-fonts
	Font text = null;
	Font title = null;
	//-string
	String strColor = ""; 
	String strDice1 = "";
	String strDice2 = ""; 
	String strDiceSum = "";
	String strPlayerTurn = "";
	String strDisplayLength = "";
	String strDisplayLength2 = "";
	String strDisplayLength3 = "";
	

	int intPlayerN = 0; 
	
	
	//-player variables
	int intYOUx = 50; 
	int intYOUy = 650;
	int intPlayerCount =0; 
	int intPlayer = 0; 
	int intTurn = 1; 
	String strPlayer = "";
	// indicate which colours were selected
	String strYourColor = ""; 
	boolean blnBlue;
	boolean blnGreen; 
	boolean blnYellow; 
	boolean blnRed; 	
	// Player one: 
	String strColor1 = "";  
	int intPlayerX1 = -50;
	int intPlayerY1 = -50;
	// Player 2:
	String strColor2 = "";
	int intPlayerX2 = -50; 
	int intPlayerY2 = -50;
	// Player 3: 
	String strColor3 = ""; 
	int intPlayerX3 = -50; 
	int intPlayerY3 = -50; 
	// Player 4: 
	String strColor4 = ""; 
	int intPlayerX4 = -50; 
	int intPlayerY4 = -50; 
	
	//Endgame
	boolean blnwinner;
	int intTimer = 1;
	
	//Methods
	/**Overriding JPanel paintComponent method in the Monopoly Animation Panel */
	public void paintComponent(Graphics g){
		g.setColor(new Color(192,225,175)); 
		g.fillRect(0,0,1280,720); 
		g.drawImage(gameboard,-260,-3,null);
		
		
		//DRAWING DIE
		g.setColor(Color.BLACK);
		g.fillRect(720,3,142,100);
		g.setFont(text);
		g.drawString("CHATBOX",900,310);
		g.setColor(Color.WHITE);
		g.fillRect(727,36,60,60);
		g.fillRect(795,36,60,60);
	
		//status bar
		g.fillRect(867,3,407,99);
		
		//turn display
		g.fillRect(724,229,374,40);
		g.setFont(text);
		g.setColor(Color.BLACK);
		g.drawString("It's "+ strPlayerTurn+ "'s turn.",728,255);
		g.setColor(Color.WHITE);
		
		//profile - icon
		g.fillRect(1104,50,170,105);
		
		
		// load the rolled number into the dice area
		g.setFont(title);
		g.setColor(Color.BLACK); 
		g.drawString(strDice1, 740,73); 
		g.drawString(strDice2, 810, 73);
		
		// Update the status bar: 
		g.setFont(text);
		g.drawString("You rolled a: " +strDiceSum, 875,20);
		
		//Icon header
		g.setFont(text);
		g.setColor(Color.BLACK); 
		g.drawString("ICON:",1110,100);
		
		if(strColor.equals("red1") || strYourColor.equals("red")){
			g.setColor(Color.ORANGE);
			strColor = "red"; 
			strYourColor = "red"; 
		}else if(strColor.equals("blue1") || strYourColor.equals("blue")){
			g.setColor(Color.BLUE); 
			strColor = "blue"; 
			strYourColor = "blue"; 
		}else if(strColor.equals("yellow1") || strYourColor.equals("yellow")){
			strColor = "yellow"; 
			strYourColor = "yellow"; 
			g.setColor(Color.YELLOW); 
		}else if(strColor.equals("green1") || strYourColor.equals("green")){
			strColor = "green"; 
			strYourColor = "green"; 
			g.setColor(Color.GREEN); 
		}
		
		
		// draw the profile box in:
		g.fillRect(1164,50,50,50); 
		

		// set up your character
		g.fillRect(intYOUx, intYOUy, 25,25);
		
		// Other peoples characters: 
		
		// Player one set up: 
		if(strColor1.equals("red")){
			g.setColor(Color.ORANGE);
		}else if(strColor1.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor1.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor1.equals("green")){
			g.setColor(Color.GREEN); 
		}
		
		g.fillRect(intPlayerX1, intPlayerY1, 25,25);
		
		// Player two set up: 
		if(strColor2.equals("red")){
			g.setColor(Color.ORANGE);
		}else if(strColor2.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor2.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor2.equals("green")){
			g.setColor(Color.GREEN); 
		}
		
		g.fillRect(intPlayerX2, intPlayerY2, 25,25);
		
		// Player three set up: 
		if(strColor3.equals("red")){
			g.setColor(Color.ORANGE);
		}else if(strColor3.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor3.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor3.equals("green")){
			g.setColor(Color.GREEN); 
		}
		
		g.fillRect(intPlayerX3, intPlayerY3, 25,25); 
		
		// Player four set up: 
		if(strColor4.equals("red")){
			g.setColor(Color.ORANGE);
		}else if(strColor4.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor4.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor4.equals("green")){
			g.setColor(Color.GREEN); 
		}
		
		g.fillRect(intPlayerX4, intPlayerY4, 25,25); 
		
		if (intTimer == 0 && blnwinner == true){
			g.setColor(new Color(192,225,175)); 
			g.fillRect(0,0,1280,720); 
			g.drawImage(winner,0,0,null);
		}else if(intTimer == 0 && blnwinner == false){
			g.setColor(new Color(192,225,175)); 
			g.fillRect(0,0,1280,720); 
			g.drawImage(loser,0,0,null);
		}
		
	}
	

	//Constructor
	public SnakeGameplay(){
		super();
		
		//CHAT FEATURE
		//-chatbox
		textfield.setLocation(900,635);
		textfield.setSize(255,80);
		
		//-scroll
		scroll = new JScrollPane(textarea); 
		scroll.setLocation(900,330);
		scroll.setSize(255,297);
		textarea.setEnabled(false);
		
		
		//buttons

		
		rolldie = new JButton("Roll the die!");
		rolldie.setSize(130,20);
		rolldie.setLocation(727,10);
		
		//winner and loser images 
		try{
			gameboard = ImageIO.read(new File("snake4.png"));
			winner = ImageIO.read(new File("winner.png"));
			loser = ImageIO.read(new File("loser.png"));
		}catch(IOException e){
			System.out.println("Unable to upload image");
		}
		
		//fixing fonts
		//-text font
		try{
			text = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("kabel.ttf")); 
			text = text.deriveFont(Font.PLAIN, 14);
		}
		catch(Exception e){
		}
		try{
			text = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			text = text.deriveFont(Font.PLAIN, 14);
		}
		catch(Exception e){
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		}
		//-title font
		try{
			title = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("kabel.ttf")); 
			title = text.deriveFont(Font.PLAIN, 30);
		}
		catch(Exception e){
		}
		try{
			title = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			title = text.deriveFont(Font.PLAIN, 30);
		}
		catch(Exception e){
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		
		}
	}	
}
