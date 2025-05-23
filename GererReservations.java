package film2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GererReservations extends JPanel {
    private JTextField tfId, tfNom, tfPrenom, tfNumTel, tfNumCarte;
    private JTable tableClients;
    private DefaultTableModel model;

    public GererReservations() {
        setLayout(new BorderLayout());

        // ==== FORMULAIRE CLIENT ====
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        tfId = new JTextField();
        tfNom = new JTextField();
        tfPrenom = new JTextField();
        tfNumTel = new JTextField();
        tfNumCarte = new JTextField();

        formPanel.add(new JLabel("ID Client :"));
        formPanel.add(tfId);
        formPanel.add(new JLabel("Nom :"));
        formPanel.add(tfNom);
        formPanel.add(new JLabel("Prénom :"));
        formPanel.add(tfPrenom);
        formPanel.add(new JLabel("Numéro Tel :"));
        formPanel.add(tfNumTel);
        formPanel.add(new JLabel("Numéro Carte :"));
        formPanel.add(tfNumCarte);

        // ==== BOUTONS ====
        JButton btnAjouterClient = new JButton("Ajouter Client");
        JButton btnVoirReservations = new JButton("Voir les Réservations");
        JButton btnSupprimerReservation = new JButton("Supprimer Réservation");

        btnAjouterClient.addActionListener(e -> ajouterClient());
        btnVoirReservations.addActionListener(e -> voirReservations());
        btnSupprimerReservation.addActionListener(e -> supprimerReservation());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAjouterClient);
        buttonPanel.add(btnVoirReservations);
        buttonPanel.add(btnSupprimerReservation);

        // ==== TABLE CLIENTS ====
        String[] colonnes = {"ID", "Nom", "Prénom", "Num Tel", "Num Carte"};
        model = new DefaultTableModel(colonnes, 0);
        tableClients = new JTable(model);
        tableClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableClients.getSelectionModel().addListSelectionListener(e -> remplirChampsDepuisTable());

        JScrollPane scrollTable = new JScrollPane(tableClients);

        // ==== AJOUT PANELS ====
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollTable, BorderLayout.SOUTH);

        chargerClients(); // affichage au démarrage
    }

    private void chargerClients() {
        model.setRowCount(0); // vider
        List<Client> clients = ClientDAO.getAllClients();
        for (Client c : clients) {
            model.addRow(new Object[]{
                c.getId(), c.getNom(), c.getPrenom(), c.getNumTel(), c.getNumCarte()
            });
        }
    }

    private void ajouterClient() {
        try {
            int id = Integer.parseInt(tfId.getText());
            String nom = tfNom.getText();
            String prenom = tfPrenom.getText();
            int tel = Integer.parseInt(tfNumTel.getText());
            int carte = Integer.parseInt( tfNumCarte.getText());

            Client c = new Client(id, nom, prenom, tel, carte);
            ClientDAO.ajouterClient(c);
            chargerClients();
            viderChamps();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide.");
        }
    }

    private void voirReservations() {
        List<Reservation> reservations = ReservationDAO.getAllReservations();
        StringBuilder sb = new StringBuilder();
        for (Reservation r : reservations) {
            sb.append("ID: ").append(r.getId())
              .append(", Client ID: ").append(r.getIdClient())
              .append(", Séance ID: ").append(r.getIdSeance())
              .append(", Siége: ").append(r.getSiege()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Aucune réservation.");
    }

    private void supprimerReservation() {
        String input = JOptionPane.showInputDialog(this, "Entrez l'ID de la réservation à supprimer :");
        if (input != null && !input.isEmpty()) {
            try {
                int id = Integer.parseInt(input);
                ReservationDAO.supprimerReservation(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID invalide.");
            }
        }
    }

    private void remplirChampsDepuisTable() {
        int row = tableClients.getSelectedRow();
        if (row >= 0) {
            tfId.setText(model.getValueAt(row, 0).toString());
            tfNom.setText(model.getValueAt(row, 1).toString());
            tfPrenom.setText(model.getValueAt(row, 2).toString());
            tfNumTel.setText(model.getValueAt(row, 3).toString());
            tfNumCarte.setText(model.getValueAt(row, 4).toString());
        }
    }

    private void viderChamps() {
        tfId.setText("");
        tfNom.setText("");
        tfPrenom.setText("");
        tfNumTel.setText("");
        tfNumCarte.setText("");
    }
   
        public static void main(String[] args) {
        	
            JFrame frame = new JFrame("Gestion des Réservations");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new GererReservations());
            frame.setVisible(true);
        	
        }
    
}

