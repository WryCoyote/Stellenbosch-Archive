// REBUILD THE FUCKING CHECK CLASS!!!!!!!!!!!! DAMMIT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class Check {

	static String pce;
	static int file;
	static int rank;
	
	static int dr;
	static int df;
	
	static boolean turn;
	static int kf;
	static int kr;
	
	public static void main(boolean check, int line) {
		try{
			if(pce.matches(".*\\p{Upper}.*")){
				findKing(true);
			}else{
				findKing(false);
			}
		}catch(Exception ex){
			pce="k";
		}
		
		switch(pce){
		
		case "p": 
		case "P": 
			
		case "d":
		case "D": 
			if((rank!=0 && rank!=9) && (file!=0 && file!=9)){
				checkPawns(pce, check, line);
			}
			break;
			
		case "r":
		case "R": 
			checkRooks(check, line);
			break;
			
		case "n":
		case "N": 
			checkKnights(check, line);
			break;
			
		case "b":
		case "B": 
			if(file!=kf && rank!=kr){
				checkBishops(check, line, pce);
			}
			break;
			
		case "q":
		case "Q": 
			if(file==kf || rank==kr){
				de.bug("queen_rook", 1);
				checkRooks(check, line);
			}else if(file!=kf && rank!=kr){
				de.bug("queen_bishop", 1);
				checkBishops(check, line, pce);
			}
			break;
			
		case "a":
		case "A": 
			if(file==kf || rank==kr){
				checkRooks(check, line);
			}else if(file!=kf && rank!=kr){
				checkBishops(check, line, pce);
			}else{
				checkKnights(check, line);
			}
			break;
			
		case "w":
		case "W": 
			if(file!=kf && rank!=kr){
				checkBishops(check, line, pce);
			}else{
				checkKnights(check, line);
			}
			break;
			
		case "f":
		case "F": 
			if(file<kf+3 || file>kf-3){
				if(rank<kr+3 || rank>kr-3){ //test again!!!
					checkBishops(check, line, pce);
				}
			}
//			checkDragons(pce, check, line);
			break;
			
		case "e":
		case "E": 
			if(check==true && kr>5){
				de.bug("bad_elephant_check", 1);
				MoveValidationErrors.illegalCheck(line);
			}else if(check==true && kr<6){
				de.bug("bad_elephant_check", 2);
				MoveValidationErrors.illegalCheck(line);
			}
			checkRooks(check, line);
			break;
		}
	}
	

	public static void checkPawns(String p, boolean check, int line){
		if(p.matches(".*\\p{Upper}.*")){
			if(MoveValid.moveboard[file+1][rank+1].equals("k") || MoveValid.moveboard[file-1][rank+1].equals("k")){
				if(check==false){
					de.bug("invalid_check_pawn", 1);
					Error(line);
				}
			}
		}else{
			if(MoveValid.moveboard[file+1][rank-1].equals("K") || MoveValid.moveboard[file-1][rank-1].equals("K")){
				if(check==false){
					de.bug("invalid_check_pawn", 2);
					Error(line);
				}
			}
		}
		
		file=0;
		rank=0;
	}

	public static void checkRooks(boolean check, int line){
		if(file==kf){
			
			if(rank>kr){
				flatU(check, line); // 1
			}else if(kr>rank){
				flatD(check, line); // 2
			}
			
		}else if(rank==kr){
			if(file>kf){
				flatR(check, line); // 3
			}else if(kf>file){
				flatL(check, line); // 4
			}
			
		}else{
			if(check==true){
				de.bug("rook_file_", file);
				de.bug("rook_rank_", rank);
				
				de.bug("king_file_", kf);
				de.bug("king_rank_", kr);
				
				de.bug("rook_check", 5);
				Error(line);
			}
		}
		file=0;
		rank=0;
	}
/* 1*/	public static void flatU(boolean check, int line){
			for(int i=kr+1; i<rank; i++){
				if(!MoveValid.moveboard[file][i].equals(".")){
					if(check==true){
						de.bug("rook_check", 1);
						Error(line);
					}
				}else{
					if(check==false){
						de.bug("rook_check", 11);
						Error(line);
					}
				}
			}
		}
/* 2*/	public static void flatD(boolean check, int line){
			for(int i=rank+1; i<kr; i++){
				if(!MoveValid.moveboard[file][i].equals(".")){
					if(check==true){
						de.bug("rook_check", 2);
						Error(line);
					}
				}else{
					if(check==false){
						de.bug("rook_check", 21);
						Error(line);
					}
				}
			}
		}
/* 3*/	public static void flatR(boolean check, int line){
			for(int i=kf+1; i<file; i++){
				if(!MoveValid.moveboard[i][rank].equals(".")){
					if(check==true){
						de.bug("rook_check", 3);
						Error(line);
					}
				}else{
					if(check==false){
						de.bug("rook_check", 31);
						Error(line);
					}
				}
			}
		}
/* 4*/	public static void flatL(boolean check, int line){
			for(int i=file+1; i<kf; i++){
				if(!MoveValid.moveboard[i][rank].equals(".")){
					if(check==true){
						de.bug("rook_check", 4);
						Error(line);
					}
				}else{
					if(check==false){
						de.bug("rook_check", 41);
						Error(line);
					}
				}
			}
		}
		
	
	public static void checkKnights(boolean check, int line){
		de.bug("KnightFile:", file);
		de.bug("KnightRank:", rank);
		
		de.bug("kf:", kf);
		de.bug("kr:", kr);
		
		if(file==kf+2 || file==kf-2){
			if(rank==kr+1 || rank==kr-1){
				if(check==false){
					de.bug("invalid_knight-", 1);
					Error(line);
				}
			}else{
				if(check==true){
					de.bug("invalid_knight-", 2);
					Error(line);
				}
			}
		}else if(rank==kr+2 || rank==kr-2){
			if(file==kf+1 || file==kf-1){
				if(check==false){
					de.bug("invalid_knight-", 3);
					Error(line);
				}
			}else{
				if(check==true){
					de.bug("invalid_knight-", 4);
					Error(line);
				}
				
			}
		
		}else{
			if(check==true){
				de.bug("invalid_knight-", 5);
				Error(line);
			}
			
		}
		file=0;
		rank=0;
	}
	
	public static void checkBishops(boolean check, int line, String p){
		String king;
		if(p.equals("b")){
			king="K";
		}else{
			king="k";
		}
		de.bug("kf:>", kf);
		de.bug("kr:>", kr);
		de.bug("bf:>", file);
		de.bug("br:>", rank);
		if(file>kf){
			if(rank>kr){
				diagDownL(check, line, king);
				
			}else if(kr>rank){
				diagUpL(check, line, king);
				
			}else{
				if(check==true/* && pce.equalsIgnoreCase("b")*/){
					de.bug("bishop_check", 3);
					Error(line);
				}
			}
			
		}else if(kf>file){
			
			if(rank<kr){
				diagDownR(check, line, king);
				
			}else if(kr<rank){
				diagUpR(check, line, king);
				
			}else{
				if(check==true/* && pce.equalsIgnoreCase("b")*/){
					de.bug("bishop_check", 6);
					Error(line);
				}
			}
			
		}else{
			if(check==true /*&& pce.equalsIgnoreCase("b")*/){
				de.bug("bishop_check", 7);
				Error(line);
			}
		}
		file=0;
		rank=0;
	}
	static int Fcount;	//from bishop
	
/* 5*/	public static void diagDownL(boolean check, int line, String king){  // file>kf; rank>kr
			for(int i=rank-1; i>kr; i--){
				for(int j=file-1; j>kf; j--){
					if(!MoveValid.moveboard[j][i].equals(".")){
						Fcount++;
						if(check==true){
							de.bug("bishop_check", 5);
							Error(line);
						}
					}else if(MoveValid.moveboard[j-1][i-1].equals(king)){
						if(check==false && Fcount==0){
							de.bug("bishop_check", 51);
							Error(line);
						}
					}
				}
			}
			
			Fcount=0;
		}
/* 4*/	public static void diagUpL(boolean check, int line, String king){	// file>kf; rank<kr
			for(int i=rank+1; i<kr; i++){
				for(int j=file-1; j>kf; j--){
					if(!MoveValid.moveboard[j][i].equals(".")){
						Fcount++;
						if(check==true){
							de.bug("bishop_check", 4);
							Error(line);
						}
					}else if(MoveValid.moveboard[j-1][i+1].equals(king)){
						if(check==false && Fcount==0){
							de.bug("bishop_check", 41);
							Error(line);
						}
					}
				}
			}
			
			Fcount=0;
		}
/* 2*/	public static void diagDownR(boolean check, int line, String king){	// file<kf; rank>kr
			for(int i=rank-1; i>kr; i--){
				for(int j=file+1; j<kf; j++){
					if(!MoveValid.moveboard[j][i].equals(".")){
						Fcount++;
						if(check==true){
							de.bug("bishop_check", 2);
							Error(line);
						}
					}else if(MoveValid.moveboard[j+1][i-1].equals(king)){
						if(check==false && Fcount==0){
							de.bug("bishop_check", 21);
							Error(line);
						}
					}
				}
			}
			
			Fcount=0;
		}
/* 1*/	public static void diagUpR(boolean check, int line, String king){ 	// file<kf; rank<kr
			for(int i=rank+1; i<kr; i++){
				for(int j=file+1; j<kf; j++){
					if(!MoveValid.moveboard[j][i].equals(".")){
						Fcount++;
						if(check==true){
							de.bug("bishop_check", 1);
							Error(line);
						}
					}else if(MoveValid.moveboard[j+1][i+1].equals(king)){
						if(check==false && Fcount==0){
							de.bug("bishop_check", 11);
							Error(line);
						}
					}
				}
			}
			
			Fcount=0;
		}


	public static void findKing(boolean play){
		for(int i=0; i<10; i++){
			for(int j=9; j>-1; j--){
				if(MoveValid.moveboard[i][j].equals("k") && play==true){
					kf = i;
					kr = j;
					turn = false;
				}else if(MoveValid.moveboard[i][j].equals("K")&& play==false){
					kf = i;
					kr = j;
					turn = true;
				}
			}
		}
	
	}
	

	public static void Error(int line){
		MoveValidationErrors.illegalCheck(line);
	}
}

/*
public static void checkDragons(String p, boolean check, int line){
	if(p.matches(".*\\p{Upper}.*")){
		findKing(true);
	}else{
		findKing(false);
	}
	
	if(file==kr+2){
		if(rank==kr+2){
			for(int i=kr+1; i<rank; i++){
				for(int j=kf+1; j<file; j++){
					if(!MoveValid.moveboard[j][i].equals(".")){
						if(check==false){
							MoveValidationErrors.illegalCheck(line);
						}
					}else{
						if(check==false){
							MoveValidationErrors.illegalCheck(line);
						}
					}
				}
			}
		}else if(rank==kf-2){
			for(int i=rank+1; i<kr; i++){
				for(int j=kf+1; j<file; j++){
					if(!MoveValid.moveboard[j][i].equals(".")){
						if(check==false){
							MoveValidationErrors.illegalCheck(line);
						}
					}else{
						if(check==false){
							MoveValidationErrors.illegalCheck(line);
						}
					}
				}
			}
		}else{
			MoveValidationErrors.illegalCheck(line);
		}
		
	}else if(file==kf-2){
		
		if(rank==kr+2){
			for(int i=kr+1; i<rank; i++){
				for(int j=file+1; j<kf; j++){
					if(!MoveValid.moveboard[j][i].equals(".")){
						if(check==false){
							MoveValidationErrors.illegalCheck(line);
						}
					}else{
						if(check==false){
							MoveValidationErrors.illegalCheck(line);
						}
					}
				}
			}
		}else if(rank==kr-2){
			for(int i=rank+1; i<kr; i++){
				for(int j=file+1; j<kf; j++){
					if(!MoveValid.moveboard[j][i].equals(".")){
						if(check==false){
							MoveValidationErrors.illegalCheck(line);
						}
					}else{
						if(check==false){
							MoveValidationErrors.illegalCheck(line);
						}
					}
				}
			}
		}else{
			MoveValidationErrors.illegalCheck(line);
		}
		
	}else{
		if(check==true){
			MoveValidationErrors.illegalCheck(line);
		}
	}
}
*/