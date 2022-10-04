class King extends Piece{
	static int allocation;
	
	static int kf;
	static int kr;
	static boolean turn;
	
	static void movevalid(String Colour, int count, boolean take) {
		if(fileDiff==0 && rankDiff==0){
			de.bug("king_invlaid!", 1);
			MoveValidationErrors.illegalMove(count);
		}
		
		if(fileDiff==0 || rankDiff==0){
			horizontal(count, take);
		}else if(fileDiff!=0 && rankDiff!=0){
			diagonal(count, take);
		}
		kingCheck.kf = MoveValid.DSTfilecount;
		kingCheck.kr = MoveValid.DSTrankcount;
		de.bug("kf", kingCheck.kf);
		de.bug("kr", kingCheck.kr);
		if(Colour.equals("w")){
			kingCheck.main(true, count, take);
		}else{
			kingCheck.main(false, count, take);
		}
	}
	
	static void horizontal(int count, boolean take){
		
		if(fileDiff==0){ //left right
			if(rankDiff>0){
				if(MoveValid.DSTrankcount!=MoveValid.rankcount+1){
					de.bug("king_invlaid!", 2);
					if(take==false){
						MoveValidationErrors.illegalMove(count);
					}else{
						MoveValidationErrors.illegalCapture(count);
					}
				}
			}else{ //rankdiff < 0
				if(MoveValid.DSTrankcount!=MoveValid.rankcount-1){
					de.bug("king_invlaid!", 21);
					if(take==false){
						MoveValidationErrors.illegalMove(count);
					}else{
						MoveValidationErrors.illegalCapture(count);
					}
				}
			}
			
		}
		
		if(rankDiff==0){ //uip down
			if(fileDiff>0){
				if(MoveValid.DSTfilecount!=MoveValid.filecount+1){
					de.bug("king_invlaid!", 3);
					if(take==false){
						MoveValidationErrors.illegalMove(count);
					}else{
						MoveValidationErrors.illegalCapture(count);
					}
				}
			}else{ //filediff < 0
				if(MoveValid.DSTfilecount!=MoveValid.filecount-1){
					de.bug("king_invlaid!", 31);
					if(take==false){
						MoveValidationErrors.illegalMove(count);
					}else{
						MoveValidationErrors.illegalCapture(count);
					}
				}
			}
			
		}
	}

	static void diagonal(int count, boolean take){
		if(fileDiff>0){ 
			if(rankDiff>0){ //diagonal up right
				if((MoveValid.DSTrankcount!=MoveValid.rankcount+1) && (MoveValid.DSTfilecount!=MoveValid.filecount+1)){
					de.bug("king_invlaid!", 2);
					if(take==false){
						MoveValidationErrors.illegalMove(count);
					}else{
						MoveValidationErrors.illegalCapture(count);
					}
				}
			}else{ //diagonal down right
				if((MoveValid.DSTrankcount!=MoveValid.rankcount-1) && (MoveValid.DSTfilecount!=MoveValid.filecount+1)){
					de.bug("king_invlaid!", 21);
					if(take==false){
						MoveValidationErrors.illegalMove(count);
					}else{
						MoveValidationErrors.illegalCapture(count);
					}
				}
			}
			
		}else{
			if(rankDiff>0){ //diagonal up left
				if((MoveValid.DSTrankcount!=MoveValid.rankcount+1) && (MoveValid.DSTfilecount!=MoveValid.filecount-1)){
					de.bug("king_invlaid!", 3);
					if(take==false){
						MoveValidationErrors.illegalMove(count);
					}else{
						MoveValidationErrors.illegalCapture(count);
					}
				}
			}else{ //diagonal down left
				if((MoveValid.DSTrankcount!=MoveValid.rankcount-1) && (MoveValid.DSTfilecount!=MoveValid.filecount-1)){
					de.bug("king_invlaid!", 31);
					if(take==false){
						MoveValidationErrors.illegalMove(count);
					}else{
						MoveValidationErrors.illegalCapture(count);
					}
				}
			}
			
			
		}
		
	}
	
}