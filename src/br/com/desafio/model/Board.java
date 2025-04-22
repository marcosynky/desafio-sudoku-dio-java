package br.com.desafio.model;

import java.util.Collection;
import java.util.List;

import static br.com.desafio.model.GameStatusEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    // Lista de listas de espaços que representa o tabuleiro
    public List<List<Space>> spaces;

    // Construtor que recebe uma lista de listas de espaços para inicializar o tabuleiro
    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;  // Inicializa o tabuleiro com a lista de espaços fornecida
    }

    // Método para determinar o status do jogo baseado nos espaços no tabuleiro
    public GameStatusEnum getGameStatus() {
        // Verifica se todos os espaços não fixos têm valor atual atribuído (se não, o jogo não começou)
        if(spaces.stream().flatMap(Collection::stream).noneMatch(space -> !space.isFixed() && nonNull(space.getActual()))){
            return NON_STARTED;  // Retorna "NON_STARTED" caso não haja espaços não fixos com valores atribuídos
        }

        // Caso contrário, verifica se há algum espaço sem valor atual (se houver, o jogo está incompleto)
        return spaces.stream().flatMap(Collection::stream).anyMatch(space -> isNull(space.getActual()))?
                INCOMPLETE : COMPLETE;  // Retorna "INCOMPLETE" se algum espaço não tiver valor, caso contrário "COMPLETE"
    }

    // Método para verificar se há erros no tabuleiro
    public boolean hasErrors() {
        // Se o jogo não foi iniciado, não há erros
        if(getGameStatus() == NON_STARTED){
            return false;  // Retorna false se o jogo ainda não começou
        }

        // Verifica se algum espaço tem valor atual que não corresponda ao valor esperado
        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(space -> nonNull(space.getActual()) && !space.getActual().equals(space.getExpected()));
    }

    // Método para alterar o valor de um espaço específico no tabuleiro
    public boolean changeValue(final int column, final int row, final int value) {
        final Space space = spaces.get(column).get(row);  // Obtém o espaço específico usando a coluna e a linha
        if(space.isFixed()) return false;  // Se o espaço for fixo, não pode ser alterado

        space.setActual(value);  // Caso contrário, altera o valor atual do espaço
        return true;  // Retorna true para indicar que a alteração foi bem-sucedida
    }

    // Método para limpar o valor de um espaço específico no tabuleiro
    public boolean clearValue(final int column, final int row) {
        final Space space = spaces.get(column).get(row);  // Obtém o espaço específico usando a coluna e a linha
        if(space.isFixed()) return false;  // Se o espaço for fixo, não pode ser limpo

        space.clearSpace();  // Caso contrário, limpa o valor atual do espaço
        return true;  // Retorna true para indicar que a limpeza foi bem-sucedida
    }

    // Método para resetar o tabuleiro, limpando todos os espaços
    public void reset() {
        spaces.forEach(column -> column.forEach(Space::clearSpace));  // Itera por todas as colunas e limpa os espaços
    }

    // Método para verificar se o jogo foi finalizado corretamente
    public boolean gameIsFinished() {
        // Verifica se não há erros e o status do jogo é "COMPLETE"
        return !hasErrors() && getGameStatus().equals(COMPLETE);
    }

    // Getter para obter a lista de espaços do tabuleiro
    public List<List<Space>> getSpaces() {
        return spaces;  // Retorna o tabuleiro (lista de listas de espaços)
    }

    // Método duplicado para verificar o status do jogo (o mesmo que getGameStatus)
    public GameStatusEnum getStatus() {
        // Verifica se todos os espaços não fixos têm valor atual atribuído (se não, o jogo não começou)
        if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))) {
            return NON_STARTED;  // Retorna "NON_STARTED" caso não haja espaços não fixos com valores atribuídos
        }

        // Caso contrário, verifica se há algum espaço sem valor atual (se houver, o jogo está incompleto)
        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? INCOMPLETE : COMPLETE;
    }

}