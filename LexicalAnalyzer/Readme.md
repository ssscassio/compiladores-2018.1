# Analisador Léxico

## Descrição
- A entrada e a saída para o analisador devem ser feitas por meio de arquivos texto.
- O analisador deverá ler **um conjunto** de arquivos-texto do diretório *entrada*.
- Para cada arquivo analisado será gerado um arquivo de saída no diretório *saida*.
- No arquivo de saída deve ser impressa a lista de tokens e exibidos os erros léxicos de forma padronizada.
- Se não houver erros, uma mensagem de sucesso deve ser gravada no arquivo de saída.

### Especificações

| Tipo | Expressão regular |
| :------ | :---------------- |
| Palavras reservadas | **const, var, struct, typedef, procedure, function, return, start, if, then, else, while, scan, print, int float, bool, string, true, false, extends**|
|identificador|**letra ( letra \| digito \| _ )\***|
|número| **(-)? Espaco\* digito digito\* ( . digito (digito)\* )?** |
|digito|**[0-9]**|
|letra|**[a-z]** \| **[A-Z]**|
|operadores aritméticos| **+** \| **-** \| **\*** \| **/** \| **++** \| **--** |
| operadores relacionais | **!=** \| **==** \| **<** \| **<=** \| **>** \| **>=** \| **=** |
|operadores lógicos| **!** \| **&&** \| **\|\|**|
|Delimitadores de comentários| **//isso é um comentário de linha <br> /\* Isso <br> é um comentário <br>de bloco\*/** |
|Delimitadores| **;** \| **,** \| **(** \| **)** \| **[** \| **]** \| **{** \| **}** \| **.** |
|Cadeia de caracteres| **" ( letra \| digito \| Simbolo \| \\")*"**|
|Simbolo| **ASCII de 32 a 126 (exceto ASCII 34)**|
| Espaco | **ASCII 9** \| **ASCII 10** \| **ASCII 13** \| **ASCII 32**|

## Execução (Linux, MacOS)

Antes de executar, garanta que os arquivos de entrada estejam dentro da pasta `./entrada`e tenham a extensão `.top`

Na raiz do projeto, crie uma pasta para os arquivos *.class* compilados e compile os arquivos *.java*:

```
mkdir target
mkdir target/classes
javac src/**/*.java -d target/classes
```

Por fim, execute o projeto digitando o seguinte no terminal:

```
java -cp target/classes lexical.Main
```

As saídas para cada arquivo de entrada serão salvas na pasta `./saida`