import Models.Categoria;
import Models.Livro;

public class SistemaBiblioteca {

    private Categoria[] categorias = new Categoria[10];  
    private Livro[] livros = new Livro[50];  
    private int totalCategorias = 0;
    private int totalLivros = 0;


    public void cadastrarCategoria(Categoria categoria) {
        if (totalCategorias < categorias.length) {
            categorias[totalCategorias] = categoria;
            totalCategorias++;
        } else {
            System.out.println("Capacidade máxima de categorias atingida.");
        }
    }

    public Categoria consultarCategoriaPorCodigo(int codigo) {
        for (int i = 0; i < totalCategorias; i++) {
            if (categorias[i].getCodigo() == codigo) {
                return categorias[i];
            }
        }
        return null;  
    }


    public Categoria consultarCategoriaPorNome(String nome) {
        for (int i = 0; i < totalCategorias; i++) {
            if (categorias[i].getNome().equalsIgnoreCase(nome)) {
                return categorias[i];
            }
        }
        return null;  
    }


    public void adicionarLivro(Livro livro) {
        if (totalLivros < livros.length) {
            livros[totalLivros] = livro;
            totalLivros++;
        } else {
            System.out.println("Capacidade máxima de livros atingida.");
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
        if (totalLivros == 0) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (int i = 0; i < totalLivros; i++) {
                System.out.println(livros[i]);
            }
        }
    }


    public boolean excluirLivro(String isbn) {
        for (int i = 0; i < totalLivros; i++) {
            if (livros[i].getIsbn().equals(isbn)) {
                
                for (int j = i; j < totalLivros - 1; j++) {
                    livros[j] = livros[j + 1];
                }
                livros[totalLivros - 1] = null; 
                totalLivros--;
                return true;  
            }
        }
        return false;  
    }
}
