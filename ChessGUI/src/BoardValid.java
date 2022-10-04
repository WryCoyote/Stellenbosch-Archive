import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class BoardValid {
	static pawn bp = new pawn(); 
	static Rook br = new Rook(); 
	static Knight bn = new Knight(); 
	static Bishop bb = new Bishop(); 
	static Queen bq = new Queen();
	static King bk = new King();
	static DSoldier bd = new DSoldier(); 
	static Amazon ba = new Amazon(); 
	static Princess bw = new Princess(); 
	static FDragon bf = new FDragon(); 
	static Elephant be = new Elephant();
	
	static pawn wp = new pawn(); 
	static Rook wr = new Rook(); 
	static Knight wn = new Knight(); 
	static Bishop wb = new Bishop(); 
	static Queen wq = new Queen();
	static King wk = new King();
	static DSoldier wd = new DSoldier(); 
	static Amazon wa = new Amazon(); 
	static Princess ww = new Princess(); 
	static FDragon wf = new FDragon(); 
	static Elephant we = new Elephant();
	
	static int pt = 0;
	static int wpt = 0;
	static int ot = 0;
	static int wot = 0;
	
	static int count = 0;
	static int rankcount = 10;
	static private int filecount = 0;
	static private char [] file = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
	static String[] np = {"c", "g", "h", "i", "j", "l", "m", "o", "s", "t", "u", "v", "x", "y", "z"};
	
	static String[][] board = new String [10][10];
	
	static int move;
	static int halfmove;
	
	static int otota;
	static int ptota;
	
	static boolean SecA=true, SecB=false, SecC=false;
	
	static String args;
	
    static void check(String k) {
    	args = k;
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
        
        while (boardScanner.hasNextLine()) {
            String line = boardScanner.nextLine();
            while(SecA){
            	try{
	            	if(line.startsWith("%")){
	            		de.bug("debug", 1);
	            		count++;
	            	}else if(line.startsWith("-")){
	            		count++;
	            		if(line.length()==5){
		            		line = boardScanner.nextLine();
		            		
		            		if(count==2) {
		            			de.bug("c", count);
		            			BoardValidationErrors.illegalPieceAllocation(count);
		            		}
		            		specAllocVerfiy(count);
		            		SecB=true;
		            		SecA=false;
	            		}else{
	            			BoardValidationErrors.illegalPieceAllocation(count);
	            		}
	            		
	            		
	            	}else{
	            		count++;
	            		SecA(line);
	            	}
	            	break;
            	}catch(Exception ex){
            		BoardValidationErrors.illegalPieceAllocation(count);
            	}
            	
            }
            
            while(SecB){
            	if(line.startsWith("%")){
            		count++;
            	}else if(line.startsWith("-")){
            		count++;
            		if(line.length()==5){
	            		line =  boardScanner.nextLine();
	            		SecB=false;
	            		SecC=true;
            		}else{
            			BoardValidationErrors.illegalBoardDimension();
            		}
            	}else{
            		count++;
            		board(line, rankcount);
//            		rankcount--;
//            		if(rankcount==0){
//            			rankcount=10;
//            			
//            		}
//            		pawnSearch(rankcount);
//                	pawn.Bcountstore = pawn.Bcountstore + pt;
//                	pawn.Wcountstore = pawn.Wcountstore + wpt;
        			SecB();
            		rankcount--;
            		filecount=0;
            	} 
            	break;
            }
            
            
            while(SecC){
            	if(line.startsWith("%")){
            		count++;
            	}else if(line.startsWith("-") && line.length()==5){
            		count++;
            	}else{
            		count++;
            		SecC(line);
            	}
            	break;
            }
            
            //for board: each file is string, count items and beware spaces to validate
//            if(de.debug==true){
//            	System.out.println("\t | " + line);
////            	System.out.println();
//            	
//            }
            
        }
        boardScanner.close();
        for(int j=board.length-1; j>-1; j--){
			for(int i=0; i<board.length; i++){
				MoveValid.moveboard[i][j] = board[i][j];
				MoveStore.moveboard[i][j] = board[i][j];
			}
		}
    }


    public static void SecA (String line){
    	for(int i=0; i<line.length(); i++){
    		if(line.startsWith(np[i])){
//        		de.bug("space", count);
        	}
    	}
    	
    	
    	if(line.startsWith("p") && pawn.allocation==0){ //pawn allocation assignments
        	pawn.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length())); //try catch
//        	de.bug("p:", pawn.allocation);
        	if(pawn.allocation<10 && pawn.allocation>0) {
        		ptota = ptota + pawn.allocation;
        		if((DSoldier.allocation<10 && DSoldier.allocation>0)) {
        			if(ptota!=10){
        				BoardValidationErrors.illegalPieceAllocation(count);
        			}
        		}
        	}else if(pawn.allocation>10) {
        		BoardValidationErrors.illegalPieceAllocation(count);
        	}
        }else if(line.startsWith("p") && pawn.allocation!=0){
//        	de.bug("PallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }

    	if(line.startsWith("d") && DSoldier.allocation==0){  //drunken soldier allocation assignments
        	DSoldier.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("d:", DSoldier.allocation);
        	if(DSoldier.allocation<10 && DSoldier.allocation>0) {
        		ptota = ptota + DSoldier.allocation;
        		if((pawn.allocation<10 && pawn.allocation>0)){
        			if(ptota!=10){
        				BoardValidationErrors.illegalPieceAllocation(count);
        			}
        		}
        	}else if(DSoldier.allocation>10) {
        		BoardValidationErrors.illegalPieceAllocation(count);
        	}
        }else if(line.startsWith("d") && DSoldier.allocation!=0){
//        	de.bug("DallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("r") && Rook.allocation==0){  //rook allocation assignments
    		MoveValid.AllocOfficers = MoveValid.AllocOfficers + "r";
        	Rook.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("r:", Rook.allocation);
        	if(Rook.allocation<2) {
        		BoardValidationErrors.illegalPieceAllocation(count);
        	}else {
        		otota = otota + Rook.allocation;
        		if(otota>10) {
        			BoardValidationErrors.illegalPieceAllocation(count);
        		}
        	}
        }else if(line.startsWith("r") && Rook.allocation!=0){
//        	de.bug("RallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("n") && Knight.allocation==0){  //knight allocation assignments
    		MoveValid.AllocOfficers = MoveValid.AllocOfficers + "n";
        	Knight.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("n:", Knight.allocation);
        	otota = otota + Knight.allocation;
    		if(otota>10) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    		}
        }else if(line.startsWith("n") && Knight.allocation!=0){
//        	de.bug("NallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("b") && Bishop.allocation==0){  //bishop allocation assignments
    		MoveValid.AllocOfficers = MoveValid.AllocOfficers + "b";
        	Bishop.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("b:", Bishop.allocation);
        	otota = otota + Bishop.allocation;
    		if(otota>10) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    		}
        }else if(line.startsWith("b") && Bishop.allocation!=0){
//        	de.bug("BallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("q") && Queen.allocation==0){  //queen allocation assignments
    		MoveValid.AllocOfficers = MoveValid.AllocOfficers + "q";
        	Queen.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("q:", Queen.allocation);
        	otota = otota + Queen.allocation;
    		if(otota>10) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    		}
        }else if(line.startsWith("q") && Queen.allocation!=0){
//        	de.bug("QallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("k") && King.allocation==0){  //king allocation assignments
        	King.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("k:", King.allocation);
        	if(King.allocation!=1 || King.allocation==0) {
        		BoardValidationErrors.illegalPieceAllocation(count);
        	}else {
        		otota = otota + King.allocation;
        		if(otota>10) {
        			BoardValidationErrors.illegalPieceAllocation(count);
        		}
        	}
        }else if(line.startsWith("k") && King.allocation!=0){
//        	de.bug("KallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("a") && Amazon.allocation==0){  //amazon allocation assignments
    		MoveValid.AllocOfficers = MoveValid.AllocOfficers + "a";
        	Amazon.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("a:", Amazon.allocation);
        	otota = otota + Amazon.allocation;
    		if(otota>10) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    		}
        }else if(line.startsWith("a") && Amazon.allocation!=0){
//        	de.bug("AallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("w") && Princess.allocation==0){  //princess allocation assignments
    		MoveValid.AllocOfficers = MoveValid.AllocOfficers + "w";
        	Princess.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("b:", Princess.allocation);
        	otota = otota + Princess.allocation;
    		if(otota>10) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    		}
        }else if(line.startsWith("w") && Princess.allocation!=0){
//        	de.bug("PallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("f") && FDragon.allocation==0){  //flying dragon allocation assignments
    		MoveValid.AllocOfficers = MoveValid.AllocOfficers + "f";
        	FDragon.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("f:", FDragon.allocation);
        	otota = otota + FDragon.allocation;
    		if(otota>10) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    		}
        }else if(line.startsWith("f") && FDragon.allocation!=0){
//        	de.bug("FallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    	
    	if(line.startsWith("e") && Elephant.allocation==0){  //elephant allocation assignments
        	Elephant.allocation = Integer.parseInt(line.substring(line.indexOf(":")+1, line.length()));
//        	de.bug("e:", Elephant.allocation);
        	otota = otota + Elephant.allocation;
    		if(otota>10) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    		}
        }else if(line.startsWith("e") && Elephant.allocation!=0){
//        	de.bug("EallocBugs", count);
        	BoardValidationErrors.illegalPieceAllocation(count);
        }
    }
    	
    public static void specAllocVerfiy(int c) {
    	
    	if(pawn.allocation==0 && DSoldier.allocation==0) {
    		BoardValidationErrors.illegalPieceAllocation(c);
    	}
    	if(Rook.allocation==0){
    		BoardValidationErrors.illegalPieceAllocation(c);
    	}
    	if(King.allocation==0){
    		BoardValidationErrors.illegalPieceAllocation(c);
    	}
    }
   
    static int blank=0;
    public static void board(String l, int rc){
    	int r = rc-1;
//    	de.bug("lenth:", l.length());
    	for(int i=0; i<l.length(); i++){
    		if(l.charAt(i)==' '){
    			blank++;
    		}
    		if(i>0 &&l.charAt(i)!=' ' && l.charAt(i-1)==' '){
    			if((i>1 && l.charAt(i-2)==' ')){
    				de.bug(file[i-blank]+":", i);
    				BoardValidationErrors.illegalPiece(file[i-blank], rankcount);
    			}
    		}
    	}
    	
    	if(l.charAt(l.length()-1)==' '){
//    		de.bug("lengthtest:", l.length());
    		BoardValidationErrors.illegalBoardDimension();
    	}
//    	de.bug("blank:", blank);
    	blank=0;
    	String [] k = l.split(" ");
    	if(k.length!=10){
    		BoardValidationErrors.illegalBoardDimension();
    	}
    	pawnSearch(rc, k);
		pawn.Bcountstore = pawn.Bcountstore + pt;
    	pawn.Wcountstore = pawn.Wcountstore + wpt;
    	for(int i=0; i<k.length; i++){
//    		de.bug(k[i], r-1);
    		
//        	de.bug("pt->", pawn.Bcountstore);
//        	de.bug("wpt->", pawn.Wcountstore);
			board[i][r]=k[i];
//			de.bug(board[i][r]+"-", r);
    	}
//    	pawnSearch(rc);
//    	pawn.Bcountstore = pawn.Bcountstore + pt;
//    	pawn.Wcountstore = pawn.Wcountstore + wpt;
    }
    
    public static void pawnSearch(int rc, String [] k){
    	pt=0;
    	wpt=0;
    	int Bcount=0;
    	int Wcount=0;
    	
    	for(int i=0; i<k.length; i++){
    		if(k[i].equals("p")){
    			if(pawn.allocation==0){
    				BoardValidationErrors.illegalPiece(file[i], rc);
    			}else{
	    			bp.count++;
	    			if(bp.count>pawn.allocation){
	    				BoardValidationErrors.pawnAllocationExceeded(file[i], rc);
	    			}else{
	    				Bcount++;
	    			}
    			}
    		}
				
			if(k[i].equals("d")){
    			if(DSoldier.allocation==0){
    				BoardValidationErrors.illegalPiece(file[i], rc);
    			}else{
	    			bd.count++;
	    			if(bd.count>DSoldier.allocation){
	    				BoardValidationErrors.pawnAllocationExceeded(file[i], rc);
	    			}else{
	    				Bcount++;
	    			}
    			}
    		}
			
			
			if(k[i].equals("P")){
    			if(pawn.allocation==0){
    				BoardValidationErrors.illegalPiece(file[i], rc);
    			}else{
	    			wp.count++;
	    			if(wp.count>pawn.allocation){
	    				BoardValidationErrors.pawnAllocationExceeded(file[i], rc);
	    			}else{
	    				Wcount++;
	    			}
    			}
    		}
    		
    		
    		if(k[i].equals("D")){
    			if(DSoldier.allocation==0){
    				BoardValidationErrors.illegalPiece(file[i], rc);
    			}else{
	    			wd.count++;
	    			if(wd.count>DSoldier.allocation){
	    				BoardValidationErrors.pawnAllocationExceeded(file[i], rc);
	    			}else{
	    				Wcount++;
	    			}
    			}
    		}
    	}
    	pt = pt + Bcount;
    	wpt = wpt + Wcount;
//    	de.bug("pt>", pt);
//    	de.bug("wpt>", wpt);
    	
    	//board[i][r]
    	
    }
    
    public static void SecB (){
//    	de.bug("rank", rankcount);
    	for(int i=0; i<board.length; i++) {
    		filecount++;
    		if(board[i][rankcount-1].equals("r")){
    			if(Rook.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			br.count++;
	    			ot++;
    			}
    			if(br.count>officerVerify(Rook.allocation, pawn.Bcountstore)){
//    	    		de.bug("bRfile", filecount);
//    	    		de.bug("bR>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    		
    	    	}
    		}
    		
    		if(board[i][rankcount-1].equals("R")){
    			if(Rook.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			wr.count++;
	    			wot++;
//	    			de.bug("wRc:", wr.count);
//	    			de.bug("wRa:", Rook.allocation);
//	    			de.bug("wPcs:", pawn.Wcountstore);
    			}
//    			de.bug("pawnWcs:", pawn.Wcountstore);
    			if(wr.count>officerVerify(Rook.allocation, pawn.Wcountstore)){
    				
//    	    		de.bug("wRfile", filecount);
//    	    		de.bug("wR>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    		
    	    	}
    		}
    		if(board[i][rankcount-1].equals("n")){
    			if(Knight.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			bn.count++;
	    			ot++;
    			}
    			if(bn.count>officerVerify(Knight.allocation, pawn.Bcountstore)) {
    				
//    	    		de.bug("bNfile", filecount);
//    	    		de.bug("bN>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("N")){
    			if(Knight.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			wn.count++;
	    			wot++;
    			}
    			if(wn.count>officerVerify(Knight.allocation, pawn.Wcountstore)) {
//    	    		de.bug("wNfile", filecount);
//    	    		de.bug("wN>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("b")){
    			if(Bishop.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			bb.count++;
	    			ot++;
    			}
    			if(bb.count>officerVerify(Bishop.allocation, pawn.Bcountstore)) {
//    	    		de.bug("bBfile", filecount);
//    	    		de.bug("bB>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("B")){
    			if(Bishop.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			wb.count++;
	    			wot++;
    			}
    			if(wb.count>officerVerify(Bishop.allocation, pawn.Wcountstore)) {
//    	    		de.bug("wBfile", filecount);
//    	    		de.bug("wB>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("q")){
    			if(Queen.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			bq.count++;
	    			ot++;
//	    			de.bug("bQc:", bq.count);
//	    			de.bug("bQa:", Queen.allocation);
//	    			de.bug("bQpt:", pawn.Bcountstore);
//	    			de.bug("bQoffveri:", officerVerify(Queen.allocation, pawn.Bcountstore));
    			}
    			if(bq.count>officerVerify(Queen.allocation, pawn.Bcountstore)) {
    	    		de.bug("bQfile", filecount);
    	    		de.bug("bQ>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("Q")){
    			if(Queen.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			wq.count++;
	    			wot++;
    			}
    			
    			if(wq.count>officerVerify(Queen.allocation, pawn.Wcountstore)) {
    				de.bug("QpawnBcs:", pawn.Bcountstore);
    	    		de.bug("wQfile", filecount);
    	    		de.bug("wQ>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("k")){
    			bk.count++;
    			if(bk.count!=1) {
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else {
    				ot++;
    			}
    		}
    		if(board[i][rankcount-1].equals("K")){
    			wk.count++;
    			if(wk.count!=1) {
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else {
    				wot++;
    			}
    		}
    		
    		if(board[i][rankcount-1].equals("a")){
    			if(Amazon.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			ba.count++;
	    			ot++;
    			}
    			if(ba.count>officerVerify(Amazon.allocation, pawn.Bcountstore)) {
    	    		de.bug("bAfile", filecount);
    	    		de.bug("bA>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("A")){
    			if(Amazon.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			wa.count++;
	    			wot++;
    			}
    			if(wa.count>officerVerify(Amazon.allocation, pawn.Wcountstore)) {
    	    		de.bug("wAfile", filecount);
    	    		de.bug("wA>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("w")){
    			if(Princess.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			bw.count++;
	    			ot++;
    			}
    			if(bw.count>officerVerify(Princess.allocation, pawn.Bcountstore)) {
    	    		de.bug("bWfile", filecount);
    	    		de.bug("bW>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("W")){
    			if(Princess.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			ww.count++;
	    			wot++;
    			}
    			if(ww.count>officerVerify(Princess.allocation, pawn.Wcountstore)) {
    	    		de.bug("wWfile", filecount);
    	    		de.bug("wW>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("f")){
    			if(FDragon.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			bf.count++;
	    			ot++;
    			}
    			if(bf.count>officerVerify(FDragon.allocation, pawn.Bcountstore)) {
    	    		de.bug("bFfile", filecount);
    	    		de.bug("bF>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("F")){
    			if(FDragon.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
	    			wf.count++;
	    			wot++;
    			}
    			if(wf.count>officerVerify(FDragon.allocation, pawn.Wcountstore)) {
    	    		de.bug("wFfile", filecount);
    	    		de.bug("wF>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("e")){
    			if(rankcount<6) {
    				BoardValidationErrors.illegalElephantPosition(file[filecount-1], rankcount);
    			}else if(Elephant.allocation==0){
        			BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
        		}else{
    				be.count++;
    				ot++;
    			}
    			if(be.count>officerVerify(Elephant.allocation, pawn.Bcountstore)) {
    	    		de.bug("bEfile", filecount);
    	    		de.bug("bE>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		if(board[i][rankcount-1].equals("E")){
    			if(rankcount>5) {
    				BoardValidationErrors.illegalElephantPosition(file[filecount-1], rankcount);
    			}else if(Elephant.allocation==0){
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    			}else{
    				we.count++;
    				wot++;
    			}
    			if(we.count>officerVerify(Elephant.allocation, pawn.Wcountstore)) {
    	    		de.bug("wEfile", filecount);
    	    		de.bug("wE>"+file[filecount-1], rankcount);
    	    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	    	}
    		}
    		
    		for(int k=0; k<np.length; k++) {
    			if(board[i][rankcount-1].equalsIgnoreCase(np[k]) || board[i][rankcount-1].length()!=1) {
    				de.bug(file[filecount-1]+":*", rankcount);
    				BoardValidationErrors.illegalPiece(file[filecount-1], rankcount);
    				
    			}
    		}
    		
    	}
    	
    	if(ot+wot+pawn.Bcountstore+pawn.Wcountstore>40){
    		de.bug("over!", count);
    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	}
    	
    	officerCountVerify();
    	
//    	de.bug("bp", bp.count);	de.bug("wp", wp.count);
//    	de.bug("br", br.count); de.bug("wr", wr.count);
//    	de.bug("bn", bn.count);	de.bug("wn", wn.count);
//    	de.bug("bb", bb.count);	de.bug("wb", wb.count);
//    	de.bug("bq", bq.count);	de.bug("wq", wq.count);
//    	de.bug("bk", bk.count);	de.bug("wk", wk.count);
//    	
//    	de.bug("bd", bd.count);	de.bug("wd", wd.count);
//    	de.bug("ba", ba.count);	de.bug("wa", wa.count);
//    	de.bug("bw", bw.count);	de.bug("ww", ww.count);
//    	de.bug("bf", bf.count);	de.bug("wf", wf.count);
//    	de.bug("be", be.count);	de.bug("we", we.count);
    	
    	if(filecount>10 || rankcount<1) {
			de.bug("file", filecount);
			BoardValidationErrors.illegalBoardDimension();
		}
    	
    }
    
    public static void officerCountVerify(){
    	
    	if(ot>officerVerify(otota, pt)){
    		de.bug("verifycheckB", officerVerify(otota, pt));
    		de.bug(file[filecount-1]+"*:", rankcount);
    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	}
    	if(wot>officerVerify(otota, wpt)){
    		de.bug(file[filecount-1]+">:", rankcount);
    		BoardValidationErrors.officerAllocationExceeded(file[filecount-1], rankcount);
    	}
    }
    
    public static int officerVerify(int i, int pd){
//    	n = i+10-(p+d) n=total off on board, i = totoal alloc
    	int n = i+10-(pd);
    	return n;
    }
    
    public static void SecC(String line){
    	String [] l = line.split(":");
    	if((!l[0].equals("w") && !l[0].equals("b")) && l[0].length()!=1){
    		BoardValidationErrors.illegalNextPlayerMarker(count);
    	}
    	if(l[0].equals("w")){
			MoveValid.p = true;
			MoveValid.player = "w";
		}else{
			MoveValid.p = false;
			MoveValid.player = "b";
		}
    	
    	
    	if(l[1].charAt(0)=='+'){
    		MoveValid.castle[0] = "+";
    		if(!board[0][9].equals("r") || !board[5][9].equals("k")){
    			BoardValidationErrors.illegalCastlingOpportunity(count, 0);
    		}
    	}else {
    		MoveValid.castle[0]="-";
    	}
    	
    	if(l[1].charAt(1)=='+'){
    		MoveValid.castle[1] = "+";
    		if(!board[9][9].equals("r") || !board[5][9].equals("k")){
    			BoardValidationErrors.illegalCastlingOpportunity(count, 1);
    		}
    	}else {
    		MoveValid.castle[1]="-";
    	}
    	
    	if(l[1].charAt(2)=='+'){
    		MoveValid.castle[2] = "+";
    		if(!board[0][0].equals("R") || !board[5][0].equals("K")){
    			BoardValidationErrors.illegalCastlingOpportunity(count, 2);
    		}
    	}else {
    		MoveValid.castle[2]="-";
    	}
    	
    	if(l[1].charAt(3)=='+'){
    		MoveValid.castle[3] = "+";
    		if(!board[9][0].equals("R") || !board[5][0].equals("K")){
    			BoardValidationErrors.illegalCastlingOpportunity(count, 3);
    		}
    	}else {
    		MoveValid.castle[3]="-";
    	}
    	
    	//halfcount
    	if(l[2].matches(".*\\d.*")) {
    		halfmove = Integer.parseInt(l[2]);
    		if(halfmove>50 || halfmove<0) {
    			BoardValidationErrors.illegalHalfmoveClock(count);
    		}
    	}else {
    		BoardValidationErrors.illegalHalfmoveClock(count);
    	}
    	FairyChess.half = halfmove;
    	//count
    	if(l[3].matches(".*\\d.*")) {
    		move = Integer.parseInt(l[3]);
    		if(move<0) {
    			BoardValidationErrors.illegalMoveCounter(count);
    		}
    	}else {
    		BoardValidationErrors.illegalMoveCounter(count);
    	}
    	FairyChess.move = move;
    	
    	for(int i=0; i<l.length; i++) {
    		MoveValid.statusline[i]=l[i];
    	}
    	
    	FairyChess.status=line;
    	
//    	for(int i=0; i<l[1].length(); i++){
//    		MoveValid.castle[i] = l[1].charAt(i);
//    	}
    }
}
