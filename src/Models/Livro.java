package Models;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;
    private Categoria categoria;

    public Livro(String titulo, String autor, String isbn, Categoria categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.disponivel = true; 
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Categoria: " + categoria.getNome() + ", Disponível: " + (disponivel ? "Sim" : "Não");
    }
}
