import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Font;
import java.io.*;

public class Snake implements ActionListener{
	/** <h1> Display Panel and Game Play of Snakes and Ladders </h1>
 * This is the display and gameplay for game <br>
 * Number of Players: 1-3 added a fourth player as an extra feature
 * @creator Luxman V and Terra X
 * @since 2022-01-06*/

	
	public JFrame theframe; 
	
	
	public JPanel thepanel;
	
	
	public JButton InstrucButt;
	
	
	public JButton ExitButt; 
	
	
	public JButton PlayButt;
	
	public JButton AboutButt;
	
	
	public snakeinstructions thepanelinstructions;
	

	public server playpage1;
	

	public start startpage;
	
	
	public join joinpage; 
	
	
	public SuperSocketMaster ssm; 
	
	
	public SnakeGameplay SNAKEpanel; 
	
	
	public snakeplayer characterspanel;
	
	public Timer thetimer;
	
	//-Sending information over network variable 
	int intPiece = 1; 
	int intRoll = 2;  
	boolean blnPlay = false; 
	boolean blnDice = false; 
	
	//-File Variables 
	//--board
	//--Data Array
	
	//-Server 
	boolean blnServer; 
	boolean blnSent = false;
	
	//-Dice 
	int intdice1; 
	int intdice2;
	int intdiesum; 
	int intCount; 
	
	
	//*****METHODS*****

	public void actionPerformed(ActionEvent evt){
		
		//INSTRUCTIONS SCREEN
		//-going to instructions screen
		if(evt.getSource() == InstrucButt){
			thepanelinstructions.blnpage1 = true;
			thepanelinstructions.backtomain.setVisible(true);
			thepanelinstructions.topage2.setVisible(true);
			thepanelinstructions.Exit2Butt.setVisible(false);
			thepanelinstructions.textfield.setVisible(false);
			thepanelinstructions.rolldie.setVisible(false);
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);	
		}
		//-page 1
		else if(evt.getSource() == thepanelinstructions.backtomain){
			thepanelinstructions.textfield.setVisible(false);
			thepanelinstructions.rolldie.setVisible(false);
			theframe.setContentPane(thepanel);
		}
		else if(evt.getSource() == thepanelinstructions.topage2){
			thepanelinstructions.backtomain.setVisible(true);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(true);
			thepanelinstructions.topage3.setVisible(true);
			thepanelinstructions.textfield.setVisible(true);
			thepanelinstructions.rolldie.setVisible(true);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = true;
			thepanelinstructions.blnpage4 = false;
			thepanelinstructions.blnpage5 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}else if(evt.getSource() == AboutButt){
			thepanelinstructions.backtomain.setVisible(true);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			thepanelinstructions.textfield.setVisible(false);
			thepanelinstructions.rolldie.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = true;
			thepanelinstructions.blnpage5 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		
		//TO LEAVE GAME
		else if(evt.getSource() == ExitButt){
			System.exit(0);
		}
		else if(evt.getSource() == thepanelinstructions.Exit2Butt){
			System.exit(0);
		}
			
		//PLAY SCREEN
		else if(evt.getSource() == PlayButt){
			thepanelinstructions.textfield.setVisible(false);
			thepanelinstructions.rolldie.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			theframe.setContentPane(playpage1); 
			theframe.setVisible(true);
		}
		else if(evt.getSource() == playpage1.back){
			theframe.setContentPane(thepanel); 
			theframe.setVisible(true); 
		}
		else if(evt.getSource() == playpage1.start){
			System.out.println("started"); 
			theframe.setContentPane(startpage); 
			theframe.setVisible(true); 
			// opens super socket master if decides to start game	
			ssm = new SuperSocketMaster(1969, this);
			startpage.strAddress = ssm.getMyAddress(); 
			ssm.connect();
			blnServer = true;
			System.out.println(blnServer); 	
		}		
		else if(evt.getSource() == playpage1.existing){
			System.out.println("going to exisitng game"); 
			theframe.setContentPane(joinpage); 
			theframe.setVisible(true); 	
		}
		
		else if(evt.getSource() == startpage.back || evt.getSource() ==joinpage.back){ 
			theframe.setContentPane(thepanel); 
			theframe.setVisible(true); 
		}
		
		else if(evt.getSource() == characterspanel.gameplay){
			blnPlay = true; 
			theframe.setContentPane(SNAKEpanel);
			theframe.setVisible(true); 	
		}
		
		//JOINING A GAME
		else if(evt.getSource() == joinpage.TF){
			joinpage.strCode = joinpage.TF.getText(); 
			System.out.println(joinpage.strCode); 
			System.out.println(startpage.strAddress); 
		}
		
		//CHARACTER PANEL		
		else if(evt.getSource() == characterspanel.gameplay){
			theframe.setContentPane(SNAKEpanel);
			theframe.setVisible(true); 	
		}
		
		//ENTERING NAME (start)
		else if(evt.getSource() == startpage.TFname){
			startpage.strName = startpage.TFname.getText();  
			SNAKEpanel.strPlayerTurn = startpage.strName;		
		}
		//ENTERING NAME (join)
		else if(evt.getSource() == joinpage.TFname){
			joinpage.strName = joinpage.TFname.getText();
			SNAKEpanel.strPlayerTurn = joinpage.strName;	
		}
		
		//CHARACTERS PAGE
		//-starting game
		else if(evt.getSource() == startpage.gameplay){
			theframe.setContentPane(characterspanel);
			theframe.setVisible(true); 
		}
		//CHARACTERS PAGE
		//-joining game
		else if(evt.getSource() == joinpage.gameplay){
			theframe.setContentPane(characterspanel); 
			theframe.setVisible(true); 
			//-supersocket master
			ssm = new SuperSocketMaster(joinpage.strCode,1969, this); 
			ssm.connect();
			blnServer = false;
			System.out.println(joinpage.strCode); 
		}
		//choosing character 1
		else if(evt.getSource() == characterspanel.select1){ 
			characterspanel.gameplay.setEnabled(true); 
			SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1;
			// If you are the server send this text.
			SNAKEpanel.strColor = "red1"; 
			SNAKEpanel.intPlayer = SNAKEpanel.intPlayerCount;
			System.out.println("you're player number:"+SNAKEpanel.intPlayer); 
			System.out.println("this is the turn number: "+SNAKEpanel.intTurn); 
			System.out.println("this is your colour: "+SNAKEpanel.strColor); 
			if(blnServer){ 
				ssm.sendText("select1");
				System.out.println("Server sent"); 
				characterspanel.select1.setEnabled(false); 
			}// If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select1"); 
				System.out.println("client sent"); 
				characterspanel.select1.setEnabled(false); 
			}
		} 
		//choosing character 2
		else if(evt.getSource() == characterspanel.select2){ 
			characterspanel.gameplay.setEnabled(true); 
			SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1;
			SNAKEpanel.intPlayer = SNAKEpanel.intPlayerCount;
			SNAKEpanel.strColor = "blue1";  
			System.out.println("you're player number:"+SNAKEpanel.intPlayer); 
			System.out.println("this is the turn number: "+SNAKEpanel.intTurn);  
			// If you are the server send this text.
			if(blnServer){ 
				ssm.sendText("select2");
				System.out.println("Server sent"); 
				characterspanel.select2.setEnabled(false); 
			// Symbolized that you sent the message. 
				blnSent = true; 
			}//If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select2"); 
				System.out.println("client sent"); 
				characterspanel.select2.setEnabled(false); 
			// Symbolized that you sent the message
				blnSent = true; 
			}
		}
		//choosing character 3
		else if(evt.getSource() == characterspanel.select3){ 
			characterspanel.gameplay.setEnabled(true); 
			SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1;
			SNAKEpanel.intPlayer = SNAKEpanel.intPlayerCount;
			System.out.println(SNAKEpanel.intPlayer);
			System.out.println("you're player number:"+SNAKEpanel.intPlayer); 
			System.out.println("this is the turn number: "+SNAKEpanel.intTurn); 
			SNAKEpanel.strColor = "yellow1"; 
			System.out.println(SNAKEpanel.strColor); 
			// If you are the server send this text.
			if(blnServer){ 
				ssm.sendText("select3");
				System.out.println("Server sent"); 
				characterspanel.select3.setEnabled(false); 
			// Symbolized that you sent the message. 
				blnSent = true; 
			}
			// If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select3"); 
				System.out.println("client sent"); 
				characterspanel.select3.setEnabled(false); 
			// Symbolized that you sent the message
				blnSent = true; 
			}
		} 
		//choosing character 4
		else if(evt.getSource() == characterspanel.select4){
			SNAKEpanel.strColor = "green1"; 
			characterspanel.gameplay.setEnabled(true); 
			SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1; 
			SNAKEpanel.intPlayer = SNAKEpanel.intPlayerCount;
			System.out.println("you're player number:"+SNAKEpanel.intPlayer); 
			System.out.println("this is the turn number: "+SNAKEpanel.intTurn); 
			// If you are the server send this text.
			if(blnServer){ 
				ssm.sendText("select4");
				System.out.println("Server sent"); 
				characterspanel.select4.setEnabled(false); 
			// Symbolized that you sent the message. 
				blnSent = true; 
			}// If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select4"); 
				System.out.println("client sent"); 
				characterspanel.select4.setEnabled(false); 
			// Symbolized that you sent the message
				blnSent = true; 
			}
		}
		
		//CHATBOX (SUPERSOCKETMASTER)
		//talking to people over server 
		else if(evt.getSource() == SNAKEpanel.textfield){
			String strChat;
			System.out.println("Going to send this out over network: "+SNAKEpanel.textfield.getText()); 

			if(blnServer == true){
				System.out.println("I am the server"); 
				strChat = SNAKEpanel.textfield.getText();
				ssm.sendText(startpage.strName+" :"+strChat); 
				System.out.println(strChat);
				SNAKEpanel.textarea.append("\nYou: "+strChat);
				SNAKEpanel.textfield.setText("");
			}
			else if(blnServer == false){
				System.out.println("I am the client"); 
				strChat = SNAKEpanel.textfield.getText();
				System.out.println(strChat);
				ssm.sendText(joinpage.strName+" :"+strChat); 
				SNAKEpanel.textarea.append("\nYou: "+strChat);
				SNAKEpanel.textfield.setText("");
			}
		}		
		
		//CHARACTERS SERVER PAGE	
		else if(evt.getSource() == ssm){
		String strData; 
		boolean blnMovement = true; 
		strData = ssm.readText(); 
		
		
			// If you are the server 
			if(blnServer == true){
				characterspanel.strData = ssm.readText(); 
				System.out.println("SERVER RECIEVED THIS: "+characterspanel.strData); 
				//Set button to false;
				if(characterspanel.strData.equals("select1")){
					characterspanel.select1.setEnabled(false); 
					SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1;
					characterspanel.repaint();   
				}
				else if(characterspanel.strData.equals("select2")){
					SNAKEpanel.intPlayerCount =SNAKEpanel.intPlayerCount + 1;  
					characterspanel.select2.setEnabled(false); 
					characterspanel.repaint(); 
				}
				else if(characterspanel.strData.equals("select3")){
					characterspanel.select3.setEnabled(false); 
					characterspanel.repaint(); 
					SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1;  
				}
				else if(characterspanel.strData.equals("select4")){
					characterspanel.select4.setEnabled(false); 
					SNAKEpanel.intPlayerCount =SNAKEpanel.intPlayerCount + 1;  
					characterspanel.repaint(); 
				}
				System.out.println("number of players: "+SNAKEpanel.intPlayerCount); 
				characterspanel.intPlayerN = SNAKEpanel.intPlayerCount; 
				// System.out.println(characterspanel.intPlayerN);
				characterspanel.repaint(); 
			} 
			
			// If you are not the server and did not send the message about shutting the button off, this code should run.
			if(blnServer == false){
				characterspanel.strData = ssm.readText(); 
				System.out.println("CLIENT received this:"+characterspanel.strData); 
				// set to false;
				if(characterspanel.strData.equals("select1")){
					characterspanel.select1.setEnabled(false);
					SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1; 
					characterspanel.repaint(); 
				}
				else if(characterspanel.strData.equals("select2")){
				SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1; 
					characterspanel.select2.setEnabled(false); 
					characterspanel.repaint(); 
				}
				else if(characterspanel.strData.equals("select3")){
					SNAKEpanel.intPlayerCount =SNAKEpanel.intPlayerCount + 1;
					characterspanel.repaint();  	
					characterspanel.select3.setEnabled(false); 
				}
				else if(characterspanel.strData.equals("select4")){
					SNAKEpanel.intPlayerCount = SNAKEpanel.intPlayerCount + 1; 
					characterspanel.repaint(); 
					characterspanel.select4.setEnabled(false); 
				}
				// make the player count equal 
				characterspanel.intPlayerN = SNAKEpanel.intPlayerCount; 
				System.out.println(characterspanel.intPlayerN); 
				characterspanel.repaint(); 
			}
			
			System.out.println("This is the turn number: "+SNAKEpanel.intTurn); 
			System.out.println("this is the piece number: "+intPiece); 
			
			// only updates screen if the message is regard to movement
			if(blnMovement == true){
				// Update other players scren: 
				if(SNAKEpanel.intTurn == 1 && intPiece == 1){
				System.out.println("testing" +SNAKEpanel.strColor); 
				
					// how to know which information is being received?! 
					if(SNAKEpanel.strColor.equals("red") || SNAKEpanel.strColor.equals("blue") || SNAKEpanel.strColor.equals("yellow") || SNAKEpanel.strColor.equals("green")) {
						System.out.println("i'm back in here"); 
						SNAKEpanel.strColor1 = ssm.readText(); 
						intPiece = intPiece + 1; 
					}
				}
				else if(SNAKEpanel.intTurn == 1 && intPiece == 2){
					String strPlayerX; 
					strPlayerX = ssm.readText(); 
					SNAKEpanel.intPlayerX1 = Integer.parseInt(strPlayerX); 
					intPiece = intPiece + 1; 
				}
				else if(SNAKEpanel.intTurn == 1 && intPiece == 3){
					String strPlayerY; 
					strPlayerY = ssm.readText(); 
					SNAKEpanel.intPlayerY1 = Integer.parseInt(strPlayerY); 
					intPiece = intPiece +1; 
				}
				else if(SNAKEpanel.intTurn == 1 && intPiece == 4){
					// updates whos turn it is. 
					String strTurn; 
					strTurn = ssm.readText(); 
				SNAKEpanel.intTurn = Integer.parseInt(strTurn); 
					System.out.println("This is the turn number: "+SNAKEpanel.intTurn); 
					intPiece = 1; 
					
					
				}
				
				// code for second player: 
				if(SNAKEpanel.intTurn == 2 && intPiece == 1){
				System.out.println("testing" +SNAKEpanel.strColor); 
					// how to know which information is being received?! 
					if(SNAKEpanel.strColor.equals("blue") || SNAKEpanel.strColor.equals("yellow") || SNAKEpanel.strColor.equals("green")) {
						System.out.println("I'm receiving it here"); 
						SNAKEpanel.strColor2 = ssm.readText(); 
						intPiece = intPiece + 1; 
					}
				}
				else if(SNAKEpanel.intTurn == 2 && intPiece == 2){
					String strPlayerX; 
					strPlayerX = ssm.readText(); 
					SNAKEpanel.intPlayerX2 = Integer.parseInt(strPlayerX); 
					intPiece = intPiece + 1; 
				}else if(SNAKEpanel.intTurn == 2 && intPiece == 3){
					String strPlayerY; 
					strPlayerY = ssm.readText(); 
					SNAKEpanel.intPlayerY2 = Integer.parseInt(strPlayerY); 
					intPiece = intPiece +1; 
				}
				else if(SNAKEpanel.intTurn == 2 && intPiece == 4){
					// updates whos turn it is. 
					String strTurn; 
					strTurn = ssm.readText(); 
				SNAKEpanel.intTurn = Integer.parseInt(strTurn); 
					System.out.println("This is the turn number: "+SNAKEpanel.intTurn); 
					intPiece = 1; 
				
					
				}
				
				
				// code for third player: 
				if(SNAKEpanel.intTurn == 3 && intPiece == 1){
				System.out.println("testing" +SNAKEpanel.strColor); 
					// how to know which information is being received?! 
					if(SNAKEpanel.strColor.equals("red") || SNAKEpanel.strColor.equals("blue") || SNAKEpanel.strColor.equals("yellow") || SNAKEpanel.strColor.equals("green")) {
						SNAKEpanel.strColor3 = ssm.readText(); 
						intPiece = intPiece + 1; 
					}
				}
				else if(SNAKEpanel.intTurn == 3 && intPiece == 2){
					String strPlayerX; 
					strPlayerX = ssm.readText(); 
					SNAKEpanel.intPlayerX3 = Integer.parseInt(strPlayerX); 
					intPiece = intPiece + 1; 
				}
				else if(SNAKEpanel.intTurn == 3 && intPiece == 3){
					String strPlayerY; 
					strPlayerY = ssm.readText(); 
					SNAKEpanel.intPlayerY3 = Integer.parseInt(strPlayerY); 
					intPiece = intPiece +1; 
				}
				else if(SNAKEpanel.intTurn == 3 && intPiece == 4){
					// updates whos turn it is. 
					String strTurn; 
					strTurn = ssm.readText(); 
					SNAKEpanel.intTurn = Integer.parseInt(strTurn); 
					System.out.println("This is the turn number: "+SNAKEpanel.intTurn); 
					intPiece = 1; 
					
					
				}
				
				// code for player four: 
				if(SNAKEpanel.intTurn == 4 && intPiece == 1){
				System.out.println("testing" +SNAKEpanel.strColor); 
					// how to know which information is being received?! 
					if(SNAKEpanel.strColor.equals("red") || SNAKEpanel.strColor.equals("blue") ||SNAKEpanel.strColor.equals("yellow") || SNAKEpanel.strColor.equals("green")) {
						SNAKEpanel.strColor4 = ssm.readText(); 
						intPiece = intPiece + 1; 
					}
				}
				else if(SNAKEpanel.intTurn == 4 && intPiece == 2){
					String strPlayerX; 
					strPlayerX = ssm.readText(); 
					SNAKEpanel.intPlayerX4 = Integer.parseInt(strPlayerX); 
					intPiece = intPiece + 1; 
				}
				else if(SNAKEpanel.intTurn == 4 && intPiece == 3){
					String strPlayerY; 
					strPlayerY = ssm.readText(); 
					SNAKEpanel.intPlayerY4 = Integer.parseInt(strPlayerY); 
					intPiece = intPiece +1; 
				}
				else if(SNAKEpanel.intTurn == 4 && intPiece == 4){
					// updates whos turn it is. 
					String strTurn; 
					strTurn = ssm.readText(); 
					SNAKEpanel.intTurn = Integer.parseInt(strTurn); 
					System.out.println("This is the turn number: "+SNAKEpanel.intTurn); 
					intPiece = 1; 
					

				}
				
				// fifth code to reset the turn number if there are three players playing, the turn number will turn to this and reset the code
				if(SNAKEpanel.intTurn == 5 && intPiece == 1){
					SNAKEpanel.intTurn = 1; 
					intPiece = 1;
					// allowing button to reset
					
				}
				
				SNAKEpanel.textarea.setText("");
			}
			
			SNAKEpanel.textarea.append("\n"+strData); 
			blnMovement = true; 
			 
			
		}
	
			
		//ROLLING THE DICE
		else if(evt.getSource() == SNAKEpanel.rolldie){
			//- random die rolling
			intdice1 = (int)(Math.random() *6+1); 
			intdice2 = (int)(Math.random()*6+1); 
			intdiesum = intdice1 + intdice2; 
			blnDice = true; 
			
			// make it even number
			intRoll = intRoll*2; 
			//send stuff to update location of your character 
			// make if you get to a certain point the intYOUy is changed instead.
			// it is not going up 
			
			//- printing out dice to animation game panel
			String strDice1; 
			String strDice2; 
			String strDiceSum;
			strDice1 = (intdice1+""); 
			strDice2 = (intdice2+""); 
			strDiceSum = (intdiesum+"");
			SNAKEpanel.strDice1 = strDice1;
			SNAKEpanel.strDice2 = strDice2;
			SNAKEpanel.strDiceSum = strDiceSum;
			
			int snakesLaddersArray [] = new int [10]; // create a 6 element array
			// store the snakes and ladders location in the array
			snakesLaddersArray [0] = 650;
			snakesLaddersArray [1] = 590;
			snakesLaddersArray [2] = 530;
			snakesLaddersArray [3] = 470;
			snakesLaddersArray [4] = 400;
			snakesLaddersArray [5] = 330;
			snakesLaddersArray [6] = 260;
			snakesLaddersArray [7] = 190;
			snakesLaddersArray [8] = 120;
			snakesLaddersArray [9] = 50;
			
			int intXtotal;
			
			for(intCount = 1; intCount <= intdiesum; intCount++){
				 if(SNAKEpanel.intYOUx >= 50 && SNAKEpanel.intYOUx < 650 && SNAKEpanel.intYOUy == 650){
					SNAKEpanel.intYOUx = SNAKEpanel.intYOUx + 60;
					System.out.println(SNAKEpanel.intYOUx);
				}
	 	
				else if(SNAKEpanel.intYOUy == 650 && SNAKEpanel.intYOUx > 600){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUx >= 80 && SNAKEpanel.intYOUx >= 50 && SNAKEpanel.intYOUy == 590){
					SNAKEpanel.intYOUx =SNAKEpanel.intYOUx - 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUx >= 80 && SNAKEpanel.intYOUx >= 50 && SNAKEpanel.intYOUy == 590){
					SNAKEpanel.intYOUx =SNAKEpanel.intYOUx - 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUy == 590 && SNAKEpanel.intYOUx <= 80){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUx <= 600  && SNAKEpanel.intYOUy == 530 ){
					SNAKEpanel.intYOUx =SNAKEpanel.intYOUx + 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUx <= 600  && SNAKEpanel.intYOUy == 530 ){
					SNAKEpanel.intYOUx = 350;
					SNAKEpanel.intYOUy = 230;
				}
				else if(SNAKEpanel.intYOUy == 530 && SNAKEpanel.intYOUx >=600){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUx >= 80  && SNAKEpanel.intYOUy == 470){
					SNAKEpanel.intYOUx =SNAKEpanel.intYOUx - 60; 
					System.out.println(SNAKEpanel.intYOUx); 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUy == 470 && SNAKEpanel.intYOUx <= 80){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 70; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUx <=590  && SNAKEpanel.intYOUy == 400){
					SNAKEpanel.intYOUx =SNAKEpanel.intYOUx + 60;
					System.out.println(SNAKEpanel.intYOUx);  
				}
				else if(SNAKEpanel.intYOUy == 400 && SNAKEpanel.intYOUx > 590){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 70; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUx >= 80  && SNAKEpanel.intYOUy == 330){
					SNAKEpanel.intYOUx =SNAKEpanel.intYOUx - 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUy == 330 && SNAKEpanel.intYOUx <= 80){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 70;
					System.out.println(SNAKEpanel.intYOUx);  
				}
				else if(SNAKEpanel.intYOUx <= 600  && SNAKEpanel.intYOUy == 260){
					SNAKEpanel.intYOUx =SNAKEpanel.intYOUx + 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUy ==  260 && SNAKEpanel.intYOUx >= 600){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 70; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUx >= 80  && SNAKEpanel.intYOUy == 190){
					SNAKEpanel.intYOUx =SNAKEpanel.intYOUx - 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUy ==  190 && SNAKEpanel.intYOUx <= 80){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 70;
					System.out.println(SNAKEpanel.intYOUx);  
				}
				else if(SNAKEpanel.intYOUy ==  120 && SNAKEpanel.intYOUx <=  600){
					SNAKEpanel.intYOUx = SNAKEpanel.intYOUx + 60; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				else if(SNAKEpanel.intYOUy ==  120 && SNAKEpanel.intYOUx >= 600){
					SNAKEpanel.intYOUy = SNAKEpanel.intYOUy - 70; 
					System.out.println(SNAKEpanel.intYOUx); 
				}
				
				else if(SNAKEpanel.intYOUy ==  50 && SNAKEpanel.intYOUx > 50){
					SNAKEpanel.intYOUx = SNAKEpanel.intYOUx - 60; 
					System.out.println(SNAKEpanel.intYOUx);
				}
				else if(SNAKEpanel.intYOUy ==  50 && SNAKEpanel.intYOUx < 50){
					SNAKEpanel.intYOUx = SNAKEpanel.intYOUx + 60; 
					System.out.println(SNAKEpanel.intYOUx);
			}
					
			}
			intXtotal = SNAKEpanel.intYOUx;
				if(intXtotal == 230 && SNAKEpanel.intYOUy == snakesLaddersArray [0]){	
					SNAKEpanel.intYOUx = 470;
					SNAKEpanel.intYOUy = 590;
					intdiesum = 0;
				}else if(intXtotal == 530 && SNAKEpanel.intYOUy == snakesLaddersArray [0]){	
					SNAKEpanel.intYOUx = 650;
					SNAKEpanel.intYOUy = 530;
					intdiesum = 0;
				}else if(intXtotal == 590 && SNAKEpanel.intYOUy == snakesLaddersArray [3]){	
					SNAKEpanel.intYOUx = 650;
					SNAKEpanel.intYOUy = 650;
					intdiesum = 0;
				}else if(intXtotal == 530 && SNAKEpanel.intYOUy == snakesLaddersArray [2]){	
					SNAKEpanel.intYOUx = 290;
					SNAKEpanel.intYOUy = 190;
					intdiesum = 0;
				}else if(intXtotal == 50 && SNAKEpanel.intYOUy == snakesLaddersArray [7]){	
					SNAKEpanel.intYOUx = 110;
					SNAKEpanel.intYOUy = 50;
					intdiesum = 0;
				}else if(intXtotal == 650 && SNAKEpanel.intYOUy == snakesLaddersArray [7]){	
					SNAKEpanel.intYOUx = 590;
					SNAKEpanel.intYOUy = 50;
					intdiesum = 0;
				}else if(intXtotal == 230 && SNAKEpanel.intYOUy == snakesLaddersArray [9]){	
					SNAKEpanel.intYOUx = 170;
					SNAKEpanel.intYOUy = 190;
					intdiesum = 0;
				}else if(intXtotal == 350 && SNAKEpanel.intYOUy == snakesLaddersArray [9]){	
					SNAKEpanel.intYOUx = 170;
					SNAKEpanel.intYOUy = 190;
					intdiesum = 0;
				}else if(intXtotal == 110 && SNAKEpanel.intYOUy == snakesLaddersArray [6]){	
					SNAKEpanel.intYOUx = 170;
					SNAKEpanel.intYOUy = 590;
					intdiesum = 0;
				}else if(intXtotal == 110 && SNAKEpanel.intYOUy == snakesLaddersArray [6]){	
					SNAKEpanel.intYOUx = 170;
					SNAKEpanel.intYOUy = 590;
					intdiesum = 0;
				}else if(intXtotal == 530 && SNAKEpanel.intYOUy == snakesLaddersArray [8]){	
					SNAKEpanel.intYOUx = 230;
					SNAKEpanel.intYOUy = 530;
					intdiesum = 0;
				}else if(intXtotal == 530 && SNAKEpanel.intYOUy == snakesLaddersArray [4]){	
					SNAKEpanel.intYOUx = 410;
					SNAKEpanel.intYOUy = 530;
					intdiesum = 0;
				}else if(intXtotal == 650 && SNAKEpanel.intYOUy == snakesLaddersArray [4]){	
					SNAKEpanel.intYOUx = 470;
					SNAKEpanel.intYOUy = snakesLaddersArray[6];
					intdiesum = 0;
				}else if(intXtotal == 50 && SNAKEpanel.intYOUy == snakesLaddersArray [2]){	
					SNAKEpanel.intYOUx = 110;
					SNAKEpanel.intYOUy = 400;
					intdiesum = 0;
				}else if(intXtotal == 290 && SNAKEpanel.intYOUy == snakesLaddersArray [3]){	
					SNAKEpanel.intYOUx = 350;
					SNAKEpanel.intYOUy = 650;
					intdiesum = 0;
				}
				else if(intXtotal == 50 && SNAKEpanel.intYOUy == snakesLaddersArray [9]){	
					thepanelinstructions.blnpage1 = false;
					thepanelinstructions.blnpage2 = true;
					thepanelinstructions.backtomain.setVisible(false);
					thepanelinstructions.Exit2Butt.setVisible(true);
					theframe.setContentPane(thepanelinstructions);
					theframe.setVisible(true);	
				}
				
			
				
				}
				
		
		
		//TIMER (REPAINT)
		//updating the animation panel using a timer
		else if(evt.getSource() == thetimer){
			SNAKEpanel.repaint(); 
			// determining whose turn it is!
			
			if(SNAKEpanel.intPlayer == SNAKEpanel.intTurn){
				SNAKEpanel.rolldie.setEnabled(true); 
			//	System.out.println("This is your colour" +monopolypanel.strColor); 
				if(blnDice == true){
					// sending colour
					ssm.sendText(SNAKEpanel.strColor); 
					// sending x-coordinate
					ssm.sendText(SNAKEpanel.intYOUx+""); 
					// sending y - coordinate
					ssm.sendText(SNAKEpanel.intYOUy+""); 
					// sending out text to say to change intTurn variable
					if(intRoll%2 == 0 && blnPlay == true && blnDice == true){
						 
						SNAKEpanel.intTurn = SNAKEpanel.intTurn +1; 
						System.out.println("Turn number before change over: "+SNAKEpanel.intTurn); 
						System.out.println("Player count: "+SNAKEpanel.intPlayerCount); 
						
						
						if(SNAKEpanel.intTurn < SNAKEpanel.intPlayerCount+1){
							ssm.sendText(SNAKEpanel.intTurn+""); 
							intRoll = intRoll + 1; 
							System.out.println("I got here");
							intPiece = 1; 
						}else if(SNAKEpanel.intTurn == SNAKEpanel.intPlayerCount +1){
							System.out.println("hereee you go"); 
							ssm.sendText("1");
							SNAKEpanel.intTurn = 1; 
							intPiece = 1; 
						}
						
						// set button to false after rolling:
						SNAKEpanel.rolldie.setEnabled(false);
						blnDice = false; 
					}
				}
			}
			else{
				SNAKEpanel.rolldie.setEnabled(false); 
			}	
				
	}
}
	
	//Constructor
	public Snake(){
		

		
		
		
	
		//set variables
		theframe = new JFrame("Snakes and Ladders");
		thepanel = new JPanel(); 
		thepanelinstructions = new snakeinstructions();
		playpage1 = new server();
		startpage = new start(); 
		joinpage = new join(); 
		SNAKEpanel = new SnakeGameplay();
		characterspanel = new snakeplayer();  
		
		//set panel
		thepanel.setPreferredSize(new Dimension(1280,720)); 
		thepanel.setLayout(null); 
		
		//instruction button 
		InstrucButt = new JButton("Instructions"); 
		InstrucButt.setSize(120, 30); 
		InstrucButt.setLocation(110,250); 
		InstrucButt.addActionListener(this);
	
		//play button 
		PlayButt = new JButton("Play"); 
		PlayButt.setSize(200, 35); 
		PlayButt.setLocation(75,200); 
		PlayButt.addActionListener(this); 
		
		//about button 
		AboutButt = new JButton("About"); 
		AboutButt.setSize(200, 35); 
		AboutButt.setLocation(75,500); 
		AboutButt.addActionListener(this); 
		
		//exit button
		ExitButt = new JButton("Exit"); 
		ExitButt.setSize(80, 30); 
		ExitButt. setLocation(1200, 0); 
		ExitButt.addActionListener(this);
		
		//insert image
		/** This inserts an image into the main menu */
		ImageIcon icon = new ImageIcon("Homepage2.jpg"); 
		JLabel label = new JLabel(icon);
		label.setSize(1280,720);  
		label.setLocation(0,0); 
		
		//INSTRUCTIONS SCREEN (ACTIONLISTENER)
		thepanelinstructions.setLayout(null);
		thepanelinstructions.backtomain.addActionListener(this);
		thepanelinstructions.topage2.addActionListener(this);
		thepanelinstructions.backtopage1.addActionListener(this);
		thepanelinstructions.topage3.addActionListener(this);
		thepanelinstructions.backtopage2.addActionListener(this);
		thepanelinstructions.topage4.addActionListener(this);
		thepanelinstructions.backtopage3.addActionListener(this);
		thepanelinstructions.topage5.addActionListener(this);
		thepanelinstructions.backtopage4.addActionListener(this);
		thepanelinstructions.tomain5.addActionListener(this);
		
		// PLAY PAGE 1
		playpage1.setLayout(null); 
		playpage1.back.addActionListener(this); 
		playpage1.start.addActionListener(this);
		playpage1.existing.addActionListener(this);  
		
		// Start game: 
		startpage.setLayout(null); 
		startpage.back.addActionListener(this); 
		startpage.gameplay.addActionListener(this); 
		startpage.TFname.addActionListener(this); 
		
		// Join game: 
		joinpage.setLayout(null); 
		joinpage.back.addActionListener(this); 
		joinpage.TF.addActionListener(this); 
		joinpage.gameplay.addActionListener(this); 
		joinpage.TFname.addActionListener(this); 
		
		// characters panel:
		characterspanel.setLayout(null); 
		characterspanel.select1.addActionListener(this); 
		characterspanel.select2.addActionListener(this); 
		characterspanel.select3.addActionListener(this); 
		characterspanel.select4.addActionListener(this); 
		characterspanel.gameplay.addActionListener(this); 
		characterspanel.gameplay.setEnabled(false); 
		
		// PLAY PAGE: 
		SNAKEpanel.setLayout(null); 
		SNAKEpanel.textfield.addActionListener(this);
		
		//add features
		thepanel.add(InstrucButt);
		thepanel.add(PlayButt); 
		thepanel.add(ExitButt);
		thepanel.add(AboutButt);
		thepanel.add(label); 
		
		// Add features: Instructions Screen
		thepanelinstructions.add(thepanelinstructions.backtomain);
		thepanelinstructions.add(thepanelinstructions.topage2);
		thepanelinstructions.add(thepanelinstructions.textfield);
		thepanelinstructions.add(thepanelinstructions.rolldie);
		thepanelinstructions.add(thepanelinstructions.Exit2Butt);
		
		
		// Add features: Play page 1 :
		playpage1.add(playpage1.back); 
		playpage1.add(playpage1.start); 
		playpage1.add(playpage1.existing); 
		
		// Add features: Join page and start page
		joinpage.add(joinpage.back); 
		startpage.add(startpage.back); 
		joinpage.add(joinpage.TF); 
		startpage.add(startpage.gameplay); 
		joinpage.add(joinpage.gameplay); 
		startpage.add(startpage.TFname); 
		joinpage.add(joinpage.TFname); 
		
		//add features: to character page
		characterspanel.add(characterspanel.select1); 
		characterspanel.add(characterspanel.select2); 
		characterspanel.add(characterspanel.select3); 
		characterspanel.add(characterspanel.select4); 
		characterspanel.add(characterspanel.gameplay); 
		
		// Add features: to play page (ACTUAL GAMEPLAY)
		SNAKEpanel.add(SNAKEpanel.scroll); 
		SNAKEpanel.add(SNAKEpanel.textfield);
		SNAKEpanel.add(SNAKEpanel.rolldie);
	
		
		// Game play - ACTION LISTENER
		SNAKEpanel.rolldie.addActionListener(this);
		
		// timer: 
		//Timer plays throughout the whole entirety of the game 
		thetimer = new Timer(1000/60, this);
		thetimer.start(); 
		
		//set frame
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		theframe.setVisible(true);
		// prevents windows from being resized
		theframe.setResizable(false); 	
	
	}
	
	
	public static void main(String [] args){
		new Snake(); 
	}
}




