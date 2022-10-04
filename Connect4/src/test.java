/*
		public static int [][] guiBomb (int i, int [][] guigrid, boolean player1) {
	    	//i column
    	//j row
    	for(int j=5; j>=0; j--) {
    		if(grid[j][i]==0) {
	    		if(j==5) {
	    			if(i==0) {
	    				Play(i, grid, player1);
		    			grid[j][i+1]=grid[j-2][i+1];
		    			grid[j-1][i+1]=grid[j-3][i+1];
		    			grid[j-2][i+1]=grid[j-4][i+1];
		    			grid[j-3][i+1]=grid[j-5][i+1];
		    			
		    			grid[j-4][i+1]=0;
		    			grid[j-5][i+1]=0;
	    			}else if(i==6) {
	    				Play(i, grid, player1);
		    			grid[j][i-1]=grid[j-2][i-1];
		    			grid[j-1][i-1]=grid[j-3][i-1];
		    			grid[j-2][i-1]=grid[j-4][i-1];
		    			grid[j-3][i-1]=grid[j-5][i-1];
		    			
		    			grid[j-4][i-1]=0;
		    			grid[j-5][i-1]=0;
	    			}else {
	    				
	    			Play(i, grid, player1);
	    			grid[j][i+1]=grid[j-2][i+1];
	    			grid[j][i-1]=grid[j-2][i-1];
	    			grid[j-1][i+1]=grid[j-3][i+1];
	    			grid[j-1][i-1]=grid[j-3][i-1];
	    			grid[j-2][i+1]=grid[j-4][i+1];
	    			grid[j-2][i-1]=grid[j-4][i-1];
	    			grid[j-3][i+1]=grid[j-5][i+1];
	    			grid[j-3][i-1]=grid[j-5][i-1];
	    			
	    			grid[j-4][i+1]=0;
	    			grid[j-4][i-1]=0;
	    			grid[j-5][i+1]=0;
	    			grid[j-5][i-1]=0;

	    			}
	    			
	    			break;
	    		}else if(j+1<=5 && j-4>=0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j][i+1]=grid[j-3][i+1];
		    			grid[j-1][i+1]=grid[j-4][i+1];
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-3][i+1]=0;
		    			grid[j-4][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			grid[j][i-1]=grid[j-3][i-1];
		    			grid[j-1][i-1]=grid[j-4][i-1];
		    			
		    			grid[j-2][i-1]=0;
		    			grid[j-3][i-1]=0;
		    			grid[j-4][i-1]=0;
	    			}else {
		    			grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			grid[j][i+1]=grid[j-3][i+1];
		    			grid[j][i-1]=grid[j-3][i-1];
		    			grid[j-1][i+1]=grid[j-4][i+1];
		    			grid[j-1][i-1]=grid[j-4][i-1];
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-2][i-1]=0;
		    			grid[j-3][i+1]=0;
		    			grid[j-3][i-1]=0;
		    			grid[j-4][i+1]=0;
		    			grid[j-4][i-1]=0;

	    			}
	    			
	    			break;
	    		}else if(j-4>0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j][i+1]=grid[j-3][i+1];
		    			
		    			grid[j-1][i+1]=0;
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-3][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			grid[j][i-1]=grid[j-3][i-1];
		    			
		    			grid[j-1][i-1]=0;
		    			
		    			grid[j-2][i-1]=0;
		    			grid[j-3][i-1]=0;
		    			
	    			}else {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			grid[j][i+1]=grid[j-3][i+1];
		    			grid[j][i-1]=grid[j-3][i-1];
		    			
		    			grid[j-1][i+1]=0;
		    			grid[j-1][i-1]=0;
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-2][i-1]=0;
		    			grid[j-3][i+1]=0;
		    			grid[j-3][i-1]=0;
		    			
	    			}
	    			
	    			break;
	    		}else if(j-3>0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			
		    			grid[j][i+1]=0;
		    			grid[j-1][i+1]=0;
		    			
		    			grid[j-2][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			
		    			grid[j][i-1]=0;
		    			grid[j-1][i-1]=0;
		    			
		    			grid[j-2][i-1]=0;
		    			
	    			}else {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=grid[j-2][i+1];
		    			grid[j+1][i-1]=grid[j-2][i-1];
		    			
		    			grid[j][i+1]=0;
		    			grid[j][i-1]=0;
		    			grid[j-1][i+1]=0;
		    			grid[j-1][i-1]=0;
		    			
		    			grid[j-2][i+1]=0;
		    			grid[j-2][i-1]=0;
		    			
	    			}
	    			
	    			break;
	    		}else if(j-2>0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=0;
		    			grid[j][i+1]=0;
		    			grid[j-1][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=0;
		    			grid[j][i-1]=0;
		    			grid[j-1][i-1]=0;
		    			
	    			}else {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=0;
		    			grid[j+1][i-1]=0;
		    			grid[j][i+1]=0;
		    			grid[j][i-1]=0;
		    			grid[j-1][i+1]=0;
		    			grid[j-1][i-1]=0;
		    			
	    			}
	    			
	    			break;
	    		}else if(j==0) {
	    			if(i==0) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=0;
		    			grid[j][i+1]=0;
		    			
	    			}else if(i==6) {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i-1]=0;
		    			grid[j][i-1]=0;
		    			
	    			}else {
	    				grid[j+1][i]=0;
		    			Play(i, grid, player1);
		    			grid[j+1][i+1]=0;
		    			grid[j+1][i-1]=0;
		    			grid[j][i+1]=0;
		    			grid[j][i-1]=0;
		    			
	    			}
	    			
	    			break;
	    		}
	    		
	    		
	    	}else if(grid[0][i]!=0) {
	    		System.out.println("Column is full.");
    			break;
	    	}
    	}
	    	
    	}
	    	*/
