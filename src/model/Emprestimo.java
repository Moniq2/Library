package model;

public class Emprestimo {
    private Exemplar exemplar;
    private Usuario usuario;
    private static int proxCodigo = 100;
    private int codigo;

    public Emprestimo(Exemplar exemplar, Usuario usuario){
        this.exemplar = exemplar;
        this.usuario = usuario;
        exemplar.setDisponivel(false);
        this.codigo = proxCodigo++;
    }
    public String getTituloLivro(){
        return exemplar.getLivro().getTitulo();
    }
    public int getCodigo() {
        return codigo;
    }
    public Exemplar getExemplar(){
        return exemplar;
    }
    public Usuario getUsuario() {
        return usuario;
    }
}
