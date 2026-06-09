package br.com.biblioteca.controller;

import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.LibrarySystem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    Usuario usuario;
    LibrarySystem librarySystem = new LibrarySystem();

    @FXML
    private Label contagem_emprestimos;
    @FXML
    private Label contagem_devolucoes;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        contagem_emprestimos.setText(String.valueOf(librarySystem.calcularNumEmprestimosAtivos(usuario)));
        contagem_devolucoes.setText(String.valueOf(librarySystem.calcularNumDevolucoes(usuario)));
    }
}
