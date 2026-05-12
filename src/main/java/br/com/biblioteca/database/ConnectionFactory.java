package br.com.biblioteca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/Biblioteca";
    private static final String USUARIO = "root";
    private static final String SENHA = "minhasenha";

    public static Connection conectar() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        catch (SQLException e){
            System.out.println("Erro ao executar operação no banco: " + e.getMessage());
            System.out.println("Código de erro: " + e.getErrorCode());
            System.out.println("SQLState: " + e.getSQLState());
        }
        return null;
    }
}
