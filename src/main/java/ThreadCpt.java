import java.util.HashMap;
import java.util.List;

public class ThreadCpt extends Thread {

    public int index;
    public HashMap<String, Integer> map = new HashMap<>();
    public List<String> line;
    Utils utils = new Utils();

    public ThreadCpt(int index, List<String> line) {
        this.index = index;
        this.line = line;
    }

    public ThreadCpt() {

    }

    public void run() {
        map = utils.compteurWord(line);
    }

    public void execMultiThread(String[] args){
        Utils utils = new Utils();

        int nbThread = Integer.parseInt(args[0]);

        ThreadCpt[] threadCpt = new ThreadCpt[nbThread];

        List<String> fileLines = utils.getFilelines(args[1]);
        List<List<String>> alllines = utils.prepareLineThread(fileLines, nbThread);

        int i = 0;
        while (!alllines.get(i).isEmpty() && i < nbThread) {
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
        utils.displayMax(utils.getMax());

    }

    public static void main(String[] args) {
        ThreadCpt exec = new ThreadCpt();
        exec.execMultiThread(args);
    }


}
