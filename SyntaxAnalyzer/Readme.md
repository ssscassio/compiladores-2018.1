# Analisador Sintático

## Descrição
- A entrada e a saída para o analisador devem ser feitas por meio de arquivos com extensão ".top".
- O analisador deverá ler **um conjunto** de arquivos-texto do diretório *entrada*.
- Para cada arquivo analisado será gerado um arquivo de saída no diretório *saida*.
- No arquivo de saída deve ser impressa a lista de erros sintáticos exibidos de forma padronizada.
- Se não houver erros, uma mensagem de sucesso deve ser gravada no arquivo de saída.

## Especificações de construções

### Declaração de Constantes
Para declaração de constantes a mesma deve ser feita na raiz do código fonte (fora de blocos) e à elas devem ser atribuídos valores na declaração. As constantes de mesmo tipo podem ser declaradas na mesma linha separadas por vírgula. Exemplo:
```
const {
    float PI = 3.14, e = 2.7;
}
```

### Declaração de Variáveis
Na declaração de variávels, as variáveis do mesmo tipo podem ser declaradas na mesma linha separadas por vírgula. Variáveis podem ter escopo global ou local, dependendo de onde são declaradas. Exemplo:
```
var {
    float PI = 3.14;
    int name, age, weight;        
}
```

### Declaração de Procedimento
Para declaração de procedimento o mesmo deve ser feito na raiz do código fonte (fora de blocos) com o seu conteúdo. Dentro dos parênteses pode constar uma lista de parâmetors (tipo e nome) que o procedimento recebe, separados por vírgula. Exemplo:
```
procedure procedureName (int a, float b) {
    procedureName();
}
```

### Declaração de Funções
Para declaração de function a mesma deve ser feito na raiz do código fonte (fora de blocos). Após a palavra **function** deve constar o tipo de retorno na função e em seguida o nome da função sendo declarada. Dentro dos parênteses pode constar uma lista de parâmetors (tipo e nome) que a função recebe, separados por vírgula. Exemplo:
```
function int functionName (int a, float b) {
    function2Name();
    anyVar = functionName();
    return something;
}
```

### Declaração de Início
O **start** marca o início do código principal, onde a maioria dos comandos devem estar contidos. Exemplo:
```
start() {
    
}
```

### Declaração de Condicional If-then-else
A estrutura condicional **if** deve ser declarada seguindo da expressão e a palavra reservada **then**. Uma chave marca a abertura do bloco de comandos e pode ser seguida da palavra reservada **else** que marca um segundo bloco de comandos. Podem ser usados comandos **if** de maneira encadeada se desejado. Exemplo:
```
if( a+b >= c) then {
    print( a+b );
}
```
```
if (a == b) then {

} else {

}
```

### Declaração de Estrutura de Repetição While
Esta estrutura de repetição deve ser feita com o uso da palavra reservada **while** seguido de uma expressão entre parênteses e uma chave que marca o início do bloco de comandos. Exemplo:
```
while (a == b) {

}
```

### Comando Print
Pode ser usado através da palavra reservada **print** que deve ser seguida de um conteúdo (cadeia de caractere, vetores, variáveis, acesso a estruturas e operações) que podem ser separados pelo uso de vírgula (**,**). O fim do comando é marcado pelo ponto-e-vírgula (**;**).

```
print(identifier, a+b, c.d, vector[10], 10.43, "Any message");
```

### Comando Scan
Para usar este comando deve ser feito o uso da palavra reservada **scan** seguido do conteúdo (varáveis, acesso a estruturas e vetores), que devem ser separados pelo uso da vírgula (**,**). O fim do comando é marcado pelo ponto-e-vírgula (**;**).
``` 
scan(identifier, c.d, vector[10]);
```

### Declaração e uso de Vetores
A declaração de vetores deve ser feita dentro do bloco de variáveis, com o tipo do vetor seguido de abre e fecha colchetes (**[]**) e o nome do vetor. Exemplo:
```
var {
    int[][][] matrix1;
    bool[] vector1, vector2;
}
```
Para atribuir ou acessar valores de uma determinada posição do vetor, deve-se indicar a posição dentro dos colchetes. Este acesso pode ser feito dentro do bloco Start. Exemplo:
```
start () {
    vector1[10] = 50;
    vector2[0][1] = (number1 || number2);
}
```

### Declaração e uso de Estruturas

Para declaração de estruturas deve-se usar a palavra reservada **struct**, esta deve ser seguida do nome da estrutura e a chave para marcar o início de um bloco. As estruturas podem ser declaradas dentro ou fora do bloco **start**, sendo que caso usadas no bloco **start** devem ser finalizadas com um ponto-e-vírgula (**;**) após o fechamento do bloco de comandos. Exemplo:

```
struct Student{
    string name;
    int age;        
};

```
Pode ser usado a palavra reservada **extends**, de forma opcional, na declaração de estruturas. Isso permite que seja feita uma nova estrutura com campos de outra previamente criada. A linguagem não permite herança multipla, ou seja, não é possível usar **extends** para herdar mais de uma estrutura. Exemplo:
```
start {
    struct UefsStudent extends Student{
        int score;
    };
}
```
Para criar uma variável do tipo de uma estrutura existente, basta usar a palavra reservada **struct** seguida do nome da estrutura e o nome da variável desejada. Exemplo:
```
var {
    struct UefsStudent student;
}
```
Para acessar o campo de uma estrutura basta usar o nome da variável do tipo estrutura seguindo de um ponto (**.**) e o nome do campo que se deseja acessar. Exemplo:
```
student.age = 21;
print(student.age);
```
### Declaração de Tipos
Os tipos devem ser declarados com o uso da palavra reservada **typedef** seguido do tipo desejado e o nome do novo tipo a ser criado. Exemplo:

```
typedef typeOfNewType newType;
typedef int[][] newVectorType;
typedef struct structName structType;
```

### Uso de Retorno
O retorno pode ser usado através da palavra reservada **return**. Pode ser usada em funções como de forma usual (para retorno de valores) ou sem nenhuma identificação de valor de forma a interromper o processo. Exemplo:
```
function int functionName() {
    return 10;
}

start () {
   print("Hello world");
   return;
}
```

## Execução (Linux, MacOS)

Antes de executar, garanta que os arquivos de entrada estejam dentro da pasta `./entrada` e tenham a extensão `.top`

Na raiz do projeto, crie uma pasta para os arquivos *.class* compilados e compile os arquivos *.java*:

```
mkdir target
mkdir target/classes
javac src/**/*.java -d target/classes
```

Por fim, execute o projeto digitando o seguinte no terminal:

```
java -cp target/classes Main
```

As saídas para cada arquivo de entrada serão salvas na pasta `./saida`