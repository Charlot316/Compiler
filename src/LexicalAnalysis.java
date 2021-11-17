import java.util.Scanner;

public class  LexicalAnalysis {
    
    public static String printWord(String token) {
        return switch (token) {
            case "if" -> "If";
            case "else" -> "Else";
            case "while" -> "While";
            case "break" -> "Break";
            case "continue" -> "Continue";
            case "return" -> "Return";
            default -> "Ident(" + token + ")";
        };
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder token = new StringBuilder();
        char[] nextLine;
        while (input.hasNextLine()) {
            nextLine = input.nextLine().trim().toCharArray();
            for (int index = 0; index < nextLine.length; index++) {
                if (Character.isLetter(nextLine[index]) || nextLine[index] == '_') {
                    token.append(nextLine[index]);
                    ++index;
                    while ((index<nextLine.length)&&(Character.isLetter(nextLine[index]) || nextLine[index] == '_' || Character.isDigit(nextLine[index]))) {
                        token.append(nextLine[index]);
                        ++index;
                    }
                    --index;
                    System.out.println(printWord(token.toString()));
                    token = new StringBuilder();
                }
                else if(Character.isDigit(nextLine[index])){
                    token.append(nextLine[index]);
                    ++index;
                    while ((index<nextLine.length)&&(Character.isDigit(nextLine[index]))) {
                        token.append(nextLine[index]);
                        ++index;
                    }
                    --index;
                    System.out.println("Number("+ token +")");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='='){
                    ++index;
                    if((index<nextLine.length)&&(nextLine[index]=='=')){
                        System.out.println("Eq");
                    } else{
                        --index;
                        System.out.println("Assign");
                    }
                    token = new StringBuilder();
                }
                else if(nextLine[index]==';'){
                    System.out.println("Semicolon");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='('){
                    System.out.println("LPar");
                    token = new StringBuilder();
                }
                else if(nextLine[index]==')'){
                    System.out.println("RPar");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='{'){
                    System.out.println("LBrace");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='}'){
                    System.out.println("RBrace");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='+'){
                    System.out.println("Plus");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='*'){
                    System.out.println("Mult");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='/'){
                    System.out.println("Div");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='<'){
                    System.out.println("Lt");
                    token = new StringBuilder();
                }
                else if(nextLine[index]=='>'){
                    System.out.println("Gt");
                    token = new StringBuilder();
                }
                else{
                    System.out.println("Err");
                    System.exit(0);
                }
            }
        }
    }
}
