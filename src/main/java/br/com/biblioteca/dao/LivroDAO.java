package br.com.biblioteca.dao;

import br.com.biblioteca.exception.LivroNaoEncontradoException;
import br.com.biblioteca.model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroDAO {
    public ArrayList<Livro> listarLivros(){ //Retorna uma Arraylist de Livros
        String SQL = "SELECT * FROM livro";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(SQL);) {
            ArrayList<Livro> lista = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Livro livro = new Livro();
                livro.setCodigo(rs.getInt("id"));
                livro.setAutor(rs.getString("autor"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setDataPublicacao(rs.getDate("data_publicacao").toLocalDate());

                lista.add(livro);
            }
            return lista;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ArrayList<Livro> buscarLivros (String termo){
        String SQL = "SELECT * FROM livro WHERE titulo LIKE ? OR autor LIKE ?";
        try(Connection conn = ConnectionFactory.conectar();
            PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setString(1, "%" + termo + "%");
            ps.setString(2, "%" + termo + "%");

            ArrayList<Livro> livros = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Livro livro = new Livro();
                livro.setDataPublicacao((rs.getDate("data_publicacao")).toLocalDate());
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setCodigo(rs.getInt("id"));
                livros.add(livro);
            }
            return livros;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Livro buscarLivroPorID(int id){
        String SQL = "SELECT * FROM livro WHERE id = ?";
        try(Connection conn = ConnectionFactory.conectar();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Livro livro = new Livro();

            if (rs.next()) {
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setCodigo(rs.getInt("id"));
                livro.setDataPublicacao(rs.getDate("data_publicacao").toLocalDate());
            }
            return livro;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        throw new LivroNaoEncontradoException("Livro não encontrado no banco de dados.");
    }
}
