
public class GuiMoveBoard {
	
	static String SRC;
	static String DST;
	
	static int SRCfile;
	static int SRCrank;
	static int DSTfile;
	static int DSTrank;
	static String Colour;
	static boolean player;
	
	static String move;
	
	static String [] arcfile = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
	static String indi="", promo="", upgrade="", check="";
	
	public static void move(double x, double y){
		int file = (int) Math.round(Math.abs(((x-20)/60)-1));
		int rank = (int) Math.round(Math.abs(((y-20)/60)));
		
		if((file<10 && file>-1) && (rank<11 && rank>0)){

			if(FairyChess.click==1){
				SRC = MoveValid.moveboard[file][rank-1];
				if(SRC.matches(".*\\p{Upper}.*") && MoveValid.p==false) {
					System.out.println("Wrong colour!");
					FairyChess.click=0;
				}else if(SRC.matches(".*\\p{Lower}.*") && MoveValid.p==true) {
					System.out.println("Wrong colour!");
					FairyChess.click=0;
				}
				
				if(!SRC.equals(".")) {
					SRCfile = file;
					SRCrank = rank;
				}else {
					FairyChess.click = 0;
				}
				
				
//				StdDraw.setPenColor(0, 128, 0);
//				StdDraw.setPenRadius(0.004);
//				StdDraw.circle(((file+1)*60)+20, (rank*60)+20, 21);
//				while(!StdDraw.isMousePressed() && FairyChess.click<2){
//					StdDraw.show();
//				}
				
				System.out.println("SRC:" + SRC + " " + arcfile[SRCfile] + SRCrank);
				
			}else if(FairyChess.click==2){
				
				DST = MoveValid.moveboard[file][rank-1];
				DSTfile = file;
				DSTrank = rank;

//				System.out.println("DST:" + DST);
				
				
				if(DST.equals(".")){

					if(SRC.equals("k") && (DSTfile == 2 && SRCrank==10) && (SRCfile==5)){
						move = "0-0-0";
					}else if(SRC.equals("k") && (DSTfile == 8 && SRCrank==10) && (SRCfile==5)){
						move = "0-0";
					}else if(SRC.equals("K") && (DSTfile == 2 && SRCrank==1) && (SRCfile==5)){
						move = "0-0-0";	
					}else if(SRC.equals("K") && (DSTfile == 8 && SRCrank==1) && (SRCfile==5)){
						move = "0-0";
					}else{
						
						
						if(DSTrank==1 && (SRC.equals("p") || SRC.equals("d"))){
							promo = "=";
							System.out.println("Promotion: Insert letter of piece you wish to promote to: e.g 'q' (Ignore case)");
							System.out.println("Choose from allocated pieces: ");
							for(int i = 0; i<MoveValid.AllocOfficers.length(); i++){
								System.out.print(MoveValid.AllocOfficers.charAt(i) + " ");
							}
							upgrade = StdIn.readString();
							upgrade = upgrade.toLowerCase();
						}
						if(DSTrank==10 && (SRC.equals("P") || SRC.equals("D"))){
							promo = "=";
							System.out.println("Promotion: Insert letter of piece you wish to promote to: e.g 'q' (Ignore case)");
							System.out.print("Choose from allocated pieces: ");
							for(int i = 0; i<MoveValid.AllocOfficers.length(); i++){
								System.out.print(MoveValid.AllocOfficers.charAt(i) + " ");
							}
							System.out.println();
							upgrade = StdIn.readString();
							upgrade = upgrade.toUpperCase();
						}

						Check.file = DSTfile;
						Check.rank = DSTrank-1;
						try{
							Check.main(false, 1);
						}catch(Exception ex){
							System.out.println("Caught!!!1");
						}
						
						move = arcfile[SRCfile] + SRCrank + "-" + arcfile[DSTfile] + DSTrank + promo + upgrade + check;
					}
				}else if(!DST.equals(SRC)){
					
					if(DSTrank==1 && (SRC.equals("p") || SRC.equals("d"))){
						promo = "=";
						System.out.println("Promotion: Insert letter of piece you wish to promote to: e.g 'q' (Ignore case)");
						System.out.print("Choose from allocated pieces: ");
						for(int i = 0; i<MoveValid.AllocOfficers.length(); i++){
							System.out.print(MoveValid.AllocOfficers.charAt(i) + " ");
						}
						System.out.println();
						upgrade = StdIn.readString();
						upgrade = upgrade.toLowerCase();
					}
					if(DSTrank==10 && (SRC.equals("P") || SRC.equals("D"))){
						promo = "=";
						System.out.println("Promotion: Insert letter of piece you wish to promote to: e.g 'q' (Ignore case)");
						System.out.print("Choose from allocated pieces: ");
						for(int i = 0; i<MoveValid.AllocOfficers.length(); i++){
							System.out.print(MoveValid.AllocOfficers.charAt(i) + " ");
						}
						System.out.println();
						upgrade = StdIn.readString();
						upgrade = upgrade.toUpperCase();
					}
					
					Check.file = DSTfile;
					Check.rank = DSTrank-1;
					try{
						Check.main(false, 1);
					}catch(Exception ex){
						System.out.println("Caught!!!2");
					}
					move = arcfile[SRCfile] + SRCrank + "x" + arcfile[DSTfile] + DSTrank + promo + upgrade + check;
				}else{
					move = "*";
				}
				
				MoveValid.veritas(move);
				
				FairyChess.click=0;
				promo = "";
				upgrade = "";
				check = "";
				
			}
			StdDraw.pause(75);
		}
	}
}
