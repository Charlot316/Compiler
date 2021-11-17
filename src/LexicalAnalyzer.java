import java.util.ArrayList;
import java.util.Scanner;

public class  LexicalAnalyzer {
    
    public static String Reserve(String word) {
        return switch (word) {
            case "if" -> "If";
            case "else" -> "Else";
            case "while" -> "While";
            case "break" -> "Break";
            case "continue" -> "Continue";
            case "return" -> "Return";
            case "int" -> "Int";
            case "main" -> "Main";
            default -> "Ident(" + word + ")";
        };
    }
    public static ArrayList<Token> LexicalAnalyze() {
        Scanner input = new Scanner(System.in);
        StringBuilder word = new StringBuilder();
        ArrayList<Token> TokenList=new ArrayList<>();
        char[] nextLine;
        while (input.hasNextLine()) {
            nextLine = input.nextLine().trim().toCharArray();
            boolean comment=false;
            for (int index = 0; index < nextLine.length; index++) {
                if(!comment){
                    if(nextLine[index]=='/'){
                        ++index;
                        if(nextLine[index]=='/') break;
                        else if(nextLine[index]=='*')comment=true;
                        else index--;
                    }
                    else if (Character.isLetter(nextLine[index]) || nextLine[index] == '_') {
                        word.append(nextLine[index]);
                        ++index;
                        while ((index<nextLine.length)&&(Character.isLetter(nextLine[index]) || nextLine[index] == '_' || Character.isDigit(nextLine[index]))) {
                            word.append(nextLine[index]);
                            ++index;
                        }
                        --index;
                        TokenList.add(new Token(Reserve(word.toString()),word.toString()));
                        word = new StringBuilder();
                    }
                    else if(Character.isDigit(nextLine[index])){
                        if(nextLine[index]=='0'){
                            word.append(nextLine[index]);
                            ++index;
                            if(nextLine[index]=='x'){
                                word.append(nextLine[index]);
                                ++index;
                                while ((index<nextLine.length)
                                        &&Character.isDigit(nextLine[index])
                                        ||
                                        (Character.isLetter(nextLine[index])
                                                &&(nextLine[index]>='a'&&nextLine[index]<='f'
                                                || nextLine[index]>='A'&&nextLine[index]<='F'))
                                        ) {
                                    word.append(nextLine[index]);
                                    ++index;
                                }
                                if(word.length()<=2) System.exit(-1);
                                --index;
                                TokenList.add(new Token("Number",Integer.toString(Integer.parseInt(word.toString(),16))));
                            }
                            else{
                                while ((index<nextLine.length)
                                        &&Character.isDigit(nextLine[index])
                                        &&nextLine[index]>='0'
                                        &&nextLine[index]<='7') {
                                    word.append(nextLine[index]);
                                    ++index;
                                }
                                --index;
                                TokenList.add(new Token("Number",Integer.toString(Integer.parseInt(word.toString(),8))));
                            }
                        }
                        else{
                            while ((index<nextLine.length)&&(Character.isDigit(nextLine[index]))) {
                                word.append(nextLine[index]);
                                ++index;
                            }
                            --index;
                            TokenList.add(new Token("Number",word.toString()));
                            word = new StringBuilder();
                        }

                    }
                    else {
                        switch (nextLine[index]) {
                            case '=' -> {
                                ++index;
                                if ((index < nextLine.length) && (nextLine[index] == '=')) {
                                    TokenList.add(new Token("Eq", "=="));
                                } else {
                                    --index;
                                    TokenList.add(new Token("Assign", "="));
                                }
                            }
                            case ';' -> TokenList.add(new Token("Semicolon", ";"));
                            case '(' -> TokenList.add(new Token("LPar", "("));
                            case ')' -> TokenList.add(new Token("RPar", ")"));
                            case '{' -> TokenList.add(new Token("LBrace", "{"));
                            case '}' -> TokenList.add(new Token("RBrace", "}"));
                            case '+' -> TokenList.add(new Token("Plus", "+"));
                            case '*' -> TokenList.add(new Token("Mult", "*"));
                            case '/' -> TokenList.add(new Token("Div", "/"));
                            case '<' -> TokenList.add(new Token("Lt", "<"));
                            case '>' -> TokenList.add(new Token("Gt", ">"));
                            default ->  System.exit(-1);
                        }
                        word = new StringBuilder();
                    }
                }
                else{
                    if(nextLine[index]=='*'){
                        ++index;
                        if(nextLine[index]=='/') comment=false;
                        else break;
                    }
                }
            }
        }
        return TokenList;
    }
}
