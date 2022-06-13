public class Environnement {
    
    private String nom; 
    private String description;
    
    public Environnement(){
        nom = "-  -";
        description = "-  -";
    }

    public Environnement(String nom, String description) {

        this.nom = nom;
        this.description = description;
        
    }
    
    public void afficherEnvironnement() {
    
        System.out.println("Vous vous situez dans un " + nom + description);
    } 
}
