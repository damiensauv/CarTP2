import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;

public class ThreadCpt extends Thread {

    int index;

    public ThreadCpt(int i) {
        index = i;
    }

    public void run() {
        System.out.println("Thread --> " + index);
    }

    public static List<List<String>> prepareLineThread(List<String> filelines, Integer nbThread) {

        int size = filelines.size() / nbThread;

        List<List<String>> allLines = Lists.partition(filelines, size);

        return allLines;
    }

    public static void main(String[] args) {

        List<String> fileLines;
        List<List<String>> alllines;
        HashMap<String, Integer> mapMot;
        HashMap<String, Integer> mapMot2;

        HashMap<String, Integer> finalMap = new HashMap<>();
        int nbThread = 2;

        // TODO : prendre en params le nb de thread

        fileLines = Sequentiel.getFilelines(args[0]);
        alllines = prepareLineThread(fileLines, nbThread);


        for (List<String> l : alllines){
            System.out.println(l);
        }

        /*
        mapMot = Sequentiel.compteurWord(alllines.get(0));
  //      Sequentiel.DebugMap(mapMot);

        mapMot2 = Sequentiel.compteurWord(alllines.get(1));
//        Sequentiel.DebugMap(mapMot2);

        finalMap.putAll(mapMot);
        finalMap.putAll(mapMot2);

        Sequentiel.DebugMap(finalMap);
*/
    }


}
