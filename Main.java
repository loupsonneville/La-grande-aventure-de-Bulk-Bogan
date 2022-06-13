import java.util.Scanner;
import java.util.Random;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);       //initialisation du scanner
        Random random = new Random();                   //initialisation de l'aléatoire

        Personnage Bulk = new Personnage();             //liste Personnage

        Adversaire Skeletor = new Adversaire("Skeletor", 200, 1, 7, random.nextInt(100));
        Adversaire Gardien = new Adversaire("Gardien de prison", 50, 2, 2, random.nextInt(20));      //liste Adversaire
        Adversaire defaut = new Adversaire(); //Adversaire par défaut


        //liste des Environnements
        Environnement Chambre = new Environnement("chambre", ", une jolie chambre avec des murs blancs et un pot de fleur à votre gauche. \nQue faites vous ?");
        Environnement Jardin = new Environnement("Jardin", ", Vous vous retrouvez dehors et il fait un peu froid.\nQue faites vous ?");
        Environnement JardinCombat = new Environnement("Jardin", ", Vous vous retrouvez dehors et il fait un peu froid. Un gardien de prison vous ordonne de retourner dans votre cellule\nQue faites vous ?");
        Environnement Foret = new Environnement("forêt", "");
        Environnement ForetCombat = new Environnement("Le nombre aléatoire a bien été choisis","");
        Environnement clotureGauche = new Environnement("en dehors de la clôture ","du côté gauche, c'est une bonne idée : fouillez la carte pour trouver des secrets.");

        //liste des Evennements
        Evennement mangerFleurs = new Evennement("<<oh yis the flowers>>");
        Evennement fuiteImpossible = new Evennement("Impossible de fuir. Pour avancer vous devez combattre");
        
        Bulk.afficherStats();
        System.out.println("\n***********\n");
        Chambre.afficherEnvironnement();

        for (;;) {

            System.out.println("\n***********\n");

            Bulk.verifierVie();

            if (Bulk.getX() == 0 && Bulk.getY() == 0) {

                System.out.print("Voici la liste des possibilités : \nsud, nord, manger, stats\n");

                String action_chambre = scanner.next();

                switch (action_chambre) {

                    case "xp" :
                        Bulk.modifXP(10);
                        break;

                    case "nord":

                        Bulk.Nord();
                        if (Gardien.getPV() > 0) {
                          JardinCombat.afficherEnvironnement();
                          Bulk.combat = true;
                           
                        }
                        else {
                          Jardin.afficherEnvironnement();
                        }
                        break;

                    case "manger":

                        if (Bulk.getPV() <= Bulk.getVieMax()) {
        
                          mangerFleurs.afficherEvennement();
                          Bulk.modifVie(10);
                          }
                        else{

                          Bulk.afficherVieMax();
                        }
                        break;

                    case "sud":

                        System.out.print("il n'y a rien au Sud, vous vous cognez contre le mur \n<<ouuuh my head>>\nc'est ainsi que la vie du grand Bulk Bogan s'acheva\n");
                        Bulk.modifVie(-1000);
                        break;

                    case "est":

                        System.out.print("il n'y a rien à l'Est, vous vous cognez contre le mur \n<<ouuuh my head>>\nc'est ainsi que la vie du grand Bulk Bogan s'acheva\n");
                        Bulk.modifVie(-1000);
                        break;

                    case "ouest":

                        System.out.print("il n'y a rien à l'Ouest, vous vous cognez contre le mur \n<<ouuuh my head>>\nc'est ainsi que la vie du grand Bulk Bogan s'acheva\n");
                        Bulk.modifVie(-1000);
                        break;

                    case "stats":

                        Bulk.afficherStats();
                        Bulk.afficherDebug();
                        break;

                    default:

                        System.out.print("Commande inconnue\n");
                        break;

                }
            }

            if (Bulk.getX() == 0 && Bulk.getY() == 1) {

                System.out.print("Voici la liste des possibilités : \nsud, nord, stats, frapper, queue, drugs\n");

                String action_jardin = scanner.next();

                if (Bulk.enCombat() == true) {
                  switch (action_jardin) {
                    case "nord":

                        fuiteImpossible.afficherEvennement();
                        break;

                    case "sud":

                        fuiteImpossible.afficherEvennement();
                        break;
                      
                    case "est":

                        fuiteImpossible.afficherEvennement();
                        break;
                      
                    case "ouest":

                        fuiteImpossible.afficherEvennement();
                        break;

                    case "frapper":
                        Gardien.modifVie(-(Bulk.getForce())-(Gardien.getArmure()));
                        Gardien.verifierVie(Bulk);
                        
                        if (Gardien.getPV() >= 0) {
                          Gardien.attaqueHasard();
                          Gardien.afficherStats();
                          Bulk.modifVie(-(Gardien.getForce())-(Bulk.getArmure()));
                          Gardien.forceReset();
                        }
                        break;

                    case "queue":
                        Bulk.queueDeBillard();
                        Gardien.modifVie(-(Bulk.getForce())-(Gardien.getArmure()));
                        Gardien.verifierVie(Bulk);
                        
                        if (Gardien.getPV() >= 0) {
                          Gardien.attaqueHasard();
                          Gardien.afficherStats();
                          Bulk.modifVie(-(Gardien.getForce())-(Bulk.getArmure()));
                          Gardien.forceReset();
                        }
                        break;

                    case "drugs":
                        Bulk.heal();
                        Gardien.attaqueHasard();
                        Gardien.afficherStats();
                        Bulk.modifVie(-(Gardien.getForce())-(Bulk.getArmure()));
                        Gardien.forceReset();
                        break;

                    case "stats":

                        Bulk.afficherStats();
                        Bulk.afficherDebug();
                        break;

                    default:

                        System.out.print("Commande inconnue\n");
                        break;
                    }
                    
                  }
                  

                if (Bulk.enCombat() == false){
                    switch (action_jardin) {

                      case "sud":
                        Bulk.Sud();
                        Chambre.afficherEnvironnement();
                        break;

                      case "nord":
                        Bulk.Nord();
                        defaut.rencontreHasard(3);
                        if(defaut.getType() != 0) {

                          Bulk.combat = true;
                          ForetCombat.afficherEnvironnement();
                        }
                        else{

                          Foret.afficherEnvironnement();
                        }
                        break;

                      case "est":
                        Bulk.Est();
                        clotureGauche.afficherEnvironnement();
                        break;

                      case "stats":
                        Bulk.afficherStats();
                        Bulk.afficherDebug();
                        break;

                      default:
                        System.out.print("Commande inconnue\n");
                        break;
                    }
                  }
                  
                }
                


            if (Bulk.getX() == 1 && Bulk.getY() == 1) {

              System.out.print("Voici la liste des possibilités : \nsud, nord, est, ouest, stats, ouvrir\n");

              String action_cloture_gauche = scanner.next();

              switch (action_cloture_gauche) {
                    case "ouest":
                  
                        System.out.print("il n'y a rien à l'Ouest, vous vous cognez contre le mur \n<<ouuuh my head>>\nc'est ainsi que la vie du grand Bulk Bogan s'acheva\n");
                        Bulk.modifVie(-1000);
                        break;

                    case "ouvrir":

                    default:
                        System.out.print("Commande inconnue\n");
                        break;
              }
            }
            
            if (Bulk.getX() == 0 && Bulk.getY() == 2) {
              
              System.out.print("Voici la liste des possibilités : \nsud, nord, stats\n");

              String action_foret = scanner.next();
                
                if (Bulk.enCombat() == true) {
                  
                  switch (action_foret) {
                    case "nord":

                        fuiteImpossible.afficherEvennement();
                        break;

                    case "sud":

                        fuiteImpossible.afficherEvennement();
                        break;

                    case "est":

                        fuiteImpossible.afficherEvennement();
                        break;
                      
                    case "ouest":

                        fuiteImpossible.afficherEvennement();
                        break;

                    case "frapper":
                        defaut.modifVie(-(Bulk.getForce())-(defaut.getArmure()));
                        defaut.verifierVie(Bulk);
                        
                        if (defaut.getPV() >= 0) {
                          defaut.attaqueHasard();
                          defaut.afficherStats();
                          Bulk.modifVie(-(defaut.getForce())-(Bulk.getArmure()));
                          defaut.forceReset();
                        }
                        break;

                    case "queue":
                        Bulk.queueDeBillard();
                        defaut.modifVie(-(Bulk.getForce())-(defaut.getArmure()));
                        defaut.verifierVie(Bulk);
                        
                        if (defaut.getPV() >= 0) {
                          defaut.attaqueHasard();
                          defaut.afficherStats();
                          Bulk.modifVie(-(defaut.getForce())-(Bulk.getArmure()));
                          defaut.forceReset();
                        }
                        break;

                    case "drugs":
                        Bulk.heal();
                        defaut.attaqueHasard();
                        defaut.afficherStats();
                        Bulk.modifVie(-(defaut.getForce())-(Bulk.getArmure()));
                        defaut.forceReset();
                        break;

                    case "stats":

                        Bulk.afficherStats();
                        Bulk.afficherDebug();
                        break;

                    default:

                        System.out.print("Commande inconnue\n");
                        break;
                    }
                  }

                if (Bulk.enCombat() == false){

                  switch (action_foret) {

                    case "sud":
                        Bulk.Sud();
                        Jardin.afficherEnvironnement();
                        break;

                    case "stats":
                        Bulk.afficherStats();
                        Bulk.afficherDebug();
                        break;

                    default:
                        System.out.print("Commande inconnue\n");
                        break;
                    }
                  }
                }
                
            }
        }
    }

