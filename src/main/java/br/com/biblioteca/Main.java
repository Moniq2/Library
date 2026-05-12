package br.com.biblioteca;
import br.com.biblioteca.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException{
        Connection conn = ConnectionFactory.conectar();
    }




}
