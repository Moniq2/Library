package br.com.biblioteca.controller;

import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.exception.UsuarioException;
import br.com.biblioteca.model.Aluno;
import br.com.biblioteca.model.Professor;
import br.com.biblioteca.model.Usuario;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


public class PagCadastroController {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private TextField campoEmail;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoSenha;
    @FXML
    private RadioButton alunoCheckbox;
    @FXML
    private RadioButton professorCheckbox;
    @FXML
    private Label mensagemLabel;

    private ToggleGroup tipoUsuarioGroup;

    @FXML
    private void initialize() {
        tipoUsuarioGroup = new ToggleGroup();
        alunoCheckbox.setToggleGroup(tipoUsuarioGroup);
        professorCheckbox.setToggleGroup(tipoUsuarioGroup);
    }


    @FXML
    private void cadastrar(Event event){
        String email = campoEmail.getText();
        String nome = campoNome.getText();
        String senha = campoSenha.getText();
        Usuario usuario;

        if (email == null || nome == null || senha == null){
            mensagemLabel.setText("Por favor preencha todos os campos..");
            mensagemLabel.setOpacity(1);
            return;
        }
        try {
            if (alunoCheckbox.isSelected()) {
                usuario = new Aluno(nome, email, senha);
            } else if (professorCheckbox.isSelected()) {
                usuario = new Professor(nome, email, senha);
            } else {
                mensagemLabel.setText("Por favor selecione uma opção.");
                mensagemLabel.setOpacity(1);
                return;
            }

            usuarioDAO.salvarUsuario(usuario);

            mensagemLabel.setText("Cadastro realizado!");

            usuario = usuarioDAO.buscarUsuarioPorLogin(email, senha); //Busquei o usuário no banco pois o banco que vai gerar o id do usuário
            abrirTelaInicial(event, usuario);

        } catch (Exception e){
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ocorreu um erro ao realizar o cadastro.\n" + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void abrirTelaInicial(Event event, Usuario usuario){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu-lateral.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            MenuLateralController controller = loader.getController();
            controller.setUsuario(usuario);
            controller.iniciar();

            Scene cenaAtual = stage.getScene();
            Scene scene = new Scene(root, cenaAtual.getWidth(), cenaAtual.getHeight());

            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Biblioteca");
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
