package factory;

import model.Carro;
import model.Moto;
import model.StatusVeiculo;
import model.TipoCombustivel;
import model.Veiculo;

public class VeiculoFactory {

    public static Veiculo criarVeiculo(String tipo, String id, String marca, String modelo, 
                                       int ano, double preco, int detalheEspecifico, 
                                       TipoCombustivel tipoCombustivel) {
        
        if (tipo == null) {
            return null;
        }

        if (tipo.equalsIgnoreCase("CARRO")) {
            return new Carro(id, marca, modelo, ano, preco, StatusVeiculo.DISPONIVEL, detalheEspecifico, tipoCombustivel);
        } 
        else if (tipo.equalsIgnoreCase("MOTO")) {
            return new Moto(id, marca, modelo, ano, preco, StatusVeiculo.DISPONIVEL, detalheEspecifico);
        }

        throw new IllegalArgumentException("Tipo de veículo inválido: " + tipo);
    }
}