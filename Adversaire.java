import java.util.Random;

public class Adversaire{

  Random random = new Random();

    private String nom;
    private int pointsDeVie;
    private int force;
    private int aleatoire;
    private int type;
    private int XP;
    private int armure;
    
    public Adversaire() {

        nom = "-  -";
    }

    public Adversaire(String nom, int pointsDeVie, int type, int armure, int XP) {

        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.type = type;
        this.armure = armure;
        this.XP = XP;
        }

    public String getNom() {

        return nom;
    }

    public int getPV() {

        return pointsDeVie;
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

    public int getType() {

        return type;
    }


    public void modifVie(int m) {

        pointsDeVie = pointsDeVie + m;
    }
    
    //rencontre aléatoire
    public void rencontreHasard(int m) {
      aleatoire = random.nextInt(m);

      switch (aleatoire) {

        case 1:
          type = 1;
          nom = "Skeletor";
          pointsDeVie = 200;
          armure = 15;
          XP = 100;

          break;

        case 2:
          type = 2;
          nom = "Gardien de prison";
          pointsDeVie = 50;
          armure = 2;
          XP = 10;

          break;

        default:
          type = 0;
          break;
      }

    }


    //liste des attaques
    public void attaqueHasard() {  
      aleatoire = random.nextInt(5);

      if(type == 1){
        switch (aleatoire) {

          case 1:
            System.out.println("Skeletor vous lance un fait perturbant");
            force = random.nextInt(10);
           break;
      
          case 2:
            System.out.println("Skeletor jette un clodo sur vous");
            force = random.nextInt(50);
            break;
      
          case 3:
            System.out.println("Skeletor arrête le temps <<THE WORLD!!>> \nC'est la fin pour vous");
            force = 200;
            break;

          case 4:
            System.out.println("Skeletor fait du breakdance devant vous");
            force = random.nextInt(20);
            break;

          default:
            System.out.println("Skeletor mange une pomme");
            modifVie(20);
            break;
      }
    }
    if(type == 2){
        switch (aleatoire) {

          case 1:
           System.out.println("Gardien de prison vous reprends votre mitraillette");
            force = random.nextInt(50);
           break;

          case 2:
           System.out.println("Gardien de prison vous attaque à l'aide de sa mattraque");
            force = random.nextInt(20);
           break;

          default:
            System.out.println("Gardien de prison boit du Jack Daniel");
            modifVie(15);
            break;
      }
    }
  }

    public void forceReset(){

      force = 0;
    }

    public void afficherStats() {

        System.out.println("nom: - " + nom + " -\nPV: - " + pointsDeVie + " -\nforce: - " + force + " -");
    }

    public void verifierVie(Personnage joueur) {

        if (pointsDeVie <= 0) {

            System.out.println("Vous voici victorieux de l'abominable " + nom + "\nVous remportez " + XP + " points d'expérience");
            joueur.modifXP(XP);
            joueur.combat = false;
        }
    }

}
