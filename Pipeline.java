import java.util.ArrayList;
public class Pipeline{
    //simular estágios de pipeline
    ArrayList<Instruction> pipelineSimulator;
    String[] stages;
    Dicionario dick;
    HazardDetector hd;
    boolean parse;

    public Pipeline(){
        pipelineSimulator = new ArrayList<Instruction>();
        stages = new String[]{"B", "D", "EX", "M", "EC"}; //busca, decodificação, execução, memória, escrita
        dick = new Dicionario();
        hd = new HazardDetector();
    }

    public void createInstructions(ArrayList<String[]> insts){
        for(String[] inst : insts){
            put(dick.search(inst));
        }
    }

    public void put(Instruction i){
        if(pipelineSimulator.isEmpty()){
            pipelineSimulator.add(i);
            print();
            System.out.println();
        }else{
            advance();
            pipelineSimulator.add(0, i);
            print();
            isHazard();
        }
    }

    public void advance(){
        if(pipelineSimulator.size()<5){
            for(int j = 0; j<pipelineSimulator.size(); j++){
                if((j+1)<5){
                    pipelineSimulator.get(j).setStage(stages[j+1], j+1);
                }
            }
        }else{
            pipelineSimulator.removeLast();
        }
    }

    public void print(){
        for(int i=0; i<pipelineSimulator.size(); i++){
            System.out.println(pipelineSimulator.get(i).getOp());
        }
        System.out.println();
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
        if(!pipelineSimulator.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public void solveHazard(int ind1, int ind2, int m){
        switch (m) {
            case 1://bolha
                Instruction b1 = new Bolha("NOP", null);
                pipelineSimulator.add(ind1, b1);
                Instruction b2 = new Bolha("NOP", null);
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