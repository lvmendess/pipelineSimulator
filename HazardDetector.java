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

    public boolean bypassDetector(Instruction i1, Instruction i2) {
        boolean isHazard = false;
        String i1Stage = i1.getStage(); // lw
        String i2Stage = i2.getStage(); // add
        System.out.println("COMPARANDO " + i1.getOp() +" & " + i2.getOp());
        System.out.println(i1.getStage() +" & " + i2.getStage());
        if((i1Stage=="EX" && i2Stage=="D") && i1.isReadyAtEX()){//conflito decodificação e execução
            System.out.println("possível conflito (EX e D)");
            isHazard = compareRegisters(i1, 2, i2, 1); // compara registradores em EX e D
        } else if ((i1Stage=="M" && i2Stage=="D") && !i1.isReadyAtEX()) {
            System.out.println("possível conflito (M e D)");
            isHazard = compareRegisters(i1, 3, i2, 1); // compara registradores em M e D
        } else {
            bubbleDetector(i1, i2);
        }
        System.out.println();
        return isHazard;
    }

    public boolean compareRegisters(Instruction i1, int stage1, Instruction i2, int stage2) {
        boolean isEqual = false;
        String[] i1Regs = i1.getRegOnStage(stage1); // recebe o registrador em stage1
            String[] i2Regs = i2.getRegOnStage(stage2); // recebe o registrador em stage2
            for (String reg1 : i1Regs) {
                for (String reg2 : i2Regs) {
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
