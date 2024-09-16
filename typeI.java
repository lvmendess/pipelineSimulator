public class typeI extends Instruction{
    private String rt;

    public typeI(String op, String rs, String rt, String ogInst, boolean ready) {
        super(op, rs, ogInst, ready);
        this.rt = rt;
        regs = new String[]{rs, rt};
        orderRegs();
    }

    @Override
    public void orderRegs(){
        int i = 0;
        String[] arr = null;
        while(i<5){
            if(i==0){//busca
                regByStage.add(null);
            }else if(i==1||i==2){ //decodificação e execução
                arr = new String[1];
                arr[0] = rt;
                regByStage.add(arr);
            }else if(i==3){ //acesso à memória
                arr = new String[1];
                arr[0] = rt;
                regByStage.add(arr);
            }else if(i==4){ //escrita no registrador
                arr = new String[1];
                arr[0] = rs;
                regByStage.add(arr);
            }
            i++;
        }
    }
    
}
