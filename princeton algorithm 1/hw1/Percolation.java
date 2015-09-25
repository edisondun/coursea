
public class Percolation {
	private int scale;
	private int count;
	private WeightedQuickUnionUF space;
	private boolean opened[];
	private int xyTo1D(int i, int j){
		int N = (i-1)*scale + j;
		return N;
	}
	public Percolation(int N) {
		this.count = (int) Math.pow(N, 2);
		this.scale = N;
		this.space = new WeightedQuickUnionUF(this.count+2);
		this.opened = new boolean[this.count+2];
		
	   // create N-by-N grid, with all sites blocked
	   }
	public void open(int i, int j)   {
		   if (i <= 0 || i > scale) throw new IndexOutOfBoundsException("row index i out of bounds");
		   if (j <= 0 || j > scale) throw new IndexOutOfBoundsException("row index j out of bounds");
		   opened[xyTo1D(i,j)] = true;
		   if ((i>1)&&(opened[xyTo1D(i-1,j)])){
			   space.union(xyTo1D(i,j), xyTo1D(i-1,j));
		   }
		   if ((i<scale)&&(opened[xyTo1D(i+1,j)])){
			   space.union(xyTo1D(i,j), xyTo1D(i+1,j));
		   }
		   if ((j>1)&&(opened[xyTo1D(i,j-1)])){
			   space.union(xyTo1D(i,j), xyTo1D(i,j-1));
		   }
		   if ((j<scale)&&(opened[xyTo1D(i,j+1)])){
			   space.union(xyTo1D(i,j), xyTo1D(i,j+1));
		   }
		   if (i==1){
			   space.union(xyTo1D(i,j), 0);
		   }
		   if (i==scale){
			   space.union( count+1, xyTo1D(i,j));
		   }
	   } 
	public boolean isOpen(int i, int j) {
		
		   if (i <= 0 || i > scale) throw new IndexOutOfBoundsException("row index i out of bounds");
		   if (j <= 0 || j > scale) throw new IndexOutOfBoundsException("row index j out of bounds");
		   
		   return opened[xyTo1D(i,j)];// open site (row i, column j) if it is not open already
		}    // is site (row i, column j) open?
	public boolean isFull(int i, int j) {
		   if (i <= 0 || i > scale) throw new IndexOutOfBoundsException("row index i out of bounds");
		   if (j <= 0 || j > scale) throw new IndexOutOfBoundsException("row index j out of bounds");		   
		   return (space.connected(xyTo1D(i,j), 0));// is site (row i, column j) full?
	}
	public boolean percolates()    {
		if (space.connected(count+1,0)){
		   return true;
	   }
		return false;// does the system percolate?
	}


//	public void printopen(){
//		for (int i =1; i<=scale; i++){
//			for (int j =1; j<=scale; j++){
//				if(opened[xyTo1D(i,j)]){
//					System.out.print('0');
//				}else{
//					System.out.print('X');
//				}
//			}
//			System.out.println();
//		}
//	}
//	public static void main(String[] args) {
//		System.out.println("hello world!");
//
//		Percolation testing = new Percolation(5);
//		testing.open(5, 3);
//		testing.open(2, 3);
//		testing.open(1,3);
//		testing.open(3,4);
//		testing.open(3,5);
//		testing.open(4,5);
//		testing.open(5,5);
//		testing.open(3,3);
//		testing.printopen();
//		System.out.print(testing.percolates());
//	}
}