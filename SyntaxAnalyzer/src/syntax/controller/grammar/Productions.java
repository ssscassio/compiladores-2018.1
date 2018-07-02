package syntax.controller.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import java.util.HashSet;

import lexical.model.Token;
import lexical.util.Consts;
import syntax.controller.ErrorController;

/**
 * TODO: Fazer documentação
 * 
 * @see Production
 * @author Cássio Santos
 * @author Beatriz de Brito
 */
public enum Productions implements Production {
    FunctionDeclaration { // FunctionDeclaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<FunctionDeclaration>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "function"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'function'");
                while (!FunctionId.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = FunctionId.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'('");
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
            } else {
                displayError(tokens.get(0), "'procedure'");
                while (!FunctionProcedureTail.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!FunctionProcedureTail.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'('");
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
            } else {
                displayError(tokens.get(0), "'start'");
                while (!Block.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'start'");
                while (!Block.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "')'");
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
                } else {
                    displayError(tokens.get(0), ")");
                    while (!Block.hasAsFirst(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }

                tokens = Block.run(tokens);

            } else if (tokens.get(0).isSameType(new Token(Consts.DELIMITER, ")"))) {

                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else {
                    displayError(tokens.get(0), "')'");
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
    Block { // Block Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Block>");

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "{"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "{");
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
            } else if (StatementList.hasAsFirst(tokens.get(0))) {
                tokens = StatementList.run(tokens);

                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "}"))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else {
                    displayError(tokens.get(0), "'}'");
                    while (!BlockAux.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            } else {
                displayError(tokens.get(0), "'}'");
                while (!BlockAux.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
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
    FunctionId { // FunctionId Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<FunctionId>");

            tokens = Type.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'Identifier'");
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

            if (!tokens.isEmpty() && tokens.get(0).isSameType(new Token(Consts.DELIMITER, ","))) {
                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ","))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                }
                tokens = ParamsDeclaration.run(tokens);
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
            } else {
                displayError(tokens.get(0), "'Identifier'");
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
            } else {
                displayError(tokens.get(0), "'typedef'");
                while (!Type.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = Type.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!TypeDeclaration.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ";"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "';'");
                while (!TypeDeclaration.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
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
    Program { // Program Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Program>");

            tokens = Declaration.run(tokens);
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
                tokens = ConstDeclaration.run(tokens);
            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "struct"))) {
                tokens = StructDeclaration.run(tokens);
            } else if (tokens.get(0).isSameType(new Token(Consts.KEY_WORD, "typedef"))) {
                tokens = TypeDeclaration.run(tokens);
            } else {
                displayError(tokens.get(0), "'start', 'function', 'var', 'struct' or 'typedef'");
                while (!ProgramAux.hasAsFirst(tokens.get(0))) {
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
    StatementList { // Statement List Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StatementList>");

            tokens = Statement.run(tokens);
            tokens = StatementListAux.run(tokens);

            System.err.println("</StatementList>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {

            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "(", "true", "false", "struct", "return",
                    "print", "scan", "if", "then", "while", "var", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));

        }
    },
    StatementListAux { // Statement List Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StatementListAux>");

            if (!tokens.isEmpty() && StatementList.hasAsFirst(tokens.get(0))
                    && !StatementListAux.hasAsFollow(tokens.get(0))) {
                tokens = StatementList.run(tokens);
            }

            System.err.println("</StatementListAux>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {

            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false", "struct",
                    "return", "print", "scan", "if", "then", "while", "var", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, "}"));
        }
    },
    Statement { // Statement Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Statement>");

            if (NormalStatement.hasAsFirst(tokens.get(0))) {
                tokens = NormalStatement.run(tokens);
            } else if (IfStatement.hasAsFirst(tokens.get(0))) {
                tokens = IfStatement.run(tokens);
            } else if (WhileStatement.hasAsFirst(tokens.get(0))) {
                tokens = WhileStatement.run(tokens);
            } else if (VarDeclaration.hasAsFirst(tokens.get(0))) {
                tokens = VarDeclaration.run(tokens);
            } else if (TypeDeclaration.hasAsFirst(tokens.get(0))) {
                tokens = TypeDeclaration.run(tokens);
            } else {
                displayError(tokens.get(0),
                        "'!', '++', '--', '(', 'true', 'false', 'struct', 'return', 'print', 'scan', 'if', 'while', 'var' or typedef");
                while (!Statement.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</Statement>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {

            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "--", "(", "true", "false", "struct",
                    "return", "print", "scan", "if", "then", "while", "var", "typedef"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);
        }

        @Override
        public boolean hasAsFollow(Token token) {

            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "(", "true", "false", "struct", "return",
                    "print", "scan", "if", "then", "while", "var", "typedef", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);
        }
    },
    NormalStatement { // Normal Statement Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<NormalStatement>");

            if (ReturnStatement.hasAsFirst(tokens.get(0))) {
                tokens = ReturnStatement.run(tokens);
            } else if (PrintStatement.hasAsFirst(tokens.get(0))) {
                tokens = PrintStatement.run(tokens);
            } else if (ScanStatement.hasAsFirst(tokens.get(0))) {
                tokens = ScanStatement.run(tokens);
            } else if (StructDeclaration.hasAsFirst(tokens.get(0))) {
                tokens = StructDeclaration.run(tokens);
            } else if (OpAssing.hasAsFirst(tokens.get(0))) {
                tokens = OpAssing.run(tokens);
            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ";"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "';'");
                while (!NormalStatement.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</NormalStatement>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {

            Set<String> VALUES = new HashSet<String>(
                    Arrays.asList("!", "++", "--", "(", "true", "false", "struct", "return", "print", "scan"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }

        @Override
        public boolean hasAsFollow(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("!", "++", "(", "true", "false", "struct", "return",
                    "print", "scan", "if", "then", "while", "var", "typedef", "}"));
            return VALUES.contains(token.getLexeme()) || token.getType().equals(Consts.IDENTIFIER)
                    || token.getType().equals(Consts.NUMBER) || token.getType().equals(Consts.STRING);

        }
    },
    ReturnStatement { // Return Statement Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ReturnStatement>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "return"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'return'");
                while (!ReturnStatementAux.hasAsFirst(tokens.get(0)) && !ReturnStatement.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (ReturnStatementAux.hasAsFirst(tokens.get(0))) {
                tokens = ReturnStatementAux.run(tokens);
            }

            System.err.println("</ReturnStatement>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            return token.isSameType(new Token(Consts.KEY_WORD, "return"));
        }

        @Override
        public boolean hasAsFollow(Token token) {
            return token.isSameType(new Token(Consts.DELIMITER, ";"));
        }
    },
    ReturnStatementAux { // Return Statement Aux Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ReturnStatementAux>");
            if (!tokens.isEmpty() && Expression.hasAsFirst(tokens.get(0))) {
                tokens = Expression.run(tokens);
            }
            System.err.println("</ReturnStatementAux>");
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
    Type { // Type Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Type>");

            tokens = TypeBase.run(tokens);
            tokens = TypeAux.run(tokens);

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
            if (!tokens.isEmpty() && ArrayType.hasAsFirst(tokens.get(0))) {
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
                } else {
                    displayError(tokens.get(0), "'Identifier'");
                    while (!TypeBase.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            } else {
                displayError(tokens.get(0), "'return'");
                while (!TypeBase.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
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
            if (TypeBase.hasAsFirst(tokens.get(0))) { // TODO: Ver se vai ser preciso definir a partir do tipo
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                System.err.println("ERROR: Scalar");
                while (!Scalar.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
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

            tokens = ArrayTypeField.run(tokens);
            tokens = ArrayTypeAux.run(tokens);

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

            if (!tokens.isEmpty() && ArrayType.hasAsFirst(tokens.get(0))) {
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
                } else {
                    displayError(tokens.get(0), "']'");
                    while (!ArrayTypeField.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            } else {
                displayError(tokens.get(0), "'['");
                while (!ArrayTypeField.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpAssing>");

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {

                if (consumeToken(tokens.get(1), new Token(Consts.DELIMITER, "."))
                        || consumeToken(tokens.get(1), new Token(Consts.DELIMITER, "["))) {
                    // <Value Write or Read> '=' <Expression>
                    tokens = ValueWriteOrRead.run(tokens);
                    if (consumeToken(tokens.get(0), new Token(Consts.RELATIONAL_OPERATOR, "="))) {
                        System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                        tokens.remove(0);
                    } else {
                        displayError(tokens.get(0), "'['");
                        while (!Expression.hasAsFirst(tokens.get(0))) {
                            tokens.remove(0);
                        }

                    }
                    tokens = Expression.run(tokens);

                } else if (consumeToken(tokens.get(1), new Token(Consts.RELATIONAL_OPERATOR, "="))) {
                    // Identifier '=' <Expression>
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                    tokens = Expression.run(tokens);

                } else { // <Expression>
                    tokens = Expression.run(tokens);
                }

            } else if (Expression.hasAsFirst(tokens.get(0))) {
                tokens = Expression.run(tokens);
            }

            System.err.println("</OpAssing>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Params>");

            tokens = Expression.run(tokens);
            tokens = ParamsAux.run(tokens);

            System.err.println("</Params>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ParamsAux>");

            if (!tokens.isEmpty() && tokens.get(0).isSameType(new Token(Consts.DELIMITER, ","))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = Params.run(tokens);
            }

            System.err.println("</ParamsAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Expression>");

            tokens = OpAnd.run(tokens);
            tokens = ExpressionAux.run(tokens);

            System.err.println("</Expression>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ExpressionAux>");

            if (!tokens.isEmpty() && tokens.get(0).isSameType(new Token(Consts.LOGICAL_OPERATOR, "||"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = Expression.run(tokens);
            }

            System.err.println("</ExpressionAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpAnd>");

            tokens = OpRelational.run(tokens);
            tokens = OpAndAux.run(tokens);

            System.err.println("</OpAnd>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpAndAux>");

            if (!tokens.isEmpty() && tokens.get(0).isSameType(new Token(Consts.LOGICAL_OPERATOR, "&&"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = OpAnd.run(tokens);
            }

            System.err.println("</OpAndAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpRelational>");

            tokens = OpAdd.run(tokens);
            tokens = OpRelationalAux.run(tokens);

            System.err.println("</OpRelational>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpRelationalAux>");

            if (!tokens.isEmpty() && RelationalSymbol.hasAsFirst(tokens.get(0))) {
                tokens = RelationalSymbol.run(tokens);
                tokens = OpRelational.run(tokens);
            }

            System.err.println("</OpRelationalAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<RelationalSymbol>");

            if (RelationalSymbol.hasAsFirst(tokens.get(0))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "Relational Operator");
                while (!RelationalSymbol.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</RelationalSymbol>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpAdd>");

            tokens = OpMult.run(tokens);
            tokens = OpAddAux.run(tokens);

            System.err.println("</OpAdd>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpAddAux>");

            if (!tokens.isEmpty() && (tokens.get(0).isSameType(new Token(Consts.ARITHMETIC_OPERATOR, "+"))
                    || tokens.get(0).isSameType(new Token(Consts.ARITHMETIC_OPERATOR, "-")))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);

                tokens = OpMult.run(tokens);
                tokens = OpAddAux.run(tokens);

            } else {
                displayError(tokens.get(0), "'+ or -'");
                while (!OpMult.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</OpAddAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpMult>");

            tokens = OpUnary.run(tokens);
            tokens = OpMultAux.run(tokens);

            System.err.println("</OpMult>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpMultAux>");

            if (!tokens.isEmpty() && (tokens.get(0).isSameType(new Token(Consts.ARITHMETIC_OPERATOR, "*"))
                    || tokens.get(0).isSameType(new Token(Consts.ARITHMETIC_OPERATOR, "/")))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);

                tokens = OpUnary.run(tokens);
                tokens = OpMultAux.run(tokens);

            }

            System.err.println("</OpMultAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OpUnary>");

            if (!tokens.isEmpty() && (tokens.get(0).isSameType(new Token(Consts.LOGICAL_OPERATOR, "!"))
                    || tokens.get(0).isSameType(new Token(Consts.ARITHMETIC_OPERATOR, "++"))
                    || tokens.get(0).isSameType(new Token(Consts.ARITHMETIC_OPERATOR, "--")))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);

                tokens = OpUnary.run(tokens);
            } else if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                if (consumeToken(tokens.get(1), new Token(Consts.DELIMITER, "."))
                        || consumeToken(tokens.get(1), new Token(Consts.DELIMITER, "["))) {
                    // <Value Write or Read> <Unary Symbol>
                    tokens = ValueWriteOrRead.run(tokens);
                    tokens = UnarySymbol.run(tokens);
                } else { // <Value Read Only> <Unary Symbol>
                    tokens = ValueReadOnly.run(tokens);
                    tokens = UnarySymbol.run(tokens);
                }
            } else if (ValueReadOnly.hasAsFirst(tokens.get(0))) {
                // <Value Read Only> <Unary Symbol>
                tokens = ValueReadOnly.run(tokens);
                tokens = UnarySymbol.run(tokens);
            } else {
                System.err.println("ERROR: ");
                while (!OpUnary.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</OpUnary>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<UnarySymbol>");

            if (UnarySymbol.hasAsFirst(tokens.get(0))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            }

            System.err.println("</UnarySymbol>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ValueWriteOrRead>");

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!Accessing.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = Accessing.run(tokens);

            System.err.println("</ValueWriteOrRead>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Accessing>");

            tokens = Field.run(tokens);
            tokens = AccessingAux.run(tokens);

            System.err.println("</Accessing>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<AccessingAux>");

            if (Accessing.hasAsFirst(tokens.get(0))) {
                tokens = Accessing.run(tokens);
            }

            System.err.println("</AccessingAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Field>");

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "."))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else {
                    displayError(tokens.get(0), "'Identifier'");
                    while (!Field.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            } else if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "["))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);

                tokens = Expression.run(tokens);

                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "]"))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else {
                    System.err.println("ERROR: ']'");
                    while (!Field.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            }
            System.err.println("</Field>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ValueReadOnly>");

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = ValueAux1.run(tokens);
            } else if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = Expression.run(tokens);
                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else {
                    System.err.println("ERROR: ')'");
                    while (!ValueReadOnly.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            } else if (consumeToken(tokens.get(0), new Token(Consts.STRING, ""))
                    || consumeToken(tokens.get(0), new Token(Consts.NUMBER, ""))
                    || consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "true"))
                    || consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "false"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                System.err.println("ERROR: ");
                while (!ValueReadOnly.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</ValueReadOnly>");
            return tokens;
        }

        @Override
        public boolean hasAsFirst(Token token) {
            Set<String> VALUES = new HashSet<String>(Arrays.asList("(", "true", "false"));
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
    ValueAux1 { // Value Aux1 Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ValueAux1>");

            if (!tokens.isEmpty() && consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = ValueAux2.run(tokens);
            }

            System.err.println("</ValueAux1>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ValueAux2>");
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else if (Params.hasAsFirst(tokens.get(0))) {
                tokens = Params.run(tokens);
                if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else {
                    System.err.println("ERROR: ')'");
                    while (!ValueAux2.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            } else {
                System.err.println("ERROR: ')' or param");
                while (!ValueAux2.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</ValueAux2>");
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
    VarDeclaration { // VarDeclaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarDeclaration>");
            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "var"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                System.err.println("ERROR: 'var'");
                while (!VarBody.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "{"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                System.err.println("ERROR: '{'");
                while (!VarBody.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            if (VarBody.hasAsFirst(tokens.get(0))) {
                tokens = VarBody.run(tokens);
            } else {
                System.err.println("ERROR: var declarations");
                while (!VarDeclaration.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
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
            } else {
                System.err.println("ERROR: ");
                while (!VarBodyAux.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
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
            } else {
                System.err.println("ERROR: ");
                while (!VarRow.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
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
            } else {
                displayError(tokens.get(0), "',' or ';'");
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
                tokens = VarIdentifierAux.run(tokens);
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!VarIdentifierListAux.hasAsFirst(tokens.get(0)) || !VarIdentifier.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<VarIdentifierAux>");
            if (!tokens.isEmpty() && consumeToken(tokens.get(0), new Token(Consts.RELATIONAL_OPERATOR, "="))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = Expression.run(tokens);
            }
            System.err.println("</VarIdentifierAux>");
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
    ConstDeclaration { // Const Declaration Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ConstDeclaration>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "const"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                System.err.println("ERROR: 'const'");
                while (!ConstBody.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "{"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                System.err.println("ERROR: '{'");
                while (!ConstBody.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            tokens = ConstBody.run(tokens);
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "}"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                System.err.println("ERROR: '}'");
                while (!ConstDeclaration.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            System.err.println("</ConstDeclaration>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ConstBody>");
            tokens = ConstRow.run(tokens);
            tokens = ConstBodyAux.run(tokens);
            System.err.println("</ConstBody>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ConstBodyAux>");
            if (!tokens.isEmpty() && ConstBody.hasAsFirst(tokens.get(0))) {
                tokens = ConstBody.run(tokens);
            }
            System.err.println("</ConstBodyAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ConstRow>");
            tokens = Type.run(tokens);
            tokens = ConstIdentifierList.run(tokens);
            System.err.println("</ConstRow>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ConstIdentifierList>");
            tokens = ConstIdentifier.run(tokens);
            tokens = ConstIdentifierListAux.run(tokens);
            System.err.println("</ConstIdentifierList>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ConstIdentifierListAux>");
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ";"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ","))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = ConstIdentifierList.run(tokens);
            } else {
                displayError(tokens.get(0), "';' ou ','");
                while (!ConstIdentifierList.hasAsFirst(tokens.get(0))
                        || !ConstIdentifierListAux.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            System.err.println("</ConstIdentifierListAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ConstIdentifier>");

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!Expression.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (consumeToken(tokens.get(0), new Token(Consts.RELATIONAL_OPERATOR, "="))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = ConstIdentifierList.run(tokens);
            } else {
                displayError(tokens.get(0), "'='");
                while (!Expression.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = Expression.run(tokens);

            System.err.println("</ConstIdentifier>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StructDeclaration>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "struct"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'struct'");
                while (!StructDeclarationAux.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            tokens = StructDeclarationAux.run(tokens);

            System.err.println("</StructDeclaration>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StructDeclarationAux>");

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } // ELSE NÃO É ERRO

            tokens = Extends.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "{"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'{'");
                while (!StructBody.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            tokens = StructBody.run(tokens);
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "}"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'}'");
                while (!StructDeclarationAux.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</StructDeclarationAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Extends>");
            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "extends"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                } else {
                    displayError(tokens.get(0), "'Identifier'");
                    while (!Extends.hasAsFollow(tokens.get(0))) {
                        tokens.remove(0);
                    }
                }
            }

            System.err.println("</Extends>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StructBody>");

            tokens = StructRow.run(tokens);
            tokens = StructBodyAux.run(tokens);

            System.err.println("</StructBody>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StructBodyAux>");

            if (StructBody.hasAsFirst(tokens.get(0))) {
                tokens = StructBody.run(tokens);
            }

            System.err.println("</StructBodyAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StructRow>");

            tokens = Type.run(tokens);
            tokens = StructIdentifierList.run(tokens);

            System.err.println("</StructRow>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StructIdentifierList>");

            tokens = StructIdentifier.run(tokens);
            tokens = StructIdentifierListAux.run(tokens);

            System.err.println("</StructIdentifierList>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StructIdentifierListAux>");
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ";"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ","))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = StructIdentifierList.run(tokens);
            } else {
                displayError(tokens.get(0), "';' ou ','");
                while (!StructIdentifierList.hasAsFirst(tokens.get(0))
                        || !StructIdentifierListAux.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            System.err.println("</StructIdentifierListAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<StructIdentifier>");

            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!StructIdentifier.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</StructIdentifier>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<IfStatement>");

            tokens = IfThen.run(tokens);
            tokens = IfStatementAux.run(tokens);

            System.err.println("</IfStatement>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<IfStatementAux>");
            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "else"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = Block.run(tokens);
            }
            System.err.println("</IfStatementAux>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<IfThen>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "if"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'if'");
                while (!Expression.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'('");
                while (!Expression.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            tokens = Expression.run(tokens);
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'}'");
                while (!Expression.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "then"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'}'");
                while (!Block.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            tokens = Block.run(tokens);

            System.err.println("</IfThen>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<WhileStatement>");

            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "while"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!Expression.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'('");
                while (!Expression.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            tokens = Expression.run(tokens);
            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "')'");
                while (!Block.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            tokens = Block.run(tokens);

            System.err.println("</WhileStatement>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<PrintStatement>");
            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "print"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'print'");
                while (!Output.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'('");
                while (!Output.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = Output.run(tokens);
            tokens = OutputList.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "')'");
                while (!PrintStatement.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            System.err.println("</PrintStatement>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Output>");

            tokens = Expression.run(tokens);

            System.err.println("</Output>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<OutputList>");
            if (!tokens.isEmpty() && consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ","))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = Output.run(tokens);
                tokens = OutputList.run(tokens);
            }
            System.err.println("</OutputList>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<ScanStatement>");
            if (consumeToken(tokens.get(0), new Token(Consts.KEY_WORD, "scan"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'scan'");
                while (!Input.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, "("))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'('");
                while (!Input.hasAsFirst(tokens.get(0))) {
                    tokens.remove(0);
                }
            }

            tokens = Input.run(tokens);
            tokens = InputList.run(tokens);

            if (consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ")"))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!ScanStatement.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            System.err.println("</ScanStatement>");
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
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<Input>");
            if (consumeToken(tokens.get(0), new Token(Consts.IDENTIFIER, ""))) {
                if (consumeToken(tokens.get(1), new Token(Consts.DELIMITER, "."))
                        || consumeToken(tokens.get(1), new Token(Consts.DELIMITER, "["))) {
                    tokens = ValueWriteOrRead.run(tokens);
                } else {
                    System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                    tokens.remove(0);
                }
            } else {
                displayError(tokens.get(0), "'Identifier'");
                while (!Input.hasAsFollow(tokens.get(0))) {
                    tokens.remove(0);
                }
            }
            System.err.println("</Input>");
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
    InputList { // Input List Production
        @Override
        public ArrayList<Token> run(ArrayList<Token> tokenList) {
            ArrayList<Token> tokens = tokenList;
            System.err.println("<InputList>");
            if (!tokens.isEmpty() && consumeToken(tokens.get(0), new Token(Consts.DELIMITER, ","))) {
                System.err.println("<Terminal>" + tokens.get(0).getLexeme() + "</Terminal>");
                tokens.remove(0);
                tokens = Input.run(tokens);
                tokens = InputList.run(tokens);
            }
            System.err.println("</InputList>");
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

    static private void displayError(Token token, String spectedString) {
        ErrorController.getInstance().addError(spectedString, token.getLexeme(), token.getRow());
    }
}
