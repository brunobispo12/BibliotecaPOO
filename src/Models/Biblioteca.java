package Models;

public class Biblioteca {

    private String nome;
    private String endereco;
    private Livro[] livros;  
    private int totalLivros;

    public Biblioteca(String nome, String endereco, int capacidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.livros = new Livro[capacidade];  
        this.totalLivros = 0;
    }

    public void adicionarLivro(Livro livro) {
        if (totalLivros < livros.length) {
            livros[totalLivros] = livro;
            totalLivros++;
        } else {
            System.out.println("Capacidade máxima atingida.");
        }
    }

    public Livro consultarLivroPorISBN(String isbn) {
        for (int i = 0; i < totalLivros; i++) {
            if (livros[i].getIsbn().equals(isbn)) {
                return livros[i];
            }
        }
        return null;
    }

    public void listarLivros() {
        for (int i = 0; i < totalLivros; i++) {
            System.out.println(livros[i]);
        }
    }

    @Override
    public String toString() {
        return "Biblioteca: " + nome + ", Endereço: " + endereco;
    }
}
