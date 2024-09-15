import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

public class LeitorTxt {
    private String diretorioArquivo;
    BufferedReader entradaDeDados;
    private StringTokenizer strK;
    
    public LeitorTxt(String diretorioArquivo) throws FileNotFoundException {

        this.diretorioArquivo = diretorioArquivo;
        this.entradaDeDados = new BufferedReader(new FileReader(diretorioArquivo));

    }

    public String[] proximaLinha() {
        String[] linhaFatiada = null;
        try {
            String linha = entradaDeDados.readLine(); //guardar linha original na instrução para facilitar escrita
            if (linha != null) {
                strK = new StringTokenizer(linha, " ,()");
                int count = 0;
                linhaFatiada = new String[strK.countTokens()];
                while (strK.hasMoreTokens()) {
                    linhaFatiada[count] = strK.nextToken();
                    count++;
                }
            } else {
                entradaDeDados.close();
                strK = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linhaFatiada;
    }
}
