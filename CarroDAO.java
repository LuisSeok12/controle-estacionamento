import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private Connection con;

    public CarroDAO() {
        con = ConexaoDB.getConnection();
    }

    // Método para inserir um carro
    public boolean inserir(Carro carro) {
        String sql = "INSERT INTO Carros (Marca, Placa, Cor, HoraEntrada, HoraSaida) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getPlaca());
            ps.setString(3, carro.getCor());
            ps.setInt(4, carro.getHoraEntrada());
            ps.setInt(5, carro.getHoraSaida());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir carro: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para atualizar um carro
    public boolean atualizar(Carro carro) {
        String sql = "UPDATE Carros SET Marca = ?, Cor = ?, HoraEntrada = ?, HoraSaida = ? WHERE Placa = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getCor());
            ps.setInt(3, carro.getHoraEntrada());
            ps.setInt(4, carro.getHoraSaida());
            ps.setString(5, carro.getPlaca());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar carro: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para excluir um carro (usando a placa como identificador único)
    public boolean excluir(String placa) {
        String sql = "DELETE FROM Carros WHERE Placa = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, placa);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir carro: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos os carros cadastrados
    public List<Carro> listar() {
        List<Carro> lista = new ArrayList<>();
        String sql = "SELECT Marca, Placa, Cor, HoraEntrada, HoraSaida FROM Carros";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Carro carro = new Carro(
                    rs.getString("Marca"),
                    rs.getString("Placa"),
                    rs.getString("Cor"),
                    rs.getInt("HoraEntrada"),
                    rs.getInt("HoraSaida")
                );
                lista.add(carro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar carros: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}
