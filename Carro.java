public class Carro {
    private String marca;
    private String placa;
    private String cor;
    private int horaEntrada;
    private int horaSaida;

    // Construtor vazio (default)
    public Carro() {}

    // Construtor com todos os atributos
    public Carro(String marca, String placa, String cor, int horaEntrada, int horaSaida) {
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    // Construtor sem horaSaida (útil para quando o carro ainda está no estacionamento)
    public Carro(String marca, String placa, String cor, int horaEntrada) {
        this(marca, placa, cor, horaEntrada, 0);
    }

    // Construtor com placa e marca
    public Carro(String placa, String marca) {
        this.marca = marca;
        this.placa = placa;
    }

    // Construtor com placa, marca e cor
    public Carro(String placa, String marca, String cor) {
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
    }

    // Getters e Setters
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public int getHoraEntrada() {
        return horaEntrada;
    }
    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
    
    public int getHoraSaida() {
        return horaSaida;
    }
    public void setHoraSaida(int horaSaida) {
        this.horaSaida = horaSaida;
    }
}
