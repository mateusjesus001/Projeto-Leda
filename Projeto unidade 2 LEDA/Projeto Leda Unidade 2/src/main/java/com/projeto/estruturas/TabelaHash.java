package com.projeto.estruturas;

import java.util.Objects;

public class TabelaHash<K, V> {
    private static class Entrada<K, V> {
        K chave;
        V valor;

        Entrada(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Entrada<?, ?> entrada = (Entrada<?, ?>) obj;
            return Objects.equals(chave, entrada.chave);
        }

        @Override
        public int hashCode() {
            return Objects.hash(chave);
        }
    }

    private ListaEncadeada<Entrada<K, V>>[] tabela;
    private int tamanho;
    private static final int CAPACIDADE_INICIAL = 16;

    @SuppressWarnings("unchecked")
    public TabelaHash() {
        this.tabela = new ListaEncadeada[CAPACIDADE_INICIAL];
        for (int i = 0; i < CAPACIDADE_INICIAL; i++) {
            tabela[i] = new ListaEncadeada<>();
        }
        this.tamanho = 0;
    }

    private int calcularHash(K chave) {
        return Math.abs(chave.hashCode()) % tabela.length;
    }

    public void put(K chave, V valor) {
        int indice = calcularHash(chave);
        ListaEncadeada<Entrada<K, V>> lista = tabela[indice];
        
        java.util.List<Entrada<K, V>> entradas = lista.paraLista();
        for (Entrada<K, V> entrada : entradas) {
            if (entrada.chave.equals(chave)) {
                entrada.valor = valor;
                return;
            }
        }
        
        lista.adicionarInicio(new Entrada<>(chave, valor));
        tamanho++;
    }

    public V get(K chave) {
        int indice = calcularHash(chave);
        ListaEncadeada<Entrada<K, V>> lista = tabela[indice];
        
        java.util.List<Entrada<K, V>> entradas = lista.paraLista();
        for (Entrada<K, V> entrada : entradas) {
            if (entrada.chave.equals(chave)) {
                return entrada.valor;
            }
        }
        return null;
    }

    public void remover(K chave) {
        int indice = calcularHash(chave);
        ListaEncadeada<Entrada<K, V>> lista = tabela[indice];
        
        java.util.List<Entrada<K, V>> entradas = lista.paraLista();
        for (Entrada<K, V> entrada : entradas) {
            if (entrada.chave.equals(chave)) {
                lista.remover(entrada);
                tamanho--;
                return;
            }
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public java.util.List<V> getTodosValores() {
        java.util.List<V> valores = new java.util.ArrayList<>();
        for (ListaEncadeada<Entrada<K, V>> lista : tabela) {
            java.util.List<Entrada<K, V>> entradas = lista.paraLista();
            for (Entrada<K, V> entrada : entradas) {
                valores.add(entrada.valor);
            }
        }
        return valores;
    }
}
