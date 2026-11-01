package com.projeto.sistema;

import java.util.Objects;

public class Filme implements Comparable<Filme> {
    private Integer id;
    private String titulo;
    private String genero;
    private Double nota;
    private Integer visualizacoes;

    public Filme(Integer id, String titulo, String genero, Double nota, Integer visualizacoes) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.nota = nota;
        this.visualizacoes = visualizacoes;
    }

    public Integer getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public Double getNota() { return nota; }
    public Integer getVisualizacoes() { return visualizacoes; }

    public void setNota(Double nota) { this.nota = nota; }
    public void setVisualizacoes(Integer visualizacoes) { this.visualizacoes = visualizacoes; }

    @Override
    public int compareTo(Filme outro) {
        // Para a Heap (Tendências), vamos priorizar pela nota.
        // Se a nota for igual, priorizamos pelas visualizações.
        int compNota = this.nota.compareTo(outro.nota);
        if (compNota != 0) return compNota;
        return this.visualizacoes.compareTo(outro.visualizacoes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id) || Objects.equals(titulo, filme.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo);
    }

    @Override
    public String toString() {
        return String.format("Filme{id=%d, titulo='%s', genero='%s', nota=%.1f, visualizacoes=%d}",
                id, titulo, genero, nota, visualizacoes);
    }
}
