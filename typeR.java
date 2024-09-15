public class typeR extends Instruction{
    private String rd, rt;

    public typeR(String op, String rd, String rs, String rt, String ogInst) {
        super(op, rs, ogInst);
        this.rd = rd;
        this.rt = rt;
        regs = new String[]{rd, rs, rt};
        orderRegs();
    }

    @Override
    public void orderRegs(){
        int i = 0;
        String[] arr = null;
        while(i<5){
            if(i==0||i==3){//busca e acesso à memória
                regByStage.add(null);
            }else if(i==1||i==2){//decodificação e execução
                arr = new String[2];
                arr[0] = rs;
                arr[1] = rt;
                regByStage.add(arr);
            }else if(i==4){//escrita no registrador
                arr = new String[1];
                arr[0] = rd;
                regByStage.add(arr);
            }
            i++;
        }
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    
}
