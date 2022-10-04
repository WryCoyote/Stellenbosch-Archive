class Amazon extends Piece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		Queen.movevalid(Colour, count, take);
		Knight.movevalid(Colour, count, take);
		
		if(Colour.equals("w")){
			Check.pce = "A";
		}else{
			Check.pce = "a";
		}
	}
}