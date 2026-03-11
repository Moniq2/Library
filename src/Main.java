
import utils.EntradaSegura;
import java.util.Scanner;
import model.*;
import service.LibrarySystem;

public class Main {
    public static void main(String[] args) {
        Livro l1 = new Livro("O problema dos 3 corpos", "Cixin Liu");
        l1.adicionarExemplar();
        l1.adicionarExemplar();
        l1.adicionarExemplar();

        Livro l2 = new Livro("A floresta Negra", "Cixin Liu");
        l2.adicionarExemplar();
        l2.adicionarExemplar();
        l2.adicionarExemplar();
        l2.adicionarExemplar();
        
        Livro l3 = new Livro("A hora da estrela", "Clarice Lispector");
        l3.adicionarExemplar();
        l3.adicionarExemplar();

        Livro l4 = new Livro("Dom Casmurro", "Machado de Assis");
        l4.adicionarExemplar();
        l4.adicionarExemplar();

        Livro l5 = new Livro("Um estudo em Vermelho", "Arthur Conan Doyle");
        l5.adicionarExemplar();

        Livro l6 = new Livro("Drácula", "Bram Stoker");
        l6.adicionarExemplar();
        l6.adicionarExemplar();
        l6.adicionarExemplar();
        l6.adicionarExemplar();

        Livro l7 = new Livro("Bobby Fischer Teaches chess", "Bobby Fischer");
        l7.adicionarExemplar();

        Livro l8 = new Livro("Rede de Computadores", "Tanenbaum");
        l8.adicionarExemplar();
        l8.adicionarExemplar();
        l8.adicionarExemplar();
        l8.adicionarExemplar();
        l8.adicionarExemplar();
        l8.adicionarExemplar();
        l8.adicionarExemplar();
        l8.adicionarExemplar();

        LibrarySystem sistema = new LibrarySystem();
        sistema.addLivro(l1);
        sistema.addLivro(l2);
        sistema.addLivro(l3);
        sistema.addLivro(l4);
        sistema.addLivro(l5);
        sistema.addLivro(l6);
        sistema.addLivro(l7);
        sistema.addLivro(l8);

        EntradaSegura entrada = new EntradaSegura();
        Scanner scan = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n-------- BIBLIOTECA --------\nEscolha uma opção:");
            System.out.println("1- Exibir lista de livros do acervo");
            System.out.println("2- Pedir um livro emprestado");
            System.out.println("3- Devolver um livro");
            System.out.println("4- Verificar emprestimos");
            System.out.println("5- Sair");
            
            opcao = entrada.lerInt();
            

            while (opcao < 1 || opcao > 5){ //Válidar a entrada
                System.out.println("Por favor, Digite uma opção válida.(1-4)\n");
                opcao = entrada.lerInt();
            }
                
            switch (opcao){

                case 1:
                    sistema.listarLivros();
                    break;
                case 2:
                    Usuario user = null; //Váriavel para armazenar o usuário que irá executar ações

                    System.out.println("Você é um usuário cadastrado?\n1- Nunca peguei livros emprestados.\n2- Já tenho cadastro.");
                    int escolha = entrada.lerInt(); //Variável para armazenar opções digitadas pelo usuário

                    while (escolha != 1 && escolha != 2) {
                        System.out.println("Por favor, Digite uma opção válida. (1 ou 2)\n");
                        escolha = entrada.lerInt();
                    }

                    if (escolha == 1) { //Se o usuário escolher que nunca pegou livros emprestados
                        System.err.println("Você é: \n1- aluno \n2- Professor\n");
                        escolha = entrada.lerInt();

                        while (escolha != 1 && escolha != 2) {
                            System.out.println("Por favor, Digite uma opção válida. (1 ou 2)\n");
                            escolha = entrada.lerInt();
                        }

                        while (user == null) {
                            System.out.println("Por favor, digite seu nome:");
                            String nome = scan.nextLine();

                            System.out.println("Olá " + nome + "! Digite seu email:");
                            String email = scan.nextLine();

                            try {
                                if (escolha == 1) { //Se o usuário tiver escolhido que é aluno
                                    user = new Aluno(nome, email);
                                } else { //Se o o usuário tiver escolhido que é professor
                                    user = new Professor(nome, email);
                                }

                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        sistema.addUsuario(user);
                        System.out.println("Seu cadastro foi realizado!");
                        System.out.println("Seu ID de usuário é: " + user.getId());
                    }

                    else { //Se o usuário já tiver cadastro
                        while (user == null) {
                            System.out.println("Digite o seu ID de usuário: ");
                            int codigo = entrada.lerInt();
                            try {
                                user = sistema.buscarUsuario(codigo);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                System.out.println("Por favor digite um ID válido");
                            }
                        }
                    }

                    System.out.println("Deseja pesquisar um livro especifico pelo título (digite 1) ou já sabe qual livro vai pegar? (digite 2)");
                    escolha = entrada.lerInt();
                    
                    while (escolha != 1 && escolha != 2) {
                        System.out.println("Por favor, Digite uma opção válida. (1 ou 2)\n");
                        escolha = entrada.lerInt();
                    }
                    
                    if (escolha == 1) {
                        while(true){
                            System.out.println("Digite o título do livro: ");
                            String titulo = scan.nextLine();
                            try {
                                sistema.buscarLivroPorTitulo(titulo);
                            } catch (Exception e) {
                                    System.out.println(e.getMessage());
                            }    
                            System.out.println("Encontrou o livro que buscava? Sim(1) Buscar novamente(2)");
                            while (escolha != 1 && escolha != 2) {
                                System.out.println("Por favor, Digite uma opção válida. (1 ou 2)\n");
                                escolha = entrada.lerInt();
                            }
                            escolha = entrada.lerInt();
                            if (escolha == 1) {
                                break;
                            }
                        }
                    }
                    System.out.println("Digite o código do livro que vc deseja pedir emprestado: ");
                    int codigo = entrada.lerInt();
                    try {
                        sistema.emprestarLivro(codigo, user);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Digite o código de emprestimo do livro que deseja devolver ");
                    codigo = entrada.lerInt();
                    try {
                        sistema.devolverLivro(codigo);
                        System.out.println("Devolução feita com sucesso. Obrigado!");
                    
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;  
                case 4:
                    System.out.println("Digite o seu código de usuário: ");
                    codigo = entrada.lerInt();
                    try {
                        user = sistema.buscarUsuario(codigo);
                        user.mostrarEmprestimos();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }

        } while (opcao != 5);

        System.out.println("Obrigado por utilizar nossa biblioteca, volte sempre!");
        scan.close();
        entrada.close();
        }
}
