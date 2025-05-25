package film2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SeatMatrix {
    private boolean[][] seatStatus;
    private int rows;
    private int cols;

    public SeatMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        seatStatus = new boolean[rows][cols];
    }

    public  void createSeatMatrixGUI() {
        JFrame frame = new JFrame("Sélection des sièges");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel seatPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
        seatPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JButton seatButton = new JButton((row + 1) + "-" + (col + 1));
                seatButton.setPreferredSize(new Dimension(50, 50));
                updateSeatButtonAppearance(seatButton, seatStatus[row][col]);

                final int currentRow = row;
                final int currentCol = col;

                seatButton.addActionListener(e -> {
                    seatStatus[currentRow][currentCol] = !seatStatus[currentRow][currentCol];
                    updateSeatButtonAppearance(seatButton, seatStatus[currentRow][currentCol]);
                });

                seatPanel.add(seatButton);
            }
        }

        JButton confirmButton = new JButton("Confirmer la sélection");
        confirmButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Sièges sélectionnés avec succès!");
            frame.dispose();
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(confirmButton);

        mainPanel.add(seatPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void updateSeatButtonAppearance(JButton button, boolean isReserved) {
        if (isReserved) {
            button.setBackground(Color.RED);
            button.setForeground(Color.WHITE);
            button.setText("X");
        } else {
            button.setBackground(Color.GREEN);
            button.setForeground(Color.BLACK);
            if (button.getText().equals("X")) {
                button.setText("Libre");
            }
        }
    }
}


