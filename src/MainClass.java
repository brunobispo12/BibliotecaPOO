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

        // Menu principal
        boolean sair = false;
        while (!sair) {
            exibirMenuPrincipal();
            int opcao = lerOpcaoMenu();
            
            switch (opcao) {
                case 1:
                    cadastrarCategoria();
                    break;
                case 2:
                    cadastrarLivro();
                    break;
                case 3:
                    consultarLivro();
                    break;
                case 4:
                    excluirLivro();
                    break;
                case 5:
                    sistemaBiblioteca.listarLivros();
                    break;
                case 0:
                    sair = true;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static int lerOpcaoMenu() {
        int opcao = -1;
        while (opcao == -1) {
            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();  // Consumir a nova linha
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Digite um número.");
                scanner.nextLine();  // Limpa o buffer de entrada
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
        System.out.println("5. Listar Todos os Livros");
        System.out.println("0. Sair");
    }

    private static void cadastrarCategoria() {
        int codigo = -1;
        while (codigo == -1) {
            try {
                System.out.print("Digite o código da categoria: ");
                codigo = scanner.nextInt();
                scanner.nextLine();  // Consumir a nova linha
            } catch (InputMismatchException e) {
                System.out.println("Código inválido. Digite um número.");
                scanner.nextLine();  // Limpa o buffer de entrada
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
                System.out.println("ISBN inválido. Deve conter apenas números.");
            }
        } while (!isbn.matches("\\d+"));

        int codigoCategoria = -1;
        while (codigoCategoria == -1) {
            try {
                System.out.print("Digite o código da categoria do livro: ");
                codigoCategoria = scanner.nextInt();
                scanner.nextLine();  // Consumir a nova linha
            } catch (InputMismatchException e) {
                System.out.println("Código inválido. Digite um número.");
                scanner.nextLine();  // Limpa o buffer de entrada
            }
        }

        Categoria categoria = sistemaBiblioteca.consultarCategoriaPorCodigo(codigoCategoria);
        if (categoria == null) {
            System.out.println("Categoria não encontrada. Cadastro cancelado.");
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
            System.out.println("Livro encontrado: ");
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
}
