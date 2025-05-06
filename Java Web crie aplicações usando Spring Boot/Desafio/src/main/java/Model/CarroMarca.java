package Model;

public class CarroMarca {

    private int codigo;
    private String nome;

    // Construtor padrão (necessário para desserialização pelo Jackson)
    public CarroMarca() {
    }

    public CarroMarca (int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
