package br.com.biblioteca.controller;

import java.io.IOException;
import br.com.biblioteca.model.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MenuLateralController {
    @FXML
    private StackPane conteudo;
    @FXML
    private Label nomeUsuario;

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        nomeUsuario.setText(usuario.getNome());
        this.usuario = usuario;
    }

    public Usuario getUsuario(){ return usuario; };

    public <T> T carregarTela(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent tela = loader.load();
            conteudo.getChildren().setAll(tela);
            return loader.getController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void iniciar() {
        HomeController homeController = carregarTela("/fxml/home.fxml");
        homeController.setUsuario(usuario);
    }

    @FXML
    private void abrirLivros() {
        LivrosController controller = carregarTela("/fxml/livros.fxml");
        controller.setMenuLateralController(this);
    }

    @FXML
    private void abrirEmprestimos() {
        EmprestimosController controller = carregarTela("/fxml/emprestimos.fxml");
        controller.setUsuario(usuario);
    }

    @FXML
    private void initialize(){}
}
