public class kingCheck {
	static int kf;
	static int kr;
	
	static int file;
	static int rank;
	
	static boolean kingColour = true;  //true = white 	//false=black
	static boolean castle=false;
	
	public static void main(boolean kC, int line, boolean take) {
		kingColour = kC;
        de.bug("check_from_King>>", 0);
//        de.bug("kf:", kf);
//		de.bug("kr:", kr);
		if(kingColour==true){
			if((9>kf && kf>0) && (9>kr && kr>-1)){
				checkPawns("p", line, take);
				checkPawns("d", line, take);
			}
			checkRooks("r", take, line);
			findKnight("n", take, line);
//			checkKnights("n", take, line);
//			if((kr<7 && kr>2) && (kf<7 && kf>2)){
//				checkKnights("n", take, line);
//			}else{
//				Check.kf = kf;
//				Check.kr = kr;
//				Check.checkKnights(false, line);
//			}
			checkBishops("b", take, line);
			checkQueen("q", take, line);
			checkKing("k", take, line);
			checkAmazon("a", take, line);
			checkPrincess("w", take, line);
			checkDragon("f", take, line);
			if(kr>4) {
				checkElephant("e", take, line);
			}
		}else{
			if((9>kf && kf>0) && (10>kr && kr>0)){
				checkPawns("P", line, take);
				checkPawns("D", line, take);
			}
			checkRooks("R", take, line);
			findKnight("N", take, line);
//			checkKnights("N", take, line);
//			if((kr<7 && kr>2) && (kf<7 && kf>2)){
//				checkKnights("N", take, line);
//			}else{
//				Check.kf = kf;
//				Check.kr = kr;
//				Check.checkKnights(false, line);
//			}
			checkBishops("B", take, line);
			checkQueen("Q", take, line);
			checkKing("K", take, line);
			checkAmazon("A", take, line);
			checkPrincess("W", take, line);
			checkDragon("F", take, line);
			if(kr<5) {
				checkElephant("E", take, line);
			}
		}
		kr=0;
		kf=0;
		
	}
	
	
	//checkPawns DONE!!!!
	public static void checkPawns(String p, int line, boolean take){	
//		de.bug("KF", kf);
//		de.bug("KR", kr);
		if(p.matches(".*\\p{Lower}.*")){
			if(MoveValid.moveboard[kf+1][kr+1].equals(p) || MoveValid.moveboard[kf-1][kr+1].equals(p)){
				de.bug("pawn_check", 1);
				Error(line, take);
			}
		}else if(p.matches(".*\\p{Upper}.*")){
//			de.bug("kf+1:", kf+1);
//			de.bug("kf-1:", kf-1);
			if(MoveValid.moveboard[kf+1][kr-1].equals(p) || MoveValid.moveboard[kf-1][kr-1].equals(p)){
				de.bug("pawn_check", 2);
				Error(line, take);
			}
		}
		
	}
	//checkRooks done? >> MAKE OWN TEST CASES!!! 	>>double check on !="."
	static int fcount=0;
	public static void checkRooks(String p, boolean take, int line){
		flatU(p, take, line); // 1
		flatD(p, take, line); // 2
		flatR(p, take, line); // 3
		flatL(p, take, line); // 4
	}
/* 1*/	public static void flatU(String p, boolean take, int line){
			for(int i=kr+1; i<9; i++){
				if(!MoveValid.moveboard[kf][i].equals(".")){
					fcount++;
				}else if(fcount==0){
					if(MoveValid.moveboard[kf][i+1].equals(p)){
						de.bug("rook_check", 1);
						Error(line, take);
					}
				}
			}
			fcount=0;
		}
/* 2*/	public static void flatD(String p, boolean take, int line){
			for(int i=kr-1; i>0; i--){
				if(!MoveValid.moveboard[kf][i].equals(".")){
					fcount++;
				}else if(fcount==0){
					if(MoveValid.moveboard[kf][i-1].equals(p)){
						de.bug("rook_check", 2);
						Error(line, take);
					}
				}
			}
			fcount=0;
		}
/* 3*/	public static void flatR(String p, boolean take, int line){
			for(int i=kf+1; i<9; i++){
				if(!MoveValid.moveboard[i][kr].equals(".")){
					fcount++;
				}else if(fcount==0){
					if(MoveValid.moveboard[i+1][kr].equals(p)){
						de.bug("rook_take", 3);
						Error(line, take);
					}
				}
			}
			fcount=0;
		}
/* 4*/	public static void flatL(String p, boolean take, int line){
			for(int i=kf-1; i>0; i--){
				if(!MoveValid.moveboard[i][kr].equals(".")){
					fcount++;
				}else if(fcount==0){
					if(MoveValid.moveboard[i-1][kr].equals(p)){
						de.bug("rook_check", 4);
						Error(line, take);
					}
				}
			}
			fcount=0;
		}
	//checkKnights done? >> MAKE OWN TEST CASES!!!
	public static void checkKnights(String p, boolean take, int line){
		int kf2R = kf+2, kf2L = kf-2;
		int kf1R = kf+1, kf1L = kf-1;
		
		int kr2U = kr+2, kr2D = kr-2;
		int kr1U = kr+1, kr1D = kr-1;
		
//		if((kr2U<9 && kr2D>0) && (kf2R<9 && kf2L>0)){
		de.bug("kf_", kf);
		de.bug("kr_", kr);
		de.bug(kingColour+"_", 1);
		
		de.bug("file_", file);
		de.bug("rank_", rank);
		
		if(kf2R==file || kf2L==file){
			if(kr1U==rank || kr1D==rank){
				de.bug("invalid_knight", 1);
				Error(line, take);
			}
		}else if(kf1R==file || kf1L==file){
			if(kr2U==rank || kr2D==rank){
				de.bug("invalid_knight", 2);
				Error(line, take);
			}
		}
		
		file=0;
		rank=0;
//			if(MoveValid.moveboard[kf2R][kr1D].equals(p) || MoveValid.moveboard[kf2L][kr1D].equals(p)){
//				de.bug("invalid_knight", 1);
//				Error(line, take);
//			}
//			if(MoveValid.moveboard[kf2R][kr1U].equals(p) || MoveValid.moveboard[kf2L][kr1U].equals(p)){
//				de.bug("invalid_knight", 2);
//				Error(line, take);
//			}
//			if(MoveValid.moveboard[kf1R][kr2U].equals(p) || MoveValid.moveboard[kf1L][kr2U].equals(p)){
//				de.bug("invalid_knight", 3);
//				Error(line, take);
//			}
//			if(MoveValid.moveboard[kf1R][kr2D].equals(p) || MoveValid.moveboard[kf1L][kr2D].equals(p)){
//				de.bug("invalid_knight", 4);
//				Error(line, take);
//			}
//		}
		
		
		
		
		
		
		
//		try{
//			
//		}catch(Exception ex){
//			de.bug("exception_caught!", 1);
//		}
	}
	
	//checkBishops done? >> MAKE OWN TEST CASES!!! 	>>double check on !="."
	public static void checkBishops(String p, boolean check /*take*/, int line){
//		if(8>kf && kf>1){
//			if(8>kr && kr>1){
				diagUpR(p, check, line);
				diagDownR(p, check, line);
				diagUpL(p, check, line);
				diagDownL(p, check, line);
//			}
//		}
	}
/* 1*/	public static void diagUpR(String p, boolean take, int line){ //from king
			for(int i=kr+1; i<9; i++){
				for(int j=kf+1; j<9; j++){
					if(!MoveValid.moveboard[j][i].equals(".")){
						fcount++;
					}else if(fcount==0){
						int r = i+1;
						int f = j+1;
						
						int rdiff = r-kr;
						int fdiff = f-kf;
						if(MoveValid.moveboard[f][r].equals(p) && rdiff==fdiff){
							de.bug("bishop_check", 1);
							Error(line, take);
						}
					}
				}
			}
			fcount=0;
		}
/* 2*/	public static void diagDownR(String p, boolean take, int line){
			for(int i=kr-1; i>0; i--){
				for(int j=kf+1; j<9; j++){
					if(!MoveValid.moveboard[j][i].equals(".")){
						fcount++;
					}else if(fcount==0){
						int r = i-1;
						int f = j+1;
						
						int rdiff = kr-r;
						int fdiff = f-kf;
						if(MoveValid.moveboard[f][r].equals(p) && rdiff==fdiff){
							de.bug("bishop_check", 2);
							Error(line, take);
						}
					}
				}
			}
			fcount=0;
		}
/* 3*/	public static void diagUpL(String p, boolean take, int line){
			for(int i=kr+1; i<9; i++){
				for(int j=kf-1; j>0; j--){
					if(!MoveValid.moveboard[j][i].equals(".")){
						fcount++;
					}else if(fcount==0){
						int r = i+1;
						int f = j-1;
						
						int rdiff = r-kr;
						int fdiff = kf - f;
						
						if(MoveValid.moveboard[f][r].equals(p) && rdiff==fdiff){
							de.bug("bishop_check", 3);
							Error(line, take);
						}
					}
				}
			}
			fcount=0;
		}
/* 4*/	public static void diagDownL(String p, boolean take, int line){
			for(int i=kr-1; i>0; i--){
				for(int j=kf-1; j>0; j--){
					if(MoveValid.moveboard[j][i].equals(".")){
						fcount++;
					}else if(fcount==0){
						int r = i-1;
						int f = j-1;
						
						int rdiff = kr-r;
						int fdiff = kf - f;
						
						if(MoveValid.moveboard[f][r].equals(p) && rdiff==fdiff){
							de.bug("bishop_check", 4);
							Error(line, take);
						}
					}
				}
			}
			fcount=0;
		}
	//MAKE OWN TEST CASES!!!
	public static void checkQueen(String p, boolean take, int line) {
		de.bug("queen_check", 1);
		checkRooks(p, take, line);
		checkBishops(p, take, line);
	}

	public static void checkKing(String p, boolean take, int line){
		findKing(kingColour);
		if(file==kf+1 || file==kf-1 || file==kf){
			if(rank==kr+1 || rank==kr-1 || rank==kr){
				de.bug("no_two_kings!", 0);
				Error(line, take);
			}
		}
	}
	
//	MAKE OWN TEST CASES!!!
	public static void checkAmazon(String p, boolean take, int line) {
		de.bug("amazon_check", 1);
		checkRooks(p, take, line);
		findKnight(p, take, line);
//		if((kr<7 && kr>2) && (kf<7 && kf>2)){
//		checkKnights(p, take, line);
//		}else{
//			Check.kf = kf;
//			Check.kr = kr;
//			Check.checkKnights(false, line);
//		}
		checkBishops(p, take, line);
	}
//	MAKE OWN TEST CASES!!!
	public static void checkPrincess(String p, boolean take, int line) {
		de.bug("princess_check", 1);
		findKnight(p, take, line);
//		if((kr<7 && kr>2) && (kf<7 && kf>2)){
//		checkKnights(p, take, line);
//		}else{
//			Check.kf = kf;
//			Check.kr = kr;
//			Check.checkKnights(false, line);
//		}
		checkBishops(p, take, line);
	}
//	checkDragons done? >> MAKE OWN TEST CASES!!!
	public static void checkDragon(String p, boolean take, int line) {
		if(7>kf && kf>2){
			if(7>kr && kr>2){
				DiagUpR(p, take, line);
				DiagDownR(p, take, line);
				DiagUpL(p, take, line);
				DiagDownL(p, take, line);
			}
		}
	}
/*1*/	public static void DiagUpR(String p, boolean take, int line){	
			for(int i=kr+1; i<kr+3; i++){
				for(int j=kf+1; j<kf+3; j++){
					if(!MoveValid.moveboard[j][i].equals(".") && MoveValid.moveboard[j][i].equals(p)){
						de.bug("dragon_check", 1);
						Error(line, take);
					}
				}
			}
		}
/*2*/	public static void DiagDownR(String p, boolean take, int line){
			for(int i=kr+1; i<kr+3; i++){
				for(int j=kf-1; j>kf-3; j--){
					if(!MoveValid.moveboard[j][i].equals(".") && MoveValid.moveboard[j][i].equals(p)){
						de.bug("dragon_check", 2);
						Error(line, take);
					}
				}
			}
		}
/*3*/	public static void DiagUpL(String p, boolean take, int line){
			for(int i=kr+1; i<kr+3; i++){
				for(int j=kf-1; j>kf-3; j--){
					if(!MoveValid.moveboard[j][i].equals(".") && MoveValid.moveboard[j][i].equals(p)){
						de.bug("dragon_check", 3);
						Error(line, take);
					}
				}
			}
		}
/*4*/	public static void DiagDownL(String p, boolean take, int line){
			for(int i=kr-1; i>kr-3; i--){
				for(int j=kf-1; j>kf-3; j--){
					if(!MoveValid.moveboard[j][i].equals(".") && MoveValid.moveboard[j][i].equals(p)){
						de.bug("dragon_check", 4);
						Error(line, take);
					}
				}
			}
		}
//	MAKE OWN TEST CASES!!!
	public static void checkElephant(String p, boolean take, int line) {
		de.bug("elephant_check", 1);
		checkRooks(p, take, line);
	}

	public static void Error(int line, boolean take){
		
		if(take==false){
			MoveValidationErrors.illegalMove(line);
		}else{
			MoveValidationErrors.illegalCapture(line);
		}
		if(castle==true){
			MoveValidationErrors.illegalCastlingMove(line);
		}
	}

	public static void findKnight(String p, boolean take, int line){
		de.bug("findKnight!!! >>", 0);
		for(int i=0; i<10; i++){
			for(int j=9; j>-1; j--){
				if(MoveValid.moveboard[j][i].equals(p)){
					file = i;
					rank = j;
					checkKnights(p, take, line);
					break;
				}
			}
		}
	}

	public static void findKing(boolean play){
		for(int i=0; i<10; i++){
			for(int j=9; j>-1; j--){
				if(MoveValid.moveboard[i][j].equals("k") && play==true){
					file = i;
					rank = j;
				}else if(MoveValid.moveboard[i][j].equals("K")&& play==false){
					file = i;
					rank = j;
				}
			}
		}
	
	}
}