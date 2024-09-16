import java.util.ArrayList;
//import java.util.Arrays;

public class HazardDetector {

    public boolean bubbleDetector(Instruction i1, Instruction i2) {
        boolean isHazard = false;
        String i1Stage = i1.getStage(); // lw
        String i2Stage = i2.getStage(); // add
        System.out.println("COMPARANDO " + i1.getOp() +" & " + i2.getOp());
        System.out.println(i1.getStage() +" & " + i2.getStage());
        if(i1Stage!="EC" && i2Stage=="D"){//conflito decodificação e execução
            System.out.println("possível conflito (EC e D)");
            isHazard = compareRegisters(i1, 4, i2, 1); // compara registradores em EC e D
        }
        System.out.println();
        return isHazard;
    }

    public int bypassDetector(Instruction i1, Instruction i2) {
        int isHazard = -1; // não há possibilidade de bypass
        String i1Stage = i1.getStage(); // lw
        String i2Stage = i2.getStage(); // add
        System.out.println("COMPARANDO " + i1.getOp() +" & " + i2.getOp());
        System.out.println(i1.getStage() +" & " + i2.getStage());
        if(i1Stage!="EC" && i2Stage=="D"){ //conflito decodificação e execução - sem bolha
            System.out.println("possível conflito (EX e D)");
            if (i1.isReadyAtEX()) {
                if (compareRegisters(i1, 4, i2, 1)) { // compara registradores em EX e D
                    isHazard = 0; // bypass sem bolha
                } 
            } else { // conflito decod. e memória - uma bolha
                if (compareRegisters(i1, 4, i2, 1)) { // compara registradores em M e D
                    isHazard = 1; // bypass com 1 bolha
                }
            }
        }
        System.out.println();
        return isHazard;
    }

    /*
    método reorderDetector(): procurar por hazard -> 
    */

    public boolean compareRegisters(Instruction i1, int stage1, Instruction i2, int stage2) {
        boolean isEqual = false;
        String[] i1Regs = i1.getRegOnStage(stage1); // recebe o registrador em stage1
            String[] i2Regs = i2.getRegOnStage(stage2); // recebe o registrador em stage2
            for (String reg1 : i1Regs) {
                for (String reg2 : i2Regs) {
                    System.out.println("comparando regs: " + reg1 + " e " + reg2);
                    if (reg1.equals(reg2)) {
                        isEqual = true;
                        System.out.println("deu pepino");
                    }
                }
            }
        return isEqual;
    }

    public ArrayList<String> getRegisters(String[] instruction) {
        ArrayList<String> registers = new ArrayList<>();
        for(int i=0; i<instruction.length; i++){
            if(instruction[i].contains("$")){
                registers.add(instruction[i]);
            }
        }
        return registers;
    }

    
}
