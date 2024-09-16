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
            String[] i1Regs = i1.getRegOnStage(4); // recebe o registrador no estágio EC (posição 4 do arraylist)
            String[] i2Regs = i2.getRegOnStage(1); // recebe o reg no estágio D (posição 1 do arraylist)
            for (String reg1 : i1Regs) {
                for (String reg2 : i2Regs) {
                    if (reg1.equals(reg2)) {
                        isHazard = true;
                        System.out.println("deu pepino");
                    }
                }
            }
        }
        System.out.println();
        return isHazard;
    }

    /*public boolean bypassDetector(Instruction i1, Instruction i2) {
        boolean isHazard = false;
        String i1Stage = i1.getStage(); // lw
        String i2Stage = i2.getStage(); // add
        System.out.println("COMPARANDO " + i1.getOp() +" & " + i2.getOp());
        System.out.println(i1.getStage() +" & " + i2.getStage());
        if((i1Stage=="EX" && i2Stage=="D") && i1.isReadyAtEX()){//conflito decodificação e execução
            System.out.println("possível conflito (EX e D)");
            String[] i1Regs = i1.getRegOnStage(2); // recebe o registrador no estágio EX (posição 2 do arraylist)
            String[] i2Regs = i2.getRegOnStage(1); // recebe o reg no estágio D (posição 1 do arraylist)
            for (String reg1 : i1Regs) {
                for (String reg2 : i2Regs) {
                    if (reg1.equals(reg2)) {
                        isHazard = true;
                        System.out.println("deu pepino");
                    }
                }
            }
        } else if ((i1Stage=="M" && i2Stage=="D") && i1.isReadyAtM()) {
            System.out.println("possível conflito (M e D)");
            String[] i1Regs = i1.getRegOnStage(3); // recebe o registrador no estágio M (posição 3 do arraylist)
            String[] i2Regs = i2.getRegOnStage(1); // recebe o reg no estágio D (posição 1 do arraylist)
            for (String reg1 : i1Regs) {
                for (String reg2 : i2Regs) {
                    if (reg1.equals(reg2)) {
                        isHazard = true;
                        System.out.println("deu pepino");
                    }
                }
            }
        } else {
            bubbleDetector(i1, i2);
        }
        System.out.println();
        return isHazard;
    }*/

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
