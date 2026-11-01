package com.projeto.estruturas;

public class Fila<T> {
    private Pilha<T> pilhaEntrada;
    private Pilha<T> pilhaSaida;

    public Fila() {
        this.pilhaEntrada = new Pilha<>();
        this.pilhaSaida = new Pilha<>();
    }

    public void enfileirar(T valor) {
        pilhaEntrada.empilhar(valor);
    }

    public T desenfileirar() {
        if (estaVazia()) return null;
        transferirSeNecessario();
        return pilhaSaida.desempilhar();
    }

    public T primeiro() {
        if (estaVazia()) return null;
        transferirSeNecessario();
        return pilhaSaida.topo();
    }

    private void transferirSeNecessario() {
        if (pilhaSaida.estaVazia()) {
            while (!pilhaEntrada.estaVazia()) {
                pilhaSaida.empilhar(pilhaEntrada.desempilhar());
            }
        }
    }

    public boolean estaVazia() {
        return pilhaEntrada.estaVazia() && pilhaSaida.estaVazia();
    }

    public int getTamanho() {
        return pilhaEntrada.getTamanho() + pilhaSaida.getTamanho();
    }
}
