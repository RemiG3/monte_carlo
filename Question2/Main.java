

class Main{
	public static void main(String[] args){
		System.out.println("Methode 1 :");
		methode1();
		
		System.out.println("\nMethode 2 :");
		methode2();
	}
	
	
	public static void methode1(){
		long startTime = System.currentTimeMillis();
		
		long N = 100_000_000;
		long n;
		long sum_n = 0;
		
		
		for(long i = 0; i < N; i++){
			n = 0;
			
			double sum_r = .0;
			while(sum_r <= 1.){
				sum_r += Math.random();
				n++;
			}
			sum_n += n;
		}
		
		double result = (double)sum_n/N;
		long endTime = System.currentTimeMillis();
		
		System.out.println("Resultat trouve en " + Math.round(endTime-startTime) / 1000.0 + " sec.");
		System.out.println(result);
	}
	
	
	public static void methode2(){
		long startTime = System.currentTimeMillis();
		long N = 100_000_000;
		long n;
		long sum_n = 0;
		
		
		for(long i = 0; i < N; i++){
			n = 1;
			
			double r = Math.random(), last_r = Math.random();
			while(last_r > r){
				last_r = r;
				r = Math.random();
				n++;
			}
			sum_n += n+1;
		}
		
		double result = (double)sum_n/N;
		long endTime = System.currentTimeMillis();
		
		System.out.println("Resultat trouve en " + Math.round(endTime-startTime) / 1000.0 + " sec.");
		System.out.println(result);
	}
}

