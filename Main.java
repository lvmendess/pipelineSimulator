import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File diretorio = new File("testes\\");
        File[] arquivos = diretorio.listFiles();
        LeitorTxt leitorTxt;
        EscritorTxt escritor = new EscritorTxt();
        ArrayList<String[]> instrucoes = new ArrayList<>();
        Pipeline p;
    
        //int cont = 1;

        for (File arquivo : arquivos) {
            String name = arquivo.getName();
            System.out.println(arquivo.getName());
            leitorTxt = new LeitorTxt(arquivo.getAbsolutePath());
            String[] linhaFatiada = null;
            while ((linhaFatiada = leitorTxt.proximaLinha()) != null) {
                instrucoes.add(linhaFatiada);
                // popular ArrayList "instrucoes"
            }
            try {
                int method = 0;
                if(name.equals("TESTE-01.txt")||name.equals("TESTE-02.txt")){
                    method = 1;
                }else if(name.equals("TESTE-03.txt")||name.equals("TESTE-04.txt")){
                    method = 2;
                }else if(name.equals("TESTE-05.txt")||name.equals("TESTE-06.txt")||name.equals("TESTE-07.txt")){
                    method = 1;
                }else if(name.equals("TESTE-08.txt")||name.equals("TESTE-09.txt")||name.equals("TESTE-10.txt")){
                    method = 2;
                }else{
                    throw new Exception("Unexpected File Name");
                }
                p = new Pipeline(method);
                p.createInstructions(instrucoes);
                escritor.write(p.getPipelineFinal(), arquivo.getName());
            }catch(NullPointerException e){
                System.out.println(-1);
            }catch (Exception e) {
                System.out.println("o nome do arquivo de teste nao ta igual oq vc falou q seria, novy");
            }
            instrucoes.clear();
        }
        /*LeitorTxt leitorTxt = new LeitorTxt("testes\\TESTE-01.txt");
        ArrayList<String[]> instrucoes = new ArrayList<>();
        Pipeline p;
        try {
            String[] linhaFatiada = null;
            while ((linhaFatiada = leitorTxt.proximaLinha()) != null) {
                instrucoes.add(linhaFatiada);
            }
            for(int i=0; i<instrucoes.size(); i++){
                System.out.println(Arrays.toString(instrucoes.get(i)));
            }
            p = new Pipeline();
            p.createInstructions(instrucoes);
            p.printInstructions();
        }catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}