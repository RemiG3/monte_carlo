import java.awt.Polygon;
import java.util.List;
import java.util.ArrayList;


class MainPolygone{
	
	
	public static void main(String[] args){
		double n = 50_000_000;
		
		double[][] polygon = new double[][]{{1.,1.},
											{3.,1.},
											{5.,2.},
											{4.,4.},
											{3.,5.},
											{1.,3.}};
		
		double c = 0;
		double a = 0, b = 10, M = 10;
		
		for(int i = 0; i < n; i++){
			double xi = Math.random()*(b-a);
			double yi = Math.random()*(M);
			
			if(isInPolygon(polygon, new double[]{xi, yi}))
				c++;
		}
		
		double result = (c / n) * ((b-a) * (M));
		
		System.out.println(result);
	}

	
	public static boolean isInPolygon(double[][] polygon, double[] point){
		// nous utilisons la méthode du site suivant : http://maxence.delannoy.pagesperso-orange.fr/pt_poly.htm
        // cette technique induit une erreur supplémentaire, mais chez nous elle est de l'ordre de 10e-15
        double nbCut = 0;
        //pour chaque segment du polygone il faut determiner si il y intersetion avec une doite infinie
        //on boucle sur chaque segment de polygone
		
        for (int i = 0; i < polygon.length; i++){
			double[] polygon1 = polygon[i];
			double[] polygon2 = (i+1 < polygon.length) ? polygon[i+1] : polygon[0];
			
            //check si le point est au niveau y du segment
            if ( ((polygon1[1] <= point[1]) && (polygon2[1] >= point[1])) || ((polygon1[1] >= point[1]) && (polygon2[1] <= point[1]))){
                //ensuite il faut savoir si le point se trouve a gauche du point projeté sur le segment
                
                //on commence par determiner la fonction affine pour trouver se point d'intersection
                double pente = (polygon2[0] - polygon1[0] != 0) ? (polygon2[1] - polygon1[1]) / (polygon2[0] - polygon1[0]) : .0; //delta x / delta y
                //on applique cette pente au point i du polygone
                double coordOrigin = polygon1[1] - (pente * polygon1[0]);   // b = y - ax
                
                //on trouve la coordonnées x du point d'intersection 
                double xIntersect = (pente != 0) ? (point[1] - coordOrigin) /  pente : polygon1[0];
                
                // on regarde si le point se trouve a gauche du segment
                if (point[0] <= xIntersect){
                    nbCut++;
                }
            }
        }
		
        return (nbCut%2 == 1);
	}
	
	
	public static void lacet(int[][] polygon){
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < polygon.length - 1; i++){
            sum1 += polygon[i][0] * polygon[i+1][1];
            sum2 += polygon[i+1][0] * polygon[i][1];
            System.out.println(sum1);
            System.out.println(sum2);
        }
        sum1 += polygon[polygon.length-1][0] * polygon[0][1];
        sum2 += polygon[0][0] * polygon[polygon.length-1][1];
        
        System.out.println("result : ");
        System.out.println(0.5 * (sum1 - sum2));
    }
}






