package com.projeto.estruturas;

import java.util.ArrayList;

public class HeapBinaria<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public HeapBinaria() {
        this.heap = new ArrayList<>();
    }

    public void inserir(T valor) {
        heap.add(valor);
        subir(heap.size() - 1);
    }

    public T extrairMaximo() {
        if (estaVazia()) return null;
        T maximo = heap.get(0);
        T ultimo = heap.remove(heap.size() - 1);
        if (!estaVazia()) {
            heap.set(0, ultimo);
            descer(0);
        }
        return maximo;
    }

    private void subir(int indice) {
        while (indice > 0) {
            int pai = (indice - 1) / 2;
            if (heap.get(indice).compareTo(heap.get(pai)) <= 0) break;
            trocar(indice, pai);
            indice = pai;
        }
    }

    private void descer(int indice) {
        int maior = indice;
        int esquerda = 2 * indice + 1;
        int direita = 2 * indice + 2;

        if (esquerda < heap.size() && heap.get(esquerda).compareTo(heap.get(maior)) > 0) {
            maior = esquerda;
        }
        if (direita < heap.size() && heap.get(direita).compareTo(heap.get(maior)) > 0) {
            maior = direita;
        }

        if (maior != indice) {
            trocar(indice, maior);
            descer(maior);
        }
    }

    private void trocar(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean estaVazia() {
        return heap.isEmpty();
    }

    public int getTamanho() {
        return heap.size();
    }

    public T espiarMaximo() {
        return estaVazia() ? null : heap.get(0);
    }
}
