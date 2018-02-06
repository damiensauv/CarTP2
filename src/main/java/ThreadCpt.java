import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadCpt extends Thread {

    int index;

    public ThreadCpt(int i) {
        index = i;
    }

    public void run() {
        System.out.println("Thread --> " + index);
    }

    public static List<List<String>> lineThread(List<String> filelines, Integer nbThread) {

        int size = (filelines.size() / nbThread) + 1;

        List<List<String>> allLines = IntStream.range(0, nbThread)
                .mapToObj(i -> filelines.subList(i * size,  filelines.size()))
                .collect(Collectors.toList());

        return allLines;
    }

    public static void main(String[] args) {

        List<String> fileLines;
        List<List<String>> alllines;
        HashMap<String, Integer> mapMot;
        int nbThread = 2;

        // TODO : prendre en params le nb de thread

        fileLines = Sequentiel.getFilelines(args[0]);
        alllines = lineThread(fileLines, nbThread);


        for (List<String> line : alllines){

            System.out.println("----------");
            System.out.println(line);
            System.out.println("-----------");
        }



//        mapMot = Sequentiel.compteurWord(fileLines);


    }


}
