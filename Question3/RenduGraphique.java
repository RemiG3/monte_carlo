import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Attention : la méthode utilisée à tendance à surchargée la RAM.
 * Cela peut aller jusqu'à un échec en cours d'éxécution : OutOfMemoryError
 * 
 * Cela dépend de la machine utilisée,
 * Il est conseillé de baisser le nombre de tirages lors du lancement en cas de machine moins performante.
 * Merci.
 */
public class RenduGraphique extends Application {
   // Création du groupe
   private static Group root = new Group();
   
   // Liste des points (utilisée par 2 threads)
   static volatile ConcurrentLinkedQueue<Ellipse> points = new ConcurrentLinkedQueue<>();
   
   private static double width = 100, height = 100;
   
   
   @Override 
   public void start(Stage stage) {
      // Création de la scène
      Scene scene = new Scene(root, width, height);
      
      // Mise en place de la scène
      stage.setTitle("Methode Monte Carlo");
      stage.setScene(scene); 
      
      //Affichage de la scène
      stage.show();

	  // Animation du dessin
      AnimationTimer timer = new AnimationTimer() {
         long startTime = -1;

         @Override
         public void handle(long l) {
            if(startTime == -1)
               startTime = System.currentTimeMillis();

            if(System.currentTimeMillis()-startTime > 100){
               startTime = System.currentTimeMillis();
               Ellipse point;
               while((point = points.poll()) != null){
                  root.getChildren().add(point);
               }
            }
         }
      };

      timer.start();
   }

   static void setWindowSize(double width, double height) {
      RenduGraphique.width = width;
      RenduGraphique.height = height;
   }
   
   // Ajoute un polygone dans la scène
   public static void createPolygon(double[][] points, double multiplicator){
      Polygon polygon = new Polygon();
       for(double[] point : points){
          polygon.getPoints().add(point[0]*multiplicator);
          polygon.getPoints().add(point[1]*multiplicator);
       }

       polygon.setFill(Color.rgb(0,0,0,0));
       polygon.setStroke(Color.rgb(0,0,0));
       polygon.setStrokeWidth(0.5);

       root.getChildren().add(polygon);
    }
   
   public static void AddPoint(Double x, Double y, Double size, Color color){
      Ellipse elipse = new Ellipse();  
      elipse.setCenterX(x);
      elipse.setCenterY(y);
      elipse.setRadiusX(size);
      elipse.setRadiusY(size);
      elipse.setFill(color);
      
      points.add(elipse);
   }
}

