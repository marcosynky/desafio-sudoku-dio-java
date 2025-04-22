package br.com.desafio.model;

public enum GameStatusEnum {

    // Definição dos três possíveis status do jogo
    NON_STARTED("não iniciado"),  // Jogo não iniciado
    INCOMPLETE("incompleto"),     // Jogo incompleto
    COMPLETE("completo");        // Jogo completo

    // Atributo privado para armazenar a descrição do status
    private final String label;

    // Construtor do enum, que recebe a descrição do status (label)
    GameStatusEnum(final String label) {
        this.label = label;  // Inicializa o atributo 'label' com o valor passado
    }

    // Método getter para acessar o label (descrição) de cada status
    public String getLabel() {
        return label;  // Retorna a descrição associada ao status
    }
}