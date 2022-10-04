class Queen extends Piece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		if(fileDiff==0 && rankDiff==0){
			de.bug("queen_invlaid!", 1);
			MoveValidationErrors.illegalMove(count);
		}
		if(fileDiff==0 || rankDiff==0){
			Rook.movevalid(Colour, count, take);
//			horizontal(count, take);
		}else if(fileDiff!=0 && rankDiff!=0){
			Bishop.DSTfile = MoveValid.DSTfilecount;
			Bishop.SRCfile = MoveValid.filecount;
			
			Bishop.DSTrank = MoveValid.DSTrankcount;
			Bishop.SRCrank = MoveValid.rankcount;
			Bishop.movevalid(Colour, count, take);
//			diagonal(count, take);
		}
		
		if(Colour.equals("w")){
			Check.pce = "Q";
		}else{
			Check.pce = "q";
		}
	}
	
	static void horizontal(int count, boolean take){
		if(take==false){
			if(fileDiff==0){
				if(rankDiff>0){
					for(int i=1; i<rankDiff+1; i++){
						if(!MoveValid.moveboard[MoveValid.filecount][MoveValid.rankcount+i].equals(".")){
							de.bug("queen_invlaid!", 2);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}else{
					for(int i=1; i<rankDiffABS+1; i++){
						if(!MoveValid.moveboard[MoveValid.filecount][MoveValid.rankcount-i].equals(".")){
							de.bug("queen_invlaid!", 21);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}
			}
			if(rankDiff==0){
				if(fileDiff>0){
					for(int i=1; i<fileDiff+1; i++){
						if(!MoveValid.moveboard[MoveValid.filecount+i][MoveValid.rankcount].equals(".")){
							de.bug("queen_invlaid!", 3);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}else{
					for(int i=1; i<fileDiffABS+1; i++){
						if(!MoveValid.moveboard[MoveValid.filecount-i][MoveValid.rankcount].equals(".")){
							de.bug("queen_invlaid!", 31);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}
			}
		}else{
			if(fileDiff==0){
				if(rankDiff>0){
					for(int i=1; i<rankDiff; i++){
						if(!MoveValid.moveboard[MoveValid.filecount][MoveValid.rankcount+i].equals(".")){
							de.bug("queen_invlaid!", 4);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}else{
					for(int i=1; i<rankDiffABS; i++){
						if(!MoveValid.moveboard[MoveValid.filecount][MoveValid.rankcount-i].equals(".")){
							de.bug("queen_invlaid!", 41);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}
			}
			if(rankDiff==0){
				if(fileDiff>0){
					for(int i=1; i<fileDiff; i++){
						if(!MoveValid.moveboard[MoveValid.filecount+i][MoveValid.rankcount].equals(".")){
							de.bug("queen_invlaid!", 5);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}else{
					for(int i=1; i<fileDiffABS; i++){
						if(!MoveValid.moveboard[MoveValid.filecount-i][MoveValid.rankcount].equals(".")){
							de.bug("queen_invlaid!", 51);
							MoveValidationErrors.illegalMove(count);
						}
					}
				}
			}
		}
	}
	
	
	static void diagonal(int count, boolean take){
		if(take==false){
			if(fileDiff>0){
				if(rankDiff>0){
					for(int i=1; i<rankDiff+1; i++){
						for(int k=1; k<fileDiff+1; k++){
							if(!MoveValid.moveboard[MoveValid.filecount+k][MoveValid.rankcount+i].equals(".")){
								de.bug("queen_invlaid!", 2);
								MoveValidationErrors.illegalMove(count);
							}
						}
					}
				}else{
					for(int i=1; i<rankDiffABS+1; i++){
						for(int k=1; k<fileDiff+1; k++){
							if(!MoveValid.moveboard[MoveValid.filecount+k][MoveValid.rankcount-i].equals(".")){
								de.bug("queen_invlaid!", 21);
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
								de.bug("queen_invlaid!", 3);
								MoveValidationErrors.illegalMove(count);
							}
						}
					}
				}else{
					for(int i=1; i<rankDiffABS+1; i++){
						for(int k=1; k<fileDiffABS+1; k++){
							if(!MoveValid.moveboard[MoveValid.filecount-k][MoveValid.rankcount-i].equals(".")){
								de.bug("queen_invlaid!", 31);
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
								de.bug("queen_invlaid!", 4);
								MoveValidationErrors.illegalMove(count);
							}
						}
					}
				}else{
					for(int i=1; i<rankDiffABS; i++){
						for(int k=1; k<fileDiff; k++){
							if(!MoveValid.moveboard[MoveValid.filecount+k][MoveValid.rankcount-i].equals(".")){
								de.bug("queen_invlaid!", 41);
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
								de.bug("queen_invlaid!", 5);
								MoveValidationErrors.illegalMove(count);
							}
						}
					}
				}else{
					for(int i=1; i<rankDiffABS; i++){
						for(int k=1; k<fileDiffABS; k++){
							if(!MoveValid.moveboard[MoveValid.filecount-k][MoveValid.rankcount-i].equals(".")){
								de.bug("queen_invlaid!", 51);
								MoveValidationErrors.illegalMove(count);
							}
						}
					}
				}
			}
		}
	}


}
		