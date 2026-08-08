package model;

public class Gerente extends Funcionario {
    private Double bonus;

    public Gerente(String id, String nome, String cpf, String email, String matricula, Double salarioBase, Double bonus) {
        super(id, nome, cpf, email, matricula, salarioBase);
        this.bonus = bonus;
    }

    @Override
    public void exibirDados() {
        System.out.println("[GERENTE] ID: " + getId() + " | Nome: " + getNome() + " | Matricula: " + getMatricula() + " | Bonus: " + bonus);
    }

    public Double getBonus() {return bonus;}

}
