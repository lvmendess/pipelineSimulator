import java.util.ArrayList;
//import java.util.Arrays;

public class HazardDetector {

    public boolean rawDetector(Instruction i1, Instruction i2) {
        /*TODO: alterar lógica para comparar se o mesmo registrador está em estágios diferentes das instruções
         * ex: lw com $t0 no último estágio e add com $t0 no segundo estágio,
         * add vai tentar ler o registrador antes que lw possa escrever nele -> HAZARD
         */
        boolean isHazard = false;
        String[] i1regs = i1.getRegs();
        String[] i2regs = i2.getRegs();
        String i1Stage = i1.getStage();
        String i2Stage = i2.getStage();
        for(String reg1 : i1regs){
            for(String reg2 : i2regs){
                if(reg1.equals(reg2)){
                    if(i1Stage!="EC" && i2Stage=="D"){//conflito decodificação e execução
                        isHazard = true;
                    }
                }
            }
        }
        return isHazard;
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
