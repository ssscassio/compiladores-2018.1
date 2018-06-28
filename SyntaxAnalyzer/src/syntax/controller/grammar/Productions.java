package syntax.controller.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

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
    Program { // Program Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Program>");
            if (Declaration.hasAsFirst(tokens.get(0))) {
                tokens = Declaration.run(tokens);
            } else { // TODO:
                     // ERROR:
                     // Ver
                     // com
                     // o
                     // que
                     // vai
                     // sincronizar

            }
            tokens = ProgramAux.run(tokens);

            System.err.println("</Program>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme());
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.getType().equals(Consts.END_OF_FILE);
        }
    },
    ProgramAux { // ProgramAux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ProgramAux>");
            if (!tokens.isEmpty() && ProgramAux.hasAsFirst(tokens.get(0))) {
                tokens = Program.run(tokens);
            }
            System.err.println("</ProgramAux>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme());
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.getType().equals(Consts.END_OF_FILE);
        }
    },
    Declaration {
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Declaration>");

            if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "function"))) {
                tokens = FunctionDeclaration.run(tokens);
            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "procedure"))) {
                tokens = ProcedureDeclaration.run(tokens);
            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "start"))) {
                tokens = StartDeclaration.run(tokens);
            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "var"))) {
                tokens = VarDeclaration.run(tokens);
            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "const"))) {

            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "struct"))) {

            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "typedef"))) {
                tokens = TypeDeclaration.run(tokens);
            } else { // TODO:
                     // Erro,
                     // token
                     // inesperado
                     // TODO:
                     // Remover
                     // 1
                     // token
                     // por
                     // vez
                     // até
                     // conseguir
                     // executar
                     // Declaration
                while (!tokens.isEmpty() && !Declaration.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            System.err.println("</Declaration>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme());
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    FunctionDeclaration { // FunctionDeclaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<FunctionDeclaration>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "function"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // a
                     // palavra
                     // "function"

            }

            tokens = FunctionId.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // ERRO:
                     // Não
                     // encontrou
                     // '('
                // TODO: Disparar erro
                // SINCRONIZAÇÃO
                while (!FunctionProcedureTail.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = FunctionProcedureTail.run(tokens);

            System.err.println("</FunctionDeclaration>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "function"));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    ProcedureDeclaration { // ProcedureDeclaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ProcedureDeclaration>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "procedure"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // a
                     // palavra
                     // "procedure"

            }

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // um
                     // Identificador

            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // ERRO:
                     // Não
                     // encontrou
                     // '('
                // TODO: Disparar erro
                // SINCRONIZAÇÃO
                while (!FunctionProcedureTail.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = FunctionProcedureTail.run(tokens);

            System.err.println("</ProcedureDeclaration>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "procedure"));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    StartDeclaration { // StartDeclaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StartDeclaration>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "start"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // a
                     // palavra
                     // "start"

            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // ERRO:
                     // Não
                     // encontrou
                     // '('
                // TODO: Disparar erro
            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // ERRO:
                     // Não
                     // encontrou
                     // ')'
                // TODO: Disparar erro
                // SINCRONIZAÇÃO
                while (!Block.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = Block.run(tokens);

            System.err.println("</StartDeclaration>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "start"));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    FunctionProcedureTail { // FunctionProcedureTail Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<FunctionProcedureTail>");

            if (ParamsDeclaration.hasAsFirst(tokens.get(0))) {

                tokens = ParamsDeclaration.run(tokens);

                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else { // TODO:
                         // Erro:
                         // não
                         // encontrou
                         // ")"
                    while (!Block.hasAsFirst(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }

                tokens = Block.run(tokens);

            } else if (tokens.get(0).isSameType(new Token(Consts.DELIMITER, ")"))) {

                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else { // TODO:
                         // Erro:
                         // não
                         // encontrou
                         // ")"
                    while (!Block.hasAsFirst(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }

                tokens = Block.run(tokens);
            }

            System.err.println("</FunctionProcedureTail>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    FunctionId { // FunctionId Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<FunctionId>");

            tokens = Type.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // um
                     // identificador
                // Sincronização com Follow
                while (!FunctionId.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</FunctionId>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "("));
        }
    },
    ParamsDeclaration { // ParamDeclaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ParamsDeclaration>");
            tokens = Param.run(tokens);
            tokens = ParamsDeclarationAux.run(tokens);
            System.err.println("</ParamsDeclaration>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ")"));
        }
    },
    ParamsDeclarationAux { // ParamDeclarationAux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ParamsDeclarationAux>");

            if (tokens.get(0).isSameType(new Token(Consts.DELIMITER, ","))) {
                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ","))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                    tokens = ParamsDeclaration.run(tokens);
                }
            }

            System.err.println("</ParamsDeclarationAux>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ")"));
        }
    },
    Param { // Param Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Param>");

            tokens = Type.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // um
                     // identificador
                // Sincronização com Follow
                while (!Param.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</Param>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(",", ")"));
            return VALUES.contains(token.getLexeme());
        }
    },
    TypeDeclaration { // TypeDeclaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<TypeDeclaration>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "typedef"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // a
                     // palavra
                     // "typedef"

            }

            tokens = Type.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // um
                     // Identificador

            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ";"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // token
                     // ';'
                // TODO: Sincronizar com próximo de TypeDeclaration
            }

            System.err.println("</TypeDeclaration>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "typedef"));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("--", "!", "(", "++", "false", "print", "return", "scan", "struct", "true", "typdef",
                            "var", "while", "}", "const", "function", "procedure", "start"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING)
                    || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    TypeDeclarationAux { // Type Declaration Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("--", "!", "(", "++", "false", "print", "return", "scan", "struct", "true", "typdef",
                            "var", "while", "}", "const", "function", "procedure", "start"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING)
                    || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    Block { // Block Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Block>");

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "{"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // um
                     // delimitador
                     // '{'
                // Sincronização com First
                while (!BlockAux.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = BlockAux.run(tokens);

            System.err.println("</Block>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "{"));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("--", "!", "(", "++", "false", "print", "return", "scan", "struct", "true", "typdef",
                            "var", "while", "else", "}", "const", "function", "procedure", "start"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING)
                    || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    BlockAux { // BlockAux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<BlockAux>");
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "}"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            }
            // TODO: StatementList
            // else if (StatementList.hasAsFirst(tokens.get(0))) {
            // tokens = StatementList.run(tokens);

            // if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "}"))) {
            // System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
            // tokens.remove(0);
            // } else { // TODO: ERRO: Esperava } e não encontrou

            // }
            // } else { // TODO: ERRO: Esperado } e não encontrou

            // }
            System.err.println("</BlockAux>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false", "return",
                    "while", "print", "scan", "if", "var", "typedef", "bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING)
                    || token.getType().equals(Consts.END_OF_FILE);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("--", "!", "(", "++", "print", "return", "scan", "struct", "true", "false", "typdef",
                            "var", "while", "else", "}", "const", "function", "procedure", "start"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING)
                    || token.getType().equals(Consts.END_OF_FILE);
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
            } else { // TODO:
                     // Erro,
                     // não
                     // encontrou
                     // a
                     // palavra
                     // "var"

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
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef", "!", "++",
                            "--", "(", "true", "false", "return", "print", "scan", "if", "then", "while", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING)
                    || token.getType().equals(Consts.END_OF_FILE);
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
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));
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
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));
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
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
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
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));
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
            } else { // TODO:
                     // Erro,
                     // esperava
                     // ','
                     // ou
                     // ';'
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
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));
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
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(",", ")"));
            return VALUES.contains(token.getLexeme());
        }
    },
    VarIdentifierAux { // Var Identifier Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.RELATIONAL_OPERATOR, "="))
                    || token.getType().equals(Consts.END_OF_FILE);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(",", ";"));
            return VALUES.contains(token.getLexeme());
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
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);
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
        public boolean hasAsFollow(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);
        }
    },
    TypeBase { // TypeBase Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<TypeBase>");
            if (Scalar.hasAsFirst(tokens.get(0))) {
                tokens = Scalar.run(tokens);
            } else if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                tokens.remove(0);
            } else if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "struct"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else { // ERRO:
                         // Esperado
                         // Identificador
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
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("["));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }
    },
    Scalar { // Scalar Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Scalar>");
            if (TypeBase.hasAsFirst(tokens.get(0))) { // TODO:
                                                      // Ver
                                                      // se
                                                      // vai
                                                      // ser
                                                      // preciso
                                                      // definir
                                                      // a
                                                      // partir
                                                      // do
                                                      // tipo
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else { // TODO:
                     // Algum
                     // erro que
                     // eu acho
                     // que não
                     // vai
                     // acontecer

            }
            System.err.println("</Scalar>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string"));
            return VALUES.contains(token.getLexeme());
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("["));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
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
        public boolean hasAsFollow(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);
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
        public boolean hasAsFollow(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);
        }
    },
    ArrayTypeField { // ArrayTypeField Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ArrayTypeField>");
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "["))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "]"))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else { // TODO:
                         // Erro:
                         // ']'
                         // não
                         // encontrado,
                         // Disparar
                         // erro
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
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("["));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }
    },

    OpAssing { // Op Assign Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ";"));
        }
    },
    Params { // Params Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ")"));
        }
    },
    ParamsAux { // Params Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ","));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ")"));
        }
    },
    Expression { // Expression Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    ExpressionAux { // Expression Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.LOGICAL_OPERATOR, "||"));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpAnd { // Op And Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpAndAux { // Op And Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.LOGICAL_OPERATOR, "&&"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpRelational { // Op Relational Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpRelationalAux { // Op Relational Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!=", "==", "<", "<=", ">", ">="));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    RelationalSymbol { // Relational Symbol Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!=", "==", "<", "<=", ">", ">="));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpAdd { // Op Add Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!=", "==", "<", "<=", ">", ">="));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpAddAux { // Op Add Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("+", "-"));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("!=", "<", "<=", "==", ">", ">=", "&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpMult { // Op Mult Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("-", "+", "!=", "<", "<=", "==", ">", ">=", "&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpMultAux { // Op Mult Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("*", "/", "&"));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("-", "+", "!=", "<", "<=", "==", ">", ">=", "&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    OpUnary { // Op Unary Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("*", "/", "-", "+", "!=", "<", "<=", "==", ">", ">=",
                    "&&", "||", ")", ",", ";", "] "));
            return VALUES.contains(token.getLexeme());
        }
    },
    UnarySymbol { // Unary Symbol Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("++", "--"));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("*", "/", "-", "+", "!=", "<", "<=", "==", ">", ">=",
                    "&&", "||", ")", ",", ";", "] "));
            return VALUES.contains(token.getLexeme());
        }
    },
    ValueWriteOrRead { // Value Write or Read Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("*", "/", "-", "+", "!=", "<", "<=", "==", ">", ">=",
                    "&&", "||", ")", ",", ";", "] "));
            return VALUES.contains(token.getLexeme());
        }
    },
    Accessing { // Accessing Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(".", "["));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("=", "--", "++", "*", "/", "-", "+", "!=", "<", "<=",
                    "==", ">", ">=", "&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    AccessingAux { // Accessing Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(".", "["));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("=", "--", "++", "*", "/", "-", "+", "!=", "<", "<=",
                    "==", ">", ">=", "&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    Field { // Field Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(".", "["));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("=", "--", "++", "*", "/", "-", "+", "!=", "<", "<=",
                    "==", ">", ">=", "&&", "||", ")", ",", ";", "]", "."));
            return VALUES.contains(token.getLexeme());
        }
    },
    ValueReadOnly { // Value Read Only Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("(", "true", "false"));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("--", "++", "*", "/", "-", "+", "!=", "<", "<=",
                    "==", ">", ">=", "&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    ValueAux1 { // Value Aux1 Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "("));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("--", "++", "*", "/", "-", "+", "!=", "<", "<=",
                    "==", ">", ">=", "&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    ValueAux2 { // Value Aux2 Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("--", "++", "*", "/", "-", "+", "!=", "<", "<=",
                    "==", ">", ">=", "&&", "||", ")", ",", ";", "]"));
            return VALUES.contains(token.getLexeme());
        }
    },
    ConstDeclaration { // Const Declaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "const"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE);
        }
    },
    ConstBody { // Const Body Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));

        }
    },
    ConstBodyAux { // Const Body Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));

        }
    },
    ConstRow { // Const Row Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }
    },
    ConstIdentifierList { // Const Identifier List Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }
    },
    ConstIdentifierListAux { // Const Identifier List Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(";", ","));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("bool", "float", "int", "string", "struct", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }
    },
    ConstIdentifier { // Const Identifier Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(";", ","));
            return VALUES.contains(token.getLexeme());

        }
    },
    StructDeclaration { // Struct Declaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "struct"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "typedef", ";", "["));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE)
                    || token.getType().equals(Consts.IDENTIFIER);
        }
    },
    StructDeclarationAux { // Struct Declaration Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("{", "extends"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("function", "procedure", "start", "var", "const",
                    "struct", "typedef", "start", "var", ";", "["));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }
    },
    Extends { // Extends Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "extends"))
                    || token.getType().equals(Consts.END_OF_FILE);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "{"));
        }
    },
    StructBody { // Struct Body Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));
        }
    },
    StructBodyAux { // Struct Body Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));
        }
    },
    StructRow { // Struct Row Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }
    },
    StructIdentifierList { // Struct Identifier List Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }
    },
    StructIdentifierListAux { // Struct Identifier List Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(",", ";"));
            return VALUES.contains(token.getLexeme());

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }
    },
    StructIdentifier { // Struct Identifier Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("int", "float", "bool", "string", "struct", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER);
        }
    },
    IfStatement { // If Statement Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "if"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef", "!", "++", "(",
                            "true", "false", "return", "print", "scan", "if", "then", "while", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE)
                    || token.getType().equals(Consts.IDENTIFIER) || token.getType().equals(Consts.NUMBER)
                    || token.getType().equals(Consts.STRING);

        }
    },
    IfStatementAux { // If Statement Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "else"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("function", "procedure", "start", "var", "const", "struct", "typedef", "!", "++", "(",
                            "true", "false", "return", "print", "scan", "if", "then", "while", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.END_OF_FILE)
                    || token.getType().equals(Consts.IDENTIFIER) || token.getType().equals(Consts.NUMBER)
                    || token.getType().equals(Consts.STRING);

        }
    },
    IfThen { // If Then Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "if"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("else", "--", "!", "(", "++", "false", "print",
                    "return", "scan", "struct", "true", "typdef", "var", "while", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }
    },
    WhileStatement { // While Statement Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "while"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("--", "!", "(", "++", "false", "print", "return",
                    "scan", "struct", "true", "typdef", "var", "while", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }
    },
    PrintStatement { // Print Statement Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "print"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ";"));

        }
    },
    Output { // Output Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(",", ")"));
            return VALUES.contains(token.getLexeme());

        }
    },
    OutputList { // Output List Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ";"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ")"));

        }
    },
    ScanStatement { // Scan Statement Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "scan"));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ";"));

        }
    },
    Input { // Input Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.getType().equals(Consts.IDENTIFIER);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList(",", ")"));
            return VALUES.contains(token.getLexeme());

        }
    },
    InputList { // Scan Statement Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {// TODO:
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Template>");

            System.err.println("</Template>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ","));

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ")"));

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
        public boolean hasAsFirst(Token token) { // Implementar
            return false;
        }

        @Override
        public boolean hasAsFollow(Token token) { // Implementar
            return false;
        }
    };

    static private boolean consumeToken(Token token, Token spectedToken) {
        return token.isSameType(spectedToken);
    }
}
