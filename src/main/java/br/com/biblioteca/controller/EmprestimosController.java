package br.com.biblioteca.controller;

import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;

public class EmprestimosController {
    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    Usuario usuario;

    @FXML
    private VBox emprestimos_container;

    @FXML
    private void initialize(){}

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        mostrarEmprestimos();
    }

    private void mostrarEmprestimos(){
        try {
            emprestimos_container.getChildren().clear();
            ArrayList<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimos(usuario);

            if (emprestimos.isEmpty()) {
                Label mensagem = new Label("Você não tem nenhum emprestimo cadastrado até agora.");
                mensagem.getStyleClass().add("texto-cinza");
                mensagem.setStyle("-fx-font-size: 15px;");
                emprestimos_container.getChildren().add(mensagem);
                return;
            }

            for (Emprestimo emprestimo : emprestimos) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/emprestimo-card.fxml"));
                Parent card = loader.load();
                EmprestimoCardController controller = loader.getController();

                controller.setEmprestimo(emprestimo);
                emprestimos_container.getChildren().add(card);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
