package film2;

public class Reservation {
    private int id;
    private int idClient;
    private int siege;
    private int idSeance;

    public Reservation(int id, int idClient, int idSeance, int siege) {
        this.id = id;
        this.idClient = idClient;
        this.siege = siege;
        this.idSeance = idSeance ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getSiege() {
        return siege;
    }

    public void setSiege(int siege) {
        this.siege = siege;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }
}

