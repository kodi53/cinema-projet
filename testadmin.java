package film2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class testadmin extends JPanel {
    private JTextField tfTitre, tfGenre, tfDuree, tfAge,tfid;
    private JTable tableFilms;
    private DefaultTableModel model;

  
	   public testadmin() {
        setLayout(new BorderLayout());

        // ==== FORMULAIRE ====
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        tfid = new JTextField();
        tfTitre = new JTextField();
        tfGenre = new JTextField();
        tfDuree = new JTextField();
        tfAge = new JTextField();
        
        formPanel.add(new JLabel("ID du film :"));
        formPanel.add(tfid);
        formPanel.add(new JLabel("Titre :"));
        formPanel.add(tfTitre);
        formPanel.add(new JLabel("Genre :"));
        formPanel.add(tfGenre);
        formPanel.add(new JLabel("Durée (min) :"));
        formPanel.add(tfDuree);
        formPanel.add(new JLabel("Âge limite :"));
        formPanel.add(tfAge);

        // ==== BOUTONS ====
        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        btnAjouter.addActionListener(e -> ajouterFilm());
        btnModifier.addActionListener(e -> modifierFilm());
        btnSupprimer.addActionListener(e -> supprimerFilm());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);

        // ==== TABLE ====
        String[] colonnes = {"ID", "Titre", "Genre", "Durée", "Âge"};
        model = new DefaultTableModel(colonnes, 0);
        tableFilms = new JTable(model);
        tableFilms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableFilms.getSelectionModel().addListSelectionListener(e -> remplirChampsDepuisTable());

        JScrollPane scrollTable = new JScrollPane(tableFilms);

        // ==== AJOUT DANS PANEL ====
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollTable, BorderLayout.SOUTH);

        chargerFilms(); // afficher les films au démarrage
    }

    // Charger films depuis la base
    private void chargerFilms() {
        model.setRowCount(0); // vider la table
        List<film> films = FilmDAO.getAllFilms(); // méthode DAO à créer
        for (film f : films) {
            model.addRow(new Object[]{
                    f.getId(), f.getTitre(), f.getGenre(), f.getDuree(),
                    f.getAgeLimite(), 
            });
        }
    }

    private void ajouterFilm() {
    	int id =  Integer.parseInt(tfid.getText());
        String titre = tfTitre.getText();
        String genre = tfGenre.getText();
        String duree = tfDuree.getText();
        int age = Integer.parseInt(tfAge.getText());

        film film = new film(id,titre, genre, duree, age);
        FilmDAO.ajouterFilm(film);
        chargerFilms();
        viderChamps();
    }

    private void modifierFilm() {
    	try {
            int idRecherche = Integer.parseInt(tfid.getText());
            for (int i = 0; i < model.getRowCount(); i++) {
                int idLigne = (int) model.getValueAt(i, 0);
                if (idLigne == idRecherche) {
                    tableFilms.setRowSelectionInterval(i, i); // sélectionne la ligne
                    String titre = tfTitre.getText();
                    String genre = tfGenre.getText();
                    String duree = tfDuree.getText();
                    int age = Integer.parseInt(tfAge.getText());

                    film film = new film(idLigne, titre, genre, duree, age);
                    FilmDAO.modifierFilm(film);
                    chargerFilms();
                    viderChamps();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "ID non trouvé dans la table.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide.");
        }
           
    }

    private void supprimerFilm() {
    	try {
            int idRecherche = Integer.parseInt(tfid.getText());
            for (int i = 0; i < model.getRowCount(); i++) {
                int idLigne = (int) model.getValueAt(i, 0);
                if (idLigne == idRecherche) {
                    
                	 FilmDAO.supprimerFilm(idLigne);
                    chargerFilms();
                    viderChamps();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "ID non trouvé dans la table.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide.");
        }}

    private void remplirChampsDepuisTable() {
        int row = tableFilms.getSelectedRow();
        if (row >= 0) {
             tfid.setText(model.getValueAt(row, 0).toString());
            tfTitre.setText(model.getValueAt(row, 1).toString());
            tfGenre.setText(model.getValueAt(row, 2).toString());
            tfDuree.setText(model.getValueAt(row, 3).toString());
            tfAge.setText(model.getValueAt(row, 4).toString());
     
        }
    }

    private void viderChamps() {
    	tfid.setText("");
        tfTitre.setText("");
        tfGenre.setText("");
        tfDuree.setText("");
        tfAge.setText("");
    
    } 


public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Gestion des Films");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new testadmin());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    });
}
}
