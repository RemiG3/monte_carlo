import javafx.application.Application;
import javafx.scene.paint.Color;


class Method2{
	public static void main(String[] args){
	    boolean withDraw;
        double radius;
        double sideLenght;
        double n;

		if(args.length == 4){
            radius = Double.parseDouble(args[0]);
            sideLenght = Double.parseDouble(args[1]);
            n = Double.parseDouble(args[2]);
            withDraw = Integer.parseInt(args[3]) != 0;
        } else{
		    System.out.println("*** Demo mode ***");
		    System.out.println("Usage : java Method2 [radius] [side length] [nb iteration] [with draw (0 or 1)]");

            withDraw = true;
            radius = 100;
			sideLenght = 10;
            n = 200_000;

			System.out.println("radius = " + radius);
			System.out.println("radius ratio = " + sideLenght);
			System.out.println("nb = " + n);
			System.out.println("with draw = " + ((withDraw) ? 1 : 0));
        }

		if(radius > sideLenght)
			System.err.println("Warning : Radius greater than side length");


		method2(withDraw, radius, sideLenght, n);
	}

	/*
	 * Methode Monte Carlo : on prend au hasard un point dans le carré (de longeur sideLenght) et l'on vérifie s'il appartient au quart de cercle (de rayon radius), l'on répéte cette opération n fois.
	 * Utilisation d'une interface graphique possible avec JavaFX (avec le paramètre withDraw).
	 */
	private static double method2(boolean withDraw, double radius, double sideLenght, double n){

		if(withDraw){
			RenduGraphique.setWindowSize(500, 500);
			// Lancement d'un nouveau thread pour l'application grahpique
			new Thread(() -> Application.launch(RenduGraphique.class)).start();
		}

		long startTime = System.currentTimeMillis();

		double c = 0;

		for(long i = 0; i < n; i++){
			// Tirage des coordonnées des points
			double xi = Math.random()*sideLenght;
			double yi = Math.random()*sideLenght;

			if(withDraw){
				// Temps d'attente tout les 1000 itérations pour l'affichage
				if(i%1000 == 0) try{ Thread.sleep(100); } catch(InterruptedException ignored){}
				
                Color color = Color.rgb(0, 0, 255, 0.25); // bleu
                if((xi*xi) + (yi*yi) <= (radius * radius)) {
                    color = Color.rgb(255, 0, 0, 0.25); // rouge
                    c++;
                }

				RenduGraphique.AddPoint(xi/sideLenght, yi/sideLenght, 2., color);
			} else{
                if((xi*xi) + (yi*yi) <= (radius * radius))
                    c++;
            }
		}

		double result = ((c / n)*4);

		long endTime = System.currentTimeMillis();


		System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
		System.out.println(result);

		return result;
	}
}

