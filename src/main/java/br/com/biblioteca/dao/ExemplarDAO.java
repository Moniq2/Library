package br.com.biblioteca.dao;

import br.com.biblioteca.exception.ExemplarException;
import br.com.biblioteca.exception.LivroNaoEncontradoException;
import br.com.biblioteca.model.Exemplar;
import br.com.biblioteca.model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExemplarDAO {
    LivroDAO livroDAO = new LivroDAO();

    public Exemplar buscarExemplarDisponivel(Livro livro) {
        String SQL = "SELECT * FROM exemplar WHERE id_livro = ? AND disponivel = TRUE ORDER BY id LIMIT 1";
        try (Connection conn = ConnectionFactory.conectar()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, livro.getCodigo());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Exemplar exemplar = new Exemplar();
                    exemplar.setLivro(livro);
                    exemplar.setDisponivel(rs.getBoolean("disponivel"));
                    exemplar.setCodigo(rs.getInt("id"));
                    return exemplar;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Exemplar buscarExemplarPorID(int id){
        String SQL = "SELECT * FROM exemplar WHERE id = ?";
        try(Connection conn = ConnectionFactory.conectar()) {
            assert conn != null;
            try(PreparedStatement ps = conn.prepareStatement(SQL)){
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                Exemplar exemplar = new Exemplar();

                if (rs.next()) {
                    Livro livro = livroDAO.buscarLivroPorID(rs.getInt("id_livro"));
                    exemplar.setLivro(livro);
                    exemplar.setCodigo(rs.getInt("id"));
                    exemplar.setDisponivel(rs.getBoolean("disponivel"));
                }
                rs.close();
                return exemplar;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        throw new LivroNaoEncontradoException("Exemplar não encontrado no banco de dados.");
    }

    public void setDisponibilidade(Connection conn, Boolean valor, int id_exemplar){
        String SQL = "UPDATE exemplar SET disponivel = ? WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(SQL)){
            ps.setBoolean(1, valor);
            ps.setInt(2, id_exemplar);
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Mensagem: " + e.getMessage());
            System.out.println("Código: " + e.getErrorCode());
            e.printStackTrace();
            throw new ExemplarException("Não foi possível definir exemplar como disponível");
        }
    }
}
