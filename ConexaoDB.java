import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    public static Connection getConnection() {
        Connection con = null;
        // Atualize o caminho abaixo para o local onde seu arquivo Access está salvo.
        String dbPath = "F:/Estacionamento.accdb";
        String url = "jdbc:ucanaccess://" + dbPath;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}
