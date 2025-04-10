import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EstacionamentoApp extends JFrame {
    private JTextField txtMarca, txtPlaca, txtCor, txtHoraEntrada, txtHoraSaida;
    private JTable tabela;
    private DefaultTableModel modelo;
    private CarroDAO carroDAO;

    public EstacionamentoApp() {
        setTitle("Controle de Estacionamento");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        carroDAO = new CarroDAO();
        initComponents();
    }

    private void initComponents() {
        // Painel para os campos de entrada
        JPanel painelEntrada = new JPanel(new GridLayout(5, 2, 5, 5));
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Dados do Carro"));

        painelEntrada.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        painelEntrada.add(txtMarca);

        painelEntrada.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        painelEntrada.add(txtPlaca);

        painelEntrada.add(new JLabel("Cor:"));
        txtCor = new JTextField();
        painelEntrada.add(txtCor);

        painelEntrada.add(new JLabel("Hora Entrada:"));
        txtHoraEntrada = new JTextField();
        painelEntrada.add(txtHoraEntrada);

        painelEntrada.add(new JLabel("Hora Saída:"));
        txtHoraSaida = new JTextField();
        painelEntrada.add(txtHoraSaida);

        // Painel para os botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnInserir = new JButton("Inserir");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnListar = new JButton("Listar");

        painelBotoes.add(btnInserir);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListar);

        // Tabela para exibir a listagem dos carros
        modelo = new DefaultTableModel();
        modelo.addColumn("Marca");
        modelo.addColumn("Placa");
        modelo.addColumn("Cor");
        modelo.addColumn("Hora Entrada");
        modelo.addColumn("Hora Saída");
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        // Layout principal
        setLayout(new BorderLayout());
        add(painelEntrada, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Eventos dos botões
        btnInserir.addActionListener(e -> inserirCarro());
        btnAtualizar.addActionListener(e -> atualizarCarro());
        btnExcluir.addActionListener(e -> excluirCarro());
        btnListar.addActionListener(e -> listarCarros());

        // Evento para selecionar uma linha na tabela e preencher os campos
        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    txtMarca.setText(modelo.getValueAt(linha, 0).toString());
                    txtPlaca.setText(modelo.getValueAt(linha, 1).toString());
                    txtCor.setText(modelo.getValueAt(linha, 2).toString());
                    txtHoraEntrada.setText(modelo.getValueAt(linha, 3).toString());
                    txtHoraSaida.setText(modelo.getValueAt(linha, 4).toString());
                }
            }
        });
    }

    private void inserirCarro() {
        try {
            String marca = txtMarca.getText();
            String placa = txtPlaca.getText();
            String cor = txtCor.getText();
            int horaEntrada = Integer.parseInt(txtHoraEntrada.getText());
            int horaSaida = Integer.parseInt(txtHoraSaida.getText());

            Carro carro = new Carro(marca, placa, cor, horaEntrada, horaSaida);
            if (carroDAO.inserir(carro)) {
                JOptionPane.showMessageDialog(this, "Carro inserido com sucesso!");
                limparCampos();
                listarCarros();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir carro!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Hora Entrada e Hora Saída devem ser números inteiros.");
        }
    }

    private void atualizarCarro() {
        try {
            String marca = txtMarca.getText();
            String placa = txtPlaca.getText();
            String cor = txtCor.getText();
            int horaEntrada = Integer.parseInt(txtHoraEntrada.getText());
            int horaSaida = Integer.parseInt(txtHoraSaida.getText());

            Carro carro = new Carro(marca, placa, cor, horaEntrada, horaSaida);
            if (carroDAO.atualizar(carro)) {
                JOptionPane.showMessageDialog(this, "Carro atualizado com sucesso!");
                limparCampos();
                listarCarros();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar carro!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Hora Entrada e Hora Saída devem ser números inteiros.");
        }
    }

    private void excluirCarro() {
        String placa = txtPlaca.getText();
        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a placa do carro para exclusão.");
            return;
        }
        if (carroDAO.excluir(placa)) {
            JOptionPane.showMessageDialog(this, "Carro excluído com sucesso!");
            limparCampos();
            listarCarros();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir carro!");
        }
    }

    private void listarCarros() {
        modelo.setRowCount(0); // Limpa a tabela
        List<Carro> carros = carroDAO.listar();
        for (Carro carro : carros) {
            modelo.addRow(new Object[]{
                carro.getMarca(),
                carro.getPlaca(),
                carro.getCor(),
                carro.getHoraEntrada(),
                carro.getHoraSaida()
            });
        }
    }

    private void limparCampos() {
        txtMarca.setText("");
        txtPlaca.setText("");
        txtCor.setText("");
        txtHoraEntrada.setText("");
        txtHoraSaida.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EstacionamentoApp().setVisible(true);
        });
    }
}
