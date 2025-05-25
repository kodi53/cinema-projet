package film2;

import javax.swing.*;
import java.awt.*;

public class Admin  {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Admin::showAdminPanel);
    }

    static void showAdminPanel() {
        JFrame frame = new JFrame("Interface Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton btnFilms = new JButton("Gérer Films");
        JButton btnSeances = new JButton("Gérer Séances");
        JButton btnReservations = new JButton("Gérer Réservations");

        // Actions à définir
        btnFilms.addActionListener(e ->{
        	JFrame frame1 = new JFrame("Gérer films");
    	    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	    frame1.setSize(600, 650);
    	    frame1.setLocationRelativeTo(null); // centre la fenêtre
    	    frame1.setContentPane(new testadmin()); // ← ici on insère ton JPanel
    	    frame1.setVisible(true);
       
        }
);

        btnSeances.addActionListener(e -> { 
        	  JFrame frame2 = new JFrame("Gérer Séances");
        	    frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        	    frame2.setSize(600, 650);
        	    frame2.setLocationRelativeTo(null); // centre la fenêtre
        	    frame2.setContentPane(new GererSeance()); // ← ici on insère ton JPanel
        	    frame2.setVisible(true);
           
               
        });

        btnReservations.addActionListener(e -> {
        	JFrame frame3 = new JFrame("Gérer reservations");
    	    frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	    frame3.setSize(600, 650);
    	    frame3.setLocationRelativeTo(null); // centre la fenêtre
    	    frame3.setContentPane(new GererReservations()); // ← ici on insère ton JPanel
    	    frame3.setVisible(true);
       
           
        });

        panel.add(btnFilms);
        panel.add(btnSeances);
        panel.add(btnReservations);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

