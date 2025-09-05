package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    FICCAO_CIENTIFICA("Science Fiction ", "Ficção Científica");

    private String categoriaOmdb;
    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(String.valueOf(text))) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromInteger(Integer codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("Código de categoria nulo");
        }
        switch (codigo) {
            case 1: return ACAO;
            case 2: return ROMANCE;
            case 3: return COMEDIA;
            case 4: return DRAMA;
            case 5: return CRIME;
            case 6: return FICCAO_CIENTIFICA;
            default:
                throw new IllegalArgumentException("Nenhuma categoria encontrada para o código fornecido: " + codigo);
        }
    }

}
