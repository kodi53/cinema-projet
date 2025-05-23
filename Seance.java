package film2;


public class Seance {
    private int id;
    private int filmId;
    private String date;
    private String heure;
    private String salle;
    private double price;

   

    // Constructeur avec id (pour lecture/modification)
    public Seance(int id, int filmId, String date,String heure, String salle, double price) {
        this.id = id;
        this.filmId = filmId;
        this.heure = heure;
        this.date = date;
        this.salle = salle;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
