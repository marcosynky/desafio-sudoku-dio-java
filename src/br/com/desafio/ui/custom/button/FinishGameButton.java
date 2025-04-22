package br.com.desafio.ui.custom.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
 // Classe FinishGameButton
public class FinishGameButton extends JButton {
    // Construtor
    public FinishGameButton(final ActionListener actionListener) {
        this.setText("Concluir");

        // Ajustando o tamanho do botão
        this.setPreferredSize(new Dimension(140, 30)); // Tamanho adequado

        // Definindo a cor de fundo e a cor do texto
        this.setBackground(Color.GREEN); // Cor de fundo verde
        this.setForeground(Color.BLACK); // Cor do texto branca

        // Fonte do botão
        this.setFont(new Font("Arial", Font.BOLD, 12)); // Fonte ajustada

        // Removendo o foco padrão (não exibe o contorno de foco)
        this.setFocusPainted(false);

        // Adicionando bordas arredondadas
        this.setBorder(new RoundedBorder(15, Color.GREEN));

        // Adicionando o ActionListener
        this.addActionListener(actionListener);
    }
}
