# 🎬 Projeto LEDA Unidade 2 — Sistema de Gerenciamento de Filmes

Projeto desenvolvido para a disciplina **LEDA — Laboratório de Estrutura de Dados** (Unidade 2). Trata-se de um sistema de linha de comando (console) em Java para gerenciamento de um catálogo de filmes, com o objetivo de aplicar na prática diversas estruturas de dados implementadas **do zero**, sem utilizar as classes prontas do `java.util` para as estruturas principais.

---

## 📋 Sobre o Projeto

O sistema permite cadastrar filmes, buscar por ID ou título, consultar as principais tendências (filmes com melhor nota), adicionar filmes a uma fila de reprodução e reproduzi-los em ordem de chegada (FIFO). Cada operação do sistema é baseada em uma estrutura de dados específica, demonstrando como diferentes estruturas são mais adequadas para diferentes tipos de operação.

### Objetivos de Aprendizagem

- Implementar estruturas de dados lineares e não-lineares **do zero** em Java.
- Compreender o funcionamento interno de Tabela Hash, Árvore AVL, Heap Binária, Pilha, Fila e Lista Encadeada.
- Aplicar cada estrutura em um cenário realista de uso (catálogo de filmes).
- Escrever testes unitários e de integração com JUnit 5.

## 🗂️ Estrutura do Projeto

```text
Projeto Leda Unidade 2/
├── pom.xml                 # Configuração do Maven (Java 17, JUnit 5)
├── src/
│   ├── main/java/com/projeto/
│   │   ├── estruturas/     # Estruturas de dados implementadas do zero
│   │   │   ├── ListaEncadeada.java
│   │   │   ├── Pilha.java
│   │   │   ├── Fila.java
│   │   │   ├── TabelaHash.java
│   │   │   ├── HeapBinaria.java
│   │   │   └── ArvoreAVL.java
│   │   └── sistema/        # Classes do sistema de filmes
│   │       ├── Filme.java
│   │       ├── SistemaFilmes.java
│   │       └── Principal.java  (ponto de entrada com menu interativo)
│   └── test/java/com/projeto/
│       ├── estruturas/     # Testes unitários das estruturas
│       │   └── EstruturasTest.java
│       └── sistema/        # Testes de integração do sistema
│           └── SistemaFilmesTest.java
├── bin/                    # Classes compiladas (versão alternativa)
├── target/                 # Build do Maven
└── Como rodar.txt          # Instrução breve original do projeto
```

---

## 🏗️ Estruturas de Dados Implementadas

Todas as classes abaixo estão no pacote `com.projeto.estruturas` e foram implementadas manualmente:

| Estrutura | Arquivo | Características principais | Uso no sistema |
|-----------|---------|---------------------------|----------------|
| **Lista Encadeada** | `ListaEncadeada.java` | Lista simplesmente encadeada genérica com inserção no início/fim, remoção, busca por valor e conversão para `List`. | Base da Pilha e encadeamento de colisões da Tabela Hash |
| **Pilha** | `Pilha.java` | LIFO (Last-In, First-Out) implementada sobre Lista Encadeada. Operações: empilhar, desempilhar, topo, está vazia, tamanho. | Componente interno da Fila |
| **Fila** | `Fila.java` | FIFO (First-In, First-Out) implementada com **duas pilhas** (amortização eficiente). Operações: enfileirar, desenfileirar, primeiro, está vazia, tamanho. | Fila de reprodução de filmes |
| **Tabela Hash** | `TabelaHash.java` | Dicionário genérico com endereçamento separado por encadeamento (lista encadeada em cada bucket). Suporta `put`, `get`, `remover`, tamanho e listagem de todos os valores. | Catálogo de filmes indexado por ID |
| **Heap Binária** | `HeapBinaria.java` | Max Heap genérica (elemento comparável) implementada com `ArrayList`. Suporta inserção, extração do máximo, espiar máximo e tamanho. | Top de tendências (filmes com maior nota) |
| **Árvore AVL** | `ArvoreAVL.java` | Árvore binária de busca balanceada automaticamente com rotações simples e duplas. Suporta inserção, remoção, busca e busca de elemento. | Catálogo de filmes ordenado por título |

---

## 🎥 Sistema de Filmes

O pacote `com.projeto.sistema` contém:

### `Filme`
Entidade que representa um filme com os atributos:
- `id` (Integer)
- `titulo` (String)
- `genero` (String)
- `nota` (Double)
- `visualizacoes` (Integer)

Implementa `Comparable<Filme>`, ordenando primariamente por **nota** (descendente) e, em caso de empate, por **visualizações** (descendente). Essa ordenação é usada pela Heap de tendências.

### `SistemaFilmes`
Classe central que orquestra todas as estruturas:

| Estrutura | Atributo | Finalidade |
|-----------|----------|------------|
| `TabelaHash<Integer, Filme>` | `catalogoPorId` | Busca instantânea de filme pelo ID |
| `ArvoreAVL<FilmePorTitulo>` | `catalogoPorTitulo` | Busca eficiente de filme pelo título (ordem alfabética) |
| `HeapBinaria<Filme>` | `tendencias` | Recuperação dos filmes com maior nota (top tendências) |
| `Fila<Filme>` | `filaDeReproducao` | Fila de reprodução em ordem de chegada (FIFO) |

**Principais métodos:**
- `adicionarFilme(Filme)` — cadastra o filme em todas as estruturas
- `buscarPorId(Integer)` — busca via Tabela Hash
- `buscarPorTitulo(String)` — busca via Árvore AVL (ignora maiúsculas/minúsculas)
- `obterTendencias(int)` — retorna os N filmes com maior nota (Max Heap)
- `adicionarAFila(Filme)` / `reproduzirProximo()` — operações da fila de reprodução
- `removerFilme(int)` — remove o filme de todas as estruturas (reconstruindo a Heap)

### `Principal`
Classe com o método `main`. Apresenta um **menu interativo no console** com as seguintes opções:

1. Adicionar Novo Filme (Tabela Hash + AVL + Heap)
2. Buscar Filme por ID (Tabela Hash)
3. Buscar Filme por Título (Árvore AVL)
4. Ver Top Tendências (Max Heap)
5. Adicionar à Fila de Reprodução (Fila)
6. Reproduzir Próximo da Fila (Fila de Pilhas)
7. Ver Status do Catálogo
8. Remover Filme do Catálogo
0. Sair

O sistema é pré-populado com 5 filmes de exemplo para facilitar os testes iniciais.

---

## 🚀 Como Executar

### Pré-requisitos
- **Java JDK 17** ou superior
- **Apache Maven** (opcional, recomendado)

### Opção 1: Executar com Maven (recomendado)

Na raiz do módulo `Projeto Leda Unidade 2`:

```bash
# Compilar o projeto
mvn compile

# Executar a classe principal
mvn exec:java -Dexec.mainClass="com.projeto.sistema.Principal"
```

Caso o plugin `exec` não esteja configurado, compile e execute manualmente:

```bash
mvn compile
java -cp target/classes com.projeto.sistema.Principal
```

### Opção 2: Executar direto pelo arquivo `Principal.java`

Conforme indicado no arquivo `Como rodar.txt` incluído no projeto, você pode abrir o arquivo `src/main/java/com/projeto/sistema/Principal.java` em qualquer IDE (IntelliJ IDEA, Eclipse, VS Code, NetBeans) e executar o método `main`.

### Opção 3: Usar as classes já compiladas em `bin/`

O diretório `bin/` contém classes `.class` pré-compiladas. Você pode executar:

```bash
java -cp bin com.projeto.sistema.Principal
```

---

## 🧪 Executar os Testes

O projeto utiliza **JUnit 5 (Jupiter)**. Para rodar todos os testes automatizados:

```bash
mvn test
```

Os testes cobrem:
- `EstruturasTest`: Pilha (LIFO), Fila com duas pilhas (FIFO), Árvore AVL (inserção, busca, rotação), Heap Binária (máximo), Tabela Hash (put/get/remover).
- `SistemaFilmesTest`: Teste de integração validando busca por ID, busca por título, top tendências e fila de reprodução.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Apache Maven** (gerenciamento de build e dependências)
- **JUnit 5.10.0** (testes unitários e de integração)
- **Padrão de pacotes:** `com.projeto.estruturas` e `com.projeto.sistema`

---

## 👨‍💻 Autores:

Projeto feito em parceria entre João Roberto, Valter Júnior e eu para a disciplina de Laboratório de Estrutura de Dados.
---

## 📄 Licença

Projeto acadêmico, sem licença comercial definida. Livre para uso educacional.
