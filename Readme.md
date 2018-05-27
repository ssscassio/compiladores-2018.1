# MI Processadores de Linguagem de Programação

## Sobre

Trabalhos desenvolvidos durante a disciplina MI Processadores de Linguagem de Programação no semestre de 2018.1 da Universidade Estadual de Feira de Santana comm o propósito de cobrir os conceitos a cerca do projeto de um compilador.

- Docente: Matheus Giovanni Pires
- Discentes:
    - Cássio Silva de Sá Santos
    - Beatriz de Brito Santana

## Etapas

### [Analisador Léxico](./LexicalAnalyzer)
- O analisador léxico é responsável por fazer a leitura de linhas de caracteres e produzir uma sequência de símbolos (tokens) para serem manipulados mais facilmente nas próximas etapas.

### [Gramática da Linguagem](./Grammar)
- As gramáticas são capazes de descrever a maioria das sintaxes das linguagens de programação. Certas restrições, como exigência que os identificadores sejam declarados antes de usados, ainda não podem ser descritas por uma gramática livre de contexto.

### [Analisador Sintático](./SyntaxAnalyzer)
- O analisador sintático, também conhecido como *parser* é o processo que analisa a sequência de tokens vindas de um analisador léxico para determinar a estrutura gramatical do código de entrada segundo uma determinada **gramática**. 

### Analisador Semântico
