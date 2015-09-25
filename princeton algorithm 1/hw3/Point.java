/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new slopeorder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;

    }
    
    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	int numer = that.y - this.y;
    	int denom = that.x - this.x;
    	if (this.x == that.x && this.y == that.y)return Double.NEGATIVE_INFINITY;
    	else if (numer == 0) {return 0.0;}
    	else if (denom == 0) return Double.POSITIVE_INFINITY;
    	else {return (numer*1.0/denom*1.0);}
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if (this.y < that.y)return -1;
    	else if (this.y== that.y && this.x < that.x)return -1;
    	else if (this.y== that.y && this.x == that.x)return 0;
    	else return 1;
    }
    private class slopeorder implements Comparator<Point>{
    	
    	public int compare(Point a, Point b){
    		double slope1 = slopeTo(a);
    		double slope2 = slopeTo(b);
    		if (slope1 == Double.POSITIVE_INFINITY && slope2 == Double.POSITIVE_INFINITY) return 0;
    		else if (slope1 == Double.NEGATIVE_INFINITY && slope2 == Double.NEGATIVE_INFINITY) return 0;
    		else if (slope1 < slope2) return -1;
    		else if (slope1 > slope2) return 1;
    		return 0;
    	}
    }
    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	int x = 1234;
    	int y = 5678;
    	int N = 6;
    	Point p = new Point(x, y);
    	Point q = new Point(19000, 10000);
    	Point q1 = new Point(18000, 10000);
    	Point q2 = new Point(32000, 10000);
    	Point q3 = new Point(14000, 10000);
    	Point q4 = new Point(21000, 10000);
    	System.out.println(q.slopeTo(p));
    	Point[] a = new Point[N];
    	a[0] = p;
    	a[1] = q;
    	a[2] = q1;
    	a[3] = q2;
    	a[4] = q3;
    	a[5] = q4;
    	Arrays.sort(a,1,4,p.SLOPE_ORDER);
    	for (Point s: a){
    		System.out.println(s.toString());
    	}
    }
}