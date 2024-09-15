import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        /*File diretorio = new File("testes\\");
        File[] arquivos = diretorio.listFiles();
        LeitorTxt leitorTxt;
        ArrayList<String[]> instrucoes = new ArrayList<>();
        Pipeline p;
    
        int cont = 0;

        for (File arquivo : arquivos) {
            System.out.println("ARQUIVO" + cont);
            leitorTxt = new LeitorTxt(arquivo.getAbsolutePath());
            String[] linhaFatiada = null;
            while ((linhaFatiada = leitorTxt.proximaLinha()) != null) {
                instrucoes.add(linhaFatiada);
                // popular ArrayList "instrucoes"
            }
            try {
                p = new Pipeline();
                p.createInstructions(instrucoes);
                //p.print();
            }catch(NullPointerException e){
                System.out.println(-1);
            }
            instrucoes.clear();
            cont++;
        }*/
        LeitorTxt leitorTxt = new LeitorTxt("testes\\TESTE-01.txt");
        ArrayList<String[]> instrucoes = new ArrayList<>();
        Pipeline p;
        try {
            String[] linhaFatiada = null;
            while ((linhaFatiada = leitorTxt.proximaLinha()) != null) {
                instrucoes.add(linhaFatiada);
            }
            /*for(int i=0; i<instrucoes.size(); i++){
                System.out.println(Arrays.toString(instrucoes.get(i)));
            }*/
            p = new Pipeline();
            p.createInstructions(instrucoes);
            //p.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}