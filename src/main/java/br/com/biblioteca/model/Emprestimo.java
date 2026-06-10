package br.com.biblioteca.model;
import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo {
    private Usuario usuario;
    private int codigo;
    private Livro livro;
    private Exemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataEntrega;
    private boolean ativo = true;

    public Emprestimo(Usuario usuario, Livro livro, Exemplar exemplar){
        setUsuario(usuario);
        setLivro(livro);
        setExemplar(exemplar);
    }

    public int getCodigo() {
        return codigo;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Exemplar getExemplar() { return exemplar; }
    public Livro getLivro() { return livro; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataEntrega() { return dataEntrega; }

    public boolean isAtivo() {
        return ativo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setDatas(){
        this.dataEmprestimo = LocalDate.now();
        this.dataEntrega = dataEmprestimo.plusDays(15);
    }

    public void setLivro(Livro livro) {
        Objects.requireNonNull(livro, "Livro inválido");
        this.livro = livro;
    }

    public void setExemplar(Exemplar exemplar) {
        Objects.requireNonNull(exemplar, "Exemplar inválido.");
        exemplar.setDisponivel(false);
        this.exemplar = exemplar;
    }

    public void setUsuario(Usuario user) {
        Objects.requireNonNull(user, "Usuário Inválido");
        this.usuario = user;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}
