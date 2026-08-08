package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private String idVenda;
    private LocalDate data;
    private double valorTotal;
    private Cliente cliente;
    private Funcionario vendedor;
    private List<Veiculo> veiculos;

    private Venda(String idVenda, LocalDate data, double valorTotal, Cliente cliente, Funcionario vendedor) {
        this.idVenda = idVenda;
        this.data = data;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.veiculos = new ArrayList<>();
    }

    public Venda(String idVenda, Cliente cliente, Funcionario vendedor) {
        this(idVenda, LocalDate.now(), 0.0, cliente, vendedor);
    }

    public void adicionarVeiculo(Veiculo veiculo){
        this.veiculos.add(veiculo);
        calcularTotal();
    }

    public void calcularTotal() {
        double total = 0;
        for (Veiculo v : veiculos) {
            total += v.getPreco();
        }
        this.valorTotal = total;
    }

    public String getIdVenda() {return idVenda;}
    public LocalDate getData() {return data;}
    public double getValorTotal() {return valorTotal;}
    public Cliente getCliente() {return cliente;}
    public Funcionario getVendedor() {return vendedor;}
    public List<Veiculo> getVeiculos() {return veiculos;}
}
