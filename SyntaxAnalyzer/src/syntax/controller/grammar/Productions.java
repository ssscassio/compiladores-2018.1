package syntax.controller.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import lexical.model.Token;
import lexical.util.Consts;

/**
 * TODO: Fazer documentação
 * 
 * @see Production
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public enum Productions implements Production {
    Program {
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Program>");
            if (Declaration.hasAsFirst(tokens.get(0))) {
                tokens = Declaration.run(tokens);
            } else { // TODO:
                     // ERROR

            }
            // if (ProgramAux.hasAsFirst(tokens.get(0))) {
            // Program.run(tokens);
            // } else { // TODO: Não da erro

            // }

            System.err.println("</Program>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            String FIRST[] = { "function", "procedure", "start", "var", "const", "struct", "typedef" };
            return (Arrays.binarySearch(FIRST, token.getLexeme()) >= 0);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Adicionar Conjunto seguinte
            return false;
        }
    },
    Declaration {
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Declaration>");

            if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "function"))) {

            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "procedure"))) {

            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "start"))) {

            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "var"))) {
                tokens = VarDeclaration.run(tokens);
            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "const"))) {

            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "struct"))) {

            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "typedef"))) {

            } else { // TODO: Erro, token inesperado TODO: Remover 1 token por vez até conseguir
                     // executar Declaration
            }
            System.err.println("</Declaration>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            String FIRST[] = { "function", "procedure", "start", "var", "const", "struct", "typedef" };
            return (Arrays.binarySearch(FIRST, token.getLexeme()) >= 0);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO:
            return false;
        }
    },
    VarDeclaration { // VarDeclaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarDeclaration>");
            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "var"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO: Erro, não encontrou a palavra "var"

            }
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "{"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // ERRO:
                // TODO: Disparar erro
                // SINCRONIZAÇÃO
                while (!VarBody.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            if (VarBody.hasAsFirst(tokens.get(0))) {
                tokens = VarBody.run(tokens);
            } else {
                // Sem body
            }
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "}"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            }

            System.err.println("</VarDeclaration>");
            return tokenList;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "var"));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return false;
        }
    },
    VarBody { // VarBody Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarBody>");
            if (VarBody.hasAsFirst(tokens.get(0))) {
                tokens = VarRow.run(tokens);
                tokens = VarBodyAux.run(tokens);
            } else { // TODO:
                     // ERRO

            }
            System.err.println("</VarBody>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            String FIRST[] = { "bool", "float", "int", "string", "struct" };
            return (Arrays.binarySearch(FIRST, token.getLexeme()) >= 0) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    VarBodyAux { // VarBodyAux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarBodyAux>");
            if (VarBody.hasAsFirst(tokens.get(0))) {
                tokens = VarBody.run(tokens);
            }
            System.err.println("</VarBodyAux>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            String FIRST[] = { "bool", "float", "int", "string", "struct" };
            return (Arrays.binarySearch(FIRST, token.getLexeme()) >= 0) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    VarRow { // VarRow Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarRow>");
            if (VarRow.hasAsFirst(tokens.get(0))) {
                tokens = Type.run(tokens);
                tokens = VarIdentifierList.run(tokens);
            } else { // TODO:
                     // ERRO

            }
            System.err.println("</VarRow>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            String FIRST[] = { "bool", "float", "int", "string", "struct" };
            return (Arrays.binarySearch(FIRST, token.getLexeme()) >= 0) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    VarIdentifierList { // VarIdentifierList Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarIdentifierList>");
            tokens = VarIdentifier.run(tokens);
            tokens = VarIdentifierListAux.run(tokens);
            System.err.println("</VarIdentifierList>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    VarIdentifierListAux { // VarIdentifierListAux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarIdentifierListAux>");
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ";"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ","))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = VarIdentifierList.run(tokens);
            } else { // TODO: Erro, esperava ',' ou ';'
                // TODO: Ideia: Fazer sincronização para Follow de Array Type Field
                // TODO: Ver se isso é realmente preciso, ou se basta dar o return;
                while (!VarIdentifierListAux.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            System.err.println("</VarIdentifierListAux>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    VarIdentifier { // VarIdentifier Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarIdentifier>");
            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                // TODO: Parte que começa as expressões
                // tokens = tokens = VarIdentifierAux.run(tokens);
            } else { // TODO: ERRO: Esperava um Identificador, não encontrou isso
                // TODO: Sincronização, como fazer sincronização com coisas que tem vazio?
            }
            System.err.println("</VarIdentifier>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    Type { // Type Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Type>");
            if (Type.hasAsFirst(tokens.get(0))) {
                tokens = TypeBase.run(tokens);
                tokens = TypeAux.run(tokens);
            } else { // TODO:
                     // ERRO

            }
            System.err.println("</Type>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            String FIRST[] = { "bool", "float", "int", "string", "struct" };
            return (Arrays.binarySearch(FIRST, token.getLexeme()) >= 0) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    TypeAux { // TypeAux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<TypeAux>");
            if (ArrayType.hasAsFirst(tokens.get(0))) {
                tokens = ArrayType.run(tokens);
            }
            System.err.println("</TypeAux>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "["));
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    TypeBase { // TypeBase Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<TypeBase>");
            if (Scalar.hasAsFirst(tokens.get(0))) {
                tokens = Scalar.run(tokens);
            }
            // else if (StructDeclaration.hasAsFirst(tokens.get(0))) {
            // TODO: Ver o que vai azer com o struct aqui já que ele tem no seu first o
            // "struct" que também é a ultima produção de TypeBase;
            // tokens = StructDeclaration.run();
            // }
            else if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                tokens.remove(0);
            } else if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "struct"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else { // ERRO: Esperado Identificador
                    // TODO: Ideia: Fazer sincronização para Follow de Type Base
                    // TODO: Ver se isso é realmente preciso, ou se basta dar o return;
                    while (!TypeBase.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            } else { // TODO: ERRO - Não entrou em nenhuma
                     // opção dos primeiros de TypeBase

            }
            System.err.println("</TypeBase>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            String FIRST[] = { "bool", "float", "int", "string", "struct" };
            return (Arrays.binarySearch(FIRST, token.getLexeme()) >= 0) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    Scalar { // Scalar Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Scalar>");
            if (TypeBase.hasAsFirst(tokens.get(0))) { // TODO: Ver se vai ser preciso definir a partir do tipo
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO: Algum erro que eu acho que não vai acontecer

            }
            System.err.println("</Scalar>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            String FIRST[] = { "bool", "float", "int", "string" };
            return (Arrays.binarySearch(FIRST, token.getLexeme()) >= 0) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    ArrayType { // ArrayType Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ArrayType>");
            if (ArrayType.hasAsFirst(tokens.get(0))) {
                tokens = ArrayTypeField.run(tokens);
                tokens = ArrayTypeAux.run(tokens);
            } else {

            }
            System.err.println("</ArrayType>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "["));
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    ArrayTypeAux { // ArrayTypeAux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ArrayTypeAux>");
            if (ArrayType.hasAsFirst(tokens.get(0))) {
                tokens = ArrayType.run(tokens);
            }
            System.err.println("</ArrayTypeAux>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "["));
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    ArrayTypeField { // ArrayTypeField Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ArrayTypeField>");
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "["))) {
                tokens.remove(0);
                if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "]"))) {
                    tokens.remove(0);
                } else { // TODO: Erro: ']' não encontrado, Disparar erro
                    // TODO: Ideia: Fazer sincronização para Follow de Array Type Field
                    // TODO: Ver se isso é realmente preciso, ou se basta dar o return;
                    while (!ArrayTypeField.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            }
            System.err.println("</ArrayTypeField>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "["));
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    },
    Template { // Template Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) { // TODO: Implementar
            return false;
        }

        @Override
        public boolean hasAsFollow(Token token) { // TODO: Implementar
            return false;
        }
    };

    static private boolean consumeToken(Token token, Token spectedToken) {
        return token.isSameType(spectedToken);
    }
}