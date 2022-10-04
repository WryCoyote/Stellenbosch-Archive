class FDragon extends Piece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		if((MoveValid.DSTfilecount>MoveValid.filecount+2 || MoveValid.DSTfilecount<MoveValid.filecount-2) && (MoveValid.DSTrankcount>MoveValid.rankcount+2 || MoveValid.DSTrankcount<MoveValid.rankcount-2)){
			//errors
			if(take==true){
				MoveValidationErrors.illegalCapture(count);
			}else{
				MoveValidationErrors.illegalMove(count);
			}
		}else{
			Bishop.movevalid(Colour, count, take);
		}
		if(Colour.equals("w")){
			Check.pce = "F";
		}else{
			Check.pce = "f";
		}
	}
}