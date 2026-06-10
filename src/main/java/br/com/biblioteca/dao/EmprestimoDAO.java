package br.com.biblioteca.dao;

import br.com.biblioteca.exception.EmprestimoException;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Exemplar;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmprestimoDAO {
    private final LivroDAO livroDAO = new LivroDAO();
    private final ExemplarDAO exemplarDAO = new ExemplarDAO();

    public void salvarEmprestimo(Connection conn, Emprestimo emp) {
        String SQL = "INSERT INTO emprestimo (id_usuario, id_exemplar, id_livro, data_emprestimo, data_entrega) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(SQL))
        {
            ps.setInt(1, emp.getUsuario().getId());
            ps.setInt(2, emp.getExemplar().getCodigo());
            ps.setInt(3, emp.getLivro().getCodigo());
            ps.setDate(4, Date.valueOf(emp.getDataEmprestimo()));
            ps.setDate(5, Date.valueOf(emp.getDataEntrega()));

            if (!(ps.executeUpdate() == 1)){
                throw new SQLException("Falha ao salvar emprestimo no banco de dados.");
            }

        } catch (SQLException e) {
            System.out.println("Mensagem: " + e.getMessage());
            System.out.println("Código: " + e.getErrorCode());
            e.printStackTrace();
            throw new EmprestimoException("Erro ao salvar emprestimo.");
        }
    }

    public ArrayList<Emprestimo> listarEmprestimos(Usuario usuario){ //Retorna uma Arraylist de Emprestimos
        String SQL = "SELECT * FROM emprestimo WHERE id_usuario = ?";
        try (Connection conn = ConnectionFactory.conectar()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, usuario.getId());
                ResultSet rs = ps.executeQuery();

                ArrayList<Emprestimo> lista = new ArrayList<>();

                while (rs.next()){
                    Livro livro = livroDAO.buscarLivroPorID(rs.getInt("id_livro"));

                    Exemplar exemplar = exemplarDAO.buscarExemplarPorID(rs.getInt("id_exemplar"));

                    Emprestimo emprestimo = new Emprestimo(usuario, livro, exemplar);
                    emprestimo.setCodigo(rs.getInt("id"));
                    emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
                    emprestimo.setDataEntrega(rs.getDate("data_entrega").toLocalDate());
                    emprestimo.setAtivo(rs.getBoolean("ativo"));

                    lista.add(emprestimo);
                }
                rs.close();
                return lista;
            }
        }
        catch (Exception e){
            System.out.println("Erro ao listar emprestimos.");
            System.out.println("Mensagem: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void desativarEmprestimo(Connection conn, int id) {
        String SQL = "UPDATE emprestimo SET ativo = false WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return;

        } catch (SQLException e) {
            System.out.println("Mensagem: " + e.getMessage());
            System.out.println("Código: " + e.getErrorCode());
            e.printStackTrace();
        }

        throw new EmprestimoException("Não foi possível desativar emprestimo de id" + id);
    }

    public void atualizarDataDeEntrega(int id, LocalDate data) {
        String SQL = "UPDATE emprestimo SET data_entrega = ? WHERE id = ?";
        try(Connection conn = ConnectionFactory.conectar()) {
            assert conn != null;
            try(PreparedStatement ps = conn.prepareStatement(SQL)){
                ps.setDate(1, Date.valueOf(data));
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }
        catch (SQLException e){
            System.out.println("Mensagem: " + e.getMessage());
            System.out.println("Código: " + e.getErrorCode());
            e.printStackTrace();
        }
        throw new EmprestimoException("Não foi possível atualizar data de entrega emprestimo de id" + id);
    }
}
