class GuiPiece{
	
	static int count = 1;
	static int Bcountstore;
	static int Wcountstore;
	static int fileDiff;
	static int fileDiffABS;
	static int rankDiff;
	static int rankDiffABS;
	
	static int SRCfile;
	static int SRCrank;
	static int DSTfile;
	static int DSTrank;
		
	static void move(String piece, int Sfile, int Srank) {
		SRCfile = Sfile;
		SRCrank = Srank;
		System.out.println("Colour dottos:" + piece);
		switch(piece) {
		
		case "p": 
		case "P": 
			GuiPawn.movevalid(piece, count); 
			Check.file = DSTfile;
			Check.rank = DSTrank;
			break;
		
		
		case "d":
		case "D": 
//			GuiSoldier.movevalid(Colour, count, take);
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
			break;
			
		case "r":
		case "R": 
//			fileDiff = DSTfile - SRCfile;
//			if(fileDiff<0){
//				fileDiffABS = (fileDiff*fileDiff)/(-fileDiff);//Math.abs(fileDiff);
//			}
//			de.bug("fileDiff:", fileDiff);
//			de.bug("fileDiffABS:", fileDiffABS);
//			rankDiff = DSTrank - SRCrank;
//			if(rankDiff<0){
//				rankDiffABS = (rankDiff*rankDiff)/(-rankDiff);
//			}
//			de.bug("rankDiff:", rankDiff);
//			de.bug("rankDiffABS:", rankDiffABS);
//			
//			GuiRook.movevalid(Colour, count, take);
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
//
//			fileDiff=0;
//			rankDiff=0;
			break;
			
		case "n":
		case "N": 
//			fileDiff = DSTfile - SRCfile;
//			fileDiffABS = Math.abs(fileDiff);
//			de.bug("fileDiff:", fileDiff);
//			de.bug("fileDiffABS:", fileDiffABS);
//			rankDiff = DSTrank - SRCrank;
//			rankDiffABS = Math.abs(rankDiff);
//			de.bug("rankDiff:", rankDiff);
//			de.bug("rankDiffABS:", rankDiffABS);
//			
//			GuiKnight.movevalid(Colour, count, take);
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
//			
//			fileDiff=0;
//			rankDiff=0;
			break;
			
		case "b":
		case "B": 
//				fileDiff = DSTfile - SRCfile;
//				if(fileDiff<0){
//					fileDiff = Math.abs(fileDiff);
//				}
//				de.bug("fileDiff:", fileDiff);
//				rankDiff = DSTrank - SRCrank;
//				if(rankDiff<0){
//					rankDiff = Math.abs(rankDiff);
//				}
//				de.bug("rankDiff:", rankDiff);
//			
//			
//			if(fileDiff==rankDiff) {
//				Bishop.DSTfile = DSTfile;
//				Bishop.SRCfile = SRCfile;
//				
//				Bishop.DSTrank = DSTrank;
//				Bishop.SRCrank = SRCrank;
//				
//				Bishop.movevalid(Colour, count, take);
//			}else{
//				if(take==true){
//					de.bug("not_diag!", 2);
//					MoveValidationErrors.illegalCapture(count);
//				}else{
//					de.bug("not_diag!", 2);
//					MoveValidationErrors.illegalMove(count);
//				}
//			}
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
			
			break;
			
		case "q":
		case "Q": 
//			fileDiff = DSTfile - SRCfile;
//			if(fileDiff<0){
//				fileDiffABS = (fileDiff*fileDiff)/(-fileDiff);//Math.abs(fileDiff);
//			}
//			de.bug("fileDiff:", fileDiff);
//			de.bug("fileDiffABS:", fileDiffABS);
//			rankDiff = DSTrank - SRCrank;
//			if(rankDiff<0){
//				rankDiffABS = (rankDiff*rankDiff)/(-rankDiff);
//			}
//			de.bug("rankDiff:", rankDiff);
//			de.bug("rankDiffABS:", rankDiffABS);
//			
//			Queen.movevalid(Colour, count, take);
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
//			fileDiff=0;
//			rankDiff=0;
			break;
			
		case "k":
		case "K": 
//			fileDiff = DSTfile - SRCfile;
//			de.bug("fileDiff:", fileDiff);
//			rankDiff = DSTrank - SRCrank;
//			de.bug("rankDiff:", rankDiff);
//			
//			King.movevalid(Colour, count, take);
////			Check.file = DSTfile;
////			Check.rank = DSTrank;
//			
//			fileDiff=0;
//			rankDiff=0;
			break;
			
		case "a":
		case "A": 
//			Amazon.movevalid(Colour, count, take);
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
			break;
			
		case "w":
		case "W": 
//			Princess.movevalid(Colour, count, take);
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
			break;
			
		case "f":
		case "F": 
//			FDragon.movevalid(Colour, count, take);
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
			break;
			
		case "e":
		case "E": 
//			Elephant.movevalid(Colour, count, take);
//			Check.file = DSTfile;
//			Check.rank = DSTrank;
			break;
		}
	}
	
}

class GuiPawn extends GuiPiece{
	static int allocation;
	
	static void movevalid(String Colour, int count) {
		System.out.println("Colour dottos2:" + Colour);
		System.out.println("Colour dottos (num):" + SRCfile + "_" + SRCrank);
		if(Colour.matches(".*\\p{Upper}.*")) {
			if(MoveValid.moveboard[SRCfile][SRCrank+1].equals(".")) {
				StdDraw.setPenColor(0, 128, 0);
				while(FairyChess.click == 1) {
					StdDraw.filledCircle((SRCfile*60)+20, ((SRCrank+1)*60)+20, 15);
					StdDraw.show();
				}
				
//				StdDraw.pause(75);
				
				
//				System.out.println("Colour dottos (num2):" + SRCfile + "_" + SRCrank);
			}
			
			
			
		}else if(Colour.equals("b")) {

		}
	}
}

class GuiSoldier extends GuiPiece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		if(Colour.equals("w")) {
			if(take==false) {
				if(DSTfile!=SRCfile && DSTfile!=SRCfile+1 && DSTfile!=SRCfile-1) {
					de.bug("soldierinvlaid!", 1);
					MoveValidationErrors.illegalMove(count);
				}
				if(DSTfile==SRCfile) {
					if(DSTrank>SRCrank+1) {
						de.bug("soldierinvlaid!", 11);
						MoveValidationErrors.illegalMove(count);
					}
					
					if(DSTrank<SRCrank) {
						de.bug("soldierinvlaid!", 12);
						MoveValidationErrors.illegalMove(count);
					}
				}
			}else {
				if(DSTfile!=SRCfile+1 && DSTfile!=SRCfile-1) {
					de.bug("soldierinvlaid!", 2);
					MoveValidationErrors.illegalCapture(count);
				}
				if(DSTrank!=SRCrank+1) {
					de.bug("soldierinvlaid!", 3);
					MoveValidationErrors.illegalCapture(count);
				}
			}
			Check.pce = "D";
			
		}else if(Colour.equals("b")) {
			if(take==false) {
				if(DSTfile!=SRCfile && DSTfile!=SRCfile+1 && DSTfile!=SRCfile-1) {
					de.bug("soliderinvlaid!", 4);
					MoveValidationErrors.illegalMove(count);
				}
				if(DSTfile==SRCfile) {
					
					if(DSTrank<SRCrank-1) {
						de.bug("soliderinvlaid!", 41);
						MoveValidationErrors.illegalMove(count);
					}
					
					if(DSTrank>SRCrank) {
						de.bug("soliderinvlaid!", 42);
						MoveValidationErrors.illegalMove(count);
					}
				}
			}else {
				if(DSTfile!=SRCfile+1 && DSTfile!=SRCfile-1) {
					de.bug("soliderinvlaid!", 5);
					MoveValidationErrors.illegalMove(count);
				}
				if(DSTrank!=SRCrank-1) {
					de.bug("soliderinvlaid!", 6);
					MoveValidationErrors.illegalMove(count);
				}
			}
			Check.pce = "d";
			
		}
	}
}

class GuiRook extends GuiPiece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		if(fileDiff!=0 && rankDiff!=0){
			de.bug("rook_invlaid!", 1);
			MoveValidationErrors.illegalMove(count);
		}
		if(take==false){
			if(fileDiff==0){
				if(rankDiff>0){
					for(int i=1; i<rankDiff+1; i++){
						if(!FairyChess.guiBoard[SRCfile][SRCrank+i].equals(".")){
							de.bug("rook_invlaid!", 2);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}else{
					for(int i=1; i<rankDiffABS+1; i++){
						if(!FairyChess.guiBoard[SRCfile][SRCrank-i].equals(".")){
							de.bug("rook_invlaid!", 21);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}
			}
			if(rankDiff==0){
				if(fileDiff>0){
					for(int i=1; i<fileDiff+1; i++){
						if(!FairyChess.guiBoard[SRCfile+i][SRCrank].equals(".")){
							de.bug("rook_invlaid!", 3);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}else{
					for(int i=1; i<fileDiffABS+1; i++){
						if(!FairyChess.guiBoard[SRCfile-i][SRCrank].equals(".")){
							de.bug("rook_invlaid!", 31);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}
			}
		}else{
			if(fileDiff==0){
				if(rankDiff>0){
					for(int i=1; i<rankDiff; i++){
						if(!FairyChess.guiBoard[SRCfile][SRCrank+i].equals(".")){
							de.bug("rook_invlaid!", 4);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}else{
					for(int i=1; i<rankDiffABS; i++){
						if(!FairyChess.guiBoard[SRCfile][SRCrank-i].equals(".")){
							de.bug("rook_invlaid!", 41);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}
			}
			if(rankDiff==0){
				if(fileDiff>0){
					for(int i=1; i<fileDiff; i++){
						if(!FairyChess.guiBoard[SRCfile+i][SRCrank].equals(".")){
							de.bug("rook_invlaid!", 5);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}else{
					for(int i=1; i<fileDiffABS; i++){
						if(!FairyChess.guiBoard[SRCfile-i][SRCrank].equals(".")){
							de.bug("rook_invlaid!", 51);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}
			}
		}
		if(Colour.equals("w")){
			Check.pce = "R";
		}else{
			Check.pce = "r";
		}
	}
}

class GuiKnight extends GuiPiece{
	static int allocation;

	static void movevalid(String Colour, int count, boolean take) {
		if(fileDiffABS+rankDiffABS!=3) {
			de.bug("knight_invalid!", 1);
			MoveValidationErrors.illegalMove(count);
		}
		
		if(Colour.equals("w")){
			Check.pce = "N";
		}else{
			Check.pce = "n";
		}
	}
}
