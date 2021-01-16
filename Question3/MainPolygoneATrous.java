import java.awt.Polygon;
import java.util.List;
import java.util.ArrayList;


class MainPolygoneATrous{
	
	
	public static void main(String[] args){
		double n = 50_000_000;
		double[][] polygon = new double[][]{{1.,1.},
											{5.,1.},
											{5.,5.},
											{1.,5.}};
		
		double[][] trou1 = new double[][]{{2,2},
										  {4,2},
										  {4,4},
										  {2,4}};
		
		double[][] trou2 = new double[][]{{1,1},
										  {1,3},
										  {3,3},
										  {3,1}};
		// Trous aires : 4 + 4 - 1 = 7
		// Polygone aire : 16 - 7 = 9
		
		List<double[][]> trous = new ArrayList<>();
		trous.add(trou1); trous.add(trou2);
		
		PolygoneATrou poly_a_trou = new PolygoneATrou(polygon, trous);
	
		
		double c = 0;
		double a = 0, b = 10, M = 10;
		
		for(int i = 0; i < n; i++){
			double xi = Math.random()*(b-a);
			double yi = Math.random()*(M);
			
			if(poly_a_trou.isIn(xi, yi))
				c++;
		}
		
		double result = (c / n) * ((b-a) * (M));
		
		System.out.println(result);
	}
}


class PolygoneATrou{
	
	private double[][] polygon;
	private List<double[][]> trous;
	
	public PolygoneATrou(double[][] polygon, List<double[][]> trous){
		this.polygon = polygon;
		this.trous = trous;
	}
	
	public boolean isIn(double x, double y){
		for(double[][] trou : trous){
			if(isInPolygon(trou, new double[]{x, y}))
				return false;
		}
		
		return isInPolygon(polygon, new double[]{x, y});
	}
	
	private boolean isInPolygon(double[][] polygon, double[] point){
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
}






