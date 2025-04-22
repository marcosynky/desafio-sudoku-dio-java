package br.com.desafio.ui.custom.input;

import br.com.desafio.model.Space;
import br.com.desafio.service.EventEnum;
import br.com.desafio.service.EventListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static br.com.desafio.service.EventEnum.CLEAR_SPACE;
import static java.awt.Font.PLAIN;

public class NumberText extends JTextField implements EventListener {

    private final Space space;

    // Construtor
    public NumberText(final Space space) {
        this.space = space;
        var dimension = new Dimension(50, 50);
        this.setPreferredSize(dimension);  // Melhor usar apenas setPreferredSize
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!space.isFixed());


        if (space.isFixed()) { // Se for fixo
            this.setText(space.getActual().toString());
        }

        // Adiciona um listener ao campo de texto
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override // Implementa os métodos do DocumentListener
            public void insertUpdate(final DocumentEvent e) {

                changeSpace();
            }

            @Override // Implementa os métodos do DocumentListener
            public void removeUpdate(final DocumentEvent e) {

                changeSpace();
            }

            @Override // Implementa os métodos do DocumentListener
            public void changedUpdate(final DocumentEvent e) {

                changeSpace();
            }

            private void changeSpace() { // Muda o espaço
                String text = getText();
                if (text.isEmpty()) {
                    space.clearSpace();
                } else {
                    try {

                        int value = Integer.parseInt(text);
                        space.setActual(value);
                    } catch (NumberFormatException e) {

                        setText("");
                    }
                }
            }
        });
    }

    @Override // Implementa o update
    public void update(final EventEnum eventType) {
        if (eventType.equals(CLEAR_SPACE) && this.isEnabled()) {
            this.setText("");
        }
    }
}
