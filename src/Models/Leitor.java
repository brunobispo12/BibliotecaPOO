package Models;

public class Leitor {
    private String nome;
    private String email;
    private int id;

    public Leitor(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Email: " + email;
    }
}
