package br.com.biblioteca.service;
import br.com.biblioteca.dao.ConnectionFactory;
import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.dao.ExemplarDAO;
import br.com.biblioteca.exception.*;
import br.com.biblioteca.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LibrarySystem {
    private final ExemplarDAO exemplarDAO = new ExemplarDAO();
    private final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    public void devolverLivro(Emprestimo emprestimo) {
        Connection conn = null;
        try{
            conn = ConnectionFactory.conectar();
            conn.setAutoCommit(false);

            emprestimoDAO.desativarEmprestimo(conn, emprestimo.getCodigo());
            exemplarDAO.setDisponibilidade(conn, true, emprestimo.getExemplar().getCodigo());

            conn.commit();
        }
        catch (Exception e){
            try {
                conn.rollback();
            }
            catch (SQLException ex){
                e.printStackTrace();

            }
            throw new EmprestimoException("Não foi possível realizar a devolução, tente novamente mais tarde.");
        }
        finally {
            if(conn != null){
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public Emprestimo emprestarLivro(Usuario usuario, Livro livro) {

        if (calcularNumEmprestimosAtivos(usuario) >= usuario.getLimiteEmprestimos()){
            throw new EmprestimoException("Limite de emprestimos atingido.");
        }

        if(temEmprestimosAtrasados(usuario)){
            throw new EmprestimoException("Não é possível realizar novos emprestimos com emprestimos atrasados.");
        }

        Exemplar exemplar = exemplarDAO.buscarExemplarDisponivel(livro);
        if (exemplar == null){
            throw new ExemplarException("Não foi possível encontrar um exemplar para o livro");
        }

        Emprestimo emprestimo =  new Emprestimo(usuario, livro, exemplar);
        emprestimo.setDatas();

        Connection conn = null;
        try{
            conn = ConnectionFactory.conectar();
            conn.setAutoCommit(false);

            emprestimoDAO.salvarEmprestimo(conn, emprestimo);
            exemplarDAO.setDisponibilidade(conn,false, exemplar.getCodigo());

            conn.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            try {
                conn.rollback();
            }
            catch (SQLException ex){
                e.printStackTrace();

            }
            throw new EmprestimoException("Não foi possível emprestar livro, tente novamente mais tarde.");
        }
        finally {
            if(conn != null){
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return emprestimo;
    }

    public Emprestimo renovarEmprestimo(Emprestimo emprestimo) {
        LocalDate dataEntrega = emprestimo.getDataEntrega();
        LocalDate dataMaxima = emprestimo.getDataEmprestimo().plusDays(45);

        if (!dataEntrega.isEqual(LocalDate.now().minusDays(3))){
            throw new EmprestimoException("Não foi possível renovar este emprestimo no momento, você só poderá renovar seus emprestimos a partir de 3 dias antes da data de entrega.");
        }

        if (dataEntrega.plusDays(15).isEqual(dataMaxima) || dataEntrega.plusDays(15).isAfter(dataMaxima)){
            throw new EmprestimoException("Não foi possível renovar emprestimo. Limite de renovações atingido.");
        }

        LocalDate novaDataEntrega = dataEntrega.plusDays(15);
        emprestimo.setDataEntrega(novaDataEntrega);

        int codigoEmprestimo = emprestimo.getCodigo();
        emprestimoDAO.atualizarDataDeEntrega(codigoEmprestimo, novaDataEntrega);
        return emprestimo;
    }

    public Boolean temEmprestimosAtrasados(Usuario usuario){
       ArrayList<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimos(usuario);
       for(Emprestimo emprestimo: emprestimos){
           if(emprestimo.getDataEntrega().isBefore(LocalDate.now())){
               return true;
           }
       }
       return false;
    }

    public int calcularNumEmprestimosAtivos(Usuario usuario){
        ArrayList<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimos(usuario);
        int contador = 0;
        for(Emprestimo emprestimo: emprestimos){
            if (emprestimo.isAtivo()) {
                contador += 1;
            }
        }
        return contador;
    }

    public int calcularNumDevolucoes(Usuario usuario){
        ArrayList<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimos(usuario);
        int contador = 0;
        for(Emprestimo emprestimo: emprestimos){
            if (emprestimo.isAtivo() && emprestimo.getDataEntrega().isEqual(LocalDate.now())) {
                contador += 1;
            }
        }
        return contador;
    }
}

