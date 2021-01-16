public class Main
{
    
    public double a = 0;
    public double b = 1;
    public double sum = 0;
    
    public long n = 10000000;
    
    public Main()
    {
        for(long i = 0; i < n; i++){
            double rng = Math.random() * ( b - a );
            sum += Math.sqrt(1 - (rng*rng));
        }
        double reply = (b - a) * (sum / n);
        System.out.println(reply*4);
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