

class Main{
	
	
	public static void main(String[] args){
		
		long startTime = System.currentTimeMillis();
		
		double c = 0;
		double a = 0, b = 1;
		double M = 10;
		double n = 100_000_000;
		for(long i = 1; i < n; i++){
			double xi = Math.random()*(b-a);
			double yi = Math.random()*M;
			
			if(yi <= Math.sqrt((b-a)-Math.pow(xi, 2)))
				c++;
		}
		
		double result = ((c*(b-a)*M) / n)*4;
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
		System.out.println(result);
	}
	
}

