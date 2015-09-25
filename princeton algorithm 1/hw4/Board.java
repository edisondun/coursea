

public class Board {
	private int[][] tiles;
	private int N;
	private int man;
	private int ham;
	private void swap(int block[][], int i,int j,int x,int y){
		int temp = block[i][j];
		block[i][j] = block[x][y];
		block[x][y] = temp;
	}
	private int[][] copyarray(){
		int block[][] = new int[N][N];
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	block[i][j] = tiles[i][j];
	        }
	    }
	    return block;
	}
	
	public Board(int[][] blocks){
		N = blocks.length;
		man = 0;
		ham = 0;
		tiles = new int[N][N];
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	tiles[i][j] = blocks[i][j];
	        }
	    }
		// construct a board from an N-by-N array of blocks
	}
	                                       // (where blocks[i][j] = block in row i, column j)
	public int dimension(){
		return N; // board dimension N
	}
	public int hamming(){
		if (ham != 0)return ham;
		int count = 0;
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	int expect = i*N+j+1;
	        	if ((tiles[i][j] != expect)&& (expect != N*N))
	        		count++;
	        }
	    }
	    ham = count;
	    return count;
		// number of blocks out of place
	}
	public int manhattan(){
		if (man != 0)return man;
		int count = 0;
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	if (tiles[i][j] == 0)continue;
	        	int x = (tiles[i][j]-1)%N;
	        	int y = (tiles[i][j]-1)/N;
	        	count += (Math.abs(i-y) + Math.abs(j-x));
	        }
	    }
	    man = count;
	    return count;
		// sum of Manhattan distances between blocks and goal
	}
	public boolean isGoal(){
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	int expect = i*N+j+1;
	        	if ((tiles[i][j] != expect)&& (expect != N*N))
	        		return false;
	        }
	    }
	    return true;
		// is this board the goal board?
	}
	public Board twin(){
		int block[][] = copyarray();
		if ((block[0][0] != 0)&& (block[0][1] != 0)){
			swap(block,0,0,0,1);
		}else{
			swap(block,1,0,1,1);
		}
		return new Board(block);
		// a boadr that is obtained by exchanging two adjacent blocks in the same row
	}
	public boolean equals(Object y){
		if (this == y) return true;
		if (y == null) return false;
		if (this.getClass() != y.getClass()) return false;
		Board x = (Board)y;
		if (x.dimension() != this.dimension() )return false;
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	if (tiles[i][j] != x.tiles[i][j]) return false;
	        }
	    }
	    return true;
		// does this board equal y?
	}
	public Iterable<Board> neighbors(){
		Stack<Board> result = new Stack<Board>();
		int x = 0;
		int y = 0;
		boolean done = false;
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	if (tiles[i][j] == 0){
	        		x = i;
	        		y = j;
	        		done = true;
	        		break;
	        	}
	        }
	        if (done)break;
	    }
	    if (x-1 >=0){
	    	int block[][] = copyarray();
	    	swap(block,x,y,x-1,y);
	    	result.push(new Board(block));
	    }
	    if (x+1 <N){
	    	int block[][] = copyarray();
	    	swap(block,x,y,x+1,y);
	    	result.push(new Board(block));
	    }
	    if (y-1 >=0){
	    	int block[][] = copyarray();
	    	swap(block,x,y,x,y-1);
	    	result.push(new Board(block));
	    }
	    if (y+1 <N){
	    	int block[][] = copyarray();
	    	swap(block,x,y,x,y+1);
	    	result.push(new Board(block));
	    }
		return result; 
		// all neighboring boards
	}
	public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            s.append(String.format("%2d ", tiles[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}     // string representation of this board (in the output format specified below)
	
	public static void main(String[] args){
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    StdOut.println(initial);	    
//	    StdOut.println(initial.hamming());
//	    StdOut.println(initial.manhattan());
//	    StdOut.println(initial.isGoal());
//	    StdOut.println(initial.hamming());
//	    StdOut.println(initial.twin());	
//	    for (Board i : initial.neighbors()){
//	    	 StdOut.println(i);	
//	    }
	    Board twins = initial.twin().twin();
	    Board twin = initial.twin();
	    StdOut.println(initial.equals(twin));
	    StdOut.println(initial.equals(twins));
		// unit tests (not graded)
	}
}