package main;

import java.util.Scanner;

import exception.VeiculoIndisponivelException;
import exception.VeiculoNaoEncontradoException;
import factory.VeiculoFactory;
import model.Cliente;
import model.Funcionario;
import model.TipoCombustivel;
import model.Veiculo;
import model.Venda;
import service.GerenciadorConcessionaria;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorConcessionaria gerenciador = new GerenciadorConcessionaria();

        String arquivoVeiculos = "veiculos.csv";

        // Carrega dados salvos anteriormente, se existirem
        gerenciador.carregarVeiculosDoArquivo(arquivoVeiculos);

        // Cadastro prévio de um cliente e um funcionário para testes
        Cliente clienteExemplo = new Cliente("C1", "João Silva", "111.222.333-44", "joao@email.com", "(31) 99999-8888");
        Funcionario vendedorExemplo = new Funcionario("F1", "Maria Oliveira", "555.666.777-88", "maria@concessionaria.com", "MAT-001", 3000.0);
        gerenciador.cadastrarPessoa(clienteExemplo);
        gerenciador.cadastrarPessoa(vendedorExemplo);

        boolean executando = true;

        while (executando) {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE GESTÃO DE CONCESSIONÁRIA   ");
            System.out.println("==========================================");
            System.out.println("1. Cadastrar Veículo (Carro / Moto)");
            System.out.println("2. Listar Veículos");
            System.out.println("3. Buscar Veículo por ID");
            System.out.println("4. Realizar Venda");
            System.out.println("5. Salvar Dados em Arquivo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarVeiculoMenu(scanner, gerenciador);
                    break;

                case 2:
                    System.out.println("\n--- VEÍCULOS CADASTRADOS ---");
                    if (gerenciador.getListaVeiculos().isEmpty()) {
                        System.out.println("Nenhum veículo cadastrado no sistema.");
                    } else {
                        for (Veiculo v : gerenciador.getListaVeiculos()) {
                            System.out.println("[" + v.getClass().getSimpleName().toUpperCase() + "] ID: " + v.getId() + 
                                               " | Modelo: " + v.getMarca() + " " + v.getModelo() + 
                                               " | Preço: R$ " + v.getPreco() + 
                                               " | Status: " + v.getStatus() + 
                                               " | Imposto Estimado: R$ " + v.calcularImposto());
                        }
                    }
                    break;

                case 3:
                    System.out.print("\nDigite o ID do veículo para busca: ");
                    String idBusca = scanner.nextLine();
                    try {
                        Veiculo vEncontrado = gerenciador.buscarVeiculoPorId(idBusca);
                        System.out.println("Encontrado: " + vEncontrado.getMarca() + " " + vEncontrado.getModelo() + 
                                           " - Status: " + vEncontrado.getStatus());
                    } catch (VeiculoNaoEncontradoException e) {
                        System.out.println("ERRO DE BUSCA: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("\nDigite o ID do veículo a ser vendido: ");
                    String idVenda = scanner.nextLine();
                    try {
                        Veiculo vVenda = gerenciador.buscarVeiculoPorId(idVenda);
                        
                        Venda novaVenda = new Venda("V001", clienteExemplo, vendedorExemplo);
                        novaVenda.adicionarVeiculo(vVenda);

                        // Chamada polimórfica com tratamento da checked exception
                        gerenciador.realizarVenda(novaVenda);

                    } catch (VeiculoNaoEncontradoException e) {
                        System.out.println("FALHA NA VENDA: " + e.getMessage());
                    } catch (VeiculoIndisponivelException e) {
                        System.out.println("FALHA NA VENDA: " + e.getMessage());
                    }
                    break;

                case 5:
                    gerenciador.salvarVeiculosEmArquivo(arquivoVeiculos);
                    break;

                case 0:
                    System.out.println("Salvando dados antes de encerrar...");
                    gerenciador.salvarVeiculosEmArquivo(arquivoVeiculos);
                    executando = false;
                    System.out.println("Sistema encerrado com sucesso!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    private static void cadastrarVeiculoMenu(Scanner scanner, GerenciadorConcessionaria gerenciador) {
        System.out.println("\n--- CADASTRO DE VEÍCULO ---");
        System.out.print("Tipo (CARRO ou MOTO): ");
        String tipo = scanner.nextLine();

        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        System.out.print("Preço: R$ ");
        double preco = Double.parseDouble(scanner.nextLine());

        TipoCombustivel combustivel = TipoCombustivel.FLEX;
        int detalhe = 0;

        if (tipo.equalsIgnoreCase("CARRO")) {
            System.out.print("Número de portas: ");
            detalhe = Integer.parseInt(scanner.nextLine());
            System.out.print("Combustível (FLEX, GASOLINA, ELETRICO): ");
            combustivel = TipoCombustivel.valueOf(scanner.nextLine().toUpperCase());
        } else if (tipo.equalsIgnoreCase("MOTO")) {
            System.out.print("Cilindradas: ");
            detalhe = Integer.parseInt(scanner.nextLine());
        }

        // Uso do Padrão Factory para instanciar o objeto
        Veiculo novoVeiculo = VeiculoFactory.criarVeiculo(tipo, id, marca, modelo, ano, preco, detalhe, combustivel);
        gerenciador.cadastrarVeiculo(novoVeiculo);
    }
}