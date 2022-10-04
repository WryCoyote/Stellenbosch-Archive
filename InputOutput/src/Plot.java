
public class Plot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double N = StdIn.readDouble();
		double[] x = new double[(int) (N+1)];
		double[] y = new double[(int) (N+1)];
		for (int i = 0; i <= N; i++) {
			x[i] = Math.PI * i / N;
			y[i] = Math.sin(4*x[i]) + Math.sin(20*x[i]);
		}
		StdDraw.setXscale(0, Math.PI);
		StdDraw.setYscale(-2.0, +2.0);
		for (int i = 0; i < N; i++)
			StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
	}

}
