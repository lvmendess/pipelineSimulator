import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {
    private Dicionario dick;
    String[] lineZeroRegs;
    String[] lineOneRegs;
    Instruction inst1;
    Instruction inst2;
    ArrayList<Instruction> allInstructions;
    ArrayList<String[]> instructions;

    public Scheduler(ArrayList<String[]> instructions) {
        this.instructions = instructions;
        allInstructions = new ArrayList<Instruction>();
        dick = new Dicionario();
        createInstructions(instructions);
        reorder();
    }

    public ArrayList<Instruction> getAllInstructions() {
        return allInstructions;
    }

    public void createInstructions(ArrayList<String[]> instructions){
        for(String[] inst : instructions){
            allInstructions.add(dick.search(inst));
        }
    }

    public boolean dependecyDetector(Instruction i1, Instruction i2) {
        boolean isDependency = false;
        lineZeroRegs = i1.getRegs();
        lineOneRegs = i2.getRegs();
        for (String reg1 : lineZeroRegs) {
            for (String reg2 : lineOneRegs) {
                if (reg1.equals(reg2)) {
                    isDependency = true;
                }
            }
        }
        return isDependency;
    }

    public void reorder(){
        branchToTop();
        for (int i = 0; i < allInstructions.size()-1; i++) {
            Instruction nextInstruction = allInstructions.get(i + 1);
            if((nextInstruction != null)){
                Instruction i1 = allInstructions.get(i);
                Instruction i2 = allInstructions.get(i + 1);
                if(!dependecyDetector(i1, i2) && !(i1.getOp().charAt(0) == 'b' || i1.getOp().charAt(0) == 'j')){
                    Collections.swap(allInstructions, i, i+1);
                }
            }
        }
    }

    public void branchToTop(){
        for (int i = 0; i < allInstructions.size(); i++) {
            Instruction i1 = allInstructions.get(i);
            if(i1.getOp().charAt(0) == 'b' || i1.getOp().charAt(0) == 'j'){
                allInstructions.remove(i1);
                allInstructions.add(0, i1);
            }
        }
    }
}
