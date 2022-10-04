class de{
	static boolean debug = false;
	
	static void bug(String l, int k){
    	if (debug==true /*&& k!=0*/) {
    		System.out.print(l + k + " ");
    	}
    }
}