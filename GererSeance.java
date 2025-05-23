package film2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GererSeance extends JPanel {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestion des Séances");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new GererSeance());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    private JTable tableSeances;
    private DefaultTableModel model;
    private JTextField tfId, tfFilmId, tfHeure, tfDate, tfSalle, tfPrice;
    private SeanceDAO seanceDAO;

    public GererSeance() {
   

        setLayout(new BorderLayout());

        // Création de la table
        String[] colonnes = {"ID", "film_id", "date", "heure", "salle", "price"};
        model = new DefaultTableModel(colonnes, 0);
        tableSeances = new JTable(model);
        tableSeances.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSeances.getSelectionModel().addListSelectionListener(e -> remplirChampsDepuisTable());
        JScrollPane scrollPane = new JScrollPane(tableSeances);

        // Formulaire
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        tfId = new JTextField();
        tfFilmId = new JTextField();
        tfHeure = new JTextField();
        tfDate = new JTextField();
        tfSalle = new JTextField();
        tfPrice = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(tfId);
        formPanel.add(new JLabel("Film ID:"));
        formPanel.add(tfFilmId);
        formPanel.add(new JLabel("date:"));
        formPanel.add(tfDate);
        formPanel.add(new JLabel("heure:"));
        formPanel.add(tfHeure);
        formPanel.add(new JLabel("Salle:"));
        formPanel.add(tfSalle);
        formPanel.add(new JLabel("Prix:"));
        formPanel.add(tfPrice);

        // Boutons
        JButton btnAjouter = new JButton("Ajouter");
        JButton btnSupprimer = new JButton("Supprimer");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnSupprimer);

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Événements
        btnAjouter.addActionListener(e -> ajouterSeance());
        btnSupprimer.addActionListener(e -> supprimerSeance());
        tableSeances.getSelectionModel().addListSelectionListener(e -> remplirChampsDepuisTable());

        chargerSeances();
    }

    private void chargerSeances() {
        model.setRowCount(0); // vider la table
        List<Seance> seances = seanceDAO.getAllSeances();
        for (Seance s : seances) {
            model.addRow(new Object[]{
                s.getId(), s.getFilmId(), s.getHeure(), s.getDate(), s.getSalle(), s.getPrice()
            });
        }
    }

    private void ajouterSeance() {
            int id = Integer.parseInt(tfId.getText());
            int filmId = Integer.parseInt(tfFilmId.getText());
            String heure = tfHeure.getText();
            String date = tfDate.getText();
            String salle = tfSalle.getText();
            double price = Double.parseDouble(tfPrice.getText());

            Seance seance = new Seance(id, filmId, date, heure, salle, price);
            seanceDAO.addSeance(seance);
            chargerSeances();
            viderChamps();
           
    }

    private void supprimerSeance() {
        try {
            int idRecherche = Integer.parseInt(tfId.getText());
            for (int i = 0; i < model.getRowCount(); i++) {
                int idLigne = (int) model.getValueAt(i, 0);
                if (idLigne == idRecherche) {
                    seanceDAO.deleteSeance(idLigne);
                    chargerSeances();
                    viderChamps();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "ID non trouvé dans la table.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide.");
        }
    }

    private void remplirChampsDepuisTable() {
        int row = tableSeances.getSelectedRow();
        if (row >= 0) {
            tfId.setText(model.getValueAt(row, 0).toString());
            tfFilmId.setText(model.getValueAt(row, 1).toString());
            tfDate.setText(model.getValueAt(row, 2).toString());
            tfHeure.setText(model.getValueAt(row, 3).toString());
            tfSalle.setText(model.getValueAt(row, 4).toString());
            tfPrice.setText(model.getValueAt(row, 5).toString());
        }
    }

    private void viderChamps() {
        tfId.setText("");
        tfFilmId.setText("");
        tfHeure.setText("");
        tfDate.setText("");
        tfSalle.setText("");
        tfPrice.setText("");
    }

    
}
