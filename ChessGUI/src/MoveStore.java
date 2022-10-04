import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class MoveStore {

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
	
	static String checkMate;
	static boolean CheckMate = false;

	static boolean p;   //true = white  //false = black
	
	public static String[] storeMoves;

	static void check(String args1) {
		MoveReadIn(args1);
        storeMoves = new String [count];
        StoreMoves(args1);
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
//            		
            	}else{
            		count++;
            	}
            	break;
            }
        }
        moveScanner.close();
	}

	public static void StoreMoves(String args1) {
		count = 0;
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
		            		
		            	}else{
		            		storeMoves[count] = line;
		            		count++;
		            	}
		            	break;
		            }
		        }
		        moveScanner.close();
	}
}