
public class PercolationStats {
	private double[] prob;
	private int demin;
	private boolean done; 
	   public PercolationStats(int N, int T)  {
		// perform T independent computational experiments on an N-by-N grid
		   if (N <=0 || T<=0) throw new java.lang.IllegalArgumentException();
		   prob = new double[T];
		   demin = N;
		   done = false;

	   }  
	   public double mean(){
		   // sample mean of percolation threshold
		   if (!done){
			   simall();
		   }
		   double meanstat = StdStats.mean(prob);
		   return meanstat;
	   }
	   public double stddev(){
		   // sample standard deviation of percolation threshold
		   if (!done){
			   simall();
		   }	
		   double stdev = StdStats.stddev(prob);
		   return stdev; 		   
	   }
	   public double confidenceLo(){
		   // returns lower bound of the 95% confidence interval

		   double meanstat = mean();
		   double stdev = stddev();

		   double result = meanstat - ((1.96*stdev)/Math.pow(prob.length,0.5));
		   return result;		   
	   }
	   public double confidenceHi(){   
		   double meanstat = mean();
		   double stdev = stddev();
		   double result = meanstat + ((1.96*stdev)/Math.pow(prob.length,0.5));
		   return result;		   
		   
	   }
	   private double simulate(int index){
		   int count = 0;
		   double result = 0;
		   Percolation sample= new Percolation(this.demin);
		   while (!sample.percolates()){
			   int i = StdRandom.uniform(1,demin+1);
			   int j = StdRandom.uniform(1,demin+1);
			   if (!sample.isOpen(i,j)){
				   sample.open(i, j);
				   count++;
			   }
		   }
		   result = count/Math.pow(demin, 2);
		   return result;
	   }
	   private void simall(){
		   done = true;
		   for (int i =0; i<prob.length; ++i){
			   prob[i] = simulate(i);
		   }
	   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PercolationStats testing = new PercolationStats(200,100);
		System.out.println("mean                    = " + testing.mean());
		System.out.println("stddev                  = " + testing.stddev());
		System.out.println("95% confidence interval = " + testing.confidenceHi()+ ", " + testing.confidenceLo());


	}

}
