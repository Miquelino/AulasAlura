package Primeiro_Projeto_Banco.First.Entidades.Conta;

public enum TipoConta {
    // 1 será Corrente
    CORRENTE(1),
    // 2 será Poupança
    POUPANCA(2);

    private final int codigo;

    TipoConta(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    // Metodo estático para buscar o enum pelo código (1 ou 2)
    public static TipoConta fromCodigo(int codigo) {
        for (TipoConta tipo : TipoConta.values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        // É bom lançar uma exceção se um código inválido for passado
        throw new IllegalArgumentException("Código de Tipo de Conta inválido: " + codigo);
    }
}


