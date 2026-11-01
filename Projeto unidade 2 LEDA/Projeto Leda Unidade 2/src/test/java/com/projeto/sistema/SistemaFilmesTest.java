package com.projeto.sistema;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaFilmesTest {

    @Test
    public void testeIntegracaoSistema() {
        SistemaFilmes sistema = new SistemaFilmes();
        
        Filme f1 = new Filme(1, "O Poderoso Chefão", "Drama", 9.2, 1000000);
        Filme f2 = new Filme(2, "Matrix", "Ação/Ficção", 8.7, 800000);
        Filme f3 = new Filme(3, "O Rei Leão", "Animação", 8.5, 950000);
        
        sistema.adicionarFilme(f1);
        sistema.adicionarFilme(f2);
        sistema.adicionarFilme(f3);
        
        // Teste Hash Table (Busca por ID)
        assertEquals(f1, sistema.buscarPorId(1));
        assertEquals(f2, sistema.buscarPorId(2));
        
        // Teste AVL (Busca por Título)
        assertEquals(f3, sistema.buscarPorTitulo("O Rei Leão"));
        assertNull(sistema.buscarPorTitulo("Inexistente"));
        
        // Teste Heap (Tendências)
        List<Filme> top = sistema.obterTendencias(2);
        assertEquals(2, top.size());
        assertEquals(f1, top.get(0)); // F1 tem nota 9.2
        assertEquals(f2, top.get(1)); // F2 tem nota 8.7 (mais que 8.5 de F3)
        
        // Teste Queue (Fila de Reprodução)
        sistema.adicionarAFila(f3);
        sistema.adicionarAFila(f1);
        assertEquals(2, sistema.getTamanhoFila());
        assertEquals(f3, sistema.reproduzirProximo());
        assertEquals(f1, sistema.reproduzirProximo());
        assertTrue(sistema.getTamanhoFila() == 0);
    }
}
