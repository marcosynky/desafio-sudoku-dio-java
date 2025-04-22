package br.com.desafio.ui.custom.penel;

import br.com.desafio.ui.custom.input.NumberText;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import static java.awt.Color.black;

public class SudokuSector extends JPanel {
   // private final List<NumberText> textFields;
    public SudokuSector(final List<NumberText> textFields) {

        var dimension = new Dimension(170, 170);
        this.setPreferredSize(dimension);

        this.setLayout(new GridLayout(3, 3)); // 3 linhas e 3 colunas

        this.setBorder(new LineBorder(black, 2, true));


        textFields.forEach(this::add);// Adiciona os textFields ao SudokuSector


        this.setVisible(true); // Exibe o SudokuSector
    }
}
