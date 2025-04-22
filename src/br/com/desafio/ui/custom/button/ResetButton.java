package br.com.desafio.ui.custom.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ResetButton extends JButton {

    public ResetButton(final ActionListener actionListener) {
        this.setText("Reiniciar Jogo");

        // Ajustando o tamanho do botão
        this.setPreferredSize(new Dimension(140, 30)); // Tamanho adequado

        // Definindo a cor de fundo e a cor do texto
        this.setBackground(Color.RED); // Cor de fundo vermelha
        this.setForeground(Color.BLACK); // Cor do texto branca

        // Fonte do botão
        this.setFont(new Font("Arial", Font.BOLD, 12)); // Fonte ajustada

        // Removendo o foco padrão (não exibe o contorno de foco)
        this.setFocusPainted(false);

        // Adicionando bordas arredondadas
        this.setBorder(new RoundedBorder(15, Color.RED));

        // Adicionando o ActionListener
        this.addActionListener(actionListener);
    }
}
