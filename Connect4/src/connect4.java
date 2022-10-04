import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
//import java.*;

public class connect4 {
    //Board size
    static int X = 6;
    static int Y = 7;
    //This variable can be used to turn your debugging output on and off. Preferably use 
    static boolean DEBUG = false;
    public static void main (String[] args) {
    	
    	boolean gui = false;
    	    	if (args[0].equals("G")) {
    	    		gui = true;
    	    	}

        int input = 0;
        int pos = -1;
        //The 6-by-7 grid that represents the gameboard, it is initially empty
        int [][] grid = new int [X][Y];
        //Shows which player's turn it is, true for player 1 and false for player 2
        boolean player1 = true;
        boolean startp = true;
        //Shows the number of special tokens a player has left
        int [] p1powers = {2, 2, 2};
        int [] p2powers = {2, 2, 2};      
        
        if (!gui) {
        	 //Terminal mode
        	StdOut.println("****************************************************************************");
	        StdOut.println("*  _______  _______  __    _  __    _  _______  _______  _______  _   ___  *"+
						"\n* |       ||       ||  |  | ||  |  | ||       ||       ||       || | |   | *"+
						"\n* |       ||   _   ||   |_| ||   |_| ||    ___||       ||_     _|| |_|   | *"+
						"\n* |       ||  | |  ||       ||       ||   |___ |       |  |   |  |       | *"+
						"\n* |      _||  |_|  ||  _    ||  _    ||    ___||      _|  |   |  |___    | *"+
						"\n* |     |_ |       || | |   || | |   ||   |___ |     |_   |   |      |   | *"+
						"\n* |_______||_______||_|  |__||_|  |__||_______||_______|  |___|      |___| *");
	        StdOut.println("*                                                                          *");
	        StdOut.println("****************************************************************************");
        
            //Array for current player's number of power storage
            int [] curppowers = new int[3];
            while (true) {
                if (player1) {
                    StdOut.println("Player 1's turn (You are Red):");
                    curppowers = p1powers;
                } else {
                    StdOut.println("Player 2's turn (You are Yellow):");
                    curppowers = p2powers;
                }
                StdOut.println("Choose your move: \n 1. Play Normal \n 2. Play Bomb ("+curppowers[0]+" left) \n 3. Play Teleportation ("+curppowers[1]+" left) \n 4. Play Colour Changer ("+curppowers[2]+" left)\n 5. Display Gameboard \n 6. Load Test File \n 0. Exit");
                try{
                    input = StdIn.readInt();
                } catch (Exception ex){
                    input = -1;
                }
                
                switch(input) {
                    case 0: Exit();
                            break;

                    case 1: StdOut.println("Choose a column to play in:");
                    		
                    		while(true) {
			                    try{
			                        input = StdIn.readInt();
		                    		if(input>=0 && input<7) {
		                        		break;
		                        	}
		                    		System.out.println("Illegal move, please input legal move:");
			                    } catch (Exception ex){
			                    	System.out.println("Illegal move, please input legal move:");
			                    }
                    		}
                    		
                    		
                    		Play (input, grid, player1);
                            
                    		break;

                    case 2: StdOut.println("Choose a column to play in:");
                    		
		                    while(true) {
			                    try{
			                        input = StdIn.readInt();
			                        if(input>=0 && input<7) {
		                        		break;
		                        	}
		                    		System.out.println("Illegal move, please input legal move:");
			                    } catch (Exception ex){
			                    	System.out.println("Illegal move, please input legal move:");
			                    }
		            		}
                    		
                    		if(player1 && p1powers[0]>0) {
                    			curppowers[0] = p1powers[0]-1;
                    			Bomb (input, grid, player1);
                    		}else if (!player1 && p2powers[0]>0){
                    			curppowers[0] = p2powers[0]-1;
                    			Bomb (input, grid, player1);
                    		}else if(p1powers[0]==0 || p2powers[0]==0) {
                    			StdOut.println("\n" + "No Bombs remaining" + "\n");
                    		}
                    		
                            break;

                    case 3: StdOut.println("Choose a column to play in:");
                    		
		                    while(true) {
			                    try{
			                        input = StdIn.readInt();
			                        if(input>=0 && input<7) {
		                        		break;
		                        	}
		                    		System.out.println("Illegal move, please input legal move:");
			                    } catch (Exception ex){
			                    	System.out.println("Illegal move, please input legal move:");
			                    }
		            		}
                   			
                   			if(player1 && p1powers[1]>0) {
                    			curppowers[1] = p1powers[1]-1;
                    			Teleport (input, grid, player1);
                    		}else if (!player1 && p2powers[1]>0){
                    			curppowers[1] = p2powers[1]-1;
                    			Teleport (input, grid, player1);
                    		}else if(p1powers[1]==0 || p2powers[1]==0) {
                    			StdOut.println("\n" + "No Teleporters remaining" + "\n");
                    		}
                    		
                            break;

                    case 4: StdOut.println("Choose a column to play in:");
                    		
		                    while(true) {
			                    try{
			                        input = StdIn.readInt();
			                        if(input>=0 && input<7) {
		                        		break;
		                        	}
		                    		System.out.println("Illegal move, please input legal move:");
			                    } catch (Exception ex){
			                    	System.out.println("Illegal move, please input legal move:");
			                    }
		            		}
                    		if(player1 && p1powers[2]>0) {
                    			curppowers[2] = p1powers[2]-1;
                    			Colour_Changer (input, grid, player1);
                    		}else if (!player1 && p2powers[2]>0){
                    			curppowers[2] = p2powers[2]-1;
                    			Colour_Changer (input, grid, player1);
                    		}else if(p1powers[2]==0 || p2powers[2]==0) {
                    			StdOut.println("\n" + "No Colour Changers remaining" + "\n");
                    		}
                            break;

					//This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 5: Display(grid);
                    		//Displays the current gameboard again, doesn't count as a move, so the player stays the same
                            player1 = !player1;
                            break;

					//This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 6: grid = Test(StdIn.readString());
                    		//Reads in a test file and loads the gameboard from it.
                            player1 = !player1;
                            break;
                            
                    case 7: Save(StdIn.readString(), grid);
            				player1 = !player1;
            				break;

                    default: 
                    	StdOut.println("Please choose a valid option");
                    	player1=!player1;
						break;
                }
				//Displays the grid after a new move was played
                	Display(grid);
                
				//Checks whether a winning condition was met
                int win = Check_Win(grid);
                if(win!=0) {
	                if(win==1 || win==2) {
		                StdOut.println("Player " + win + " wins!" + "\nDo you want to play again?" + "\n 1. Yes\n 2. No");
	                }else if(win==3) {
	                	StdOut.println("Draw!" + "\nDo you want to play again?" + "\n 1. Yes\n 2. No");
	                }
	                int yn = 0;
	                while(true) {
		                try{
		                    yn = StdIn.readInt();
		                    Debug(yn+"");
		                    if(yn==1) {
			            		for(int p=0; p<3; p++) {
			            			p1powers[p]=2;
			            			p2powers[p]=2;
			            		}
			            		grid = new int [X][Y];
			            		player1=startp;
			            		startp=!startp;
			                    break;
			                   }else if(yn==2){
			//                	   StdOut.println("GOODBYE AND THANK YOU FOR PLAYING!");
			                   		System.exit(0);
			                   }
		                } catch (Exception ex){
		                	Debug(ex+"");
		                	StdOut.println("Choose either 1 for Yes or 2 for No.");
		                }
	                }
	                
                }
            	
                player1 = !player1;
                
            }
        } else {
            //Graphics mode
        	
        	StdDraw.setCanvasSize(1000, 675);
        	int[][] guigrid = new int[600][700];
//        	player1=true;
        	int [] curppowers = new int[3];
            while (true) {
	        	StdDraw.enableDoubleBuffering();
	           	guidisplay(guigrid);
                
                StdDraw.setPenColor(StdDraw.BLACK);
                if (player1) {
                    StdDraw.textLeft(50, 650, "Player 1's turn (You are Red):");
                    curppowers = p1powers;
                } else {
                    StdDraw.textLeft(50, 650, "Player 2's turn (You are Yellow):");
                    curppowers = p2powers;
                }
                StdDraw.textLeft(350, 650, "0. Exit");
                
                	StdDraw.textLeft(350, 625, "Choose a column to play in:");
                	
                	if(player1) {
                		StdDraw.textLeft(750, 450, "Hold and select column: ");
                    	StdDraw.textLeft(750, 400, "B to play a Bomb (" + p1powers[0] + ")");
                    	StdDraw.textLeft(750, 350, "T to play a Teleporter (" + p1powers[1] + ")");
                    	StdDraw.textLeft(750, 300, "C to play a Colour Changer (" + p1powers[2] + ")");
                	}else {
                		StdDraw.textLeft(750, 450, "Hold and select column: ");
                    	StdDraw.textLeft(750, 400, "B to play a Bomb (" + p2powers[0] + ")");
                    	StdDraw.textLeft(750, 350, "T to play a Teleporter (" + p2powers[1] + ")");
                    	StdDraw.textLeft(750, 300, "C to play a Colour Changer (" + p2powers[2] + ")");
                	}
        			
        			if(StdDraw.isKeyPressed(97) || StdDraw.isKeyPressed(49) || (StdDraw.isMousePressed() && StdDraw.mouseX()<100)){
        				guiplay(50, guigrid, player1);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(98) || StdDraw.isKeyPressed(50) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<200 && StdDraw.mouseX()>100))){
        				guiplay(150, guigrid, player1);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(99) || StdDraw.isKeyPressed(51) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<300 && StdDraw.mouseX()>200))){
        				guiplay(250, guigrid, player1);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(100) || StdDraw.isKeyPressed(52) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<400 && StdDraw.mouseX()>300))){
        				guiplay(350, guigrid, player1);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(101) || StdDraw.isKeyPressed(53) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<500 && StdDraw.mouseX()>400))){
        				guiplay(450, guigrid, player1);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(102) || StdDraw.isKeyPressed(54) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<600 && StdDraw.mouseX()>500))){
        				guiplay(550, guigrid, player1);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(103) || StdDraw.isKeyPressed(55) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<700 && StdDraw.mouseX()>600))){
        				guiplay(650, guigrid, player1);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}
            if(StdDraw.isKeyPressed(66)) { //bomb "b"
            		
        		StdDraw.setPenColor(StdDraw.BOOK_RED);
            	StdDraw.rectangle(850, 400, 125, 11);
            	StdDraw.pause(100);
            	
    			if(StdDraw.isKeyPressed(97) || StdDraw.isKeyPressed(49) || (StdDraw.isMousePressed() && StdDraw.mouseX()<100)){
    				playgb(player1, curppowers, p1powers, p2powers, guigrid, 50);
    				StdDraw.pause(50);
    				guidisplay(guigrid);
    				player1=!player1;
    				StdDraw.pause(100);
    				
    			}else if(StdDraw.isKeyPressed(98) || StdDraw.isKeyPressed(50) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<200 && StdDraw.mouseX()>100))){
    				playgb(player1, curppowers, p1powers, p2powers, guigrid, 150);
    				StdDraw.pause(50);
    				guidisplay(guigrid);
    				player1=!player1;
    				StdDraw.pause(100);
    				
    			}else if(StdDraw.isKeyPressed(99) || StdDraw.isKeyPressed(51) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<300 && StdDraw.mouseX()>200))){
    				playgb(player1, curppowers, p1powers, p2powers, guigrid, 250);
    				StdDraw.pause(50);
    				guidisplay(guigrid);
    				player1=!player1;
    				StdDraw.pause(100);
    				
    			}else if(StdDraw.isKeyPressed(100) || StdDraw.isKeyPressed(52) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<400 && StdDraw.mouseX()>300))){
    				playgb(player1, curppowers, p1powers, p2powers, guigrid, 350);
    				StdDraw.pause(50);
    				guidisplay(guigrid);
    				player1=!player1;
    				StdDraw.pause(100);
    				
    			}else if(StdDraw.isKeyPressed(101) || StdDraw.isKeyPressed(53) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<500 && StdDraw.mouseX()>400))){
    				playgb(player1, curppowers, p1powers, p2powers, guigrid, 450);
    				StdDraw.pause(50);
    				guidisplay(guigrid);
    				player1=!player1;
    				StdDraw.pause(100);
    				
    			}else if(StdDraw.isKeyPressed(102) || StdDraw.isKeyPressed(54) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<600 && StdDraw.mouseX()>500))){
    				playgb(player1, curppowers, p1powers, p2powers, guigrid, 550);
    				StdDraw.pause(50);
    				guidisplay(guigrid);
    				player1=!player1;
    				StdDraw.pause(100);
    				
    			}else if(StdDraw.isKeyPressed(103) || StdDraw.isKeyPressed(55) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<700 && StdDraw.mouseX()>600))){
    				playgb(player1, curppowers, p1powers, p2powers, guigrid, 650);
    				StdDraw.pause(50);
    				guidisplay(guigrid);
    				player1=!player1;
    				StdDraw.pause(100);
    				
    			}
            }
            
            
                if(StdDraw.isKeyPressed(84)) { //teleporter "t"
                	
                	StdDraw.setPenColor(StdDraw.BOOK_RED);
                	StdDraw.rectangle(850, 350, 125, 11);
                	StdDraw.pause(100);
                	
        			if(StdDraw.isKeyPressed(97) || StdDraw.isKeyPressed(49) || (StdDraw.isMousePressed() && StdDraw.mouseX()<100)){
        				playgt(player1, curppowers, p1powers, p2powers, guigrid, 50);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(98) || StdDraw.isKeyPressed(50) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<200 && StdDraw.mouseX()>100))){
        				playgt(player1, curppowers, p1powers, p2powers, guigrid, 150);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(99) || StdDraw.isKeyPressed(51) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<300 && StdDraw.mouseX()>200))){
        				playgt(player1, curppowers, p1powers, p2powers, guigrid, 250);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(100) || StdDraw.isKeyPressed(52) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<400 && StdDraw.mouseX()>300))){
        				playgt(player1, curppowers, p1powers, p2powers, guigrid, 350);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(101) || StdDraw.isKeyPressed(53) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<500 && StdDraw.mouseX()>400))){
        				playgt(player1, curppowers, p1powers, p2powers, guigrid, 450);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(102) || StdDraw.isKeyPressed(54) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<600 && StdDraw.mouseX()>500))){
        				playgt(player1, curppowers, p1powers, p2powers, guigrid, 550);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(103) || StdDraw.isKeyPressed(55) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<700 && StdDraw.mouseX()>600))){
        				playgt(player1, curppowers, p1powers, p2powers, guigrid, 650);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}
                }
                
                
                if(StdDraw.isKeyPressed(67)) { //colour changer "c"
                	
                	StdDraw.setPenColor(StdDraw.BOOK_RED);
                	StdDraw.rectangle(850, 300, 125, 11);
                	StdDraw.pause(100);
                	
        			if(StdDraw.isKeyPressed(97) || StdDraw.isKeyPressed(49) || (StdDraw.isMousePressed() && StdDraw.mouseX()<100)){
        				playgcc(player1, curppowers, p1powers, p2powers, guigrid, 50);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(98) || StdDraw.isKeyPressed(50) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<300 && StdDraw.mouseX()>200))){
        				playgcc(player1, curppowers, p1powers, p2powers, guigrid, 150);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(99) || StdDraw.isKeyPressed(51) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<400 && StdDraw.mouseX()>300))){
        				playgcc(player1, curppowers, p1powers, p2powers, guigrid, 250);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(100) || StdDraw.isKeyPressed(52) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<500 && StdDraw.mouseX()>400))){
        				playgcc(player1, curppowers, p1powers, p2powers, guigrid, 350);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(101) || StdDraw.isKeyPressed(53) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<600 && StdDraw.mouseX()>500))){
        				playgcc(player1, curppowers, p1powers, p2powers, guigrid, 450);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(102) || StdDraw.isKeyPressed(54) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<600 && StdDraw.mouseX()>500))){
        				playgcc(player1, curppowers, p1powers, p2powers, guigrid, 550);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}else if(StdDraw.isKeyPressed(103) || StdDraw.isKeyPressed(55) || (StdDraw.isMousePressed() && (StdDraw.mouseX()<700 && StdDraw.mouseX()>600))){
        				playgcc(player1, curppowers, p1powers, p2powers, guigrid, 650);
        				StdDraw.pause(50);
        				guidisplay(guigrid);
        				player1=!player1;
        				StdDraw.pause(100);
        				
        			}
        			
                }
    			
			if(StdDraw.isKeyPressed(96)) {
				Exit();
			
			}
                
            	int win=guiwin(guigrid);
            	if (win!=0) {
            		StdDraw.setPenColor(StdDraw.BLUE);
        			StdDraw.filledRectangle(350, 640, 350, 40);
        			StdDraw.setPenColor(StdDraw.BLACK);
            	}
            	if(win==1 || win==2) {
        			StdDraw.textLeft(300, 650, "Player " + win + " Wins!");
        			guiwinoption();
        			if(StdDraw.isKeyPressed(97)) {
        				guigrid = new int [600][700];
        				for(int p=0; p<3; p++) {
	            			p1powers[p]=2;
	            			p2powers[p]=2;
	            		}
        				guidisplay(guigrid);
        				StdDraw.pause(500);
        			}else if(StdDraw.isKeyPressed(96)) {
        				Exit();
        			}
            	}else if(win==3) {
        			StdDraw.textLeft(300, 650, "DRAW!");
        			guiwinoption();
        			if(StdDraw.isKeyPressed(97)) {
        				guigrid = new int [600][700];
        				for(int p=0; p<3; p++) {
	            			p1powers[p]=2;
	            			p2powers[p]=2;
	            		}
        				guidisplay(guigrid);
        				StdDraw.pause(500);
        			}else if(StdDraw.isKeyPressed(96)) {
        				Exit();
        			}
            	}
                StdDraw.show();
            }
        }
    }
    

    public static void Display (int [][] grid) {

    	System.out.println("\n  0 1 2 3 4 5 6");	
    	for(int j=0; j<grid.length; j++) {			//j is the row number 
    		System.out.print(j + " ");
    		for(int i=0; i<grid.length+1; i++) {	//i is the column number
    			if(grid[j][i] == 1) {
    				System.out.print("R ");    				
    			}else if (grid[j][i] == 2) {
    				System.out.print("Y ");
    			}else {
    				System.out.print("* ");
    			}
    		}
    		System.out.println();
    	}
    	System.out.println();
    }

    public static void Exit() {

//    	StdOut.println("GOODBYE AND HAVE A GREAT DAY!");
    	System.exit(0);
    }

    public static int [][] Play (int i, int [][] grid, boolean player1) {
    	
    	for(int j= 5; j>=0; j--) { //j is the row number; i is the column number
    		if(grid[j][i]==0) {
    			if(player1) {
    				grid[j][i]=1;    				
    			}else {
    				grid[j][i] = 2;
    			}
    			break;
    		}else if(grid[0][i]!=0) {
    			System.out.println("Column is full.");
    			break;
    		}
    	}
    		
        return grid;
    }

    public static int Check_Win (int [][] grid) {
        int winner = 0;
        
        int count1 = 0;
        int count2=0;
        
        int i = 0; //rows
        int j = 0; //column
        //check vertical
        for(i=0; i<=2; i++) {
        	for(j=0; j<7; j++) {
        		if((grid[i][j]!=0) && (grid[i][j]==grid[i+1][j]) && (grid[i][j]==grid[i+2][j]) && (grid[i][j]==grid[i+3][j])){
        			if(grid[i][j]==1) {
        				count1++;
        			}else if(grid[i][j]==2) {
        				count2++;
        			}
        		}
        	}
        }
        //check horizontal
        for(i=0; i<6; i++) {
        	for(j=0; j<=3; j++) {
        		if((grid[i][j]!=0) && (grid[i][j]==grid[i][j+1]) && (grid[i][j]==grid[i][j+2]) && (grid[i][j]==grid[i][j+3])){
        			if(grid[i][j]==1) {
        				count1++;
        			}else if(grid[i][j]==2) {
        				count2++;
        			}
        		}
        	}
        }
        //check diagonal up
        for(i=5; i>=3; i--) {
        	for(j=0; j<=3; j++) {
        		if((grid[i][j]!=0) && (grid[i][j]==grid[i-1][j+1]) && (grid[i][j]==grid[i-2][j+2]) && (grid[i][j]==grid[i-3][j+3])){
        			if(grid[i][j]==1) {
        				count1++;
        			}else if(grid[i][j]==2) {
        				count2++;
        			}
        		}
        	}
        }
      //check diagonal down:
        for(i=0; i<=2; i++) {
        	for(j=0; j<=3; j++) {
        		if((grid[i][j]!=0) && (grid[i][j]==grid[i+1][j+1]) && (grid[i][j]==grid[i+2][j+2]) && (grid[i][j]==grid[i+3][j+3])){
        			if(grid[i][j]==1) {
        				count1++;
        			}else if(grid[i][j]==2) {
        				count2++;
        			}
        		}
        	}
        }
        //check draw; no wins
        int full=0;
        for(j=0; j<7; j++) {
	        if(grid[0][j]!=0) {
				full++;
			}
        }
        if(full==7) {
        	winner=3;
        }
        if(count1>0 && count2>0) {
        	winner=3;
        }else if(count1>0 && count2==0) {
        	winner=1;
        }else if(count2>0 && count1==0) {
        	winner=2;
        }

        return winner;
    }

    public static int [][] Bomb (int i, int [][] grid, boolean player1) {
    	//i column
    	//j row
    	for(int j=5; j>=0; j--) {
    		if(grid[j][i]==0) {
	    		if(j==5) {
	    			if(i==0) {
	    				Play(i, grid, player1);
		    			grid[j][i+1]=grid[j-2][i+1];
		    			grid[j-1][i+1]=grid[j-3][i+1];
		    			grid[j-2][i+1]=grid[j-4][i+1];
		    			grid[j-3][i+1]=grid[j-5][i+1];
		    			
		    			grid[j-4][i+1]=0;
		    			grid[j-5][i+1]=0;
	    			}else if(i==6) {
	    				Play(i, grid, player1);
		    			grid[j][i-1]=grid[j-2][i-1];
		    			grid[j-1][i-1]=grid[j-3][i-1];
		    			grid[j-2][i-1]=grid[j-4][i-1];
		    			grid[j-3][i-1]=grid[j-5][i-1];
		    			
		    			grid[j-4][i-1]=0;
		    			grid[j-5][i-1]=0;
	    			}else {
	    				
	    			Play(i, grid, player1);
	    			grid[j][i+1]=grid[j-2][i+1];
	    			grid[j][i-1]=grid[j-2][i-1];
	    			grid[j-1][i+1]=grid[j-3][i+1];
	    			grid[j-1][i-1]=grid[j-3][i-1];
	    			grid[j-2][i+1]=grid[j-4][i+1];
	    			grid[j-2][i-1]=grid[j-4][i-1];
	    			grid[j-3][i+1]=grid[j-5][i+1];
	    			grid[j-3][i-1]=grid[j-5][i-1];
	    			
	    			grid[j-4][i+1]=0;
	    			grid[j-4][i-1]=0;
	    			grid[j-5][i+1]=0;
	    			grid[j-5][i-1]=0;

	    			}
	    			
	    			break;
	    		}else if(j+1<=5 && j-4>=0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j][i+1]=grid[j-3][i+1];
		    			grid[j-1][i+1]=grid[j-4][i+1];
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-3][i+1]=0;
		    			grid[j-4][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			grid[j][i-1]=grid[j-3][i-1];
		    			grid[j-1][i-1]=grid[j-4][i-1];
		    			
		    			grid[j-2][i-1]=0;
		    			grid[j-3][i-1]=0;
		    			grid[j-4][i-1]=0;
	    			}else {
		    			grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			grid[j][i+1]=grid[j-3][i+1];
		    			grid[j][i-1]=grid[j-3][i-1];
		    			grid[j-1][i+1]=grid[j-4][i+1];
		    			grid[j-1][i-1]=grid[j-4][i-1];
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-2][i-1]=0;
		    			grid[j-3][i+1]=0;
		    			grid[j-3][i-1]=0;
		    			grid[j-4][i+1]=0;
		    			grid[j-4][i-1]=0;

	    			}
	    			
	    			break;
	    		}else if(j-4>0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j][i+1]=grid[j-3][i+1];
		    			
		    			grid[j-1][i+1]=0;
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-3][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			grid[j][i-1]=grid[j-3][i-1];
		    			
		    			grid[j-1][i-1]=0;
		    			
		    			grid[j-2][i-1]=0;
		    			grid[j-3][i-1]=0;
		    			
	    			}else {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			grid[j][i+1]=grid[j-3][i+1];
		    			grid[j][i-1]=grid[j-3][i-1];
		    			
		    			grid[j-1][i+1]=0;
		    			grid[j-1][i-1]=0;
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-2][i-1]=0;
		    			grid[j-3][i+1]=0;
		    			grid[j-3][i-1]=0;
		    			
	    			}
	    			
	    			break;
	    		}else if(j-3>0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			
		    			grid[j][i+1]=0;
		    			grid[j-1][i+1]=0;
		    			
		    			grid[j-2][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			
		    			grid[j][i-1]=0;
		    			grid[j-1][i-1]=0;
		    			
		    			grid[j-2][i-1]=0;
		    			
	    			}else {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			
		    			grid[j][i+1]=0;
		    			grid[j][i-1]=0;
		    			grid[j-1][i+1]=0;
		    			grid[j-1][i-1]=0;
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-2][i-1]=0;
		    			
	    			}
	    			
	    			break;
	    		}else if(j-2>0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=0;
		    			grid[j][i+1]=0;
		    			grid[j-1][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=0;
		    			grid[j][i-1]=0;
		    			grid[j-1][i-1]=0;
		    			
	    			}else {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=0;
		    			grid[j+1][i-1]=0;
		    			grid[j][i+1]=0;
		    			grid[j][i-1]=0;
		    			grid[j-1][i+1]=0;
		    			grid[j-1][i-1]=0;
		    			
	    			}
	    			
	    			break;
	    		}else if(j==0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=0;
		    			grid[j][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=0;
		    			grid[j][i-1]=0;
		    			
	    			}else {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=0;
		    			grid[j+1][i-1]=0;
		    			grid[j][i+1]=0;
		    			grid[j][i-1]=0;
		    			
	    			}
	    			
	    			break;
	    		}
	    		
	    		
	    	}else if(grid[0][i]!=0) {
	    		System.out.println("Column is full.");
    			break;
	    	}
    	}
    	
        return grid;
    }

    public static int [][] Teleport (int i, int [][] grid, boolean player1) {

    	for(int j=X-1; j>=0; j--) {
    		boolean old=false;
    		if(j==5 && grid[j][i]==0) {
    			grid[j][i]=0;
    			break;
    		}else if(j<5 && grid[j][i]==0){ //fixing play on full column and play to full column
    			if(grid[j+1][i]==1) { //grid[j+1][i]==1 || 
					old=true;
				}else if(grid[j+1][i]==2) {
					old=false;
				}
    			if(i<4 && grid[j][i+3]==0) {
    				Play(i+3, grid, old);
    				grid[j+1][i]=0;
    				Play(i, grid, player1);
    			}else if(i>3 && grid[j][i-4]==0) {
    				Play(i-4, grid, old);
    				grid[j+1][i]=0;
    				Play(i, grid, player1);
    			}
    			break;
    		}else if(grid[0][i]!=0) {
    			if(grid[0][i]==1) { //grid[j+1][i]==1 || 
					old=true;
				}else if(grid[0][i]==2) {
					old=false;
				}
    			if(i<4 && grid[j][i+3]==0) {
    				Play(i+3, grid, old);
    				grid[0][i]=0;
    				Play(i, grid, player1);
    			}else if(i>3 && grid[j][i-4]==0) {
    				Play(i-4, grid, old);
    				grid[0][i]=0;
    				Play(i, grid, player1);
    			}
    			break;
    		}
    		if(i<4) {
    			if(grid[0][i+3]!=0){
    				Colour_Changer(i, grid, player1);
    				System.out.println("Column is full.");
    				break;
        		}
    		}else if(i>3) {
    			if(grid[0][i-4]!=0) {
    				Colour_Changer(i, grid, player1);
    				System.out.println("Column is full.");
    				break;
    			}
    		}
    		
    	}
    	return grid;
    }

    public static int [][] Colour_Changer (int i, int [][] grid, boolean player1) {
    	//int i = column
    	//int j = row
    	try{
    		for(int j= 5; j>=0; j--) { 
	    		if(grid[j][i]==0) {
	    			if(player1) {
	    				grid[j][i]=1;    		
	    			}else {
	    				grid[j][i] = 2;
	    			}
	    			if(grid[j+1][i]==1) {
	        			grid[j+1][i]=2;
	        		}else if(grid[j+1][i]==2) {
	        			grid[j+1][i]=1;
	        		}
	    			grid[j][i]=0;
	    			break;
	    		}else if(grid[0][i]==1) {
	    			grid[0][i]=2;
	    			break;
	    		}else if(grid[0][i]==2) {
	    			grid[0][i]=1;
	    			break;
	    		}
	    	}
    	}catch(Exception ex) {
    		if(player1) {
    			grid[5][i]=1;
    		}else {
    			grid[5][i]=2;
    		}
    	}
        return grid;
    }

    public static int [][] Test(String name) {
        int [][] grid = new int[6][7];
        try{
            File file = new File(name+".txt");
            Scanner sc = new Scanner(file);

            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return grid;
    }
    
    public static void Debug(String line) {
        if (DEBUG)
            System.out.println(line);
    }
    
    public static int [][] Save(String name, int [][] grid) {
    	try{
    		FileWriter fileWriter = new FileWriter(name+".txt");
	    	for (int i = 0; i < X; i++) {
	    		for (int j = 0; j < Y; j++) {
		    		fileWriter.write(Integer.toString(grid[i][j]) + " ");
		    	}
		    	fileWriter.write("\n");
		    }
		    fileWriter.close();
	    } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return grid;
    }
    
    public static void guimenu() {
    	StdDraw.setPenColor(StdDraw.BOOK_BLUE);
    	StdDraw.filledRectangle(850, 350, 150, 350);
    	StdDraw.setPenColor(StdDraw.BLACK);
    	StdDraw.text(850, 650, "WELCOME TO CONNECT 4!");
    }
    
    public static void guidisplay(int[][] guigrid) {
    	
    	//Draw Blue box
    	StdDraw.setPenColor(StdDraw.BLUE);
    	StdDraw.setXscale(0, 1000);
    	StdDraw.setYscale(0, 675);
    	StdDraw.filledRectangle(350, 250, 675, 500);
    	guimenu();
    	
    	//draw circles
    	for(int i=50; i<700; i+=100) { //column
    		StdDraw.setPenColor(StdDraw.BLACK);
    		StdDraw.text(i, 605, " " + ((i+50)/100) + " ");
    		for(int j=50; j<600; j+=100) { //row
    			if(guigrid[j][i]==0) {
    				StdDraw.setPenColor(195, 195, 195);
    				StdDraw.filledCircle(i, j, 45);
    			}else if(guigrid[j][i]==1) {
    				StdDraw.setPenColor(StdDraw.RED);
    				StdDraw.filledCircle(i, j, 45);
    				StdDraw.setPenColor(StdDraw.BOOK_RED);
    				StdDraw.filledCircle(i, j, 40);
    			}else if(guigrid[j][i]==2) {
    				StdDraw.setPenColor(StdDraw.YELLOW);
    				StdDraw.filledCircle(i, j, 45);
    				StdDraw.setPenColor(225, 165, 0); //225, 242, 0);
    				StdDraw.filledCircle(i, j, 40);
    			}
    		}
    	}
    }
    
    public static int[][] guiplay(int i, int[][] guigrid, boolean player1){
    	StdDraw.setScale(700, 600);
    	for(int j=50; j<600; j+=100) { //j is the row number; i is the column number
    		if(guigrid[j][i]==0) {
    			if(player1) {
    				guigrid[j][i]=1;
    				break;
    			}else {
    				guigrid[j][i]=2;
    				break;
    			}
    		}else if(guigrid[550][i]!=0) {
    			StdDraw.setPenColor(StdDraw.BLACK);
    			StdDraw.textLeft(50, 625, "Column full");
    			StdDraw.pause(100);
    			break;
    		}
    		StdDraw.show();
    	}
    	StdDraw.show();
    	return guigrid;
    }

    public static int guiwin(int[][] guigrid) {
    	int winner=0;
    	 int count1 = 0;
         int count2=0;
         
         int i = 0; //rows
         int j = 0; //column
         //check vertical
         for(i=50; i<=250; i+=100) {
         	for(j=50; j<700; j+=100) {
         		if((guigrid[i][j]!=0) && (guigrid[i][j]==guigrid[i+100][j]) && (guigrid[i][j]==guigrid[i+200][j]) && (guigrid[i][j]==guigrid[i+300][j])){
         			if(guigrid[i][j]==1) {
         				count1++;
         			}else if(guigrid[i][j]==2) {
         				count2++;
         			}
         		}
         	}
         }
         //check horizontal
         for(i=50; i<600; i+=100) {
         	for(j=50; j<=350; j+=100) {
         		if((guigrid[i][j]!=0) && (guigrid[i][j]==guigrid[i][j+100]) && (guigrid[i][j]==guigrid[i][j+200]) && (guigrid[i][j]==guigrid[i][j+300])){
         			if(guigrid[i][j]==1) {
         				count1++;
         			}else if(guigrid[i][j]==2) {
         				count2++;
         			}
         		}
         	}
         }
         //check diagonal up
         for(i=550; i>=350; i-=100) {
         	for(j=50; j<=350; j+=100) {
         		if((guigrid[i][j]!=0) && (guigrid[i][j]==guigrid[i-100][j+100]) && (guigrid[i][j]==guigrid[i-200][j+200]) && (guigrid[i][j]==guigrid[i-300][j+300])){
         			if(guigrid[i][j]==1) {
         				count1++;
         			}else if(guigrid[i][j]==2) {
         				count2++;
         			}
         		}
         	}
         }
       //check diagonal down:
         for(i=50; i<=250; i+=100) {
         	for(j=50; j<=350; j+=100) {
         		if((guigrid[i][j]!=0) && (guigrid[i][j]==guigrid[i+100][j+100]) && (guigrid[i][j]==guigrid[i+200][j+200]) && (guigrid[i][j]==guigrid[i+300][j+300])){
         			if(guigrid[i][j]==1) {
         				count1++;
         			}else if(guigrid[i][j]==2) {
         				count2++;
         			}
         		}
         	}
         }
         //check draw; no wins
         int full=0;
         for(j=50; j<700; j+=100) {
 	        if(guigrid[550][j]!=0) {
 				full++;
 			}
         }
         if(full==7) {
         	winner=3;
         }
         if(count1>0 && count2>0) {
         	winner=3;
         }else if(count1>0 && count2==0) {
         	winner=1;
         }else if(count2>0 && count1==0) {
         	winner=2;
         }
    	
    	return winner;
    }

    public static void guiwinoption() {
//    	StdDraw.setPenColor(StdDraw.BLUE);
//		StdDraw.filledRectangle(520, 540, 170, 45);
		StdDraw.textLeft(300, 625, "1. Play Again  0. Exit ");
		StdDraw.pause(100);
    }
    
    public static int [][] guiBomb (int i, int [][] guigrid, boolean player1) {
	    
    	//j row
    	for(int j=50; j<600; j+=100) {
    		if(guigrid[j][i]==0) {
	    		if(j==50) {
	    			if(i==50) {
	    				guiplay(i, guigrid, player1);
		    			guigrid[j][i+100]=guigrid[j+200][i+100];
		    			guigrid[j+100][i+100]=guigrid[j+300][i+100];
		    			guigrid[j+200][i+100]=guigrid[j+400][i+100];
		    			guigrid[j+300][i+100]=guigrid[j+500][i+100];
		    			
		    			guigrid[j+400][i+100]=0;
		    			guigrid[j+500][i+100]=0;
	    			}else if(i==650) {
	    				guiplay(i, guigrid, player1);
		    			guigrid[j][i-100]=guigrid[j+200][i-100];
		    			guigrid[j+100][i-100]=guigrid[j+300][i-100];
		    			guigrid[j+200][i-100]=guigrid[j+400][i-100];
		    			guigrid[j+300][i-100]=guigrid[j+500][i-100];
		    			
		    			guigrid[j+400][i-100]=0;
		    			guigrid[j+500][i-100]=0;
	    			}else {
	    				
		    			guiplay(i, guigrid, player1);
		    			guigrid[j][i+100]=guigrid[j+200][i+100];
		    			guigrid[j][i-100]=guigrid[j+200][i-100];
		    			guigrid[j+100][i+100]=guigrid[j+300][i+100];
		    			guigrid[j+100][i-100]=guigrid[j+300][i-100];
		    			guigrid[j+200][i+100]=guigrid[j+400][i+100];
		    			guigrid[j+200][i-100]=guigrid[j+400][i-100];
		    			guigrid[j+300][i+100]=guigrid[j+500][i+100];
		    			guigrid[j+300][i-100]=guigrid[j+500][i-100];
		    			
		    			guigrid[j+400][i+100]=0;
		    			guigrid[j+400][i-100]=0;
		    			guigrid[j+500][i+100]=0;
		    			guigrid[j+500][i-100]=0;

	    			}
	    			
	    			break;
	    			//done
	    		}else if(j-100>=50 && j+400<=550) {
	    			if(i==50) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=guigrid[j+200][i+100];
		    			guigrid[j][i+100]=guigrid[j+300][i+100];
		    			guigrid[j+100][i+100]=guigrid[j+400][i+100];
		    			
		    			guigrid[j+200][i+100]=0;
		    			guigrid[j+300][i+100]=0;
		    			guigrid[j+400][i+100]=0;
		    			
	    			}else if(i==650) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i-100]=guigrid[j+200][i-100];
		    			guigrid[j][i-100]=guigrid[j+300][i-100];
		    			guigrid[j+100][i-100]=guigrid[j+400][i-100];
		    			
		    			guigrid[j+200][i-100]=0;
		    			guigrid[j+300][i-100]=0;
		    			guigrid[j+400][i-100]=0;
	    			}else {
		    			guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=guigrid[j+200][i+100];
		    			guigrid[j-100][i-100]=guigrid[j+200][i-100];
		    			guigrid[j][i+100]=guigrid[j+300][i+100];
		    			guigrid[j][i-100]=guigrid[j+300][i-100];
		    			guigrid[j+100][i+100]=guigrid[j+400][i+100];
		    			guigrid[j+100][i-100]=guigrid[j+400][i-100];
		    			
		    			guigrid[j+200][i+100]=0;
		    			guigrid[j+200][i-100]=0;
		    			guigrid[j+300][i+100]=0;
		    			guigrid[j+300][i-100]=0;
		    			guigrid[j+400][i+100]=0;
		    			guigrid[j+400][i-100]=0;

	    			}
	    			
	    			break;
	    			//done
	    		}else if(j+400>550 && j+300<=550) {
	    			if(i==50) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=guigrid[j+200][i+100];
		    			guigrid[j][i+100]=guigrid[j+300][i+100];
		    			
		    			guigrid[j+100][i+100]=0;
		    			
		    			guigrid[j+200][i+100]=0;
		    			guigrid[j+300][i+100]=0;
		    			
	    			}else if(i==650) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i-100]=guigrid[j+200][i-100];
		    			guigrid[j][i-100]=guigrid[j+300][i-100];
		    			
		    			guigrid[j+100][i-100]=0;
		    		
		    			guigrid[j+200][i-100]=0;
		    			guigrid[j+300][i-100]=0;
		    			
	    			}else {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=guigrid[j+200][i+100];
		    			guigrid[j-100][i-100]=guigrid[j+200][i-100];
		    			guigrid[j][i+100]=guigrid[j+300][i+100];
		    			guigrid[j][i-100]=guigrid[j+300][i-100];
		    			
		    			guigrid[j+100][i+100]=0;
		    			guigrid[j+100][i-100]=0;
		    			
		    			guigrid[j+200][i+100]=0;
		    			guigrid[j+200][i-100]=0;
		    			guigrid[j+300][i+100]=0;
		    			guigrid[j+300][i-100]=0;
		    			
	    			}
	    			
	    			break;
	    			//done
	    		}else if(j+300>550 && j+200<=550) {
	    			if(i==50) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=guigrid[j+200][i+100];
		    			
						guigrid[j][i+100]=0;
		    			guigrid[j+100][i+100]=0;
		    			
		    			guigrid[j+200][i+100]=0;
		    			
	    			}else if(i==650) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i-100]=guigrid[j+200][i-100];
		    			
						guigrid[j][i-100]=0;
		    			guigrid[j+100][i-100]=0;
		    		
		    			guigrid[j+200][i-100]=0;
		    			
	    			}else {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=guigrid[j+200][i+100];
		    			guigrid[j-100][i-100]=guigrid[j+200][i-100];
		    			
						guigrid[j][i+100]=0;
		    			guigrid[j][i-100]=0;
		    			guigrid[j+100][i+100]=0;
		    			guigrid[j+100][i-100]=0;
		    			
		    			guigrid[j+200][i+100]=0;
		    			guigrid[j+200][i-100]=0;
		    			
	    			}
	    			
	    			break;
	    			//done
	    		}else if(j+200>550 && j+100<=550) {
	    			if(i==50) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=0;
		    			guigrid[j][i+100]=0;
		    			guigrid[j+100][i+100]=0;
		    					    			
	    			}else if(i==650) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i-100]=0;
		    			guigrid[j][i-100]=0;
		    			guigrid[j+100][i-100]=0;
		    				    			
	    			}else {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=0;
		    			guigrid[j-100][i-100]=0;
		    			guigrid[j][i+100]=0;
		    			guigrid[j][i-100]=0;
		    			guigrid[j+100][i+100]=0;
		    			guigrid[j+100][i-100]=0;
		    			
		    			}
	    			
	    			break;
	    			//done
	    		}else if(j==550) {
	    			if(i==50) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=0;
		    			guigrid[j][i+100]=0;
		    			
	    			}else if(i==650) {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i-100]=0;
		    			guigrid[j][i-100]=0;
		    			
	    			}else {
	    				guigrid[j-100][i]=0;
		    			guiplay(i, guigrid, player1);
		    			guigrid[j-100][i+100]=0;
		    			guigrid[j-100][i-100]=0;
		    			guigrid[j][i+100]=0;
		    			guigrid[j][i-100]=0;
		    			
	    			}
	    			
	    			break;
	    		}
	    		
	    		
	    	}else if(guigrid[550][i]!=0) {
	    		StdDraw.setPenColor(StdDraw.BLACK);
    			StdDraw.textLeft(50, 625, "Column full");
    			StdDraw.pause(100);
    			break;
	    	}
    	}
	    	
    	return guigrid;
	}
    
    public static int [][] guiTeleport (int i, int [][] guigrid, boolean player1) {

    	for(int j=50; j<600; j+=100) {
    		boolean old=false;
    		if(j==50 && guigrid[j][i]==0) {
    			guigrid[j][i]=0;
    			break;
    		}else if(j>50 && guigrid[j][i]==0){ //fixing guiplay on full column and guiplay to full column
    			if(guigrid[j-100][i]==1) { //guigrid[j+1][i]==1 || 
					old=true;
				}else if(guigrid[j-100][i]==2) {
					old=false;
				}
    			if(i<450 && guigrid[j][i+300]==0) {
    				guiplay(i+300, guigrid, old);
    				guigrid[j-100][i]=0;
    				guiplay(i, guigrid, player1);
    			}else if(i>350 && guigrid[j][i-400]==0) {
    				guiplay(i-400, guigrid, old);
    				guigrid[j-100][i]=0;
    				guiplay(i, guigrid, player1);
    			}
    			break;
    		}else if(guigrid[550][i]!=0) {
    			if(guigrid[550][i]==1) { //guigrid[j+1][i]==1 || 
					old=true;
				}else if(guigrid[0][i]==2) {
					old=false;
				}
    			if(i<450 && guigrid[j][i+300]==0) {
    				guiplay(i+300, guigrid, old);
    				guigrid[550][i]=0;
    				guiplay(i, guigrid, player1);
    			}else if(i>350 && guigrid[j][i-400]==0) {
    				guiplay(i-400, guigrid, old);
    				guigrid[550][i]=0;
    				guiplay(i, guigrid, player1);
    			}
    			break;
    		}
    		if(i<450) {
    			if(guigrid[550][i+300]!=0){
    				guiColourChanger(i, guigrid, player1);
    				StdDraw.setPenColor(StdDraw.BLACK);
        			StdDraw.textLeft(50, 625, "Column full");
        			StdDraw.pause(100);
    				break;
        		}
    		}else if(i>350) {
    			if(guigrid[550][i-400]!=0) {
    				guiColourChanger(i, guigrid, player1);
    				StdDraw.setPenColor(StdDraw.BLACK);
        			StdDraw.textLeft(50, 625, "Column full");
        			StdDraw.pause(100);
    				break;
    			}
    		}
    		
    	}
    	return guigrid;
    }

    public static int [][] guiColourChanger (int i, int [][] guigrid, boolean player1) {
    	//int i = column
    	//int j = row
    	StdDraw.setScale(700, 600);
    		for(int j=50; j<650; j+=100) { 
	    		if(guigrid[j][i]==0 && (j>50 && j<550)) {
	    			if(player1) {
	    				guigrid[j][i]=1;    		
	    			}else {
	    				guigrid[j][i] = 2;
	    			}
	    			if(guigrid[j-100][i]==1) {
	        			guigrid[j-100][i]=2;
	        		}else if(guigrid[j-100][i]==2) {
	        			guigrid[j-100][i]=1;
	        		}
	    			guigrid[j][i]=0;
	    			break;
	    		}else if(guigrid[550][i]==1) {
	    			guigrid[550][i]=2;
	    			break;
	    		}else if(guigrid[550][i]==2) {
	    			guigrid[550][i]=1;
	    			break;
	    		}else if(guigrid[50][i]==0) {
	    			guiplay(i, guigrid, player1);
	    			break;
	    		}
	    	}
        return guigrid;
    }
    
    public static void playgcc(boolean player1, int[] curppowers, int[] p1powers, int[] p2powers, int[][] guigrid, int i) {
		if(player1) {
			if(p1powers[2]>0) {
				guiColourChanger(i, guigrid, player1);
				curppowers[2]=p1powers[2]-1;
				StdDraw.pause(50);
			}else {
            	StdDraw.setPenColor(StdDraw.BLACK);
            	StdDraw.textLeft(750, 250, "No Colour Changers remaining");
            }
		}else {
			if(p2powers[2]>0) {
				guiColourChanger(i, guigrid, player1);
				curppowers[2]=p2powers[2]-1;
				StdDraw.pause(50);
			}else {
            	StdDraw.setPenColor(StdDraw.BLACK);
            	StdDraw.textLeft(750, 250, "No Colour Changers remaining");
            }
		}
    }
   
    public static void playgt(boolean player1, int[] curppowers, int[] p1powers, int[] p2powers, int[][] guigrid, int i) {
    	
    	if(player1) {
    		if(p1powers[1]>0) {
				guiTeleport(i, guigrid, player1);
				curppowers[1]=p1powers[1]-1;
				StdDraw.pause(50);
    		}else {
    			StdDraw.setPenColor(StdDraw.BLACK);
    			StdDraw.textLeft(750, 250, "No Teleporters remaining");
    		}
		}else {
			if(p2powers[1]>0) {
				guiTeleport(i, guigrid, player1);
				curppowers[1]=p2powers[1]-1;
				StdDraw.pause(50);
			}else {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(750, 250, "No Teleporters remaining");
			}
		}
    }
    
    public static void playgb(boolean player1, int[] curppowers, int[] p1powers, int[] p2powers, int[][] guigrid, int i) {

    	if(player1) {
    		if(p1powers[0]>0) {
				guiBomb(i, guigrid, player1);
				curppowers[0]=p1powers[0]-1;
				StdDraw.pause(50);
    		}else {
    			StdDraw.setPenColor(StdDraw.BLACK);
    			StdDraw.textLeft(750, 250, "No Bombs remaining");
    		}
		}else {
			if(p2powers[0]>0) {
				guiBomb(i, guigrid, player1);
				curppowers[0]=p2powers[0]-1;
				StdDraw.pause(50);
			}else {
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(750, 250, "No Bombs remaining");
			}
		}
	}
}


/* BUG LIST:
 * 1. CREATE DRAW FROM A WIN
 * 2. NO FULL COLUMN ERROR FOR STD DISK, BOMB
 * 3. PLAY AGAIN IF WIN MENU DISPLAYS TOO LONG
 * 4. SPECIALS EMPTY ERROR MESSAGE WONT DISPLAY
 * 5. PLAY STD DISK WHEN SPECIALS EMPTY AND HOLDING POWER KEYS DOWN
 * 
 * 
 * FIXES/ CHANGES TO MAKE:
 * 1. MOVE CHOOSE COLUMN MESSAGE TO MENU SIDE
 * 2. MOVE PLAYER TURN TO MENU SIDE
 * 3. CREATE TITLE CARD ABOVE BOARD
 * 4. SHIFT EXIT MESSAGE TO ACCOMODATE TITLE
 * 
 * STILL TO DO:
 * 1. FINISH MOUSE INPUT FOR EXIT AND MENU SELECTION ON WIN OPTIONS
 */


