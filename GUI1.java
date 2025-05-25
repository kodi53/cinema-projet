package film2;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class GUI1 {

    static class Film {
        int id;
        String titre;
        String genre;
        String duree;
        int ageLimite;

        Film(int id, String titre, String genre, String duree, int ageLimite) {
            this.id = id;
            this.titre = titre;
            this.genre = genre;
            this.duree = duree;
            this.ageLimite = ageLimite;
        }
    }

    static class Seance {
        int id;
        int filmId;
        String date;
        String heure;
        String salle;
        double price;

        Seance(int id, int filmId, String date, String heure, String salle, double price) {
            this.id = id;
            this.filmId = filmId;
            this.date = date;
            this.heure = heure;
            this.salle = salle;
            this.price = price;        }
    }

    static Map<Integer, Film> films = new HashMap<>();
    static Map<Integer, Seance> seances = new HashMap<>();
    static Random random = new Random();

    public static void main(String[] args) {
    	  films.put(1, new Film(1, "Minecraft", "Aventure", "1h42min", 5));
          films.put(2, new Film(2, "Sonic 3", "Action", "1h39min",5));
          films.put(3, new Film(3, "Moana 2", "Aventure", "1h47min",5));
          films.put(4, new Film(4, "Smile 2", "Horreur", "1h49min",12));
          films.put(5, new Film(5, "Bad Boys", "Action", "1h53min",10));
          films.put(6, new Film(6, "Le Comte de Monte-Cristo", "Drame", "2h49min",12));
          films.put(7, new Film(7, "Deadpool&Wolverine", "Action", "2h07min",7));
          films.put(8, new Film(8, "Thunderbolts", "Action", "2h09min",10));
          films.put(9, new Film(9, "Sinners", "Horreur", "2h13min",16));

          seances.put(1, new Seance(1, 1, "16-05", "13:20", "A1", 600));
          seances.put(2, new Seance(2, 2, "17-05", "11:00", "B1", 500));
          seances.put(3, new Seance(3, 3, "18-05", "16:45", "C1", 600));
          seances.put(4, new Seance(4, 4, "17-05", "16:45", "A2", 500));
          seances.put(5, new Seance(5, 5, "16-05", "11:00", "B2", 700));
          seances.put(6, new Seance(6, 6, "17-05", "13:20", "A1", 700));
          seances.put(7, new Seance(7, 7, "16-05", "16:45", "B1", 600));
          seances.put(8, new Seance(8, 8, "18-05", "11:00", "C1", 700));
          seances.put(9, new Seance(9, 9, "18-05", "13:00", "B2", 500));


        SwingUtilities.invokeLater(() -> showHomePage(1));
    }

    static void showHomePage(int page) {
        JFrame frame = new JFrame("Cinéma - Séances disponibles - Page " + page);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
     

        JPanel filmsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        filmsPanel.setForeground(new Color(255, 230, 200)); 
        int start = (page - 1) * 3 + 1;

        for (int i = start; i < start + 3 && i <= seances.size(); i++) {
            Seance seance = seances.get(i);
            Film film = films.get(seance.filmId);

          
            JPanel card = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    GradientPaint gp = new GradientPaint(
                            0, 0, Color.RED,
                            getWidth(), getHeight(), Color.BLUE);
                    g2d.setPaint(gp);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            
            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 1));
            titlePanel.setOpaque(false); // pour laisser apparaître le fond dégradé
            JLabel titre = new JLabel("Titre: " + film.titre);
            titre.setFont(new Font("Arial", Font.BOLD, 20));
            titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
            
           
            JPanel infosPanel = new JPanel(new GridLayout (0, 1, 0, 0));

   
            infosPanel.setOpaque(false);
            JLabel genre = new JLabel("Genre: " + film.genre);
            JLabel duree = new JLabel("Durée: " + film.duree  + "     Age limit:" + film.ageLimite);
            JLabel date  = new JLabel("Date: " + seance.date  + "     Heure: " + seance.heure);                  
            JLabel price = new JLabel("prix: " + seance.price + "     Salle: " + seance.salle);
          
            JLabel num = new JLabel( "N°" + film.id);
            num.setAlignmentX(Component.CENTER_ALIGNMENT);
            num.setForeground(Color.WHITE);
           
            num.setFont(new Font("Arial", Font.BOLD, 20));

         // image factice (vide)
            String imagePath = "D:/projetCINEMA/" + film.id + ".jpg";
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaled = icon.getImage().getScaledInstance(250, 370, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
          
            titlePanel.add(titre);
            infosPanel.add(genre);
            infosPanel.add(duree);
            infosPanel.add(date);
            infosPanel.add(price);
            card.add(titlePanel);
            card.add(infosPanel);
            card.add(Box.createRigidArea(new Dimension(0, 5)));
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.add(imageLabel);
            card.add(num);
            
            filmsPanel.add(card);
         

    
        }

        JPanel navigationPanel = new JPanel(new BorderLayout());
        JButton prev = new JButton("← Précédent");
        JButton next = new JButton("Suivant →");
        JButton reserverr = new JButton("Réserver");
        JButton conAdmin = new JButton("gérer comme Admin");
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel adroitePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       
        

        if (page > 1) {
            prev.addActionListener(e -> {
                frame.dispose();
                showHomePage(page - 1);
            });
            centerPanel.add(prev);
        }

        if ((page * 3) < seances.size()) {
            next.addActionListener(e -> {
                frame.dispose();
                showHomePage(page + 1);
            });
            centerPanel.add(next);
        }

        reserverr.addActionListener(e -> {
            frame.dispose();
            showReservationPage();
        });
        centerPanel.add(reserverr);
        adroitePanel.add(conAdmin);
        navigationPanel.add(centerPanel, BorderLayout.CENTER);
        navigationPanel.add(adroitePanel, BorderLayout.EAST);
        
        conAdmin.addActionListener(e -> {
            frame.dispose();
            showLoginWindow();
        });
        mainPanel.add(filmsPanel, BorderLayout.CENTER);
        mainPanel.add(navigationPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
      
    public static void showLoginWindow() {
    	JFrame frame = new JFrame("Connexion Admin");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(400, 200);
    	frame.setLayout(new BorderLayout());

    	JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
    	JTextField userField = new JTextField();
    	JPasswordField passField = new JPasswordField();
    	panel.add(new JLabel("Nom d'utilisateur:"));
    	panel.add(userField);
    	panel.add(new JLabel("Mot de passe:"));
    	panel.add(passField);

    	JButton loginBtn = new JButton("Connexion");
    	JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
    	messageLabel.setForeground(Color.RED);
    	 loginBtn.addActionListener(e -> {
             String user = userField.getText();
             String password = new String(passField.getPassword());

             if (user.equals("admin") && password.equals("5555")) {
                 frame.dispose(); // fermer la fenêtre de login
             	 Admin.showAdminPanel(); 
       
           
             } else {
                 messageLabel.setText("nom d'utilisateur ou mot de passe incorrect !");
             }
         });
    	 
    	JPanel centerPanel = new JPanel();
    	centerPanel.add(loginBtn); 

    	frame.add(panel, BorderLayout.NORTH);
    	frame.add(centerPanel, BorderLayout.CENTER);
    	frame.add(messageLabel, BorderLayout.SOUTH);

    	frame.setLocationRelativeTo(null); // centre la fenêtre à l'écran
    	frame.setVisible(true);

    }
    
   
    
    static void showReservationPage() {
        JFrame frame = new JFrame("Réservation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField numeroField = new JTextField();
        JTextField numcarteField = new JTextField();
        JComboBox<Integer> idSeanceBox = new JComboBox<>();
        for (int i = 1; i <= 9; i++) {
            idSeanceBox.addItem(i);
        }

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultArea);

        panel.add(new JLabel("Nom:"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom:"));
        panel.add(prenomField);
        panel.add(new JLabel("Âge:"));
        panel.add(ageField);
        panel.add(new JLabel("Téléphone:"));
        panel.add(numeroField);
        panel.add(new JLabel("N° Séance (1-9):"));
        panel.add(idSeanceBox);
        panel.add(new JLabel("Numéro de carte bancaire"));
        panel.add(numcarteField);
        
     // Ajoute ce champ global dans showReservationPage
        final int[] nbPlaces = {0}; // stocke le nombre validé
        final boolean[] pretAPayer = {false}; // vérifie si le bouton PAYER est autorisé
      
        JButton reserver = new JButton("RÉSERVER");
        reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int age = Integer.parseInt(ageField.getText());
                    int idSeance = (Integer) idSeanceBox.getSelectedItem();

                    if (!seances.containsKey(idSeance)) {
                        resultArea.setText("ID de séance invalide.");
                        return;
                    }

                    Seance seance = seances.get(idSeance);
                    Film film = films.get(seance.filmId);
                    int ageLimite = film.ageLimite; 

                    if (age < ageLimite) {
                        resultArea.setText("Vous n’avez pas l’âge requis pour ce film.");
                        return;
                    }

                    String placesStr = JOptionPane.showInputDialog("Combien de places voulez-vous réserver ?");
                    int places = Integer.parseInt(placesStr);

                    for (int i = 1; i <= places; i++) {
                        int rep = JOptionPane.showConfirmDialog(null,
                            "La personne " + i + " a-t-elle plus de " + ageLimite + " ans ?",
                            "Vérification d’âge", JOptionPane.YES_NO_OPTION);
                        if (rep != JOptionPane.YES_OPTION) {
                            resultArea.setText("Vous ne pouvez pas réserver : une personne n'a pas l'âge requis.");
                            return;
                        }
                    }
                    
                   

                 
                    double prixUnitaire = seance.price ; // modifie selon tes tarifs
                    double total = prixUnitaire * places;
                    resultArea.setText("Prix total : " + total + "DA\nVeuillez entrer le numéro de carte puis cliquer sur PAYER.");
                    
                

                    nbPlaces[0] = places;
                    pretAPayer[0] = true;

                } catch (Exception ex) {
                    resultArea.setText("Erreur : veuillez entrer des valeurs valides.");
                }
            }
        });
     
        JButton choisirSiegeButton = new JButton("Choisir siège");
        choisirSiegeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SeatMatrix  seatMatrix = new SeatMatrix(5, 7);
            	 seatMatrix.createSeatMatrixGUI();
            }
        });
        panel.add(choisirSiegeButton);
        panel.add(reserver);
       
      
        JButton payer = new JButton("PAYER");
        panel.add(payer);
        payer.addActionListener(new ActionListener() {  	 
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	    
                try {
                    String nom = nomField.getText();
                    String prenom = prenomField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    int numero = Integer.parseInt(numeroField.getText());
                    int idSeance = (Integer) idSeanceBox.getSelectedItem();
                    int numcarte = Integer.parseInt(numcarteField.getText());
                       
                    if (!seances.containsKey(idSeance)) {
                        resultArea.setText("ID de séance invalide.");
                        return;
                    }

                    // ==== 1. Créer et insérer le client ====
                    int clientId = new Random().nextInt(100); // ou auto-incrément en base
                    Client c = new Client(clientId, nom, prenom, age, numero, numcarte);
                    ClientDAO.ajouterClient(c); // insère dans la base

                    // ==== 2. Créer et insérer la réservation ====
                    for (int i = 0; i < nbPlaces[0]; i++) {
                    int reservationId = new Random().nextInt(100); 
                    int siege = new Random().nextInt(35); 
                    Reservation r = new Reservation(reservationId, clientId, idSeance, siege);
                    ReservationDAO.ajouterReservation(r); // insère dans la base
                    }
                    Seance seance = seances.get(idSeance);
                    Film film = films.get(seance.filmId);
                

                    String message = "Merci d'avoir réservé, " + prenom + " " + nom + " !\n" +
                    		"Vous avez réservé " + nbPlaces[0] + " places pour :\n" +
                    		"Film : " + film.titre + " le " + seance.date + " à " + seance.heure + "\n" +
                            "Salle : " + seance.salle + "\n un E-billet a été généré.";
                           
                    pretAPayer[0] = false;
                    nbPlaces[0] = 0;

                    resultArea.setText(message);
                } catch (Exception ex) {
                    resultArea.setText("Veuillez remplir tous les champs correctement.");
                }
            }
        });

        
       
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}