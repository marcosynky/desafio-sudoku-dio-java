package br.com.desafio.service;

import br.com.desafio.model.Board;
import br.com.desafio.model.GameStatusEnum;
import br.com.desafio.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Classe para gerenciar o jogo
public class BoardServices {
    private final static int BOARD_LIMIT = 9; // Limite do tabuleiro

    private final Board board; // Tabuleiro do jogo

    public List<List<Space>> getSpaces() { return board.getSpaces(); } // Getter para obter a lista de espaços do tabuleiro>

    public void reset() { board.reset(); } // Método para resetar o tabuleiro

    public boolean hasErrors() { return board.hasErrors(); } // Método para verificar se há erros no tabuleiro

    public GameStatusEnum getStatus() { return board.getStatus(); }

    public boolean gameIsFinished() { return board.gameIsFinished(); }

    public BoardServices(final Map<String, String> gameConfig) {
        this.board = new Board( initBoard(gameConfig) );
    } // Construtor da classe

    private List<List<Space>> initBoard(final Map<String, String> gameConfig) { // Método para iniciar o jogo com base nas posições fornecidas
        List<List<Space>> spaces = new ArrayList<>(); // Lista para armazenar as linhas do tabuleiro
        for (int i = 0; i < BOARD_LIMIT; i++) { // Itera por todas as linhas
            spaces.add(new ArrayList<>()); // Adiciona uma nova linha ao tabuleiro
            for (int j = 0; j < BOARD_LIMIT; j++) { // Itera por todas as colunas
                // Recupera a configuração da posição para i,j
                var positionConfig = gameConfig.get("%s,%s".formatted(i, j)); // String com a configuração da posição

                // Extrai o valor esperado e o status fixo da posição
                var expected = Integer.parseInt(positionConfig.split(",")[0]); // Valor esperado
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]); // Status fixo

                // Cria o objeto Space com os valores extraídos
                var currentSpace = new Space(expected, fixed);

                // Adiciona o objeto Space à lista da linha correspondente
                spaces.get(i).add(currentSpace);
            }
        }

        // Cria o tabuleiro e inicia o jogo
        return  spaces;
    }


}