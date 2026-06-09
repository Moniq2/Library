package br.com.biblioteca.controller;

import br.com.biblioteca.exception.EmprestimoException;
import br.com.biblioteca.exception.ExemplarException;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Exemplar;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.LibrarySystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PagEmprestimoController {
    private Livro livro;
    private Usuario usuario;
    private LibrarySystem librarySystem = new LibrarySystem();

    @FXML
    private Label tituloLivro;
    @FXML
    private Label autorLivro;
    @FXML
    private Label dataEmprestimo;
    @FXML
    private Label dataEntrega;
    @FXML
    private Label codigoExemplar;
    @FXML
    private VBox caixa_info_emprestimo;
    @FXML
    private Button emprestarButton;

    public void setLivro(Livro livro) {
        this.livro = livro;
        tituloLivro.setText(livro.getTitulo());
        autorLivro.setText(livro.getAutor());
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void mostrarCaixaResultado() {
        caixa_info_emprestimo.getStyleClass().remove("painel-oculto");
        caixa_info_emprestimo.getStyleClass().add("faixa-info-emprestimo");
    }

    @FXML
    private void pedirEmprestado() {
        emprestarButton.setVisible(false);
        emprestarButton.setManaged(false);
        Label texto = new Label();

        try {
            Emprestimo emprestimo = librarySystem.emprestarLivro(usuario, livro);

            Exemplar exemplar = emprestimo.getExemplar();

            codigoExemplar.setText(String.valueOf(exemplar.getCodigo()));
            dataEmprestimo.setText(emprestimo.getDataEmprestimo().toString());
            dataEntrega.setText(emprestimo.getDataEntrega().toString());
            mostrarCaixaResultado();

            return;

        } catch (ExemplarException e) {
            texto.setText("Não foi possível realizar o empréstimo! Não existem exemplares disponíveis para esse livro no momento.");
            e.printStackTrace();
        }
        catch (EmprestimoException e) {
            texto.setText(e.getMessage());
        }
        
        caixa_info_emprestimo.getChildren().clear();
        texto.setWrapText(true);
        texto.getStyleClass().add("texto-detalhe");

        caixa_info_emprestimo.getChildren().add(texto);
        mostrarCaixaResultado();
    }
}
