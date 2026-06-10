package br.com.biblioteca.controller;

import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Livro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;

public class LivrosController {
    private LivroDAO livroDAO = new LivroDAO();

    @FXML
    private VBox livrosContainer;
    @FXML
    private TextField caixaDePesquisa;

    private MenuLateralController menuLateralController;

    public void setMenuLateralController(MenuLateralController menuLateralController) {
        this.menuLateralController = menuLateralController;
        carregarLivros();
    }

    private void carregarLivros() {
        LivroDAO dao = new LivroDAO();
        ArrayList<Livro> livros = dao.listarLivros();
        mostrarLivros(livros);
    }

    @FXML
    private void pesquisar() {
        String termo = caixaDePesquisa.getText();

        if (termo == null || termo.isBlank()) {
            livrosContainer.getChildren().clear();
            Label mensagem = new Label("Por favor digite um termo de pesquisa.");
            mensagem.getStyleClass().add("texto-cinza");
            mensagem.setStyle("-fx-font-size: 15px;");
            livrosContainer.getChildren().add(mensagem);
            return;
        }

        ArrayList<Livro> livros = livroDAO.buscarLivros(termo);
        mostrarLivros(livros);
    }

    @FXML
    private void mostrarLivros(ArrayList<Livro> livros) {
        try {
            livrosContainer.getChildren().clear();

            if (livros.isEmpty()) {
                Label mensagem = new Label("Não foram encontrados resultados para essa pesquisa.");
                mensagem.getStyleClass().add("texto-cinza");
                mensagem.setStyle("-fx-font-size: 15px;");
                livrosContainer.getChildren().add(mensagem);
                return;
            }

            for (Livro livro : livros) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/livro-card.fxml"));
                Parent card = loader.load();
                LivroCardController controller = loader.getController();
                controller.setMenuLateralController(menuLateralController);
                controller.setLivro(livro);

                livrosContainer.getChildren().add(card);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

