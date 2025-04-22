package br.com.desafio.ui.custom.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonCheckGamesStatusButton extends JButton { // Botão para verificar o status do jogo
    // Construtor
    public ButtonCheckGamesStatusButton(final ActionListener actionListener) {
        this.setText("Verificar jogo");

        // Ajustando o tamanho do botão
        this.setPreferredSize(new Dimension(140, 30)); // Tamanho ajustado

        // Definindo a cor de fundo e a cor do texto
        this.setBackground(Color.YELLOW); // Cor de fundo amarela
        this.setForeground(Color.BLACK); // Cor do texto preta

        // Fonte do botão
        this.setFont(new Font("Arial", Font.BOLD, 12)); // Ajuste no tamanho da fonte

        // Removendo o foco padrão (não exibe o contorno de foco)
        this.setFocusPainted(false);

        // Adicionando bordas arredondadas
        this.setBorder(new RoundedBorder(10, Color.YELLOW));// Bordas arredondadas

        // Adicionando o ActionListener
        this.addActionListener(actionListener);
    }
}
