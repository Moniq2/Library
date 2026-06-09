package br.com.biblioteca.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Livro {
    private String titulo;
    private String autor;
    private int codigo;
    private LocalDate dataPublicacao;

    public int getCodigo() {
        return codigo;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() { return autor; }
    public LocalDate getDataPublicacao() {return dataPublicacao; }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public void setAutor (String autor) {
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

    public void setDataPublicacao(LocalDate data) {
        if(data.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data inválida.");
        }
        dataPublicacao = data;
    }


}
