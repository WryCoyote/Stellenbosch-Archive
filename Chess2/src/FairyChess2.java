import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The driver class for the Fairy Chess project.
 */


public class FairyChess2 {
	
	static String[][] board;
	
	static int pawnAllocTotal = 0;
	static int officerAllocTotal = 0;
	
	static boolean SectionA = true, SectionB1 = false, SectionB2 = false, SectionC = false;
								   //pawn-board checks  officer checks
	static int count = 0;
	static int rankcount = 10;
	
    public static void main(String[] args) {
    	
        // Get the board file's name, and initialize a File object to represent it
		if (args.length < 1) {
			throw new IllegalArgumentException("Provide a file name as first argument");
		}

        String boardFilename = args[0];
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
            while(SectionA==true) {
            	if(line.startsWith("%")) {
            		count++;
            	}else if(line.startsWith("-")) {
            		count++;
            		if(line.length()==5) {
            			line = boardScanner.nextLine();
	            		if(count==2) {
	            			de.bug("c", count);
	            			BoardValidationErrors.illegalPieceAllocation(count);
	            		}
            			SectionB1 = true;
            			SectionA = false;
            		}else {
            			BoardValidationErrors.illegalPieceAllocation(count);
            		}
            	}else {
            		count++;
            		SecA(line);
            	}
            	break;
            }
            
            
            //for board: each file is string, count items and beware spaces to validate
            if(de.debug==true){
            	System.out.println("\t | " + line);
            }
        }
        boardScanner.close();
    }

    //Sec A stuff
    public static void SecA (String l){
    	if(l.startsWith("p")) {	//Pawn Allocations
    		Pawn.allocation = pawnAlloc(l);
    		de.bug("test", Pawn.allocation);
    	}
    	
    	if(l.startsWith("d")) {	//Drunken Soldier Allocations
    		DS.allocation = pawnAlloc(l);
    		de.bug("test", DS.allocation);
    	}
    	
    	if(l.startsWith("r")) {  //Rook ALlocations
    		Rook.allocation = OfficerAlloc(l);
    		de.bug("test", Rook.allocation);
    		if(Rook.allocation<2) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    		}
    		
    	}
    	
    	if(l.startsWith("n")) {  //Knight ALlocations
    		Knight.allocation = OfficerAlloc(l);
    		de.bug("test", Knight.allocation);
    	}
    	
    	if(l.startsWith("b")) {  //Bishop ALlocations
    		Bishop.allocation = OfficerAlloc(l);
    		de.bug("test", Bishop.allocation);
    	}
    	
    	if(l.startsWith("q")) { //Queen Allocations
    		Queen.allocation = OfficerAlloc(l);
    		de.bug("test", Queen.allocation);
    	}
    	
    	if(l.startsWith("k")) {  //King ALlocations
    		King.allocation = OfficerAlloc(l);
    		de.bug("test", King.allocation);
    		if(King.allocation!=1) {
    			BoardValidationErrors.illegalPieceAllocation(count);
    			
    		}
    		
    	}
    	
    	if(l.startsWith("a")) { //Amazon Allocations
    		Amazon.allocation = OfficerAlloc(l);
    		de.bug("test", Amazon.allocation);
    	}
    	
    	if(l.startsWith("w")) { //Princess Allocations
    		Princess.allocation = OfficerAlloc(l);
    		de.bug("test", Princess.allocation);
    	}
    	
    	if(l.startsWith("f")) { //Flying Dragon Allocations
    		FD.allocation = OfficerAlloc(l);
    		de.bug("test", FD.allocation);
    	}
    	
    	if(l.startsWith("e")) { //Elephant Allocations
    		Elephant.allocation = OfficerAlloc(l);
    		de.bug("test", Elephant.allocation);
    	}
    }
    	
    public static int pawnAlloc(String k) {
    	int alloc=0;
    	try{
			alloc = Integer.parseInt(k.substring(k.indexOf(':')+1, k.length()));
		}catch(Exception ex) {
			BoardValidationErrors.illegalPieceAllocation(count);
		}
		de.bug(k.charAt(0)+":", alloc);
		if(alloc<10 && alloc>0) {
			pawnAllocTotal += alloc;
			if(pawnAllocTotal>10) {
				BoardValidationErrors.illegalPieceAllocation(count);
			}
		}else {
			BoardValidationErrors.illegalPieceAllocation(count);
		}
    	return alloc;
    }
    
   
    public static int OfficerAlloc(String k) {
    	int alloc=0;
    	try{
			alloc = Integer.parseInt(k.substring(k.indexOf(':')+1, k.length()));
		}catch(Exception ex) {
			BoardValidationErrors.illegalPieceAllocation(count);
		}
		de.bug(k.charAt(0)+":", alloc);
		officerAllocTotal += alloc;
		if(officerAllocTotal>10) {
			BoardValidationErrors.illegalPieceAllocation(count);
		}
		
		return alloc;
    }
    static int blank=0;
    public static void Board(String l, int rc){
    	
    }
    
    //Sec B stufff
    public static void pawnSearch(int rc, String [] k){
    	
    }
    
   
    public static void SecB (){
    	
    }
    
   
    public static void officerCountVerify(){
    	
    }
    
    public static int officerVerify(int totAlloc, int pwn, int ds){
//    	n = i+10-(p+d) n=total off on board, i = totoal alloc
    	int onBoard = totAlloc + 10 - (pwn + ds);
    	return onBoard;
    }
    
    
    //Sec C stuuf
    public static void SecC(String line){
    	
    }
}

