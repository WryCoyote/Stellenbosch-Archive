import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class MoveValid {

//	static boolean SecA=true, SecB=false, SecC=false;

	static boolean take;
	static boolean CHECK = false;

	static String upgrade;
	static String AllocOfficers = "";

	static int filecount = 0;
	static int rankcount=0;
	static int DSTfilecount = 0;
	static int DSTrankcount=0;
	static char [] file = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
	static int count = 0;

	static String SRC, DST;

	static String[][] moveboard = new String[10][10];
	static String[] statusline = new String[4];

	static String[] castle = new String [4];
	static int move;
	static int halfmove;
	static String player;
	
	static String playerStart;
	static int moveStart;
	static int halfMoveStart;
	
	static String checkMate;
	static boolean CheckMate = false;

	static boolean p;   //true = white  //false = black

	static void check(String args1/*, String args2*/) {
		player = statusline[0];
		playerStart = player;
		if(player.equals("w")){
			p=true;
		}else{
			p=false;
		}
		move = Integer.parseInt(statusline[3]);
		halfmove = Integer.parseInt(statusline[2]);
		moveStart = move;
		halfMoveStart = halfmove;
//		MoveReadIn(args1);
//        BoardPrint(args1);
        CHECK=false;
        MoveStore.check(args1);
    }
	
	public static void MoveReadIn(String args1) {
		// Get the board file's name, and initialize a File object to represent it
		if (args1.length() < 1) {
			throw new IllegalArgumentException("Provide a file name as first argument");
		}

        String moveFilename = args1;
        File moveFile = new File(moveFilename);

        // Initialize the Scanner
        Scanner moveScanner = null;
        try {
            moveScanner = new Scanner(moveFile);
        } catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Moves file does not exist");
        }

        // Read the file line by line, printing out as we go along

        while (moveScanner.hasNextLine()) {
            String line = moveScanner.nextLine();

            while(!line.isEmpty()){
            	if(line.startsWith("%")){
            		count++;
            	}else{
            		count++;
            		veritas(line);
//            		FairyChess.movos[count-1] = line;
//		            FairyChess.boardUpdate();
//		            FairyChess.statusLine();
//		            StdDraw.show();
//		            StdDraw.pause(1250);
            	}
            	break;
            }
        }
        moveScanner.close();
	}

	public static void veritas(String l){
		
		l = l.replaceAll(" ", "");
		if(l.contains("-")){
			if(l.equals("0-0-0") || l.equals("0-0")){
				if(CHECK==true) {
					System.out.println("bad_castle!");
					MoveValidationErrors.illegalCastlingMove(count);
				}else {
					Castling.valid(l, count, p, player);
				}
			}else {
				if(l.charAt(l.length()-1)=='+' && l.charAt(l.length()-2)!='+'){ //check
					Check.main(true, count);
					CHECK = true;
					if(l.charAt(l.length()-3)=='='){ //promotion
						upgrade = l.substring(l.indexOf('=')+1, l.length()-1);
						SRC = l.substring(0, l.indexOf('-'));
						DST = l.substring(l.indexOf('-')+1, l.indexOf("="));
						promotions(l, count);
					}else{
						SRC = l.substring(0, l.indexOf('-'));
						DST = l.substring(l.indexOf('-')+1, l.indexOf('+'));
						regMove(l);
					}

				}else if(l.charAt(l.length()-1)=='+' && l.charAt(l.length()-2)=='+'){ //checkmate
					//checkmate method/class??
					CheckMate=true;
					if(p==true){
						checkMate = "CHECKMATE! WHITE WINS!";
					}else{
						checkMate = "CHECKMATE! BLACK WINS!";
					}
				}else if(l.charAt(l.length()-2)=='='){ //promotion
					upgrade = l.substring(l.indexOf('=')+1);
					SRC = l.substring(0, l.indexOf('-'));
					DST = l.substring(l.indexOf('-')+1, l.indexOf("="));
					promotions(l, count);

				}else {
					SRC = l.substring(0, l.indexOf('-'));
					DST = l.substring(l.indexOf('-')+1, l.length());
//					de.bug(SRC+"_", 1);
//					de.bug(DST+"_", 1);
					regMove(l);

				}
				Check.main(CHECK, count);
			}

		}else if(l.contains("x")){						//take moveset
			if(l.charAt(l.length()-1)=='+' && l.charAt(l.length()-2)!='+'){ //check
				Check.main(true, count);
				CHECK = true;
				if(l.charAt(l.length()-3)=='='){ //promotion
					upgrade = l.substring(l.indexOf('=')+1, l.length()-1);
					SRC = l.substring(0, l.indexOf('x'));
					DST = l.substring(l.indexOf('x')+1, l.indexOf("="));
					promotions(l, count);
				}else{
					SRC = l.substring(0, l.indexOf('x'));
					DST = l.substring(l.indexOf('x')+1, l.length()-1);
					take(l);
				}

			}else if(l.charAt(l.length()-1)=='+' && l.charAt(l.length()-2)=='+'){ //checkmate
				//checkmate method/class??
				CheckMate=true;
				if(p==true){
					checkMate = "CHECKMATE! BLACK WINS!";
				}else{
					checkMate = "CHECKMATE! WHITE WINS!";
				}
				
			}else if(l.charAt(l.length()-2)=='='){ //promotion
				upgrade = l.substring(l.indexOf('=')+1);
				SRC = l.substring(0, l.indexOf('x'));
				DST = l.substring(l.indexOf('x')+1, l.indexOf("="));
				promotions(l, count);

			}else {
				SRC = l.substring(0, l.indexOf('x'));
				DST = l.substring(l.indexOf('x')+1, l.length());
				take(l);
			}
			Check.main(CHECK, count);
		}else if(l.equals("*")){
			
		}else{
			MoveValidationErrors.illegalMove(count);
		}
		
		filecount=0;
		rankcount=0;
		DSTfilecount=0;
		DSTrankcount=0;
	}

	public static void regMove(String l) {
		//SRC read in
		de.bug("reg_Move!", count);
		int k=0;
		for(int i=0; i<file.length; i++){
			if(SRC.charAt(0)==file[i]){
				filecount = i;
				de.bug("SRC_filecount:", filecount);
			}else{
				k++;
			}
		}
		if(k==file.length){
			de.bug("out_bounds_SRCfile!", 1);
			MoveValidationErrors.illegalMove(count);
		}
		k=0;
		try{
			rankcount = Integer.parseInt(SRC.substring(1))-1;
			if(rankcount>9 || rankcount<0){
				de.bug("out_bounds_SRCrank!", 1);
				MoveValidationErrors.illegalMove(count);
			}
			de.bug("SRC_rankcount:", rankcount);
		}catch(Exception ex){
			de.bug("SRC_exception_caught!", 1);
		}

		//DST Read In
		for(int i=0; i<file.length; i++){
			if(DST.charAt(0)==file[i]){
				DSTfilecount = i;
				de.bug("DST_filecount:", DSTfilecount);
			}else{
				k++;
			}
		}
		if(k==file.length){
			de.bug("out_bounds_DSTfile!", 1);
			MoveValidationErrors.illegalMove(count);
		}
		k=0;
		try{
			DSTrankcount = Integer.parseInt(DST.substring(1))-1;
			if(DSTrankcount>9 || DSTrankcount<0){
				de.bug("out_bounds_DSTrank!", 1);
				MoveValidationErrors.illegalMove(count);
			}
			de.bug("DST_rankcount:", DSTrankcount);
		}catch(Exception ex){
			de.bug("DST_exception_caught!", 1);
		}



		de.bug("player:" + player + "-", 1);
		if(!moveboard[filecount][rankcount].equals(".") && moveboard[DSTfilecount][DSTrankcount].equals(".")){
			Piece.move(player, count, moveboard[filecount][rankcount], false, CHECK);

			if(p==true/*player.equals("w")*/) {
				if(moveboard[filecount][rankcount].matches(".*\\p{Lower}.*")) { //what!!!!!???????
					de.bug("bad move!", 1);
					MoveValidationErrors.illegalMove(count);
				}
				player = "b";
			}

//			}
			if(p==false /*player.equals("b")*/) {
				if(moveboard[filecount][rankcount].matches(".*\\p{Upper}.*")) { //what!!!!!???????
					de.bug("bad move!", 2);
					MoveValidationErrors.illegalMove(count);
				}
				player = "w";
				move++;
			}

			moveboard[DSTfilecount][DSTrankcount] = moveboard[filecount][rankcount];
			if(CHECK==true) {
				if(moveboard[DSTfilecount][DSTrankcount].equals("k") || moveboard[DSTfilecount][DSTrankcount].equals("K")){
					kingCheck.kf = MoveValid.DSTfilecount;
					kingCheck.kr = MoveValid.DSTrankcount;
					kingCheck.main(p, count, false);
					CHECK=false;
				}
			}
			halfmoveUpdate();
			moveboard[filecount][rankcount] = ".";
			
			p=!p;
			castleUpdate();

			//de.bug("SRC> :", rankcount);
			//de.bug("DST> :", DSTrankcount);
		}else{
			de.bug("no move!>", 11);
			de.bug("SRC>>" + moveboard[filecount][rankcount]+">>"+"file:"+filecount+"_rank:", rankcount);
			de.bug("DST>>" + moveboard[DSTfilecount][DSTrankcount]+">>"+"file:"+DSTfilecount+"_rank:", DSTrankcount);
			MoveValidationErrors.illegalMove(count);
		}
//		filecount=0;
//		rankcount=0;
//		DSTfilecount=0;
//		DSTrankcount=0;

	}

	public static void take(String l) {
		de.bug("take_Move!", count);
		int k=0;
		//SRC read in
		for(int i=0; i<file.length; i++){
			if(SRC.charAt(0)==file[i]){
				filecount = i;
			}else{
				k++;
			}
		}
		if(k==file.length){
			de.bug("out_bounds_SRCfile!", 2);
			MoveValidationErrors.illegalCapture(count);
		}
		k=0;
		try{
			rankcount = Integer.parseInt(SRC.substring(1))-1;
			if(rankcount>9 || rankcount<0){
				de.bug("out_bounds_SRCrank!", 2);
				MoveValidationErrors.illegalCapture(count);
			}
		}catch(Exception ex){

		}
		de.bug("SRC:" + file[filecount], rankcount);
//		piece = moveboard[filecount][rankcount];
//
//		de.bug(piece, 1);


		//DST Read In
		for(int i=0; i<file.length; i++){
			if(DST.charAt(0)==file[i]){
				DSTfilecount = i;
			}else{
				k++;
			}
		}
		if(k==file.length){
			de.bug("out_bounds_DSTfile!", 2);
			MoveValidationErrors.illegalCapture(count);
		}
		k=0;
		try{
			DSTrankcount = Integer.parseInt(DST.substring(1))-1;
			if(DSTrankcount>9 || DSTrankcount<0){
				de.bug("out_bounds_DSTrank!", 2);
				MoveValidationErrors.illegalCapture(count);
			}
		}catch(Exception ex){

		}
		de.bug("DST:" + file[DSTfilecount]+"", DSTrankcount);



		de.bug("player:" + player + "-", 1);
		if(!moveboard[filecount][rankcount].equals(".") && !moveboard[DSTfilecount][DSTrankcount].equals(".")){
			Piece.move(player, count, moveboard[filecount][rankcount], true, CHECK);

			if(p==true/*player.equals("w")*/) {
				if(moveboard[filecount][rankcount].matches(".*\\p{Lower}.*")) { //what!!!!!???????
					de.bug("bad move!", 1);
					MoveValidationErrors.illegalCapture(count);
				}
				if(moveboard[DSTfilecount][DSTrankcount].matches(".*\\p{Upper}.*")) { //what!!!!!???????
					de.bug("bad move!", 11);
					MoveValidationErrors.illegalCapture(count);
				}
				de.bug("white move!", 1);
				player = "b";
				de.bug(player, 1);
			}

//			}
			if(p==false /*player.equals("b")*/) {
				if(moveboard[filecount][rankcount].matches(".*\\p{Upper}.*")) { //what!!!!!???????
					de.bug("bad move!", 2);
					MoveValidationErrors.illegalCapture(count);
				}
				if(moveboard[DSTfilecount][DSTrankcount].matches(".*\\p{Lower}.*")) { //what!!!!!???????
					de.bug("bad move!", 21);
					MoveValidationErrors.illegalCapture(count);
				}
				de.bug("black move!", 2);
				player = "w";
				de.bug(player, 2);
				move++;
			}

			moveboard[DSTfilecount][DSTrankcount] = moveboard[filecount][rankcount];
			if(CHECK==true) {
				if(moveboard[DSTfilecount][DSTrankcount].equals("k") || moveboard[DSTfilecount][DSTrankcount].equals("K")){
					kingCheck.kf = MoveValid.DSTfilecount;
					kingCheck.kr = MoveValid.DSTrankcount;
					kingCheck.main(p, count, false);
					CHECK=false;
				}
			}
			moveboard[filecount][rankcount] = ".";
			p=!p;
			castleUpdate();

			de.bug("SRC> " + moveboard[filecount][rankcount] + ":", rankcount);
			de.bug("DST> " + moveboard[DSTfilecount][DSTrankcount] + ":", DSTrankcount);
		}else{
			de.bug("no move!>", 21);
			de.bug("SRC>>" + moveboard[filecount][rankcount]+">>", 22);
			de.bug("DST>>" + moveboard[DSTfilecount][DSTrankcount]+">>", 23);
			MoveValidationErrors.illegalCapture(count);
		}
		halfmove=0;
//		filecount=0;
//		rankcount=0;
//		DSTfilecount=0;
//		DSTrankcount=0;

	}

	public static void BoardPrint(String args2) {
		String boardFilename = args2;
		File boardFile = new File(boardFilename);

		// Initialize the Scanner
		Scanner boardScanner = null;
		try {
			boardScanner = new Scanner(boardFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Board file does not exist");
		}

		// Read the file line by line, printing out as we go along
		if(de.debug==true) {
			System.out.println("FINAL OUTPUT: START>>");
		}
		while (boardScanner.hasNextLine()) {
			String line = boardScanner.nextLine();
			while(!line.isEmpty()){
				for(int i=0; i<count; i++){
					if(FairyChess.movos[i].equals("")) {
						FairyChess.movos[i] = line;
					}
				}
			}
			
			
		}

		boardScanner.close();
	}

	public static void castleUpdate(){
		if(!moveboard[0][9].equals("r")){
			castle[0] = "-";
		}
		if(!moveboard[9][9].equals("r")){
			castle[1] = "-";
		}
		if(!moveboard[5][9].equals("k")){
			castle[0] = "-";
			castle[1] = "-";
		}

		if(!moveboard[0][0].equals("R")){
			castle[2] = "-";
		}
		if(!moveboard[9][0].equals("R")){
			castle[3] = "-";
		}
		if(!moveboard[5][0].equals("K")){
			castle[2] = "-";
			castle[3] = "-";
		}
	}

	public static void halfmoveUpdate(){
		String[] pce = {"p", "d", "P", "D"};
		int pcount=0;
		for(int i=0; i<pce.length; i++){
			if(!moveboard[filecount][rankcount].equals(pce[i])){
				pcount++;
			}
		}
		if(pcount==pce.length){
			halfmove++;
		}else{
			halfmove=0;
		}
	}

	public static void promotions(String l, int line) {
		int upgradeCount=0;
		
		//check if upgrade is an officer allocated
		de.bug(AllocOfficers, AllocOfficers.length());

		if(p==true) {
			if(upgrade.matches(".*\\p{Lower}.*")) {
				MoveValidationErrors.illegalPromotion(line);
			}else {
				upgrade = upgrade.toLowerCase();
			}
		}else {
			if(upgrade.matches(".*\\p{Upper}.*")) {
				MoveValidationErrors.illegalPromotion(line);
			}
		}

		char[] up = upgrade.toCharArray();
		for(int i=0; i<AllocOfficers.length(); i++) {
			if(up[0] == AllocOfficers.charAt(i)) {
				upgradeCount++;
			}
		}
		if(upgradeCount==1) {
			if(p==true) {
				upgrade = upgrade.toUpperCase();
			}
		}else {
			MoveValidationErrors.illegalPromotion(line);
		}
		de.bug(upgrade+":>", upgradeCount);

		//SRC read in
		for(int i=0; i<file.length; i++){
			if(SRC.charAt(0)==file[i]){
				filecount = i;
			}
		}
		try{
			rankcount = Integer.parseInt(SRC.substring(1))-1;
		}catch(Exception ex){

		}
//		de.bug("SRC:" + file[filecount], rankcount);

		//DST Read In
		for(int i=0; i<file.length; i++){
			if(DST.charAt(0)==file[i]){
				DSTfilecount = i;

			}
		}
		try{
			DSTrankcount = Integer.parseInt(DST.substring(1))-1;
		}catch(Exception ex){

		}

		if(p==true) {
			//white promotions
			if(rankcount!=8 && DSTrankcount!=9) {
				//error
				MoveValidationErrors.illegalPromotion(line);
			}else {
				if(moveboard[filecount][rankcount].equals("P") || moveboard[filecount][rankcount].equals("D")) {
					moveboard[DSTfilecount][DSTrankcount] = upgrade;
					moveboard[filecount][rankcount] = ".";

					player = "b";
					p=!p;
				}else {
					//error
					MoveValidationErrors.illegalPromotion(line);
				}
			}
		}else {
			//black promotions
			if(rankcount!=1 && DSTrankcount!=0) {
				//error
				MoveValidationErrors.illegalPromotion(line);
			}else {
				if(moveboard[filecount][rankcount].equals("p") || moveboard[filecount][rankcount].equals("d")) {
					moveboard[DSTfilecount][DSTrankcount] = upgrade;
					moveboard[filecount][rankcount] = ".";

					player="w";
					p=!p;
					move++;
				}else {
					//error
					MoveValidationErrors.illegalPromotion(line);
				}
			}
		}

		Check.pce = upgrade;
		Check.file = MoveValid.DSTfilecount;
		Check.rank = MoveValid.DSTrankcount;

		filecount=0;
		rankcount=0;
		DSTfilecount=0;
		DSTrankcount=0;
	}

}