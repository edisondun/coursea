import java.util.Arrays;


public class Brute {
   
	 private static void drawp(String filename,Point[] a) {

	        // rescale coordinates and turn on animation mode
	        StdDraw.setXscale(0, 32768);
	        StdDraw.setYscale(0, 32768);
	        StdDraw.show(0);
	        StdDraw.setPenRadius(0.01);  // make the points a bit larger


	        for (Point s: a){
	        	s.draw();
	        }

	        // display to screen all at once
	        StdDraw.show(0);

	        // reset the pen radius
	        StdDraw.setPenRadius();
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String filename = args[0];
        In in = new In(filename);
        
        int N = in.readInt();
        Point[] b = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            b[i] = p;
        }
        drawp(filename,b);
        for (int i = 0; i < N; i++) {
            Point p = b[i];
            for (int j = i+1; j < N; j++){
                Point q = b[j];
                double slope1 = p.slopeTo(q);
                for (int k = j+1; k < N; k++){
                    Point r = b[k];
                    double slope2 = p.slopeTo(r);
                    if (slope1 == slope2){
                        for (int m = k+1; m < N; m++){
                            Point s =b[m];
                            double slope3 = p.slopeTo(s);
                            if (slope1 == slope3){
                            	Point[] a = {p,q,r,s};
                            	Arrays.sort(a);
                            	for (int now = 0; now <3; now++){
                            		System.out.print(a[now].toString() + " -> ");
                            	}
                            	System.out.println(a[3].toString());
                            	a[0].drawTo(a[3]);
                            	StdDraw.show(0);
                            }
                    }
                }
            }
        }
	}

}
}
