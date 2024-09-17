import java.util.ArrayList;
public class Pipeline{
    //simular estágios de pipeline
    ArrayList<Instruction> pipelineSimulator;
    ArrayList<String> pipelineFinal;
    String[] stages;
    Dicionario dick;
    HazardDetector hd;
    int max;
    int method;

    public Pipeline(int method){
        pipelineSimulator = new ArrayList<Instruction>();
        pipelineFinal = new ArrayList<String>();
        stages = new String[]{"B", "D", "EX", "M", "EC"}; //busca, decodificação, execução, memória, escrita
        dick = new Dicionario();
        hd = new HazardDetector();
        max = 5;
        this.method = method;
    }

    public ArrayList<String> getPipelineFinal() {
        return pipelineFinal;
    }

    public void printInstructions(){
        for (String string : pipelineFinal) {
            System.out.println(string);
        }
    }

    public void insertInstructions(ArrayList<Instruction> instructions){
        for(Instruction inst : instructions){
            put(inst);
        }
        for (int i = 0; i < pipelineSimulator.size(); i++) {
            System.out.println("inseriu mais um");
            put(new Bolha());
        }
    }

    public void createInstructions(ArrayList<String[]> insts){
        for(String[] inst : insts){
            if(!inst[0].equals("NOP")){
                put(dick.search(inst));
            }
        }
        for (int i = 0; i < pipelineSimulator.size(); i++) {
            System.out.println("inseriu mais um");
            put(new Bolha());
        }
    }

    public void put(Instruction i){
        if(pipelineSimulator.isEmpty()){
            pipelineSimulator.add(i);
        }else{
            if(pipelineSimulator.size()>max){
                pipelineFinal.add(pipelineSimulator.getLast().getOgInst());
                pipelineSimulator.removeLast();
            }
            pipelineSimulator.add(0, i);
            advance();
            isHazard();
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
        if(pipelineSimulator.size()>=2){
            Instruction i1 = null;
            Instruction i2 = null;
            boolean r = false;
            for(int i=1; i<pipelineSimulator.size(); i++){
                i1 = pipelineSimulator.get(i);
                i2 = pipelineSimulator.get(i-1);
                HazardDetector hd = new HazardDetector();
                if(i1.getOp()!="NOP" && i2.getOp()!="NOP"){
                    switch (method) {
                        case 1: //bolha
                            r = hd.bubbleDetector(i1, i2);
                            if(r){
                                solveHazard(i, i-1, 1);
                                print();
                            }
                            break;
                        case 2: //bypass
                            int s = hd.bypassDetector(i1, i2);
                            System.out.println("É PEPINO "+s);
                            if(s==0){
                                solveHazard(i, i-1, 2);
                                print();
                            }else if (s == 1){
                                solveHazard(i, i-1, 3);
                                print();
                            }
                            break;

                        case 3: //all-in
                            int caso = 2; // caso que não faz nada
                            if (hd.bypassAndBubbleDetector(i1, i2) != 0) { // 1 ou -1
                                r = true;
                            }
                            if (hd.bypassAndBubbleDetector(i1, i2) == 1) {
                                caso = 3;
                            } else if (hd.bypassAndBubbleDetector(i1, i2) == -1) {
                                caso = 1;
                            }
                            if(r){
                                solveHazard(i, i-1, caso);
                                print();
                            }
                            break;
                        default:
                            System.out.println("novy nao precisa de entrada p nenhum show pq ele ja tem 2 por default");
                            break;
                    }
                    // method = 1 -> r = bubble()
                    // method = 2 -> r = bypass()
                    // method = 3 -> r = reorder()
                    // method = 4 -> r = reorder; solve() / r = bypass(); solve() / r = bubble(); solve()
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
                Instruction b1 = new Bolha();
                pipelineSimulator.add(ind1, b1);
                Instruction b2 = new Bolha();
                pipelineSimulator.add(ind1, b2);
                break;

            case 2: //TODO: adiantamento
                // adiantei o valor pra instrução seguinte, confia :)
                break;

            case 3: //TODO: adiantamento c/ bolha
                Instruction b3 = new Bolha();
                pipelineSimulator.add(ind1, b3);
                break;
            
            default:
                break;
        }
    }
}