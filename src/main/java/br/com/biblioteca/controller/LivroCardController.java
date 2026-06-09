package br.com.biblioteca.controller;

import br.com.biblioteca.model.Livro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LivroCardController {
    private Livro livro;
    private MenuLateralController menuLateralController;

    @FXML
    private Label tituloLabel;

    @FXML
    private Label autorLabel;

    @FXML
    private Label codigoLabel;

    @FXML
    private Label anoLabel;

    public void setMenuLateralController(MenuLateralController menuLateralController) {
        this.menuLateralController = menuLateralController;
    }

    @FXML
    public void setLivro(Livro livro) {
        tituloLabel.setText(livro.getTitulo());
        autorLabel.setText(livro.getAutor());
        codigoLabel.setText(String.valueOf(livro.getCodigo()));
        anoLabel.setText(String.valueOf(livro.getDataPublicacao()));
        this.livro = livro;
    }

    public void abrirTelaEmprestimo() {
        PagEmprestimoController pagController = menuLateralController.carregarTela("/fxml/pag-emprestimo.fxml");
        pagController.setLivro(this.livro);
        pagController.setUsuario(menuLateralController.getUsuario());
    }
}
