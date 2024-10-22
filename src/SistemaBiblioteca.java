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

    public void alterarCategoria(int codigo, String novoNome) {
        Categoria categoria = consultarCategoriaPorCodigo(codigo);
        if (categoria != null) {
            categoria.setNome(novoNome);
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    public boolean excluirCategoria(int codigo) {
        for (int i = 0; i < totalCategorias; i++) {
            if (categorias[i].getCodigo() == codigo) {
                for (int j = i; j < totalCategorias - 1; j++) {
                    categorias[j] = categorias[j + 1];
                }
                categorias[totalCategorias - 1] = null;
                totalCategorias--;
                return true;
            }
        }
        return false;
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

    public void alterarLivro(String isbn, String novoTitulo, String novoAutor) {
        Livro livro = consultarLivroPorISBN(isbn);
        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
        } else {
            System.out.println("Livro não encontrado.");
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

    public void listarLivros() {
        if (totalLivros == 0) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (int i = 0; i < totalLivros; i++) {
                System.out.println(livros[i]);
            }
        }
    }

    public void listarCategorias() {
        if (totalCategorias == 0) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            for (int i = 0; i < totalCategorias; i++) {
                System.out.println(categorias[i]);
            }
        }
    }
}
