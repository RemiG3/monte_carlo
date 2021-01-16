

class Main{
	//les parametres de l'experiences
    static double nb_Aiguille = 1_000_000;
    static double lg_aiguille = 2.5;
    static double lg_latte = 3;
    static double lg_parquet = 10 * lg_latte;     //il y a 10 lattes de parquet
    
    //variables d'experiences
    static double x1, y1, x2, y2;
    static double theta;
    
    //resulats esperience
    static double nb_lattes_coupees = 0;
    static double pi_appro = 0;
    

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        
        //boucle principale
        //on lance les aiguilles une par une et on regarde sa position par rapport aux lattes
        for (int i = 0; i < nb_Aiguille; i++){
            //on place le premier point sur le parquet
            x1 = Math.random() * (lg_parquet - (lg_aiguille * 2) + lg_aiguille);    // attention :  si le point est trop proche de la bordure 
            y1 = Math.random() * (lg_parquet - (lg_aiguille * 2) + lg_aiguille);    //              le second pourrait sortir de la zone et faussé les résultats
            //on place le second point a une distance a
            theta = Math.random() * 360;
            x2 = x1 + (lg_aiguille * Math.cos(Math.toRadians(theta))); 
            y2 = y1 + (lg_aiguille * Math.sin(Math.toRadians(theta))); 
            
            //on peut vérifier, on a bien des aiguilles de tailles lg_aiguilles
            //System.out.println("x1 : " + x1 + "  y1 : " + y1);
            //System.out.println("x2 : " + x2 + "  y2 : " + y2);
            //System.out.println("");
            
            //on regarde si les deux points y1 et y2 de l aiguille se trouvent sur la meme bande
            if (((y1 - (y1 % lg_latte)) / lg_latte) != ((y2 - (y2 % lg_latte)) / lg_latte)){
                nb_lattes_coupees++;
            }
            
            //remarque:
            //  en soit on as pas besoin de calculer des points en x,y mais seulement en y
            //  mais cela se rapproche plus de l'experience originel de Mario Lazzarini (et Laplace)
        }
        
        //calcul de l approximation de pi
        
        System.out.println(nb_lattes_coupees);
        pi_appro = (lg_aiguille / lg_latte) * (nb_Aiguille / nb_lattes_coupees) * 2;
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Result found in " + Math.round(endTime-startTime) / 1000.0 + " sec.");
        System.out.println("pi estimer a : " + pi_appro);
    }
	
}

