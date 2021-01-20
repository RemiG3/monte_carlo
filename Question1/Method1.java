public class Method1
{
    public static void main(String[] args)
    {
        double b;
        double n;

        if(args.length == 2){
            b = Double.parseDouble(args[0]);
            n = Double.parseDouble(args[1]);
        } else{
            System.out.println("*** Demo mode ***");
            System.out.println("Usage : java Method1 [b] [nb iteration]");

            b = 1;
            n = 10_000_000;

            System.out.println("b = " + b);
            System.out.println("nb = " + n);
        }

        method1(b, n);
    }

    private static double method1(double b, double n){
        long startTime = System.currentTimeMillis();

        double a = 0;
        double sum = 0;

        for(long i = 0; i < n; i++){
            double rng = Math.random() * ( b - a );
            sum += Math.sqrt(1 - (rng*rng));
        }
        double reply = (b - a) * (sum / n) * 4;

        long endTime = System.currentTimeMillis();


        System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
        System.out.println(reply);

        return reply;
    }
}
/*
    // sortie console : 
    3.141845797206595
    3.1416959073298036
    3.1412376544245943
    3.1413853413178603
    3.1412977564082825

*/
