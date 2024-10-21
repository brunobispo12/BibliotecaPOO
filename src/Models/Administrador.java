package Models;

public class Administrador {
    private String login;
    private String senha;

    public Administrador(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

}
