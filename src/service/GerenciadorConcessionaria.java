package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exception.*;
import model.*;

public class GerenciadorConcessionaria {
    private List<Veiculo> listarVeiculos;
    private List<Pessoa> listarPessoas;
    private List<Venda> listarVendas;

    public GerenciadorConcessionaria() {
        this.listarVeiculos = new ArrayList<>();
        this.listarPessoas = new ArrayList<>();
        this.listarVendas = new ArrayList<>();
    }

    //---CADASTROS---
    public void cadastrarVeiculo(Veiculo veiculo) {
        listarVeiculos.add(veiculo);
        System.out.println("Veículo" + veiculo.getModelo() + " cadastrado com sucesso!");
    }

    public void cadastrarPessoa(Pessoa pessoa) {
        listarPessoas.add(pessoa);
        System.out.println("Pessoa " + pessoa.getNome() + " cadastrada com sucesso!");
    }

    //---BUSCAS E EXCEÇÕES---
    public Veiculo buscarVeiculoPorId(String id)throws VeiculoNaoEncontradoException {
        for(Veiculo v : listarVeiculos){
            if(v.getId().equals(id)){
                return v;
            }
        }
        throw new VeiculoNaoEncontradoException("Veículo não encontrado com ID: " + id);
       }

    public List<Veiculo> getListaVeiculos() { return listarVeiculos; }

    public void salvarVeiculosEmArquivo(String caminhoArquivo) { salvarDadosEmArquivo(caminhoArquivo); }
    //---REGRAS DE NEGÓCIO(VENDA)---
    public void realizarVenda(Venda venda) throws VeiculoIndisponivelException {
        //Valição de disponibilidade do veículo
        for(Veiculo v : venda.getVeiculos()){
            if(v.getStatus() == StatusVeiculo.VENDIDO){
                throw new VeiculoIndisponivelException("Veículo " + v.getModelo() + " não está disponível para venda.");
            }
        }

        //Marcação de veículos vendidos
        for(Veiculo v : venda.getVeiculos()){
            v.setStatus(StatusVeiculo.VENDIDO);
        }
        listarVendas.add(venda);
        System.out.println("Venda" + venda.getIdVenda() + " realizada com sucesso!");
    }

    //---ARMAZENAMENTO EM ARQUIVO---
    public void salvarDadosEmArquivo(String caminhoArquivo){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for(Veiculo v : listarVeiculos){
                String tipo = (v instanceof Carro) ? "CARRO" : "MOTO";
                String detalhe = "";
                String combustivel = "N/A";

                if (v instanceof Carro){
                    Carro c = (Carro) v;
                    detalhe = String.valueOf(c.getNumeroPortas());
                    combustivel = c.getTipoCombustivel().name();
                }
                else if (v instanceof Moto){
                    Moto m = (Moto) v;
                    detalhe = String.valueOf(m.getCilindradas());
                }

                String linha = String.join(";", 
                    tipo, v.getId(), v.getMarca(), v.getModelo(), 
                    String.valueOf(v.getAno()), String.valueOf(v.getPreco()), 
                    v.getStatus().name(), detalhe, combustivel
                );

                bw.write(linha);
                bw.newLine();
            }
            System.out.println("Dados de veículos salvos em arquivo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados em arquivo: " + e.getMessage());
        }
    }

    public void carregarVeiculosDoArquivo(String caminhoArquivo){
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length < 8) {
                    System.err.println("Linha inválida no arquivo: " + linha);
                    continue;
                }

                String tipo = dados[0];
                String id = dados[1];
                String marca = dados[2];
                String modelo = dados[3];
                int ano = Integer.parseInt(dados[4]);
                double preco = Double.parseDouble(dados[5]);
                StatusVeiculo status = StatusVeiculo.valueOf(dados[6]);

                if (tipo.equals("CARRO")) {
                    int numeroPortas = Integer.parseInt(dados[7]);
                    TipoCombustivel tipoCombustivel = TipoCombustivel.valueOf(dados[8]);
                    Carro carro = new Carro(id, marca, modelo, ano, preco, status, numeroPortas, tipoCombustivel);
                    listarVeiculos.add(carro);
                } else if (tipo.equals("MOTO")) {
                    int cilindradas = Integer.parseInt(dados[7]);
                    Moto moto = new Moto(id, marca, modelo, ano, preco, status, cilindradas);
                    listarVeiculos.add(moto);
                }
            }
            System.out.println("Dados de veículos carregados do arquivo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }
}

  