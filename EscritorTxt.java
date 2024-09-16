import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class EscritorTxt {
    public void write(ArrayList<String> result, int n){
        try {
            String num = null;
            if(n<10){
                num = "0" + n;
            }else{
                num = String.format("%d", n);
            }
            
            File file = new File("testes\\TESTE-" + num + "-RESULTADO.txt");
            
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            StringBuilder str = new StringBuilder();
            for (String string : result) {
                str.append(string+"\n");
            }
            String w = str.toString();
            bufferedWriter.write(w, 0, w.length());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
