import java.util.ArrayList;
public class SyntaxAnalyzer {
    private final ArrayList<Token> tokenList;
    private int index=0;
    private int returnValue;
    public SyntaxAnalyzer(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private boolean outOfBound(){
        return this.getIndex()>this.getTokenList().size();
    }
    
    public String getCurrentType(){
        return this.getTokenList().get(this.getIndex()).getType();
    }

    public Token getCurrentToken(){
        return this.getTokenList().get(this.getIndex());
    }

    public String getCurrentValue(){
        return this.getTokenList().get(this.getIndex()).getValue();
    }

    public void compareToken(String type){
        if(this.outOfBound()||!this.getCurrentType().equals(type)){
            error();
        }
    }
    public void CompUnit(){
        FuncDef();
    }

    public void FuncDef(){
        FuncType();
        Ident();
        compareToken("LPar");
        getSym();
        compareToken("RPar");
        getSym();
        Block();
    }

    public void FuncType(){
        compareToken("Int");
        getSym();
    }

    public void Ident(){
        compareToken("Main");
        getSym();
    }

    public void Block(){
        compareToken("LBrace");
        getSym();
        Stmt();
        compareToken("RBrace");
        getSym();
    }

    public void Stmt(){
        compareToken("Return");
        getSym();
        compareToken("Number");
        getSym();
        returnValue=Integer.parseInt(this.getCurrentValue());
        getSym();
        compareToken("Semicolon");
        getSym();
    }

    public void getSym(){
        if(this.getIndex()+1<this.getTokenList().size()) {
            System.out.println(this.getCurrentToken()); this.setIndex(this.getIndex()+1);}
    }

    public void error(){
        System.exit(-1);
    }

    public void outPrint(){
        System.out.println("define dso_local i32 @main(){");
        System.out.println("\tret i32 "+returnValue);
        System.out.println("}");
    }
}
