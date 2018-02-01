import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        List<String> fileLines = new ArrayList<>();
        HashMap<String, Integer> mapMot = new HashMap<>();

        // Traiter l'exceion si pas de file en parametre
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;

            while ((line = br.readLine()) != null) {
                fileLines.add(line.toUpperCase());
            }

//            debugFile(fileLines);

            //TODO : Gere la ponctuation, Uppercase

            for (String line2 : fileLines) {
                String result[] = line2.split("[. \\n,!?:';]+");

                for (int x = 0; x < result.length; x++) {

                    if (result[x].length() > 0) {

                        Integer v = mapMot.get(result[x]);
                        if (v == null) {
                            v = mapMot.put(result[x], 1);
                        } else {
                            mapMot.put(result[x], mapMot.get(result[x]) + 1);
                        }
                    }
                }

            }

            DebugMap(mapMot);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void DebugMap(HashMap<String, Integer> mapMot) {

        for (Map.Entry<String, Integer> entry : mapMot.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("\"" + key + "\"" + " --> " + value);
        }
    }

    private static void debugFile(List<String> fileLines) {
        System.out.println("*----------------------------*");
        for (String line : fileLines) {
            System.out.println(line);
        }
        System.out.println("*----------------------------*");

    }


}
