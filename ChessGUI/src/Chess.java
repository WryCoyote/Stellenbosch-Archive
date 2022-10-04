import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The driver class for the Fairy Chess project. GUI VERSION!!!!!!!!!!!!!!!!!!
 */
public class Chess {
	static String SRC;
	static String DST;
	
	static int SRCfile;
	static int SRCrank;
	static int DSTfile;
	static int DSTrank;
	static String Colour;
	static boolean player;
	
	static String move="";
	
	static int click=0;
	
	static String boardFile;
	static String moveFile = "";
	static String textMove = "";
	
	static private String [] file = {"a", "b", "c", "d", "e", "f", "g", "h"};
	static private String [] rank = {"8", "7", "6", "5", "4", "3", "2", "1"};
	
	static String[][] regboard = new String [8][8];
	
	static boolean p = true;
	
	public static void main(String boardName) {
		
		StdDraw.setCanvasSize(1000, 650);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 650);
		boardIn(boardName);
		
		while(true){
			StdDraw.enableDoubleBuffering();
			guiBoard();
			boardPopulate();
			if(StdDraw.isMousePressed()){
				StdDraw.pause(75);
				if(StdDraw.mouseX()>50 && StdDraw.mouseX()<650){
					click++;
					move(StdDraw.mouseX(), StdDraw.mouseY());
					
				}
				if(StdDraw.mouseX()>699 && StdDraw.mouseX()<1000){
					guiMenuFunctions(StdDraw.mouseX(), StdDraw.mouseY());
					System.out.println();
				}
				
			}else if(StdDraw.hasNextKeyTyped()) {
				if(StdDraw.isKeyPressed(27)){
					System.exit(1);
				}
			}
			StdDraw.show();
		}
		


	}
	
	private static void guiBoard(){
		StdDraw.picture(500, 325, "lagoon_nebula_horizontal.png", 1000, 650);
		//board
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.square(350, 350, 299);
		StdDraw.picture(350, 350, "regBoard.png" /*"CHESS BOARD.png"*/, 600, 600);
		//Menu and title backing
		//Title pane and Title (use image??)
		
		StdDraw.picture(825, 590, "back.png", 300, 100);
//		
//		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(825, 590, "CHESS");
//		//menu (use image based list??)
//		
		StdDraw.picture(850, 300, "back.png", 380, 400);
//		//text input
//		StdDraw.picture(825, 450, "textIn.png", 300, 50);
//		
//		StdDraw.text(850, 450, "<<TEXT INPUT HERE>>");
//		//load move
//		StdDraw.picture(850, 375, "otherButtons.png", 300, 50);
//		StdDraw.text(850, 375, "LOAD MOVE FILE");
//		//step move // quick play
//		StdDraw.picture(850, 300, "otherButtons.png", 300, 50);
//		StdDraw.text(850, 300, "STEP MOVE>>");
//		
//		StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
//		StdDraw.filledRectangle(825, 225, 150, 25);
		StdDraw.picture(825, 225, "otherButtons.png", 300, 50);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(825, 225, "TURN");
		if(p==true) {
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledCircle(750, 225, 20);
		}else {
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
			StdDraw.filledCircle(750, 225, 20);
		}
//		
//		//exit
		StdDraw.picture(825, 150, "exit.png", 300, 50);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(825, 150, "EXIT");
//		//turn indicator spot
//		StdDraw.picture(825, 50, "back.png", 380, 70);
//		StdDraw.text(820, 75, "TURN INDICATOR");
//		
		//rank and file labels
		StdDraw.setPenColor(StdDraw.WHITE);
		int count=7;
		for(int i=90; i<650; i+=75){
			StdDraw.picture(25, i, "back.png", 30, 30);
			StdDraw.text(25, i, rank[count]);
			if(count>0){
				count--;
			}else{
				break;
			}
		}
		for(int i=90; i<650; i+=75){
			StdDraw.picture(i, 25, "back.png", 30, 30);
			StdDraw.text(i, 25, file[count]);
			if(count<7){
				count++;
			}else{
				break;
			}
		}
//		
//		//allocations indicator
//		StdDraw.picture(850, 675, "back.png", 300, 30);
//		
//		int allocNum=0;
//		for(int i=50; i<1050; i+=60){
//			StdDraw.picture(i, 675, "back.png", 40, 30);
//			StdDraw.text(i, 675, pce[allocNum] + ":" + allocations(pce[allocNum]));
//			allocNum++;
//			if(allocNum==11){
//				break;
//			}
//		}
//		StdDraw.setPenColor(StdDraw.BLACK);
//		StdDraw.text(850, 675, "<< ALLOCATIONS");
	}

	private static void guiMenuFunctions(double x, double y){
		
		//exit
		if(y>125 && y<175){
			//EXIT
			System.out.println("EXIT");
			System.exit(0);
		}
	}
	
	private static void boardPopulate(){
		
		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(!regboard[i][j].equals(".")){
					if(regboard[i][j].matches(".*\\p{Upper}.*")){
						StdDraw.setPenColor(StdDraw.WHITE);
					}else{
						StdDraw.setPenColor(StdDraw.GRAY);
					}
					StdDraw.filledCircle(((i+1)*75)+15, ((j+1)*75)+15, 20);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(((i+1)*75)+15, ((j+1)*75)+15, regboard[i][j]);
				}
			}
		}
	}
	
	public static void boardIn(String args) {
		String [] l = new String[8];
        // Get the board file's name, and initialize a File object to represent it
		if (args.length() < 1) {
			throw new IllegalArgumentException("Provide a file name as first argument");
		}

        String boardFilename = args;
        File boardFile = new File(boardFilename);

        // Initialize the Scanner
        Scanner boardScanner = null;
        try {
            boardScanner = new Scanner(boardFile);
        } catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Board file does not exist");
        }

        // Read the file line by line, printing out as we go along
        int r = 7;
        while (boardScanner.hasNextLine()) {
            String line = boardScanner.nextLine();
            while(!line.isEmpty()){
            	l = line.split(" ");
        		for(int i=0; i<8; i++) {
        			regboard[i][r] = l[i];
        		}
        		r--;
            	break;
            }
        }
        boardScanner.close();
        
	}

	private static void move(double x, double y) {
		int file = (int) Math.round(Math.abs(((x-15)/75)-1));
		int rank = (int) Math.round(Math.abs(((y-15)/75)));
		
		if((file<8 && file>-1) && (rank<9 && rank>0)){

			if(click==1){
				SRC = regboard[file][rank-1];
				if(SRC.matches(".*\\p{Upper}.*") && p==false) {
					System.out.println("Wrong colour!");
					click=0;
				}else if(SRC.matches(".*\\p{Lower}.*") && p==true) {
					System.out.println("Wrong colour!");
					click=0;
				}
				if(!SRC.equals(".")) {
					SRCfile = file;
					SRCrank = rank;
				}else {
					click = 0;
				}
				
				
			}else if(click==2){
				
				DST = regboard[file][rank-1];
				DSTfile = file;
				DSTrank = rank;
				
				if(SRC.equals("k") && (DSTfile == 2 && SRCrank==8) && (SRCfile==4)){
					regboard[2][7] = "k";
					regboard[3][7] = "r";
					
					regboard[4][7] = ".";
					regboard[0][7] = ".";
				}else if(SRC.equals("k") && (DSTfile == 6 && SRCrank==8) && (SRCfile==4)){
					
					regboard[6][7] = "k";
					regboard[5][7] = "r";
					
					regboard[4][7] = ".";
					regboard[7][7] = ".";
					
				
				}else if(SRC.equals("K") && (DSTfile == 2 && SRCrank==1) && (SRCfile==4)){
					regboard[2][0] = "K";
					regboard[3][0] = "R";
					
					regboard[4][0] = ".";
					regboard[0][0] = ".";
				}else if(SRC.equals("K") && (DSTfile == 6 && SRCrank==1) && (SRCfile==4)){
					regboard[6][0] = "K";
					regboard[5][0] = "R";
					
					regboard[4][0] = ".";
					regboard[7][0] = ".";
				}else {
					if(!DST.equals(SRC)){
						regboard[DSTfile][DSTrank-1] = SRC;
						regboard[SRCfile][SRCrank-1] = ".";
					}
					
				}
				click=0;
				p=!p;
				
			}
			StdDraw.pause(75);
		}
	}
	
	
}

