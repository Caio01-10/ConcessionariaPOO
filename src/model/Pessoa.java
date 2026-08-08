package model;

public abstract class Pessoa {
    private String id;
    private String nome;
    private String cpf;
    private String email;

    public Pessoa(String id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public abstract void exibirDados();
    
    public String getId() {return id;}
    public String getNome() {return nome;}
    public String getCpf() {return cpf;}
    public String getEmail() {return email;}
}


