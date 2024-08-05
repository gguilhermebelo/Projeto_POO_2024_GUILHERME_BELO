public class Produto {
    private int id;
    private String nome;
    private String marca;
    private String descricao;
    private float preco;
    private String imagem;


    public Produto(String nome, String marca, String descricao, float preco, String imagem) {  // CONTSTRUTOR
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }  // um getter

    public void setId(int id) {
        this.id = id;
    } // um setter

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
