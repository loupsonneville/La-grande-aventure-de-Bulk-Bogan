public class Evennement {
    
    private String description;
    
    public Evennement(){
        description = "-  -";
    }

    public Evennement(String description) {

        this.description = description;
        
    }
    
    public void afficherEvennement() {
    
        System.out.println("\n" + description);
    } 
}
