package br.com.biblioteca.model;
import java.util.Objects;

public class Exemplar {
    private Livro livro;
    private int codigo;
    private boolean disponivel = true;

    public Livro getLivro(){
        return livro;
    }

    public int getCodigo(){
        return codigo;
    }

    public boolean getDisponivel(){
        return disponivel;
    }


    public void setLivro(Livro livro){
        Objects.requireNonNull(livro, "livro inválido.");
        this.livro = livro;
    }

    public void setCodigo(int codigo){
        Objects.requireNonNull(codigo, "código inválido.");
        this.codigo = codigo;
    }

    public void setDisponivel(Boolean bool){
        Objects.requireNonNull(bool, "Booleano inválido.");
        disponivel = bool;
    }
}
