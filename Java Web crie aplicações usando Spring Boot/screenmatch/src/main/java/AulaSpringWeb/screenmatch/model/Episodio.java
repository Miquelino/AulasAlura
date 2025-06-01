package AulaSpringWeb.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional; // Importe Optional

@Entity
@Table(name = "episodios")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temporada; // Descomentado e corretamente definido
    private String titulo;
    private Integer numero; // Número do episódio
    private double avaliacao;
    private LocalDate dataLancamento;

    @ManyToOne // Indica que muitos Episódios estão relacionados a uma única Serie
    @JoinColumn(name = "serie_id") // Nome da coluna de chave estrangeira na tabela 'episodios'
    private Serie serie; // Um episódio pertence a uma Serie

    // Construtor padrão sem argumentos (OBRIGATÓRIO para JPA)
    public Episodio() {
    }

    // Construtor com argumentos, movendo a lógica de parsing para cá
    // Na sua classe AulaSpringWeb.screenmatch.model.Episodio

    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio, Serie serie) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numero = dadosEpisodio.numero(); // Assumindo que DadosEpisodio tem um método numero()

        // Para 'avaliacao', como já é 'double' no DadosEpisodio, atribua diretamente.
        // Ele será 0.0 se não vier no JSON ou se for um valor inválido convertido pelo Jackson para 0.0.
        this.avaliacao = dadosEpisodio.avaliacao();

        // Para 'dataLancamento', que é String e precisa de parsing
        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (DateTimeParseException ex) {
            this.dataLancamento = null; // Defina como null ou outra data padrão em caso de erro
        }

        this.serie = serie; // ESSENCIAL para o relacionamento ManyToOne
    }

    // --- Getters e Setters (Mantidos e/ou corrigidos) ---

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "Temporada: " + temporada +
                ", Título: '" + titulo + '\'' +
                ", Número: " + numero +
                ", Avaliação: " + avaliacao +
                ", Data de Lançamento: " + dataLancamento;
    }
}