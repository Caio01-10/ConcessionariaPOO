package model;

public class Cliente extends Pessoa{
    private String telefone;

    public Cliente(String id, String nome, String cpf, String email, String telefone) {
        super(id, nome, cpf, email);
        this.telefone = telefone;
    }

    @Override
    public void exibirDados() {
        System.out.println("[CLIENTE] ID: " + getId() + " | Nome: " + getNome() + " | Tel: " + telefone);
    }

    public String getTelefone() {return telefone;}
}