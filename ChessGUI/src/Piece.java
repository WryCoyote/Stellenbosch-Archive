class Piece{
	
	int count;
	static int Bcountstore;
	static int Wcountstore;
	static int fileDiff;
	static int fileDiffABS;
	static int rankDiff;
	static int rankDiffABS;
		
	static void move(String Colour, int count, String piece, boolean take, boolean check) {
		switch(piece) {
		
		case "p": 
		case "P": 
			pawn.movevalid(Colour, count, take); 
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			break;
		
		
		case "d":
		case "D": 
			DSoldier.movevalid(Colour, count, take);
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			break;
			
		case "r":
		case "R": 
			Rook.movevalid(Colour, count, take);
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			break;
			
		case "n":
		case "N": 
			fileDiff = MoveValid.DSTfilecount - MoveValid.filecount;
			if(fileDiff<0){
				fileDiffABS = (fileDiff*fileDiff)/(-fileDiff);//Math.abs(fileDiff);
			}
			de.bug("fileDiff:", fileDiff);
			de.bug("fileDiffABS:", fileDiffABS);
			rankDiff = MoveValid.DSTrankcount - MoveValid.rankcount;
			if(rankDiff<0){
				rankDiffABS = (rankDiff*rankDiff)/(-rankDiff);
			}
			de.bug("rankDiff:", rankDiff);
			de.bug("rankDiffABS:", rankDiffABS);
			
			Knight.movevalid(Colour, count, take);
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			
			fileDiff=0;
			rankDiff=0;
			break;
			
		case "b":
		case "B": 
				fileDiff = MoveValid.DSTfilecount - MoveValid.filecount;
				if(fileDiff<0){
					fileDiff = (fileDiff*fileDiff)/(-fileDiff);//Math.abs(fileDiff);
				}
				de.bug("fileDiff:", fileDiff);
				rankDiff = MoveValid.DSTrankcount - MoveValid.rankcount;
				if(rankDiff<0){
					rankDiff = (rankDiff*rankDiff)/(-rankDiff);
				}
				de.bug("rankDiff:", rankDiff);
			
			
			if(fileDiff==rankDiff) {
				Bishop.DSTfile = MoveValid.DSTfilecount;
				Bishop.SRCfile = MoveValid.filecount;
				
				Bishop.DSTrank = MoveValid.DSTrankcount;
				Bishop.SRCrank = MoveValid.rankcount;
				
				Bishop.movevalid(Colour, count, take);
			}else{
				if(take==true){
					de.bug("not_diag!", 2);
					MoveValidationErrors.illegalCapture(count);
				}else{
					de.bug("not_diag!", 2);
					MoveValidationErrors.illegalMove(count);
				}
			}
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			
			break;
			
		case "q":
		case "Q": 
			fileDiff = MoveValid.DSTfilecount - MoveValid.filecount;
			if(fileDiff<0){
				fileDiffABS = Math.abs(fileDiff);
			}
			de.bug("fileDiff:", fileDiff);
			de.bug("fileDiffABS:", fileDiffABS);
			rankDiff = MoveValid.DSTrankcount - MoveValid.rankcount;
			if(rankDiff<0){
				rankDiffABS = Math.abs(rankDiff);
			}
			de.bug("rankDiff:", rankDiff);
			de.bug("rankDiffABS:", rankDiffABS);
			
			Queen.movevalid(Colour, count, take);
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			fileDiff=0;
			rankDiff=0;
			break;
			
		case "k":
		case "K": 
			fileDiff = MoveValid.DSTfilecount - MoveValid.filecount;
			de.bug("fileDiff:", fileDiff);
			rankDiff = MoveValid.DSTrankcount - MoveValid.rankcount;
			de.bug("rankDiff:", rankDiff);
			
			King.movevalid(Colour, count, take);
//			Check.file = MoveValid.DSTfilecount;
//			Check.rank = MoveValid.DSTrankcount;
			
			fileDiff=0;
			rankDiff=0;
			break;
			
		case "a":
		case "A": 
			Amazon.movevalid(Colour, count, take);
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			break;
			
		case "w":
		case "W": 
			Princess.movevalid(Colour, count, take);
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			break;
			
		case "f":
		case "F": 
			FDragon.movevalid(Colour, count, take);
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			break;
			
		case "e":
		case "E": 
			Elephant.movevalid(Colour, count, take);
			Check.file = MoveValid.DSTfilecount;
			Check.rank = MoveValid.DSTrankcount;
			break;
		}
	}
	
}

class pawn extends Piece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		if(Colour.equals("w")) {
			if(take==false) {
				if(MoveValid.DSTfilecount!=MoveValid.filecount) {
					de.bug("pawninvlaid!", 1);
					MoveValidationErrors.illegalMove(count);
				}
				if(MoveValid.DSTfilecount==MoveValid.filecount) {
					if(MoveValid.DSTrankcount==MoveValid.rankcount+2 && MoveValid.rankcount==1) {
						if(!MoveValid.moveboard[MoveValid.filecount][MoveValid.rankcount+1].equals(".")) {
							de.bug("pawninvlaid!", 11);
							MoveValidationErrors.illegalMove(count);
						}
					}else if(MoveValid.DSTrankcount>MoveValid.rankcount+2){
						MoveValidationErrors.illegalMove(count);
					}
					
					if(MoveValid.DSTrankcount>MoveValid.rankcount+1 && MoveValid.rankcount!=1) {
						de.bug("pawninvlaid!", 12);
						MoveValidationErrors.illegalMove(count);
					}
					
					if(MoveValid.DSTrankcount<MoveValid.rankcount) {
						de.bug("pawninvlaid!", 13);
						MoveValidationErrors.illegalMove(count);
					}
				}
			}else {
				if(MoveValid.DSTfilecount!=MoveValid.filecount+1 && MoveValid.DSTfilecount!=MoveValid.filecount-1) {
					de.bug("pawninvlaid!", 2);
					MoveValidationErrors.illegalCapture(count);
				}
				if(MoveValid.DSTrankcount!=MoveValid.rankcount+1) {
					de.bug("pawninvlaid!", 3);
					MoveValidationErrors.illegalCapture(count);
				}
			}
			Check.pce = "P";
		}else if(Colour.equals("b")) {
			if(take==false) {
				if(MoveValid.DSTfilecount!=MoveValid.filecount) {
					de.bug("pawninvlaid!", 4);
					MoveValidationErrors.illegalMove(count);
				}
				if(MoveValid.DSTfilecount==MoveValid.filecount) {
					if(MoveValid.DSTrankcount==MoveValid.rankcount-2 && MoveValid.rankcount==8) {
						if(!MoveValid.moveboard[MoveValid.filecount][MoveValid.rankcount-1].equals(".")) {
							de.bug("pawninvlaid!", 21);
							MoveValidationErrors.illegalMove(count);
						}
					}else if(MoveValid.DSTrankcount<MoveValid.rankcount-2){
						MoveValidationErrors.illegalMove(count);
					}
					
					if(MoveValid.DSTrankcount<MoveValid.rankcount-1 && MoveValid.rankcount!=8) {
						de.bug("pawninvlaid!", 22);
						MoveValidationErrors.illegalMove(count);
					}
					
					if(MoveValid.DSTrankcount>MoveValid.rankcount) {
						de.bug("pawninvlaid!", 23);
						MoveValidationErrors.illegalMove(count);
					}
				}
			}else {
				if(MoveValid.DSTfilecount!=MoveValid.filecount+1 && MoveValid.DSTfilecount!=MoveValid.filecount-1) {
					de.bug("pawninvlaid!", 5);
					MoveValidationErrors.illegalMove(count);
				}
				if(MoveValid.DSTrankcount!=MoveValid.rankcount-1) {
					de.bug("pawninvlaid!", 6);
					MoveValidationErrors.illegalMove(count);
				}
			}
			Check.pce = "p";
		}
	}
}

class DSoldier extends Piece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		if(Colour.equals("w")) {
			if(take==false) {
				if(MoveValid.DSTfilecount!=MoveValid.filecount && MoveValid.DSTfilecount!=MoveValid.filecount+1 && MoveValid.DSTfilecount!=MoveValid.filecount-1) {
					de.bug("soldierinvlaid!", 1);
					MoveValidationErrors.illegalMove(count);
				}
				if(MoveValid.DSTfilecount==MoveValid.filecount) {
					if(MoveValid.DSTrankcount>MoveValid.rankcount+1) {
						de.bug("soldierinvlaid!", 11);
						MoveValidationErrors.illegalMove(count);
					}
					
					if(MoveValid.DSTrankcount<MoveValid.rankcount) {
						de.bug("soldierinvlaid!", 12);
						MoveValidationErrors.illegalMove(count);
					}
				}
			}else {
				if(MoveValid.DSTfilecount!=MoveValid.filecount+1 && MoveValid.DSTfilecount!=MoveValid.filecount-1) {
					de.bug("soldierinvlaid!", 2);
					MoveValidationErrors.illegalCapture(count);
				}
				if(MoveValid.DSTrankcount!=MoveValid.rankcount+1) {
					de.bug("soldierinvlaid!", 3);
					MoveValidationErrors.illegalCapture(count);
				}
			}
			Check.pce = "D";
			
		}else if(Colour.equals("b")) {
			if(take==false) {
				if(MoveValid.DSTfilecount!=MoveValid.filecount && MoveValid.DSTfilecount!=MoveValid.filecount+1 && MoveValid.DSTfilecount!=MoveValid.filecount-1) {
					de.bug("soliderinvlaid!", 4);
					MoveValidationErrors.illegalMove(count);
				}
				if(MoveValid.DSTfilecount==MoveValid.filecount) {
					
					if(MoveValid.DSTrankcount<MoveValid.rankcount-1) {
						de.bug("soliderinvlaid!", 41);
						MoveValidationErrors.illegalMove(count);
					}
					
					if(MoveValid.DSTrankcount>MoveValid.rankcount) {
						de.bug("soliderinvlaid!", 42);
						MoveValidationErrors.illegalMove(count);
					}
				}
			}else {
				if(MoveValid.DSTfilecount!=MoveValid.filecount+1 && MoveValid.DSTfilecount!=MoveValid.filecount-1) {
					de.bug("soliderinvlaid!", 5);
					MoveValidationErrors.illegalMove(count);
				}
				if(MoveValid.DSTrankcount!=MoveValid.rankcount-1) {
					de.bug("soliderinvlaid!", 6);
					MoveValidationErrors.illegalMove(count);
				}
			}
			Check.pce = "d";
			
		}
	}
}

class Rook extends Piece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		
		System.out.println("src:" + MoveValid.filecount + "->" + MoveValid.rankcount);
		System.out.println("dst:" + MoveValid.DSTfilecount + "->" + MoveValid.DSTrankcount);
		
		
		
		
		if(MoveValid.filecount==MoveValid.DSTfilecount){
			
			if(MoveValid.rankcount<MoveValid.DSTrankcount){
				up(count);
			}else if(MoveValid.rankcount>MoveValid.DSTrankcount){
				down(count);
			}

		}else if(MoveValid.rankcount==MoveValid.DSTrankcount){
			
			if(MoveValid.filecount<MoveValid.DSTfilecount){
				right(count);
			}else if(MoveValid.filecount>MoveValid.DSTfilecount){
				left(count);
			}
			
		}
		
		
		
		
		if(Colour.equals("w")){
			Check.pce = "R";
		}else{
			Check.pce = "r";
		}
	}
	
	private static void up(int count){
		for (int i=MoveValid.rankcount+1; i<MoveValid.DSTrankcount; i++){
			if(!MoveValid.moveboard[MoveValid.filecount][i].equals(".")){
				System.out.println("probos:" + MoveValid.moveboard[MoveValid.filecount][i]);
				de.bug("rook_invlaid!", 1);
				MoveValidationErrors.illegalMove(count);
			}
		}
	}
	private static void down(int count){
		for (int i=MoveValid.rankcount-1; i>MoveValid.DSTrankcount; i--){
			if(!MoveValid.moveboard[MoveValid.filecount][i].equals(".")){
				de.bug("rook_invlaid!", 2);
				MoveValidationErrors.illegalMove(count);
			}
		}
	}
	private static void left(int count){
		for (int i=MoveValid.filecount-1; i>MoveValid.DSTfilecount+1; i--){
			if(!MoveValid.moveboard[i][MoveValid.rankcount].equals(".")){
				de.bug("rook_invlaid!", 3);
				MoveValidationErrors.illegalMove(count);
			}
		}
	}
	private static void right(int count){
		for (int i=MoveValid.filecount+1; i<MoveValid.DSTfilecount-1; i++){
			if(!MoveValid.moveboard[i][MoveValid.rankcount].equals(".")){
				de.bug("rook_invlaid!", 4);
				MoveValidationErrors.illegalMove(count);
			}
		}
	}


}

class Knight extends Piece{
	static int allocation;

	static void movevalid(String Colour, int count, boolean take) {
		if((MoveValid.DSTfilecount == MoveValid.filecount+2) || (MoveValid.DSTfilecount == MoveValid.filecount-2)){
			if((MoveValid.DSTrankcount != MoveValid.rankcount+1) && (MoveValid.DSTrankcount != MoveValid.rankcount-1)){
				de.bug("knight_invalid!", 1);
				MoveValidationErrors.illegalMove(count);
			}
		}
		if((MoveValid.DSTrankcount == MoveValid.rankcount+2) || (MoveValid.DSTrankcount == MoveValid.rankcount-2)){
			if((MoveValid.DSTfilecount != MoveValid.filecount+1) && (MoveValid.DSTfilecount != MoveValid.filecount-1)){
				de.bug("knight_invalid!", 2);
				MoveValidationErrors.illegalMove(count);
			}
		}
		
		if(Colour.equals("w")){
			Check.pce = "N";
		}else{
			Check.pce = "n";
		}
	}
}
