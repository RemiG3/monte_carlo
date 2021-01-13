
class Main{
	
	
	public static void main(String[] args){
		
		long startTime = System.currentTimeMillis();
		
		double rayon = 10;
		double c = 0;
		double n = 10_000_000;
		for(long i = 1; i < n; i++){
			double xi = Math.random()*rayon;
			double yi = Math.random()*rayon;
			
			if((xi*xi) + (yi*yi) <= (rayon * rayon))
				c++;
		}
		
		double result = ((c / n)*4);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
		System.out.println(result);
	}
	
}
