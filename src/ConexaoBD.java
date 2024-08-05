import java.sql.Connection; // gerencia conexões com o banco de dados
import java.sql.DriverManager; // DriverManager gerenciaa drivers de banco de dados
import java.sql.SQLException; // trata exceções relacionadas ao SQL

public class ConexaoBD {
    // Constantes para a URL do banco de dados, usuário e senha
    private static final String URL = "jdbc:mysql://localhost:3306/trama"; // URL de conexão com o banco de dados MySQL
    private static final String USER = "root"; // Nome de usuário do banco de dados
    private static final String PASSWORD = ""; // Senha do banco de dados

    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver JDBC do MySQL
        return DriverManager.getConnection(URL, USER, PASSWORD); // Retorna uma conexão usando a URL, usuário e senha
    }
}