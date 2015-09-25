import java.util.Arrays;


public class Fast {
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
	private static void output(Point[] a, int count, int i, int j){
		Point[] b = new Point[count+1];
		System.arraycopy( a,i, b, 0, 1);
		System.arraycopy( a,j, b, 1, count);
		Arrays.sort(b);
		b[0].drawTo(b[count]);
    	StdDraw.show(0);
    	for (int now = 0; now <count; now++){
    		System.out.print(b[now].toString() + " -> ");
    	}
    	System.out.println(b[count].toString());
	}
	private static boolean past(Point[] a, double slope, int index){
		for (int i = 0; i<index; i++){
			if (a[index].slopeTo(a[i]) == slope)return false;
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String filename = args[0];
        In in = new In(filename);
        
        int N = in.readInt();
        Point[] a = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            a[i] = p;
        }
        drawp(filename,a);
        for (int i = 0; i<N-3; i++){
        	Arrays.sort(a,i+1,N,a[i].SLOPE_ORDER);
        	double slope1 = Double.NEGATIVE_INFINITY;
        	int count = 1;
        	for (int j = i+1; j<N; j++){
        		double slopenow =  a[i].slopeTo(a[j]);
        		if (slope1 != slopenow){
        			if ((count >=3)&& past(a, slope1, i)){
        				output( a, count,  i, j-count);
        			}
        			slope1 = slopenow;
        			count = 1;
        		}else count++;
        	}
			if (count >=3){
				output( a, count,  i, N-count);

			}
        }

	}
}
