class Princess extends Piece{
	static int allocation;
	
	static void movevalid(String Colour, int count, boolean take) {
		Bishop.movevalid(Colour, count, take);
		Knight.movevalid(Colour, count, take);
		
		if(Colour.equals("w")){
			Check.pce = "W";
		}else{
			Check.pce = "w";
		}
	}
}