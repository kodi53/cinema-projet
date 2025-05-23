package film2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
     
	public static void ajouterReservation(Reservation reservation) {
	    String sql = "INSERT INTO reservation (id, id_client, id_seance, siege) VALUES (?, ?, ?, ?)";
	    try (Connection conn = Connexion.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, reservation.getId());
	        stmt.setInt(2, reservation.getIdClient());
	        stmt.setInt(3, reservation.getIdSeance());
	        stmt.setInt(4, reservation.getSiege());
	    

	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    public static void supprimerReservation(int id) {
        String sql = "DELETE FROM reservation WHERE id=?";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Reservation> getAllReservations() {
        List<Reservation> liste = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Connection conn = Connexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reservation r = new Reservation(
                    rs.getInt("id"),
                    rs.getInt("id_client"),
                    rs.getInt("id_seance"),
                    rs.getInt("siege")
                );
                liste.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}
