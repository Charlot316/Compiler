import java.util.ArrayList;
import java.util.Scanner;

public class  LexicalAnalyzer {
    
    public static String Reserve(String word) {
        switch (word) {
            case "if" : return "If";
            case "else" : return "Else";
            case "while" : return "While";
            case "break" : return "Break";
            case "continue" :return  "Continue";
            case "return" : return "Return";
            case "int" : return "Int";
            case "main" : return "Main";
            default :
                System.exit(-1);
                return "Ident(" + word + ")";
        }
    }
    public ArrayList<Token> LexicalAnalyze() {
        Scanner input = new Scanner(System.in);
        StringBuilder word = new StringBuilder();
        ArrayList<Token> tokenList=new ArrayList<>();
        char[] nextLine;
        boolean comment=false;
        while (input.hasNextLine()) {
            nextLine = input.nextLine().trim().toCharArray();
            for (int index = 0; index < nextLine.length; index++) {
                if(tokenList.size()>0)System.out.println(tokenList.get(tokenList.size()-1));
                if(!comment){
                    if(nextLine[index]=='/'){
                        ++index;
                        if(index<nextLine.length&&nextLine[index]=='/') break;
                        else if(index<nextLine.length&&nextLine[index]=='*') comment=true;

                    }
                    else if (Character.isLetter(nextLine[index]) || nextLine[index] == '_') {
                        word.append(nextLine[index]);
                        ++index;
                        while ((index<nextLine.length)&&(Character.isLetter(nextLine[index]) || nextLine[index] == '_' || Character.isDigit(nextLine[index]))) {
                            word.append(nextLine[index]);
                            ++index;
                        }
                        --index;
                        tokenList.add(new Token(Reserve(word.toString()),word.toString()));
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
                                tokenList.add(new Token("Number",Integer.toString(Integer.parseInt(word.toString(),16))));
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
                                tokenList.add(new Token("Number",Integer.toString(Integer.parseInt(word.toString(),8))));
                            }
                        }
                        else{
                            while ((index<nextLine.length)&&(Character.isDigit(nextLine[index]))) {
                                word.append(nextLine[index]);
                                ++index;
                            }
                            --index;
                            tokenList.add(new Token("Number",word.toString()));
                        }
                        word = new StringBuilder();
                    }
                    else {
                        if ((nextLine[index] != ' ' && nextLine[index] != '\n' && nextLine[index] != '\t' && nextLine[index] != '\r')) {
                            switch (nextLine[index]) {
                                case '=' -> {
                                    ++index;
                                    if ((index < nextLine.length) && (nextLine[index] == '=')) {
                                        tokenList.add(new Token("Eq", "=="));
                                    } else {
                                        --index;
                                        tokenList.add(new Token("Assign", "="));
                                    }
                                }
                                case ';' -> tokenList.add(new Token("Semicolon", ";"));
                                case '(' -> tokenList.add(new Token("LPar", "("));
                                case ')' -> tokenList.add(new Token("RPar", ")"));
                                case '{' -> tokenList.add(new Token("LBrace", "{"));
                                case '}' -> tokenList.add(new Token("RBrace", "}"));
                                case '+' -> tokenList.add(new Token("Plus", "+"));
                                case '*' -> tokenList.add(new Token("Mult", "*"));
                                case '/' -> tokenList.add(new Token("Div", "/"));
                                case '<' -> tokenList.add(new Token("Lt", "<"));
                                case '>' -> tokenList.add(new Token("Gt", ">"));
                                default -> System.exit(-1);
                            }
                        }
                    }
                }
                else{
                    if(nextLine[index]=='*'){
                        ++index;
                        if(index<nextLine.length&&nextLine[index]=='/') comment=false;
                    }
                }
            }
        }
        return tokenList;
    }
}
