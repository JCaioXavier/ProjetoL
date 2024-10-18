//GerenciadorDeIngredientes
package Util;

import Entidades.Ingrediente;

import java.util.List;
import java.util.Scanner;

import static Util.RevisarOpcao.simOuNao;

public class GerenciadorDeIngredientes {

    public static void confirmarDadosIngredientes(Ingrediente ingrediente) { //PEDE A CONFIRMAÇÃO DOS DADOS ************************
        System.out.println("Os dados do produto estão corretos?" +
                "\nId_produto: " + ingrediente.id_ingrediente + "\n" +
                "Nome: " + ingrediente.nome + "\n" +
                "Estoque: " + ingrediente.estoque + "\n");
    }

    public static Ingrediente cadastrarIngrediente(List<Ingrediente> IngredienteExistente, int i) { //CADASTRO --------------
        Ingrediente novoIngrediente = new Ingrediente();
        Scanner scanner = new Scanner(System.in);
        int id = IngredienteExistente.size();

        if (i != (-1)) { // é usado quando o adm for editar um produto cadastrado
            novoIngrediente.id_ingrediente = i; // mantém o id do produto existente
        } else {
            novoIngrediente.id_ingrediente = id + 1; // incrementa apenas se for um novo produto
        }

        checkadordeIngredientes(novoIngrediente, IngredienteExistente);

        do {
            try {
                System.out.print("Digite o estoque do Ingrediente: ");
                String novoEstoqueString = scanner.nextLine();

                Integer.parseInt(novoEstoqueString);

                if (!novoEstoqueString.isEmpty()) {
                    novoIngrediente.estoque = Integer.parseInt(novoEstoqueString); // Atu
                    break;
                } else if (novoIngrediente.estoque < 0) {
                    System.out.println("Estoque não pode ser menor que 0! Digite novamente.");
                }
            } catch (NumberFormatException erro) {
                System.out.println("[ERROR]");
            }
        } while (true);

        return novoIngrediente;
    }

    private static void checkadordeIngredientes(Ingrediente novoIngrediente, List<Ingrediente> IngredientesExistentes) { //VERIFICA SE USUÁRIO JA EXISTE
        Scanner scanner = new Scanner(System.in);
        boolean espacoEmbrancoUm = true;
        boolean espacoEmbrancoDois;
        boolean ingredienteExistente;

        do {
            ingredienteExistente = false; // Reseta o flag a cada iteração

            while (espacoEmbrancoUm) {
                System.out.print("Digite o nome do Ingrediente: ");
                novoIngrediente.nome = scanner.nextLine();

                if (novoIngrediente.nome.isEmpty()) {
                    System.out.println("Ingrediente não pode ser vazio ou apenas espaços! Digite novamente.");
                    continue;
                }
                espacoEmbrancoUm = false;
                for (Ingrediente ingrediente : IngredientesExistentes) {
                    espacoEmbrancoDois = true;
                    if (ingrediente.nome.equals(novoIngrediente.nome)) {
                        ingredienteExistente = true;

                        do {
                            if ((ingrediente.nome.equals(novoIngrediente.nome))) {
                                System.out.println("Ingrediente já cadastrado. Tente novamente.");
                            }

                            System.out.print("Digite o nome do Ingrediente: ");
                            novoIngrediente.nome = scanner.nextLine();

                            if (novoIngrediente.nome.isEmpty()) {
                                System.out.println("Nome do ingrediente não pode ser vazio ou apenas espaços! Digite novamente.");
                                continue;
                            }
                            espacoEmbrancoDois = false;
                        } while (espacoEmbrancoDois);

                        break; // Saia do loop se o usuário já existir
                    } else {
                        break;
                    }
                }
                // Verifica se o usuário já existe
            }
        } while (ingredienteExistente); // Continua pedindo até um usuário único ser inserido

    }

    public static void removerIngrediente(List<Ingrediente> ingredientes, int indexIngrediente) {
        if (indexIngrediente >= 0 && indexIngrediente < ingredientes.size()) {
            ingredientes.remove(indexIngrediente);
            // Atualiza os IDs dos produtos após a remoção
            for (int i = 0; i < ingredientes.size(); i++) {
                ingredientes.get(i).id_ingrediente = i + 1;
            }
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Não existe produto com esse ID!");
        }
    }

    public static void editarIngrediente(List<Ingrediente> ingredientes, int indexIngrediente) {
        Scanner scanner = new Scanner(System.in);
        int simOuNao;

        if (indexIngrediente >= 0 && indexIngrediente < ingredientes.size()) {
            Ingrediente ingredienteAtual = ingredientes.get(indexIngrediente);

            System.out.println("\nIngrediente atual: " + ingredienteAtual);// mostra as informações do produto escolhido

            checkadordeIngredientes(ingredienteAtual, ingredientes);

            //nome
            do {//estoque
                try {
                    System.out.print("Digite o novo estoque do ingrediente: ");
                    String novoEstoqueString = scanner.nextLine();

                    Integer.parseInt(novoEstoqueString);

                    if (!novoEstoqueString.isEmpty()) {
                        System.out.println(novoEstoqueString);
                        System.out.println("\nConfirmar novo estoque?");
                        simOuNao = simOuNao();
                        if (simOuNao == 1) {
                            ingredienteAtual.estoque = Integer.parseInt(novoEstoqueString); // Atu
                            break;// Atualiza
                        }
                    }
                } catch (NumberFormatException erro) {
                    System.out.println("[ERROR]");
                }
            } while (true);

            System.out.println("Produto editado com sucesso!");

        } else {
            System.out.println("Não existe produto com esse ID!");
        }

    }

    public static int pegarIngrediente(List<Ingrediente> ingredientes) {//PEGAR ID DO FUNCIONÁRIO -----------------------
        Scanner scanner = new Scanner(System.in);
        int idIngrediente = 0;
        do {
            try {
                idIngrediente = scanner.nextInt(); //2 -thiago
                if (idIngrediente == 1) {
                    idIngrediente--;
                    break;
                }
                idIngrediente--;

                //0-adm, 1-thiago
                if ((ingredientes.size() < idIngrediente) || (0 >= idIngrediente)) {//2 < 1 || 0 > 1
                    System.out.println("Não existe funcionário com esse id! Digite novamente.");
                }
            } catch (IndexOutOfBoundsException erro) {
                System.out.println("[ERROR], digite uma opção valida. ");
            }
        } while ((ingredientes.size() < idIngrediente) || (0 >= idIngrediente));
        return idIngrediente;
    }
}
