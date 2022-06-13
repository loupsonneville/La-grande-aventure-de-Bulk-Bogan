public class Personnage {

    private String nom;
    private int pointsDeVie;
    private int vieMax;
    private int force;
    private int armure;
    private int XP;
    private int niveau;
    public boolean combat;
    private int x;
    private int y;

    public Personnage() {

        nom = "- Bulk Bogan -";
        pointsDeVie = 100;
        vieMax = 99;
        force = 20;
        armure = 3;
        XP = 0;
        niveau = 20;
        combat = false;
        x = 0;
        y = 0;
    }

    /*déplacements */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int Nord() {

        y += 1;
        return 0;
    }

    public int Est() {

        x += 1;
        return 0;
    }

    public int Ouest() {

        x -= 1;
        return 0;
    }

    public int Sud() {

        y -= 1;
        return 0;
    }

    public void afficherDebug() {
      
        System.out.println("\nPartie de déboguage :\n");
        System.out.println("x: - " + x + " -\ny: - " + y + " -");
        System.out.println("en combat ? : - " + combat + " -");
    }
    /*Fin déplacements*/

    public String getNom() {

        return nom;
    }

    public int getPV() {

        return pointsDeVie;
    }

    public int getVieMax() {

        return vieMax;
    }

    public int getArmure() {

        return armure;
    }

    public int getForce() {

        return force;
    }

    public int getXP() {

        return XP;
    }

    public boolean enCombat() {

        return combat;
    }

    public void modifVie(int m) {

        pointsDeVie += m;
    }

    public void modifXP(int m) {

        XP += m;
    }

    public void niveauSuperieur() {

        force *= 1.2;
        vieMax *= 1.1;
        pointsDeVie = vieMax + 1;
        XP = 0;
        niveau *= 2.5;
    }


    /*actions*/
    public void queueDeBillard() {

      System.out.print("<<Feel the mooscles in your head>>\n");
      force = 40;
    }
    public void heal() {
      if (pointsDeVie <= vieMax) {
        
        System.out.print("<<Drugs into the mooscles>>\n");
        modifVie(40);
        }

      else{

        afficherVieMax();
        }
      
    }
    /*Fin actions*/

    public void afficherStats() {

        System.out.println("                //*****///             ");
        System.out.println("             /               *         ");
        System.out.println("           / ,              ,,,,*/     ");
        System.out.println("          /  ,                ,   /    ");
        System.out.println("         *                  ,,,,,, *   ");
        System.out.println("        /* ,    ,,  #####    ####  *   ");
        System.out.println("        /* ,,,,,,   //((/    /((/  *  ");
        System.out.println("       /* ,,,,,   (/(%#*//  /*#%(/ *  ");
        System.out.println("       /*/  ,     /(/((/(/  (/((/ */  ");
        System.out.println("       /*//        ,    ,,,,    ///    ");
        System.out.println("       *        ///   ///  ///////     ");
        System.out.println("      /   ,     ///////////((##(/      ");
        System.out.println("      *****//*///////*****/////       ");
        System.out.println("     /*** *(/**////////(##((((((/      ");
        System.out.println("    #*** *#/***/////((((//(////      ");
        System.out.println("   (**** ,,,,,,***//*****///*,*,,");

        System.out.println("nom: " + nom + "\nPV: - " + pointsDeVie + " / " + vieMax  + "-\nforce: - " + force + " -\nXP : - " + XP + " / " + niveau + " -");
    }

    public void afficherVieMax(){

        System.out.println("Votre vie est déjà au maximum, montez de niveau si vous voulez l'augmenter.");
    }

    public void verifierVie() {

        if (pointsDeVie <= 0) {

            System.out.println("RIP\nBulk Bogan");
            System.exit(1);
        }

        if (XP >= niveau) {

            niveauSuperieur();

            System.out.println("Vous venez de passer au niveau supérieur, félicitations");
            afficherStats();
        }
    }

}
