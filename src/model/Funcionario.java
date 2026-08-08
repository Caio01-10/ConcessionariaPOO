package model;

public class Funcionario extends Pessoa {
    private String  matricula;
    private Double salarioBase;

    public Funcionario(String id, String nome, String cpf, String email, String matricula, Double salarioBase) {
        super(id, nome, cpf, email);
        this.matricula = matricula;
        this.salarioBase = salarioBase;
    }

    @Override
    public void exibirDados() {
        System.out.println("[FUNCIONARIO] ID: " + getId() + " | Nome: " + getNome() + " | Matricula: " + matricula);
    }

    public String getMatricula() {return matricula;}
    public Double getSalarioBase() {return salarioBase;}

}
