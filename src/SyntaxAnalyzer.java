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
    
    public void CompUnit(){
        FuncDef();
    }

    public void FuncDef(){
        FuncType();
        getSym();
        Ident();
        getSym();
        if(this.outOfBound()||!this.getCurrentType().equals("LPar")){
            error();
        }
        getSym();
        if(this.outOfBound()||!this.getCurrentType().equals("RPar")){
            error();
        }
        getSym();
        Block();
    }

    public void FuncType(){
        if(this.outOfBound()||!this.getCurrentType().equals("Int")){
            error();
        }
    }

    public void Ident(){
        if(this.outOfBound()||!this.getCurrentType().equals("Main")){
            error();
        }
    }

    public void Block(){
        if(this.outOfBound()||!this.getCurrentType().equals("LBrace")){
            error();
        }
        getSym();
        Stmt();
        getSym();
        if(this.outOfBound()||!this.getCurrentType().equals("RBrace")){
            error();
        }
    }

    public void Stmt(){
        if(this.outOfBound()||!this.getCurrentType().equals("Return")){
            error();
        }
        getSym();
        if(this.outOfBound()||!this.getCurrentType().equals("Number")){
            error();
        }
        returnValue=Integer.parseInt(this.getCurrentValue());
        getSym();
        if(this.outOfBound()||!this.getCurrentType().equals("Semicolon")){
            error();
        }
    }

    public void getSym(){
        this.setIndex(this.getIndex()+1);
    }

    public void error(){
        System.exit(1);
    }

    public void outPrint(){
        System.out.println("define dso_local i32 @main(){");
        System.out.println("\tret i32 "+returnValue);
        System.out.println("}");
    }
}
