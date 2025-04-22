package br.com.desafio.ui.custom.frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    //private final Dimension dimension = new Dimension(600, 600);
    public MainFrame(final Dimension dimension, final JPanel mainPenel) {
        super("Sudoku");
        this.setSize(dimension); // Tamanho adequado
        this.setPreferredSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(mainPenel);
    }
}