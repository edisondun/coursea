import java.io.File;


public class PuzzleChecker {

    public static void main(String[] args) {

        // header
        StdOut.printf("%-25s %7s %8s\n", "filename", "moves", "time");
        StdOut.println("------------------------------------------");

        // for each command-line argument
        for (String filename : args) {
            // read in the board specified in the filename
            In in = new In(filename);
            int N = in.readInt();
            int[][] blocks = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    blocks[i][j] = in.readInt();
            Board initial = new Board(blocks);
            Stopwatch timer = new Stopwatch();
            Solver solver = new Solver(initial);
            int moves = solver.moves();
            double time = timer.elapsedTime();
            StdOut.printf("%-25s %7d %8.2f\n", filename, moves, time);
            // check if puzzle is solvable; if so, solve it print out number of moves

        }

    }
}