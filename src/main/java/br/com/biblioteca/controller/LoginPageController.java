package br.com.biblioteca.controller;

import br.com.biblioteca.exception.UsuarioException;
import br.com.biblioteca.model.Usuario;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import br.com.biblioteca.dao.UsuarioDAO;

public class LoginPageController {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    MenuLateralController menuLateralController = new MenuLateralController();
    @FXML
    private TextField campoEmail;
    @FXML
    private TextField campoSenha;
    @FXML
    private Label mensagemLabel;

    @FXML
    private void abrirPagCadastro(Event event){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                    getClass().getResource("/fxml/pag-cadastro.fxml"),
                    "FXML não encontrado: /fxml/pag-cadastro.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene cenaAtual = stage.getScene();
            Scene scene = new Scene(root, cenaAtual.getWidth(), cenaAtual.getHeight());

            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void abrirTelaInicial(Event event, Usuario usuario){
        try{
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                    getClass().getResource("/fxml/menu-lateral.fxml"),
                    "FXML não encontrado: /fxml/menu-lateral.fxml"));

            Parent root = loader.load();
            menuLateralController = loader.getController();
            menuLateralController.setUsuario(usuario);
            menuLateralController.iniciar();

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            Scene cenaAtual = stage.getScene();
            Scene scene = new Scene(root, cenaAtual.getWidth(), cenaAtual.getHeight());

            stage.setScene(scene);
            stage.setTitle("Biblioteca");
            stage.setMaximized(true);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void logar(Event event){
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        if (email.trim().isEmpty() || senha.trim().isEmpty()){
            mensagemLabel.setText("Por favor, preencha todos os campos para prosseguir.");
            mensagemLabel.setOpacity(1.0);
            return ;
        }

        try {
            Usuario usuario = usuarioDAO.buscarUsuarioPorLogin(email, senha);
            abrirTelaInicial(event, usuario);

        } catch (UsuarioException e){
            mensagemLabel.setText("Email e/ou senha incorretas.");
            mensagemLabel.setOpacity(1.0);
            System.out.println(e.getMessage());
        }

    }
}
