package model;

public class Livro {
    private String titulo;
    private String autor;
    private int codigo; //Indíce do livro no array
    Boolean disponivel;

    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
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
    }

    public String setTitulo() {
        return titulo;
    }
}
