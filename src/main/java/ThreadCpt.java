import java.util.HashMap;
import java.util.List;

public class ThreadCpt extends Thread {

    public int index;
    public HashMap<String, Integer> map = new HashMap<>();
    public List<String> line;
    public List<String> fileLines;
    Utils utils = new Utils();
    public int nbThread;

    public ThreadCpt(int index, List<String> line) {
        this.index = index;
        this.line = line;
    }


    public ThreadCpt() {

    }

    public void run() {
        map = utils.compteurWord(line);
    }

    public String execMultiThread() {

        Utils utils = new Utils();

        ThreadCpt[] threadCpt = new ThreadCpt[nbThread];

        List<List<String>> alllines = utils.prepareLineThread(fileLines, nbThread);

//        System.out.println(alllines);

        int i = 0;
        while (i < nbThread) {
            threadCpt[i] = new ThreadCpt(i, alllines.get(i));
            i++;
        }

        i = 0;
        while (!alllines.get(i).isEmpty() && i < nbThread) {
            threadCpt[i].start();
            i++;
        }
        i = 0;
        while (!alllines.get(i).isEmpty() && i < nbThread) {
            try {
                threadCpt[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

        i = 0;
        while (!alllines.get(i).isEmpty() && i < nbThread) {
            utils.mergeMap(threadCpt[i].map);
            i++;
        }

        System.out.println("Compteur : multi-threaded");
        utils.setMap(utils.getFinalMap());
        String display = utils.displayMax(utils.getMax());
        return display;

//return "";
    }

    public static void main(String[] args) {
        ThreadCpt exec = new ThreadCpt();
        Utils u = new Utils();
        exec.nbThread = Integer.parseInt(args[0]);
        exec.fileLines = u.getFilelines(args[1]);
        exec.execMultiThread();
    }


}
