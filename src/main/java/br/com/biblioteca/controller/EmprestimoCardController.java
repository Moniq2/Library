package br.com.biblioteca.controller;

import br.com.biblioteca.exception.EmprestimoException;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.LibrarySystem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class EmprestimoCardController {
    Emprestimo emprestimo;
    LibrarySystem libSystem = new LibrarySystem();

    @FXML
    private Label tituloLabel;
    @FXML
    private Label dataEntregaLabel;
    @FXML
    private Label autorLabel;
    @FXML
    private Button devolverButton;
    @FXML
    private Button renovarButton;
    @FXML
    private GridPane emprestimoGrid;

    public void setEmprestimo(Emprestimo emprestimo){
        this.emprestimo = emprestimo;
        Livro livro = emprestimo.getLivro();
        tituloLabel.setText(emprestimo.getLivro().getTitulo());
        dataEntregaLabel.setText(emprestimo.getDataEntrega().toString());
        autorLabel.setText(livro.getAutor());

        if (!emprestimo.isAtivo()){
            tornarDevolvido();
        }
    }

    public void renovar() {
        try {
        Emprestimo emprestimoAtualizado = libSystem.renovarEmprestimo(emprestimo);
        setEmprestimo(emprestimoAtualizado);
        }
        catch (EmprestimoException e){
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ocorreu um erro ao renovar este emprestimo.\n" + e.getMessage());
            alert.getDialogPane().setPrefWidth(500);
            alert.getDialogPane().setPrefHeight(250);
            alert.showAndWait();
        }
    }

    public void devolver(){
        try {
            libSystem.devolverLivro(emprestimo);
        }
        catch (EmprestimoException e){
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ocorreu um erro ao realizar a devolução, tente novamente mais tarde.");
            alert.showAndWait();
            return;
        }
        tornarDevolvido();
    }

    private void tornarDevolvido() //Essa função serve para remover os botões de renovar e devolver, uma vez que não é possível realizar nenhuma dessas ações após a devolução.
    {
        renovarButton.setVisible(false);
        renovarButton.setManaged(false);

        devolverButton.setVisible(false);
        devolverButton.setManaged(false);

        Label texto = new Label();
        texto.setText("Devolução registrada.");
        texto.setFont(new Font(12));
        emprestimoGrid.add(texto, 4, 1);
    }
}
