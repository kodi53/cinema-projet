package film2;


public class film {
    private int id;
    private String titre;
    private String genre;
    private String duree;
    private int ageLimite;
   

  

    // Constructeur pour modification 
    public film(int id, String titre, String genre, String duree, int ageLimite) {
        this.id = id;
        this.titre = titre;
        this.genre = genre;
        this.duree = duree;
        this.ageLimite = ageLimite;
      
    }

    // Getters
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getGenre() { return genre; }
    public String getDuree() { return duree; }
    public int getAgeLimite() { return ageLimite; }
   

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setDuree(String duree) { this.duree = duree; }
    public void setAgeLimite(int ageLimite) { this.ageLimite = ageLimite; }
   
}

