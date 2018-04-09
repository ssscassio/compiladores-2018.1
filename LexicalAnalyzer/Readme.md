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