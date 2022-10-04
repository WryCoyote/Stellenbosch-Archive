class de{
	static boolean debug = true;
	
	static void bug(String l, int k){
    	if (debug /*&& k!=0*/) {
    		System.out.print(l + k + " ");
    	}
    }
}