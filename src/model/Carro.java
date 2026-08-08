package model;

public class Carro extends Veiculo{
    private int numeroPortas;
    private TipoCombustivel tipoCombustivel;

    public Carro(String id, String marca, String modelo, int ano, Double preco, StatusVeiculo status, int numeroPortas, TipoCombustivel tipoCombustivel) {
        super(id, marca, modelo, ano, preco, status);
        this.numeroPortas = numeroPortas;
        this.tipoCombustivel = tipoCombustivel;
    }

    @Override
    public Double calcularImposto() {
        Double imposto = getPreco() * 0.05; // 5% do preço do carro
        return imposto;
    }

    public int getNumeroPortas() {return numeroPortas;}
    public TipoCombustivel getTipoCombustivel() {return tipoCombustivel;}
}
