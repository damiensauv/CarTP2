import java.util.HashMap;
import java.util.List;

public class Sequentiel {

    public static void main(String[] args) {
        Utils utils = new Utils();

        List<String> fileLines = utils.getFilelines(args[0]);
        HashMap<String, Integer> map = utils.compteurWord(fileLines);
        utils.setMap(map);

        System.out.println("Compteur : Sequentiel");
        utils.displayMax(utils.getMax());

    }
}
