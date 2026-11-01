package com.projeto.estruturas;

public class Pilha<T> {
    private ListaEncadeada<T> lista;

    public Pilha() {
        this.lista = new ListaEncadeada<>();
    }

    public void empilhar(T valor) {
        lista.adicionarInicio(valor);
    }

    public T desempilhar() {
        if (estaVazia()) return null;
        return lista.removerInicio();
    }

    public T topo() {
        return lista.getPrimeiro();
    }

    public boolean estaVazia() {
        return lista.estaVazia();
    }

    public int getTamanho() {
        return lista.getTamanho();
    }
}
