package com.projeto.estruturas;

public class ListaEncadeada<T> {
    private No<T> cabeca;
    private int tamanho;

    private static class No<T> {
        T valor;
        No<T> proximo;

        No(T valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public ListaEncadeada() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    public void adicionarInicio(T valor) {
        No<T> novoNo = new No<>(valor);
        novoNo.proximo = cabeca;
        cabeca = novoNo;
        tamanho++;
    }

    public void adicionarFim(T valor) {
        No<T> novoNo = new No<>(valor);
        if (estaVazia()) {
            cabeca = novoNo;
        } else {
            No<T> atual = cabeca;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
        tamanho++;
    }

    public T removerInicio() {
        if (estaVazia()) return null;
        T valor = cabeca.valor;
        cabeca = cabeca.proximo;
        tamanho--;
        return valor;
    }

    public boolean estaVazia() {
        return cabeca == null;
    }

    public int getTamanho() {
        return tamanho;
    }

    public T getPrimeiro() {
        return estaVazia() ? null : cabeca.valor;
    }

    public T buscar(T valor) {
        No<T> atual = cabeca;
        while (atual != null) {
            if (atual.valor.equals(valor)) {
                return atual.valor;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public void remover(T valor) {
        if (estaVazia()) return;
        if (cabeca.valor.equals(valor)) {
            cabeca = cabeca.proximo;
            tamanho--;
            return;
        }
        No<T> atual = cabeca;
        while (atual.proximo != null) {
            if (atual.proximo.valor.equals(valor)) {
                atual.proximo = atual.proximo.proximo;
                tamanho--;
                return;
            }
            atual = atual.proximo;
        }
    }

    public java.util.List<T> paraLista() {
        java.util.List<T> lista = new java.util.ArrayList<>();
        No<T> atual = cabeca;
        while (atual != null) {
            lista.add(atual.valor);
            atual = atual.proximo;
        }
        return lista;
    }
}
