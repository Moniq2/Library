package service;
import java.util.ArrayList;
import javax.naming.LimitExceededException;
import exceptions.*;
import model.*;

public class LibrarySystem {
    private ArrayList<Livro> acervoLivros = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Emprestimo> todosEmprestimos = new ArrayList<>();

    public void addLivro(Livro livro){
        acervoLivros.add(livro);
    }
    public Usuario buscarUsuario(int codigo) throws UsuarioNaoEncontradoException{
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == codigo) {
                return usuario;
            }
        }
        throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com esse ID na base de dados.");
    }
    public void listarLivros(){
        System.out.println("---------LIVROS---------\n");
        for (Livro livro : acervoLivros) {
            System.out.println(livro.getCodigo() + " - " + livro.getTitulo() + " Autor: " + livro.getAutor());
            if (livro.estaDisponivel()) {
                System.out.println("Status: Disponível\n");
            }
            else {
                System.out.println("Status: Indisponível\n");
            }
        }    
    }
    
    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void buscarLivroPorTitulo(String titulo) throws LivroNaoEncontradoException{ //Busca livros pelo título e mostra os encontrados.
        int contador = 0;
        for (Livro livro : acervoLivros) {
          
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                contador++;
                System.out.println("Codigo: " + livro.getCodigo() + " - " + livro.getTitulo() + " - Autor: " + livro.getAutor());

                if (livro.estaDisponivel()) {
                    System.out.println("Status: Disponível\n");
                }
                else {
                    System.out.println("Status: Indisponível\n");
                }
            }
        }
        if (contador == 0) {
            throw new LivroNaoEncontradoException("Nenhum livro com o título " + titulo + " foi encontrado.");
        }
    }

    public Livro buscarLivro(int codigo) throws LivroNaoEncontradoException{
        for (Livro livro : acervoLivros) {
            if (livro.getCodigo() == codigo) {
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("Livro com código " + codigo + " não encontrado!\n");
    }

    public void devolverLivro(int codigoEmprestimo) throws EmprestimoNaoEncontradoException{
        Emprestimo encontrado = null;
        for (Emprestimo emprestimo : todosEmprestimos) {
            if (emprestimo.getCodigo() == codigoEmprestimo) {
                encontrado = emprestimo;
            }
        }
        if (encontrado == null) {
            throw new EmprestimoNaoEncontradoException("Emprestimo com código " + codigoEmprestimo + " não encontrado.");
        }
        encontrado.getExemplar().setDisponivel(true);
        encontrado.getUsuario().removerEmprestimo(codigoEmprestimo); //Remove o emprestimo da lista de emprestimos do usuário
    }

    public void emprestarLivro(int codigo, Usuario usuario) throws LimitExceededException, LivroNaoEncontradoException, ExemplarNaoEncontradoException{
        if (usuario.limiteEmprestimos() == usuario.quantEmprestimos()) {
            throw new LimitExceededException("O número máximo de emprestimos foi atingido.");
        }
        Livro livro = buscarLivro(codigo); 
        Exemplar exemplar = livro.getExemplarDisponivel(); 
        exemplar.setDisponivel(false);
        Emprestimo emprestimo = new Emprestimo(exemplar, usuario);

        todosEmprestimos.add(emprestimo); // Adiciona o emprestimo criado na Array de emprestimos.
        usuario.addEmprestimo(emprestimo); //Adiciona o emprestimo na array de emprestimos do USuário.

        System.out.println("O livro " + livro.getTitulo() + " foi emprestado com sucesso.\nCódigo do emprestimo: " + emprestimo.getCodigo());
    }
}
