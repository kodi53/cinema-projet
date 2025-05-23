package film2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public static void ajouterClient(Client client) {
        String sql = "INSERT INTO client (id, nom, prenom, numtel, numcarte) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, client.getId());
            stmt.setString(2, client.getNom());
            stmt.setString(3, client.getPrenom());
            stmt.setInt(4, client.getNumTel());
            stmt.setInt(5, client.getNumCarte());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Client> getAllClients() {
        List<Client> liste = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = Connexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client c = new Client(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getInt("numtel"),
                    rs.getInt("numcarte")
                );
                liste.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}
