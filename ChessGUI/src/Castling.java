public class Castling {
	
	public static void valid(String m, int line, boolean p, String player) {
		kingCheck.castle=true;
		if(m.equals("0-0-0")) {
			if(p==false) {
				System.out.println("stuffs_1");
				queensideBlack(line);
			}else {
				System.out.println("stuffs_2");
				queensideWhite(line);
			}
		}else if(m.equals("0-0")) {
			if(p==false) {
				System.out.println("stuffs_3");
				kingsideBlack(line);
			}else {
				System.out.println("stuffs_4");
				kingsideWhite(line);
			}
		}
		kingCheck.castle=false;
		
		MoveValid.p=!MoveValid.p;
	}
	
	
	public static void queensideBlack(int line) {
		if(MoveValid.castle[0].equals("-")) {
			MoveValidationErrors.illegalCastlingMove(line);
		}
		if(MoveValid.moveboard[0][9].equals("r") && MoveValid.moveboard[5][9].equals("k")) {
			for(int i=1; i<5; i++) {
				
				if(!MoveValid.moveboard[i][9].equals(".")) {
					//de.bug("bad_castle!", 1);
					MoveValidationErrors.illegalCastlingMove(line);
				}
			}
			
			for(int i=5; i>0; i--){
				kingCheck.kr=9;
				kingCheck.kf=i;
				kingCheck.main(false, line, false);
			}
			
			MoveValid.moveboard[2][9]="k";
			MoveValid.moveboard[3][9]="r";
			
			MoveValid.moveboard[0][9]=".";
			MoveValid.moveboard[5][9]=".";
			
			MoveValid.player="w";
			MoveValid.castle[0] = "-";
			MoveValid.castle[1] = "-";
			
			//de.bug(MoveValid.castle[0] + " indi?", 02);
			//de.bug(MoveValid.castle[1] + " indi?", 12);
			
			MoveValid.halfmove++;
			MoveValid.move++;
		}else {
			//de.bug("bad_castle!", 11);
			MoveValidationErrors.illegalCastlingMove(line);
		}
	}
	
	public static void queensideWhite(int line) {
		if(MoveValid.castle[2].equals("-")) {
			MoveValidationErrors.illegalCastlingMove(line);
		}
		if(MoveValid.moveboard[0][0].equals("R") && MoveValid.moveboard[5][0].equals("K")) {
			for(int i=1; i<5; i++) {
				if(!MoveValid.moveboard[i][0].equals(".")) {
					//de.bug("bad_castle!", 2);
					MoveValidationErrors.illegalCastlingMove(line);
				}
			}
			
			for(int i=5; i>0; i--){
				kingCheck.kr=0;
				kingCheck.kf=i;
				kingCheck.main(true, line, false);
			}
			
			MoveValid.moveboard[2][0]="K";
			MoveValid.moveboard[3][0]="R";
			
			MoveValid.moveboard[0][0]=".";
			MoveValid.moveboard[5][0]=".";
			
			MoveValid.player="b";
			MoveValid.castle[2] = "-";
			MoveValid.castle[3] = "-";
			
			//de.bug(MoveValid.castle[2] + " indi?", 22);
			//de.bug(MoveValid.castle[3] + " indi?", 32);
			
			MoveValid.halfmove++;
		}else {
			//error
			//de.bug("bad_castle!", 21);
			MoveValidationErrors.illegalCastlingMove(line);
		}
	}
	
	public static void kingsideBlack(int line) {
		if(MoveValid.castle[1].equals("-")) {
			MoveValidationErrors.illegalCastlingMove(line);
		}
		if(MoveValid.moveboard[9][9].equals("r") && MoveValid.moveboard[5][9].equals("k")) {
			for(int i=6; i<9; i++) {
				if(!MoveValid.moveboard[i][9].equals(".")) {
					//de.bug("bad_castle!", 3);
					MoveValidationErrors.illegalCastlingMove(line);
				}
			}
			
			for(int i=5; i<9; i++){
				kingCheck.kr=9;
				kingCheck.kf=i;
				kingCheck.main(false, line, false);
			}
			                      
			
			
			MoveValid.moveboard[8][9]="k";
			MoveValid.moveboard[7][9]="r";
			
			MoveValid.moveboard[9][9]=".";
			MoveValid.moveboard[5][9]=".";
			
			MoveValid.player="w";
			MoveValid.castle[1] = "-";
			MoveValid.castle[0] = "-";
			
			//de.bug(MoveValid.castle[0] + " indi?", 03);
			//de.bug(MoveValid.castle[1] + " indi?", 13);
			
			MoveValid.halfmove++;
			MoveValid.move++;
		}else {
			//error
			//de.bug("bad_castle!", 31);
			MoveValidationErrors.illegalCastlingMove(line);
		}
	}
	
	public static void kingsideWhite(int line) {
		if(MoveValid.castle[3].equals("-")) {
			MoveValidationErrors.illegalCastlingMove(line);
		}
		if(MoveValid.moveboard[9][0].equals("R") && MoveValid.moveboard[5][0].equals("K")) {
			for(int i=6; i<9; i++) {
				if(!MoveValid.moveboard[i][0].equals(".")) {
					//de.bug("bad_castle!", 4);
					MoveValidationErrors.illegalCastlingMove(line);
				}
			}
			
			for(int i=5; i<9; i++){
				kingCheck.kr=0;
				kingCheck.kf=i;
				kingCheck.main(true, line, false);
			}
			
			MoveValid.moveboard[8][0]="K";
			MoveValid.moveboard[7][0]="R";
			
			MoveValid.moveboard[9][0]=".";
			MoveValid.moveboard[5][0]=".";
			
			MoveValid.player="b";
			MoveValid.castle[3] = "-";
			MoveValid.castle[2] = "-";
			
			//de.bug(MoveValid.castle[2] + " indi?", 23);
			//de.bug(MoveValid.castle[3] + " indi?", 33);
			
			MoveValid.halfmove++;
		}else {
			//error
			//de.bug("bad_castle!", 41);
			MoveValidationErrors.illegalCastlingMove(line);
		}
	}
}
