import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sequentiel {

    public List<String> getFilelines(String filename) {
        List<String> fileLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                fileLines.add(line.toUpperCase());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileLines;
    }

    public HashMap<String, Integer> compteurWord(List<String> fileLines) {
        HashMap<String, Integer> mapMot = new HashMap<>();

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
        return mapMot;
    }


    public void main(String[] args) {

        List<String> fileLines;
        HashMap<String, Integer> mapMot;

        fileLines = this.getFilelines(args[0]);
        mapMot = this.compteurWord(fileLines);
        
        DebugMap(mapMot);
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
