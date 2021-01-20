import javafx.application.Application;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.ArrayList;


class MainPolygoneATrous{
	
	
	public static void main(String[] args){
		//double n = 50_000_000;
		double n;
		boolean withDraw;

		if(args.length == 2){
			n = Double.parseDouble(args[0]);
			withDraw = Integer.parseInt(args[1]) != 0;
		} else{
			System.out.println("*** Demo mode ***");
			System.out.println("Usage : java MainPolygoneATrous [nb iteration] [with draw (0 or 1)]");

			withDraw = true;
			n = 250_000;

			System.out.println("nb = " + n);
			System.out.println("with draw = " + ((withDraw) ? 1 : 0));
		}

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

		monte_carlo(poly_a_trou, n, withDraw);
	}

	public static double monte_carlo(PolygoneATrou poly_a_trou, double n, boolean withDraw){
		long startTime = System.currentTimeMillis();

		double c = 0;
		double a = 0, b = 10, M = 10;
		double multipicator = 50;

		if(withDraw){
			RenduGraphique.setWindowSize((b-a)*multipicator, M*multipicator);
			RenduGraphique.createPolygon(poly_a_trou.getPolygon(), multipicator);
			for(double[][] trou : poly_a_trou.getTrous())
				RenduGraphique.createPolygon(trou, multipicator);
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
				if(poly_a_trou.isIn(xi, yi)){
					color = Color.rgb(255, 0, 0, 0.25); // rouge
					c++;
				}

				RenduGraphique.AddPoint(xi*multipicator, yi*multipicator, 2., color);
			} else{
				if(poly_a_trou.isIn(xi, yi))
					c++;
			}
		}

		double result = (c / n) * ((b-a) * (M));

		long endTime = System.currentTimeMillis();


		System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
		System.out.println(result);

		return result;
	}
}


class PolygoneATrou{
	private double[][] polygon;
	private List<double[][]> trous;
	
	public PolygoneATrou(double[][] polygon, List<double[][]> trous){
		this.polygon = polygon;
		this.trous = trous;
	}

	public double[][] getPolygon(){
		return polygon;
	}

	public List<double[][]> getTrous(){
		return trous;
	}
	
	public boolean isIn(double x, double y){
		for(double[][] trou : trous){
			if(MainPolygoneMonteCarlo.isInPolygon(trou, new double[]{x, y}))
				return false;
		}
		
		return MainPolygoneMonteCarlo.isInPolygon(polygon, new double[]{x, y});
	}
}






