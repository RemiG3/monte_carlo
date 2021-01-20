import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import java.util.concurrent.ConcurrentLinkedQueue;


public class RenduGraphique extends Application {
   private static int width = 100, height = 100;
   
   // Liste des points (utilisée par 2 threads)
   private static volatile ConcurrentLinkedQueue<Ellipse> points = new ConcurrentLinkedQueue<>();

   @Override
   public void start(Stage stage) {
      // Création du groupe
      Group root = new Group();
      
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


   static void setWindowSize(int width, int height) {
      RenduGraphique.width = width;
      RenduGraphique.height = height;
   }
   
   
   static void AddPoint(Double x, Double y, Double size, Color color){
        Ellipse elipse = new Ellipse();
        elipse.setCenterX(x * width);
        elipse.setCenterY(y * height);
        elipse.setRadiusX(size);
        elipse.setRadiusY(size);
        elipse.setFill(color);

        points.add(elipse);
   }
}

