package com.projeto.sistema;

import com.projeto.estruturas.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaFilmes {
    private TabelaHash<Integer, Filme> catalogoPorId;
    private ArvoreAVL<FilmePorTitulo> catalogoPorTitulo;
    private HeapBinaria<Filme> tendencias;
    private Fila<Filme> filaDeReproducao;

    // Classe auxiliar para buscar filmes por título na AVL
    private static class FilmePorTitulo implements Comparable<FilmePorTitulo> {
        String titulo;
        Filme filme;

        FilmePorTitulo(String titulo, Filme filme) {
            this.titulo = titulo;
            this.filme = filme;
        }

        @Override
        public int compareTo(FilmePorTitulo outro) {
            return this.titulo.compareToIgnoreCase(outro.titulo);
        }
    }

    public SistemaFilmes() {
        this.catalogoPorId = new TabelaHash<>();
        this.catalogoPorTitulo = new ArvoreAVL<>();
        this.tendencias = new HeapBinaria<>();
        this.filaDeReproducao = new Fila<>();
    }

    public void adicionarFilme(Filme filme) {
        catalogoPorId.put(filme.getId(), filme);
        catalogoPorTitulo.inserir(new FilmePorTitulo(filme.getTitulo(), filme));
        tendencias.inserir(filme);
    }

    public Filme buscarPorId(Integer id) {
        return catalogoPorId.get(id);
    }

    public Filme buscarPorTitulo(String titulo) {
        FilmePorTitulo resultado = catalogoPorTitulo.buscarElemento(new FilmePorTitulo(titulo, null));
        return resultado != null ? resultado.filme : null;
    }

    public List<Filme> obterTendencias(int quantidade) {
        List<Filme> top = new ArrayList<>();
        HeapBinaria<Filme> copia = new HeapBinaria<>();
        
        // Como a extração remove o elemento, vamos usar uma cópia temporária
        // ou reinserir os elementos depois (aqui vamos simular a extração)
        // Nota: Para simplificar no sistema real, poderíamos ter uma lista de tendências.
        // Mas para demonstrar a Heap:
        
        // Vamos extrair os top N e depois reinserir para não perder dados
        List<Filme> extraidos = new ArrayList<>();
        for (int i = 0; i < quantidade && !tendencias.estaVazia(); i++) {
            Filme f = tendencias.extrairMaximo();
            top.add(f);
            extraidos.add(f);
        }
        
        for (Filme f : extraidos) {
            tendencias.inserir(f);
        }
        
        return top;
    }

    public void adicionarAFila(Filme filme) {
        filaDeReproducao.enfileirar(filme);
    }

    public Filme reproduzirProximo() {
        return filaDeReproducao.desenfileirar();
    }

    public int getTamanhoCatalogo() {
        return catalogoPorId.getTamanho();
    }
    
    public int getTamanhoFila() {
        return filaDeReproducao.getTamanho();
    }

    public void removerFilme(int id) {
        Filme filmeParaRemover = catalogoPorId.get(id);
        if (filmeParaRemover == null) {
            System.out.println("\nFilme com ID " + id + " não encontrado para remoção.");
            return;
        }

        // 1. Remove da Tabela Hash
        catalogoPorId.remover(id);

        // 2. Remove da Árvore AVL
        catalogoPorTitulo.remover(new FilmePorTitulo(filmeParaRemover.getTitulo(), null));

        // 3. Reconstrói a Heap de Tendências
        reconstruirHeapTendencias();

        System.out.println("\nFilme '" + filmeParaRemover.getTitulo() + "' removido com sucesso.");
    }

    private void reconstruirHeapTendencias() {
        List<Filme> todosOsFilmes = catalogoPorId.getTodosValores();
        tendencias = new HeapBinaria<>();
        for (Filme f : todosOsFilmes) {
            tendencias.inserir(f);
        }
    }
}
