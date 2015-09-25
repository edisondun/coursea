import java.util.Comparator;

public class Solver {
	private final Comparator<SearchNode> manhattan_Order = new man_order();
	private final Comparator<SearchNode> hamming_Order = new ham_order();
	private MinPQ<SearchNode> Board_PQ;
	private MinPQ<SearchNode> Twin_PQ;
	private int move;
	private int twin_move;
	private boolean solvable;
	private SearchNode result;
	private class SearchNode {
		private int moves;
		private SearchNode previous;
		private Board node;
		private int manhattan;
		private int hamming;
		public SearchNode(int move, SearchNode last, Board inital){
			moves = move;
			previous = last;
			node = inital;
			manhattan = node.manhattan()+ moves;
			hamming = node.hamming()+moves;
		}
		public int get_manhattan(){
			return manhattan;
		}
		public int get_hamming(){
			return hamming;
		}
		public SearchNode get_previous(){
			return previous;
		}
		public Board get_Board(){
			return node;
		}
		public int get_move(){
			return moves;
		}
	}
	public Solver(Board initial) {
		Board_PQ = new MinPQ<SearchNode> (manhattan_Order);
		Twin_PQ = new MinPQ<SearchNode> (manhattan_Order);
		move = 0;
		twin_move =0;
		solvable = false;
		result = null;
		Board_PQ.insert(new SearchNode(move,null,initial));
		Twin_PQ.insert(new SearchNode(move,null,initial.twin()));
		SearchNode temp = null;
		while (!(Board_PQ.isEmpty())&& !(Twin_PQ.isEmpty())){
			temp = Board_PQ.delMin();
			SearchNode twin = Twin_PQ.delMin();
			if (twin.get_Board().isGoal()) break;
			if (temp.get_Board().isGoal()){
				solvable = true;
				break;
			}
			move = temp.get_move() + 1;
			twin_move = twin.get_move() + 1;
			for (Board i: temp.get_Board().neighbors()){
				if (temp.get_previous() != null){
					if (i.equals(temp.get_previous().get_Board()))continue;
				}
				Board_PQ.insert(new SearchNode(move,temp,i));
			}
			for (Board i: twin.get_Board().neighbors()){
				if (twin.get_previous() != null){
					if (i.equals(twin.get_previous().get_Board()))continue;
				}
				Twin_PQ.insert(new SearchNode(move,twin,i));
			}
		}
		if (solvable){
			result = temp;
		}
		// find a solution to the initial board (using the A* algorithm)
	}
	public boolean isSolvable() {
		return solvable;            // is the initial board solvable?
	}
	public int moves(){
		if (solvable) return result.get_move();
		return -1;
		// min number of moves to solve initial board; -1 if unsolvable
	}
	public Iterable<Board> solution(){
		Stack<Board> answer = new Stack<Board>();
		SearchNode temp = result;
		if (temp == null) return null;
		while (temp != null){
			answer.push(temp.get_Board());
			temp = temp.get_previous();
		}
		return answer;
		// sequence of boards in a shortest solution; null if unsolvable
	}
	
	private class man_order implements Comparator<SearchNode>{
		public int compare(SearchNode a, SearchNode b){
			return (a.get_manhattan() - b.get_manhattan()); 
		}
	}
	private class ham_order implements Comparator<SearchNode>{
		public int compare(SearchNode a, SearchNode b){
			return (a.get_hamming() - b.get_hamming()); 
		}
	}
	public static void main(String[] args){
		// solve a slider puzzle (given below)
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}
}