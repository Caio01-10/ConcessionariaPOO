package model;

public class Moto extends Veiculo{
    private int cilindradas;

    public Moto(String id, String marca, String modelo, int ano, Double preco, StatusVeiculo status, int cilindradas) {
        super(id, marca, modelo, ano, preco, status);
        this.cilindradas = cilindradas;
    }

    @Override
    public Double calcularImposto() {
        Double imposto = getPreco() * 0.03; // 3% do preço da moto
        return imposto;
    }

    public int getCilindradas() {return cilindradas;}
}
