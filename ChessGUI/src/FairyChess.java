/**
 * The driver class for the Fairy Chess project. GUI VERSION!!!!!!!!!!!!!!!!!!
 */
public class FairyChess {
	
	static int click=0;
	
	static String boardFile;
	static String moveFile = "";
	static String textMove = "";
	
	static private String [] file = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
	static private String [] rank = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
	static private String [] pce = {"p", "d", "r", "n", "b", "q", "k", "a", "w", "f", "e"};
	
	static String[][] guiBoard = new String [10][10];
	
	static String status;
	static int half=0;
	static int move=0;
	
	static String regular;
	
	static String[] movos = new String[1000];
	static boolean step;
	static int stepMove = 0;
	
	public static void main(String[] args) {
		regular = args[0];
		if(regular.equalsIgnoreCase("Y")){
			Chess.main("regBoard.board");
		}else {
			boardFile = args[0];
			if(args.length == 2){
				moveFile = args[1];
			}
		}
		
		StdDraw.setCanvasSize(1050, 700);
		StdDraw.setXscale(0, 1050);
		StdDraw.setYscale(0, 700);
		
		if(!boardFile.isEmpty()){
			BoardValid.check(boardFile);
		}
		if(!moveFile.isEmpty()){
			MoveValid.check(moveFile);
		}
		
		while(true){
			StdDraw.enableDoubleBuffering();
			guiBoard();
			boardPopulate();
			statusLine();
			if(StdDraw.isMousePressed()){
				StdDraw.pause(75);
				if(StdDraw.mouseX()>699 && StdDraw.mouseX()<1000){
					guiMenuFunctions(StdDraw.mouseX(), StdDraw.mouseY());
					System.out.println();
				}
				if(StdDraw.mouseX()>50 && StdDraw.mouseX()<650){
					click++;
					GuiMoveBoard.move(StdDraw.mouseX(), StdDraw.mouseY());
					
				}
				
			}else if(StdDraw.hasNextKeyTyped()) {
				if(StdDraw.isKeyPressed(27)){
					System.exit(1);
				}
			}
			StdDraw.show();
		}
		


	}
	
	public static void guiBoard(){
		StdDraw.picture(525, 350, "lagoon_nebula_horizontal.png", 1050, 700);
		//board
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.square(350, 350, 299);
		StdDraw.picture(350, 350, "altBoard.png", 600, 600);
		//Title pane and Title (use image??)
		StdDraw.picture(850, 590, "back.png", 380, 100);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(850, 590, "FAIRY CHESS");
		//menu (use image based list??)
		StdDraw.picture(850, 300, "back.png", 380, 400);
		//text input
		StdDraw.picture(850, 450, "textIn.png", 300, 50);
		StdDraw.text(850, 450, "<<TEXT INPUT HERE>>");
		//step move // quick play
		StdDraw.picture(850, 375, "otherButtons.png", 300, 50);
		StdDraw.text(850, 375, "STEP MOVE>>");
		
		//exit
		StdDraw.picture(850, 150, "exit.png", 300, 50);
		StdDraw.text(850, 150, "EXIT");
		//turn indicator spot
		StdDraw.picture(850, 50, "back.png", 380, 70);
		StdDraw.text(820, 75, "TURN INDICATOR");
		//rank and file labels
		StdDraw.setPenColor(StdDraw.WHITE);
		int count=9;
		for(int i=80; i<1050; i+=60){
			StdDraw.picture(25, i, "back.png", 30, 30);
			StdDraw.text(25, i, rank[count]);
			if(count>0){
				count--;
			}else{
				break;
			}
		}
		for(int i=80; i<650; i+=60){
			StdDraw.picture(i, 25, "back.png", 30, 30);
			StdDraw.text(i, 25, file[count]);
			if(count<9){
				count++;
			}else{
				break;
			}
		}
		
		//allocations indicator
		StdDraw.picture(850, 675, "back.png", 300, 30);
		
		int allocNum=0;
		for(int i=50; i<1050; i+=60){
			StdDraw.picture(i, 675, "back.png", 40, 30);
			StdDraw.text(i, 675, pce[allocNum] + ":" + allocations(pce[allocNum]));
			allocNum++;
			if(allocNum==11){
				break;
			}
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(850, 675, "<< ALLOCATIONS");
	}

	public static void guiMenuFunctions(double x, double y){
		
		if(y>425 && y<475){
			//TEXT BASED INPUT
			while(true){
				try{
					System.out.println("Insert Move e.g: a1-b2");
					System.out.println("Type 'exit' to leave text based move input. NOTE: not case sensitive");
					textMove = StdIn.readString();
					StdDraw.pause(100);
					if(textMove.equalsIgnoreCase("exit")){
						break;
					}
					
					MoveValid.veritas(textMove);
					StdDraw.show();
					break;
				}catch(Exception ex){
					System.out.println("Invalid move, please try again");
					System.out.println();
				}
			}
			
			
		}
		
		//step and quick play
		if(y>325 && y<425){ //850, 375
			System.out.println("STEP>>");
			try {
				MoveValid.veritas(MoveStore.storeMoves[stepMove]);
				boardUpdate();
				stepMove++;
			}catch(Exception ex) {
				System.out.println("No Moves Left");
			}
					
			
//			StdDraw.pause(75);
		}
		
		
		//exit
		if(y>125 && y<175){
			//EXIT
			System.out.println("EXIT");
			System.exit(0);
		}
	}
	
	public static void boardPopulate(){
		
		for (int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				if(!MoveValid.moveboard[i][j].equals(".")){
					if(MoveValid.moveboard[i][j].matches(".*\\p{Upper}.*")){
						StdDraw.setPenColor(StdDraw.WHITE);
					}else{
						StdDraw.setPenColor(StdDraw.GRAY);
					}
					StdDraw.filledCircle(((i+1)*60)+20, ((j+1)*60)+20, 20);
					if(MoveValid.moveboard[i][j].matches(".*\\p{Upper}.*")){
						StdDraw.setPenColor(StdDraw.BLACK);
					}else{
						StdDraw.setPenColor(StdDraw.WHITE);
					}
					StdDraw.text(((i+1)*60)+20, ((j+1)*60)+20, MoveValid.moveboard[i][j]);
				}
			}
		}
	}
	
	public static void boardUpdate(){
		guiBoard();
		statusLine();
		for (int i=0; i<10; i++){
			for(int j=0; j<10; j++){
				if(!MoveValid.moveboard[i][j].equals(".")){
					if(MoveValid.moveboard[i][j].matches(".*\\p{Upper}.*")){
						StdDraw.setPenColor(StdDraw.WHITE);
					}else{
						StdDraw.setPenColor(StdDraw.GRAY);
					}
					StdDraw.filledCircle(((i+1)*60)+20, ((j+1)*60)+20, 20);
					if(MoveValid.moveboard[i][j].matches(".*\\p{Upper}.*")){
						StdDraw.setPenColor(StdDraw.BLACK);
					}else{
						StdDraw.setPenColor(StdDraw.WHITE);
					}
					StdDraw.text(((i+1)*60)+20, ((j+1)*60)+20, MoveValid.moveboard[i][j]);
				}
			}
		}
	}

	public static void statusLine(){
		String image = "";
		StdDraw.setPenColor(StdDraw.BLACK);
		if(MoveValid.p==true){
			StdDraw.setPenColor(StdDraw.WHITE);
		}else{
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
		}
		StdDraw.filledCircle(700, 50, 30);
		
		for(int i=0; i<4; i++){
			if(MoveValid.castle[i].equals("+")){
				image = "textIn.png";
			}else{
				image = "exit.png";
			}
			StdDraw.picture(760+i*40, 45, image, 30, 30);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(760, 45, "BQ");
		StdDraw.text(800, 45, "BK");
		StdDraw.text(840, 45, "WQ");
		StdDraw.text(880, 45, "WK");
		
		StdDraw.picture(940, 45, "hM.png", 40, 40);
		StdDraw.picture(1000, 45, "hM.png", 40, 40);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(940, 70, "HM");
		StdDraw.text(1000, 70, "M");
		StdDraw.text(940, 45, ""+MoveValid.halfmove+"");
		StdDraw.text(1000, 45, ""+MoveValid.move+"");
	}

	public static int allocations(String p){
		int k = 0;;
		switch(p){
		
		case "p": 
			k = pawn.allocation;
			break;
			
		case "d":
			k = DSoldier.allocation;
			break;
			
		case "r":
			k = Rook.allocation;
			break;
			
		case "n":
			k = Knight.allocation;
			break;
			
		case "b":
			k = Bishop.allocation;
			break;
			
		case "q":
			k = Queen.allocation;
			break;
			
		case "k":
			k = King.allocation;
			break;
			
		case "a":
			k = Amazon.allocation;
			break;
			
		case "w":
			k = Princess.allocation;
			break;
			
		case "f":
			k = FDragon.allocation;
			break;
			
		case "e":
			k = Elephant.allocation;
			break;
		
		}
		return k;
	}

}