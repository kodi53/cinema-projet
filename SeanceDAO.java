package film2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {
    

    // 1. Lister toutes les séances
    public static List<Seance> getAllSeances() {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM seance";

        try (Connection conn = Connexion.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                Seance seance = new Seance(
                    rs.getInt("id"),
                    rs.getInt("film_id"),
                    rs.getString("heure"),
                    rs.getString("date"),
                    rs.getString("salle"),
                    rs.getDouble("price")
                );
                seances.add(seance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }

    // 2. Ajouter une séance
    public static void addSeance(Seance seance) {
        String sql = "INSERT INTO seance (id, film_id, date, heure, salle, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connexion.getConnection();
        		 PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
        	stmt.setInt(1, seance.getId());    	
            stmt.setInt(2, seance.getFilmId());
            stmt.setString(3, seance.getDate());
            stmt.setString(4, seance.getHeure());
            stmt.setString(5, seance.getSalle());
            stmt.setDouble(6, seance.getPrice());

              stmt.executeUpdate();
          
        } catch (SQLException e) {
            e.printStackTrace();
        
        }
    }

    // 3. Supprimer une séance par ID
    public static void deleteSeance(int Id) {
        String sql = "DELETE FROM seance WHERE id = ?";

        try (Connection conn = Connexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Id);
             stmt.executeUpdate();
         
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
    }

    // 4. Lister les séances par film ID (ta méthode initiale)
    public List<Seance> getSeancesByFilmId(int filmId) {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM seance WHERE film_id = ?";

        try (Connection conn = Connexion.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
        	
            while (rs.next()) {
                Seance seance = new Seance(
                    rs.getInt("id"),
                    rs.getInt("film_id"),
                    rs.getString("date"),
                    rs.getString("heure"),
                    rs.getString("salle"),
                    rs.getDouble("price")
                );
                seances.add(seance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }
}
