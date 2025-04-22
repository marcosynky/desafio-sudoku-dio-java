package br.com.desafio.model;

public class Space {

    // Atributos privados da classe
    private Integer actual;  // Representa o valor atual do espaço
    private final int expected;  // Representa o valor esperado para o espaço
    private final boolean fixed;  // Indica se o valor do espaço é fixo ou não

    // Construtor da classe que recebe o valor esperado e o estado fixo do espaço
    public Space(final int expected, final boolean fixed) {
        this.expected = expected;  // Inicializa o valor esperado
        this.fixed = fixed;  // Define se o valor do espaço é fixo ou não
        if (fixed) {
            actual = expected;  // Se o valor for fixo, o valor atual é igual ao esperado
        }
    }

    // Getter para o atributo 'actual', que retorna o valor atual do espaço
    public Integer getActual() {
        return actual;
    }

    // Setter para o atributo 'actual', que só pode alterar o valor se 'fixed' for false
    public void setActual(final Integer actual) {
        if (fixed) return;  // Se o valor do espaço for fixo, não permite alteração
        this.actual = actual;  // Caso contrário, altera o valor atual
    }

    // Método para limpar o valor do espaço, definindo 'actual' como null
    public void clearSpace(){
        setActual(null);  // Chama o setter para limpar o valor atual
    }

    // Getter para o atributo 'expected', que retorna o valor esperado do espaço
    public int getExpected() {
        return expected;
    }

    // Getter para o atributo 'fixed', que retorna se o valor do espaço é fixo ou não
    public boolean isFixed() {
        return fixed;
    }
}