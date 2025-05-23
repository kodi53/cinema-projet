package film2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {

    public static void ajouterFilm(film film) {
        String sql = "INSERT INTO movies ( id, titre, genre, duree, age_limit) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	 stmt.setInt(1, film.getId());
            stmt.setString(2, film.getTitre());
            stmt.setString(3, film.getGenre());
            stmt.setString(4, film.getDuree());
            stmt.setInt(5, film.getAgeLimite());
         

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifierFilm(film film) {
        String sql = "UPDATE movies SET titre=?, genre=?, duree=?, age_limit=? WHERE id=?";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
        
            stmt.setString(1, film.getTitre());
            stmt.setString(2, film.getGenre());
            stmt.setString(3, film.getDuree());
            stmt.setInt(4, film.getAgeLimite());
            stmt.setInt(5, film.getId());
           

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerFilm(int id) {
        String sql = "DELETE FROM movies WHERE id=?";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<film> getAllFilms() {
        List<film> liste = new ArrayList<>();
        String sql = "SELECT * FROM movies";
        try (Connection conn = Connexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                film f = new film(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("genre"),
                    rs.getString("duree"),
                    rs.getInt("age_limit")
               
                );
                liste.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}
