package br.com.biblioteca.model;
import org.mindrot.jbcrypt.BCrypt;

abstract public class Usuario {
    private String nome;
    private String email;
    private int id;
    private String senha;

    public Usuario(String nome, String email, String senha){
        setNome(nome);
        setEmail(email);
        setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
    }

    public abstract int getLimiteEmprestimos();

    public String getNome() {
        return nome;
    }
    public int getId() {
        return id;
    }
    public String getSenha() {return senha; }
    public String getEmail() {return email; }

    public void setNome(String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        this.nome = nome;
    }

    public void setEmail(String email){
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public void setSenha(String senha){
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha inválida.");
        }
        this.senha = senha;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        this.id = id;
    }
}
