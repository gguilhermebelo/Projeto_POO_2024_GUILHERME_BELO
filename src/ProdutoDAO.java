import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO extends ListaProdutos {

    @Override
    public void adicionarProduto(Produto produto) throws Excecao {
        String sql = "INSERT INTO produtos (id, nome, marca, descricao, preco, imagem) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, produto.getId());
            pstmt.setString(2, produto.getNome());
            pstmt.setString(3, produto.getMarca());
            pstmt.setString(4, produto.getDescricao());
            pstmt.setFloat(5, produto.getPreco());
            pstmt.setString(6, produto.getImagem());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new Excecao("Falha ao inserir o produto, nenhuma linha afetada.");
            }

            System.out.println("Produto adicionado com sucesso!");

        } catch (SQLException | ClassNotFoundException e) {
            throw new Excecao("Erro ao adicionar produto", e);
        }
    }

    @Override
    public void editarProduto(Produto produto) throws Excecao {
        String sql = "UPDATE produtos SET nome = ?, marca = ?, descricao = ?, preco = ?, imagem = ? WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getMarca());
            pstmt.setString(3, produto.getDescricao());
            pstmt.setFloat(4, produto.getPreco());
            pstmt.setString(5, produto.getImagem());
            pstmt.setInt(6, produto.getId());

            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new Excecao("Erro ao editar produto", e);
        }
    }

    @Override
    public void adicionarProduto(String nome, String marca, String descricao, float preco, String imagem) throws Excecao {
        Produto produto = new Produto(nome, marca, descricao, preco, imagem);
        adicionarProduto(produto);
    }

    @Override
    public void editarProduto(int id, String novoNome, String novaMarca, String novaDescricao, float novoPreco, String novaImagem) throws Excecao {
        Produto produto = new Produto(novoNome, novaMarca, novaDescricao, novoPreco, novaImagem);
        produto.setId(id);
        editarProduto(produto);
    }

    @Override
    public void excluirProduto(int id) throws Excecao {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new Excecao("Erro ao excluir produto", e);
        }
    }

    @Override
    public ArrayList<Produto> getProdutos() throws Excecao {
        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto(rs.getString("nome"), rs.getString("marca"), rs.getString("descricao"), rs.getFloat("preco"), rs.getString("imagem"));
                produto.setId(rs.getInt("id"));
                produtos.add(produto);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new Excecao("Erro ao obter produtos", e);
        }
        return produtos;
    }

    @Override
    public boolean verificarDisponibilidade() {
        // Implementação do método aqui
        return false;
    }

    @Override
    public void pesquisarProduto(int id) {
        // Implementação do método aqui
    }


    @Override
    public Produto buscarProduto(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;

        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            // rs = resultset
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto(rs.getString("nome"), rs.getString("marca"), rs.getString("descricao"), rs.getFloat("preco"), rs.getString("imagem"));
                    produto.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produto;
    }

}