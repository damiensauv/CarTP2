import java.util.HashMap;
import java.util.List;

public class Sequentiel {

    public static void main(String[] args) {
        Utils utils = new Utils();
        List<String> fileLines = null;

        // On Recupere le fichier
        try {
            fileLines = utils.getFilelines(args[0]);
        } catch (Exception e) {
            System.out.println("Bad parameter");
            System.exit(-1);
        }
        // On Compte le nombre d'occurence des mots
        HashMap<String, Integer> map = utils.compteurWord(fileLines);
        utils.setMap(map);
        System.out.println("Compteur : Sequentiel");
        // On affiche le resultat
        utils.displayMax(utils.getMax());
    }
}
