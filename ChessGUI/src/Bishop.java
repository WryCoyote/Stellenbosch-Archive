class Bishop extends Piece{
	static int allocation;
	
	
	static boolean take;
	static int SRCfile; //kf
	static int SRCrank; //kr
	static int DSTfile; //file
	static int DSTrank; //rank
	
	static void movevalid(String Colour, int count, boolean capture) {
		de.bug("bishop_valid????", 1);
		take = capture;
		canMove(count, Colour);
		if(Colour.equals("w")){
			Check.pce = "B";
		}else{
			Check.pce = "b";
		}
	}
	
	public static void canMove(int line, String colour){
		de.bug("bishop_valid????", 2);
		if(DSTfile>SRCfile){
			de.bug("bishop_valid????", 3);
			if(DSTrank>SRCrank){
				diagDownL(line);
				
			}else if(SRCrank>DSTrank){
				diagUpL(line);
				
			}
			
		}else if(SRCfile>DSTfile){
			
			if(DSTrank<SRCrank){
				diagDownR(line);
				
			}else if(SRCrank<DSTrank){
				diagUpR(line);
				
			}
		}
	}
	static int Fcount;	//from bishop
	
/* 5*/	public static void diagDownL(int line){  // DSTfile>SRCfile; DSTrank>SRCrank
			for(int i=DSTrank-1; i>SRCrank; i--){
				for(int j=DSTfile-1; j>SRCfile; j--){
					if(MoveValid.moveboard[j][i].equals(".")){
						Fcount++;
					}else if(Fcount==0){
						de.bug("bishop_bad", 5);
						Error(line);
					}
				}
			}
			Fcount=0;
		}
/* 4*/	public static void diagUpL(int line){	// DSTfile>SRCfile; DSTrank<SRCrank
			for(int i=DSTrank+1; i<SRCrank; i++){
				for(int j=DSTfile-1; j>SRCfile; j--){
					if(MoveValid.moveboard[j][i].equals(".")){
						Fcount++;
					}else if(Fcount==0){
						de.bug("bishop_bad", 4);
						Error(line);
					}
				}
			}
			Fcount=0;
		}
/* 2*/	public static void diagDownR(int line){	// DSTfile<SRCfile; DSTrank>SRCrank
			for(int i=DSTrank-1; i>SRCrank; i--){
				for(int j=DSTfile+1; j<SRCfile; j++){
					if(MoveValid.moveboard[j][i].equals(".")){
						Fcount++;
					}else if(Fcount==0){
						de.bug("bishop_bad", 2);
						Error(line);
					}
				}
			}
			Fcount=0;
		}
/* 1*/	public static void diagUpR(int line){ 	// DSTfile<SRCfile; DSTrank<SRCrank
			de.bug("DST>>" + DSTfile + "-", DSTrank);
			de.bug("SRC>>" + SRCfile + "-", SRCrank);
			for(int i=DSTrank+1; i<SRCrank; i++){
				for(int j=DSTfile+1; j<SRCfile; j++){
					if(MoveValid.moveboard[j][i].equals(".")){
						Fcount++;
					}else if(Fcount==0){
						de.bug("bishop_bad", 1);
						Error(line);
					}
				}
			}
			Fcount=0;
		}


	public static void Error(int line){
		if(take==true){
			de.bug("bishop_bad_move!", 1);
			MoveValidationErrors.illegalCapture(line);
		}else{
			de.bug("bishop_bad_move!", 2);
			MoveValidationErrors.illegalMove(line);
		}
	}

}

/*
if(fileDiff==0 && rankDiff==0){
de.bug("bishop_invlaid!", 1);
MoveValidationErrors.illegalMove(count);
}

if(take==false){
if(fileDiff>0){
	if(rankDiff>0){
		for(int i=1; i<rankDiff+1; i++){
			for(int k=1; k<fileDiff+1; k++){
				if(!MoveValid.moveboard[MoveValid.filecount+k][MoveValid.rankcount+i].equals(".")){
					de.bug("bishop_invlaid!", 2);
					MoveValidationErrors.illegalMove(count);
				}
			}
		}
	}else{
		for(int i=1; i<rankDiffABS+1; i++){
			for(int k=1; k<fileDiff+1; k++){
				de.bug(MoveValid.moveboard[MoveValid.filecount+k][MoveValid.rankcount-i]+":", 2);
				if(!MoveValid.moveboard[MoveValid.filecount+k][MoveValid.rankcount-i].equals(".")){
					de.bug("bishop_invlaid!", 21);
					MoveValidationErrors.illegalMove(count);
				}
			}
		}
	}
}else{
	if(rankDiff>0){
		for(int i=1; i<rankDiff+1; i++){
			for(int k=1; k<fileDiffABS+1; k++){
				if(!MoveValid.moveboard[MoveValid.filecount-k][MoveValid.rankcount+i].equals(".")){
					de.bug("bishop_invlaid!", 3);
					MoveValidationErrors.illegalMove(count);
				}
			}
		}
	}else{
		for(int i=1; i<rankDiffABS+1; i++){
			for(int k=1; k<fileDiffABS+1; k++){
				if(!MoveValid.moveboard[MoveValid.filecount-k][MoveValid.rankcount-i].equals(".")){
					de.bug("bishop_invlaid!", 31);
					MoveValidationErrors.illegalMove(count);
				}
			}
		}
	}
}

}else{

if(fileDiff>0){
	if(rankDiff>0){
		for(int i=1; i<rankDiff; i++){
			for(int k=1; k<fileDiff; k++){
				if(!MoveValid.moveboard[MoveValid.filecount+k][MoveValid.rankcount+i].equals(".")){
					de.bug("bishop_invlaid!", 4);
					MoveValidationErrors.illegalMove(count);
				}
			}
		}
	}else{
		for(int i=1; i<rankDiffABS; i++){
			for(int k=1; k<fileDiff; k++){
				if(!MoveValid.moveboard[MoveValid.filecount+k][MoveValid.rankcount-i].equals(".")){
					de.bug("bishop_invlaid!", 41);
					MoveValidationErrors.illegalMove(count);
				}
			}
		}
	}
}else{
	if(rankDiff>0){
		for(int i=1; i<rankDiff; i++){
			for(int k=1; k<fileDiffABS; k++){
				if(!MoveValid.moveboard[MoveValid.filecount-k][MoveValid.rankcount+i].equals(".")){
					de.bug("bishop_invlaid!", 5);
					MoveValidationErrors.illegalMove(count);
				}
			}
		}
	}else{
		for(int i=1; i<rankDiffABS; i++){
			for(int k=1; k<fileDiffABS; k++){
				if(!MoveValid.moveboard[MoveValid.filecount-k][MoveValid.rankcount-i].equals(".")){
					de.bug("bishop_invlaid!", 51);
					MoveValidationErrors.illegalMove(count);
				}
			}
		}
	}
}
*/