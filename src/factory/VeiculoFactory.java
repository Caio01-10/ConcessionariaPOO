package factory;

import model.Carro;
import model.Moto;
import model.StatusVeiculo;
import model.TipoCombustivel;
import model.Veiculo;

public class VeiculoFactory {

    public static Veiculo criarVeiculo(int tipo, String id, String marca, String modelo, 
                                       int ano, double preco, int detalheEspecifico, 
                                       TipoCombustivel tipoCombustivel) {
        
        if (tipo == 0) {
            return null;
        }

        if (tipo == 1) {
            return new Carro(id, marca, modelo, ano, preco, StatusVeiculo.DISPONIVEL, detalheEspecifico, tipoCombustivel);
        } 
        else if (tipo == 2) {
            return new Moto(id, marca, modelo, ano, preco, StatusVeiculo.DISPONIVEL, detalheEspecifico);
        }

        throw new IllegalArgumentException("Tipo de veículo inválido: " + tipo);
    }
}