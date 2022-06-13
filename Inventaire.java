import java.util.ArrayList;

public class Inventaire {


  private ArrayList<Inventaire> inventaire;
  private boolean aFleche;
  private boolean aQueue;

  public Inventaire() {

    inventaire = new ArrayList<Inventaire>();
  }

  public void ajouterObjet(Inventaire objet) {
        inventaire.add(objet);
    }

  

}