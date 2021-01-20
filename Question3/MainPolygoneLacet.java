
class MainPolygoneLacet {
	public static void main(String[] args){

        double[][] polygon = new double[][]{{1.,1.},
											{3.,1.},
											{5.,2.},
											{4.,4.},
											{3.,5.},
											{1.,3.}};

        monte_carlo(polygon);
	}

	private static double monte_carlo(double[][] polygon){
        long startTime = System.currentTimeMillis();

        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < polygon.length - 1; i++){
            sum1 += polygon[i][0] * polygon[i+1][1];
            sum2 += polygon[i+1][0] * polygon[i][1];
        }
        sum1 += polygon[polygon.length-1][0] * polygon[0][1];
        sum2 += polygon[0][0] * polygon[polygon.length-1][1];

        double result = 0.5 * (sum1 - sum2);

        long endTime = System.currentTimeMillis();

        System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
        System.out.println(result);

        return result;
    }
}






