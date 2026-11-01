package com.projeto.estruturas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstruturasTest {

    @Test
    public void testePilha() {
        Pilha<Integer> pilha = new Pilha<>();
        pilha.empilhar(1);
        pilha.empilhar(2);
        pilha.empilhar(3);
        assertEquals(3, pilha.desempilhar());
        assertEquals(2, pilha.desempilhar());
        assertEquals(1, pilha.desempilhar());
        assertTrue(pilha.estaVazia());
    }

    @Test
    public void testeFilaComDuasPilhas() {
        Fila<Integer> fila = new Fila<>();
        fila.enfileirar(1);
        fila.enfileirar(2);
        fila.enfileirar(3);
        assertEquals(1, fila.desenfileirar());
        assertEquals(2, fila.desenfileirar());
        assertEquals(3, fila.desenfileirar());
        assertTrue(fila.estaVazia());
    }

    @Test
    public void testeArvoreAVL() {
        ArvoreAVL<Integer> avl = new ArvoreAVL<>();
        avl.inserir(10);
        avl.inserir(20);
        avl.inserir(30); // Deve causar uma rotação
        assertTrue(avl.buscar(10));
        assertTrue(avl.buscar(20));
        assertTrue(avl.buscar(30));
        assertFalse(avl.buscar(40));
    }

    @Test
    public void testeHeapBinaria() {
        HeapBinaria<Integer> heap = new HeapBinaria<>();
        heap.inserir(10);
        heap.inserir(30);
        heap.inserir(20);
        assertEquals(30, heap.extrairMaximo());
        assertEquals(20, heap.extrairMaximo());
        assertEquals(10, heap.extrairMaximo());
    }

    @Test
    public void testeTabelaHash() {
        TabelaHash<String, String> hash = new TabelaHash<>();
        hash.put("chave1", "valor1");
        hash.put("chave2", "valor2");
        assertEquals("valor1", hash.get("chave1"));
        assertEquals("valor2", hash.get("chave2"));
        hash.remover("chave1");
        assertNull(hash.get("chave1"));
    }
}
