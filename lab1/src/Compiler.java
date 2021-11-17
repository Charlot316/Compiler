import java.util.ArrayList;

public class Compiler {
    public static void main(String[] args) {
        LexicalAnalyzer lexicalAnalyzer=new LexicalAnalyzer();
        ArrayList<Token> tokenList=lexicalAnalyzer.LexicalAnalyze();
        SyntaxAnalyzer syntaxAnalyzer=new SyntaxAnalyzer(tokenList);
        syntaxAnalyzer.CompUnit();
        syntaxAnalyzer.outPrint();
    }
}
