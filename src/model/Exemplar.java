package model;

public class Exemplar {
    private Livro livro;
    private boolean disponivel = true;
    private static int proxCodigo = 0;
    private int codigo;


    public Exemplar(Livro livro){
        this.livro = livro;
        this.codigo = proxCodigo++;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getCodigo() {
        return codigo;
    }
    public Livro getLivro() {
        return livro;
    }
    public Boolean getDisponivel() {
        return disponivel;
    }
}
