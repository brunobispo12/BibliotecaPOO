import Models.Administrador;
import Models.Categoria;
import Models.Livro;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

    private static Scanner scanner = new Scanner(System.in);
    private static SistemaBiblioteca sistemaBiblioteca = new SistemaBiblioteca();
    private static Administrador administrador = new Administrador("admin", "senha123");

    public static void main(String[] args) {
        if (!autenticarAdministrador()) {
            System.out.println("Autenticação falhou. Programa encerrado.");
            return;
        }

        boolean sair = false;
        while (!sair) {
            exibirMenuPrincipal();
            int opcao = lerOpcaoMenu();
            switch (opcao) {
                case 1: cadastrarCategoria(); break;
                case 2: cadastrarLivro(); break;
                case 3: consultarLivro(); break;
                case 4: excluirLivro(); break;
                case 5: alterarLivro(); break;
                case 6: sistemaBiblioteca.listarLivros(); break;
                case 7: sistemaBiblioteca.listarCategorias(); break;
                case 8: alterarCategoria(); break;
                case 9: excluirCategoria(); break;
                case 0: sair = true; System.out.println("Saindo do sistema..."); break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static int lerOpcaoMenu() {
        int opcao = -1;
        while (opcao == -1) {
            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Digite um número.");
                scanner.nextLine();
            }
        }
        return opcao;
    }

    private static boolean autenticarAdministrador() {
        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        return administrador.autenticar(login, senha);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n----- Sistema da Biblioteca -----");
        System.out.println("1. Cadastrar Categoria");
        System.out.println("2. Cadastrar Livro");
        System.out.println("3. Consultar Livro");
        System.out.println("4. Excluir Livro");
        System.out.println("5. Alterar Livro");
        System.out.println("6. Listar Todos os Livros");
        System.out.println("7. Listar Todas as Categorias");
        System.out.println("8. Alterar Categoria");
        System.out.println("9. Excluir Categoria");
        System.out.println("0. Sair");
    }

    private static void cadastrarCategoria() {
        int codigo = -1;
        while (codigo == -1) {
            try {
                System.out.print("Digite o código da categoria: ");
                codigo = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Código inválido.");
                scanner.nextLine();
            }
        }
        System.out.print("Digite o nome da categoria: ");
        String nome = scanner.nextLine();
        Categoria categoria = new Categoria(codigo, nome);
        sistemaBiblioteca.cadastrarCategoria(categoria);
        System.out.println("Categoria cadastrada com sucesso.");
    }

    private static void cadastrarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        String isbn;
        do {
            System.out.print("Digite o ISBN do livro: ");
            isbn = scanner.nextLine();
            if (!isbn.matches("\\d+")) {
                System.out.println("ISBN inválido.");
            }
        } while (!isbn.matches("\\d+"));
        int codigoCategoria = -1;
        while (codigoCategoria == -1) {
            try {
                System.out.print("Digite o código da categoria: ");
                codigoCategoria = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Código inválido.");
                scanner.nextLine();
            }
        }
        Categoria categoria = sistemaBiblioteca.consultarCategoriaPorCodigo(codigoCategoria);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }
        Livro livro = new Livro(titulo, autor, isbn, categoria);
        sistemaBiblioteca.adicionarLivro(livro);
        System.out.println("Livro cadastrado com sucesso.");
    }

    private static void consultarLivro() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        Livro livro = sistemaBiblioteca.consultarLivroPorISBN(isbn);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void excluirLivro() {
        System.out.print("Digite o ISBN do livro a ser excluído: ");
        String isbn = scanner.nextLine();
        boolean sucesso = sistemaBiblioteca.excluirLivro(isbn);
        if (sucesso) {
            System.out.println("Livro excluído com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void alterarLivro() {
        System.out.print("Digite o ISBN do livro a ser alterado: ");
        String isbn = scanner.nextLine();
        System.out.print("Digite o novo título do livro: ");
        String novoTitulo = scanner.nextLine();
        System.out.print("Digite o novo autor do livro: ");
        String novoAutor = scanner.nextLine();
        sistemaBiblioteca.alterarLivro(isbn, novoTitulo, novoAutor);
    }

    private static void alterarCategoria() {
        System.out.print("Digite o código da categoria a ser alterada: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o novo nome da categoria: ");
        String novoNome = scanner.nextLine();
        sistemaBiblioteca.alterarCategoria(codigo, novoNome);
    }

    private static void excluirCategoria() {
        System.out.print("Digite o código da categoria a ser excluída: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        boolean sucesso = sistemaBiblioteca.excluirCategoria(codigo);
        if (sucesso) {
            System.out.println("Categoria excluída com sucesso.");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }
}
