package br.com.biblioteca.dao;

import br.com.biblioteca.exception.UsuarioException;
import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.model.Professor;
import br.com.biblioteca.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    public Usuario buscarUsuarioPorLogin(String email, String senha) {
        String SQL = "SELECT * FROM USUARIO WHERE email = ?;";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String senhaHash = rs.getString("senha");
                if (!BCrypt.checkpw(senha, senhaHash)) {
                    throw new UsuarioException("Não foi possível pegar dados do usuário com email " + email);
                }

                if (rs.getString("tipo").equalsIgnoreCase("aluno")) {
                    String nome = rs.getString("nome");
                    Aluno aluno = new Aluno(nome, email, senhaHash);
                    aluno.setId(rs.getInt("id"));
                    rs.close();
                    return aluno;

                } else {
                    String nome = rs.getString("nome");
                    Professor professor = new Professor(nome, email, senhaHash);
                    professor.setId(rs.getInt("id"));
                    rs.close();
                    return professor;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UsuarioException("Não foi possível pegar dados do usuário com email " + email);
    }

    public < T extends Usuario> void salvarUsuario (T usuario) {
        String SQL = "INSERT INTO usuario (nome, email, senha, tipo) VALUES (?, ?, ?, ?);";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement ps = conn.prepareStatement(SQL);) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            if ( usuario instanceof Aluno) {
                ps.setString(4, "aluno");
            }
            else {
                ps.setString(4, "professor");
            }

            if (ps.executeUpdate() >= 1){
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        throw new UsuarioException("Não foi possível salvar cadastro.");
    }
}
