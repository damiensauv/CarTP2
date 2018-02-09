import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    private HashMap<String, Integer> map = new HashMap<>();
    private HashMap<String, Integer> finalMap = new HashMap<>();

    public Map.Entry<String, Integer> getMax() {
        Map.Entry<String, Integer> max = null;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (max == null || entry.getValue().compareTo(max.getValue()) > 0) {
                max = entry;
            }
        }
        return max;
    }

    public void displayMax(Map.Entry<String, Integer> entry) {
        System.out.println("Le mot max est " + entry.getKey() + " qui apparait " + entry.getValue() + " fois.");
    }

    public List<String> getFilelines(String filename) {
        List<String> fileLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                fileLines.add(line.toUpperCase());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileLines;
    }

    public List<List<String>> prepareLineThread(List<String> filelines, Integer nbThread) {

        int size = filelines.size() / nbThread + 1;

        List<List<String>> allLines = Lists.partition(filelines, size);

        return allLines;
    }

    public void mergeMap(HashMap<String, Integer> mapToMerge) {
        mapToMerge.forEach((k, v) -> finalMap.merge(k, v, (v1, v2) -> v1 + v2));
    }

    public HashMap<String, Integer> getFinalMap() {
        return finalMap;
    }

    public HashMap<String, Integer> compteurWord(List<String> fileLines) {

        HashMap<String, Integer> mapcpt = new HashMap<>();

        for (String line2 : fileLines) {
            String result[] = line2.split("[. \\n,!?:';]+");

            for (int x = 0; x < result.length; x++) {

                if (result[x].length() > 0) {

                    Integer v = mapcpt.get(result[x]);
                    if (v == null) {
                        v = mapcpt.put(result[x], 1);
                    } else {
                        mapcpt.put(result[x], mapcpt.get(result[x]) + 1);
                    }
                }
            }
        }
        return mapcpt;
    }


    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

}
