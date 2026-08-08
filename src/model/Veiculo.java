package model;

public abstract class Veiculo {
    private String id;
    private String marca;
    private String modelo;
    private int ano;
    private Double preco;
    private StatusVeiculo status;

    public Veiculo(String id, String marca, String modelo, int ano, Double preco, StatusVeiculo status) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.status = status;
    }

    public abstract Double calcularImposto();

    public String getId() {return id;}
    public String getMarca() {return marca;}
    public String getModelo() {return modelo;}
    public int getAno() {return ano;}
    public Double getPreco() {return preco;}
    public StatusVeiculo getStatus() {return status;}
    public void setStatus(StatusVeiculo status) { this.status = status; }
}
