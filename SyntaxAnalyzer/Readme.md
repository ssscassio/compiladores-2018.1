# Analisador Semântico

## Descrição
- A entrada e a saída para o analisador devem ser feitas por meio de arquivos com extensão ".top".
- O analisador deverá ler **um conjunto** de arquivos-texto do diretório *entrada*.
- Para cada arquivo analisado será gerado um arquivo de saída no diretório *saida*.
- No arquivo de saída deve ser impressa a lista de erros semânticos exibidos de forma padronizada.
- Se não houver erros, uma mensagem de sucesso deve ser gravada no arquivo de saída.

## Algumas regras semânticas
- A linguagem é fortemente tipada. Não há conversão implícita ou explícita de tipos.
- Será permitido o uso de identificadores de mesmo nome, porém em escopos diferentes.
- Não é permitido alterar o valor de uma constante.
- Não é permitida herança múltipla, mas é permitida herança em cadeia. neste caso, identificadores herdados são considerados como pertencentes a um único escopo
- A linguagem permite a sobrecarga de métodos, mas não a sobrescrita.
- Deve existir somente um método principal chamado **start**.

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