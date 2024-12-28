import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, ArrayList<Integer>> rules = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<List<Integer>> updates = new ArrayList<List<Integer>>();

        try (BufferedReader bin = new BufferedReader(new FileReader("data/day5.txt"))) {
            boolean separation = false;
            while (bin.ready()) {
                String line = bin.readLine();
                if (line.equals("")) {
                    separation = true;
                    continue;
                }

                if (!separation) {
                    String[] tokens = line.split("\\|");
                    if (!rules.containsKey(Integer.parseInt(tokens[0]))) {
                        rules.put(Integer.parseInt(tokens[0]), new ArrayList<Integer>());
                    }
                    rules.get(Integer.parseInt(tokens[0])).add(Integer.parseInt(tokens[1]));
                } else {
                    ArrayList<Integer> update = new ArrayList<Integer>();
                    String[] tokens = line.split(",");
                    for (String string : tokens) {
                        update.add(Integer.parseInt(string));
                    }
                    updates.add(update);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        boolean OK;
        int score = 0, score2 = 0;
        for (List<Integer> list : updates) {
            OK = true;
            for (int i = 0; i < list.size(); i++) {
                if (rules.containsKey(list.get(i))) {
                    for (int j = 0; j < i; j++) {
                        if (rules.get(list.get(i)).contains(list.get(j))) {
                            OK = false;
                            list.add(j, list.get(i));
                            list.remove(i+1);
                            i = 0;
                        }
                    }
                }
            }
            if (OK)
                score += list.get(list.size() / 2);
            else
                score2+= list.get(list.size() / 2);
        }
        System.out.println(score);
        System.out.println(score2);
    }
}
