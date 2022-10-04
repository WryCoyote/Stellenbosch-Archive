
public class ChaosGameTri {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(args[0]);//StdIn.readInt();
//		StdDraw.setPenRadius(0.0000000000000000000000000000001);
//		StdDraw.setCanvasSize(250, 250);
		double[] cx = { 0.000, 1.000, 0.500 };
		double[] cy = { 0.000, 0.000, 0.866 };
		double x = 0.0, y = 0.0;
		for (int t = 0; t <= T; t++) {
			int r = (int) (Math.random() * 3);
			x = (x + cx[r]) / 2.0;
			y = (y + cy[r]) / 2.0;
			if((x<0.500 && x>=0.000) && (y>=0.000 && y<0.433)) {
				StdDraw.setPenColor(StdDraw.RED);
			}else if((x>0.500 && x<=1.000) && (y>=0.000 && y<0.433)) {
				StdDraw.setPenColor(StdDraw.GREEN);
			}else {
				StdDraw.setPenColor(StdDraw.BLUE);
			}
			StdDraw.point(x, y);
			for(int k=0; k<T; k++) {
				if(t==(k*1000)) {
					System.out.println(t);
				}
			}
			if(t==T) {
				System.out.println(T + " permutations run successfully!");
			}
		}
	}

}
