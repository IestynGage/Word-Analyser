import java.util.HashMap;

public class Application {

    private HashMap<String, Integer> totalWordCount = new HashMap<>();

    public void setTotalWordCount(){
        PageReader pr1 = new PageReader("C:\\Users\\iesty\\Desktop\\Java\\WordAnalyser\\src\\hello1.txt");
        PageReader pr2 = new PageReader("C:\\Users\\iesty\\Desktop\\Java\\WordAnalyser\\src\\hello.txt");

        Thread thread1 = new Thread(pr1);
        Thread thread2 = new Thread(pr2);

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HashMap<String, Integer> tempWordCound = pr1.getWordCounter();
        for (String i : tempWordCound.keySet()) {
            if(totalWordCount.get(i)!=null){
                totalWordCount.replace(i,totalWordCount.get(i) + tempWordCound.get(i));
            } else{
                totalWordCount.put(i,tempWordCound.get(i));
            }
        }

        tempWordCound = pr2.getWordCounter();
        for (String i : tempWordCound.keySet()) {
            if(totalWordCount.get(i)!=null){
                totalWordCount.replace(i,totalWordCount.get(i) + tempWordCound.get(i));
            } else{
                totalWordCount.put(i,tempWordCound.get(i));
            }
        }

        for (String i : totalWordCount.keySet()) {
            System.out.println("key: " + i + " value: " + totalWordCount.get(i));
        }
    }
    public static void main(String[] arg){
        Application theApp = new Application();
        theApp.setTotalWordCount();


    }
}
