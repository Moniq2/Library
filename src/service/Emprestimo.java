package service;
import model.Livro;
import java.util.ArrayList;
import java.util.Scanner;

public class Emprestimo {
    
    void emprestar(Livro livro){
        ArrayList<Livro> livrosEmprestados = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o código do livro que deseja pedir emprestado:\n");
        int codigoLivro = entrada.nextInt();

        if (livrosEmprestados.size() <= 3) {
            
        }
    }
}
