package model;
import java.util.ArrayList;

abstract public class Usuario {
    private String nome;
    private String email;
    private static int proxId = 1000;
    private int id;
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public abstract int limiteEmprestimos();
    
    public Usuario(String nome, String email) throws IllegalArgumentException{
    if (nome == null || nome.isBlank()) {
        throw new IllegalArgumentException("Nome inválido.");
    }

    if (email == null || email.isBlank()) {
        throw new IllegalArgumentException("Email inválido.");
    }

    this.nome = nome;
    this.email = email;
    this.id = proxId++;
    }

    public int quantEmprestimos() {
        return emprestimos.size();
    }

    public void addEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(int codigo){
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCodigo() == codigo) {
                emprestimos.remove(emprestimo);
            }
        }
    }

    public void mostrarEmprestimos(){
        if (emprestimos.size() == 0) {
            System.out.println("Não há emprestimos cadastrados.");
            return;
        }
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("Livro emprestado: " + emprestimo.getTituloLivro() + "\ncódigo do emprestimo: " +  emprestimo.getCodigo());
        }
    }

    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }
}
