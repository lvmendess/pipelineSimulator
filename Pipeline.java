import java.util.ArrayList;
public class Pipeline{
    //simular estágios de pipeline
    ArrayList<Instruction> pipelineSimulator;
    ArrayList<String> pipelineFinal;
    String[] stages;
    Dicionario dick;
    HazardDetector hd;

    public Pipeline(){
        pipelineSimulator = new ArrayList<Instruction>();
        pipelineFinal = new ArrayList<String>();
        stages = new String[]{"B", "D", "EX", "M", "EC"}; //busca, decodificação, execução, memória, escrita
        dick = new Dicionario();
        hd = new HazardDetector();
    }

    public ArrayList<String> getPipelineFinal() {
        return pipelineFinal;
    }

    public void printInstructions(){
        for (String string : pipelineFinal) {
            System.out.println(string);
        }
    }

    public void createInstructions(ArrayList<String[]> insts){
        for(String[] inst : insts){
            put(dick.search(inst));
        }
        for (int i = 0; i < 5; i++) {
            put(new Bolha("NOP", null, "NOP"));
        }
    }

    public void put(Instruction i){
        if(pipelineSimulator.isEmpty()){
            pipelineSimulator.add(i);
        }else{
            if(pipelineSimulator.size()>5){
                pipelineFinal.add(pipelineSimulator.getLast().getOgInst());
                pipelineSimulator.removeLast();
            }
            pipelineSimulator.add(0, i);
            isHazard();
            advance();
            print();
        }
    }

    public void advance(){
        for(int j = 0; j<pipelineSimulator.size(); j++){
            if((j+1)<5){
                pipelineSimulator.get(j).setStage(stages[j+1], j+1);
            }
        }
    }

    public void print(){
        for(int i=0; i<pipelineSimulator.size(); i++){
            System.out.println(pipelineSimulator.get(i).getOp());
        }
        System.out.println();
    }

    public boolean isEmpty(){
        boolean isEmpty = true;
        for (Instruction instruction : pipelineSimulator) {
            if (!(instruction instanceof Bolha)) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    public boolean isHazard(){
        if(pipelineSimulator.size()>2){
            Instruction i1 = null;
            Instruction i2 = null;
            boolean r = false;
            for(int i=1; i<pipelineSimulator.size(); i++){
                i1 = pipelineSimulator.get(i);
                i2 = pipelineSimulator.get(i-1);
                if(i1.getOp()!="NOP" && i2.getOp()!="NOP"){
                    r = hd.rawDetector(i1, i2);
                }
                if(r){
                    solveHazard(i, i-1, 1);
                    print();
                    return r;
                }
            }
            return r;
        }else{
            return false;
        }
    }

    public boolean running(){
        if(!isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public void solveHazard(int ind1, int ind2, int m){
        switch (m) {
            case 1://bolha
                Instruction b1 = new Bolha("NOP", null, "NOP");
                pipelineSimulator.add(ind1, b1);
                Instruction b2 = new Bolha("NOP", null, "NOP");
                pipelineSimulator.add(ind1, b2);
                break;

            case 2: //TODO: adiantamento

                break;

            case 3: //TODO: reordenamento
        
            default:
                break;
        }
    }
}