package AulaSpringWeb.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    // Adicione outras categorias conforme necessário, com seus nomes em inglês e português
    FICCAO_CIENTIFICA("Science Fiction", "Ficção Científica"),
    AVENTURA("Adventure", "Aventura"),
    FANTASIA("Fantasy", "Fantasia"),
    ANIMACAO("Animation", "Animação"),
    FAMILIA("Family", "Família"),
    MISTERIO("Mystery", "Mistério"),
    DOCUMENTARIO("Documentary", "Documentário"),
    HISTORIA("History", "História"),
    GUERRA("War", "Guerra"),
    FAROESTE("Western", "Faroeste"),
    MUSICA("Music", "Música"),
    REALITY("Reality", "Reality Show"),
    TALK_SHOW("Talk Show", "Talk Show"),
    NOTICIAS("News", "Notícias"),
    ESPORTES("Sports", "Esportes"),
    BIOGRAFIA("Biography", "Biografia"),
    TERROR("Horror", "Terror"),
    THRILLER("Thriller", "Thriller"),
    ACAO_AVENTURA("Action & Adventure", "Ação e Aventura"), // Para gêneros compostos
    DESCONHECIDO("Unknown", "Desconhecido"); // Categoria padrão para não encontrados

    private String categoriaApi; // O nome da categoria na API (geralmente em inglês)
    private String nomePortugues; // O nome da categoria em português

    Categoria(String categoriaApi, String nomePortugues) {
        this.categoriaApi = categoriaApi;
        this.nomePortugues = nomePortugues;
    }

    public String getNomePortugues() {
        return nomePortugues;
    }

    // Método para converter um nome em português para a enum Categoria
    public static Categoria fromPortugues(String textoPortugues) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.nomePortugues.equalsIgnoreCase(textoPortugues)) {
                return categoria;
            }
        }
        // Tentativa de fallback para o nome da API, caso o nome em português não mapeie
        return fromString(textoPortugues); // Reutiliza o fromString se ele já mapeia de inglês
    }

    // Método existente (ou adaptado) para converter um nome da API (inglês) para a enum Categoria
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaApi.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        return DESCONHECIDO;
    }
}