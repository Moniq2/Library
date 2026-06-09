package br.com.biblioteca.model;

public class Professor extends Usuario {
    public Professor(String nome, String email, String senha){
        super(nome, email, senha);
    }
    @Override
    public int getLimiteEmprestimos(){
        return 6;
    } 
}
