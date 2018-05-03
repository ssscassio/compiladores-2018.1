# Gramática

## Descrição
- A gramática deve ser fatorada à esquerda
- A gramática não deve possuir recursão à esquerda
- A gramática deve ser construída utilizando o software [GOLD Parser Builder](http://www.goldparser.org/)

### Especificações
1. Todo o programa fonte deve ser feito em un único arquivo.
2. A linguagem permite a definição de variáveis e constantes globais, além de variáveis do tipo estrutura.
3. Deve ser possível declarar múltiplas variáveis ou constantes do mesmo tipo na mesma linha.
4. As variáveis podem ser campos, se vierem declaradas em uma estrutura; globais, se vierem declaradas fora de procedimentos ou funções; ou locais, se vierem declaradas dentro de um procedimento ou função.
5. Não é permitida a declaração de constantes locais.
6. A declaração de uma ou mais constantes deve ser dentro de um bloco que se inicia com a palavra **const**.
7. A declaração de uma ou mais variáveis deve ser dentro de um bloco que se inicia com a palavra **var**.
8. A declaração de uma estrutura deve iniciar com a palavra **struct**, seguido de um bloco de variáveis.
9. A linguagem permite a implementação de Herança entre variáveis do tipo estrutura, usando a palavra **extends**.
10. Não é permitida herança múltipla.
11. A linguagem permite a sobrecarga de procedimentos e funções, mas não a sobrescrita.
12. Será permitido o uso de identificadores de mesmo nome, porém em escopos diferentes.
13. Cada função deve conter inicialmente a palavra **function**, seguido do tipo de retorno, seguido do nome da função, seguido da lista de parâmetros formais. O retorno de uma função é feito através da palavra **return**.
14. Cada procedimento deve conter inicialmente a palavra **procedure**, seguido do nome do procedimento, seguido da lista de parâmetros formais.
15. Deve existir somente um método principal chamado **start**. Este método não possui parâmetros e nem retorno.
16. A linguagem permite o uso de vetores e matrizes de variáveis primitivas e do tipo estrutura. Deve ser possível acessar (ler ou inserir) cada elemento de um vetor ou matriz.
17. Uma variável (ou campo) de uma estrutura também pode ser um vetor ou uma matriz.
18. A linguagem permite chamada a procedimentos e funções. No caso de funções, o retorno pode ser atribuído a uma variável.
19. Delimitadores **{** e **}** marcam inicio e fim de blocos (de comandos, procedimentos, funções, etc.), respectivamente.
20. A linguagem permite o uso de expressões relacionais, lógicas e aritméticas.
21. Comandos:
    - Comando **if .. then .. else**: Na condição do comando **if** só são permitidas as expressões relacionais ou lógicas. O comando **else*8 não é obrigatório.
    - Comando **while**: Comando semelhante ao da linguagem Java. Na condição do comando só são permitida as expressões relacionais ou lógicas.
    - Comando **print**: O comando iniciará com a palavra *8print** e o que deverá ser impresso entre parênteses, finalizando com ponto-e-vírgula. O comando pode imprimir cadeias de caracteres, constantes, variáveis, variáveis (campos) de estruturas, elementos de vetores ou matrizes. Múltiplas impressões no mesmo comando devem ser separadas por vírgula.
    - Comando **scan**: O comando iniciará com a palavra **scan** e o nome da variável (inclusive campos de estruturas, elementos de vetores ou matrizes) entre parênteses, finalizando com ponto-e-vírgula. Múltiplas leituras no mesmo comando deverão ser separadas por vírgula.
22. O usuário pode criar um tipo usando a palavra typedef.














