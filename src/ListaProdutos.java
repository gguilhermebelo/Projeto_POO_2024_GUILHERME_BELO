import java.util.ArrayList;
import java.util.Date;

public abstract class ListaProdutos {
    protected ArrayList<Produto> produtos = new ArrayList<>();
    protected int totalDeProdutos;
    protected Date dataUltimaAtualizacao;
    protected String categoria;
    protected boolean estoqueBaixo;
    protected String ultimaAcaoRealizada;


    public abstract void adicionarProduto(Produto produto) throws Excecao;

    public abstract void editarProduto(Produto produto) throws Excecao;

    public abstract void adicionarProduto(String nome, String marca, String descricao, float preco, String imagem) throws Excecao;

    public abstract void editarProduto(int id, String novoNome, String novaMarca, String novaDescricao, float novoPreco, String novaImagem) throws Excecao;

    public abstract void excluirProduto(int id) throws Excecao;

    public abstract void pesquisarProduto(int id) throws Excecao;

    public abstract Produto buscarProduto(int id) throws Excecao;

    public abstract ArrayList<Produto> getProdutos() throws Excecao;

    public boolean verificarDisponibilidade() {
        return true; // Supondo que está disponível por padrão
    }
}
