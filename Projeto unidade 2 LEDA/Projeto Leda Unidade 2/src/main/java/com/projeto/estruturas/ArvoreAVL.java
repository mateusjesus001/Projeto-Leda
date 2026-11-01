package com.projeto.estruturas;

public class ArvoreAVL<T extends Comparable<T>> {
    private No<T> raiz;

    private static class No<T> {
        T valor;
        No<T> esquerda, direita;
        int altura;

        No(T valor) {
            this.valor = valor;
            this.altura = 1;
        }
    }

    public void inserir(T valor) {
        raiz = inserir(raiz, valor);
    }

    private No<T> inserir(No<T> no, T valor) {
        if (no == null) return new No<>(valor);

        if (valor.compareTo(no.valor) < 0) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor.compareTo(no.valor) > 0) {
            no.direita = inserir(no.direita, valor);
        } else {
            return no; // Valores duplicados não permitidos
        }

        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));
        return balancear(no);
    }

    private int getAltura(No<T> no) {
        return no == null ? 0 : no.altura;
    }

    private int getFatorBalanceamento(No<T> no) {
        return no == null ? 0 : getAltura(no.esquerda) - getAltura(no.direita);
    }

    private No<T> balancear(No<T> no) {
        int fb = getFatorBalanceamento(no);

        if (fb > 1) {
            if (getFatorBalanceamento(no.esquerda) < 0) {
                no.esquerda = rotarEsquerda(no.esquerda);
            }
            return rotarDireita(no);
        }

        if (fb < -1) {
            if (getFatorBalanceamento(no.direita) > 0) {
                no.direita = rotarDireita(no.direita);
            }
            return rotarEsquerda(no);
        }

        return no;
    }

    private No<T> rotarDireita(No<T> y) {
        No<T> x = y.esquerda;
        No<T> t2 = x.direita;
        x.direita = y;
        y.esquerda = t2;
        y.altura = 1 + Math.max(getAltura(y.esquerda), getAltura(y.direita));
        x.altura = 1 + Math.max(getAltura(x.esquerda), getAltura(x.direita));
        return x;
    }

    private No<T> rotarEsquerda(No<T> x) {
        No<T> y = x.direita;
        No<T> t2 = y.esquerda;
        y.esquerda = x;
        x.direita = t2;
        x.altura = 1 + Math.max(getAltura(x.esquerda), getAltura(x.direita));
        y.altura = 1 + Math.max(getAltura(y.esquerda), getAltura(y.direita));
        return y;
    }

    public boolean buscar(T valor) {
        return buscar(raiz, valor);
    }

    private boolean buscar(No<T> no, T valor) {
        if (no == null) return false;
        if (valor.compareTo(no.valor) == 0) return true;
        return valor.compareTo(no.valor) < 0 ? buscar(no.esquerda, valor) : buscar(no.direita, valor);
    }

    public T buscarElemento(T valor) {
        return buscarElemento(raiz, valor);
    }

    private T buscarElemento(No<T> no, T valor) {
        if (no == null) return null;
        if (valor.compareTo(no.valor) == 0) return no.valor;
        return valor.compareTo(no.valor) < 0 ? buscarElemento(no.esquerda, valor) : buscarElemento(no.direita, valor);
    }

    public void remover(T valor) {
        raiz = remover(raiz, valor);
    }

    private No<T> remover(No<T> no, T valor) {
        if (no == null) return null;

        if (valor.compareTo(no.valor) < 0) {
            no.esquerda = remover(no.esquerda, valor);
        } else if (valor.compareTo(no.valor) > 0) {
            no.direita = remover(no.direita, valor);
        } else {
            if (no.esquerda == null || no.direita == null) {
                no = (no.esquerda == null) ? no.direita : no.esquerda;
            } else {
                No<T> sucessor = encontrarMinimo(no.direita);
                no.valor = sucessor.valor;
                no.direita = remover(no.direita, sucessor.valor);
            }
        }

        if (no == null) return null;

        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));
        return balancear(no);
    }

    private No<T> encontrarMinimo(No<T> no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }
}
