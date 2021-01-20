class Method1 {
	/*
	 * Methode 1 : Utilisation de la somme des plus petit entier n tel que pour chaque la somme des ri > 1
	 * Où les ri sont des nombres tirés aléatoirement entre 0 et 1.
	 */
	public static void main(String[] args){
		long N;

		if(args.length == 1){
			N = Long.parseLong(args[0]);
		} else{
			System.out.println("*** Demo mode ***");
			System.out.println("Usage : java Method1 [nb needles] [size needle] [size slat] [nb slat]");

			N = 100_000_000;

			System.out.println("Nb experience = " + N);
		}

		method1(N);
	}

	private static double method1(long N){
		long startTime = System.currentTimeMillis();

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

		System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
		System.out.println(result);

		return result;
	}
}

