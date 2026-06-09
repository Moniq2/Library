package br.com.biblioteca.model;

public class Aluno extends Usuario {
    public Aluno(String nome, String email, String senha){
        super(nome, email, senha);
    }
    @Override
    public int getLimiteEmprestimos(){
        return 3;
    }
}
