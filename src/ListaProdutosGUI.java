import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListaProdutosGUI {
    private JTextArea textArea;
    private ProdutosController listaProdutos;

    public ListaProdutosGUI(ProdutosController listaProdutos) {
        this.listaProdutos = listaProdutos;
        try {
            criarGUI();
            atualizarLista(); // Chama atualizarLista() na inicialização para carregar os dados do banco
        } catch (Excecao ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a interface gráfica: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarGUI() throws Excecao {
        JFrame frame = new JFrame("Lista de Produtos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(117, 147, 88));
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));
        JLabel headerLabel = new JLabel("Bem-vindo(a) ");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        textArea = new JTextArea(20, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.setBackground(Color.WHITE);

        JButton adicionarButton = new JButton("Adicionar Produto");
        adicionarButton.setForeground(Color.WHITE);
        adicionarButton.setBackground(new Color(117, 147, 88));
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nomeField = new JTextField();
                JTextField marcaField = new JTextField();
                JTextField descricaoField = new JTextField();
                JTextField precoField = new JTextField();
                JTextField imagemField = new JTextField();

                Object[] message = {
                        "Nome:", nomeField,
                        "Marca:", marcaField,
                        "Descrição:", descricaoField,
                        "Preço:", precoField,
                        "Imagem", imagemField
                };

                int option = JOptionPane.showConfirmDialog(frame, message, "Adicionar Produto", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String nome = nomeField.getText();
                        String marca = marcaField.getText();
                        String descricao = descricaoField.getText();
                        String imagem = imagemField.getText();
                        float preco = Float.parseFloat(precoField.getText());

                        listaProdutos.adicionarProduto(nome, marca, descricao, preco, imagem);
                        atualizarLista();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Preço inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (Excecao ex) {
                        JOptionPane.showMessageDialog(frame, "Erro ao adicionar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(adicionarButton);

        JButton editarButton = new JButton("Editar Produto");
        editarButton.setForeground(Color.WHITE);
        editarButton.setBackground(new Color(117, 147, 88));
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String produtoId = JOptionPane.showInputDialog(frame, "Digite o ID do produto a ser editado:");
                if (produtoId != null && !produtoId.isEmpty()) {
                    try {
                        int id = Integer.parseInt(produtoId);
                        Produto produtoParaEditar = listaProdutos.buscarProduto(id);
                        if (produtoParaEditar != null) {
                            JTextField nomeField = new JTextField();
                            JTextField marcaField = new JTextField();
                            JTextField descricaoField = new JTextField();
                            JTextField precoField = new JTextField();
                            JTextField imagemField = new JTextField();

                            nomeField.setText(produtoParaEditar.getNome());
                            marcaField.setText(produtoParaEditar.getMarca());
                            descricaoField.setText(produtoParaEditar.getDescricao());
                            precoField.setText(String.valueOf(produtoParaEditar.getPreco()));
                            imagemField.setText(produtoParaEditar.getImagem());

                            Object[] message = {
                                    "Novo Nome:", nomeField,
                                    "Nova Marca:", marcaField,
                                    "Nova Descrição:", descricaoField,
                                    "Novo Preço:", precoField,
                                    "Nova Imagem:", imagemField
                            };

                            int option = JOptionPane.showConfirmDialog(frame, message, "Editar Produto", JOptionPane.OK_CANCEL_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                String novoNome = nomeField.getText();
                                String novaMarca = marcaField.getText();
                                String novaDescricao = descricaoField.getText();
                                float novoPreco = Float.parseFloat(precoField.getText());
                                String novaImagem = imagemField.getText();
                                listaProdutos.editarProduto(id, novoNome, novaMarca, novaDescricao, novoPreco, novaImagem);
                                atualizarLista();
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException | Excecao ex) {
                        JOptionPane.showMessageDialog(frame, "ID ou Preço inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(editarButton);

        JButton excluirButton = new JButton("Excluir Produto"); //  botão para excluir produtos
        excluirButton.setForeground(Color.WHITE);
        excluirButton.setBackground(new Color(117, 147, 88));
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String produtoId = JOptionPane.showInputDialog(frame, "Digite o ID do produto a ser excluído:"); // Solicita o ID do produto a ser excluído
                if (produtoId != null && !produtoId.isEmpty()) { // Se o ID não for nulo e não estiver vazio
                    try {
                        int id = Integer.parseInt(produtoId); // Converte o ID para int
                        listaProdutos.excluirProduto(id); // Exclui o produto da lista
                        atualizarLista(); // Atualiza a lista de produtos
                    } catch (NumberFormatException ex) { // Peha exceções de formato inválido
                        JOptionPane.showMessageDialog(frame, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro
                    } catch (Excecao ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        buttonPanel.add(excluirButton);

        JButton pesquisarButton = new JButton("Pesquisar Produto");
        pesquisarButton.setForeground(Color.WHITE);
        pesquisarButton.setBackground(new Color(117, 147, 88));
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String produtoIdStr = JOptionPane.showInputDialog(frame, "Digite o ID do produto a ser pesquisado:");
                if (produtoIdStr != null && !produtoIdStr.isEmpty()) {
                    try {
                        int produtoId = Integer.parseInt(produtoIdStr);
                        Produto produto = listaProdutos.buscarProduto(produtoId);
                        if (produto != null) {
                            // Atualiza a lista com o produto encontrado
                            atualizarTextArea(List.of(produto));
                        } else {
                            JOptionPane.showMessageDialog(frame, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(pesquisarButton);

        JButton sairButton = new JButton("Sair");
        sairButton.setForeground(Color.WHITE);
        sairButton.setBackground(new Color(174, 0, 0));
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        buttonPanel.add(sairButton);

        frame.add(buttonPanel, BorderLayout.EAST);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(117, 147, 88));
        footerPanel.setPreferredSize(new Dimension(frame.getWidth(), 30));
        JLabel footerLabel = new JLabel("Todos os direitos reservados a Trama");
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);
        frame.add(footerPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void atualizarLista() {
        List<Produto> todosProdutos;
        try {
            todosProdutos = listaProdutos.getProdutos();
            atualizarTextArea(todosProdutos);
        } catch (Excecao ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter produtos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTextArea(List<Produto> produtos) {
        textArea.setText("");
        for (Produto produto : produtos) {
            textArea.append("ID: " + produto.getId() + "\n");
            textArea.append("Nome: " + produto.getNome() + "\n");
            textArea.append("Marca: " + produto.getMarca() + "\n");
            textArea.append("Descrição: " + produto.getDescricao() + "\n");
            textArea.append("Preço: R$ " + produto.getPreco() + "\n");
            textArea.append("Imagem: " + produto.getImagem() + "\n");
            textArea.append("----------------------------\n");
        }
    }
}
