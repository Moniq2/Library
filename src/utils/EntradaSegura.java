package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntradaSegura {
    private Scanner scan = new Scanner(System.in);
    
    public int lerInt() { // Ler um inteiro com segurança.
        while (true) {
            try {
                int value = scan.nextInt();
                scan.nextLine(); // limpa o buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número inteiro válido!");
                scan.nextLine(); // limpa o buffer
            }
        }
    }

    public void close() {
        scan.close();
    }
}
