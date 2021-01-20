public class Method2 {
	/*
	 * Methode 2 : Utilisation de la moyenne des M expériences tel que r_1 > r_2 > ... > r_(m-1) <= r_m
	 * Où les ri sont des nombres tirés aléatoirement entre 0 et 1.
	 */
    public static void main(String[] args){
        long N;

        if(args.length == 1){
            N = Long.parseLong(args[0]);
        } else{
            System.out.println("*** Demo mode ***");
            System.out.println("Usage : java Method2 [nb needles] [size needle] [size slat] [nb slat]");

            N = 100_000_000;

            System.out.println("Nb experience = " + N);
        }

        method2(N);
    }

    private static double method2(long N){
        long startTime = System.currentTimeMillis();

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

        System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
        System.out.println(result);

        return result;
    }
}
