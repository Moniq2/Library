package model;
import java.util.ArrayList;
import exceptions.ExemplarNaoEncontradoException;

public class Livro {
    private String titulo;
    private String autor; 
    private ArrayList<Exemplar>exemplares = new ArrayList<>(); //Lista de exemplares do livro
    private static int proximoCodigo = 100;
    private int codigo = 0;

    public Livro(String titulo, String autor){
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = proximoCodigo++;
    }

    public void adicionarExemplar(){
        Exemplar exemplar = new Exemplar(this);
        exemplares.add(exemplar);
    }

    public boolean estaDisponivel(){ //Verifica se existem exemplares disponíveis desse livro
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getDisponivel()) {
                return true;
            }
        }
        return false;
    }

    public Exemplar buscaExemplar(int codigo) throws ExemplarNaoEncontradoException{
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getCodigo() == codigo) {
                return exemplar;
            }
        }
        throw new ExemplarNaoEncontradoException("O exemplar com o código " + codigo + " não existe.");
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
       
        return autor;
    }
    
    public Exemplar getExemplarDisponivel() throws ExemplarNaoEncontradoException{
    for (Exemplar exemplar : exemplares) {
        if (exemplar.getDisponivel()) {
            return exemplar;
        }
    }
    throw new ExemplarNaoEncontradoException("Não foi encontrado exemplares disponíveis para este livro.");
    }
    public void setAutor (String autor) throws IllegalArgumentException {
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("Autor inválido.");
        }
        this.autor = autor;
    }
    public void setTitulo (String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título inválido.");
        }
        this.titulo = titulo;
    }
}
