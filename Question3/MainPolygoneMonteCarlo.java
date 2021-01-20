import javafx.application.Application;
import javafx.scene.paint.Color;


class MainPolygoneMonteCarlo{
	public static void main(String[] args){
		//double n = 50_000_000;
        double n;
		boolean withDraw;


        if(args.length == 2){
            n = Double.parseDouble(args[0]);
            withDraw = Integer.parseInt(args[1]) != 0;
        } else{
            System.out.println("*** Demo mode ***");
            System.out.println("Usage : java MainPolygone [nb iteration] [with draw (0 or 1)]");

            withDraw = true;
            n = 250_000;

            System.out.println("nb = " + n);
            System.out.println("with draw = " + ((withDraw) ? 1 : 0));
        }


        double[][] polygon = new double[][]{{1.,1.},
											{3.,1.},
											{5.,2.},
											{4.,4.},
											{3.,5.},
											{1.,3.}};

        monte_carlo(polygon, n, withDraw);
	}

	private static double monte_carlo(double[][] polygon, double n, boolean withDraw){
        long startTime = System.currentTimeMillis();

        double c = 0;
        double a = 0, b = 10, M = 10;
        double multipicator = 50;

        if(withDraw){
            RenduGraphique.setWindowSize((b-a)*multipicator, M*multipicator);
            RenduGraphique.createPolygon(polygon, multipicator);
			// Lancement d'un nouveau thread pour l'application grahpique
            new Thread(() -> Application.launch(RenduGraphique.class)).start();
        }

        for(int i = 0; i < n; i++){
			// Tirage des coordonnées des points
            double xi = Math.random()*(b-a);
            double yi = Math.random()*(M);

            if(withDraw){
				// Temps d'attente tout les 1000 itérations pour l'affichage
                if(i%1000 == 0) try{ Thread.sleep(100); } catch(InterruptedException ignored){}

                Color color = Color.rgb(0, 0, 255, 0.25); // bleu
                if(isInPolygon(polygon, new double[]{xi, yi})){
                    color = Color.rgb(255, 0, 0, 0.25); // rouge
                    c++;
                }

                RenduGraphique.AddPoint(xi*multipicator, yi*multipicator, 2., color);
            } else{
                if(isInPolygon(polygon, new double[]{xi, yi}))
                    c++;
            }
        }

        double result = (c / n) * ((b-a) * (M));

        long endTime = System.currentTimeMillis();

        System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
        System.out.println(result);

        return result;
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
}






