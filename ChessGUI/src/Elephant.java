class Elephant extends Piece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		if(Colour.equals("w") && MoveValid.DSTrankcount<5){
			Rook.movevalid(Colour, count, take);
			Check.pce = "E";
		}else if(Colour.equals("b") && MoveValid.DSTrankcount>4){
			Rook.movevalid(Colour, count, take);
			Check.pce = "e";
		}else{
			if(take==false){
				MoveValidationErrors.illegalMove(count);
			}else{
				MoveValidationErrors.illegalCapture(count);
			}
		}
	}
}