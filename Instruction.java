import java.util.ArrayList;
import java.util.Arrays;

public abstract class Instruction {
    protected String op, rs; //registradores
    protected String[] regs;
    protected ArrayList<String[]> regByStage;
    protected String stage;
    protected int stageId;
    protected String ogInst;
    protected boolean readyAtEX;

    public Instruction(String op, String rs, String ogInst, boolean ready) {
        this.op = op;
        this.rs = rs;
        regByStage = new ArrayList<String[]>();
        this.stage = "B"; 
        stageId = 0;
        this.ogInst = ogInst;
        this.readyAtEX = ready;
    }

    public void orderRegs(){}

    public String getOp() {
        return op;
    }

    public String getRs() {
        return rs;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String[] getRegs() {
        return regs;
    }

    public void setRegs(String[] regs) {
        this.regs = regs;
    }

    public ArrayList<String[]> getRegByStage() {
        return regByStage;
    }

    public void setRegByStage(ArrayList<String[]> regByStage) {
        this.regByStage = regByStage;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage, int stageId) {
        this.stage = stage;
        setStageId(stageId);
    }

    public void printRegs(){
        System.out.println(Arrays.toString(regs));
    }

    public void printRegsByStage(){
        for (String[] regs : regByStage) {
            System.out.println(Arrays.toString(regs));
        }
    }

    public String[] getRegOnStage(int id){
        return regByStage.get(id);
    }


    public int getStageId() {
        return stageId;
    }


    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public String getOgInst() {
        return ogInst;
    }

    public void setOgInst(String ogInst) {
        this.ogInst = ogInst;
    }
    
}
