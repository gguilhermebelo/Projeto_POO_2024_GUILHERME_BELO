import java.util.ArrayList;

public class ProdutosController extends ListaProdutos {
    private ProdutoDAO produtoDAO;

    public ProdutosController() {
        produtoDAO = new ProdutoDAO();
    }

    @Override
    public void adicionarProduto(Produto produto) throws Excecao {
        produtoDAO.adicionarProduto(produto);
    }

    @Override
    public void editarProduto(Produto produto) throws Excecao {
        produtoDAO.editarProduto(produto);
    }

    @Override
    public void adicionarProduto(String nome, String marca, String descricao, float preco, String imagem) throws Excecao {
        produtoDAO.adicionarProduto(nome, marca, descricao, preco, imagem);
    }

    @Override
    public void editarProduto(int id, String novoNome, String novaMarca, String novaDescricao, float novoPreco, String novaImagem) throws Excecao {
        produtoDAO.editarProduto(id, novoNome, novaMarca, novaDescricao, novoPreco, novaImagem);
    }

    @Override
    public void excluirProduto(int id) throws Excecao {
        produtoDAO.excluirProduto(id);
    }

    @Override
    public void pesquisarProduto(int id) {
        // Implementação do método aqui
    }

    @Override
    public Produto buscarProduto(int id) {
        return produtoDAO.buscarProduto(id);
    }

    @Override
    public ArrayList<Produto> getProdutos() throws Excecao {
        return produtoDAO.getProdutos();
    }

    @Override
    public boolean verificarDisponibilidade() {
        return produtoDAO.verificarDisponibilidade();
    }
}