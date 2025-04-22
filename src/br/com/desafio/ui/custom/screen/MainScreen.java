package br.com.desafio.ui.custom.screen;

import br.com.desafio.model.Space;

import br.com.desafio.service.BoardServices;
import br.com.desafio.service.EventListener;

import br.com.desafio.service.NotifierServices;
import br.com.desafio.ui.custom.button.ButtonCheckGamesStatusButton;
import br.com.desafio.ui.custom.button.FinishGameButton;
import br.com.desafio.ui.custom.button.ResetButton;
import br.com.desafio.ui.custom.frame.MainFrame;
import br.com.desafio.ui.custom.input.NumberText;
import br.com.desafio.ui.custom.penel.MainPenel;
import br.com.desafio.ui.custom.penel.SudokuSector;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static br.com.desafio.service.EventEnum.CLEAR_SPACE;
import static java.util.Arrays.stream;
import static javax.swing.JOptionPane.*;


// src/br/com/desafio/ui/custom/screen/MainScreen.java
public class MainScreen {
    private final static Dimension dimension = new Dimension(600, 600); // Defina o tamanho desejado

    private final BoardServices boardService; // Injeta o BoardServices
    private final NotifierServices notifierService; // Injeta o NotifierServices

    private JButton finishGameButton; // Botão para concluir o jogo
    private JButton  checkGameStatusButton; // Botão para verificar o status do jogo
    private JButton resetButton; // Botão para reiniciar o jogo

    // Construtor
    public MainScreen(final Map<String, String> gameConfig) {

        this.boardService = new BoardServices(gameConfig);
        this.notifierService = new NotifierServices();
    }
    // Construtor
    public void buildMainScreen() {
        JPanel mainPenel = new MainPenel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPenel);
        for(int r = 0; r < 9; r+=3){
            var endRow = r + 2;
            for(int c = 0; c < 9; c+=3){
                var endCol = c + 2;
                var spaces  = getSpacesFromSector(boardService.getSpaces(), c, endCol, r, endRow);
                JPanel sector = generateSection(spaces);
                mainPenel.add(sector);

            }

        }
        addResetButton(mainPenel); // Adiciona o botão de reiniciar
        addCheckGameStatusButton(mainPenel); // Adiciona o botão de verificar o status
        addFinishGameButton(mainPenel); // Adiciona o botão de concluir o jogo
        mainFrame.revalidate(); // Atualiza o layout
        mainFrame.repaint(); // Atualiza a tela
    }
     // Método para obter espaços de um sector
    private List<Space>getSpacesFromSector(final List<List<Space>>spaces,
                                           final int startCol, int endCol, final int initRow, int endRow){

        List<Space>spaceSector = new ArrayList<>();
        for(int r = initRow; r <= endRow; r++){
            for(int c = startCol; c <= endCol; c++){
                spaceSector.add(spaces.get(c).get(r));
            }
        }
        return spaceSector;
    }
    // Método para gerar um sector
    public JPanel generateSection(final List<Space> spaces) {
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(t -> notifierService.subscribe(CLEAR_SPACE, t));

        return new SudokuSector(fields);
    }

    // Adiciona o botão de concluir o jogo
    private void addFinishGameButton( final JPanel mainPenel) {
        finishGameButton = new FinishGameButton(e -> {
            if (boardService.gameIsFinished()){
                showMessageDialog(null, "Parabéns você concluiu o jogo");
                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);

            } else {
                var message = "Seu jogo tem alguma inconsistência, ajuste e tente novamente";
                showMessageDialog(null, message);
            }
        });
        mainPenel.add(finishGameButton);
    }
    // Adiciona o botão de verificar o status do jogo
    private void addCheckGameStatusButton(JPanel mainPenel) {
        checkGameStatusButton = new ButtonCheckGamesStatusButton(e -> {
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch (gameStatus) {
                case NON_STARTED -> "O jogo não foi iniciado";
                case INCOMPLETE -> "O jogo está imcompleto";
                case COMPLETE -> "O jogo está completo";
            };
            message += hasErrors ? " e contém erros" : " e não contém erros";
            showMessageDialog(null, message);
        });
        mainPenel.add(MainScreen.this.checkGameStatusButton);

    }
    // Adiciona o botão de reiniciar o jogo
    private void addResetButton(JPanel mainPenel) {
        resetButton = new ResetButton(e ->{
            var dialogResult = showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo?",
                    "Limpar o jogo",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );
            if (dialogResult == 0){
                boardService.reset();
                notifierService.notify(CLEAR_SPACE);


            }
        });
        mainPenel.add(resetButton);


    }
}