package Model;

public enum CodigoErro {
    NOT_FOUND(404, "Recurso não encontrado"),
    BAD_REQUEST(400, "Requisição inválida"),
    INTERNAL_SERVER_ERROR(500, "Erro interno do servidor");

    private final int erro;
    private final String descricao;

    CodigoErro(int erro, String descricao){
        this.erro = erro;
        this.descricao = descricao;
    }

    public int getCodigo() {
        // Implementar aqui
        return erro;
    }

    public String getDescricao() {
        // Implementar aqui
        return descricao;
    }

}
