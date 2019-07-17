import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class PageReader implements Runnable{

    private String fileName;
    private HashMap<String, Integer> wordCounter = new HashMap<>();

    public PageReader(String txtFileName){
        this.fileName = txtFileName;
    }

    public void openFile() throws IOException {
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(fileName));
            Scanner a = new Scanner(br);
            a.delimiter();

            String line;

            while (a.hasNext()) {
                line = a.next();

                if(wordCounter.get(line)!=null){
                    wordCounter.replace(line,wordCounter.get(line)+1);
                } else{
                    wordCounter.put(line,1);
                }

            }
         } catch (IOException e){

        }

        br.close();
    }

    @Override
    public void run() {
        try {
            openFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> getWordCounter(){
        return wordCounter;
    }
}
