package com.projeto.sistema;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private static SistemaFilmes sistema = new SistemaFilmes();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pré-popular o sistema com alguns dados para facilitar o teste
        popularDadosIniciais();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("\nErro: Por favor, digite um número válido.");
            }
        }
        System.out.println("\nSaindo do sistema... Até logo!");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n================================================");
        System.out.println("       SISTEMA DE GERENCIAMENTO DE FILMES       ");
        System.out.println("================================================");
        System.out.println("1. Adicionar Novo Filme (Tabela Hash + AVL + Heap)");
        System.out.println("2. Buscar Filme por ID (Tabela Hash)");
        System.out.println("3. Buscar Filme por Título (Árvore AVL)");
        System.out.println("4. Ver Top Tendências (Max Heap)");
        System.out.println("5. Adicionar à Fila de Reprodução (Fila)");
        System.out.println("6. Reproduzir Próximo da Fila (Fila de Pilhas)");
        System.out.println("7. Ver Status do Catálogo");
        System.out.println("8. Remover Filme do Catálogo");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                adicionarFilme();
                break;
            case 2:
                buscarPorId();
                break;
            case 3:
                buscarPorTitulo();
                break;
            case 4:
                verTendencias();
                break;
            case 5:
                adicionarAFila();
                break;
            case 6:
                reproduzirProximo();
                break;
            case 7:
                System.out.println("\nTotal de filmes no catálogo: " + sistema.getTamanhoCatalogo());
                System.out.println("Filmes na fila de reprodução: " + sistema.getTamanhoFila());
                break;
            case 8:
                removerFilme();
                break;
            case 0:
                break;
            default:
                System.out.println("\nOpção inválida! Tente novamente.");
        }
    }

    private static void popularDadosIniciais() {
        sistema.adicionarFilme(new Filme(101, "O Poderoso Chefão", "Drama", 9.2, 5000));
        sistema.adicionarFilme(new Filme(102, "Matrix", "Ação/Sci-Fi", 8.7, 8000));
        sistema.adicionarFilme(new Filme(103, "O Rei Leão", "Animação", 8.5, 4500));
        sistema.adicionarFilme(new Filme(104, "Interstellar", "Sci-Fi", 8.6, 7000));
        sistema.adicionarFilme(new Filme(105, "Pulp Fiction", "Crime", 8.9, 6000));
    }

    private static void adicionarFilme() {
        System.out.print("\nID do filme: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        System.out.print("Nota (0.0 a 10.0): ");
        double nota = Double.parseDouble(scanner.nextLine());
        System.out.print("Visualizações: ");
        int views = Integer.parseInt(scanner.nextLine());

        Filme novo = new Filme(id, titulo, genero, nota, views);
        sistema.adicionarFilme(novo);
        System.out.println("\nFilme '" + titulo + "' adicionado com sucesso!");
    }

    private static void buscarPorId() {
        System.out.print("\nDigite o ID para busca: ");
        int id = Integer.parseInt(scanner.nextLine());
        Filme f = sistema.buscarPorId(id);
        if (f != null) {
            System.out.println("\nEncontrado: " + f);
        } else {
            System.out.println("\nFilme com ID " + id + " não encontrado.");
        }
    }

    private static void buscarPorTitulo() {
        System.out.print("\nDigite o título para busca: ");
        String titulo = scanner.nextLine();
        Filme f = sistema.buscarPorTitulo(titulo);
        if (f != null) {
            System.out.println("\nEncontrado: " + f);
        } else {
            System.out.println("\nFilme '" + titulo + "' não encontrado.");
        }
    }

    private static void verTendencias() {
        System.out.print("\nQuantos filmes quer ver no top? ");
        int qtd = Integer.parseInt(scanner.nextLine());
        List<Filme> top = sistema.obterTendencias(qtd);
        System.out.println("\n--- TOP " + top.size() + " TENDÊNCIAS ---");
        for (int i = 0; i < top.size(); i++) {
            System.out.println((i + 1) + "º: " + top.get(i).getTitulo() + " (Nota: " + top.get(i).getNota() + ")");
        }
    }

    private static void adicionarAFila() {
        System.out.print("\nDigite o ID do filme para a fila: ");
        int id = Integer.parseInt(scanner.nextLine());
        Filme f = sistema.buscarPorId(id);
        if (f != null) {
            sistema.adicionarAFila(f);
            System.out.println("\n'" + f.getTitulo() + "' adicionado à fila.");
        } else {
            System.out.println("\nFilme não encontrado.");
        }
    }

    private static void reproduzirProximo() {
        Filme f = sistema.reproduzirProximo();
        if (f != null) {
            System.out.println("\nReproduzindo agora: " + f.getTitulo());
            System.out.println("Detalhes: " + f);
        } else {
            System.out.println("\nA fila está vazia!");
        }
    }

    private static void removerFilme() {
        System.out.print("\nDigite o ID do filme que deseja remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        sistema.removerFilme(id);
    }
}
